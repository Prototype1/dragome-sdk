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
package com.dragome.forms.bindings.builders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.dragome.commons.compiler.annotations.CompilerType;
import com.dragome.commons.compiler.annotations.DragomeCompilerSettings;

public class BindingSync
{
	static Queue<MethodVisitedEvent> events= new LinkedList<MethodVisitedEvent>();
	private static List<ValueModelDelegator<?>> conditions= new ArrayList<ValueModelDelegator<?>>();
	static boolean firing;
	public static Stack<NullMutableValueModel<?>> recordingFor= new Stack<NullMutableValueModel<?>>();

	@DragomeCompilerSettings(CompilerType.Strict)
	public static void fireChanges()
	{
		if (!firing)
		{
			try
			{
				firing= true;

				MethodVisitedEvent event= events.peek();
				while (event != null)
				{
					List<ValueModelDelegator<?>> conditions2= new ArrayList<ValueModelDelegator<?>>(conditions);
					for (ValueModelDelegator<?> valueModelDelegator : conditions2)
					{
						if (valueModelDelegator.getValueSource() instanceof NullMutableValueModel)
						{
							NullMutableValueModel<Object> nullMutableValueModel= (NullMutableValueModel) valueModelDelegator.getValueSource();
							List<MethodVisitedEvent> methodVisitedEvents= new ArrayList<MethodVisitedEvent>(nullMutableValueModel.getMethodVisitedEvents());
							for (MethodVisitedEvent methodVisitedEvent : methodVisitedEvents)
								if (methodVisitedEvent.isSameProperty(event))
									valueModelDelegator.fireValueChangeEvent();
						}
					}

					events.poll();
					event= events.peek();
				}
			}
			finally
			{
				firing= false;
			}
		}
	}

	public static <V> ValueModelDelegator<V> createCondition(final Supplier<V> object)
	{
		ValueModelDelegator<V> condition= new ValueModelDelegator<V>(new NullMutableValueModel<V>()
		{
			public V getDelegatedValue()
			{
				return object.get();
			}
		});

		addCondition(condition);
		return (ValueModelDelegator<V>) condition;
	}

	public static void addCondition(ValueModelDelegator<?> condition)
	{
		conditions.add(condition);
	}

	public static void addEvent(MethodVisitedEvent methodVisitedEvent)
	{
		if (!events.contains(methodVisitedEvent))
			events.add(methodVisitedEvent);
	}

}
