package com.otiliouine.marovea.core.model;

import java.util.List;

public interface ITextTable {

	Line getLine(int index);

	void addLine(Line line);

	List<Column> getColumns();

	void setColumns(List<Column> columns);

	List<Line> getLines();

	void setLines(List<Line> lines);

	void moveColumn(int fromIndex, int toIndex);

}