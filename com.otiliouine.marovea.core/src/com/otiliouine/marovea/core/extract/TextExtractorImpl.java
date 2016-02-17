package com.otiliouine.marovea.core.extract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.otiliouine.marovea.core.io.LinesReader;
import com.otiliouine.marovea.core.model.Column;
import com.otiliouine.marovea.core.model.Fragment;
import com.otiliouine.marovea.core.model.Fragment;
import com.otiliouine.marovea.core.model.Line;
import com.otiliouine.marovea.core.model.Line;
import com.otiliouine.marovea.core.model.TextTable;
import com.otiliouine.marovea.core.model.TextTable;

public class TextExtractorImpl implements TextExtractor {
	
	private ExtractionRules rules;

	public TextExtractorImpl(ExtractionRules rules) {
		this.rules = rules;
	}

	/* (non-Javadoc)
	 * @see com.otiliouine.marovea.core.extract.TextExtractor#extract(com.otiliouine.marovea.core.io.LinesReader)
	 */
	@Override
	public TextTable extract(LinesReader reader)
			throws IOException {
		List<Line> lines = new ArrayList<Line>();

		LineDecomposer lineDecomposer = new LineDecomposer(
				rules.getColumnsSeparators());
		lineDecomposer.setAddDelimiterColumns(rules.isShowDelimiterColumns());

		String lineText;
		while ((lineText = reader.readLine()) != null) {
			List<String> lineWords = lineDecomposer.decompose(lineText);
			Line line = createLine(lineWords);
			lines.add(line);
		}

		normalizeLinesSize(lines);

		List<Column> columns = createColumns(lines,
				rules.isShowDelimiterColumns());

		TextTable table = new TextTable(lines);
		table.setColumns(columns);

		return table;
	}

	private void normalizeLinesSize(List<Line> lines) {
		int maxLength = 0;
		for (Line line : lines) {
			if (line.getFragments().size() > maxLength) {
				maxLength = line.getFragments().size();
			}
		}

		for (Line line : lines) {
			while (line.getFragments().size() < maxLength) {
				Fragment fragment = new Fragment("");
				line.addFragment(fragment);
			}
		}
	}

	private List<Column> createColumns(List<Line> lines,
			boolean addDelimiterColumns) {
		if (lines.isEmpty()) {
			return new ArrayList<Column>();
		}

		int columnsCount = lines.get(0).getFragments().size();
		List<Column> columns = new ArrayList<Column>(columnsCount);
		for (int i = 0; i < columnsCount; i++) {
			String prefix;
			int suffix;
			if (addDelimiterColumns) {
				int mod = i % 2;
				prefix = mod == 0 ? Column.COLUMN_NAME_PREFIX
						: Column.SEPARATOR_NAME_PREFIX;
				suffix = ((int) ((i - mod) / 2));
			} else {
				prefix = Column.COLUMN_NAME_PREFIX;
				suffix = i;
			}
			Column column = new Column(prefix + suffix);
			columns.add(column);
		}

		return columns;
	}

	private Line createLine(List<String> words) {
		List<Fragment> fragments = new ArrayList<Fragment>(words.size());
		for (String word : words) {
			fragments.add(new Fragment(word));
		}
		Line line = new Line(fragments);
		return line;
	}
}
