//package com.otiliouine.marovea.bindablemodel;
//
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeSupport;
//import java.util.Collection;
//
//import com.otiliouine.marovea.bindablemodel.event.ListPropertyChangeEvent;
//import com.otiliouine.marovea.core.model.Project;
//import com.otiliouine.marovea.core.model.ProjectManager;
//import com.otiliouine.marovea.core.model.ProjectsManager;
//
//public class BindableProjectManager extends BindableObject implements
//		ProjectManager {
//
//	private static final BindableProjectManager instance = new BindableProjectManager();
//	protected final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
//			this);
//
//	public static BindableProjectManager getInstance() {
//		return instance;
//	}
//
//	protected ProjectManager projectManager = ProjectsManager.getInstance();
//
//	protected BindableProjectManager() {
//	}
//
//	@Override
//	public Collection<Project> getProjects() {
//		return projectManager.getProjects();
//	}
//
//	@Override
//	public Project createProject() {
//		return projectManager.createProject();
//	}
//
//	@Override
//	public void addProject(Project project) {
//		projectManager.addProject(project);
////		PropertyChangeEvent event = new ListPropertyChangeEvent(
////				ListPropertyChangeEvent.ELEMENT_ADD, this, "projects", null,
////				project);
////		firePropertyChange(event);
//		firePropertyChange("projects", null, null);
//	}
//
//	@Override
//	public Project getProject(long id) {
//		return projectManager.getProject(id);
//	}
//
//}
