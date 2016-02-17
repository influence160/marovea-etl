package com.otiliouine.marovea.core.extract;

import java.util.ArrayList;
import java.util.Collection;

public class ExtractionRules {

	private Collection<ColumnsSeparator> columnsSeparators = new ArrayList<ColumnsSeparator>();
	// when true add delimiters to components
	private boolean showDelimiterColumns = true;

	public ExtractionRules() {
	}

	public ExtractionRules(Collection<ColumnsSeparator> columnsSeparators) {
		this.columnsSeparators = columnsSeparators;
	}

	public Collection<ColumnsSeparator> getColumnsSeparators() {
		return columnsSeparators;
	}

	public void setColumnsSeparators(
			Collection<ColumnsSeparator> columnsSeparators) {
		this.columnsSeparators = columnsSeparators;
	}

	public boolean isShowDelimiterColumns() {
		return showDelimiterColumns;
	}

	public void setShowDelimiterColumns(boolean shwoDelimiterColumns) {
		this.showDelimiterColumns = shwoDelimiterColumns;
	}

}
