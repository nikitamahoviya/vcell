package cbit.vcell.modelopt.gui;
import javax.swing.MutableComboBoxModel;
import java.io.Serializable;
import javax.swing.AbstractListModel;

/**
 * Insert the type's description here.
 * Creation date: (7/3/2002 1:48:59 PM)
 * @author: John Wagner
 */
public class AnalysisTaskComboBoxModel extends AbstractListModel implements javax.swing.ComboBoxModel, java.beans.PropertyChangeListener {
	cbit.vcell.modelopt.AnalysisTask selectedObject = null;
	private cbit.vcell.mapping.SimulationContext fieldSimulationContext = null;

	/**
	 * Constructs an empty DefaultComboBoxModel object.
	 */
	public AnalysisTaskComboBoxModel() {
	}


// implements javax.swing.ListModel
public Object getElementAt(int index) {
	if (getSimulationContext()==null){
		return null;
	}
	cbit.vcell.modelopt.AnalysisTask analysisTasks[] = getSimulationContext().getAnalysisTasks();
	if (analysisTasks==null || index<0 || index >= analysisTasks.length){
		return null;
	}else{
		return analysisTasks[index];
	}
}


	/**
	 * Returns the index-position of the specified object in the list.
	 *
	 * @param anObject  
	 * @return an int representing the index position, where 0 is 
	 *         the first position
	 */
	public int getIndexOf(Object anObject) {
		if (getSimulationContext()==null){
			return -1;
		}
		cbit.vcell.modelopt.AnalysisTask[] analysisTasks = getSimulationContext().getAnalysisTasks();
		for (int i = 0;analysisTasks!=null && i < analysisTasks.length; i++){
			if (analysisTasks[i] == anObject){
				return i;
			}
		}
		return -1;
	}


 /** Return the selected item **/
public java.lang.Object getSelectedItem() {
	return selectedObject;
}


/**
 * Gets the simulationContext property (cbit.vcell.mapping.SimulationContext) value.
 * @return The simulationContext property value.
 * @see #setSimulationContext
 */
public cbit.vcell.mapping.SimulationContext getSimulationContext() {
	return fieldSimulationContext;
}


	// implements javax.swing.ListModel
	public int getSize() {
		if (getSimulationContext()==null || getSimulationContext().getAnalysisTasks()==null){
			return 0;
		}else{
			return getSimulationContext().getAnalysisTasks().length;
		}
	}


	/**
	 * This method gets called when a bound property is changed.
	 * @param evt A PropertyChangeEvent object describing the event source 
	 *   	and the property that has changed.
	 */
public void propertyChange(java.beans.PropertyChangeEvent evt) {
	 if (evt.getSource() == getSimulationContext() && evt.getPropertyName().equals("analysisTasks")){
		 cbit.vcell.modelopt.AnalysisTask oldSelected = (cbit.vcell.modelopt.AnalysisTask)getSelectedItem();
		 
		 cbit.vcell.modelopt.AnalysisTask[] oldAnalysisTasks = (cbit.vcell.modelopt.AnalysisTask[])evt.getOldValue();
		 if (oldAnalysisTasks!=null && oldAnalysisTasks.length > 0){
			 for (int i = 0; i < oldAnalysisTasks.length; i++){
			 	oldAnalysisTasks[i].removePropertyChangeListener(this);
			 }
			 fireIntervalRemoved(this,0,oldAnalysisTasks.length-1);
		 }
		 cbit.vcell.modelopt.AnalysisTask[] newAnalysisTasks = (cbit.vcell.modelopt.AnalysisTask[])evt.getNewValue();		 
		 if (newAnalysisTasks!=null && newAnalysisTasks.length > 0){
			 for (int i = 0; i < newAnalysisTasks.length; i++){
			 	newAnalysisTasks[i].addPropertyChangeListener(this);
			 }
			 fireIntervalAdded(this,0,newAnalysisTasks.length-1);
		 }
		 if (newAnalysisTasks==null || newAnalysisTasks.length==0){
			 setSelectedItem(null);
		 } else {
			 cbit.vcell.modelopt.AnalysisTask newSelected = null;
			 if (oldSelected != null) {
				 for (int i = 0; i < newAnalysisTasks.length; i++){
				 	if (oldSelected.getName().equals(newAnalysisTasks[i].getName())) {
					 	newSelected = newAnalysisTasks[i];
					 	break;
				 	}
				 }
			 }
			 if (newSelected == null) {
				 newSelected = newAnalysisTasks[0];
			 }
			 setSelectedItem(newSelected);
		 }
	 }
	 if (evt.getSource() instanceof cbit.vcell.modelopt.AnalysisTask){
		 fireContentsChanged(this,0,getSize()-1);
	 }
}


/**
 * Insert the method's description here.
 * Creation date: (12/19/2005 4:26:38 PM)
 */
private void refreshList() {}


// implements javax.swing.ComboBoxModel
public void setSelectedItem(Object anObject) {
	if ((selectedObject != null && !selectedObject.equals(anObject)) || selectedObject == null && anObject != null) {
		selectedObject = (cbit.vcell.modelopt.AnalysisTask)anObject;
		fireContentsChanged(this, -1, -1);
	}
}


/**
 * Sets the simulationContext property (cbit.vcell.mapping.SimulationContext) value.
 * @param simulationContext The new value for the property.
 * @see #getSimulationContext
 */
public void setSimulationContext(cbit.vcell.mapping.SimulationContext simulationContext) {
	if (fieldSimulationContext!=null){
		fieldSimulationContext.removePropertyChangeListener(this);
		if (fieldSimulationContext.getAnalysisTasks() != null) {
			for (int i = 0; i < fieldSimulationContext.getAnalysisTasks().length; i++){
				fieldSimulationContext.getAnalysisTasks()[i].removePropertyChangeListener(this);
			}
		}
	}
	int oldSize = getSize();
	cbit.vcell.modelopt.AnalysisTask oldSelection = (cbit.vcell.modelopt.AnalysisTask)getSelectedItem();
	
	fieldSimulationContext = simulationContext;
	
	if (simulationContext!=null){
		simulationContext.addPropertyChangeListener(this);
		if (simulationContext.getAnalysisTasks() != null) {
			for (int i = 0; i < simulationContext.getAnalysisTasks().length; i++){
				simulationContext.getAnalysisTasks()[i].addPropertyChangeListener(this);
			}
		}		
	}

	if (oldSize > 0) {
		fireIntervalRemoved(this, 0, oldSize-1);
	}
	if (simulationContext!=null){
		fireIntervalAdded(this, 0, getSize());
		//
		// try to select corresponding item if exists
		//
		cbit.vcell.modelopt.AnalysisTask[] analysisTasks = simulationContext.getAnalysisTasks();
		cbit.vcell.modelopt.AnalysisTask newSelection = null;
		if (oldSelection != null){
			if (getIndexOf(oldSelection)>-1){
				newSelection = oldSelection;
			}else{
				for (int i = 0;analysisTasks!=null && i < analysisTasks.length; i++){
					if (analysisTasks[i].getName().equals(oldSelection.getName())){
						newSelection = analysisTasks[i];
					}
				}
			}
		}
		if (newSelection == null && analysisTasks != null && analysisTasks.length > 0){
			newSelection = analysisTasks[0];
		}
		setSelectedItem(newSelection);
	}		
		
}
}