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
package com.dragome.render.html.components;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.KeyboardEvent;

import com.dragome.model.EventDispatcherImpl;
import com.dragome.model.interfaces.VisualComponent;
import com.dragome.model.listeners.BlurListener;
import com.dragome.model.listeners.ClickListener;
import com.dragome.model.listeners.DoubleClickListener;
import com.dragome.model.listeners.InputListener;
import com.dragome.model.listeners.KeyDownListener;
import com.dragome.model.listeners.KeyPressListener;
import com.dragome.model.listeners.KeyUpListener;
import com.dragome.model.listeners.ListenerChanged;
import com.dragome.model.listeners.MouseDownListener;
import com.dragome.model.listeners.MouseOutListener;
import com.dragome.model.listeners.MouseOverListener;
import com.dragome.model.listeners.MouseUpListener;
import com.dragome.remote.entities.DragomeEntityManager;
import com.dragome.render.interfaces.ComponentRenderer;

public abstract class AbstractHTMLComponentRenderer<T> implements ComponentRenderer<Element, T>
{
	private static final String KEYPRESS= "keypress";
	private static final String KEYDOWN= "keydown";
	private static final String KEYUP= "keyup";
	private static final String INPUT= "input";
	private static final String BLUR= "blur";
	private static final String MOUSEOUT= "mouseout";
	private static final String MOUSEOVER= "mouseover";
	private static final String MOUSEDOWN= "mousedown";
	private static final String MOUSEUP= "mouseup";
	private static final String DBLCLICK= "dblclick";
	private static final String CLICK= "click";
	public static final String COMPONENT_ID_ATTRIBUTE= "data-component-id";
	
	private List<String> listeners= new ArrayList<String>();

	public AbstractHTMLComponentRenderer()
	{
	}

	public void addListeners(final VisualComponent visualComponent, final Element element)
	{
		element.setAttribute(COMPONENT_ID_ATTRIBUTE, DragomeEntityManager.add(visualComponent));
		
		visualComponent.addListener(ListenerChanged.class, new ListenerChanged()
		{
			public <T extends EventListener> void listenerAdded(Class<? extends T> type, T listener)
			{
				addListeners(visualComponent, element, type);
			}

			public <T extends EventListener> void listenerRemoved(Class<? extends T> type, T listener)
			{
			}
		});

		addListeners(visualComponent, element, null);

		visualComponent.getStyle().fireStyleChanged();
	}

	private void addListeners(final VisualComponent visualComponent, final Element element, Class<? extends EventListener> expectedType)
	{
		addListener(visualComponent, element, ClickListener.class, CLICK, expectedType);
		addListener(visualComponent, element, DoubleClickListener.class, DBLCLICK, expectedType);
		addListener(visualComponent, element, KeyUpListener.class, KEYUP, expectedType);
		addListener(visualComponent, element, KeyDownListener.class, KEYDOWN, expectedType);
		addListener(visualComponent, element, KeyPressListener.class, KEYPRESS, expectedType);
		addListener(visualComponent, element, InputListener.class, INPUT, expectedType);
		addListener(visualComponent, element, MouseOverListener.class, MOUSEOVER, expectedType);
		addListener(visualComponent, element, MouseOutListener.class, MOUSEOUT, expectedType);
		addListener(visualComponent, element, BlurListener.class, BLUR, expectedType);

		EventDispatcherImpl.setEventListener(element, new org.w3c.dom.events.EventListener()
		{
			public void handleEvent(Event event)
			{
				String type= event.getType();

				if (type.equals(CLICK))
					visualComponent.getListener(ClickListener.class).clickPerformed(visualComponent);
				else if (type.equals(DBLCLICK))
					visualComponent.getListener(DoubleClickListener.class).doubleClickPerformed(visualComponent);
				else if (type.equals(MOUSEOVER))
					visualComponent.getListener(MouseOverListener.class).mouseOverPerformed(visualComponent);
				else if (type.equals(MOUSEOUT))
					visualComponent.getListener(MouseOutListener.class).mouseOutPerformed(visualComponent);
				else if (type.equals(MOUSEDOWN))
					visualComponent.getListener(MouseDownListener.class).mouseDownPerformed(null);
				else if (type.equals(MOUSEUP))
					visualComponent.getListener(MouseUpListener.class).mouseUpPerformed(visualComponent);
				else if (type.equals(BLUR))
					visualComponent.getListener(BlurListener.class).blurPerformed(visualComponent);
				else if (type.equals(INPUT))
					visualComponent.getListener(InputListener.class).inputPerformed(visualComponent);

				else if (event instanceof KeyboardEvent)
				{
					KeyboardEvent keyboardEvent= (KeyboardEvent) event;
					int keyId= Integer.parseInt(keyboardEvent.getKeyIdentifier());
					if (type.equals(KEYUP))
						visualComponent.getListener(KeyUpListener.class).keyupPerformed(visualComponent, keyId);
					else if (type.equals(KEYDOWN))
						visualComponent.getListener(KeyDownListener.class).keydownPerformed(visualComponent, keyId);
					else if (type.equals(KEYPRESS))
						visualComponent.getListener(KeyPressListener.class).keypressPerformed(visualComponent, keyId);
				}

			}
		}, listeners.toArray(new String[0]));
	}
	protected void addListener(final VisualComponent visualComponent, final Element element, Class<? extends EventListener> listenerType, String jsAttributeName, Class<? extends EventListener> expectedType)
	{
		if (visualComponent.hasListener(listenerType) && (expectedType == null || expectedType.equals(listenerType)))
			listeners.add(jsAttributeName);

		//			element.setAttribute(jsAttributeName, "_ed.onEvent()");
	}
}
