package com.otiliouine.marovea.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DefaultLinesReader implements LinesReader {

	private BufferedReader bufferedReader;
	private TextSource textSource;

	public DefaultLinesReader(TextSource textSource) throws IOException {
		bufferedReader = new BufferedReader(new InputStreamReader(
				textSource.getTextAsStream()));
		this.textSource = textSource;
	}

	@Override
	public TextSource getTextSource() {
		return textSource;
	}

	@Override
	public String readLine() throws IOException {
		return bufferedReader.readLine();
	}
}
