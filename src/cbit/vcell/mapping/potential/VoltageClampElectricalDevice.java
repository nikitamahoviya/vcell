package cbit.vcell.mapping.potential;
import cbit.vcell.parser.Expression;
import cbit.vcell.parser.ExpressionException;
import cbit.vcell.parser.SymbolTableEntry;
import cbit.vcell.mapping.ElectricalStimulus;
import cbit.vcell.mapping.ParameterContext.LocalParameter;
/**
 * Insert the type's description here.
 * Creation date: (4/7/2004 11:00:15 AM)
 * @author: Jim Schaff
 */
public class VoltageClampElectricalDevice extends ElectricalDevice {
	private cbit.vcell.mapping.VoltageClampStimulus voltageClampStimulus = null;

/**
 * Insert the method's description here.
 * Creation date: (4/7/2004 11:00:30 AM)
 */
public VoltageClampElectricalDevice(cbit.vcell.mapping.VoltageClampStimulus argVoltageClampStimulus, cbit.vcell.mapping.MathMapping argMathMapping) throws ExpressionException {
	super("device_"+argVoltageClampStimulus.getName(), argMathMapping);
	this.voltageClampStimulus = argVoltageClampStimulus;
	
	ElectricalDevice.ElectricalDeviceParameter parameters[] = new ElectricalDevice.ElectricalDeviceParameter[3];
	
	parameters[0] = new ElectricalDeviceParameter(
							DefaultNames[ROLE_TotalCurrent],
							new Expression(DefaultNames[ROLE_TransmembraneCurrent]),
							ROLE_TotalCurrent,
							cbit.vcell.units.VCUnitDefinition.UNIT_pA);
	
	parameters[1] = new ElectricalDeviceParameter(
							DefaultNames[ROLE_TransmembraneCurrent],
							null,
							ROLE_TransmembraneCurrent,
							cbit.vcell.units.VCUnitDefinition.UNIT_pA);

	LocalParameter voltageParm = voltageClampStimulus.getVoltageParameter();
	parameters[2] = new ElectricalDeviceParameter(
							voltageParm.getName(),
							new Expression(voltageParm.getExpression()),
							ROLE_Voltage,
							voltageParm.getUnitDefinition());
	//
	// add any user-defined parameters
	//
	LocalParameter[] stimulusParameters = voltageClampStimulus.getLocalParameters();
	for (int i = 0;stimulusParameters!=null && i <stimulusParameters.length; i++){
		int role = stimulusParameters[i].getRole();
		if (role==ElectricalStimulus.ROLE_UserDefined){
			ElectricalDeviceParameter newParam = new ElectricalDeviceParameter(stimulusParameters[i].getName(),new Expression(stimulusParameters[i].getExpression()),ROLE_UserDefined,stimulusParameters[i].getUnitDefinition());
			parameters = (ElectricalDeviceParameter[])org.vcell.util.BeanUtils.addElement(parameters,newParam);
		}
	}
	
	setParameters(parameters);
}


/**
 * Insert the method's description here.
 * Creation date: (4/7/2004 11:32:53 AM)
 * @return boolean
 */
public boolean getCalculateVoltage() {
	return true;
}


/**
 * Insert the method's description here.
 * Creation date: (4/7/2004 11:32:53 AM)
 * @return boolean
 */
public boolean getResolved() {
	return false;
}


/**
 * Insert the method's description here.
 * Creation date: (4/7/2004 2:51:28 PM)
 * @return java.lang.String
 */
public SymbolTableEntry getVoltageSymbol() {
	return getParameterFromRole(ROLE_Voltage);
}


/**
 * Insert the method's description here.
 * Creation date: (4/7/2004 11:38:39 AM)
 * @return cbit.vcell.mapping.VoltageClampStimulus
 */
public cbit.vcell.mapping.VoltageClampStimulus getVoltageClampStimulus() {
	return voltageClampStimulus;
}


/**
 * Insert the method's description here.
 * Creation date: (4/7/2004 11:32:53 AM)
 * @return boolean
 */
public boolean hasCapacitance() {
	return false;
}


/**
 * Insert the method's description here.
 * Creation date: (4/7/2004 11:32:53 AM)
 * @return boolean
 */
public boolean isVoltageSource() {
	return true;
}
}