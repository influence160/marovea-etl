package com.otiliouine.marovea.core.model;

import java.text.Format;
import java.util.List;

public class Column {

    public static final String COLUMN_NAME_PREFIX = "C";
    public static final String SEPARATOR_NAME_PREFIX = "s";

    private int index;
    private String name;
    private List<Fragment> fragments;
    private Format formatter;
    
    public Column(String name) {
	this.name = name;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Fragment> getFragments() {
        return fragments;
    }
    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
    public Format getFormatter() {
        return formatter;
    }
    public void setFormatter(Format formatter) {
        this.formatter = formatter;
    }

}
