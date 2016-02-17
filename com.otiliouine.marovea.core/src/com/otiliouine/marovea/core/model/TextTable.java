package com.otiliouine.marovea.core.model;

import java.util.ArrayList;
import java.util.List;

import com.otiliouine.marovea.core.io.LinesReader;

public class TextTable extends ModelObject implements Cloneable {

    private List<Column> columns = new ArrayList<Column>();
    private List<Line> lines = new ArrayList<Line>();

    public TextTable(List<Line> lines) {
	this.lines = lines;
    }

    public TextTable() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.otiliouine.marovea.core.model.TextTable#getLine(int)
     */
    public Line getLine(int index) {
	return lines.get(index);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.otiliouine.marovea.core.model.TextTable#addLine(com.otiliouine.marovea
     * .core.model.Line)
     */
    public void addLine(Line line) {
	this.lines.add(line);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.otiliouine.marovea.core.model.TextTable#getColumns()
     */
    public List<Column> getColumns() {
	return columns;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.otiliouine.marovea.core.model.TextTable#setColumns(java.util.List)
     */
    public void setColumns(List<Column> columns) {
	this.columns = columns;
	for (int i = 0; i < columns.size(); i++) {
	    columns.get(i).setIndex(i);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.otiliouine.marovea.core.model.TextTable#getLines()
     */
    public List<Line> getLines() {
	return lines;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.otiliouine.marovea.core.model.TextTable#setLines(java.util.List)
     */
    public void setLines(List<Line> lines) {
	this.lines = lines;
    }

    public void moveColumn(int fromIndex, int toIndex) {
	for (Line line : lines) {
	    line.moveFragment(fromIndex, toIndex);
	}
	Column column = columns.remove(fromIndex);
	columns.add(toIndex, column);
	// column.setIndex(index);//TODO
	firePropertyChange("lines", null, null);
    }

}
