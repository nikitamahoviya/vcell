package cbit.vcell.math;
import cbit.util.CommentStringTokenizer;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import cbit.vcell.parser.*;
import java.util.*;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public class PdeEquation extends Equation {
	private Expression diffusionExp = null;
//	private boolean bFixedDiffusion = false;
	
	private Expression boundaryXm = null;
	private Expression boundaryXp = null;
	private Expression boundaryYm = null;
	private Expression boundaryYp = null;
	private Expression boundaryZm = null;
	private Expression boundaryZp = null;
	private Expression velocityX = null;
	private Expression velocityY = null;
	private Expression velocityZ = null;

/**
 * This method was created by a SmartGuide.
 * @param volVar cbit.vcell.math.VolVariable
 */
public PdeEquation (MemVariable memVar) {
	super(memVar);
	
}


/**
 * PdeEquation constructor comment.
 * @param compartmentSubDomain cbit.vcell.math.CompartmentSubDomain
 * @param var cbit.vcell.math.Variable
 * @param initialExp cbit.vcell.parser.Expression
 * @param rateExp cbit.vcell.parser.Expression
 * @param diffusionRate cbit.vcell.parser.Expression
 */
public PdeEquation(Variable var, Expression initialExp,
					Expression rateExp, Expression diffusionRate) {
	super(var, initialExp, rateExp);
	diffusionExp = diffusionRate;
}


/**
 * This method was created by a SmartGuide.
 * @param volVar cbit.vcell.math.VolVariable
 */
public PdeEquation (VolVariable volVar) {
	super(volVar);
	
}


/**
 * Insert the method's description here.
 * Creation date: (9/4/2003 12:32:19 PM)
 * @return boolean
 * @param object cbit.util.Matchable
 */
public boolean compareEqual(cbit.util.Matchable object) {
	PdeEquation equ = null;
	if (!(object instanceof PdeEquation)){
		return false;
	}else{
		equ = (PdeEquation)object;
	}
	if (!compareEqual0(equ)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(diffusionExp,equ.diffusionExp)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(boundaryXm,equ.boundaryXm)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(boundaryXp,equ.boundaryXp)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(boundaryYm,equ.boundaryYm)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(boundaryYp,equ.boundaryYp)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(boundaryZm,equ.boundaryZm)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(boundaryZp,equ.boundaryZp)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(velocityX,equ.velocityX)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(velocityY,equ.velocityY)){
		return false;
	}
	if (!cbit.util.Compare.isEqualOrNull(velocityZ,equ.velocityZ)){
		return false;
	}
	return true;
}


/**
 * Insert the method's description here.
 * Creation date: (10/10/2002 10:41:10 AM)
 * @param sim cbit.vcell.solver.Simulation
 */
void flatten(SymbolTable symbolTable, boolean bRoundCoefficients) throws cbit.vcell.parser.ExpressionException, MathException {
	super.flatten0(symbolTable,bRoundCoefficients);
	
	diffusionExp = getFlattenedExpression(symbolTable,diffusionExp,bRoundCoefficients);
	velocityX = getFlattenedExpression(symbolTable,velocityX,bRoundCoefficients);
	velocityY = getFlattenedExpression(symbolTable,velocityX,bRoundCoefficients);
	velocityZ = getFlattenedExpression(symbolTable,velocityX,bRoundCoefficients);
	boundaryXm = getFlattenedExpression(symbolTable,boundaryXm,bRoundCoefficients);
	boundaryXp = getFlattenedExpression(symbolTable,boundaryXp,bRoundCoefficients);
	boundaryYm = getFlattenedExpression(symbolTable,boundaryYm,bRoundCoefficients);
	boundaryYp = getFlattenedExpression(symbolTable,boundaryYp,bRoundCoefficients);
	boundaryZm = getFlattenedExpression(symbolTable,boundaryZm,bRoundCoefficients);
	boundaryZp = getFlattenedExpression(symbolTable,boundaryZp,bRoundCoefficients);
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
public Expression getBoundaryXm() {
	return boundaryXm;
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
public Expression getBoundaryXp() {
	return boundaryXp;
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
public Expression getBoundaryYm() {
	return boundaryYm;
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
public Expression getBoundaryYp() {
	return boundaryYp;
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
public Expression getBoundaryZm() {
	return boundaryZm;
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
public Expression getBoundaryZp() {
	return boundaryZp;
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
public Expression getDiffusionExpression() {
	return diffusionExp;
}


/**
 * This method was created by a SmartGuide.
 * @return java.util.Vector
 */
protected Vector getExpressions(MathDescription mathDesc) {
	Vector list = new Vector();
	
	if (getBoundaryXm()!=null)		list.addElement(getBoundaryXm());
	if (getBoundaryXp()!=null)		list.addElement(getBoundaryXp());
	if (getBoundaryYm()!=null)		list.addElement(getBoundaryYm());
	if (getBoundaryYp()!=null)		list.addElement(getBoundaryYp());
	if (getBoundaryZm()!=null)		list.addElement(getBoundaryZm());
	if (getBoundaryZp()!=null)		list.addElement(getBoundaryZp());

	if (getVelocityX()!=null)		list.addElement(getVelocityX());
	if (getVelocityY()!=null)		list.addElement(getVelocityY());
	if (getVelocityZ()!=null)		list.addElement(getVelocityZ());
	
	if (getRateExpression()!=null)		list.addElement(getRateExpression());
	if (getInitialExpression()!=null)	list.addElement(getInitialExpression());
	if (getExactSolution()!=null)		list.addElement(getExactSolution());

	list.addElement(diffusionExp);

	//
	// get Parent Subdomain
	//
	SubDomain parentSubDomain = null;
	Enumeration enum1 = mathDesc.getSubDomains();
	while (enum1.hasMoreElements()){
		SubDomain subDomain = (SubDomain)enum1.nextElement();
		if (subDomain.getEquation(getVariable()) == this){
			parentSubDomain = subDomain;
		}
	}

	if (getVariable() instanceof VolVariable){
		try {
			MembraneSubDomain membranes[] = mathDesc.getMembraneSubDomains((CompartmentSubDomain)parentSubDomain);
			for (int i = 0; membranes!=null && i < membranes.length; i++){
				JumpCondition jump = membranes[i].getJumpCondition((VolVariable)getVariable());
				if (membranes[i].getInsideCompartment()==parentSubDomain){
					list.addElement(jump.getInFluxExpression());
				}else{
					list.addElement(jump.getOutFluxExpression());
				}
			}
		}catch (Exception e){
			e.printStackTrace(System.out);
		}
	}

	return list;
}


/**
 * This method was created by a SmartGuide.
 * @return java.util.Enumeration
 */
public Enumeration getTotalExpressions() throws ExpressionException {
	Vector vector = new Vector();
	vector.addElement(getTotalRateExpression());
	vector.addElement(getTotalInitialExpression());
	Expression solutionExp = getTotalSolutionExpression();
	if (solutionExp!=null){
		vector.addElement(solutionExp);
	}	
	return vector.elements();
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Expression
 */
private Expression getTotalRateExpression() throws ExpressionException {
	Expression lvalueExp = Expression.derivative("t",new Expression(getVariable().getName()+";"));
//	Expression lvalueExp = new Expression("d/dt*"+getVariable().getName()+";");
	Expression laplacianExp = Expression.laplacian(new Expression(getVariable().getName()+";"));
	Expression diffExp = Expression.mult(new Expression(getDiffusionExpression()),laplacianExp);
	Expression rvalueExp = Expression.add(new Expression(diffExp),new Expression(getRateExpression()));
	Expression totalExp = Expression.assign(lvalueExp,rvalueExp);
	totalExp.bindExpression(null);
	totalExp.flatten();
	return totalExp;
}


/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String getVCML() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("\t"+VCML.PdeEquation+" "+getVariable().getName()+" {\n");
	if (boundaryXm != null){
		buffer.append("\t\t"+VCML.BoundaryXm+" "+boundaryXm.infix()+";\n");
	}	
	if (boundaryXp != null){
		buffer.append("\t\t"+VCML.BoundaryXp+" "+boundaryXp.infix()+";\n");
	}	
	if (boundaryYm != null){
		buffer.append("\t\t"+VCML.BoundaryYm+" "+boundaryYm.infix()+";\n");
	}	
	if (boundaryYp != null){
		buffer.append("\t\t"+VCML.BoundaryYp+" "+boundaryYp.infix()+";\n");
	}	
	if (boundaryZm != null){
		buffer.append("\t\t"+VCML.BoundaryZm+" "+boundaryZm.infix()+";\n");
	}	
	if (boundaryZp != null){
		buffer.append("\t\t"+VCML.BoundaryZp+" "+boundaryZp.infix()+";\n");
	}	
	if (velocityX != null){
		buffer.append("\t\t"+VCML.VelocityX+" "+velocityX.infix()+";\n");
	}	
	if (velocityY != null){
		buffer.append("\t\t"+VCML.VelocityY+" "+velocityY.infix()+";\n");
	}	
	if (velocityZ != null){
		buffer.append("\t\t"+VCML.VelocityZ+" "+velocityZ.infix()+";\n");
	}	
	if (getRateExpression() != null){
		buffer.append("\t\t"+VCML.Rate+"\t "+getRateExpression().infix()+";\n");
	}else{
		buffer.append("\t\t"+VCML.Rate+"\t "+"0.0;\n");
	}
	if (diffusionExp != null){
		buffer.append("\t\t"+VCML.Diffusion+"\t "+diffusionExp.infix()+";\n");
	}else{
		buffer.append("\t\t"+VCML.Diffusion+"\t "+"0.0;\n");
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
			buffer.append("\t\t"+VCML.Exact+"\t "+exactExp.infix()+";\n");
			break;
		}
	}				
		
	buffer.append("\t}\n");
	return buffer.toString();		
}


/**
 * Insert the method's description here.
 * Creation date: (5/17/2004 11:18:22 AM)
 * @return cbit.vcell.parser.Expression
 */
public cbit.vcell.parser.Expression getVelocityX() {
	return velocityX;
}


/**
 * Insert the method's description here.
 * Creation date: (5/17/2004 11:18:22 AM)
 * @return cbit.vcell.parser.Expression
 */
public cbit.vcell.parser.Expression getVelocityY() {
	return velocityY;
}


/**
 * Insert the method's description here.
 * Creation date: (5/17/2004 11:18:22 AM)
 * @return cbit.vcell.parser.Expression
 */
public cbit.vcell.parser.Expression getVelocityZ() {
	return velocityZ;
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
			initialExp = new Expression(tokens);
			continue;
		}
		if (token.equalsIgnoreCase(VCML.Diffusion)){
			diffusionExp = new Expression(tokens);
			continue;
		}
		if (token.equalsIgnoreCase(VCML.Rate)){
			Expression exp = new Expression(tokens);
			setRateExpression(exp);
			continue;
		}
		if (token.equalsIgnoreCase(VCML.Exact)){
			exactExp = new Expression(tokens);
			solutionType = EXACT_SOLUTION;
			continue;
		}
		if (token.equalsIgnoreCase(VCML.BoundaryXm)){
			boundaryXm = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.BoundaryXp)){
			boundaryXp = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.BoundaryYm)){
			boundaryYm = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.BoundaryYp)){
			boundaryYp = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.BoundaryZm)){
			boundaryZm = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.BoundaryZp)){
			boundaryZp = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.VelocityX)){
			velocityX = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.VelocityY)){
			velocityY = new Expression(tokens);
			continue;
		}			
		if (token.equalsIgnoreCase(VCML.VelocityZ)){
			velocityZ = new Expression(tokens);
			continue;
		}			
		throw new MathFormatException("unexpected identifier "+token);
	}	
		
}


