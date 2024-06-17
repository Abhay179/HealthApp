package com.HealthApp.HealthApp.Authority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // The annotation can be applied to methods
@Retention(RetentionPolicy.RUNTIME)
public @interface IsProvider{

}