package com.otiliouine.marovea.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class URLTextSource implements TextSource {
	
	private URL url;

	public URLTextSource(URL url) {
		this.url = url;
	}

	@Override
	public InputStream getTextAsStream() throws IOException {
		return url.openConnection().getInputStream();
	}

}
