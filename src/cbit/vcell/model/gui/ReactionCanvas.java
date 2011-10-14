/*
 * Copyright (C) 1999-2011 University of Connecticut Health Center
 *
 * Licensed under the MIT License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/mit-license.php
 */

package cbit.vcell.model.gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;

import cbit.vcell.model.Catalyst;
import cbit.vcell.model.Flux;
import cbit.vcell.model.FluxReaction;
import cbit.vcell.model.Kinetics;
import cbit.vcell.model.MassActionKinetics;
import cbit.vcell.model.Membrane;
import cbit.vcell.model.Product;
import cbit.vcell.model.Reactant;
import cbit.vcell.model.ReactionParticipant;
import cbit.vcell.model.ReactionStep;
import cbit.vcell.model.SimpleReaction;
import cbit.vcell.model.Species;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public class ReactionCanvas extends javax.swing.JPanel implements java.beans.PropertyChangeListener {
	private java.awt.Dimension expressionBounds = new Dimension();
	private java.awt.Image offScreenImage = null;
	private java.awt.Dimension offScreenImageSize = null;
	private cbit.vcell.model.ReactionStep fieldReactionStep = null;
	private ReactionCanvasDisplaySpec fieldReactionCanvasDisplaySpec = null;

/**
 * Constructor
 */
public ReactionCanvas() {
	super();
	initialize();
}

//used by the publish package.
	public int getReactionAsImage(Image customImage, int width, int height, int fontSize) {

	try { 
		if (getReactionStep()== null){
			System.err.println("Cannot generate image: no reaction step.");
			return fontSize;
		}
		if (fontSize <= 0) {									//default font size.
			fontSize = 12;
		}
		boolean firstTime = true;
		int totWidth = 0;
		while (totWidth > width || firstTime) {
			if (firstTime) {
				firstTime = false;
			} else {
				fontSize = fontSize - 1;
				//System.out.println("New font size: " + fontSize);
			}
			refreshGraphics0(customImage,width,height,fontSize);
		}		
	}catch (Exception e){
		offScreenImage = null;
		System.out.println("exception in ReactionCanvas.refreshGraphics()");
		e.printStackTrace(System.out);
	}
	
	return fontSize;
}


/**
 * Gets the reactionCanvasDisplaySpec property (cbit.vcell.model.gui.ReactionCanvasDisplaySpec) value.
 * @return The reactionCanvasDisplaySpec property value.
 * @see #setReactionCanvasDisplaySpec
 */
public ReactionCanvasDisplaySpec getReactionCanvasDisplaySpec() {
	return fieldReactionCanvasDisplaySpec;
}


/**
 * Gets the reactionStep property (cbit.vcell.model.ReactionStep) value.
 * @return The reactionStep property value.
 * @see #setReactionStep
 */
public cbit.vcell.model.ReactionStep getReactionStep() {
	return fieldReactionStep;
}


/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	// System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	// exception.printStackTrace(System.out);
}


/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("ReactionCanvas");
		setLayout(null);
		setSize(160, 120);
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}


/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		javax.swing.JFrame frame = new javax.swing.JFrame();
		ReactionCanvas aReactionCanvas;
		aReactionCanvas = new ReactionCanvas();
		frame.setContentPane(aReactionCanvas);
		frame.setSize(aReactionCanvas.getSize());
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		java.awt.Insets insets = frame.getInsets();
		frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
		frame.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of javax.swing.JPanel");
		exception.printStackTrace(System.out);
	}
}


/**
 * This method was created by a SmartGuide.
 * @param g java.awt.Graphics
 */
public void paintComponent(java.awt.Graphics g) {
	super.paintComponent(g);
	if (offScreenImage == null || offScreenImageSize == null || !offScreenImageSize.equals(getSize())){
		refreshGraphics();
	}
	if (offScreenImage != null){
		g.drawImage(offScreenImage,-1,-1,this);
	}	
	return;
}


/**
 * This method was created in VisualAge.
 * @param event java.beans.PropertyChangeEvent
 */
