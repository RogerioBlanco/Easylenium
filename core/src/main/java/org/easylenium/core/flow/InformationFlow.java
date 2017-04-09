package org.easylenium.core.flow;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.easylenium.core.executor.Executor;
import org.easylenium.core.flow.annotations.PassInformation;
import org.easylenium.core.flow.annotations.RequiredInformation;
import org.easylenium.core.flow.exceptions.FlowInformationWithIncompatibleTypes;
import org.easylenium.core.flow.exceptions.RequiredInformationException;
import org.easylenium.core.flow.exceptions.FieldValueException;
import org.easylenium.core.suitetest.Scenario;
import org.easylenium.core.util.InheritedFields;

public class InformationFlow
{
	private HashMap<String, Object> flow;

	private Scenario scenario;

	public InformationFlow(Scenario scenario)
	{
		this.flow = new HashMap<String, Object>();
		this.scenario = scenario;
	}

	public void validateRequired(Executor executor)
	{
		Collection<Field> fields = new InheritedFields(executor.getClass()).getAll();

		for (Field field : fields)
		{
			if (field.isAnnotationPresent(RequiredInformation.class))
			{
				String key = field.getAnnotation(RequiredInformation.class).key();

				if (!StringUtils.isNotBlank(key))
					key = defaultKey(field);

				Object value = flow.get(key);

				if (value == null)
					throw new RequiredInformationException(
							"The step '%s' of the scenario '%s' require information in the field '%s', but don't have any previous step who fill this information.",
							executor.getClass(), scenario.getName(), field.getName());

				if (!field.getClass().equals(value.getClass()))
					throw new FlowInformationWithIncompatibleTypes("The field '%s' of step '%s' is incompatible of the value type. Is expecting the type '%s', but received '%s'.",
							executor.getClass(), scenario.getName(), field.getClass(), value.getClass());
			}
		}
	}

	public void initSetupExecutor(Executor executor)
	{
		Collection<Field> fields = new InheritedFields(executor.getClass()).getAll();

		for (Field field : fields)
		{
			if (field.isAnnotationPresent(RequiredInformation.class))
			{
				String key = field.getAnnotation(RequiredInformation.class).key();

				if (!StringUtils.isNotBlank(key))
					key = defaultKey(field);

				Object value = flow.get(key);

				field.setAccessible(true);

				try
				{
					field.set(executor, value);
				} catch (Exception e)
				{
					throw new FieldValueException(e, "It wasn't possible to set the propertie '%s' of the class '%s'.", field.getName(), executor.getClass());
				}
			}
		}
	}

	public void retrieveInformation(Executor executor)
	{
		Collection<Field> fields = new InheritedFields(executor.getClass()).getAll();

		for (Field field : fields)
		{
			if (field.isAnnotationPresent(PassInformation.class))
			{
				try
				{
					Object value = field.get(executor);

					PassInformation pass = field.getAnnotation(PassInformation.class);

					String key = pass.key();

					if (!StringUtils.isNotBlank(key))
						key = defaultKey(field);

					if (flow.get(key) == null || pass.override())
						flow.put(key, value);
					
				} catch (Exception e)
				{
					throw new FieldValueException(e, "It wasn't possible to get the propertie '%s' of the class '%s'.", field.getName(), executor.getClass());
				}
			}
		}
	}

	private String defaultKey(Field field)
	{
		return String.format("%s#%s", field.getName(), field.getType().getName());
	}
}
