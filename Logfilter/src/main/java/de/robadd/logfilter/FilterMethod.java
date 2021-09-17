package de.robadd.logfilter;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import de.robadd.logfilter.ui.filter.FilterPanel;

@Retention(RUNTIME)
@Target(METHOD)
public @interface FilterMethod
{
	public Class<?> value() default FilterPanel.class;

	public FilterMethodType type() default FilterMethodType.VALUE;
}
