package com.otiliouine.marovea.ui.wizard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.wizard.Wizard;

import com.otiliouine.marovea.core.extract.ExtractionOperation;
import com.otiliouine.marovea.core.extract.ExtractionRules;
import com.otiliouine.marovea.core.extract.TextExtractor;
import com.otiliouine.marovea.core.extract.TextExtractorImpl;
import com.otiliouine.marovea.core.io.DefaultLinesReader;
import com.otiliouine.marovea.core.io.FileTextSource;
import com.otiliouine.marovea.core.io.LinesReader;
import com.otiliouine.marovea.core.io.StringTextSource;
import com.otiliouine.marovea.core.io.TextSource;
import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.core.model.TextTable;
import com.otiliouine.marovea.ui.handlers.OpenEditorHandler;
import com.otiliouine.marovea.ui.service.IMaroveaCommandService;

public class CreateProjectWizard extends Wizard {

    @Inject
    private ECommandService commandService;
    @Inject
    private EHandlerService handlerService;
    @Inject
    private IEclipseContext eclipseContext;
    
    @Inject
    private IMaroveaCommandService maroveaCommandService;

    protected CreateProjectSelectFilePage one;
    protected CreateProjectConfigurePage two;

    public CreateProjectWizard() {
	super();
	setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
	one = new CreateProjectSelectFilePage();
	two = new CreateProjectConfigurePage();
	addPage(one);
	addPage(two);
    }

    @Override
    public boolean performFinish() {
	TextSource textSource = one.isFileRadioSelected() ? new FileTextSource(one.getFile())
	    : new StringTextSource(one.getText());
	LinesReader linesReader;
	try {
	    linesReader = new DefaultLinesReader(textSource);
	} catch (IOException e) {
	    throw new RuntimeException(e.getMessage(), e);
	}
	ProjectsManager projectsManager = ProjectsManager.getInstance();
	ExtractionRules extractionRules = new ExtractionRules(
		two.getColumnsSeparators());
	extractionRules.setShowDelimiterColumns(two.getShowDelimiterColumns());
	Project project = projectsManager.createProject(one.getProjectName(),
		linesReader, extractionRules);
	maroveaCommandService.executeOpenEditor(project);
	return true;
    }
}