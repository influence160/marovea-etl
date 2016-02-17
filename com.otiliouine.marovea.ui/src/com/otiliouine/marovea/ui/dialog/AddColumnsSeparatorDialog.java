package com.otiliouine.marovea.ui.dialog;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

public class AddColumnsSeparatorDialog extends InputDialog{

	public AddColumnsSeparatorDialog(Shell parentShell) {
		super(parentShell, "Add a columns separator", "Delimiter", "", null);
	}


}
