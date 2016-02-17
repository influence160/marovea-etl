package com.otiliouine.marovea.ui.component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

import com.otiliouine.marovea.core.model.Fragment;
import com.otiliouine.marovea.core.model.Line;
import com.otiliouine.marovea.core.model.TextTable;

public class StyledTextTable extends StyledText {

    private static final String LINE_DELIMITER = "\n";

    private TextTable textTable;
    private Color fragmentColor = new Color(getDisplay(), 0, 0, 0);
    private Color delimiterColor = new Color(getDisplay(), 255, 0, 0);

    public StyledTextTable(Composite parent, int style) {
	super(parent, style);
	setEditable(false);
    }

    public void setTextTable(TextTable textTable) {
	this.textTable = textTable;
	readText(textTable);
	textTable.addPropertyChangeListener(new PropertyChangeListener() {
	    
	    private TextTable textTable;

	    @Override
	    public void propertyChange(PropertyChangeEvent evt) {
		readText((TextTable) evt.getSource());
	    }
	});
    }

    public void setColors(Color fragmentColor, Color delimiterColor) {
	this.fragmentColor = fragmentColor;
	this.delimiterColor = delimiterColor;
    }

    public void readText(TextTable textTable) {
	setText("");
	Color[] colors = new Color[] { fragmentColor, delimiterColor };
	List<Line> lines = textTable.getLines();
	Line line;
	Fragment fragment;
	int cursor = 0;
	for (int i = 0; i < lines.size(); i++) {
	    line = lines.get(i);
	    List<Fragment> fragments = line.getFragments();
	    for (int j = 0; j < fragments.size(); j++) {
		fragment = fragments.get(j);
		String textToInsert = fragment.getText();
		append(textToInsert);
		Color color = colors[j % 2];
		StyleRange styleRange = new StyleRange();
		styleRange.start = cursor;
		cursor += textToInsert.length();
		styleRange.length = textToInsert.length();
		styleRange.foreground = color;
		setStyleRange(styleRange);
	    }
	    append(LINE_DELIMITER);
	    cursor++;
	}
    }

}
