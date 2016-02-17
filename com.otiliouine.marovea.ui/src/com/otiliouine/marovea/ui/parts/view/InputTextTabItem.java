package com.otiliouine.marovea.ui.parts.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.otiliouine.marovea.core.io.LinesReader;
import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;

public class InputTextTabItem {

    private CTabFolder folder;
    private Project project;
    private ProjectsManager projectsManager = ProjectsManager.getInstance();

    public Composite createControl(CTabFolder folder, Project project) {
	this.folder = folder;
	this.project = project;

	Composite container = new Composite(folder, SWT.NONE);
	StyledText text = new StyledText(container, SWT.NONE);
	InputStream input;
	try {
	    input = projectsManager
	    	.getExtractionOperation(project.getId()).getTextSource()
	    	.getTextAsStream();
	} catch (IOException e) {
	    throw new RuntimeException(e.getMessage(), e);
	}
	StringWriter writer = new StringWriter();
	try {
	    IOUtils.copy(input, writer);
	} catch (IOException e) {
	    throw new RuntimeException(e.getMessage(), e);
	}
	text.setForeground(new Color(folder.getDisplay(), 0, 0, 0));
	text.setText(writer.toString());
	text.append("string");
	
	
	container.setLayout(new FillLayout());
	text.setEditable(false);
	return container;
    }
    
}
