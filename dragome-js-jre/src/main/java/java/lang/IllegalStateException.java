/*******************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 * 
 *  This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
/* Copyright 1998, 2002 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

/**
 * This runtime exception is thrown when an action is attempted at a time when
 * the virtual machine is not in the correct state.
 */
public class IllegalStateException extends RuntimeException
{

	private static final long serialVersionUID= -1848914673093119416L;

	/**
	 * Constructs a new instance of this class with its walkback filled in.
	 */
	public IllegalStateException()
	{
		super();
	}

	/**
	 * Constructs a new instance of this class with its walkback and message
	 * filled in.
	 * 
	 * @param detailMessage
	 *            String The detail message for the exception.
	 */
	public IllegalStateException(String detailMessage)
	{
		super(detailMessage);
	}

	/**
	 * <p>Constructs a new instance with a message and cause.</p>
	 * @param message The message to assign to this exception.
	 * @param cause The optional cause of this exception; may be <code>null</code>.
	 * @since 1.5
	 */
	public IllegalStateException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * <p>Constructs a new instance with a cause.</p>
	 * @param cause The optional cause of this exception; may be <code>null</code>.
	 * @since 1.5
	 */
	public IllegalStateException(Throwable cause)
	{
		super((cause == null ? null : cause.toString()), cause);
	}
}