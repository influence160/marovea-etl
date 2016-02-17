package com.otiliouine.marovea.core.extract;

import java.io.IOException;

import com.otiliouine.marovea.core.io.LinesReader;
import com.otiliouine.marovea.core.model.TextTable;

public interface TextExtractor {

	TextTable extract(LinesReader reader) throws IOException;

}