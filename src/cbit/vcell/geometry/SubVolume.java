package cbit.vcell.geometry;

/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.awt.*;
import java.io.*;
import java.util.*;
import cbit.sql.KeyValue;
import cbit.util.Compare;
import cbit.util.Matchable;
import cbit.sql.Cacheable;
/**
 * This type was created in VisualAge.
 */
public abstract class SubVolume implements Serializable, Matchable, Cacheable {
	private String name = null;
//	protected Geometry geometry = null;
	private int handle = -1;
	private KeyValue key = null;
	protected transient java.beans.PropertyChangeSupport propertyChange;
	protected transient java.beans.VetoableChangeSupport vetoPropertyChange;
/**
 * SubVolume constructor comment.
 */
protected SubVolume(KeyValue key, String name, int handle) {
	this.name = name;
	this.key = key;
	this.handle = handle;
}
/**
 * The addPropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
	getPropertyChange().addPropertyChangeListener(listener);
}
/**
 * The addVetoableChangeListener method was generated to support the vetoPropertyChange field.
 */
public synchronized void addVetoableChangeListener(java.beans.VetoableChangeListener listener) {
	getVetoPropertyChange().addVetoableChangeListener(listener);
}
/**
 * Insert the method's description here.
 * Creation date: (2/24/2004 2:01:20 PM)
 */
public void clearKey() {
	key = null;
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param obj java.lang.Object
 */
protected boolean compareEqual0(Matchable obj) {
	SubVolume sv = null;
	if (!(obj instanceof SubVolume)){
		return false;
	}
	sv = (SubVolume)obj;
	
	if (!Compare.isEqual(name,sv.name)){
		return false;
	}

	if (handle != sv.handle){
		return false;
	}

	//
	// dont compare 'geometry' objects, would be recursively defined
	//
	
	return true;
}
/**
 * The firePropertyChange method was generated to support the propertyChange field.
 */
public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
	getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
}
/**
 * The fireVetoableChange method was generated to support the vetoPropertyChange field.
 */
public void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws java.beans.PropertyVetoException {
	getVetoPropertyChange().fireVetoableChange(propertyName, oldValue, newValue);
}
/**
 * This method was created in VisualAge.
 * @return int
 */
public int getHandle() {
	return handle;
}
/**
 * This method was created in VisualAge.
 * @return cbit.sql.KeyValue
 */
public KeyValue getKey() {
	return key;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getName() {
	return name;
}
/**
 * Accessor for the propertyChange field.
 */
protected java.beans.PropertyChangeSupport getPropertyChange() {
	if (propertyChange == null) {
		propertyChange = new java.beans.PropertyChangeSupport(this);
	};
	return propertyChange;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public abstract String getVCML();
/**
 * Accessor for the vetoPropertyChange field.
 */
protected java.beans.VetoableChangeSupport getVetoPropertyChange() {
	if (vetoPropertyChange == null) {
		vetoPropertyChange = new java.beans.VetoableChangeSupport(this);
	};
	return vetoPropertyChange;
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param x double
 * @param y double
 * @param z double
 */
public abstract boolean isInside(double x,double y, double z, GeometrySpec geometrySpec) throws GeometryException, cbit.image.ImageException, cbit.vcell.parser.ExpressionException;
/**
 * The removePropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
	getPropertyChange().removePropertyChangeListener(listener);
}
/**
 * The removeVetoableChangeListener method was generated to support the vetoPropertyChange field.
 */
public synchronized void removeVetoableChangeListener(java.beans.VetoableChangeListener listener) {
	getVetoPropertyChange().removeVetoableChangeListener(listener);
}
/**
 * This method was created in VisualAge.
 * @param handle int
 */
void setHandle(int handle) {
	this.handle = handle;
}
/**
 * This method was created in VisualAge.
 * @param name java.lang.String
 */
public void setName(String aName) throws java.beans.PropertyVetoException {
	String oldName = this.name;
	fireVetoableChange("name",oldName,aName);
	this.name = aName;
	firePropertyChange("name",oldName,name);
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String toString() {
	return getClass().getName()+"@"+Integer.toHexString(hashCode())+": "+getName()+"("+getHandle()+")";
}
}
