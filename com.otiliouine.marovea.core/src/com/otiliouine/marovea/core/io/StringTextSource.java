package com.otiliouine.marovea.core.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

public class StringTextSource implements TextSource {

	private String data;

	public StringTextSource(String data) {
		super();
		this.data = data;
	}

	@Override
	public InputStream getTextAsStream() {
		return new ByteArrayInputStream(data.getBytes());
	}

}
