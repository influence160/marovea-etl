//package com.otiliouine.marovea.bindablemodel;
//
//import java.beans.PropertyChangeSupport;
//import java.util.List;
//
//import com.otiliouine.marovea.core.model.Fragment;
//import com.otiliouine.marovea.core.model.Line;
//import com.otiliouine.marovea.core.model.Line;
//
//public class BindableLine implements Line {
//	
//	protected final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
//			this);
//	
//	private Line line;
//	
//	public BindableLine(List<Fragment> fragments) {
//		line = new Line(fragments);
//		propertyChangeSupport.firePropertyChange("fragments", null, fragments);
//	}
//	
//	@Override
//	public List<Fragment> getFragments() {
//		return line.getFragments();
//	}
//
//	@Override
//	public void setFragments(List<Fragment> fragments) {
//		line.setFragments(fragments);
//		propertyChangeSupport.firePropertyChange("fragments", null, null);
//	}
//
//	@Override
//	public void addFragment(Fragment fragment) {
//		line.addFragment(fragment);
//		propertyChangeSupport.firePropertyChange("fragments", null, null);
//	}
//
//	@Override
//	public Fragment getFragmentAt(int index) {
//		return line.getFragmentAt(index);
//	}
//
//	@Override
//	public int getIndex() {
//		return line.getIndex();
//	}
//
//}
