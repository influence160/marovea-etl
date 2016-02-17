package com.otiliouine.marovea.mock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.otiliouine.marovea.core.extract.ColumnsSeparator;
import com.otiliouine.marovea.core.extract.ExtractionOperation;
import com.otiliouine.marovea.core.extract.ExtractionRules;
import com.otiliouine.marovea.core.extract.SimpleColumnsSeparator;
import com.otiliouine.marovea.core.extract.TextExtractor;
import com.otiliouine.marovea.core.extract.TextExtractorImpl;
import com.otiliouine.marovea.core.io.DefaultLinesReader;
import com.otiliouine.marovea.core.io.LinesReader;
import com.otiliouine.marovea.core.io.StringTextSource;
import com.otiliouine.marovea.core.io.TextSource;
import com.otiliouine.marovea.core.io.URLTextSource;
import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.core.model.TextTable;

public class MockModelCreator {
	ProjectsManager projectsManager = ProjectsManager.getInstance();

	public List<Project> getMockProjects() {
		List<Project> list = new ArrayList<Project>();
		list.add(getProject1());
		list.add(getProject2());
		return list;
	}

	private Project getProject1() {
		String string = "aze,fef,grh,dsdsf\n" + "aaa,ejgk,q\n" + "aaqq";
		TextSource textSource = new StringTextSource(string);
		LinesReader linesReader;
		try {
			linesReader = new DefaultLinesReader(textSource);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		Collection<ColumnsSeparator> columnsSeparators = new HashSet<ColumnsSeparator>();
		columnsSeparators.add(new SimpleColumnsSeparator(","));
		ExtractionRules extractionRules = new ExtractionRules(columnsSeparators);
		extractionRules.setShowDelimiterColumns(true);
		Project project = projectsManager.createProject("mock 1", linesReader, extractionRules);
		return project;
	}

	private Project getProject2() {
		try {
			Project project = new Project();
			project.setName("mock 2");
			URL url = new URL(
					"platform:/plugin/com.otiliouine.marovea.mock/files/text.txt");
			TextSource textSource = new URLTextSource(url);
			LinesReader linesReader = new DefaultLinesReader(textSource);
			Collection<ColumnsSeparator> columnsSeparators = new HashSet<ColumnsSeparator>();
			columnsSeparators.add(new SimpleColumnsSeparator(","));
			ExtractionRules extractionRules = new ExtractionRules(columnsSeparators);
			extractionRules.setShowDelimiterColumns(true);
			project = projectsManager.createProject("mock 2", linesReader, extractionRules);
			return project;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
