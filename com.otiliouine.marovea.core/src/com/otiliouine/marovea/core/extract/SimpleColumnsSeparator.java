package com.otiliouine.marovea.core.extract;

import com.otiliouine.marovea.core.util.IntRange;

public class SimpleColumnsSeparator extends AbstractColumnsSeparator {

	protected String delimiter;
	protected IntRange targetColumns;

	public SimpleColumnsSeparator() {
		super();
	}

	public SimpleColumnsSeparator(String delimiter) {
		super();
		this.delimiter = delimiter;
	}

	public SimpleColumnsSeparator(String delimiter, int targetColumn) {
		this.delimiter = delimiter;
		this.targetColumns = new IntRange(targetColumn);
	}

	public SimpleColumnsSeparator(String delimiter, IntRange targetColumns) {
		super();
		this.delimiter = delimiter;
		this.targetColumns = targetColumns;
	}

	@Override
	public boolean find(String s) {
		return find(s, delimiter);
	}

	@Override
	public boolean haveTargetColumn(int columnIndex) {
		if (targetColumns == null) {
			return false;
		} else {
			return targetColumns.contains(columnIndex);
		}
	}

	@Override
	public boolean applicableFor(int columnIndex) {
		if (targetColumns == null) {
			return true;
		} else {
			return targetColumns.contains(columnIndex);
		}
	}

	public String toString() {
		String result = delimiter;
		if (targetColumns != null) {
			String start = targetColumns.getStart() >= 0 ? String.valueOf(targetColumns.getStart()) : "*";
			String end = targetColumns.getEnd() == IntRange.INFINITE ?  "*" : String.valueOf(targetColumns.getEnd());
			if (start.equals(end)) {
				result += " (" + start + ")";
			} else {
				result += " (" + start + "->" + end + ")";
			}
		}
		return result ;
	}
}
