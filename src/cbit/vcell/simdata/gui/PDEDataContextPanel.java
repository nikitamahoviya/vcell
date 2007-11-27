package cbit.vcell.simdata.gui;

import java.util.Vector;

import cbit.vcell.simdata.*;
import cbit.image.*;
import cbit.vcell.client.data.DataViewer;
import cbit.vcell.geometry.*;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/

/**
 * Insert the type's description here.
 * Creation date: (3/13/2001 12:53:10 PM)
 * @author: Frank Morgan
 */
public class PDEDataContextPanel extends javax.swing.JPanel implements CurveValueProvider {
	//
	private DataViewer.DataInfoProvider dataInfoProvider;
	//
	private java.util.Vector<Curve> membraneSamplerCurves = null;
	private java.util.Hashtable<SampledCurve, int[]> membranesAndIndexes = null;
	private java.util.Hashtable<SampledCurve, Vector<Double>> contoursAndValues = null;
	private MeshDisplayAdapter meshDisplayAdapter = null;
	private ImagePlaneManagerPanel ivjImagePlaneManagerPanel = null;
	private cbit.vcell.simdata.PDEDataContext fieldPdeDataContext = null;
	private SpatialSelection fieldSpatialSelection = null;
	private int fieldSlice = 0;
	private int fieldNormalAxis = 0;
	private boolean ivjConnPtoP1Aligning = false;
	private PDEDataContext ivjpdeDataContext1 = null;
	IvjEventHandler ivjEventHandler = new IvjEventHandler();
	private boolean ivjConnPtoP2Aligning = false;
	private DisplayAdapterServicePanel ivjdisplayAdapterServicePanel1 = null;
	private boolean ivjConnPtoP3Aligning = false;
	private ImagePlaneManager ivjimagePlaneManager1 = null;
	private boolean ivjConnPtoP4Aligning = false;
	private boolean ivjConnPtoP5Aligning = false;
	private DisplayAdapterService ivjdisplayAdapterService1 = null;

class IvjEventHandler implements java.beans.PropertyChangeListener {
		public void propertyChange(java.beans.PropertyChangeEvent evt) {
			if (evt.getSource() == PDEDataContextPanel.this && (evt.getPropertyName().equals("pdeDataContext"))) 
				connEtoC5(evt);
			if (evt.getSource() == PDEDataContextPanel.this && (evt.getPropertyName().equals("pdeDataContext"))) 
				connPtoP1SetTarget();
			if (evt.getSource() == PDEDataContextPanel.this.getImagePlaneManagerPanel() && (evt.getPropertyName().equals("displayAdapterServicePanel"))) 
				connPtoP2SetTarget();
			if (evt.getSource() == PDEDataContextPanel.this.getImagePlaneManagerPanel() && (evt.getPropertyName().equals("imagePlaneManager"))) 
				connPtoP3SetTarget();
			if (evt.getSource() == PDEDataContextPanel.this.getImagePlaneManagerPanel() && (evt.getPropertyName().equals("sourceDataInfo"))) 
				connEtoC10(evt);
			if (evt.getSource() == PDEDataContextPanel.this.getimagePlaneManager1() && (evt.getPropertyName().equals("slice"))) 
				connPtoP4SetTarget();
			if (evt.getSource() == PDEDataContextPanel.this && (evt.getPropertyName().equals("slice"))) 
				connPtoP4SetSource();
			if (evt.getSource() == PDEDataContextPanel.this.getimagePlaneManager1() && (evt.getPropertyName().equals("normalAxis"))) 
				connPtoP5SetTarget();
			if (evt.getSource() == PDEDataContextPanel.this && (evt.getPropertyName().equals("normalAxis"))) 
				connPtoP5SetSource();
			if (evt.getSource() == PDEDataContextPanel.this.getpdeDataContext1() && (evt.getPropertyName().equals("sourceDataInfo"))) 
				connEtoM5(evt);
			if (evt.getSource() == PDEDataContextPanel.this.getimagePlaneManager1() && (evt.getPropertyName().equals("imagePlaneData"))) 
				connEtoC3(evt);
			if (evt.getSource() == PDEDataContextPanel.this.getImagePlaneManagerPanel()) 
				connEtoC4(evt);
			if (evt.getSource() == PDEDataContextPanel.this.getImagePlaneManagerPanel() && (evt.getPropertyName().equals("curveRendererSelection"))) 
				connEtoC1(evt);
			if (evt.getSource() == PDEDataContextPanel.this) 
				connEtoC6(evt);
		};
	};
/**
 * PDEDataContextPanel2 constructor comment.
 */
public PDEDataContextPanel() {
	super();
	initialize();
}
/**
 * Insert the method's description here.
 * Creation date: (10/26/00 4:49:39 PM)
 */
private void colorMembraneCurvesPrivate(java.util.Hashtable<SampledCurve, int[]> curvesAndMembraneIndexes,MeshDisplayAdapter meshDisplayAdapter) {
	cbit.image.DisplayAdapterService das = getdisplayAdapterService1();
	if (curvesAndMembraneIndexes != null) {
		java.util.Enumeration<SampledCurve> keysEnum = curvesAndMembraneIndexes.keys();
		while (keysEnum.hasMoreElements()) {
			SampledCurve curve = keysEnum.nextElement();
			int[] membraneIndexes = curvesAndMembraneIndexes.get(curve);
			double[] membraneValues = null;
			if(membraneIndexes != null && getPdeDataContext().getDataValues() != null && getPdeDataContext().getDataIdentifier() != null){
				membraneValues = meshDisplayAdapter.getDataValuesForMembraneIndexes(membraneIndexes,getPdeDataContext().getDataValues(),getPdeDataContext().getDataIdentifier().getVariableType());
			}
			if (membraneValues != null) {
				int[] valueColors = new int[membraneValues.length];
				for (int i = 0; i < membraneValues.length; i += 1) {
					valueColors[i] = das.getColorFromValue(membraneValues[i]);
				}
				getImagePlaneManagerPanel().getCurveRenderer().renderPropertySegmentColors(curve, valueColors);
				getImagePlaneManagerPanel().getCurveRenderer().renderPropertySegmentIndexes(curve, membraneIndexes);
			} else {
				getImagePlaneManagerPanel().getCurveRenderer().renderPropertySegmentColors(curve, null);
				getImagePlaneManagerPanel().getCurveRenderer().renderPropertySegmentIndexes(curve, null);
			}
		}
	}
}
/**
 * connEtoC1:  (ImagePlaneManagerPanel.curveRendererSelection --> PDEDataContextPanel.firePropertyChange(Ljava.lang.String;Ljava.lang.Object;Ljava.lang.Object;)V)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC1(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.firePropertyChange("curveRendererSelection", arg1.getOldValue(), arg1.getNewValue());
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC10:  (ImagePlaneManagerPanel.sourceDataInfo --> PDEDataContextPanel.updateContours()V)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC10(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.updateContours();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC2:  (DisplayAdapterService1.this --> PDEDataContextPanel.initDisplayAdapterService(Lcbit.image.DisplayAdapterService;)V)
 * @param value cbit.image.DisplayAdapterService
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC2(cbit.image.DisplayAdapterService value) {
	try {
		// user code begin {1}
		// user code end
		this.initDisplayAdapterService(getdisplayAdapterService1());
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC3:  (imagePlaneManager1.imagePlaneData --> PDEDataContextPanel.updateMembraneCurves()V)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC3(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.updateMembraneCurves();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC4:  (ImagePlaneManagerPanel.propertyChange.propertyChange(java.beans.PropertyChangeEvent) --> PDEDataContextPanel.imagePlaneManagerPanel_PropertyChange(Ljava.beans.PropertyChangeEvent;)V)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC4(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.imagePlaneManagerPanel_PropertyChange(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC5:  (PDEDataContextPanel.pdeDataContext --> PDEDataContextPanel.initMeshDisplayAdapter()V)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC5(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.initMeshDisplayAdapter();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC6:  (PDEDataContextPanel2.propertyChange.propertyChange(java.beans.PropertyChangeEvent) --> PDEDataContextPanel.createSpatialSample(Ljava.beans.PropertyChangeEvent;)V)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC6(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		this.createSpatialSample(arg1);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoC7:  (pdeDataContext1.this --> PDEDataContextPanel.slice2dEnable()V)
 * @param value cbit.vcell.simdata.PDEDataContext
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoC7(cbit.vcell.simdata.PDEDataContext value) {
	try {
		// user code begin {1}
		// user code end
		this.slice2dEnable();
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoM1:  (PDEDataContextPanel.initialize() --> ImagePlaneManagerPanel.curveValueProvider)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoM1() {
	try {
		// user code begin {1}
		// user code end
		getImagePlaneManagerPanel().setCurveValueProvider(this);
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoM2:  (pdeDataContext1.this --> ImagePlaneManagerPanel.sourceDataInfo)
 * @param value cbit.vcell.simdata.PDEDataContext
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoM2(cbit.vcell.simdata.PDEDataContext value) {
	try {
		// user code begin {1}
		// user code end
		if ((getpdeDataContext1() != null)) {
			getImagePlaneManagerPanel().setSourceDataInfo(getpdeDataContext1().getSourceDataInfo());
		}
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connEtoM5:  (pdeDataContext1.sourceDataInfo --> ImagePlaneManagerPanel.sourceDataInfo)
 * @param arg1 java.beans.PropertyChangeEvent
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connEtoM5(java.beans.PropertyChangeEvent arg1) {
	try {
		// user code begin {1}
		// user code end
		getImagePlaneManagerPanel().setSourceDataInfo(getpdeDataContext1().getSourceDataInfo());
		// user code begin {2}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP1SetSource:  (PDEDataContextPanel2.pdeDataContext <--> pdeDataContext1.this)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP1SetSource() {
	/* Set the source from the target */
	try {
		if (ivjConnPtoP1Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP1Aligning = true;
			if ((getpdeDataContext1() != null)) {
				this.setPdeDataContext(getpdeDataContext1());
			}
			// user code begin {2}
			// user code end
			ivjConnPtoP1Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP1Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP1SetTarget:  (PDEDataContextPanel2.pdeDataContext <--> pdeDataContext1.this)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP1SetTarget() {
	/* Set the target from the source */
	try {
		if (ivjConnPtoP1Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP1Aligning = true;
			setpdeDataContext1(this.getPdeDataContext());
			// user code begin {2}
			// user code end
			ivjConnPtoP1Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP1Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP2SetTarget:  (ImagePlaneManagerPanel.displayAdapterServicePanel <--> displayAdapterServicePanel1.this)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP2SetTarget() {
	/* Set the target from the source */
	try {
		if (ivjConnPtoP2Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP2Aligning = true;
			setdisplayAdapterServicePanel1(getImagePlaneManagerPanel().getDisplayAdapterServicePanel());
			// user code begin {2}
			// user code end
			ivjConnPtoP2Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP2Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP3SetTarget:  (ImagePlaneManagerPanel.imagePlaneManager <--> imagePlaneManager1.this)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP3SetTarget() {
	/* Set the target from the source */
	try {
		if (ivjConnPtoP3Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP3Aligning = true;
			setimagePlaneManager1(getImagePlaneManagerPanel().getImagePlaneManager());
			// user code begin {2}
			// user code end
			ivjConnPtoP3Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP3Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP4SetSource:  (imagePlaneManager1.slice <--> PDEDataContextPanel.slice)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP4SetSource() {
	/* Set the source from the target */
	try {
		if (ivjConnPtoP4Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP4Aligning = true;
			if ((getimagePlaneManager1() != null)) {
				getimagePlaneManager1().setSlice(this.getSlice());
			}
			// user code begin {2}
			// user code end
			ivjConnPtoP4Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP4Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP4SetTarget:  (imagePlaneManager1.slice <--> PDEDataContextPanel.slice)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP4SetTarget() {
	/* Set the target from the source */
	try {
		if (ivjConnPtoP4Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP4Aligning = true;
			if ((getimagePlaneManager1() != null)) {
				this.setSlice(getimagePlaneManager1().getSlice());
			}
			// user code begin {2}
			// user code end
			ivjConnPtoP4Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP4Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP5SetSource:  (imagePlaneManager1.normalAxis <--> PDEDataContextPanel.normalAxis)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP5SetSource() {
	/* Set the source from the target */
	try {
		if (ivjConnPtoP5Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP5Aligning = true;
			if ((getimagePlaneManager1() != null)) {
				getimagePlaneManager1().setNormalAxis(this.getNormalAxis());
			}
			// user code begin {2}
			// user code end
			ivjConnPtoP5Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP5Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP5SetTarget:  (imagePlaneManager1.normalAxis <--> PDEDataContextPanel.normalAxis)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP5SetTarget() {
	/* Set the target from the source */
	try {
		if (ivjConnPtoP5Aligning == false) {
			// user code begin {1}
			// user code end
			ivjConnPtoP5Aligning = true;
			if ((getimagePlaneManager1() != null)) {
				this.setNormalAxis(getimagePlaneManager1().getNormalAxis());
			}
			// user code begin {2}
			// user code end
			ivjConnPtoP5Aligning = false;
		}
	} catch (java.lang.Throwable ivjExc) {
		ivjConnPtoP5Aligning = false;
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * connPtoP6SetTarget:  (displayAdapterServicePanel1.displayAdapterService <--> displayAdapterService1.this)
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void connPtoP6SetTarget() {
	/* Set the target from the source */
	try {
		setdisplayAdapterService1(getdisplayAdapterServicePanel1().getDisplayAdapterService());
		// user code begin {1}
		// user code end
	} catch (java.lang.Throwable ivjExc) {
		// user code begin {3}
		// user code end
		handleException(ivjExc);
	}
}
/**
 * Comment
 */
private void createSpatialSample(java.beans.PropertyChangeEvent propertyChangeEvent) {
	/* moved from PDEResultsPanel; comment below is from when it was there */
	//PDEResultsPanel and PDEDatacontextPanel should be merged into one
	//this was put here to allow spatialsamples to know when to be 2D projections
	if(propertyChangeEvent.getPropertyName().equals("curveRendererSelection")){
		CurveSelectionInfo csi = (CurveSelectionInfo)propertyChangeEvent.getNewValue();
		createSpatialSelection((csi == null || !(csi.getCurve() instanceof CurveSelectionCurve)) && isSpatialSampling2D());
		boolean bSpatProj =
			getpdeDataContext1() != null &&
			getpdeDataContext1().getSourceDataInfo() != null &&
			getpdeDataContext1().getSourceDataInfo().getZSize() > 1;
		boolean always2D =
			csi != null && 
			(csi.getCurve() instanceof CurveSelectionCurve || csi.getCurve() instanceof SinglePoint);
		getImagePlaneManagerPanel().getSpatialProjectionJCheckBox().setEnabled(!always2D && csi != null && bSpatProj);
		//getImagePlaneManagerPanel().getSpatialProjectionJCheckBox().setSelected(((csi != null && always2D) || userPrefer2D) || !bSpatProj);
	}
}
/**
 * Comment
 */
public void createSpatialSelection(boolean isSpatialSampling2D) {
	SpatialSelection sl[] = null;
	if(	getImagePlaneManagerPanel() != null &&
		getImagePlaneManagerPanel().getCurveRenderer() != null &&
		getImagePlaneManagerPanel().getCurveRenderer().getSelection() != null &&
		getImagePlaneManagerPanel().getCurveRenderer().getSelection().getCurve().isValid()){
		sl = fetchSpatialSelections(getImagePlaneManagerPanel().getCurveRenderer().getSelection().getCurve(),isSpatialSampling2D,false);
	}
	setSpatialSelection((sl!=null?sl[0]:null));
}
/**
 * Insert the method's description here.
 * Creation date: (7/6/2003 8:40:06 PM)
 * @param curve cbit.vcell.geometry.Curve
 */
public void curveAdded(cbit.vcell.geometry.Curve curve) {
	
	fireDataSamplers();	
}
/**
 * Insert the method's description here.
 * Creation date: (7/6/2003 7:42:59 PM)
 * @param curve cbit.vcell.geometry.Curve
 */
public void curveRemoved(cbit.vcell.geometry.Curve curve) {

	fireDataSamplers();	
}
/**
 * Insert the method's description here.
 * Creation date: (6/28/2003 4:57:18 PM)
 * @return cbit.vcell.simdata.gui.SpatialSelection[]
 */
private SpatialSelection[] fetchSpatialSelections(Curve curveOfInterest,boolean isSpatial2D,boolean bFetchOnlyVisible) {
	//
	java.util.Vector<SpatialSelection> spatialSelection = new java.util.Vector<SpatialSelection>();
	//
	if (getPdeDataContext() != null &&
		getPdeDataContext().getCartesianMesh() != null &&
		getImagePlaneManagerPanel() != null &&
		getImagePlaneManagerPanel().getCurveRenderer() != null){
		//
		VariableType vt = getPdeDataContext().getDataIdentifier().getVariableType();
		cbit.vcell.solvers.CartesianMesh cm = getPdeDataContext().getCartesianMesh();
		Curve[] curves = getImagePlaneManagerPanel().getCurveRenderer().getAllCurves();
		//
		if(curves != null && curves.length > 0){
			for(int i = 0;i < curves.length;i+= 1){
				boolean bIsVisible = getImagePlaneManagerPanel().getCurveRenderer().getRenderPropertyVisible(curves[i]);
				if( (bFetchOnlyVisible && !bIsVisible) ||  
					(curveOfInterest != null && curves[i] != curveOfInterest)){
					continue;
				}
				//
				if(	(vt.equals(VariableType.VOLUME) || vt.equals(VariableType.VOLUME_REGION)) &&
					curves[i] instanceof ControlPointCurve &&
					!(curves[i] instanceof CurveSelectionCurve) &&
					(membranesAndIndexes == null || !membranesAndIndexes.containsKey(curves[i]))){	//Volume
					//
					Curve samplerCurve = null;
					if(isSpatial2D){
					samplerCurve = projectCurveOntoSlice(curves[i].getSampledCurve());
					}else{
						samplerCurve = curves[i];
					}
					if(samplerCurve != null){
						spatialSelection.add(new SpatialSelectionVolume(new CurveSelectionInfo(samplerCurve),vt,cm));
					}

					
				}else if((vt.equals(VariableType.MEMBRANE) || vt.equals(VariableType.MEMBRANE_REGION)) && 
							membranesAndIndexes != null){											//Membrane
					//
					if(curves[i] instanceof CurveSelectionCurve){
						CurveSelectionCurve csCurve = (CurveSelectionCurve)curves[i];
						if(csCurve.getSourceCurveSelectionInfo().getCurve() instanceof ControlPointCurve){
							int[] csisegsel = csCurve.getSourceCurveSelectionInfo().getSegmentsInSelectionOrder();
							if(csisegsel != null){
								ControlPointCurve cscpcCurve = (ControlPointCurve)(csCurve.getSourceCurveSelectionInfo().getCurve());
								Curve[] membraneCurves = (Curve[])(membranesAndIndexes.keySet().toArray(new Curve[membranesAndIndexes.size()]));
								//See if CurveSelectionCurve matches controlpoints in space of a membrane we have
								for(int j =0;j < membraneCurves.length;j+= 1){
									if(membraneCurves[j] instanceof ControlPointCurve){//They should all be
										ControlPointCurve cpc = (ControlPointCurve)membraneCurves[j];
										boolean bSame = true;
										for(int k=0;k<csisegsel.length;k+= 1){
											if(	csisegsel[k] >= cpc.getControlPointCount() ||
												csisegsel[k] >= cscpcCurve.getControlPointCount() ||
												!Coordinate.get2DProjection(
													cpc.getControlPoint(csisegsel[k]),
													getNormalAxis()).equals(
												Coordinate.get2DProjection(
														cscpcCurve.getControlPoint(csisegsel[k]),
														getNormalAxis()))){
												//
												bSame = false;
												break;
											}
											
										}
										if(bSame){
											int[] mi = (int[])membranesAndIndexes.get(membraneCurves[j]);
											spatialSelection.add(new SpatialSelectionMembrane(
												new CurveSelectionInfo(membraneCurves[j],
															csisegsel[0],
															csisegsel[csisegsel.length-1],
															csCurve.getSourceCurveSelectionInfo().getDirectionNegative()),
													vt,cm,mi,csCurve));
										}
									}
								}
							}
						}
					}else if(curves[i] instanceof SinglePoint){
						CurveSelectionInfo[] csiArr = getImagePlaneManagerPanel().getCurveRenderer().getCloseCurveSelectionInfos(curves[i].getBeginningCoordinate());
						if(csiArr != null && csiArr.length > 0){
							for(int j =0;j<csiArr.length;j+= 1){
								if(membranesAndIndexes.containsKey(csiArr[j].getCurve())){
									CurveSelectionInfo closestCSI =
										getImagePlaneManagerPanel().getCurveRenderer().getClosestSegmentSelectionInfo(curves[i].getBeginningCoordinate(),csiArr[j].getCurve());
									int[] mi = (int[])membranesAndIndexes.get(csiArr[j].getCurve());
									spatialSelection.add(new SpatialSelectionMembrane(closestCSI,vt,cm,mi,(SinglePoint)curves[i]));
									break;
								}
							}
						}
					}
				}
			}			
		}
	}
	//
	if(spatialSelection.size() > 0){
		SpatialSelection[] ss = new SpatialSelection[spatialSelection.size()];
		spatialSelection.copyInto(ss);
		return ss;
	}
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (6/28/2003 4:57:18 PM)
 * @return cbit.vcell.simdata.gui.SpatialSelection[]
 */
public SpatialSelection[] fetchSpatialSelections(boolean isSpatial2D,boolean bIgnoreSelection,boolean bFetchOnlyVisible) {
	if(	getImagePlaneManagerPanel() != null &&
		getImagePlaneManagerPanel().getCurveRenderer() != null){
		return fetchSpatialSelections(
			(bIgnoreSelection || getImagePlaneManagerPanel().getCurveRenderer().getSelection() == null
			?
			null
			:
			getImagePlaneManagerPanel().getCurveRenderer().getSelection().getCurve()
			)
			,isSpatial2D,bFetchOnlyVisible);
	}
	return null;
}
/**
 * Insert the method's description here.
 * Creation date: (7/6/2003 7:42:59 PM)
 * @param curve cbit.vcell.geometry.Curve
 */
private void fireDataSamplers() {

	//fire "dataSamplers" if there are any
	boolean bTimeDataSamplerVisible = false;
	boolean bSpatialDataSamplerVisible = false;
	cbit.vcell.geometry.gui.CurveRenderer cr = getImagePlaneManagerPanel().getCurveRenderer();
	if(cr != null){
		Curve[] curves = getImagePlaneManagerPanel().getCurveRenderer().getAllCurves();
		if(curves != null && curves.length > 0){
			for(int i=0;i<curves.length;i+= 1){
				if(membranesAndIndexes == null || !membranesAndIndexes.containsKey(curves[i])){
					boolean isCurveValidDataSampler = isValidDataSampler(curves[i]);
					bTimeDataSamplerVisible = bTimeDataSamplerVisible || (isCurveValidDataSampler && curves[i] instanceof SinglePoint);
					bSpatialDataSamplerVisible =
							bSpatialDataSamplerVisible || 
							(isCurveValidDataSampler && !(curves[i] instanceof SinglePoint)) &&
							cr.getSelection() != null &&
							cr.getSelection().getCurve() == curves[i];
				}
			}
		}
	}
	//
	firePropertyChange("timeDataSamplers",!bTimeDataSamplerVisible,bTimeDataSamplerVisible);
	firePropertyChange("spatialDataSamplers",!bSpatialDataSamplerVisible,bSpatialDataSamplerVisible);
	
}
/**
 * Insert the method's description here.
 * Creation date: (4/26/2001 3:19:07 PM)
 * @return cbit.vcell.geometry.Curve[]
 */
public Curve[] getAllUserCurves() {
	return getImagePlaneManagerPanel().getCurveRenderer().getAllUserCurves();
}
/**
 * Insert the method's description here.
 * Creation date: (3/13/2001 12:53:10 PM)
 * @return java.lang.String
 * @param csi cbit.vcell.geometry.CurveSelectionInfo
 */
public String getCurveValue(cbit.vcell.geometry.CurveSelectionInfo csi) {
	String infoS = null;
	if (csi.getType() == cbit.vcell.geometry.CurveSelectionInfo.TYPE_SEGMENT) {
		if (membranesAndIndexes != null) {
			java.util.Enumeration<SampledCurve> keysEnum = membranesAndIndexes.keys();
			while (keysEnum.hasMoreElements()) {
				cbit.vcell.geometry.Curve curve = (cbit.vcell.geometry.Curve) keysEnum.nextElement();
				if(csi.getCurve() == curve){
					int[] membraneIndexes = (int[]) membranesAndIndexes.get(curve);
					if(meshDisplayAdapter != null){
						double[] membraneValues =
								meshDisplayAdapter.getDataValuesForMembraneIndexes(
									membraneIndexes,
									getPdeDataContext().getDataValues(),
									getPdeDataContext().getDataIdentifier().getVariableType());
						if(membraneValues != null){
							Coordinate segmentWC = getPdeDataContext().getCartesianMesh().getCoordinateFromMembraneIndex(membraneIndexes[csi.getSegment()]);
							String xCoordString = cbit.util.NumberUtils.formatNumber(segmentWC.getX());
							String yCoordString = cbit.util.NumberUtils.formatNumber(segmentWC.getY());
							String zCoordString = cbit.util.NumberUtils.formatNumber(segmentWC.getZ());
							infoS = "("+xCoordString+","+yCoordString+","+zCoordString+")  ["+
										membraneIndexes[csi.getSegment()]+"]  Value = " +
										membraneValues[csi.getSegment()];
							if(getDataInfoProvider() != null){
								DataViewer.MembraneDataInfo membraneDataInfo =
									getDataInfoProvider().getMembraneDataInfo(membraneIndexes[csi.getSegment()]);
								infoS+= " \""+membraneDataInfo.membraneName+"\"";
								infoS+= " mrID="+membraneDataInfo.membraneRegionID;
							}
							break;
						}
					}
				}
			}
		}
	}
	return infoS;
}
/**
 * Return the displayAdapterService1 property value.
 * @return cbit.image.DisplayAdapterService
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public cbit.image.DisplayAdapterService getdisplayAdapterService1() {
	// user code begin {1}
	// user code end
	return ivjdisplayAdapterService1;
}
/**
 * Return the displayAdapterServicePanel1 property value.
 * @return cbit.image.DisplayAdapterServicePanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private cbit.image.DisplayAdapterServicePanel getdisplayAdapterServicePanel1() {
	// user code begin {1}
	// user code end
	return ivjdisplayAdapterServicePanel1;
}
/**
 * Return the imagePlaneManager1 property value.
 * @return cbit.image.ImagePlaneManager
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private cbit.image.ImagePlaneManager getimagePlaneManager1() {
	// user code begin {1}
	// user code end
	return ivjimagePlaneManager1;
}
/**
 * Return the ImagePlaneManagerPanel1 property value.
 * @return cbit.image.ImagePlaneManagerPanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private cbit.image.ImagePlaneManagerPanel getImagePlaneManagerPanel() {
	if (ivjImagePlaneManagerPanel == null) {
		try {
			ivjImagePlaneManagerPanel = new cbit.image.ImagePlaneManagerPanel();
			ivjImagePlaneManagerPanel.setName("ImagePlaneManagerPanel");
			ivjImagePlaneManagerPanel.setMode(1);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	}
	return ivjImagePlaneManagerPanel;
}
/**
 * Insert the method's description here.
 * Creation date: (7/4/2003 6:10:48 PM)
 */
public cbit.vcell.geometry.CurveSelectionInfo getInitalCurveSelection(int tool, cbit.vcell.geometry.Coordinate wc) {
	//
	CurveSelectionInfo newCurveSelection = null;
	if(	getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.MEMBRANE) ||
		getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.MEMBRANE_REGION)){
		//
			CurveSelectionInfo[] closeCSI = getImagePlaneManagerPanel().getCurveRenderer().getCloseCurveSelectionInfos(wc);
			if(closeCSI != null){
				for(int i =0;i < closeCSI.length;i+= 1){
					if(membranesAndIndexes != null && membranesAndIndexes.containsKey(closeCSI[i].getCurve())){
						if (tool == cbit.vcell.geometry.gui.CurveEditorTool.TOOL_LINE) {
							newCurveSelection = new CurveSelectionInfo(new CurveSelectionCurve((SampledCurve)(closeCSI[i].getCurve())));
						}else if(tool == cbit.vcell.geometry.gui.CurveEditorTool.TOOL_POINT) {
							newCurveSelection = new CurveSelectionInfo(new SinglePoint());
						}
						break;
					}
				}
				
			}
	}
	if(newCurveSelection != null){
		if(membraneSamplerCurves == null){
			membraneSamplerCurves = new java.util.Vector<Curve>();
		}
		membraneSamplerCurves.add(newCurveSelection.getCurve());
	}
	return newCurveSelection;
}
/**
 * Gets the normalAxis property (int) value.
 * @return The normalAxis property value.
 * @see #setNormalAxis
 */
public int getNormalAxis() {
	return fieldNormalAxis;
}
/**
 * Gets the pdeDataContext property (cbit.vcell.simdata.PDEDataContext) value.
 * @return The pdeDataContext property value.
 * @see #setPdeDataContext
 */
public cbit.vcell.simdata.PDEDataContext getPdeDataContext() {
	return fieldPdeDataContext;
}
/**
 * Return the pdeDataContext1 property value.
 * @return cbit.vcell.simdata.PDEDataContext
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private cbit.vcell.simdata.PDEDataContext getpdeDataContext1() {
	// user code begin {1}
	// user code end
	return ivjpdeDataContext1;
}
/**
 * Gets the slice property (int) value.
 * @return The slice property value.
 */
public int getSlice() {
	return fieldSlice;
}
/**
 * Gets the spatialSelection property (cbit.vcell.simdata.gui.SpatialSelection) value.
 * @return The spatialSelection property value.
 */
public SpatialSelection getSpatialSelection() {
	return fieldSpatialSelection;
}
/**
 * Called whenever the part throws an exception.
 * @param exception java.lang.Throwable
 */
private void handleException(java.lang.Throwable exception) {

	/* Uncomment the following lines to print uncaught exceptions to stdout */
	System.out.println("--------- UNCAUGHT EXCEPTION ---------");
	exception.printStackTrace(System.out);
}
/**
 * Comment
 */
private void imagePlaneManagerPanel_PropertyChange(java.beans.PropertyChangeEvent propertyChangeEvent) {
	if(propertyChangeEvent.getSource() == getImagePlaneManagerPanel()){
		//These properties are from generic propertyChangeEvents from the ImagePlaneMangerPanel.DisplayAdapterService
		//These are necessary so that curves are updated before a repaint that redraws them
		if( propertyChangeEvent.getPropertyName().equals("valueDomain") ||
			propertyChangeEvent.getPropertyName().equals("activeScaleRange") ||
			propertyChangeEvent.getPropertyName().equals("activeColorModelID")){
				//System.out.println("PDEDataContextPanel<--ImagePlaneMangerPanel.propertyChange="+propertyChangeEvent.getPropertyName());
				refreshColorCurves();
			}
	}
}
/**
 * Initializes connections
 * @exception java.lang.Exception The exception description.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initConnections() throws java.lang.Exception {
	// user code begin {1}
	// user code end
	this.addPropertyChangeListener(ivjEventHandler);
	getImagePlaneManagerPanel().addPropertyChangeListener(ivjEventHandler);
	connPtoP1SetTarget();
	connPtoP2SetTarget();
	connPtoP3SetTarget();
	connPtoP4SetTarget();
	connPtoP5SetTarget();
	connPtoP6SetTarget();
}
/**
 * Comment
 */
private void initDisplayAdapterService(DisplayAdapterService das) {
	das.setValueDomain(null);
	das.addColorModelForValues(
		cbit.image.DisplayAdapterService.createGrayColorModel(), 
		cbit.image.DisplayAdapterService.createGraySpecialColors(),
		"Gray");
	das.addColorModelForValues(
		cbit.image.DisplayAdapterService.createBlueRedColorModel(),
		cbit.image.DisplayAdapterService.createBlueRedSpecialColors(),
		"BlueRed");
	das.setActiveColorModelID("BlueRed");
}
/**
 * Initialize the class.
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void initialize() {
	try {
		// user code begin {1}
		// user code end
		setName("PDEDataContextPanel2");
		setLayout(new java.awt.BorderLayout());
		setSize(578, 487);
		add(getImagePlaneManagerPanel(), "Center");
		initConnections();
		connEtoM1();
	} catch (java.lang.Throwable ivjExc) {
		handleException(ivjExc);
	}
	// user code begin {2}
	// user code end
}
/**
 * Comment
 */
private void initMeshDisplayAdapter() {
	if(getPdeDataContext() != null && getPdeDataContext().getCartesianMesh() != null){
		meshDisplayAdapter = new MeshDisplayAdapter(getPdeDataContext().getCartesianMesh());
	}else{
		meshDisplayAdapter = null;
	}
}
/**
 * Insert the method's description here.
 * Creation date: (7/4/2003 6:10:48 PM)
 */
public boolean isAddControlPointOK(int tool, cbit.vcell.geometry.Coordinate wc,Curve addedToThisCurve) {
	//
	cbit.vcell.geometry.gui.CurveRenderer curveR = getImagePlaneManagerPanel().getCurveRenderer();
	if(getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.VOLUME) ||
	getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.VOLUME_REGION)){
		return true;
	}

	if(getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.MEMBRANE) ||
	getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.MEMBRANE_REGION)){
		CurveSelectionInfo[] closeCSI = curveR.getCloseCurveSelectionInfos(wc);
		if(closeCSI != null && closeCSI.length > 0){
			for(int i=0;i<closeCSI.length;i+= 1){
				if(tool == cbit.vcell.geometry.gui.CurveEditorTool.TOOL_POINT && addedToThisCurve instanceof SinglePoint &&
					membranesAndIndexes != null && membranesAndIndexes.containsKey(closeCSI[i].getCurve())){
					return true;
				}else if(tool == cbit.vcell.geometry.gui.CurveEditorTool.TOOL_LINE &&
							addedToThisCurve instanceof CurveSelectionCurve){
					Curve sourceCurve = ((CurveSelectionCurve)(addedToThisCurve)).getSourceCurveSelectionInfo().getCurve();
					if(sourceCurve == closeCSI[i].getCurve()){
						if(membranesAndIndexes != null && membranesAndIndexes.containsKey(sourceCurve)){
							return true;
						}
					}
				}
			}
		}
	}
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (7/17/2003 8:32:10 AM)
 * @return boolean
 */
public boolean isSpatialSampling2D() {
	return !getImagePlaneManagerPanel().getSpatialProjectionJCheckBox().isSelected();
}
/**
 * Insert the method's description here.
 * Creation date: (7/6/2003 7:44:14 PM)
 * @return boolean
 * @param curve cbit.vcell.geometry.Curve
 */
private boolean isValidDataSampler(Curve curve) {
	if(!curve.isValid()){
		return false;
	}
	VariableType vt = getPdeDataContext().getDataIdentifier().getVariableType();
	boolean isCurveVisible = true;
	//
	if (vt.equals(cbit.vcell.simdata.VariableType.VOLUME) || vt.equals(cbit.vcell.simdata.VariableType.VOLUME_REGION)) {
			//
			if(membraneSamplerCurves != null && membraneSamplerCurves.contains(curve)){isCurveVisible = false;}
			else{isCurveVisible = true;}
			//
	} else if (vt.equals(cbit.vcell.simdata.VariableType.MEMBRANE) || vt.equals(cbit.vcell.simdata.VariableType.MEMBRANE_REGION)) {
			//
			if(membraneSamplerCurves != null && membraneSamplerCurves.contains(curve)){isCurveVisible = (fetchSpatialSelections(curve,false,false) != null);}
			else{isCurveVisible = false;}
			//
	}
	//
	return isCurveVisible;
}
/**
 * main entrypoint - starts the part when it is run as an application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	try {
		javax.swing.JFrame frame = new javax.swing.JFrame();
		PDEDataContextPanel aPDEDataContextPanel;
		aPDEDataContextPanel = new PDEDataContextPanel();
		frame.setContentPane(aPDEDataContextPanel);
		frame.setSize(aPDEDataContextPanel.getSize());
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			};
		});
		frame.setVisible(true);
		java.awt.Insets insets = frame.getInsets();
		frame.setSize(frame.getWidth() + insets.left + insets.right, frame.getHeight() + insets.top + insets.bottom);
		frame.setVisible(true);
	} catch (Throwable exception) {
		System.err.println("Exception occurred in main() of javax.swing.JPanel");
		exception.printStackTrace(System.out);
	}
}


/**
 * Insert the method's description here.
 * Creation date: (7/16/2003 1:05:56 PM)
 * @return cbit.vcell.geometry.ControlPointCurve
 * @param curve cbit.vcell.geometry.ControlPointCurve
 */
private ControlPointCurve projectCurveOntoSlice(ControlPointCurve curve) {
	//for SinglePoint(timepoint) and PolyLine(spatial) samplers(always stored in world coordinates),
	//convert the curve coordinates into view coordinates from the sliceviewer
	ControlPointCurve cpCurve = null;
	java.util.Vector<Coordinate> cpV = new java.util.Vector<Coordinate>();
	int normalAxis = getimagePlaneManager1().getNormalAxis();
	for(int i=0;i < curve.getControlPointCount();i+= 1){
		//convert curves that are always stored in world coordinates into coordinates that
		//represent how user sees them in the slice viewer
		double xCoord = Coordinate.convertAxisFromStandardXYZToNormal(curve.getControlPoint(i),Coordinate.X_AXIS,normalAxis);
		double yCoord = Coordinate.convertAxisFromStandardXYZToNormal(curve.getControlPoint(i),Coordinate.Y_AXIS,normalAxis);
		//Get z from slice
		double zCoord = Coordinate.convertAxisFromStandardXYZToNormal(getimagePlaneManager1().getWorldCoordinateFromUnitized2D(0,0),Coordinate.Z_AXIS,normalAxis);
		//These are now the real coordinates as they are viewed in the slice viewer
		//Coordinate newCoord = new Coordinate(xCoord,yCoord,zCoord);
		Coordinate newCoord = Coordinate.convertCoordinateFromNormalToStandardXYZ(xCoord,yCoord,zCoord,normalAxis);
		cpV.add(newCoord);
	}
	if(cpV.size() > 0){
		Coordinate[] cpArr = new Coordinate[cpV.size()];
		cpV.copyInto(cpArr);
		//Determine if curve has been projected down to a single point
		boolean bSinglePoint = true;
		for(int i=0;i<cpArr.length;i+= 1){
			if(i > 0 && !cpArr[i].equals(cpArr[i-1])){
				bSinglePoint = false;
				break;
			}
		}
		//if(curve instanceof SinglePoint){
		if(bSinglePoint){
			cpCurve = new SinglePoint(cpArr[0]);
		}else if(curve instanceof SampledCurve){
			cpCurve = new PolyLine(cpArr);
		}
	}
	return cpCurve;
}
/**
 * Insert the method's description here.
 * Creation date: (7/4/2003 6:10:48 PM)
 */
public boolean providesInitalCurve(int tool, cbit.vcell.geometry.Coordinate wc) {
	
	if(getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.MEMBRANE) ||
		getPdeDataContext().getDataIdentifier().getVariableType().equals(VariableType.MEMBRANE_REGION)){
			return true;
	}else{
		return false;
	}
}
/**
 * Insert the method's description here.
 * Creation date: (10/26/00 4:49:39 PM)
 */
private void refreshColorCurves() {
    if (getPdeDataContext() != null) {
        if (meshDisplayAdapter != null) {
            colorMembraneCurvesPrivate(membranesAndIndexes, meshDisplayAdapter);
            //colorCurvesPrivate(contoursAndValues,meshDisplayAdapter);
        }
    }
}
/**
 * Set the displayAdapterService1 to a new value.
 * @param newValue cbit.image.DisplayAdapterService
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
public void setdisplayAdapterService1(cbit.image.DisplayAdapterService newValue) {
	if (ivjdisplayAdapterService1 != newValue) {
		try {
			cbit.image.DisplayAdapterService oldValue = getdisplayAdapterService1();
			ivjdisplayAdapterService1 = newValue;
			connEtoC2(ivjdisplayAdapterService1);
			firePropertyChange("displayAdapterService1", oldValue, newValue);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	// user code begin {3}
	// user code end
}
/**
 * Set the displayAdapterServicePanel1 to a new value.
 * @param newValue cbit.image.DisplayAdapterServicePanel
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void setdisplayAdapterServicePanel1(cbit.image.DisplayAdapterServicePanel newValue) {
	if (ivjdisplayAdapterServicePanel1 != newValue) {
		try {
			ivjdisplayAdapterServicePanel1 = newValue;
			connPtoP6SetTarget();
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	// user code begin {3}
	// user code end
}
/**
 * Set the imagePlaneManager1 to a new value.
 * @param newValue cbit.image.ImagePlaneManager
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void setimagePlaneManager1(cbit.image.ImagePlaneManager newValue) {
	if (ivjimagePlaneManager1 != newValue) {
		try {
			/* Stop listening for events from the current object */
			if (ivjimagePlaneManager1 != null) {
				ivjimagePlaneManager1.removePropertyChangeListener(ivjEventHandler);
			}
			ivjimagePlaneManager1 = newValue;

			/* Listen for events from the new object */
			if (ivjimagePlaneManager1 != null) {
				ivjimagePlaneManager1.addPropertyChangeListener(ivjEventHandler);
			}
			connPtoP4SetTarget();
			connPtoP5SetTarget();
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	// user code begin {3}
	// user code end
}
/**
 * Sets the normalAxis property (int) value.
 * @param normalAxis The new value for the property.
 * @see #getNormalAxis
 */
public void setNormalAxis(int normalAxis) {
	int oldValue = fieldNormalAxis;
	fieldNormalAxis = normalAxis;
	firePropertyChange("normalAxis", new Integer(oldValue), new Integer(normalAxis));
}
/**
 * Sets the pdeDataContext property (cbit.vcell.simdata.PDEDataContext) value.
 * @param pdeDataContext The new value for the property.
 * @see #getPdeDataContext
 */
public void setPdeDataContext(cbit.vcell.simdata.PDEDataContext pdeDataContext) {
	cbit.vcell.simdata.PDEDataContext oldValue = fieldPdeDataContext;
	fieldPdeDataContext = pdeDataContext;
	firePropertyChange("pdeDataContext", oldValue, pdeDataContext);
}
/**
 * Set the pdeDataContext1 to a new value.
 * @param newValue cbit.vcell.simdata.PDEDataContext
 */
/* WARNING: THIS METHOD WILL BE REGENERATED. */
private void setpdeDataContext1(cbit.vcell.simdata.PDEDataContext newValue) {
	if (ivjpdeDataContext1 != newValue) {
		try {
			cbit.vcell.simdata.PDEDataContext oldValue = getpdeDataContext1();
			/* Stop listening for events from the current object */
			if (ivjpdeDataContext1 != null) {
				ivjpdeDataContext1.removePropertyChangeListener(ivjEventHandler);
			}
			ivjpdeDataContext1 = newValue;

			/* Listen for events from the new object */
			if (ivjpdeDataContext1 != null) {
				ivjpdeDataContext1.addPropertyChangeListener(ivjEventHandler);
			}
			connPtoP1SetSource();
			connEtoM2(ivjpdeDataContext1);
			connEtoC7(ivjpdeDataContext1);
			firePropertyChange("pdeDataContext", oldValue, newValue);
			// user code begin {1}
			// user code end
		} catch (java.lang.Throwable ivjExc) {
			// user code begin {2}
			// user code end
			handleException(ivjExc);
		}
	};
	// user code begin {3}
	// user code end
}
/**
 * Sets the slice property (int) value.
 * @param slice The new value for the property.
 * @see #getSlice
 */
public void setSlice(int slice) {
	int oldValue = fieldSlice;
	fieldSlice = slice;
	firePropertyChange("slice", new Integer(oldValue), new Integer(slice));
}
/**
 * Sets the pdeDataContext property (cbit.vcell.simdata.PDEDataContext) value.
 * @param pdeDataContext The new value for the property.
 * @see #getPdeDataContext
 */
private void setSpatialSelection(SpatialSelection spatialSelection) {
	SpatialSelection oldValue = fieldSpatialSelection;
	fieldSpatialSelection = spatialSelection;
	firePropertyChange("spatialSelection", oldValue, spatialSelection);
}
/**
 * Comment
 */
private void slice2dEnable() {
	boolean bSpatProj =
		getpdeDataContext1() != null &&
		getpdeDataContext1().getSourceDataInfo() != null &&
		getpdeDataContext1().getSourceDataInfo().getZSize() > 1;

	//getImagePlaneManagerPanel().getSpatialProjectionJCheckBox().setSelected(!bSpatProj);
	getImagePlaneManagerPanel().getSpatialProjectionJCheckBox().setEnabled(bSpatProj);
}
/**
 * Insert the method's description here.
 * Creation date: (11/9/2000 4:14:39 PM)
 */
private void updateContours() {
	//
	if (getPdeDataContext() == null ||
			getPdeDataContext().getDataIdentifier() == null) {
		return;
	}
	//Remove previous curves
	if (contoursAndValues != null) {
		java.util.Enumeration<SampledCurve> keysEnum = contoursAndValues.keys();
		while (keysEnum.hasMoreElements()) {
			cbit.vcell.geometry.Curve curve = (cbit.vcell.geometry.Curve) keysEnum.nextElement();
			getImagePlaneManagerPanel().getCurveRenderer().removeCurve(curve);
		}
	}
	//
	contoursAndValues = null;
	boolean hasValues = false;
	if(meshDisplayAdapter != null){
		if (getPdeDataContext().getDataIdentifier().getVariableType().equals(cbit.vcell.simdata.VariableType.VOLUME)) {
			//Get curves with no values for overlay on volume Data
			contoursAndValues = meshDisplayAdapter.getCurvesFromContours(null);
		} else if (getPdeDataContext().getDataIdentifier().getVariableType().equals(cbit.vcell.simdata.VariableType.CONTOUR)) {
			//get curves with values
			contoursAndValues = meshDisplayAdapter.getCurvesFromContours(getPdeDataContext().getDataValues());
			hasValues = true;
		}
	}
	//Add new curves to curveRenderer
	if (contoursAndValues != null) {
		java.util.Enumeration<SampledCurve> keysEnum = contoursAndValues.keys();
		while (keysEnum.hasMoreElements()) {
			cbit.vcell.geometry.Curve curve = (cbit.vcell.geometry.Curve) keysEnum.nextElement();
			//
			getImagePlaneManagerPanel().getCurveRenderer().addCurve(curve);
			//
			getImagePlaneManagerPanel().getCurveRenderer().renderPropertyEditable(curve, false);
			getImagePlaneManagerPanel().getCurveRenderer().renderPropertySelectable(curve, hasValues);
			if (hasValues) {
				getImagePlaneManagerPanel().getCurveRenderer().renderPropertySubSelectionType(curve, cbit.vcell.geometry.gui.CurveRenderer.SUBSELECTION_SEGMENT);
			} else {
				getImagePlaneManagerPanel().getCurveRenderer().renderPropertyLineWidthMultiplier(curve, 3);
			}
		}
	}

}
/**
 * Insert the method's description here.
 * Creation date: (10/22/00 3:15:49 PM)
 */
private void updateMembraneCurves() {
	//
	if (getPdeDataContext() == null ||
			getPdeDataContext().getDataIdentifier() == null) {
		return;
	}
	int normalAxis = getImagePlaneManagerPanel().getImagePlaneManager().getNormalAxis();
	int slice = getImagePlaneManagerPanel().getImagePlaneManager().getSlice();
	//Remove previous curves
	if (membranesAndIndexes != null) {
		java.util.Enumeration<SampledCurve> keysEnum = membranesAndIndexes.keys();
		while (keysEnum.hasMoreElements()) {
			cbit.vcell.geometry.Curve curve = (cbit.vcell.geometry.Curve) keysEnum.nextElement();
			getImagePlaneManagerPanel().getCurveRenderer().removeCurve(curve);
		}
	}
	//
	membranesAndIndexes = null;
	boolean hasValues = false;
	if(meshDisplayAdapter != null){
		//Get new curves for slice and normalAxis
		if (getPdeDataContext().getDataIdentifier().getVariableType().equals(cbit.vcell.simdata.VariableType.VOLUME) ||
			getPdeDataContext().getDataIdentifier().getVariableType().equals(cbit.vcell.simdata.VariableType.VOLUME_REGION)) {
			//Turn off showing Membrane values over mouse
			//getImagePlaneManagerPanel().setCurveValueProvider(null);
			//
			//GET CURVES WITH NO VALUES FOR OVERLAY ON VOLUME DATA
			membranesAndIndexes = meshDisplayAdapter.getCurvesAndMembraneIndexes(normalAxis, slice);
		} else if (getPdeDataContext().getDataIdentifier().getVariableType().equals(cbit.vcell.simdata.VariableType.MEMBRANE)) {
			//Turn on showing Membrane values over mouse
			//getImagePlaneManagerPanel().setCurveValueProvider(this);
			//
			//GET CURVES WITH VALUES
			membranesAndIndexes = meshDisplayAdapter.getCurvesAndMembraneIndexes(normalAxis, slice);
			hasValues = true;
		} else if (getPdeDataContext().getDataIdentifier().getVariableType().equals(cbit.vcell.simdata.VariableType.MEMBRANE_REGION)) {
			//Turn on showing Membrane values over mouse
			//getImagePlaneManagerPanel().setCurveValueProvider(this);
			//
			//GET CURVES WITH REGIONIDS
			membranesAndIndexes = meshDisplayAdapter.getCurvesAndMembraneIndexes(normalAxis, slice);
			/*
			//RegionID values
			double[] regionValues = getPdeDataContext().getDataValues();
			java.util.Iterator regionIDCurveValues = membranesAndValues.values().iterator();
			//convert RegionID to value
			while(regionIDCurveValues.hasNext()){
				java.util.Vector regionIDValues = (java.util.Vector)regionIDCurveValues.next();
				for(int i = 0;i < regionIDValues.size();i+= 1){
					int regionID = (int)(((Double)(regionIDValues.elementAt(i))).doubleValue());
					Double decodedRegionValue = new Double(regionValues[regionID]);
					regionIDValues.setElementAt(decodedRegionValue,i);
				}
			}
			*/
			hasValues = true;
		}
	}
	//Add new curves to curveRenderer
	if (membranesAndIndexes != null) {
		java.util.Enumeration<SampledCurve> keysEnum = membranesAndIndexes.keys();
		while (keysEnum.hasMoreElements()) {
			cbit.vcell.geometry.Curve curve = (cbit.vcell.geometry.Curve) keysEnum.nextElement();
			//
			getImagePlaneManagerPanel().getCurveRenderer().addCurve(curve);
			//
			getImagePlaneManagerPanel().getCurveRenderer().renderPropertyEditable(curve, false);
			getImagePlaneManagerPanel().getCurveRenderer().renderPropertySelectable(curve, false);
			if(!hasValues){getImagePlaneManagerPanel().getCurveRenderer().renderPropertyLineWidthMultiplier(curve,3);}
			//getImagePlaneManagerPanel().getCurveRenderer().renderPropertySelectable(curve, hasValues);
			//if (hasValues) {
				//getImagePlaneManagerPanel().getCurveRenderer().renderPropertySubSelectionType(curve, cbit.vcell.geometry.gui.CurveRenderer.SUBSELECTION_SEGMENT);
			//}else{
				//getImagePlaneManagerPanel().getCurveRenderer().renderPropertyLineWidthMultiplier(curve,3);
			//}
		}
	}
	//
	refreshColorCurves();
	//
	//Set visibility of curve samplers
	cbit.vcell.geometry.gui.CurveRenderer cr = getImagePlaneManagerPanel().getCurveRenderer();
	if(cr != null){
		Curve[] curves = getImagePlaneManagerPanel().getCurveRenderer().getAllCurves();
		if(curves != null && curves.length > 0){
			for(int i=0;i<curves.length;i+= 1){
				if(membranesAndIndexes == null || !membranesAndIndexes.containsKey(curves[i])){
					boolean isCurveValidDataSampler = isValidDataSampler(curves[i]);
					cr.renderPropertyVisible(curves[i],isCurveValidDataSampler);
					cr.renderPropertyEditable(curves[i],(membraneSamplerCurves != null && membraneSamplerCurves.contains(curves[i])?false:true));
				}
			}
		}
	}
	//See if we should keep selection
	if(getImagePlaneManagerPanel().getCurveRenderer().getSelection() != null){
		CurveSelectionInfo csi = getImagePlaneManagerPanel().getCurveRenderer().getSelection();
		getImagePlaneManagerPanel().getCurveRenderer().selectNothing();
		if(isValidDataSampler(csi.getCurve())){
			getImagePlaneManagerPanel().getCurveRenderer().setSelection(csi);
		}
	}
	//
	//
	fireDataSamplers();
}
private DataViewer.DataInfoProvider getDataInfoProvider() {
	return dataInfoProvider;
}
public void setDataInfoProvider(DataViewer.DataInfoProvider dataInfoProvider) {
	this.dataInfoProvider = dataInfoProvider;
	getImagePlaneManagerPanel().setDataInfoProvider(getDataInfoProvider());
}
}
