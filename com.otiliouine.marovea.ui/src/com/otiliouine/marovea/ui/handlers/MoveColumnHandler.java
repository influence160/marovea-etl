package com.otiliouine.marovea.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.widgets.Shell;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;

public class MoveColumnHandler {
    public static final String COMMAND_ID = "com.otiliouine.marovea.ui.command.movecolumn";

    public static final String PARAM_MOVED_FROM = "com.otiliouine.marovea.ui.commandparameter.movedFrom";
    public static final String PARAM_MOVED_TO = "com.otiliouine.marovea.ui.commandparameter.movedTo";
    public static final String PARAM_PROJECT_ID = "com.otiliouine.marovea.ui.commandparameter.projectId";

    @Execute
    public void execute(IWorkbench workbench, IEclipseContext context,
	    @Named(PARAM_MOVED_FROM) String movedFrom,
	    @Named(PARAM_MOVED_TO) String movedTo,
	    @Named(PARAM_PROJECT_ID) String projectId) {
    	Project project = ProjectsManager.getInstance().getProject(Long.parseLong(projectId));
    	project.getTextTable().moveColumn(Integer.parseInt(movedFrom), Integer.parseInt(movedTo));
    }
}
