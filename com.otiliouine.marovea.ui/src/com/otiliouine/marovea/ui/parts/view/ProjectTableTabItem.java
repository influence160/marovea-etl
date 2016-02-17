package com.otiliouine.marovea.ui.parts.view;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
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
import com.otiliouine.marovea.ui.service.IMaroveaCommandService;
import com.otiliouine.marovea.ui.util.IntSuite;

public class ProjectTableTabItem {
    
    @Inject
    private IMaroveaCommandService maroveaCommandService;
    
    private Composite parent;
    private Project project;
    
    private Table table;
    private TableViewer tableViewer;
    private IntSuite previewsColumnOrder;

    public Composite createControl(CTabFolder folder, Project project) {
	this.parent = parent;
	this.project = project;
	Composite container = new Composite(folder, SWT.NONE);
	container.setLayout(new FillLayout());

	tableViewer = new TableViewer(container, SWT.FULL_SELECTION | SWT.MULTI
		| SWT.BORDER);
	table = tableViewer.getTable();
	table.setLinesVisible(true);
	table.setHeaderVisible(true);
	createColumns();
	previewsColumnOrder = new IntSuite(table.getColumnOrder());
	ObservableListContentProvider observableList = new ObservableListContentProvider();
	tableViewer.setContentProvider(observableList);

	tableViewer.setInput(new WritableList(
		project.getTextTable().getLines(), Line.class));
	
	
	return container;
    }

    private void createColumns() {
	List<Column> modelColumns = project.getTextTable().getColumns();
	for (int i = 0; i < modelColumns.size(); i++) {
	    final int columnIndex = i;
	    TableViewerColumn col = new TableViewerColumn(tableViewer, SWT.NONE);
	    col.getColumn().setWidth(50);
	    col.getColumn().setText(
		    project.getTextTable().getColumns().get(i).getName());
	    col.setLabelProvider(new ColumnLabelProvider() {
		@Override
		public String getText(Object element) {
		    Line line = (Line) element;
		    return line.getFragmentAt(columnIndex).getText();
		}
	    });

	    col.getColumn().setMoveable(true);
	    col.getColumn().addListener(SWT.Move, new Listener() {
		public void handleEvent(Event event) {
		    int[] columnsOrder = table.getColumnOrder();
		    boolean moved = previewsColumnOrder.move(columnsOrder);
		    if (moved) {
			moveDataColumn(previewsColumnOrder.getMovedFrom(),
				previewsColumnOrder.getMovedTo());
		    }
		}
	    });
	}
    }

    private void moveDataColumn(int movedFrom, int movedTo) {
	maroveaCommandService.executeMoveColumn(project, movedFrom, movedTo);
    }
    
    
    
    
    
    
    
    
    
    
    
    

    /* Drag and drop */
    private void addDragAndDropSupport() {
	int operations = DND.DROP_COPY | DND.DROP_MOVE;
	Transfer[] transferTypes = new Transfer[] { TextTransfer.getInstance() };
	tableViewer.addDragSupport(operations, transferTypes,
		new MyDragListener(tableViewer));
    }

    public class MyDragListener implements DragSourceListener {

	private final TableViewer viewer;

	public MyDragListener(TableViewer viewer) {
	    this.viewer = viewer;
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
	    System.out.println("Finshed Drag");
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
	    // Here you do the convertion to the type which is expected.
	    IStructuredSelection selection = (IStructuredSelection) viewer
		    .getSelection();
	    // Todo firstElement = (Todo) selection.getFirstElement();
	    //
	    // if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
	    // event.data = firstElement.getShortDescription() + " "
	    // + firstElement.getLongDescription();
	    // }

	}

	@Override
	public void dragStart(DragSourceEvent event) {
	    System.out.println("Start Drag");
	}
    }

    public class MyDropListener extends ViewerDropAdapter {

	private final Viewer viewer;

	public MyDropListener(Viewer viewer) {
	    super(viewer);
	    this.viewer = viewer;
	}

	@Override
	public void drop(DropTargetEvent event) {
	    int location = this.determineLocation(event);
	    String target = (String) determineTarget(event);
	    String translatedLocation = "";
	    switch (location) {
	    case 1:
		translatedLocation = "Dropped before the target ";
		break;
	    case 2:
		translatedLocation = "Dropped after the target ";
		break;
	    case 3:
		translatedLocation = "Dropped on the target ";
		break;
	    case 4:
		translatedLocation = "Dropped into nothing ";
		break;
	    }
	    System.out.println(translatedLocation);
	    System.out.println("The drop was done on the element: " + target);
	    super.drop(event);
	}

	// This method performs the actual drop
	// We simply add the String we receive to the model and trigger a
	// refresh of the
	// viewer by calling its setInput method.
	@Override
	public boolean performDrop(Object data) {
	    // ContentProviderTree.INSTANCE.getModel().add(data.toString());
	    // viewer.setInput(ContentProviderTree.INSTANCE.getModel());
	    return false;
	}

	@Override
	public boolean validateDrop(Object target, int operation,
		TransferData transferType) {
	    return true;

	}

    }
}
