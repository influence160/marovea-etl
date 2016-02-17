package com.otiliouine.marovea.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.otiliouine.marovea.ui.wizard.CreateProjectWizard;

public class NewProjectHandler {
    
    public static final String COMMAND_ID = "com.otiliouine.marovea.ui.command.newproject";

    @Execute
    public void execute(IWorkbench workbench, IEclipseContext context,
	    @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
	CreateProjectWizard createProjectWizard = ContextInjectionFactory.make(
		CreateProjectWizard.class, context);
	WizardDialog wizardDialog = new WizardDialog(shell, createProjectWizard);
	if (wizardDialog.open() == Window.OK) {
	    System.out.println("Ok pressed");
	} else {
	    System.out.println("Cancel pressed");
	}
    }
}
