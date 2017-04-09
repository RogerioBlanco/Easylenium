package org.easylenium.core.flow.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value=ElementType.FIELD)
public @interface RequiredInformation
{
	String key() default "";
}
