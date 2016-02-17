package com.otiliouine.marovea.ui.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.ui.service.IMaroveaCommandService;

import org.eclipse.swt.widgets.List;

public class ProjectsPart {

    @Inject
    private IEclipseContext context;
    @Inject
    private ESelectionService selectionService;
    @Inject
    private EHandlerService handlerService;
    @Inject
    private ECommandService commandService;
    @Inject 
    private IMaroveaCommandService maroveaCommandService;

    private ProjectsManager projectManager = ProjectsManager.getInstance();

    private ListViewer viewer;

    @PostConstruct
    public void createControls(Composite parent, EPartService partService) {
	viewer = new ListViewer(parent);
	List list = viewer.getList();
	list.setLocation(-218, 0);
	viewer.setContentProvider(ArrayContentProvider.getInstance());
	viewer.setLabelProvider(new LabelProvider() {
	    @Override
	    public String getText(Object element) {
		Project project = (Project) element;
		return project.getName();
	    };
	});
	viewer.setInput(projectManager.getProjects());
	viewer.addSelectionChangedListener(new ISelectionChangedListener() {
	    public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selection = (IStructuredSelection) event
			.getSelection();
		// set the selection to the service
		selectionService.setSelection(selection.size() == 1 ? selection
			.getFirstElement() : selection.toArray());
	    }
	});
	hookDoubleClickCommand();
	addProjectsChangeListener();
    }

    private void hookDoubleClickCommand() {
	viewer.addDoubleClickListener(new IDoubleClickListener() {

	    public void doubleClick(DoubleClickEvent event) {
		try {
		    ParameterizedCommand command = commandService
			    .createCommand(
				    "com.otiliouine.marovea.ui.command.openeditor",
				    null);
		    handlerService.executeHandler(command);
		} catch (Exception ex) {
		    throw new RuntimeException(
			    "Error execute command com.otiliouine.marovea.ui.command.openeditor", ex);
		}
	    }
	});
    }

    public void setFocus() {
	viewer.getControl().setFocus();
    }

    private void addProjectsChangeListener() {
	projectManager.addPropertyChangeListener("projects",
		new PropertyChangeListener() {
		    public void propertyChange(PropertyChangeEvent event) {
			// if (event instanceof ListPropertyChangeEvent) {
			// ListPropertyChangeEvent listEvent =
			// (ListPropertyChangeEvent) event;
			// switch (listEvent.getEventType()) {
			// case ListPropertyChangeEvent.ELEMENT_ADD:
			// Project project = (Project) listEvent.getNewValue();
			// viewer.add(project);
			// break;
			// }
			// } else {
			viewer.refresh();
			// }
		    }
		});
    }

}
