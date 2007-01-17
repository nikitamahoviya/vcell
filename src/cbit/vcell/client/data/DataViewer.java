package cbit.vcell.client.data;
import javax.swing.*;
import cbit.rmi.event.*;
/**
 * Insert the type's description here.
 * Creation date: (6/14/2004 9:48:35 PM)
 * @author: Ion Moraru
 */
public abstract class DataViewer extends JPanel implements ExportListener,cbit.rmi.event.DataJobListener {
	private cbit.vcell.client.DataViewerManager fieldDataViewerManager = null;
	private SimulationModelInfo fieldSimulationModelInfo = null;

/**
 * Insert the method's description here.
 * Creation date: (3/31/2006 8:22:43 AM)
 */
public void dataJobMessage(DataJobEvent dje) {

	//Override this method if you are interested in receiveing events generated by
	//the dataserver.  (e.g. statistics job progress, timeseries job progress etc...)

	System.out.println("DataJobEvent jobID="+dje.getJobID()+" eventType="+dje.getEventTypeID()+" jobHost="+dje.getHostName());
}


/**
 * Insert the method's description here.
 * Creation date: (6/15/2004 1:55:39 AM)
 * @param event cbit.rmi.event.ExportEvent
 */
public void exportMessage(cbit.rmi.event.ExportEvent event) {
	getExportMonitorPanel().addExportEvent(event, event.getVCSimulationIdentifier().getID());
}


/**
 * Gets the dataViewerManager property (cbit.vcell.client.DataViewerManager) value.
 * @return The dataViewerManager property value.
 * @see #setDataViewerManager
 */
public cbit.vcell.client.DataViewerManager getDataViewerManager() {
	return fieldDataViewerManager;
}


/**
 * Method generated to support the promotion of the exportMonitorPanel attribute.
 * @return cbit.vcell.export.ExportMonitorPanel
 */
public abstract cbit.vcell.export.ExportMonitorPanel getExportMonitorPanel();


/**
 * Gets the simulationModelInfo property (cbit.vcell.client.data.SimulationModelInfo) value.
 * @return The simulationModelInfo property value.
 * @see #setSimulationModelInfo
 */
public SimulationModelInfo getSimulationModelInfo() {
	return fieldSimulationModelInfo;
}


/**
 * Sets the dataViewerManager property (cbit.vcell.client.DataViewerManager) value.
 * @param dataViewerManager The new value for the property.
 * @exception java.beans.PropertyVetoException The exception description.
 * @see #getDataViewerManager
 */
public void setDataViewerManager(cbit.vcell.client.DataViewerManager dataViewerManager) throws java.beans.PropertyVetoException {
	cbit.vcell.client.DataViewerManager oldValue = fieldDataViewerManager;
	fieldDataViewerManager = dataViewerManager;
	firePropertyChange("dataViewerManager", oldValue, dataViewerManager);
}


/**
 * Sets the simulationModelInfo property (cbit.vcell.client.data.SimulationModelInfo) value.
 * @param simulationModelInfo The new value for the property.
 * @see #getSimulationModelInfo
 */
public void setSimulationModelInfo(SimulationModelInfo simulationModelInfo) {
	SimulationModelInfo oldValue = fieldSimulationModelInfo;
	fieldSimulationModelInfo = simulationModelInfo;
	firePropertyChange("simulationModelInfo", oldValue, simulationModelInfo);
}
}