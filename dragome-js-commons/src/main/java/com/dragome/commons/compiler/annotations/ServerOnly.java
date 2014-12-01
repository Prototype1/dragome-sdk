package com.dragome.commons.compiler.annotations;

public @interface ServerOnly
{
	String reason() default "Will be ignored by JS Compiler"; 
}
