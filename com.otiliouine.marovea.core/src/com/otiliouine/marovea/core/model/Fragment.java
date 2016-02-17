package com.otiliouine.marovea.core.model;

import java.io.Serializable;
import java.text.Format;

public class Fragment extends ModelObject{

	protected String text;
	protected boolean failed;
	protected Line line;
	protected Column column;

	public Fragment() {
		super();
	}

	public Fragment(String text) {
		setText(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otiliouine.marovea.core.model.Fragment#getText()
	 */
	public String getText() {
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otiliouine.marovea.core.model.Fragment#setText(java.lang.String)
	 */
	public void setText(String text) {
		String oldValue = getText();
		this.text = text;
		firePropertyChange("text", oldValue, getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otiliouine.marovea.core.model.Fragment#getValue()
	 */
	public Serializable getValue() {
		return this.text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otiliouine.marovea.core.model.Fragment#formattingFailed()
	 */
	public boolean formattingFailed() {
		return this.failed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otiliouine.marovea.core.model.Fragment#format(java.text.Format)
	 */
	public void format(Format formatter) {
		try {
			this.text = formatter.format(getValue());
		} catch (IllegalArgumentException e) {
			this.failed = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otiliouine.marovea.core.model.Fragment#getLine()
	 */
	public Line getLine() {
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.otiliouine.marovea.core.model.Fragment#setLine(com.otiliouine.marovea
	 * .core.model.Line)
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otiliouine.marovea.core.model.Fragment#getColumn()
	 */
	public Column getColumn() {
		return column;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.otiliouine.marovea.core.model.Fragment#setColumn(com.otiliouine.marovea
	 * .core.model.Column)
	 */
	public void setColumn(Column column) {
		this.column = column;
	}

}
