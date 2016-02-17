package com.otiliouine.marovea.core.extract;

import java.io.IOException;
import java.io.InputStream;

import com.otiliouine.marovea.core.io.LinesReader;
import com.otiliouine.marovea.core.io.TextSource;
import com.otiliouine.marovea.core.model.TextTable;

public class ExtractionOperation {

	private TextExtractor textExtractor;
	private TextTable textTable;
	private ExtractionRules extractionRules;
	private LinesReader linesReader;
	
	public ExtractionOperation(TextExtractor textExtractor,
			TextTable textTable, ExtractionRules extractionRules, 
			LinesReader linesReader) {
		this.textExtractor = textExtractor;
		this.textTable = textTable;
		this.extractionRules = extractionRules;
		this.linesReader = linesReader;
	}
	
	public TextSource getTextSource() {
		return linesReader.getTextSource();
	}

	public TextExtractor getTextExtractor() {
		return textExtractor;
	}

	public TextTable getTextTable() {
		return textTable;
	}

	public ExtractionRules getExtractionRules() {
		return extractionRules;
	}

	public LinesReader getLinesReader() {
		return linesReader;
	}
	
}
