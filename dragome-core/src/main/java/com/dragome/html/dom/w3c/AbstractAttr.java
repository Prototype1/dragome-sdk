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
package com.dragome.html.dom.w3c;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

public class AbstractAttr implements Attr
{
	public AbstractAttr()
	{
	}
	public String getNodeName()
	{
		return null;
	}
	public String getNodeValue() throws DOMException
	{
		return null;
	}
	public void setNodeValue(String nodeValue) throws DOMException
	{
	}
	public short getNodeType()
	{
		return 0;
	}
	public Node getParentNode()
	{
		return null;
	}
	public NodeList getChildNodes()
	{
		return null;
	}
	public Node getFirstChild()
	{
		return null;
	}
	public Node getLastChild()
	{
		return null;
	}
	public Node getPreviousSibling()
	{
		return null;
	}
	public Node getNextSibling()
	{
		return null;
	}
	public NamedNodeMap getAttributes()
	{
		return null;
	}
	public Document getOwnerDocument()
	{
		return null;
	}
	public Node insertBefore(Node newChild, Node refChild) throws DOMException
	{
		return null;
	}
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException
	{
		return null;
	}
	public Node removeChild(Node oldChild) throws DOMException
	{
		return null;
	}
	public Node appendChild(Node newChild) throws DOMException
	{
		return null;
	}
	public boolean hasChildNodes()
	{
		return false;
	}
	public Node cloneNode(boolean deep)
	{
		return null;
	}
	public void normalize()
	{
	}
	public boolean isSupported(String feature, String version)
	{
		return false;
	}
	public String getNamespaceURI()
	{
		return null;
	}
	public String getPrefix()
	{
		return null;
	}
	public void setPrefix(String prefix) throws DOMException
	{
	}
	public String getLocalName()
	{
		return null;
	}
	public boolean hasAttributes()
	{
		return false;
	}
	public String getBaseURI()
	{
		return null;
	}
	public short compareDocumentPosition(Node other) throws DOMException
	{
		return 0;
	}
	public String getTextContent() throws DOMException
	{
		return null;
	}
	public void setTextContent(String textContent) throws DOMException
	{
	}
	public boolean isSameNode(Node other)
	{
		return false;
	}
	public String lookupPrefix(String namespaceURI)
	{
		return null;
	}
	public boolean isDefaultNamespace(String namespaceURI)
	{
		return false;
	}
	public String lookupNamespaceURI(String prefix)
	{
		return null;
	}
	public boolean isEqualNode(Node arg)
	{
		return false;
	}
	public Object getFeature(String feature, String version)
	{
		return null;
	}
	public Object setUserData(String key, Object data, UserDataHandler handler)
	{
		return null;
	}
	public Object getUserData(String key)
	{
		return null;
	}
	public String getName()
	{
		return null;
	}
	public boolean getSpecified()
	{
		return false;
	}
	public String getValue()
	{
		return null;
	}
	public void setValue(String value) throws DOMException
	{
	}
	public Element getOwnerElement()
	{
		return null;
	}
	public TypeInfo getSchemaTypeInfo()
	{
		return null;
	}
	public boolean isId()
	{
		return false;
	}
}
