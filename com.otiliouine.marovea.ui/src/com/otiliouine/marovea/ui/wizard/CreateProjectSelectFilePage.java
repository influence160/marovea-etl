package com.otiliouine.marovea.ui.wizard;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class CreateProjectSelectFilePage extends WizardPage {

	private static final String INFO_MESSAGE = " Select an existing text file or paste text from clipboard";
	private static final String ERROR_MESSAGE = " No file are found to import";

	private Composite container;

	private Text projectNameText;
	private Button selectFileRadio;
	private Text fileText;
	private Button browseButton;
	private Button selectTextRadio;
	private Text textText;

	public CreateProjectSelectFilePage() {
		super("Enter source text");
		setTitle("Enter source text");
		setInfoMessage();
	}

	private void setInfoMessage() {
		setMessage(INFO_MESSAGE, WizardPage.INFORMATION);
	}

	private void setErrorMessage() {
		setMessage(ERROR_MESSAGE, WizardPage.WARNING);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(1, false);
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		createProjectNameGroup();
		createTextSourceGroup();

		// gd.horizontalSpan = 2;
		// gd.horizontalAlignment = SWT.FILL;

		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}

	private void createTextSourceGroup() {
		Composite composite = new Composite(container, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		selectFileRadio = new Button(composite, SWT.RADIO);
		selectFileRadio.setSelection(true);
		selectFileRadio.setText("File");
		selectFileRadio.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectFileRadio.getSelection()) {
					fileText.setEnabled(true);
					browseButton.setEnabled(true);
					textText.setEnabled(false);
				} else {
					fileText.setEnabled(false);
					browseButton.setEnabled(false);
					textText.setEnabled(true);
				}
				ensureReadyToComplete();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		selectFileRadio.setLayoutData(gd);

		fileText = new Text(composite, SWT.BORDER | SWT.SINGLE);
		fileText.setText("");
		fileText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				ensureReadyToComplete();
			}
		});
		fileText.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				if (fileText.getText().isEmpty()) {
					openFileDialog();
				}
			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 250;
		fileText.setLayoutData(gd);

		MouseListener browseMouseListener = new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				openFileDialog();
			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		};
		browseButton = new Button(composite, SWT.PUSH);
		browseButton.setText("Browse...");
		browseButton.addMouseListener(browseMouseListener);

		selectTextRadio = new Button(composite, SWT.RADIO);
		selectTextRadio.setSelection(false);
		selectTextRadio.setText("Text");
		gd = new GridData();
		gd.horizontalSpan = 2;
		selectTextRadio.setLayoutData(gd);

		textText = new Text(composite, SWT.BORDER | SWT.MULTI);
		textText.setText("");
		textText.setEnabled(false);
		textText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				ensureReadyToComplete();
			}
		});
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		textText.setLayoutData(gd);
	}

	private void createProjectNameGroup() {
		Composite composte = new Composite(container, SWT.NULL);
		composte.setLayout(new GridLayout(2, false));
		composte.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label1 = new Label(composte, SWT.NULL);
		label1.setText("Project name");

		projectNameText = new Text(composte, SWT.BORDER | SWT.SINGLE);
		projectNameText.setText("");
		projectNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				ensureReadyToComplete();
			}
		});
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		projectNameText.setLayoutData(gd);
	}

	private void ensureReadyToComplete() {
		boolean projectNameTextEmpty = projectNameText.getText().isEmpty();
		if (projectNameTextEmpty) {
			setPageComplete(false);
			setInfoMessage();
		} else {
			if (selectFileRadio.getSelection() == true) {
				String filePath = fileText.getText();
				if (filePath.isEmpty()) {
					setPageComplete(false);
					setInfoMessage();
				} else {
					File file = new File(filePath);
					boolean fileExists = file.exists() && file.isFile();
					if (fileExists) {
						setPageComplete(true);
						setInfoMessage();
					} else {
						setPageComplete(false);
						setErrorMessage();
					}
				}
			} else {
				setPageComplete(!textText.getText().isEmpty());
				setInfoMessage();
			}
		}
	}

	private boolean isSelectedFileExist() {
		String filePath = fileText.getText();
		File file = new File(filePath);
		if (filePath.isEmpty()) {
			setInfoMessage();
			return false;
		} else {
			boolean fileExists = file.exists() && file.isFile();
			if (fileExists == false) {
				setErrorMessage();
				return false;
			} else {
				setInfoMessage();
				return true;
			}
		}
	}

	private void openFileDialog() {
		FileDialog dialog = new FileDialog(container.getShell());
		String filePath = dialog.open();
		if (filePath != null) {
			fileText.setText(filePath);
			ensureReadyToComplete();
		}
	}

	public String getProjectName() {
		return projectNameText.getText();
	}
	
	public boolean isFileRadioSelected() {
		return selectFileRadio.getSelection();
	}
	
	public File getFile() {
		String filePath = fileText.getText();
		File file = new File(filePath);
		return file;
	}
	
	public String getText() {
		return textText.getText();
	}
}