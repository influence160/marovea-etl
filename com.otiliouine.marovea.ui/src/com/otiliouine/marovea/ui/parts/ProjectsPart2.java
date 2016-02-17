package com.otiliouine.marovea.ui.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.core.model.TextTable;

import org.eclipse.swt.widgets.List;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class ProjectsPart2 {

    private static class ProjectsTreeFactory implements IObservableFactory {
	public IObservable createObservable(Object target) {
	    return BeanProperties.list("projects").observe(target);
	}
    }

    private static class ProjectsTreeStructureAdvisor extends
	    TreeStructureAdvisor {
	public Object getParent(Object element) {
	    return ProjectsManager.getInstance();
	}

	@Override
	public Boolean hasChildren(Object element) {
	    if (element instanceof Project) {
		Project project = (Project) element;
		return ProjectsManager.getInstance().contains(project);
	    }
	    return super.hasChildren(element);
	}
    }

    private static class ProjectsLabelProvider extends LabelProvider {

	private static final Image FOLDER = getImage("folder.png");
	private static final Image FILE = getImage("folder.png");

	@Override
	public String getText(Object element) {
	    if (element instanceof Project) {
		Project project = (Project) element;
		return project.getName();
	    }
	    return element.toString();
	}

	@Override
	public Image getImage(Object element) {
	    if (element instanceof Project) {
		return FOLDER;
	    }
	    return FILE;
	}

	private static Image getImage(String file) {
	    Bundle bundle = FrameworkUtil
		    .getBundle(ProjectsLabelProvider.class);
	    URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
	    ImageDescriptor image = ImageDescriptor.createFromURL(url);
	    return image.createImage();
	}
    }

    @Inject
    private ESelectionService selectionService;
    @Inject
    private EHandlerService handlerService;
    @Inject
    private ECommandService commandService;

    private ProjectsManager projectManager = ProjectsManager.getInstance();

    private TreeViewer treeViewer;

    @PostConstruct
    public void createControls(Composite parent, EPartService partService) {
	parent.setLayout(new FillLayout());
	treeViewer = new TreeViewer(parent);
	IContentProvider contentProvider = new ObservableListTreeContentProvider(
		new ProjectsTreeFactory(), new ProjectsTreeStructureAdvisor());
	treeViewer.setContentProvider(contentProvider);
	treeViewer.setLabelProvider(new ProjectsLabelProvider());
	treeViewer.setInput(projectManager);
	treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
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
	treeViewer.addDoubleClickListener(new IDoubleClickListener() {

	    public void doubleClick(DoubleClickEvent event) {
		try {
		    ParameterizedCommand command = commandService
			    .createCommand(
				    "com.otiliouine.marovea.ui.command.openeditor",
				    null);
		    handlerService.executeHandler(command);
		} catch (Exception ex) {
		    throw new RuntimeException(
			    "com.otiliouine.marovea.ui.command.openeditor", ex);
		}
	    }
	});
    }

    public void setFocus() {
	treeViewer.getControl().setFocus();
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
			treeViewer.refresh();
			// }
		    }
		});
    }

}
