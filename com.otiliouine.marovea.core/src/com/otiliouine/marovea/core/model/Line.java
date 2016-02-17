package com.otiliouine.marovea.core.model;

import java.util.List;

public class Line extends ModelObject{

	private int index;
	private String text;
	private List<Fragment> fragments;
	private TextTable table;

	// public Line(List<String> words) {
	// data = new ArrayList<Fragment>(words.size());
	// for (String word : words) {
	// data.add(new Fragment(word));
	// }
	// this(data);
	// }

	public Line(List<Fragment> fragments) {
		setFragments(fragments);
	}

	/* (non-Javadoc)
	 * @see com.otiliouine.marovea.core.model.Line#getFragments()
	 */
	public List<Fragment> getFragments() {
		return fragments;
	}

	/* (non-Javadoc)
	 * @see com.otiliouine.marovea.core.model.Line#setFragments(java.util.List)
	 */
	public void setFragments(List<Fragment> fragments) {
		this.fragments = fragments;
		for (Fragment fragment : fragments) {
			fragment.setLine(this);
		}
		firePropertyChange("fragments", null, fragments);
	}

	/* (non-Javadoc)
	 * @see com.otiliouine.marovea.core.model.Line#addFragment(com.otiliouine.marovea.core.model.Fragment)
	 */
	public void addFragment(Fragment fragment) {
		fragments.add(fragment);
		fragment.setLine(this);
		firePropertyChange("fragments", null, null);
	}

	/* (non-Javadoc)
	 * @see com.otiliouine.marovea.core.model.Line#getFragmentAt(int)
	 */
	public Fragment getFragmentAt(int index) {
		return fragments.get(index);
	}
	
	/* (non-Javadoc)
	 * @see com.otiliouine.marovea.core.model.Line#getIndex()
	 */
	public int getIndex() {
		return index;
	}
	
	public void moveFragment(int fromIndex, int toIndex) {
		Fragment fragment = fragments.remove(fromIndex);
		fragments.add(toIndex, fragment);
		firePropertyChange("fragments", null, null);
	}

}
