/*******************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 * 
 * This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.dragome.render.canvas.interfaces;

public interface Canvas<ContentType>
{
	public void setContent(ContentType object);
	public ContentType getContent();
	public void replaceSection(String anAlias, Canvas<?> aCanvas);
}
