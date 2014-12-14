package com.dragome.compiler.task;

import java.util.List;

import com.dragome.compiler.ast.TypeDeclaration;
import com.dragome.compiler.exceptions.FatalParseException;
import com.dragome.compiler.exceptions.ServerInClientCode;

public interface Parsable<R, T>
{
	public List<R> parse() throws ServerInClientCode, FatalParseException;

	public void prepareParsing(T t) throws Exception;
}
