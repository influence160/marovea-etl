package com.otiliouine.marovea.ui.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.ui.handlers.MoveColumnHandler;
import com.otiliouine.marovea.ui.handlers.OpenEditorHandler;

public class MaroveaCommandService implements IMaroveaCommandService {

    @Inject
    private ECommandService commandService;
    @Inject
    private EHandlerService handlerService;
    
    @Override
    public void executeQuit() {
	System.out.println("Quit command");
    }

    @Override
    public void executeOpenEditor(Project project) {
	Map<String, String> parameters = new HashMap<String, String>();
	parameters.put(OpenEditorHandler.PARAM_PROJECT_ID, String.valueOf(project.getId()));
	executeCommand(OpenEditorHandler.COMMAND_ID, parameters);
    }
    
    public void executeCommand(String commandId, Map<String, ?> parameters) {
	ParameterizedCommand command = commandService.createCommand(
		commandId, parameters);
	handlerService.executeHandler(command);
    }
    @Override
    public void executeMoveColumn(Project project, int movedFrom, int movedTo) {
	System.out.println("move data column " + movedFrom + " -> " + movedTo);
	Map<String, String> params = new HashMap<String, String>();
	params.put(MoveColumnHandler.PARAM_MOVED_FROM,
		String.valueOf(movedFrom));
	params.put(MoveColumnHandler.PARAM_MOVED_TO,
		String.valueOf(movedTo));
	params.put(MoveColumnHandler.PARAM_PROJECT_ID, String.valueOf(project.getId()));
	ParameterizedCommand parameterizedCommand = commandService.createCommand(
		MoveColumnHandler.COMMAND_ID, params);
	handlerService.executeHandler(parameterizedCommand);
    }

}
