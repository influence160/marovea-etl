package com.otiliouine.marovea.ui.service;

import java.util.Map;

import com.otiliouine.marovea.core.model.Project;

public interface IMaroveaCommandService {

    public void executeCommand(String commandId, Map<String, ?> parameters);
    
    public void executeQuit();

    //textTable commands
    
    public void executeOpenEditor(Project project);
    
    public void executeMoveColumn(Project project, int movedFrom, int movedTo);
}