/**
 * Insert the method's description here.
 * Creation date: (5/24/00 2:58:12 PM)
 */
public void setBoundaryXm(Expression exp) {
	boundaryXm = exp;
}


/**
 * Insert the method's description here.
 * Creation date: (5/24/00 2:58:12 PM)
 */
public void setBoundaryXp(Expression exp) {
	boundaryXp = exp;
}


/**
 * Insert the method's description here.
 * Creation date: (5/24/00 2:58:12 PM)
 */
public void setBoundaryYm(Expression exp) {
	boundaryYm = exp;
}


/**
 * Insert the method's description here.
 * Creation date: (5/24/00 2:58:12 PM)
 */
public void setBoundaryYp(Expression exp) {
	boundaryYp = exp;
}


/**
 * Insert the method's description here.
 * Creation date: (5/24/00 2:58:12 PM)
 */
public void setBoundaryZm(Expression exp) {
	boundaryZm = exp;
}


/**
 * Insert the method's description here.
 * Creation date: (5/24/00 2:58:12 PM)
 */
public void setBoundaryZp(Expression exp) {
	boundaryZp = exp;
}


/**
 * Insert the method's description here.
 * Creation date: (7/6/00 9:32:03 AM)
 * @param diffusionExpression cbit.vcell.parser.Expression
 */
