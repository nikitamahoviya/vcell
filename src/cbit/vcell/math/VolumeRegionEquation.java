package cbit.vcell.math;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.util.Enumeration;
import java.util.Vector;

import org.vcell.util.CommentStringTokenizer;
import org.vcell.util.Compare;
import org.vcell.util.Matchable;

import cbit.vcell.parser.Expression;
import cbit.vcell.parser.ExpressionException;
import cbit.vcell.solver.SimulationSymbolTable;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public class VolumeRegionEquation extends Equation {
	private Expression uniformRateExpression = new Expression(0.0);
	private Expression volumeRateExpression = new Expression(0.0);

/**
 * OdeEquation constructor comment.
 * @param subDomain cbit.vcell.math.SubDomain
 * @param var cbit.cell.math.Variable
 * @param initialExp cbit.vcell.parser.Expression
 * @param rateExp cbit.vcell.parser.Expression
 */
public VolumeRegionEquation(VolumeRegionVariable var, Expression initialExp) {
	super(var, initialExp, null);
}


/**
 * Insert the method's description here.
 * Creation date: (9/4/2003 12:32:19 PM)
 * @return boolean
 * @param object cbit.util.Matchable
 */
public boolean compareEqual(Matchable object) {
	VolumeRegionEquation equ = null;
	if (!(object instanceof VolumeRegionEquation)){
		return false;
	}else{
		equ = (VolumeRegionEquation)object;
	}
	if (!compareEqual0(equ)){
		return false;
	}
	if (!Compare.isEqualOrNull(volumeRateExpression,equ.volumeRateExpression)){
		return false;
	}
	if (!Compare.isEqualOrNull(uniformRateExpression,equ.uniformRateExpression)){
		return false;
	}
	return true;
}


/**
 * Insert the method's description here.
 * Creation date: (10/10/2002 10:41:10 AM)
 * @param sim cbit.vcell.solver.Simulation
 */
void flatten(SimulationSymbolTable simSymbolTable, boolean bRoundCoefficients) throws cbit.vcell.parser.ExpressionException, MathException {
	super.flatten0(simSymbolTable,bRoundCoefficients);
	
	volumeRateExpression = getFlattenedExpression(simSymbolTable,volumeRateExpression,bRoundCoefficients);
	uniformRateExpression = getFlattenedExpression(simSymbolTable,uniformRateExpression,bRoundCoefficients);
}


/**
 * This method was created by a SmartGuide.
 * @return java.util.Vector
 */
protected Vector<Expression> getExpressions(MathDescription mathDesc){
	Vector<Expression> list = new Vector<Expression>();
	list.addElement(getVolumeRateExpression());
	list.addElement(getUniformRateExpression());
	
	if (getRateExpression()!=null)		list.addElement(getRateExpression());
	if (getInitialExpression()!=null)	list.addElement(getInitialExpression());
	if (getExactSolution()!=null)		list.addElement(getExactSolution());
	
	//
	// get Parent Subdomain
	//
	SubDomain parentSubDomain = null;
	Enumeration<SubDomain> enum1 = mathDesc.getSubDomains();
	while (enum1.hasMoreElements()){
		SubDomain subDomain = enum1.nextElement();
		if (subDomain.getEquation(getVariable()) == this){
			parentSubDomain = subDomain;
			break;
		}
	}
	
	try {
		MembraneSubDomain membranes[] = mathDesc.getMembraneSubDomains((CompartmentSubDomain)parentSubDomain);
		for (int i = 0; membranes!=null && i < membranes.length; i++){
			JumpCondition jump = membranes[i].getJumpCondition(getVariable());
			if (jump != null) {
				if (membranes[i].getInsideCompartment()==parentSubDomain){
					list.addElement(jump.getInFluxExpression());
				}else{
					list.addElement(jump.getOutFluxExpression());
				}
			}
		}
	}catch (Exception e){
		e.printStackTrace(System.out);
	}
	
	return list;
}

/**
 * This method was created by a SmartGuide.
 * @return java.util.Enumeration
 */
public Enumeration<Expression> getTotalExpressions() throws ExpressionException {
	Vector<Expression> vector = new Vector<Expression>();
	Expression lvalueExp = new Expression("VolumeRate_"+getVariable().getName());
	Expression rvalueExp = new Expression(getVolumeRateExpression());
	Expression totalExp = Expression.assign(lvalueExp,rvalueExp);
	totalExp.bindExpression(null);
	totalExp.flatten();
	vector.addElement(totalExp);
	vector.addElement(getTotalInitialExpression());
	Expression solutionExp = getTotalSolutionExpression();
	if (solutionExp!=null){
		vector.addElement(solutionExp);
	}	
	return vector.elements();
}


/**
 * Insert the method's description here.
 * Creation date: (7/9/01 2:05:09 PM)
 * @return cbit.vcell.parser.Expression
 */
public Expression getUniformRateExpression() {
	return uniformRateExpression;
}


/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String getVCML() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("\t"+VCML.VolumeRegionEquation+" "+getVariable().getName()+" {\n");
	if (getUniformRateExpression() != null){
		buffer.append("\t\t"+VCML.UniformRate+" "+getUniformRateExpression().infix()+";\n");
	}else{
		buffer.append("\t\t"+VCML.UniformRate+" "+"0.0;\n");
	}
	if (getVolumeRateExpression() != null){
		buffer.append("\t\t"+VCML.VolumeRate+" "+getVolumeRateExpression().infix()+";\n");
	}else{
		buffer.append("\t\t"+VCML.VolumeRate+" "+"0.0;\n");
	}
	if (initialExp != null){
		buffer.append("\t\t"+VCML.Initial+"\t "+initialExp.infix()+";\n");
	}
	switch (solutionType){
		case UNKNOWN_SOLUTION:{
			if (initialExp == null){
				buffer.append("\t\t"+VCML.Initial+"\t "+"0.0;\n");
			}
			break;
		}
		case EXACT_SOLUTION:{
			buffer.append("\t\t"+VCML.Exact+" "+exactExp.infix()+";\n");
			break;
		}
	}				
		
	buffer.append("\t}\n");
	return buffer.toString();		
}


