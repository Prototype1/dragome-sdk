package com.dragome.html.dom.w3c;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

public class MouseEventImpl extends EventImpl implements MouseEvent
{
	private int clientX;
	private int clientY;
	private boolean shiftKey;

	public MouseEventImpl(String eventType)
	{
		super(eventType);
		// TODO Auto-generated constructor stub
	}

	public MouseEventImpl(String eventType, int clientX, int clientY, boolean shiftKey)
	{ 
		this(eventType);
		this.clientX= clientX;
		this.clientY= clientY;
		this.shiftKey= shiftKey;
	}

	public int getDetail()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractView getView()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initUIEvent(String arg0, boolean arg1, boolean arg2, AbstractView arg3, int arg4)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getAltKey()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public short getButton()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getClientX()
	{
		return clientX;
	}

	@Override
	public int getClientY()
	{
		// TODO Auto-generated method stub
		return clientY;
	}

	@Override
	public boolean getCtrlKey()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getMetaKey()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EventTarget getRelatedTarget()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScreenX()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScreenY()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getShiftKey()
	{
		return shiftKey;
	}

	@Override
	public void initMouseEvent(String arg0, boolean arg1, boolean arg2, AbstractView arg3, int arg4, int arg5, int arg6, int arg7, int arg8, boolean arg9, boolean arg10, boolean arg11, boolean arg12, short arg13, EventTarget arg14)
	{
		// TODO Auto-generated method stub
		
	}
}
