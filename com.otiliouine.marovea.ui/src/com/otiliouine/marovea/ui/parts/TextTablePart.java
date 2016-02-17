package com.otiliouine.marovea.ui.parts;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;

import com.otiliouine.marovea.core.model.Column;
import com.otiliouine.marovea.core.model.Line;
import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.core.model.ProjectsManager;
import com.otiliouine.marovea.ui.parts.view.InputTextTabItem;
import com.otiliouine.marovea.ui.parts.view.OutputTextTabItem;
import com.otiliouine.marovea.ui.parts.view.ProjectTableTabItem;
import com.otiliouine.marovea.ui.service.IMaroveaCommandService;
import com.otiliouine.marovea.ui.util.IntSuite;

public class TextTablePart {

    public static final String DESCRIPTOR_ID = "com.otiliouine.marovea.ui.partdescriptor.texttable";
    public static final String PARAM_PROJECT_ID = "com.otiliouine.marovea.ui.commandparameter.projectId";

    private Table table;
    private TableViewer tableViewer;
    private Project project;

    private IntSuite previewsColumnOrder;

    @Inject
    private IEclipseContext context;
    @Inject
    private MInputPart inputPart;
    @Inject
    private EHandlerService handlerService;
    @Inject
    private ECommandService commandService;
    @Inject
    private IMaroveaCommandService maroveaCommandService;
    ProjectsManager projectsManager = ProjectsManager.getInstance();

    @PostConstruct
    public void createControls(Composite parent, EPartService partService,
	    ESelectionService selectionService) {
	// project = (Project) selectionService.getSelection();
	project = projectsManager.getProject(Long.parseLong(inputPart
		.getElementId()));
	CTabFolder folder = new CTabFolder(parent, SWT.BOTTOM);
	folder.setSimple(false);
	
	//Table Tab
	CTabItem pTableTab = new CTabItem(folder, SWT.NULL);
	pTableTab.setText("Table");
	ProjectTableTabItem pTableTabItem = ContextInjectionFactory.make(ProjectTableTabItem.class, context);
	Composite pTableComposite = pTableTabItem.createControl(folder, project);
	pTableTab.setControl(pTableComposite);

	//Input text tab
	CTabItem pInputTab = new CTabItem(folder, SWT.NULL);
	pInputTab.setText("Input");
	InputTextTabItem pInputTabItem = ContextInjectionFactory.make(InputTextTabItem.class, context);
	Composite pInputComposite = pInputTabItem.createControl(folder, project);
	pInputTab.setControl(pInputComposite);

	//Input text tab
	CTabItem pOutputTab = new CTabItem(folder, SWT.NULL);
	pOutputTab.setText("Output");
	OutputTextTabItem pOutputTabItem = ContextInjectionFactory.make(OutputTextTabItem.class, context);
	Composite pOutputComposite = pOutputTabItem.createControl(folder, project);
	pOutputTab.setControl(pOutputComposite);
	
	folder.setSelection(0);
    }
    
    @PreDestroy
    public void predestroy() {
	
    }

}