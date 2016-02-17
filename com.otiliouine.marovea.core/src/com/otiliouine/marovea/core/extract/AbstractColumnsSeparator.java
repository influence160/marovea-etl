package com.otiliouine.marovea.core.extract;

public abstract class AbstractColumnsSeparator implements ColumnsSeparator {
    
    protected int start = -1;
    protected int end = -1;

    public boolean find(String s, String delimiter) {
	start = s.indexOf(delimiter);
	if (start >= 0) {
	    end = start + delimiter.length();
	}
	return start >= 0;
    }

    @Override
    public int start() {
	return start;
    }

    @Override
    public int end() {
	return end;
    }

}
