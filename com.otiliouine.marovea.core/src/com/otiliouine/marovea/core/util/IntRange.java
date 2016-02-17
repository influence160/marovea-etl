package com.otiliouine.marovea.core.util;

public class IntRange {
    
    public static final int INFINITE = Integer.MAX_VALUE;

    private int start;
    private int end;

    public IntRange() {
    }

    public IntRange(int start, int end) {
	super();
	this.start = start;
	this.end = end;
    }
    
    public IntRange(int index) {
	this(index, index);
    }

    public boolean contains(int index) {
	return (start >= 0 && index >= start && index <= end);
    }

    public int getStart() {
	return start;
    }

    public void setStart(int start) {
	this.start = start;
    }

    public int getEnd() {
	return end;
    }

    public void setEnd(int end) {
	this.end = end;
    }

}
