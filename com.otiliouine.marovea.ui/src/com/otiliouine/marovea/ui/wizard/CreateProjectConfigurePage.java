package com.otiliouine.marovea.ui.wizard;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.otiliouine.marovea.core.extract.ColumnsSeparator;
import com.otiliouine.marovea.core.extract.SimpleColumnsSeparator;
import com.otiliouine.marovea.core.model.Project;
import com.otiliouine.marovea.ui.dialog.AddColumnsSeparatorDialog;

public class CreateProjectConfigurePage extends WizardPage {

    private Text text1;
    private Composite container;
    private ListViewer viewer;
    private Collection<ColumnsSeparator> columnsSeparators;
    private Button showSeparatorsCheck;

    public CreateProjectConfigurePage() {
	super("Second Page");
	setTitle("Second Page");
	setDescription("Now this is the second page");
	setControl(text1);
	columnsSeparators = new ArrayList<ColumnsSeparator>();
    }

    @Override
    public void createControl(Composite parent) {
	container = new Composite(parent, SWT.NULL);
	GridLayout layout = new GridLayout(1, false);
	container.setLayout(layout);
	container.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

	createConfigGroup();
	createSeparatorsGroup();

	// Required to avoid an error in the system
	setControl(container);
	setPageComplete(false);
    }

    private void createConfigGroup() {
	Composite composite = new Composite(container, SWT.NULL);
	composite.setLayout(new GridLayout(2, false));
	composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	Label label1 = new Label(composite, SWT.NULL);
	label1.setText("Say hello to Fred");

	text1 = new Text(composite, SWT.BORDER | SWT.SINGLE);
	text1.setText("");
	text1.addKeyListener(new KeyListener() {

	    @Override
	    public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		if (!text1.getText().isEmpty()) {
		    setPageComplete(true);
		}
	    }

	});
	GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	text1.setLayoutData(gd);

	Label showSeparatorsLabel = new Label(composite, SWT.NONE);
	showSeparatorsLabel.setText("Show delimiter columns");
	showSeparatorsCheck = new Button(composite, SWT.CHECK);
	showSeparatorsCheck.setSelection(true);
    }

    private void createSeparatorsGroup() {
	Composite composite = new Composite(container, SWT.NULL);
	GridLayout layout = new GridLayout(2, false);
	composite.setLayout(layout);
	composite.setLayoutData(new GridData(GridData.FILL_BOTH));

	Composite viewerContainer = new Composite(composite, SWT.NULL);
	FillLayout viewerlayout = new FillLayout();
	viewerContainer.setLayout(viewerlayout);
	viewerContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
	viewer = new ListViewer(viewerContainer, SWT.NULL);
	viewer.setContentProvider(ArrayContentProvider.getInstance());
	viewer.setLabelProvider(new LabelProvider() {
	    public String getText(Object element) {
		ColumnsSeparator columnsSeparator = (ColumnsSeparator) element;
		return columnsSeparator.toString();
	    };
	});
	viewer.setInput(columnsSeparators);

	Composite buttonsContainer = new Composite(composite, SWT.NULL);
	GridLayout buttonslayout = new GridLayout(1, false);
	buttonsContainer.setLayout(buttonslayout);
	buttonsContainer.setLayoutData(new GridData(GridData.FILL_VERTICAL));

	Button addButton = new Button(buttonsContainer, SWT.NULL);
	addButton.setText("Add");
	addButton.addMouseListener(new MouseListener() {
	    public void mouseUp(MouseEvent e) {
		showCreateSeparatorDialog();
	    }

	    public void mouseDown(MouseEvent e) {
	    }

	    public void mouseDoubleClick(MouseEvent e) {
	    }
	});
    }

    protected void showCreateSeparatorDialog() {
	InputDialog dialog = new AddColumnsSeparatorDialog(container.getShell());
	int returnCode = dialog.open();
	if (returnCode == Dialog.OK) {
	    createSeparator(dialog.getValue());
	}
    }

    private void createSeparator(String delimiter) {
	columnsSeparators.add(new SimpleColumnsSeparator(delimiter));
	viewer.refresh();
	setPageComplete(true);
    }

    public String getText1() {
	return text1.getText();
    }

    public Collection<ColumnsSeparator> getColumnsSeparators() {
	return columnsSeparators;
    }

    public boolean getShowDelimiterColumns() {
	return showSeparatorsCheck.getSelection();
    }

}