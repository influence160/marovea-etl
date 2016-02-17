package com.otiliouine.marovea.bindablemodel;

import java.beans.PropertyChangeSupport;

import com.otiliouine.marovea.core.model.Fragment;

public class BindableFragment extends Fragment{	
	protected final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	
	public BindableFragment(String text) {
		super();
		setText(text);
	}

	@Override
	public void setText(String text) {
		String oldValue = getText();
		super.setText(text);
		propertyChangeSupport.firePropertyChange("text", oldValue, getText());
	}

}
