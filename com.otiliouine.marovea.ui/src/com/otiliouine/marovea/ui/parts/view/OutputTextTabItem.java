package com.otiliouine.marovea.ui.parts.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.ui.component.StyledTextTable;

public class OutputTextTabItem {

    private CTabFolder folder;
    private Project project;
    private ProjectsManager projectsManager = ProjectsManager.getInstance();

    public Composite createControl(CTabFolder folder, Project project) {
	this.folder = folder;
	this.project = project;

	Composite container = new Composite(folder, SWT.NONE);
	StyledTextTable text = new StyledTextTable(container, SWT.NONE);
	text.setTextTable(project.getTextTable());
	text.setEditable(false);
	container.setLayout(new FillLayout());
	return container;
    }
    
}
