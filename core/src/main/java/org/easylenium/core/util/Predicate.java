package org.easylenium.core.util;

import java.util.ArrayList;
import java.util.Collection;

public class Predicate
{

	public static <T> Collection<T> filter(Collection<T> target, IPredicate<T> predicate)
	{
		Collection<T> result = new ArrayList<T>();

		for (T element : target)
			if (predicate.apply(element))
				result.add(element);

		return result;
	}

	public static <T> Collection<T> filterInverse(Collection<T> target, IPredicate<T> predicate)
	{
		Collection<T> result = new ArrayList<T>();

		for (T element : target)
			if (!predicate.apply(element))
				result.add(element);

		return result;
	}
}
