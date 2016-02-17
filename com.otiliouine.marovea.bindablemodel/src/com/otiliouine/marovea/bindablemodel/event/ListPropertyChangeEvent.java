package com.otiliouine.marovea.bindablemodel.event;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;

public class ListPropertyChangeEvent extends PropertyChangeEvent{
	
	public static final int LIST_UPDATE = 0;
	public static final int ELEMENT_ADD = 1;
	public static final int ELEMENT_UPDATE = 2;
	public static final int ELEMENT_REMOVE = 3;

	private int eventType;
	
	public ListPropertyChangeEvent(int eventType, Object source, String propertyName,
			Object oldValue, Object newValue) {
		super(source, propertyName, oldValue, newValue);
		if (eventType < 0 || eventType > 3) {
			throw new IllegalArgumentException("invalid ListPropertyChangeEvent's type, type " + eventType + " is not allowed");
		}
		this.eventType = eventType;
	}

	public int getEventType() {
		return eventType;
	}

}
