package cbit.vcell.simdata;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import cbit.vcell.simdata.gui.SpatialSelection;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;
import cbit.vcell.solvers.CartesianMesh;
import cbit.vcell.export.server.ExportServiceImpl;
import cbit.vcell.field.FieldDataFileOperationResults;
import cbit.vcell.field.FieldDataFileOperationSpec;
import cbit.vcell.server.ObjectNotFoundException;
import cbit.vcell.server.SessionLog;
import cbit.vcell.server.User;
import cbit.vcell.server.VCDataIdentifier;
import cbit.vcell.math.AnnotatedFunction;
import cbit.vcell.math.CoordinateIndex;
import cbit.vcell.server.PermissionException;
import cbit.vcell.server.DataAccessException;
import cbit.plot.PlotData;
import cbit.rmi.event.ExportEvent;
import cbit.rmi.event.MessageEvent;
import cbit.vcell.solver.DataProcessingOutput;
import cbit.vcell.solver.SimulationInfo;

/**
 * This interface was generated by a SmartGuide.
 * 
 */
public class DataServerImpl {
	private SessionLog log = null;
	private User user = null;
	private DataSetControllerImpl dataSetControllerImpl = null;
	private ExportServiceImpl exportServiceImpl = null;

/**
 * This method was created by a SmartGuide.
 */
public DataServerImpl (SessionLog log, DataSetControllerImpl dsControllerImpl, ExportServiceImpl esl) {
	super();
	this.log = log;
	this.dataSetControllerImpl = dsControllerImpl;
	exportServiceImpl = esl;
}


/**
 * Insert the method's description here.
 * Creation date: (10/11/00 1:11:04 PM)
 * @param function cbit.vcell.math.Function
 * @exception cbit.vcell.server.DataAccessException The exception description.
 * @exception java.rmi.RemoteException The exception description.
 */
public void addFunctions(User user, VCDataIdentifier vcdID, AnnotatedFunction[] functionArr,boolean[] bReplaceArr) throws DataAccessException {
	log.print("DataServerImpl.addFunction()");
	checkWriteAccess(user, vcdID);
	try {
		dataSetControllerImpl.addFunctions(vcdID, functionArr,bReplaceArr);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * Insert the method's description here.
 * Creation date: (8/5/2001 10:29:59 PM)
 * @param simInfo cbit.vcell.solver.SimulationInfo
 */
private void checkReadAccess(User user, VCDataIdentifier vcdID) throws PermissionException {
//	//
//	// throw PermissionException if current user doesn't own Simulation and it isn't public.
//	//
//	if (!simInfo.getVersion().getOwner().compareEqual(getUser()) &&
//		!simInfo.getVersion().getGroupAccess().isMember(getUser())){
//		throw new PermissionException("PermissionException: simulation="+simInfo.toString());
//	}

	//
	// DON'T PROHIBIT ACCESS AT THIS LEVEL FOR READ-ONLY 
	// (access is by reachability, the Simulation.Version doesn't have enough information).
	//
}


/**
 * Insert the method's description here.
 * Creation date: (8/5/2001 10:29:59 PM)
 * @param simInfo cbit.vcell.solver.SimulationInfo
 */
private void checkWriteAccess(User user, VCDataIdentifier vcdID) throws PermissionException {
	//
	// throw PermissionException if current user doesn't own Simulation.
	//
	if (!vcdID.getOwner().compareEqual(user)){
		throw new PermissionException("PermissionException: VCData="+vcdID.getID());
	}
}


public FieldDataFileOperationResults fieldDataFileOperation(User user,FieldDataFileOperationSpec fieldDataOpearationSpec) throws DataAccessException {
	//checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.fieldDataFileOperation(fieldDataOpearationSpec);
	}catch (DataAccessException e){
		log.exception(e);
		throw e;
	}catch (Exception e){
		log.exception(e);
		throw new DataAccessException("Error FieldDataFileOperation",e);
	}
}



/**
 * This method was created by a SmartGuide.
 * @return java.lang.String[]
 */
public DataIdentifier[] getDataIdentifiers(User user, VCDataIdentifier vcdID) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getDataIdentifiers(vcdID);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created by a SmartGuide.
 * @return double[]
 */
public double[] getDataSetTimes(User user, VCDataIdentifier vcdID) throws DataAccessException {
	log.print("DataServerImpl.getDataSetTimes()");
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getDataSetTimes(vcdID);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * Insert the method's description here.
 * Creation date: (10/11/00 1:11:04 PM)
 * @param function cbit.vcell.math.Function
 * @exception cbit.vcell.server.DataAccessException The exception description.
 * @exception java.rmi.RemoteException The exception description.
 */
public AnnotatedFunction[] getFunctions(User user, VCDataIdentifier vcdID) throws DataAccessException {
	log.print("DataServerImpl.getFunctions()");
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getFunctions(vcdID);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created in VisualAge.
 * @return boolean
 */
public boolean getIsODEData(User user, VCDataIdentifier vcdID) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getIsODEData(vcdID);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.plot.PlotData
 * @param varName java.lang.String
 * @param begin cbit.vcell.math.CoordinateIndex
 * @param end cbit.vcell.math.CoordinateIndex
 */
public PlotData getLineScan(User user, VCDataIdentifier vcdID, String varName, double time, CoordinateIndex begin, CoordinateIndex end) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getLineScan(vcdID,varName,time,begin,end);
	} catch (DataAccessException e) {
		log.exception(e);
		throw e;
	} catch (Throwable e) {
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.plot.PlotData
 * @param variable java.lang.String
 * @param time double
 * @param spatialSelection cbit.vcell.simdata.gui.SpatialSelection
 * @exception java.rmi.RemoteException The exception description.
 */
public cbit.plot.PlotData getLineScan(User user, VCDataIdentifier vcdID, java.lang.String varName, double time, SpatialSelection spatialSelection) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getLineScan(vcdID,varName,time,spatialSelection);
	} catch (DataAccessException e) {
		log.exception(e);
		throw e;
	} catch (Throwable e) {
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created in VisualAge.
 * @return CartesianMesh
 */
public CartesianMesh getMesh(User user, VCDataIdentifier vcdID) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getMesh(vcdID);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * Insert the method's description here.
 * Creation date: (1/14/00 11:20:51 AM)
 * @return cbit.vcell.export.data.ODESimData
 * @exception cbit.vcell.server.DataAccessException The exception description.
 * @exception java.rmi.RemoteException The exception description.
 */
public cbit.vcell.solver.ode.ODESimData getODEData(User user, VCDataIdentifier vcdID) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getODEDataBlock(vcdID).getODESimData();
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.DataSet
 * @param time double
 */
public ParticleDataBlock getParticleDataBlock(User user, VCDataIdentifier vcdID, double time) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getParticleDataBlock(vcdID, time);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.DataSet
 * @param time double
 */
public DataProcessingOutput getDataProcessingOutput(User user, VCDataIdentifier vcdID) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getDataProcessingOutput(vcdID);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created in VisualAge.
 * @return boolean
 */
public boolean getParticleDataExists(User user, VCDataIdentifier vcdID) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getParticleDataExists(vcdID);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.DataSet
 * @param time double
 */
public SimDataBlock getSimDataBlock(User user, VCDataIdentifier vcdID, String var, double time) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getSimDataBlock(vcdID,var,time);
	}catch (DataAccessException e){
		log.exception(e);
		throw (DataAccessException)e;
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created by a SmartGuide.
 * @return double[]
 * @param varName java.lang.String
 * @param x int
 * @param y int
 * @param z int
 */
public cbit.util.TimeSeriesJobResults getTimeSeriesValues(User user, VCDataIdentifier vcdID, cbit.util.TimeSeriesJobSpec timeSeriesJobSpec) throws DataAccessException {
	checkReadAccess(user, vcdID);
	try {
		return dataSetControllerImpl.getTimeSeriesValues(vcdID,timeSeriesJobSpec);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * Insert the method's description here.
 * Creation date: (3/30/2001 11:11:52 AM)
 * @param exportSpecs cbit.vcell.export.server.ExportSpecs
 * @exception cbit.vcell.server.DataAccessException The exception description.
 */
public ExportEvent makeRemoteFile(User user, cbit.vcell.export.server.ExportSpecs exportSpecs) throws cbit.vcell.server.DataAccessException {
	log.print("DataServerImpl.makeRemoteFile(" + exportSpecs.getVCDataIdentifier() + ")");
	try {
		ExportEvent exportEvent = exportServiceImpl.makeRemoteFile(user, this, exportSpecs);
		return exportEvent;
	} catch (Throwable e) {
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * Insert the method's description here.
 * Creation date: (10/11/00 1:11:04 PM)
 * @param function cbit.vcell.math.Function
 * @exception cbit.vcell.server.DataAccessException The exception description.
 * @exception java.rmi.RemoteException The exception description.
 */
public void removeFunction(User user, VCDataIdentifier vcdID, AnnotatedFunction function) throws DataAccessException {
	log.print("DataServerImpl.removeFunction("+function.getName()+"="+function.getExpression().toString()+")");
	checkWriteAccess(user, vcdID);
	try {
		dataSetControllerImpl.removeFunction(vcdID,function);
	}catch (Throwable e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}
}