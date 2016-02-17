package com.otiliouine.marovea.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;

import com.otiliouine.marovea.core.extract.ExtractionRules;

public class ExtractTextHandler {

    @Execute
    public void execute(IWorkbench workbench, IEclipseContext context, @Named("extractionRules") ExtractionRules extractionRules) {
    	assert extractionRules == context.get("extractionRules");
//    	extractionRules
    }
    
}