public void propertyChange(java.beans.PropertyChangeEvent event) {
	if (event.getSource() == getReactionStep()){
		if (event.getPropertyName().equals("kinetics")){
			if (event.getOldValue()!=null){
				((Kinetics)event.getOldValue()).removePropertyChangeListener(this);
			}
			if (event.getNewValue()!=null){
				((Kinetics)event.getNewValue()).addPropertyChangeListener(this);
			}
		}
		updateDisplaySpecFromReactionStep();
	} else if (event.getSource() instanceof Kinetics){
		updateDisplaySpecFromReactionStep();
	}
}


/**
 * This method was created by a SmartGuide.
 * @param g java.awt.Graphics
 */
private synchronized void refreshGraphics () {

	try {
		if (offScreenImage==null || offScreenImageSize==null || !offScreenImageSize.equals(getSize())){
			offScreenImageSize = new java.awt.Dimension(getSize().width,getSize().height);
			offScreenImage = createImage(offScreenImageSize.width,offScreenImageSize.height);
		}
		if (offScreenImage==null){
			return;
		}
		refreshGraphics0(offScreenImage,offScreenImageSize.width,offScreenImageSize.height,12);
	}catch (Exception e){
		offScreenImage = null;
		System.out.println("exception in ReactionCanvas.refreshGraphics()");
		e.printStackTrace(System.out);
	}			
	return;
}


/**
 * This method was created by a SmartGuide.
 * @param g java.awt.Graphics
 */
private void refreshGraphics0(Image offScreenImage, int width, int height, int fontSize) {

	try {
		java.awt.Graphics g = offScreenImage.getGraphics();
		g.setClip(0,0,width,height);
		g.setFont(getFont().deriveFont(Font.BOLD));
		g.setColor(getBackground());
	  	g.fillRect(0,0,width,height);
  	
		if (getReactionCanvasDisplaySpec()!=null){
			ReactionCanvasDisplaySpec spec = getReactionCanvasDisplaySpec();
			g.setColor(java.awt.Color.black);
			Dimension newBounds = new Dimension(0,0);
			java.awt.FontMetrics fm = g.getFontMetrics();
			int bottomWidth =  (spec.getBottomText()!=null)?fm.stringWidth(spec.getBottomText()):0;
			int topWidth =  (spec.getTopText()!=null)?fm.stringWidth(spec.getTopText()):0;
			int leftWidth = (spec.getLeftText()!=null)?fm.stringWidth(spec.getLeftText()):0;
			int productWidth =  (spec.getRightText()!=null)?fm.stringWidth(spec.getRightText()):0;
			int bottomHeight =  (spec.getBottomText()!=null)?fm.getHeight():0;
			int topHeight =  (spec.getTopText()!=null)?fm.getHeight():0;
			int leftHeight = (spec.getLeftText()!=null)?fm.getHeight():0;
			int rightHeight =  (spec.getRightText()!=null)?fm.getHeight():0;
			int rateWidth = Math.max(bottomWidth,topWidth) + 30;
			int totHeight = Math.max(leftHeight, rightHeight);
			totHeight += topHeight + bottomHeight + 20;
			int totWidth = leftWidth + rateWidth + productWidth + 40;
			newBounds.width = totWidth;
			newBounds.height = totHeight;
			java.awt.Rectangle rect = g.getClipBounds();
			int posy = rect.y + rect.height/2 + leftHeight/2;
			int posx = rect.x + rect.width/2 - totWidth / 2 + 10;
			if (spec.getLeftText()!=null){
				g.drawString(spec.getLeftText(), posx, posy); posx += leftWidth + 10;
			}	
			if (spec.getArrowType()==ReactionCanvasDisplaySpec.ARROW_RIGHT){
				g.drawLine(posx,             posy-fm.getHeight()/3,   posx+rateWidth,   posy-fm.getHeight()/3  );
				g.drawLine(posx+rateWidth,   posy-fm.getHeight()/3,   posx+rateWidth-5, posy-fm.getHeight()/3-5);
				g.drawLine(posx+rateWidth,   posy-fm.getHeight()/3,   posx+rateWidth-5, posy-fm.getHeight()/3+5);
			} else if (spec.getArrowType()==ReactionCanvasDisplaySpec.ARROW_BOTH){
				g.drawLine(posx,             posy-fm.getHeight()/3-2, posx+rateWidth,   posy-fm.getHeight()/3-2);
				g.drawLine(posx,             posy-fm.getHeight()/3+2, posx+rateWidth,   posy-fm.getHeight()/3+2);
				g.drawLine(posx+rateWidth,   posy-fm.getHeight()/3-2, posx+rateWidth-5, posy-fm.getHeight()/3-7);
				g.drawLine(posx+1,           posy-fm.getHeight()/3+2, posx+6,           posy-fm.getHeight()/3+7);
			}	 
			posx += rateWidth + 10;
			if (spec.getRightText()!=null){
				g.drawString(spec.getRightText(), posx, posy);
			}	
			posy -= leftHeight;
			posx -= rateWidth/2 + 10 + topWidth/2;
			if (spec.getTopText()!=null){
				g.drawString(spec.getTopText(), posx, posy);
			}	
			posy += leftHeight + bottomHeight;
			posx += topWidth/2 - bottomWidth/2;
			if (spec.getBottomText()!=null){
				g.drawString(spec.getBottomText(), posx, posy);
			}	
			if (! newBounds.equals(expressionBounds)) {
				expressionBounds = newBounds;
				resizeToExpression();
			}
		}		
	}catch (Exception e){
		offScreenImage = null;
		System.out.println("exception in ReactionCanvas.refreshGraphics()");
		e.printStackTrace(System.out);
	}			
	return;
}


