package org.easylenium.core.testcase.xml;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.beanutils.BeanUtils;
import org.easylenium.core.testcase.xml.exception.ConvertNodeToPojoException;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.core.ValueRequiredException;

public class SimpleConvertXML {

	private static final Persister PERSISTER = new Persister();

	public <T> T convert(Class<? extends T> dataClass, String source) {
		try {
			return PERSISTER.read(dataClass, source);
		} catch (ValueRequiredException ex) {
			throw new RuntimeException(ex);
		} catch (Exception e) {
			throw new ConvertNodeToPojoException("It was not possible to convert the node to pojo.");
		}
	}

	public <T> T convert(Class<? extends T> dataClass, String source, T template) {
		T instance = convert(dataClass, source);

		try {
			@SuppressWarnings("unchecked")
			T target = (T) BeanUtils.cloneBean(template);

			for (Field field : getInheritedFields(dataClass)) {
				field.setAccessible(true);
				if (field.get(instance) != null) {
					field.set(target, field.get(instance));
				}
			}

			return target;

		} catch (Exception e) {
			throw new ClonePropertiesException(e, "It was not possible to clone the properties", dataClass);
		}
	}

	private Collection<Field> getInheritedFields(Class<?> clazz) {
		Collection<Field> fields = Collections.emptyList();

		for (Class<?> c = clazz; c != null; c = c.getSuperclass())
			fields.addAll(Arrays.asList(c.getDeclaredFields()));

		return fields;
	}
}
