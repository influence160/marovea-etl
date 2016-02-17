package com.otiliouine.marovea.core.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.otiliouine.marovea.core.extract.ExtractionOperation;
import com.otiliouine.marovea.core.extract.ExtractionRules;
import com.otiliouine.marovea.core.extract.TextExtractor;
import com.otiliouine.marovea.core.extract.TextExtractorImpl;
import com.otiliouine.marovea.core.io.LinesReader;
import com.otiliouine.marovea.core.io.TextSource;

public class ProjectsManager extends ModelObject{

	private static final ProjectsManager instance = new ProjectsManager();

	private Map<Long, Project> projects = new HashMap<Long, Project>();
	private Map<Long, ExtractionOperation> extractionOperations = new HashMap<Long, ExtractionOperation>();

	protected ProjectsManager() {
	}

	public static ProjectsManager getInstance() {
		return instance;
	}

	public Collection<Project> getProjects() {
		return new ArrayList<Project>(projects.values());
	}

	public Project createProject() {
		Project project = new Project();
		addProject(project);
		return project;
	}

	public Project createProject(String name, LinesReader linesReader,
			ExtractionRules extractionRules) {
		Project project = new Project();
		project.setName(name);
		TextExtractor extractor = new TextExtractorImpl(extractionRules);
		TextTable textTable;
		try {
		    textTable = extractor.extract(linesReader);
		} catch (IOException e) {
		    throw new RuntimeException(e.getMessage(), e);
		}
		project.setTextTable(textTable);
		ExtractionOperation extractionOperation = new ExtractionOperation(extractor, textTable, extractionRules, linesReader);
		addProject(project);
		putExtractionOperation(project.getId(), extractionOperation);
		return project;
	}

	public void putExtractionOperation(long id,
			ExtractionOperation extractionOperation) {
		extractionOperations.put(id, extractionOperation);
	}

	public void addProject(Project project) {
		projects.put(project.getId(), project);
		firePropertyChange("projects", null, null);
	}

	public void addProject(List<Project> list) {
		for (Project project : list) {
			this.projects.put(project.getId(), project);
		}
		firePropertyChange("projects", null, null);
	}

	public Project getProject(long id) {
		return projects.get(id);
	}
	
	public ExtractionOperation getExtractionOperation(long projectId) {
		return extractionOperations.get(projectId);
	}
	
	public boolean contains(Project project) {
		return projects.containsValue(project);
	}

}
