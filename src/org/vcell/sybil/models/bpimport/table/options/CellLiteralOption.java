/*
 * Copyright (C) 1999-2011 University of Connecticut Health Center
 *
 * Licensed under the MIT License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/mit-license.php
 */

package org.vcell.sybil.models.bpimport.table.options;

/*   CellLiteralOption  --- by Oliver Ruebenacker, UCHC --- July 2009 to November 2009
 *   An option to choose from in a cell, representing a Literal
 */

import com.hp.hpl.jena.rdf.model.Literal;

public class CellLiteralOption extends CellSelectableOption implements CellOption {

	protected Literal literal;
	protected String label;

	public CellLiteralOption(Literal literal) { 
		this.literal = literal;
		if(literal != null) { label = literal.getLexicalForm(); }
		else { label = "[none]"; }
	}
	
	public Literal node() { return literal; }

	public String label() { return label; }
	@Override
	public String toString() { return label; }
	@Override
	public boolean equals(Object o) {
		if(o instanceof CellLiteralOption) {
			return literal.equals(((CellLiteralOption) o).node());
		}
		return false;
	}
	
	@Override
	public int hashCode() { return literal.hashCode(); }

	
}
