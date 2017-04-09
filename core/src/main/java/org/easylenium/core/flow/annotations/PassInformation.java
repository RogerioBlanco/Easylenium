package org.easylenium.core.flow.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface PassInformation
{
	String key() default "";
	
	boolean override() default false;
}
