package org.easylenium.core.xml.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.easylenium.core.xml.exception.ConvertNodeToPojoException;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.core.ValueRequiredException;

public class SimpleConvertXML
{

	private static final Persister PERSISTER = new Persister();

	public <T> T convert(Class<? extends T> dataClass, String source)
	{
		try
		{
			return PERSISTER.read(dataClass, source, Boolean.FALSE);
		} catch (ValueRequiredException ex)
		{
			throw new RuntimeException(ex);
		} catch (Exception e)
		{
			throw new ConvertNodeToPojoException("It wasn't possible to convert the node to POJO.");
		}
	}

	public <T> T convert(Class<? extends T> dataClass, String source, T template)
	{
		T instance = convert(dataClass, source);

		try
		{
			@SuppressWarnings("unchecked")
			T target = (T) BeanUtils.cloneBean(template);

			for (Field field : getInheritedFields(dataClass))
			{
				field.setAccessible(true);
				if (field.get(instance) != null)
				{
					field.set(target, field.get(instance));
				}
			}

			return target;

		} catch (Exception e)
		{
			throw new ClonePropertiesException(e, "It wasn't possible to clone the properties", dataClass);
		}
	}

	private Collection<Field> getInheritedFields(Class<?> clazz)
	{
		Collection<Field> fields = new ArrayList();

		for (Class<?> c = clazz; c != null; c = c.getSuperclass())
			fields.addAll(Arrays.asList(c.getDeclaredFields()));

		return fields;
	}
}
