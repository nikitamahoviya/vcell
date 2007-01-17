package cbit.vcell.client.desktop.simulation;
import cbit.vcell.messaging.db.SimulationJobStatus;
import cbit.vcell.solver.Simulation;
import cbit.vcell.solver.ode.gui.SimulationStatus;
/**
 * Insert the type's description here.
 * Creation date: (8/21/2006 9:35:06 AM)
 * @author: Jim Schaff
 */
public class SimulationStatusDetailsTableModel extends javax.swing.table.AbstractTableModel implements java.beans.PropertyChangeListener {
	private final static int COLUMN_SCANINDEX = 0;
	private final static int COLUMN_STATUS = 1;
	private final static int COLUMN_COMPUTEHOST = 2;
	private final static int COLUMN_SOFTWAREVERSION = 3;
	private final static int COLUMN_SUBMITDATE = 4;
	private final static int COLUMN_STARTDATE = 5;
	private final static int COLUMN_ENDDATE = 6;
	
	private final static int NUM_COLUMNS = 8;
	private String[] columnNames = new String[] {"Scan Index", "Status", "Compute Host", "Software Version", "Submit Date", "Start Date", "End Date"};
	protected transient java.beans.PropertyChangeSupport propertyChange;
	private SimulationStatusDetails fieldSimulationStatusDetails = null;

/**
 * SimulationStatusDetailsTableModel constructor comment.
 */
public SimulationStatusDetailsTableModel() {
	super();
}


/**
 * The addPropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
	getPropertyChange().addPropertyChangeListener(listener);
}


/**
 * The addPropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void addPropertyChangeListener(java.lang.String propertyName, java.beans.PropertyChangeListener listener) {
	getPropertyChange().addPropertyChangeListener(propertyName, listener);
}


/**
 * The firePropertyChange method was generated to support the propertyChange field.
 */
public void firePropertyChange(java.beans.PropertyChangeEvent evt) {
	getPropertyChange().firePropertyChange(evt);
}


/**
 * The firePropertyChange method was generated to support the propertyChange field.
 */
public void firePropertyChange(java.lang.String propertyName, int oldValue, int newValue) {
	getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
}


/**
 * The firePropertyChange method was generated to support the propertyChange field.
 */
public void firePropertyChange(java.lang.String propertyName, java.lang.Object oldValue, java.lang.Object newValue) {
	getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
}


/**
 * The firePropertyChange method was generated to support the propertyChange field.
 */
public void firePropertyChange(java.lang.String propertyName, boolean oldValue, boolean newValue) {
	getPropertyChange().firePropertyChange(propertyName, oldValue, newValue);
}


/**
 * Insert the method's description here.
 * Creation date: (8/22/2006 10:06:41 AM)
 * @return java.lang.Class
 * @param columnIndex int
 */
public Class getColumnClass(int columnIndex) {
	switch (columnIndex) {
		case COLUMN_SUBMITDATE : 
		case COLUMN_STARTDATE : 
		case COLUMN_ENDDATE : {
			return java.util.Date.class;
		}
		default: {
			return Object.class;
		}
	}
}


/**
 * getColumnCount method comment.
 */
public int getColumnCount() {
	return columnNames.length;
}


/**
 * getColumnCount method comment.
 */
public String getColumnName(int column) {
	return columnNames[column];
}


/**
 * Accessor for the propertyChange field.
 */
protected java.beans.PropertyChangeSupport getPropertyChange() {
	if (propertyChange == null) {
		propertyChange = new java.beans.PropertyChangeSupport(this);
	};
	return propertyChange;
}


/**
 * getRowCount method comment.
 */
public int getRowCount() {
	if (getSimulationStatusDetails() == null) {
		return 0;
	}
	
	return getSimulationStatusDetails().getSimulation().getScanCount();
}


/**
 * Gets the simulationStatusDetails property (cbit.vcell.client.desktop.simulation.SimulationStatusDetails) value.
 * @return The simulationStatusDetails property value.
 * @see #setSimulationStatusDetails
 */
public SimulationStatusDetails getSimulationStatusDetails() {
	return fieldSimulationStatusDetails;
}


/**
 * getValueAt method comment.
 */
public Object getValueAt(int row, int col) {
	SimulationStatus simStatus = getSimulationStatusDetails().getSimulationStatus();
	if (row >= 0 && row < getRowCount() && simStatus != null) {
		SimulationJobStatus jobStatus = simStatus.getJobStatus(row);
		switch (col) {			
			case COLUMN_SCANINDEX: {
				if (jobStatus == null) {
					return null;
				}
				return new Integer(jobStatus.getJobIndex());
			}
			case COLUMN_STATUS : {
				return getSimulationStatusDetails().getSimulationStatusDisplay(row);
			}
			case COLUMN_COMPUTEHOST : {
				if (jobStatus == null) {
					return null;
				}
				return jobStatus.getComputeHost();
			}
			case COLUMN_SOFTWAREVERSION : {
				if (jobStatus == null) {
					return null;
				}
				return jobStatus.getServerID();
			}
			case COLUMN_SUBMITDATE : {
				if (jobStatus == null) {
					return null;
				}
				return jobStatus.getSubmitDate();
			}
			case COLUMN_STARTDATE : {
				if (jobStatus == null) {
					return null;
				}
				return jobStatus.getStartDate();	
			}
			case COLUMN_ENDDATE : {
				if (jobStatus == null) {
					return null;
				}
				return jobStatus.getEndDate();
			}
			default: {
				if (jobStatus == null) {
					return null;
				}
				return null;
			}
		}
	} else {
		return null;
	}
}


/**
 * The hasListeners method was generated to support the propertyChange field.
 */
public synchronized boolean hasListeners(java.lang.String propertyName) {
	return getPropertyChange().hasListeners(propertyName);
}


	/**
	 * This method gets called when a bound property is changed.
	 * @param evt A PropertyChangeEvent object describing the event source 
	 *   	and the property that has changed.
	 */
public void propertyChange(java.beans.PropertyChangeEvent evt) {
	if (evt.getSource() == fieldSimulationStatusDetails.getSimulationWorkspace() && evt.getPropertyName().equals("status")) {
		fireTableDataChanged();
	}	
}


/**
 * The removePropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
	getPropertyChange().removePropertyChangeListener(listener);
}


/**
 * The removePropertyChangeListener method was generated to support the propertyChange field.
 */
public synchronized void removePropertyChangeListener(java.lang.String propertyName, java.beans.PropertyChangeListener listener) {
	getPropertyChange().removePropertyChangeListener(propertyName, listener);
}


/**
 * Sets the simulationStatusDetails property (cbit.vcell.client.desktop.simulation.SimulationStatusDetails) value.
 * @param simulationStatusDetails The new value for the property.
 * @see #getSimulationStatusDetails
 */
public void setSimulationStatusDetails(SimulationStatusDetails simulationStatusDetails) {
	if (getSimulationStatusDetails() != null && getSimulationStatusDetails().getSimulationWorkspace() != null) {
		getSimulationStatusDetails().getSimulationWorkspace().removePropertyChangeListener(this);
	}
	
	SimulationStatusDetails oldValue = fieldSimulationStatusDetails;
	fieldSimulationStatusDetails = simulationStatusDetails;
	firePropertyChange("simulationStatusDetails", oldValue, simulationStatusDetails);


	if (getSimulationStatusDetails() != null && getSimulationStatusDetails().getSimulationWorkspace() != null) {	
		getSimulationStatusDetails().getSimulationWorkspace().addPropertyChangeListener(this);
	}

	fireTableDataChanged();
}
}