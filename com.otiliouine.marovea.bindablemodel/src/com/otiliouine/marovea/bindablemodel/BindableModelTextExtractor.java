//package com.otiliouine.marovea.bindablemodel;
//
//import java.util.List;
//
//import com.otiliouine.marovea.core.extract.ExtractionRules;
//import com.otiliouine.marovea.core.extract.TextExtractorImpl;
//import com.otiliouine.marovea.core.model.Fragment;
//import com.otiliouine.marovea.core.model.Fragment;
//import com.otiliouine.marovea.core.model.Line;
//import com.otiliouine.marovea.core.model.Line;
//import com.otiliouine.marovea.core.model.TextTable;
//import com.otiliouine.marovea.core.model.TextTableImpl;
//
//public class BindableModelTextExtractor extends TextExtractorImpl {
//
//	public BindableModelTextExtractor(ExtractionRules rules) {
//		super(rules);
//	}
//
//	protected TextTable createTextTable(List<Line> lines) {
//		return new TextTableImpl(lines);
//	}
//
//	protected Fragment createEmptyFragment() {
//		return new BindableFragment("");
//	}
//
//	protected Line createLineInstance(List<Fragment> fragments) {
//		return new BindableLine(fragments);
//	}
//
//	protected Fragment createFragment(String word) {
//		return new BindableFragment(word);
//	}
//
//}
