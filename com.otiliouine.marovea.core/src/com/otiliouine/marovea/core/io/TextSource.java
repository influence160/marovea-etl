package com.otiliouine.marovea.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface TextSource {
	
	/**
	 * Each call Return a new {@link InputStream} of the text source.
	 * used to be able to read the text source multiple times
	 * @return
	 * @throws IOException
	 */
	InputStream getTextAsStream() throws IOException;

}
