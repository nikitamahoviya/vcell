package cbit.vcell.constraints.gui;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import cbit.gui.DialogUtils;
import cbit.gui.graph.Shape;
import cbit.gui.graph.SimpleContainerShape;
import cbit.gui.graph.SimpleGraphCartoonTool;

/**
 * This class was generated by a SmartGuide.
 * 
 */
public class ConstraintGraphCartoonTool extends SimpleGraphCartoonTool {

	private cbit.vcell.constraints.ConstraintSolver fieldConstraintSolver = null;

/**
 * This method was created by a SmartGuide.
 * @param canvas cbit.vcell.graph.CartoonCanvas
 */
public ConstraintGraphCartoonTool () {
	super();
}

/**
 * Insert the method's description here.
 * Creation date: (9/9/2002 10:25:37 AM)
 * @return cbit.vcell.graph.GraphModel
 */
public ConstraintsGraphModel getConstraintsGraphModel() {
	return (ConstraintsGraphModel)getGraphModel();
}


/**
 * Gets the constraintSolver property (cbit.vcell.constraints.ConstraintSolver) value.
 * @return The constraintSolver property value.
 * @see #setConstraintSolver
 */
public cbit.vcell.constraints.ConstraintSolver getConstraintSolver() {
	return fieldConstraintSolver;
}


/**
 * Insert the method's description here.
 * Creation date: (9/17/2002 3:56:54 PM)
 * @param shape cbit.vcell.graph.Shape
 * @param menuAction java.lang.String
 */
protected void menuAction(Shape shape, String menuAction) {
	try {
	
		if(shape == null){return;}

		// if multiselect, then get them all
		ConstraintsGraphModel constraintsGraphModel = (ConstraintsGraphModel)getGraphModel();
		Shape shapes[] = constraintsGraphModel.getAllSelectedShapes();
		//	
		if (menuAction.equals(RESET_MENU_ACTION)){
			getConstraintSolver().resetIntervals();
		}
		if (menuAction.equals(SOLVE_MENU_ACTION)){
			getConstraintSolver().narrow();
		}
		if (menuAction.equals(ENABLE_MENU_ACTION)){
			for (int i = 0; i < shapes.length; i++){
				if (shapes[i] instanceof BoundsNode || shapes[i] instanceof GeneralConstraintNode){
					cbit.vcell.constraints.AbstractConstraint constraint = (cbit.vcell.constraints.AbstractConstraint)shapes[i].getModelObject();
					getConstraintsGraphModel().getConstraintContainerImpl().setActive(constraint,true);
				}
			}
		}
		if (menuAction.equals(DISABLE_MENU_ACTION)){
			for (int i = 0; i < shapes.length; i++){
				if (shapes[i] instanceof BoundsNode || shapes[i] instanceof GeneralConstraintNode){
					cbit.vcell.constraints.AbstractConstraint constraint = (cbit.vcell.constraints.AbstractConstraint)shapes[i].getModelObject();
					getConstraintsGraphModel().getConstraintContainerImpl().setActive(constraint,false);
				}
			}
		}
		if (menuAction.equals(DELETE_MENU_ACTION)){
			java.util.Vector boundsToDelete = new java.util.Vector();
			java.util.Vector genConstraintsToDelete = new java.util.Vector();
			for (int i = 0; i < shapes.length; i++){
				if (shapes[i] instanceof BoundsNode){
					boundsToDelete.add(shapes[i].getModelObject());
				}else if (shapes[i] instanceof GeneralConstraintNode){
					genConstraintsToDelete.add(shapes[i].getModelObject());
				}
			}
			if (boundsToDelete.size()>0){
				cbit.vcell.constraints.SimpleBounds bounds[] = constraintsGraphModel.getConstraintContainerImpl().getSimpleBounds();
				for (int i = 0; i < boundsToDelete.size(); i++){
					bounds = (cbit.vcell.constraints.SimpleBounds[])cbit.util.BeanUtils.removeElement(bounds,boundsToDelete.elementAt(i));
				}
				constraintsGraphModel.getConstraintContainerImpl().setSimpleBounds(bounds);
			}
			if (genConstraintsToDelete.size()>0){
				cbit.vcell.constraints.GeneralConstraint genConstraints[] = constraintsGraphModel.getConstraintContainerImpl().getGeneralConstraints();
				for (int i = 0; i < genConstraintsToDelete.size(); i++){
					genConstraints = (cbit.vcell.constraints.GeneralConstraint[])cbit.util.BeanUtils.removeElement(genConstraints,genConstraintsToDelete.elementAt(i));
				}
				constraintsGraphModel.getConstraintContainerImpl().setGeneralConstraints(genConstraints);
			}
		}
	}catch (Throwable e){
		DialogUtils.showErrorDialog(e.getMessage());
	}
}

/**
 * Insert the method's description here.
 * Creation date: (5/14/2003 10:51:54 AM)
 * @param newReactionCartoon cbit.vcell.graph.ReactionCartoon
 */
public void setConstraintsGraphModel(ConstraintsGraphModel constraintsGraphModel) {
	setGraphModel(constraintsGraphModel);
}


/**
 * Sets the constraintSolver property (cbit.vcell.constraints.ConstraintSolver) value.
 * @param constraintSolver The new value for the property.
 * @see #getConstraintSolver
 */
public void setConstraintSolver(cbit.vcell.constraints.ConstraintSolver constraintSolver) {
	cbit.vcell.constraints.ConstraintSolver oldValue = fieldConstraintSolver;
	fieldConstraintSolver = constraintSolver;
	firePropertyChange("constraintSolver", oldValue, constraintSolver);
}


/**
 * Insert the method's description here.
 * Creation date: (9/17/2002 3:47:34 PM)
 * @return boolean
 * @param shape cbit.vcell.graph.Shape
 * @param actionString java.lang.String
 */
protected boolean shapeHasMenuAction(Shape shape, String menuAction) {
	if (shape == null){
		return false;
	}
	
	if (menuAction.equals(RESET_MENU_ACTION)){
		if (shape instanceof SimpleContainerShape && shape.getModelObject() == ((ConstraintsGraphModel)getGraphModel()).getConstraintContainerImpl()){
			return true;
		}
	}
	if (menuAction.equals(SOLVE_MENU_ACTION)){
		if (shape instanceof SimpleContainerShape && shape.getModelObject() == ((ConstraintsGraphModel)getGraphModel()).getConstraintContainerImpl()){
			return true;
		}
	}
	if (menuAction.equals(ENABLE_MENU_ACTION)){
		if (shape instanceof GeneralConstraintNode ||
			shape instanceof BoundsNode){
			return true;
		}
	}
	if (menuAction.equals(DISABLE_MENU_ACTION)){
		if (shape instanceof GeneralConstraintNode ||
			shape instanceof BoundsNode){
			return true;
		}
	}
	if (menuAction.equals(DELETE_MENU_ACTION)){
		if (shape instanceof GeneralConstraintNode ||
			shape instanceof BoundsNode){
			return true;
		}
	}
	return false;
}


/**
 * Insert the method's description here.
 * Creation date: (5/9/2003 9:11:06 AM)
 * @return boolean
 * @param actionString java.lang.String
 */
protected boolean shapeHasMenuActionEnabled(Shape shape, java.lang.String menuAction) {
	if (shape == null){
		return false;
	}
	
	if (menuAction.equals(RESET_MENU_ACTION)){
		if (shape instanceof SimpleContainerShape && shape.getModelObject() == ((ConstraintsGraphModel)getGraphModel()).getConstraintContainerImpl()){
			return true;
		}
	}
	if (menuAction.equals(SOLVE_MENU_ACTION)){
		if (shape instanceof SimpleContainerShape && shape.getModelObject() == ((ConstraintsGraphModel)getGraphModel()).getConstraintContainerImpl()){
			return true;
		}
	}
	if (menuAction.equals(ENABLE_MENU_ACTION)){
		//
		// enable if any selected shapes can be enabled
		//
		Shape shapes[] = getGraphModel().getAllSelectedShapes();
		for (int i = 0; i < shapes.length; i++){
			if ((shapes[i] instanceof GeneralConstraintNode || shapes[i] instanceof BoundsNode)){
				cbit.vcell.constraints.AbstractConstraint constraint = (cbit.vcell.constraints.AbstractConstraint)shapes[i].getModelObject();
				if (getConstraintsGraphModel().getConstraintContainerImpl().getActive(constraint)==false){
					return true;
				}
			}
		}
	}
	if (menuAction.equals(DISABLE_MENU_ACTION)){
		//
		// disable if any selected shapes can be disabled
		//
		Shape shapes[] = getGraphModel().getAllSelectedShapes();
		for (int i = 0; i < shapes.length; i++){
			if ((shapes[i] instanceof GeneralConstraintNode || shapes[i] instanceof BoundsNode)){
				cbit.vcell.constraints.AbstractConstraint constraint = (cbit.vcell.constraints.AbstractConstraint)shapes[i].getModelObject();
				if (getConstraintsGraphModel().getConstraintContainerImpl().getActive(constraint)==true){
					return true;
				}
			}
		}
	}
	if (menuAction.equals(DELETE_MENU_ACTION)){
		//
		// delete if any selected shapes can be deleted
		//
		Shape shapes[] = getGraphModel().getAllSelectedShapes();
		for (int i = 0; i < shapes.length; i++){
			if ((shapes[i] instanceof GeneralConstraintNode || shapes[i] instanceof BoundsNode)){
				return true;
			}
		}
	}
	return false;
}

}