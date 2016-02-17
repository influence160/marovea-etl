package com.otiliouine.marovea.ui;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;

import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.mock.MockModelCreator;

public class LifecycleManager {

 
    @ProcessAdditions
    void postContextCreate(IEclipseContext context) {
	System.out.println("postContextCreate");
	MockModelCreator mock = new MockModelCreator();
	ProjectsManager.getInstance().addProject(mock.getMockProjects());
    }
    
}
