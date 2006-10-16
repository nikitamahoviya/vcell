package cbit.vcell.model;

import java.beans.PropertyVetoException;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.util.*;
import cbit.util.*;
import javax.swing.*;

import org.vcell.expression.ExpressionException;
import org.vcell.expression.ExpressionFactory;
import org.vcell.expression.IExpression;

import edu.uchc.vcell.expression.internal.*;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public class HMM_REVKinetics extends Kinetics {
/**
 * MassActionKinetics constructor comment.
 * @param name java.lang.String
 * @param exp cbit.vcell.parser.IExpression
 */
public HMM_REVKinetics(ReactionStep reactionStep) throws ExpressionException {
	super(KineticsDescription.HMM_reversible.getName(),reactionStep);
	try {
		KineticsParameter rateParm = new KineticsParameter(getDefaultParameterName(ROLE_Rate),ExpressionFactory.createExpression(0.0),ROLE_Rate,null);
		KineticsParameter currentParm = new KineticsParameter(getDefaultParameterName(ROLE_Current),ExpressionFactory.createExpression(0.0),ROLE_Current,null);
		KineticsParameter kmFwdParm = new KineticsParameter(getDefaultParameterName(ROLE_KmFwd),ExpressionFactory.createExpression(0.0),ROLE_KmFwd,null);
		KineticsParameter vMaxFwdParm = new KineticsParameter(getDefaultParameterName(ROLE_VmaxFwd),ExpressionFactory.createExpression(0.0),ROLE_VmaxFwd,null);
		KineticsParameter kmRevParm = new KineticsParameter(getDefaultParameterName(ROLE_KmRev),ExpressionFactory.createExpression(0.0),ROLE_KmRev,null);
		KineticsParameter vMaxRevParm = new KineticsParameter(getDefaultParameterName(ROLE_VmaxRev),ExpressionFactory.createExpression(0.0),ROLE_VmaxRev,null);

		if (reactionStep.getStructure() instanceof Membrane){
			setKineticsParameters(new KineticsParameter[] { rateParm, currentParm, kmFwdParm, vMaxFwdParm, kmRevParm, vMaxRevParm });
		}else{
			setKineticsParameters(new KineticsParameter[] { rateParm, kmFwdParm, vMaxFwdParm, kmRevParm, vMaxRevParm });
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
public boolean compareEqual(cbit.util.Matchable obj) {
	if (obj == this){
		return true;
	}
	if (!(obj instanceof HMM_REVKinetics)){
		return false;
	}
	
	HMM_REVKinetics hmm = (HMM_REVKinetics)obj;

	if (!compareEqual0(hmm)){
		return false;
	}
	
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (5/12/2004 3:26:54 PM)
 * @return cbit.util.Issue[]
 */
public void gatherIssues(Vector issueList){
	
	super.gatherIssues(issueList);
	
	//
	// check for correct number of reactants and products
	//
	int reactantCount=0;
	int productCount=0;
	ReactionParticipant reactionParticipants[] = getReactionStep().getReactionParticipants();
	for (int i = 0; i < reactionParticipants.length; i++){
		if (reactionParticipants[i] instanceof Reactant){
			reactantCount++;
		}
		if (reactionParticipants[i] instanceof Product){
			productCount++;
		}
	}
	if (reactantCount!=1){
		issueList.add(new Issue(this,ISSUECATEGORY_KineticsApplicability,"HMM Reversible Kinetics must have exactly one reactant",Issue.SEVERITY_ERROR));
	}
	if (productCount!=1){
		issueList.add(new Issue(this,ISSUECATEGORY_KineticsApplicability,"HMM Reversible Kinetics must have exactly one product",Issue.SEVERITY_ERROR));
	}
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 9:52:55 AM)
 * @return cbit.vcell.model.KineticsDescription
 */
public KineticsDescription getKineticsDescription() {
	return KineticsDescription.HMM_reversible;
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 3:48:16 PM)
 * @return cbit.vcell.model.KineticsParameter
 */
public KineticsParameter getKmFwdParameter() {
	return getKineticsParameterFromRole(ROLE_KmFwd);
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 3:48:16 PM)
 * @return cbit.vcell.model.KineticsParameter
 */
public KineticsParameter getKmRevParameter() {
	return getKineticsParameterFromRole(ROLE_KmRev);
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 3:48:16 PM)
 * @return cbit.vcell.model.KineticsParameter
 */
public KineticsParameter getVmaxFwdParameter() {
	return getKineticsParameterFromRole(ROLE_VmaxFwd);
}
/**
 * Insert the method's description here.
 * Creation date: (8/6/2002 3:48:16 PM)
 * @return cbit.vcell.model.KineticsParameter
 */
public KineticsParameter getVmaxRevParameter() {
	return getKineticsParameterFromRole(ROLE_VmaxRev);
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
		
		Reactant R0 = null;
		int reactantCount = 0;
		Product P0 = null;
		int productCount = 0;
		ReactionParticipant reactionParticipants[] = getReactionStep().getReactionParticipants();
		for (int i = 0; i < reactionParticipants.length; i++){
			if (reactionParticipants[i] instanceof Reactant){
				reactantCount++;
				R0 = (Reactant)reactionParticipants[i];
			}
			if (reactionParticipants[i] instanceof Product){
				productCount++;
				P0 = (Product)reactionParticipants[i];
			}
		}
		if (reactantCount!=1){
			System.out.println("HMM_IRRKinetics.refreshUnits(): HMM_IRRKinetics must have exactly one reactant");
			return;
		}
		if (productCount!=1){
			System.out.println("HMM_IRRKinetics.refreshUnits(): HMM_IRRKinetics must have exactly one product");
			return;
		}

		Kinetics.KineticsParameter rateParm = getRateParameter();
		Kinetics.KineticsParameter currentParm = getCurrentParameter();
		Kinetics.KineticsParameter kmFwdParm = getKmFwdParameter();
		Kinetics.KineticsParameter vmaxFwdParm = getVmaxFwdParameter();
		Kinetics.KineticsParameter kmRevParm = getKmRevParameter();
		Kinetics.KineticsParameter vmaxRevParm = getVmaxRevParameter();
		if (getReactionStep().getStructure() instanceof Membrane){
			if (rateParm!=null){
				rateParm.setUnitDefinition(cbit.vcell.units.VCUnitDefinition.UNIT_molecules_per_um2_per_s);
			}
			if (currentParm!=null){
				currentParm.setUnitDefinition(cbit.vcell.units.VCUnitDefinition.UNIT_pA_per_um2);
			}
			if (vmaxFwdParm!=null){
				vmaxFwdParm.setUnitDefinition(cbit.vcell.units.VCUnitDefinition.UNIT_molecules_per_um2_per_s);
			}
			if (vmaxRevParm!=null){
				vmaxRevParm.setUnitDefinition(cbit.vcell.units.VCUnitDefinition.UNIT_molecules_per_um2_per_s);
			}
			if (kmFwdParm!=null){
				kmFwdParm.setUnitDefinition(R0.getSpeciesContext().getUnitDefinition());
			}
			if (kmRevParm!=null){
				kmRevParm.setUnitDefinition(P0.getSpeciesContext().getUnitDefinition());
			}
		}else{
			if (rateParm!=null){
				rateParm.setUnitDefinition(cbit.vcell.units.VCUnitDefinition.UNIT_uM_per_s);
			}
			if (vmaxFwdParm!=null){
				vmaxFwdParm.setUnitDefinition(cbit.vcell.units.VCUnitDefinition.UNIT_uM_per_s);
			}
			if (vmaxRevParm!=null){
				vmaxRevParm.setUnitDefinition(cbit.vcell.units.VCUnitDefinition.UNIT_uM_per_s);
			}
			if (kmFwdParm!=null){
				kmFwdParm.setUnitDefinition(R0.getSpeciesContext().getUnitDefinition());
			}
			if (kmRevParm!=null){
				kmRevParm.setUnitDefinition(P0.getSpeciesContext().getUnitDefinition());
			}
		}
	}finally{
		bRefreshingUnits=false;
	}
}
/**
 * Insert the method's description here.
 * Creation date: (10/19/2003 12:05:14 AM)
 * @exception org.vcell.expression.ExpressionException The exception description.
 */
protected void updateGeneratedExpressions() throws org.vcell.expression.ExpressionException, PropertyVetoException {
	KineticsParameter rateParm = getKineticsParameterFromRole(ROLE_Rate);
	KineticsParameter currentParm = getKineticsParameterFromRole(ROLE_Current);
	if (currentParm==null && rateParm==null){
		return;
	}
	KineticsParameter kmFwd = getKineticsParameterFromRole(ROLE_KmFwd);
	KineticsParameter vMaxFwd = getKineticsParameterFromRole(ROLE_VmaxFwd);
	KineticsParameter kmRev = getKineticsParameterFromRole(ROLE_KmRev);
	KineticsParameter vMaxRev = getKineticsParameterFromRole(ROLE_VmaxRev);
	
	MembraneVoltage V = null;
	if (getReactionStep().getStructure() instanceof Membrane){
		V = ((Membrane)getReactionStep().getStructure()).getMembraneVoltage();
	}
	
	ReactionParticipant reactionParticipants[] = getReactionStep().getReactionParticipants();
	Reactant R0 = null;
	Product P0 = null;
	for (int i = 0; i < reactionParticipants.length; i++){
		if (reactionParticipants[i] instanceof Reactant && R0 == null){
			R0 = (Reactant)reactionParticipants[i];
		}
		if (reactionParticipants[i] instanceof Product && P0 == null){
			P0 = (Product)reactionParticipants[i];
		}
	}
	if (R0==null){
		System.out.println("HMM_REVKinetics.updateGeneratedExpressions(): HMM_REVKinetics must have exactly one reactant");
		return;
		//throw new RuntimeException("HMM_REVKinetics must have exactly one reactant");
	}
	if (P0==null){
		System.out.println("HMM_REVKinetics.updateGeneratedExpressions(): HMM_REVKinetics must have exactly one product");
		return;
		//throw new RuntimeException("HMM_REVKinetics must have exactly one product");
	}
	
	//	new IExpression("(A0*R0/A1 - A2*P0/A3)/(1 + R0/A1 + P0/A3)"),  where Ai = { "VmaxFwd","KmFwd", "VmaxRev", "KmRev" }
	IExpression newRateExp = ExpressionFactory.createExpression("("+vMaxFwd.getName()+"*"+R0.getName()+"/"+kmFwd.getName()+" - "+vMaxRev.getName()+"*"+P0.getName()+"/"+kmRev.getName()+")/"+
											"(1.0 + "+R0.getName()+"/"+kmFwd.getName()+" + "+P0.getName()+"/"+kmRev.getName()+")");
	newRateExp.bindExpression(getReactionStep());
	rateParm.setExpression(newRateExp);

	if (getReactionStep().getPhysicsOptions() == ReactionStep.PHYSICS_MOLECULAR_AND_ELECTRICAL){
		IExpression tempCurrentExpression = null;
		int z = (int)getReactionStep().getChargeCarrierValence().getConstantValue();
		ReservedSymbol F = ReservedSymbol.FARADAY_CONSTANT;
		ReservedSymbol F_nmol = ReservedSymbol.FARADAY_CONSTANT_NMOLE;
		ReservedSymbol N_PMOLE = ReservedSymbol.N_PMOLE;
		if (getReactionStep() instanceof SimpleReaction){
			tempCurrentExpression = ExpressionFactory.mult(ExpressionFactory.createExpression("("+z+"*"+F.getName()+"/"+N_PMOLE.getName()+")"), ExpressionFactory.createExpression(rateParm.getName()));
		}else{
			tempCurrentExpression = ExpressionFactory.mult(ExpressionFactory.createExpression(z+"*"+F_nmol.getName()), ExpressionFactory.createExpression(rateParm.getName()));
		}
		tempCurrentExpression.bindExpression(getReactionStep());
		if (currentParm == null){
			addKineticsParameter(new KineticsParameter(getDefaultParameterName(ROLE_Current),tempCurrentExpression,ROLE_Current,cbit.vcell.units.VCUnitDefinition.UNIT_pA_per_um2));
		}else{
			currentParm.setExpression(tempCurrentExpression);
		}
	}else{
		if (currentParm != null && !currentParm.getExpression().isZero()){
			//removeKineticsParameter(currentParm);
			currentParm.setExpression(ExpressionFactory.createExpression(0.0));
		}
	}
}
}
