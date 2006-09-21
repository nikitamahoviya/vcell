package cbit.vcell.math;

/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.util.*;
import java.io.*;
import cbit.util.*;
import cbit.vcell.parser.Expression;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public abstract class SubDomain implements Serializable, Matchable {
	private String name = null;
	private Vector equationList = new Vector();
	private FastSystem fastSystem = null;
/**
 * This method was created by a SmartGuide.
 * @param name java.lang.String
 */
protected SubDomain (String name) {
	this.name = name;
}
/**
 * This method was created by a SmartGuide.
 * @param equation cbit.vcell.math.Equation
 */
public void addEquation(Equation equation) throws MathException {
	if (getEquation(equation.getVariable()) != null){
		throw new MathException("equation for variable "+equation.getVariable().getName()+" already exists");
	}
	equationList.addElement(equation);	
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param object java.lang.Object
 */
protected boolean compareEqual0(Object object) {
	
	SubDomain subDomain = null;
	if (object == null){
		return false;
	}
	if (!(object instanceof SubDomain)){
		return false;
	}else{
		subDomain = (SubDomain)object;
	}

	//
	// compare name
	//
	if (!Compare.isEqualOrNull(name,subDomain.name)){
		return false;
	}
	//
	// compare equations
	//
	if (!Compare.isEqual(equationList,subDomain.equationList)){
		return false;
	}
	//
	// compare fastSystem
	//
	if (!Compare.isEqualOrNull(fastSystem,subDomain.fastSystem)){
		return false;
	}
	return true;
}
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.math.Equation
 * @param variable cbit.vcell.math.Variable
 */
public Equation getEquation(Variable variable) {
	Enumeration enum1 = equationList.elements();
	while (enum1.hasMoreElements()){
		Equation equ = (Equation)enum1.nextElement();
		if (equ.getVariable().getName().equals(variable.getName())){
			return equ;
		}
	}
	return null;
}
/**
 * This method was created by a SmartGuide.
 * @return java.util.Enumeration
 */
public java.util.Enumeration getEquations() {
	return equationList.elements();
}
/**
 * This method was created in VisualAge.
 * @return cbit.vcell.math.FastSystem
 */
public FastSystem getFastSystem() {
	return fastSystem;
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String getName() {
	return name;
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public abstract String getVCML(int spatialDimension);
/**
 * This method was created in VisualAge.
 * @param equation cbit.vcell.math.Equation
 */
public void replaceEquation(Equation equation) throws MathException {
	Equation currentEquation = getEquation(equation.getVariable());
	if (currentEquation!=null){
		equationList.removeElement(currentEquation);
	}
	addEquation(equation);
}
/**
 * This method was created in VisualAge.
 * @param fastSystem cbit.vcell.math.FastSystem
 * @exception java.lang.Exception The exception description.
 */
public void setFastSystem(FastSystem fastSystem) {
	this.fastSystem = fastSystem;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String toString() {
	return getClass().getName()+"@"+Integer.toHexString(hashCode())+": "+getName();
}
/**
 * Insert the method's description here.
 * Creation date: (10/12/2002 3:30:10 PM)
 */
public void trimTrivialEquations(MathDescription mathDesc) {
	for (int i = 0; i < equationList.size(); i++){
		Equation equ = (Equation)equationList.elementAt(i);
		Vector expressionList = equ.getExpressions(mathDesc);
		boolean bNonZeroExists = false;
		for (int j = 0; j < expressionList.size(); j++){
			Expression exp = (Expression)expressionList.elementAt(j);
			if (!exp.isZero()){
				bNonZeroExists = true;
				break;
			}
		}
		if (!bNonZeroExists){
			equationList.remove(i);
			i--;
		}
	}
}
}
