package com.otiliouine.marovea.core.extract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Othmen TILIOUINE
 *
 */
public class LineDecomposer {

    private String text;
    private List<String> components;
    private int position;
    private Collection<ColumnsSeparator> separators;
    private boolean addDelimiterColumns = true;//when true add delimiters to components

    public LineDecomposer(Collection<ColumnsSeparator> separators) {
	this.separators = separators;
    }

    public void addColumnsSeparator(ColumnsSeparator separator) {
	this.separators.add(separator);
    }

    /**
     * decomposes a string representing a line of the text to a list of strings
     * using a list of separators
     * 
     * @param line
     * @return
     */
    public List<String> decompose(String line) {
	if (line == null) {
	    throw new NullPointerException("text is null");
	}

	components = new ArrayList<String>();
	position = 0;
	this.text = line;

	// if text == "" then components text id not decomposable
	if (line.length() == 0) {
	    components.add(line);
	}

	String rest = line;
	while (position < this.text.length()) {
	    rest = this.text.substring(position);
	    int columnIndex = components.size();
	    boolean found = false;

	    ColumnsSeparator targetSeparator = getTargetSeparator(columnIndex);
	    if (targetSeparator != null) {
		found = targetSeparator.find(rest);
		if (found) {
		    separate(rest, targetSeparator);
		}
	    }

	    if (!found) {
		ColumnsSeparator firstFoundSeparator = firstFoundSeparator(rest);
		if (firstFoundSeparator != null) {
		    found = true;
		    separate(rest, firstFoundSeparator);
		}
	    }

	    // if not found a pattern in the rest of the text then the reste of
	    // the text is the last component
	    if (!found) {
		components.add(text.substring(position, text.length()));
		position = text.length();
	    }
	}
	return components;
    }

    /**
     * separates the string rest using the given separator and add the left part
     * to components, if addDelimiterColumns is true, also add the delimiter to
     * components
     * 
     * @param reste the string to separate
     * @param separator the separator to use for separation
     */
    private void separate(String rest, ColumnsSeparator separator) {
	int start = separator.start();
	int end = separator.end();
	String newComponent = rest.substring(0, start);
	components.add(newComponent);

	if (addDelimiterColumns) {
	    String newDelimiter = rest.substring(start, end);
	    components.add(newDelimiter);
	}
	position += end;
    }

    /**
     * get the target separator of the given column
     * 
     * @param columnIndex
     *            the index of the column
     * @return
     */
    private ColumnsSeparator getTargetSeparator(int columnIndex) {
	for (ColumnsSeparator separator : separators) {
	    if (separator.haveTargetColumn(columnIndex)) {
		//TODO check if there is many target separators
		return separator;
	    }
	}
	return null;
    }

    /**
     * get the first separator found in rest, the separator that have the
     * minimum start index
     * 
     * @param rest
     *            the string to separate
     * @return the first separator found or <b>null</b> if no separator found in
     *         rest
     */
    private ColumnsSeparator firstFoundSeparator(String rest) {
	int columnIndex = components.size();
	int minStart = Integer.MAX_VALUE;
	ColumnsSeparator firstFoundSeparator = null;
	
	for (ColumnsSeparator separator : separators) {
	    if (separator.applicableFor(columnIndex) && separator.find(rest)) {
		int start = separator.start();
		if (start < minStart) {
		    minStart = start;
		    firstFoundSeparator = separator;
		}
	    }
	}
	
	return firstFoundSeparator;
    }

    public Collection<ColumnsSeparator> getSeparators() {
        return separators;
    }

    public void setSeparators(Collection<ColumnsSeparator> separators) {
        this.separators = separators;
    }

    public boolean isAddDelimiterColumns() {
        return addDelimiterColumns;
    }

    public void setAddDelimiterColumns(boolean addDelimiterColumns) {
        this.addDelimiterColumns = addDelimiterColumns;
    }

    public List<String> getComponents() {
        return components;
    }

}