/**
 * Insert the method's description here.
 * Creation date: (7/9/01 2:05:09 PM)
 * @return cbit.vcell.parser.Expression
 */
public Expression getVolumeRateExpression() {
	return volumeRateExpression;
}


/**
 * This method was created by a SmartGuide.
 * @param tokens java.util.StringTokenizer
 * @exception java.lang.Exception The exception description.
 */
public void read(CommentStringTokenizer tokens) throws MathFormatException, ExpressionException {
	String token = null;
	token = tokens.nextToken();
	if (!token.equalsIgnoreCase(VCML.BeginBlock)){
		throw new MathFormatException("unexpected token "+token+" expecting "+VCML.BeginBlock);
	}			
	while (tokens.hasMoreTokens()){
		token = tokens.nextToken();
		if (token.equalsIgnoreCase(VCML.EndBlock)){
			break;
		}			
		if (token.equalsIgnoreCase(VCML.Initial)){
			initialExp = MathFunctionDefinitions.fixFunctionSyntax(tokens);
			continue;
		}
		if (token.equalsIgnoreCase(VCML.VolumeRate)){
			Expression exp = MathFunctionDefinitions.fixFunctionSyntax(tokens);
			setVolumeRateExpression(exp);
			continue;
		}
		if (token.equalsIgnoreCase(VCML.UniformRate)){
			Expression exp = MathFunctionDefinitions.fixFunctionSyntax(tokens);
			setUniformRateExpression(exp);
			continue;
		}
		if (token.equalsIgnoreCase(VCML.Exact)){
			exactExp = MathFunctionDefinitions.fixFunctionSyntax(tokens);
			solutionType = EXACT_SOLUTION;
			continue;
		}
		throw new MathFormatException("unexpected identifier "+token);
	}	
		
}

/**
 * Insert the method's description here.
 * Creation date: (7/9/01 2:05:09 PM)
 * @param newVolumeRateExpression cbit.vcell.parser.Expression
 */
public void setUniformRateExpression(Expression newUniformRateExpression) {
	uniformRateExpression = newUniformRateExpression;
}


/**
 * Insert the method's description here.
 * Creation date: (7/9/01 2:05:09 PM)
 * @param newVolumeRateExpression cbit.vcell.parser.Expression
 */
public void setVolumeRateExpression(Expression newVolumeRateExpression) {
	volumeRateExpression = newVolumeRateExpression;
}


@Override
public void checkValid(MathDescription mathDesc, SubDomain subDomain) throws MathException, ExpressionException {	
	checkValid_Volume(mathDesc,getVolumeRateExpression(), (CompartmentSubDomain)subDomain);
	checkValid_Volume(mathDesc,getUniformRateExpression(), (CompartmentSubDomain)subDomain);
	
	checkValid_Volume(mathDesc,getRateExpression(), (CompartmentSubDomain)subDomain);
	checkValid_Volume(mathDesc,getInitialExpression(), (CompartmentSubDomain)subDomain);
	checkValid_Volume(mathDesc,getExactSolution(), (CompartmentSubDomain)subDomain);
	
	// jump condition can have membrane variable in it
	MembraneSubDomain membranes[] = mathDesc.getMembraneSubDomains((CompartmentSubDomain)subDomain);
	if (membranes!=null) {
		for (int i = 0; i < membranes.length; i++){
			JumpCondition jump = membranes[i].getJumpCondition(getVariable());
			if (jump != null) {
				jump.checkValid(mathDesc, membranes[i]);
			}
		}
	}
}
}