/**
 * This method was created by a SmartGuide.
 */
private void resizeToExpression() {
	try {
		if (getReactionCanvasDisplaySpec() != null) {
			setSize(expressionBounds.width, expressionBounds.height);
			setPreferredSize(new Dimension(expressionBounds.width, expressionBounds.height));
			revalidate();
		} else {
			java.awt.Dimension parentDim = getParent().getSize();
			setSize(parentDim.width - 10, parentDim.height - 10);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}


/**
 * Sets the reactionCanvasDisplaySpec property (cbit.vcell.model.gui.ReactionCanvasDisplaySpec) value.
 * @param reactionCanvasDisplaySpec The new value for the property.
 * @see #getReactionCanvasDisplaySpec
 */
public void setReactionCanvasDisplaySpec(ReactionCanvasDisplaySpec reactionCanvasDisplaySpec) {
	ReactionCanvasDisplaySpec oldValue = fieldReactionCanvasDisplaySpec;
	fieldReactionCanvasDisplaySpec = reactionCanvasDisplaySpec;
	firePropertyChange("reactionCanvasDisplaySpec", oldValue, reactionCanvasDisplaySpec);
	refreshGraphics();
	repaint();
}


/**
 * Sets the reactionStep property (cbit.vcell.model.ReactionStep) value.
 * @param reactionStep The new value for the property.
 * @see #getReactionStep
 */
public void setReactionStep(cbit.vcell.model.ReactionStep reactionStep) {
	ReactionStep oldReactionStep = fieldReactionStep;
	fieldReactionStep = reactionStep;
	if (oldReactionStep != null){
		oldReactionStep.removePropertyChangeListener(this);
		if (oldReactionStep.getKinetics()!=null){
			oldReactionStep.getKinetics().removePropertyChangeListener(this);
		}
	}	
	if (fieldReactionStep != null){
		fieldReactionStep.addPropertyChangeListener(this);
		if (fieldReactionStep.getKinetics()!=null){
			fieldReactionStep.getKinetics().addPropertyChangeListener(this);
		}
	}
	firePropertyChange("reactionStep", oldReactionStep, reactionStep);
	updateDisplaySpecFromReactionStep();
}


/**
 * This method was created by a SmartGuide.
 * @param observable java.util.Observable
 * @param object java.lang.Object
 */
private void updateDisplaySpecFromReactionStep() {
	try {
		if (getReactionStep()!=null){
			
			String forwardRateString = null;
			String reverseRateString = null;
			String reactantString = null;
			String productString = null;
			String fluxString = null;
			boolean bReversible = false;

			//
			// get list of reactants, products, catalysts
			//
			Vector<ReactionParticipant> reactantList = new Vector<ReactionParticipant>();
			Vector<ReactionParticipant> productList = new Vector<ReactionParticipant>();
			Vector<ReactionParticipant> catalystList = new Vector<ReactionParticipant>();
			ReactionParticipant rp_Array[] = getReactionStep().getReactionParticipants();
			for (int i = 0; i < rp_Array.length; i++) {
				if (rp_Array[i] instanceof Reactant){
					reactantList.addElement(rp_Array[i]);
				}else if (rp_Array[i] instanceof Product){
					productList.addElement(rp_Array[i]);
				}else if (rp_Array[i] instanceof Catalyst){
					catalystList.addElement(rp_Array[i]);
				}
			}
			
			//
			// default for ForwardRateString is to display catalysts, if present
			//
			//           E1
			// (e.g. a -----> b    )
			//
			//
			forwardRateString = "";
			if (catalystList.size()>0){
				for (int i = 0; i < catalystList.size(); i++){
					if (i>0){
						forwardRateString += ",";
					}
					forwardRateString += ((Catalyst)catalystList.elementAt(i)).getSpeciesContext().getName();						
				}
			}
			
			if (getReactionStep() instanceof SimpleReaction){
				SimpleReaction simpleReaction = (SimpleReaction)getReactionStep();
				//
				// get rate expression strings
				//
				if (simpleReaction.getKinetics() instanceof MassActionKinetics) {
					Kinetics.KineticsParameter forwardRateParam = ((MassActionKinetics)simpleReaction.getKinetics()).getForwardRateParameter();
					if (forwardRateParam!=null){
						forwardRateString = forwardRateParam.getName();
					}
					Kinetics.KineticsParameter reverseRateParam = ((MassActionKinetics)simpleReaction.getKinetics()).getReverseRateParameter();
					if (reverseRateParam!=null){
						reverseRateString = reverseRateParam.getName();
						bReversible = true;
					}
				} else {
					reverseRateString = null;
				}
				//
				// form reactant string
				//
				reactantString = "";
				for (int i=0;i<reactantList.size();i++){
					if (i>0){
						reactantString += " + ";
					}
					if (((Reactant)reactantList.elementAt(i)).getStoichiometry() != 1.0){
						reactantString += ((Reactant)reactantList.elementAt(i)).getStoichiometry() + " ";
					}	
					reactantString += ((Reactant)reactantList.elementAt(i)).getSpeciesContext().getName();
				}		
				//
				// form product string
				//
				productString = "";
				for (int i=0;i<productList.size();i++){
					if (i>0){
						productString += " + ";
					}	
					if (((Product)productList.elementAt(i)).getStoichiometry() != 1.0){
						productString += ((Product)productList.elementAt(i)).getStoichiometry() + " ";
					}	
					productString += ((Product)productList.elementAt(i)).getSpeciesContext().getName();
				}		
			}else if (getReactionStep() instanceof FluxReaction){
				bReversible = false;
				FluxReaction fluxReaction = (FluxReaction)getReactionStep();
				Species fluxCarrier = fluxReaction.getFluxCarrier();
				reverseRateString = null;			
				if (fluxCarrier==null){
					fluxString = "unspecified flux carrier";
					reactantString = fluxString;
					productString = fluxString;
				}else{
					Membrane membrane = (Membrane)fluxReaction.getStructure();
					Flux outFlux = fluxReaction.getFlux(membrane.getOutsideFeature());
					Flux inFlux = fluxReaction.getFlux(membrane.getInsideFeature());
					String outSCName = (outFlux!=null)?outFlux.getSpeciesContext().getName():"unknown";
					String inSCName = (inFlux!=null)?inFlux.getSpeciesContext().getName():"unknown";
					fluxString = outSCName+"   > > > > > > > > > >   "+inSCName;
					reactantString = outSCName;
					productString = inSCName;
				}
			}
			int arrowType = (bReversible)?(ReactionCanvasDisplaySpec.ARROW_BOTH):(ReactionCanvasDisplaySpec.ARROW_RIGHT);
			setReactionCanvasDisplaySpec(new ReactionCanvasDisplaySpec(reactantString,productString,forwardRateString,reverseRateString,arrowType));
		}	
	}catch (Exception e){
		System.out.println("exception in ReactionCanvas.update(Observable, Object)");
		e.printStackTrace(System.out);
	}		
}

}
