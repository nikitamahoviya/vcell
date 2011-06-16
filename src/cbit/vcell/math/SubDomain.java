/*
 * Copyright (C) 1999-2011 University of Connecticut Health Center
 *
 * Licensed under the MIT License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/mit-license.php
 */

package cbit.vcell.math;
import java.util.*;
import java.io.*;

import org.vcell.util.Compare;
import org.vcell.util.Matchable;

import cbit.util.*;
import cbit.vcell.parser.Expression;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public abstract class SubDomain implements Serializable, Matchable {
	private String name = null;
	private Vector<Equation> equationList = new Vector<Equation>();
	private FastSystem fastSystem = null;
	private ArrayList<JumpProcess> listOfJumpProcesses = null;
	private ArrayList<VarIniCondition> listOfVarIniConditions = null;
	private ArrayList<ParticleJumpProcess> listOfParticleJumpProcesses = null;
	private ArrayList<ParticleProperties> listOfParticleProperties = null;

/**
 * This method was created by a SmartGuide.
 * @param name java.lang.String
 */
protected SubDomain (String name) {
	this.name = name;
	listOfParticleJumpProcesses = new ArrayList<ParticleJumpProcess>();
	listOfParticleProperties = new ArrayList<ParticleProperties>();
	listOfJumpProcesses = new ArrayList<JumpProcess>();
	listOfVarIniConditions = new ArrayList<VarIniCondition>();
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
 * Append a new process to the jump process list if it is still not in the list.
 * Creation date: (6/26/2006 5:28:25 PM)
 */
public void addJumpProcess(JumpProcess newJumpProcess) throws MathException
{
	if(getJumpProcess(newJumpProcess.getName())!=null)
		throw new MathException("JumpProcess "+newJumpProcess.getName()+" already exists");
	listOfJumpProcesses.add(newJumpProcess);
}

public void addParticleJumpProcess(ParticleJumpProcess newParticleJumpProcess) throws MathException
{
	if(getParticleJumpProcess(newParticleJumpProcess.getName())!=null)
		throw new MathException("JumpProcess "+newParticleJumpProcess.getName()+" already exists");
	listOfParticleJumpProcesses.add(newParticleJumpProcess);
}

public void addParticleProperties(ParticleProperties newParticleProperties) throws MathException
{
	if(getParticleProperties(newParticleProperties.getVariable())!=null)
		throw new MathException("ParticleProperties "+newParticleProperties.getVariable().getName()+" already exists");
	listOfParticleProperties.add(newParticleProperties);
}


public ParticleProperties getParticleProperties(Variable variable) {
	for (ParticleProperties pp : listOfParticleProperties) {
		if (pp.getVariable().getName().equals(variable.getName())) {
			return pp;
		}
	}
	return null;
}


/**
 * Append a new variable initial condition to the list if the variable is not in the list.
 * Creation date: (6/27/2006 10:02:29 AM)
 * @param newVarIniCondition cbit.vcell.math.VarIniCondition
 */
public void addVarIniCondition(VarIniCondition newVarIniCondition) throws MathException
{
	if(getVarIniCondition(newVarIniCondition.getVar().getName())!=null)
		throw new MathException("Initial condition regarding variable: "+newVarIniCondition.getVar().getName()+" already exists");
	listOfVarIniConditions.add(newVarIniCondition);
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
	//
	// compare jumpProcesses
	//
	if ((listOfJumpProcesses != null) && (subDomain.listOfJumpProcesses != null))
	{
		JumpProcess jumpProcesses1[] = (JumpProcess[]) listOfJumpProcesses.toArray(new JumpProcess[0]);
		JumpProcess jumpProcesses2[] = (JumpProcess[]) subDomain.listOfJumpProcesses.toArray(new JumpProcess[0]);
		
		if (!Compare.isEqualOrNull(jumpProcesses1, jumpProcesses2)){ //call isEqualOrNull(Matchable[], Matchable[]) function
			return false;
		}
	}
	else return false;
	//
	// compare varIniConditions
	//
	if ((listOfVarIniConditions != null) && (subDomain.listOfVarIniConditions != null))
	{
		VarIniCondition varIniConditions1[] = (VarIniCondition[]) listOfVarIniConditions.toArray(new VarIniCondition[0]);
		VarIniCondition varIniConditions2[] = (VarIniCondition[]) subDomain.listOfVarIniConditions.toArray(new VarIniCondition[0]);
		if (!Compare.isEqualOrNull(varIniConditions1,varIniConditions2)){
			return false;
		}
	
	}
	else return false;
		
	if ((listOfParticleProperties != null) && (subDomain.listOfParticleProperties != null))
	{
		ParticleProperties pps1[] = (ParticleProperties[]) listOfParticleProperties.toArray(new ParticleProperties[0]);
		ParticleProperties pps2[] = (ParticleProperties[]) subDomain.listOfParticleProperties.toArray(new ParticleProperties[0]);
		
		if (!Compare.isEqualOrNull(pps1, pps2)){ //call isEqualOrNull(Matchable[], Matchable[]) function
			return false;
		}
	} else {
		return false;
	}
	if ((listOfParticleJumpProcesses != null) && (subDomain.listOfParticleJumpProcesses != null))
	{
		ParticleJumpProcess pjps1[] = (ParticleJumpProcess[]) listOfParticleJumpProcesses.toArray(new ParticleJumpProcess[0]);
		ParticleJumpProcess pjps2[] = (ParticleJumpProcess[]) subDomain.listOfParticleJumpProcesses.toArray(new ParticleJumpProcess[0]);
		
		if (!Compare.isEqualOrNull(pjps1, pjps2)){ //call isEqualOrNull(Matchable[], Matchable[]) function
			return false;
		}
	} else {
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
	for (Equation equ : equationList){
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
public Enumeration<Equation> getEquations() {
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
 * Get a jump process from the list by it's index.
 * Creation date: (6/27/2006 10:23:01 AM)
 * @return cbit.vcell.math.JumpProcess
 * @param index int
 */
public JumpProcess getJumpProcess(int index)
{
	if(index<listOfJumpProcesses.size())
		return (JumpProcess)listOfJumpProcesses.get(index);
	return null;
}


/**
 * Get a jump process from process list by it's name.
 * Creation date: (6/27/2006 10:36:11 AM)
 * @return cbit.vcell.math.JumpProcess
 * @param processName java.lang.String
 */
public JumpProcess getJumpProcess(String processName) {
	for(JumpProcess jp : listOfJumpProcesses){
		if(jp.getName().equals(processName)){
			return jp;
		}
	}
	return null;
}

public ParticleJumpProcess getParticleJumpProcess(String processName) {
	for (ParticleJumpProcess pjp : listOfParticleJumpProcesses){
		if(pjp.getName().compareTo(processName)==0)
			return pjp;
	}
	return null;
}

public List<ParticleJumpProcess> getParticleJumpProcesses() {
	return Collections.unmodifiableList(listOfParticleJumpProcesses);
}

public List<ParticleProperties> getParticleProperties() {
	return Collections.unmodifiableList(listOfParticleProperties);
}


/**
 * Return the reference of the jump process list.
 * Creation date: (6/27/2006 3:05:52 PM)
 * @return java.util.Vector
 */
public List<JumpProcess> getJumpProcesses() {
	return Collections.unmodifiableList(listOfJumpProcesses);
}

/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String getName() {
	return name;
}


/**
 * Get a variable initial condition from the list by it's index.
 * Creation date: (6/27/2006 10:40:07 AM)
 * @return cbit.vcell.math.VarIniCondition
 * @param index int
 */
public VarIniCondition getVarIniCondition(int index) 
{
	if(index<listOfVarIniConditions.size())
		return (VarIniCondition)listOfVarIniConditions.get(index);
	return null;
}


/**
 * Get a variable initial condition from list by varaible's name.
 * Creation date: (6/27/2006 10:42:24 AM)
 * @return cbit.vcell.math.VarIniCondition
 * @param varName java.lang.String
 */
public VarIniCondition getVarIniCondition(String varName) 
{
	for (VarIniCondition vic : listOfVarIniConditions){
		if (vic.getVar().getName().equals(varName)){
			return vic;
		}
	}
	return null;
}


/**
 * Return the reference of the variable initial condition list.
 * Creation date: (6/27/2006 3:07:26 PM)
 * @return java.util.Vector
 */
public List<VarIniCondition> getVarIniConditions() {
	return Collections.unmodifiableList(listOfVarIniConditions);
}


/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public abstract String getVCML(int spatialDimension);


/**
 * Remove a jump process from jump process list by it's index in the list.
 * Creation date: (6/26/2006 5:39:50 PM)
 */
public void removeJumpProcess(int index)
{
	if(index<listOfJumpProcesses.size())
		listOfJumpProcesses.remove(index);
}


/**
 * Remove a jump process from jump process list by it's index in the list.
 * Creation date: (6/26/2006 5:39:50 PM)
 */
public void removeJumpProcess(String procName)
{
	for(JumpProcess jp : listOfJumpProcesses){
		if (jp.getName().equals(procName)){
			listOfJumpProcesses.remove(jp);
		}
	}
}


/**
 * empty the jump process list
 * Creation date: (6/26/2006 5:39:50 PM)
 */
public void removeJumpProcesses()
{
	listOfJumpProcesses.clear();
}


/**
 * Remove a variable initial conditino from list by it's index.
 * Creation date: (6/27/2006 10:04:45 AM)
 * @param index int
 */
public void removeVarIniCondition(int index)
{
	if(index<listOfVarIniConditions.size())
		listOfVarIniConditions.remove(index);
}


/**
 * Remove a variable initial condition from list by it's name in the list.
 * Creation date: (6/27/2006 10:06:44 AM)
 * @param varName java.lang.String
 */
public void removeVarIniCondition(String varName)
{
	for (VarIniCondition vic : listOfVarIniConditions){
		if(vic.getVar().getName().equals(varName)){
			listOfVarIniConditions.remove(vic);
		}
	}	
}


/**
 * empty the variable initial condition list
 * Creation date: (11/14/2006 6:40:22 PM)
 */
public void removeVarIniConditions() 
{
	listOfVarIniConditions.clear();
}


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
		Vector<Expression> expressionList = equ.getExpressions(mathDesc);
		boolean bNonZeroExists = false;
		for (Expression exp : expressionList){
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