public void setDiffusionExpression(Expression diffusionExpression) {
	this.diffusionExp = diffusionExpression;
}


/**
 * Insert the method's description here.
 * Creation date: (5/17/2004 11:18:22 AM)
 * @param newVelocityX cbit.vcell.parser.Expression
 */
public void setVelocityX(cbit.vcell.parser.Expression newVelocityX) {
	if (!(getVariable() instanceof VolVariable)){
		throw new RuntimeException("only Volume Variables can have advection term in PdeEquation");
	}
	velocityX = newVelocityX;
}


/**
 * Insert the method's description here.
 * Creation date: (5/17/2004 11:18:22 AM)
 * @param newVelocityY cbit.vcell.parser.Expression
 */
public void setVelocityY(cbit.vcell.parser.Expression newVelocityY) {
	if (!(getVariable() instanceof VolVariable)){
		throw new RuntimeException("only Volume Variables can have advection term in PdeEquation");
	}
	velocityY = newVelocityY;
}


/**
 * Insert the method's description here.
 * Creation date: (5/17/2004 11:18:22 AM)
 * @param newVelocityZ cbit.vcell.parser.Expression
 */
public void setVelocityZ(cbit.vcell.parser.Expression newVelocityZ) {
	if (!(getVariable() instanceof VolVariable)){
		throw new RuntimeException("only Volume Variables can have advection term in PdeEquation");
	}
	velocityZ = newVelocityZ;
}
}