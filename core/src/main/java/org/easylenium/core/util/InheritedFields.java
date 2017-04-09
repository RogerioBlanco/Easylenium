package org.easylenium.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class InheritedFields
{
	private Class<?> clazz;

	public InheritedFields(Class<?> clazz)
	{
		this.clazz = clazz;
	}

	public Collection<Field> getAll()
	{
		Collection<Field> fields = new ArrayList<Field>();

		for (Class<?> c = clazz; c != null; c = c.getSuperclass())
			fields.addAll(Arrays.asList(c.getDeclaredFields()));

		return fields;
	}
}
