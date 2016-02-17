package com.otiliouine.marovea.core.extract;

public interface ColumnsSeparator {
    
    public boolean haveTargetColumn(int columnIndex);
    
    public boolean applicableFor(int columnIndex);
    
    public boolean find(String s);
    
    public int start();
    
    public int end();

}
