package com.otiliouine.marovea.core.io;

import java.io.IOException;

public interface LinesReader {
    
    public String readLine() throws IOException;
    
    public TextSource getTextSource();
    
}
