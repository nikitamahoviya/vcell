/*
 * Copyright (C) 1999-2011 University of Connecticut Health Center
 *
 * Licensed under the MIT License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/mit-license.php
 */

package cbit.vcell.model;

import java.beans.PropertyVetoException;

import org.vcell.util.Matchable;

import cbit.vcell.parser.Expression;
import cbit.vcell.parser.ExpressionException;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public class GeneralKinetics extends DistributedKinetics {
/**
 * MassActionKinetics constructor comment.
 * @param name java.lang.String
 * @param exp cbit.vcell.parser.Expression
 */
public GeneralKinetics(ReactionStep reactionStep) throws ExpressionException {
	super(KineticsDescription.General.getName(),reactionStep);
	try {
		KineticsParameter rateParm = new KineticsParameter(getDefaultParameterName(ROLE_ReactionRate),new Expression(0.0),ROLE_ReactionRate,null);
		KineticsParameter currentParm = new KineticsParameter(getDefaultParameterName(ROLE_CurrentDensity),new Expression(0.0),ROLE_CurrentDensity,null);
		KineticsParameter netChargeValence = new KineticsParameter(getDefaultParameterName(ROLE_NetChargeValence),new Expression(1.0),ROLE_NetChargeValence,null);
		KineticsParameter carrierChargeValence = new KineticsParameter(getDefaultParameterName(ROLE_NetChargeValence),new Expression(1.0),ROLE_NetChargeValence,null);
			
		if (reactionStep.getStructure() instanceof Membrane){
			if (reactionStep instanceof FluxReaction){
				setKineticsParameters(new KineticsParameter[] { rateParm, currentParm, carrierChargeValence });
			}else{
				setKineticsParameters(new KineticsParameter[] { rateParm, currentParm, netChargeValence });
			}
		}else{
			setKineticsParameters(new KineticsParameter[] { rateParm });
		}
		updateGeneratedExpressions();
		refreshUnits();
	}catch (PropertyVetoException e){
		e.printStackTrace(System.out);
		throw new RuntimeException("unexpected exception: "+e.getMessage());
	}
}
	
/**
 * Checks for internal representation of objects, not keys from database
 * @return boolean
 * @param obj java.lang.Object
 */
public boolean compareEqual(Matchable obj) {
	if (obj == this){
		return true;
	}
	if (!(obj instanceof GeneralKinetics)){
		return false;
	}
	
	GeneralKinetics gck = (GeneralKinetics)obj;

	if (!compareEqual0(gck)){
		return false;
	}
	
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 9:52:55 AM)
 * @return cbit.vcell.model.KineticsDescription
 */
public KineticsDescription getKineticsDescription() {
	return KineticsDescription.General;
}

/**
 * Insert the method's description here.
 * Creation date: (3/31/2004 3:56:05 PM)
 */
protected void refreshUnits() {
	if (bRefreshingUnits){
		return;
	}
	try {
		bRefreshingUnits=true;
		Model model = getReactionStep().getModel();
		if (model != null) {
			ModelUnitSystem modelUnitSystem = model.getUnitSystem();
			Kinetics.KineticsParameter rateParm = getReactionRateParameter();
			if (getReactionStep().getStructure() instanceof Feature){
				if (rateParm != null){
					rateParm.setUnitDefinition(modelUnitSystem.getVolumeReactionRateUnit());
				}
			}else if (getReactionStep().getStructure() instanceof Membrane){
				if (getReactionStep() instanceof FluxReaction){
					if (rateParm != null){
						rateParm.setUnitDefinition(modelUnitSystem.getFluxReactionUnit());
					}
				}else{
					if (rateParm != null){
						rateParm.setUnitDefinition(modelUnitSystem.getMembraneReactionRateUnit());
					}
				}
			}else{
				throw new RuntimeException("unexpected structure");
			}
			Kinetics.KineticsParameter currentDensityParm = getCurrentDensityParameter();
			if (currentDensityParm != null){
				currentDensityParm.setUnitDefinition(modelUnitSystem.getCurrentDensityUnit());
			}
			KineticsParameter chargeValenceParm = getChargeValenceParameter();
			if (chargeValenceParm!=null){
				chargeValenceParm.setUnitDefinition(modelUnitSystem.getInstance_DIMENSIONLESS());
			}
		}
	}finally{
		bRefreshingUnits=false;
	}	
}
/**
 * Insert the method's description here.
 * Creation date: (10/19/2003 12:05:14 AM)
 * @exception cbit.vcell.parser.ExpressionException The exception description.
 */
protected void updateGeneratedExpressions() throws ExpressionException, PropertyVetoException{
	// PRIMARY REACTION RATE
	// user defined, nothing to do
	
	// SECONDARY CURRENT DENSITY
	// update from reaction rate
	updateInwardCurrentDensityFromReactionRate();
}
}
