package com.dragome.compiler.task;

import com.dragome.compiler.ast.TypeDeclaration;
import com.dragome.compiler.exceptions.FatalParseException;
import com.dragome.compiler.exceptions.ServerInClientCode;

public interface Parsable<T>
{
	public TypeDeclaration parse() throws ServerInClientCode, FatalParseException;

	public void prepareParsing(T t) throws Exception;
}
