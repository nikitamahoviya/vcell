/*
 * Copyright (C) 1999-2011 University of Connecticut Health Center
 *
 * Licensed under the MIT License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/mit-license.php
 */

package cbit.vcell.parser;

/* JJT: 0.2.2 */

import net.sourceforge.interval.ia_math.RealInterval;

import org.vcell.util.TokenMangler;

public class ASTLiteralNode extends SimpleNode {

  String name = null;

ASTLiteralNode() {
	super(ExpressionParserTreeConstants.JJTLITERALNODE);
  }    
ASTLiteralNode(int id) {
	super(id);
if (id != ExpressionParserTreeConstants.JJTLITERALNODE){ System.out.println("ASTLiteralNode(), id = "+id); }

  }    
/**
 * This method was created by a SmartGuide.
 */
ASTLiteralNode ( ASTLiteralNode node ) {
	super(node.id);
	this.name = node.name;
}
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Node
 * @exception java.lang.Exception The exception description.
 */
public Node copyTree() {
	ASTLiteralNode node = new ASTLiteralNode(this);
	return node;	
}
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Node
 * @exception java.lang.Exception The exception description.
 */
public Node copyTreeBinary() {
	ASTLiteralNode node = new ASTLiteralNode(this);
	return node;	
}
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.parser.Node
 * @param variable java.lang.String
 * @exception java.lang.Exception The exception description.
 */
public Node differentiate(String variable) throws ExpressionException {
	throw new ExpressionException("Cannot differentiate literal identifier "+infixString(LANGUAGE_DEFAULT));
}
/**
 * This method was created by a SmartGuide.
 * @return boolean
 * @param node cbit.vcell.parser.Node
 * @exception java.lang.Exception The exception description.
 */
public boolean equals(Node node) throws ExpressionException {
	//
	// check to see if the types and children are the same
	//
	if (!super.equals(node)){
		return false;
	}
	
	//
	// check this node for same state (identifier)
	//	
	ASTLiteralNode idNode = (ASTLiteralNode)node;
	if (!idNode.name.equals(name)){
		return false;
	}

	return true;
}

public double evaluateConstant() throws ExpressionException {
	throw new ExpressionException("trying to evaluate literal identifier '"+infixString(LANGUAGE_DEFAULT)+"'");
}        

public RealInterval evaluateInterval(RealInterval intervals[]) throws ExpressionException {
	throw new ExpressionBindingException("cannote evaluate literal identifier " + name);
}

public double evaluateVector(double values[]) throws ExpressionException {
	throw new ExpressionBindingException("cannote evaluate literal identifier " + name);
}

public Node flatten() throws ExpressionException {
	return copyTree();
}

public String infixString(int lang) {
	String idName = name;
	if (lang == LANGUAGE_DEFAULT) {
		return idName;
	} else if (lang == LANGUAGE_C){
		return idName;
	} else if (lang == LANGUAGE_MATLAB){	
		return TokenMangler.getEscapedTokenMatlab(idName);
	}else if (lang == LANGUAGE_JSCL) {
		return TokenMangler.getEscapedTokenJSCL(idName);
	}else if (lang == LANGUAGE_ECLiPSe) {
		return TokenMangler.getEscapedTokenECLiPSe(idName);
	}else{
		throw new RuntimeException("Lanaguage '"+lang+" not supported");
	}
}

public boolean narrow(RealInterval intervals[]) {
	return true;
}

public String toString() {
	return "LiteralNode (" + name + ")";
}

}
