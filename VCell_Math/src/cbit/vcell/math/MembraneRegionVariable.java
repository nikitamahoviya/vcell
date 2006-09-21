package cbit.vcell.math;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
/**
 * This class was generated by a SmartGuide.
 * 
 */
public class MembraneRegionVariable extends Variable {
/**
 * MembraneVariable constructor comment.
 * @param name java.lang.String
 */
public MembraneRegionVariable(String name) {
	super(name);
}


/**
 * This method was created in VisualAge.
 * @return boolean
 * @param obj Matchable
 */
public boolean compareEqual(cbit.util.Matchable obj) {
	if (!(obj instanceof MembraneRegionVariable)){
		return false;
	}
	if (!compareEqual0(obj)){
		return false;
	}
	
	return true;
}


/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String getVCML() {
	return VCML.MembraneRegionVariable+"   "+getName();
}
}