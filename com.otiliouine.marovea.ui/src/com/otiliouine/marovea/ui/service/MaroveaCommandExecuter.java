package com.otiliouine.marovea.ui.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.internal.workbench.E4Workbench;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.ui.handlers.OpenEditorHandler;

public class MaroveaCommandExecuter {
    
    private static MaroveaCommandExecuter instance;
    
    public static MaroveaCommandExecuter getInstance() {
	if (instance == null) {
	    IEclipseContext context = E4Workbench.getServiceContext();
	    instance = ContextInjectionFactory.make(MaroveaCommandExecuter.class, context);
	}
	return instance;
    }

    @Inject
    private ECommandService commandService;
    @Inject
    private EHandlerService handlerService;

    public MaroveaCommandExecuter() {
    }

    public void executeOpenEditor(Project project) {
	
	Map<String, String> parameters = new HashMap<String, String>();
	parameters.put(OpenEditorHandler.PARAM_PROJECT_ID, String.valueOf(project.getId()));
	ParameterizedCommand command = commandService.createCommand(
		"com.otiliouine.marovea.ui.command.openeditor", parameters);
	handlerService.executeHandler(command);
    }
}
