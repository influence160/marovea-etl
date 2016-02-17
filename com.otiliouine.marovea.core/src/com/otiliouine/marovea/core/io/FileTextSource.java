package com.otiliouine.marovea.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileTextSource implements TextSource {

	private File data;

	public FileTextSource(File data) {
		this.data = data;
	}

	@Override
	public InputStream getTextAsStream() {
		try {
			return new FileInputStream(data);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
