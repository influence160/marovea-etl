package com.otiliouine.marovea.ui.handlers;

import java.util.Collection;
import java.util.List;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.ui.parts.TextTablePart;

public class OpenEditorHandler {

    public static final String COMMAND_ID = "com.otiliouine.marovea.ui.command.openeditor";
    public static final String PARAM_PROJECT_ID = "com.otiliouine.marovea.ui.commandparameter.projectId";

    ProjectsManager projectsManager = ProjectsManager.getInstance();
    
    @Execute
    public void execute(MWindow window, MApplication application,
	    IEclipseContext context, EPartService partService,
	    ESelectionService selectionService, EModelService modelService,
	    @Optional @Named(PARAM_PROJECT_ID) String projectId) {
	Project input;
	if (projectId != null) {
	    input = projectsManager.getProject(Long.parseLong(projectId));
	} else {
	    input = (Project) selectionService.getSelection();
	}
	MInputPart part;
	Collection<MInputPart> inputParts = partService.getInputParts(String
		.valueOf(input.id));
	if (inputParts != null && !inputParts.isEmpty()) {
	    part = inputParts.iterator().next();
	} else {
	    part = MBasicFactory.INSTANCE.createInputPart();
	    part.setElementId(String.valueOf(input.id));
	    part.setLabel(input.getName());
	    part.setInputURI(String.valueOf(input.id));
	    part.setContributionURI("bundleclass://com.otiliouine.marovea.ui/"
		    + "com.otiliouine.marovea.ui.parts.TextTablePart");
	    part.setCloseable(true);
	}
	MPartStack stack = (MPartStack) modelService.find(
		"com.otiliouine.marovea.ui.partstack.1", application);
	stack.getChildren().add(part);
	partService.showPart(part, PartState.VISIBLE);
    }

}
