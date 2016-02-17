package com.otiliouine.marovea.core.model;

import com.otiliouine.marovea.core.history.History;

public class Project extends ModelObject {

    public final long id;
    private String name;
    private History history;
    private TextTable textTable;

    public static long generateID() {
	return System.currentTimeMillis()
		+ Math.round(Math.random() * 1000000000000L);
    }

    public Project() {
	id = generateID();
	history = new History(this);
    }

    // public TextTable extractTable(LinesReader linesReader, ExtractionRules
    // extractionRules) {
    // this.linesReader = linesReader;
    // this.extractionRules = extractionRules;
    // TextExtractor extractor = new TextExtractorImpl(extractionRules);
    // try {
    // TextTable textTable = extractor.extract(linesReader);
    // setTextTable(textTable);
    // return textTable;
    // } catch (IOException e) {
    // throw new RuntimeException(e.getMessage(), e);
    // }
    // }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public long getId() {
	return id;
    }

    public TextTable getTextTable() {
	return textTable;
    }

    public void setTextTable(TextTable textTable) {
	this.textTable = textTable;
    }

    public History getHistory() {
	return history;
    }

}
