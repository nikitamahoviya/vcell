package cbit.vcell.anonymizer;
import cbit.vcell.server.SessionLog;
/**
 * Insert the type's description here.
 * Creation date: (5/14/2006 11:13:53 PM)
 * @author: Jim Schaff
 */
public class AnonymizerSimulationController extends AnonymizerService implements cbit.vcell.server.SimulationController {
/**
 * AnonymizerSimulationController constructor comment.
 * @exception java.rmi.RemoteException The exception description.
 */
protected AnonymizerSimulationController(AnonymizerVCellConnection arg_anonymizerVCellConnection, SessionLog arg_sessionLog) throws java.rmi.RemoteException {
	super(cbit.vcell.server.PropertyLoader.getIntProperty(cbit.vcell.server.PropertyLoader.rmiPortSimulationController,0), arg_anonymizerVCellConnection, arg_sessionLog);
}


/**
 * Insert the method's description here.
 * Creation date: (5/12/2006 5:54:27 PM)
 * @param function cbit.vcell.math.Function
 * @exception cbit.vcell.server.DataAccessException The exception description.
 * @exception java.rmi.RemoteException The exception description.
 */
private void remoteCall(String methodName, Class[] argClasses, Object[] args) throws java.rmi.RemoteException {
	try {
		remoteCall(anonymizerVCellConnection.getRemoteSimulationController(), methodName, argClasses, args);
	} catch (cbit.vcell.server.DataAccessException ex) {
		// should never happen
		sessionLog.exception(ex);
	}
}


/**
 * This method was created by a SmartGuide.
 * @exception java.rmi.RemoteException The exception description.
 */
public void startSimulation(cbit.vcell.solver.VCSimulationIdentifier vcSimulationIdentifier) throws java.rmi.RemoteException {
	remoteCall("startSimulation", new Class[] {cbit.vcell.solver.VCSimulationIdentifier.class}, new Object[] {vcSimulationIdentifier});
}


/**
 * This method was created by a SmartGuide.
 * @exception java.rmi.RemoteException The exception description.
 */
public void stopSimulation(cbit.vcell.solver.VCSimulationIdentifier vcSimulationIdentifier) throws java.rmi.RemoteException {
	remoteCall("stopSimulation", new Class[] {cbit.vcell.solver.VCSimulationIdentifier.class}, new Object[] {vcSimulationIdentifier});
}
}