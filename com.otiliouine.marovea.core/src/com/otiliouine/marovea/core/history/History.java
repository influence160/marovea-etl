package com.otiliouine.marovea.core.history;

import java.util.List;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.TextTable;

public class History {

    private Project project;

    protected List<TextTable> _pastEntries; // done changes, can be undone
    protected List<TextTable> _futureEntries; // undone changes, can be redone

    public History(Project project) {
	this.project = project;
    }

}
