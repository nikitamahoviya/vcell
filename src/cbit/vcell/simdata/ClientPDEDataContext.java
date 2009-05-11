package cbit.vcell.simdata;
import org.vcell.util.DataAccessException;
import org.vcell.util.VCDataIdentifier;

import cbit.vcell.solver.VCSimulationDataIdentifier;
import cbit.vcell.desktop.controls.DataListener;
import cbit.vcell.desktop.controls.DataManager;

/**
 * Insert the type's description here.
 * Creation date: (3/19/2004 10:42:31 AM)
 * @author: Fei Gao
 */
public abstract class ClientPDEDataContext extends PDEDataContext implements DataListener {
		//
	private DataManager dataManager = null;

/**
 * Insert the method's description here.
 * Creation date: (5/22/2001 3:18:21 PM)
 * @param simManager cbit.vcell.desktop.controls.SimulationManager
 */
protected ClientPDEDataContext(DataManager argDataManager) {
	super();
	if (argDataManager != null) {
		this.dataManager = argDataManager;
		initialize();
	} else {
		throw new RuntimeException("Data Manager can not be null in ClientPDEDataContext");
	}
}


/**
 * adds a named <code>Function</code> to the list of variables that are availlable for this Simulation.
 *
 * @param function named expression that is to be bound to dataset and whose name is added to variable list.
 *
 * @throws org.vcell.util.DataAccessException if Function cannot be bound to this dataset or SimulationInfo not found.
 */
public void addFunctions(cbit.vcell.math.AnnotatedFunction[] functionArr,boolean[] bReplaceArr) throws org.vcell.util.DataAccessException {
	dataManager.addFunctions(functionArr,bReplaceArr);
	firePropertyChange(PROP_CHANGE_FUNC_ADDED, null, functionArr);
}


/**
 * Insert the method's description here.
 * Creation date: (5/22/2001 3:19:48 PM)
 * @return cbit.vcell.desktop.controls.SimulationManager
 */
public DataManager getDataManager() {
	return dataManager;
}


/**
 * gets list of named Functions defined for the resultSet for this Simulation.
 *
 * @returns array of functions, or null if no functions.
 *
 * @throws org.vcell.util.DataAccessException if SimulationInfo not found.
 *
 * @see Function
 */
public cbit.vcell.math.AnnotatedFunction[] getFunctions() throws org.vcell.util.DataAccessException {
	return dataManager.getFunctions();
}


/**
 * tests if resultSet contains ODE data for the specified simulation.
 *
 * @returns <i>true</i> if results are of type ODE, <i>false</i> otherwise.
 *
 * @throws org.vcell.util.DataAccessException if SimulationInfo not found.
 *
 * @see Function
 */
public boolean getIsODEData() throws org.vcell.util.DataAccessException {
	return dataManager.getIsODEData();
}


/**
 * retrieves a line scan (data sampled along a curve in space) for the specified simulation.
 *
 * @param variable name of variable to be sampled
 * @param time simulation time which is to be sampled.
 * @param spatialSelection spatial curve.
 *
 * @returns annotated array of 'concentration vs. distance' in a plot ready format.
 *
 * @throws org.vcell.util.DataAccessException if SimulationInfo not found.
 *
 * @see PlotData
 */
public cbit.plot.PlotData getLineScan(java.lang.String variable, double time, cbit.vcell.simdata.gui.SpatialSelection spatialSelection) throws org.vcell.util.DataAccessException {
	return dataManager.getLineScan(variable, time, spatialSelection);
}


/**
 * Insert the method's description here.
 * Creation date: (3/19/2004 11:27:04 AM)
 * @return cbit.vcell.simdata.SimDataBlock
 * @param varName java.lang.String
 * @param time double
 */
protected ParticleDataBlock getParticleDataBlock(double time) throws DataAccessException {
	return getDataManager().getParticleDataBlock(time);
}


/**
 * Insert the method's description here.
 * Creation date: (3/19/2004 11:27:04 AM)
 * @return cbit.vcell.simdata.SimDataBlock
 * @param varName java.lang.String
 * @param time double
 */
protected SimDataBlock getSimDataBlock(java.lang.String varName, double time) throws org.vcell.util.DataAccessException {
	return getDataManager().getSimDataBlock(varName, time);
}


/**
 * retrieves a time series (single point as a function of time) of a specified spatial data set.
 *
 * @param variable name of variable to be sampled
 * @param index identifies index into data array.
 *
 * @returns annotated array of 'concentration vs. time' in a plot ready format.
 *
 * @throws org.vcell.util.DataAccessException if SimulationInfo not found.
 *
 * @see CartesianMesh for transformation between indices and coordinates.
 */
public org.vcell.util.TimeSeriesJobResults getTimeSeriesValues(org.vcell.util.TimeSeriesJobSpec timeSeriesJobSpec) throws org.vcell.util.DataAccessException {
	return dataManager.getTimeSeriesValues(timeSeriesJobSpec);
}


/**
 * Gets the simulationInfo property (cbit.vcell.solver.SimulationInfo) value.
 * @return The simulationInfo property value.
 */
public org.vcell.util.VCDataIdentifier getVCDataIdentifier() {
	return dataManager.getVCDataIdentifier();
}


/**
 * Insert the method's description here.
 * Creation date: (10/3/00 5:16:19 PM)
 */
private void initialize() {
	try {
		setParticleData(getDataManager().getParticleDataExists());
		setCartesianMesh(getDataManager().getMesh());
		setTimePoints(getDataManager().getDataSetTimes());
		setDataIdentifiers(getDataManager().getDataIdentifiers());
		double tp = getTimePoint();
		double[] timePoints = getTimePoints();
		if (timePoints != null && timePoints.length >0) {
			tp = timePoints[0];
		}
		String variable = getVariableName();
		DataIdentifier[] dataIdentifiers = getDataIdentifiers();
		if (dataIdentifiers != null && dataIdentifiers.length > 0) {
			variable = getVariableNames()[0];
		}
		setVariableAndTime(variable, tp);
	} catch (DataAccessException exc) {
		exc.printStackTrace(System.out);
		throw new RuntimeException(exc.getMessage());
	}
}


/**
 * This method was created in VisualAge.
 *
 * @param exportSpec cbit.vcell.export.server.ExportSpecs
 */
public abstract void makeRemoteFile(cbit.vcell.export.server.ExportSpecs exportSpecs) throws org.vcell.util.DataAccessException;


/**
 * 
 * @param event cbit.vcell.desktop.controls.SimulationEvent
 */
public void newData(cbit.vcell.desktop.controls.DataEvent event) {
	try {
		setTimePoints(getDataManager().getDataSetTimes());
		//
		//Added for cases where time was set and server couldn't deliver data
		//if after referesh the time is set again, the property won't propagate
		//refreshData();
	} catch (DataAccessException exc) {
		exc.printStackTrace(System.out);
	}
}


/**
 * Insert the method's description here.
 * Creation date: (10/3/00 5:03:43 PM)
 */
public abstract void refreshIdentifiers();


/**
 * removes the specified <i>function</i> from this Simulation.
 *
 * @param function function to be removed.
 *
 * @throws org.vcell.util.DataAccessException if SimulationInfo not found.
 * @throws org.vcell.util.PermissionException if not the owner of this dataset.
 */
public void removeFunction(cbit.vcell.math.AnnotatedFunction function) throws org.vcell.util.DataAccessException, org.vcell.util.PermissionException {
	dataManager.removeFunction(function);
	firePropertyChange(PROP_CHANGE_FUNC_REMOVED, function, null);
}


/**
 * Insert the method's description here.
 * Creation date: (10/19/2005 12:21:50 PM)
 * @param newDataManager cbit.vcell.desktop.controls.DataManager
 */
public void setDataManager(cbit.vcell.desktop.controls.DataManager newDataManager) throws DataAccessException{
	VCDataIdentifier oldid = dataManager.getVCDataIdentifier();
	VCDataIdentifier newid = newDataManager.getVCDataIdentifier();
	if (oldid instanceof VCSimulationDataIdentifier &&
		newid instanceof VCSimulationDataIdentifier &&
		((VCSimulationDataIdentifier)oldid).getVcSimID().equals(((VCSimulationDataIdentifier)newid).getVcSimID())
	) {	
		dataManager = newDataManager;
		setTimePoints(getDataManager().getDataSetTimes());
		externalRefresh();
	} else {
		throw new RuntimeException("DataManager change not allowed: oldID = "+oldid+" newID = "+newid);
	}
}
}