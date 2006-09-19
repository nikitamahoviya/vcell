package cbit.vcell.solvers;
import cbit.gui.PropertyLoader;
import cbit.util.SessionLog;
import cbit.vcell.math.OutsideVariable;
import cbit.vcell.math.InsideVariable;
import cbit.vcell.math.FilamentVariable;
import cbit.vcell.math.FilamentRegionVariable;
import cbit.vcell.math.MemVariable;
import cbit.vcell.math.VariableType;
import cbit.vcell.math.VolVariable;
import cbit.vcell.math.VolumeRegionVariable;
import cbit.vcell.math.MembraneRegionVariable;
import cbit.vcell.math.Variable;
import cbit.vcell.math.AnnotatedFunction;
import cbit.vcell.parser.Expression;
import cbit.vcell.math.Function;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import cbit.vcell.solver.*;
import java.io.*;
import java.util.*;
/**
 * This interface was generated by a SmartGuide.
 * 
 */
public class FVSolver extends AbstractCompiledSolver implements Solver {
	private CppCoderVCell cppCoderVCell = null;
	private Thread thread = null;
	private boolean bInitializing = true;

/**
 * This method was created by a SmartGuide.
 * @param mathDesc cbit.vcell.math.MathDescription
 * @param platform cbit.vcell.solvers.Platform
 * @param directory java.lang.String
 * @param simID java.lang.String
 * @param clientProxy cbit.vcell.solvers.ClientProxy
 */
public FVSolver (SimulationJob argSimulationJob, File dir, SessionLog sessionLog) throws SolverException {
	super(argSimulationJob, dir, sessionLog);
	if (! getSimulation().getIsSpatial()) {
		throw new SolverException("Cannot use FVSolver on non-spatial simulation");
	}
	this.cppCoderVCell = new CppCoderVCell((new File(getBaseName())).getName(), getSaveDirectory(), argSimulationJob);
}


/**
 * This method was created by a SmartGuide.
 */
private void autoCode(boolean bNoCompile) throws SolverException {
	getSessionLog().print("LocalMathController.autoCode()");
	setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "initializing coder"));
	fireSolverStarting("generating code...");
	
	String baseName = new File(getSaveDirectory(), cppCoderVCell.getBaseFilename()).getPath();

	String Compile = System.getProperty(PropertyLoader.compilerProperty);                 // "cl /c";
	String Link = System.getProperty(PropertyLoader.linkerProperty);                      // "cl";
	String exeOutputSpecifier = System.getProperty(PropertyLoader.exeOutputProperty);     // "/Fe";
	String objOutputSpecifier = System.getProperty(PropertyLoader.objOutputProperty);     // "/Fo";
	String compileFlags = System.getProperty(PropertyLoader.includeProperty)+" "+
							System.getProperty(PropertyLoader.definesProperty);           // "/I"+includeDir+" /DWIN32 /DDEBUG";
	String CodeFilename = baseName+System.getProperty(PropertyLoader.srcsuffixProperty);  // ".cpp";
	String libs = System.getProperty(PropertyLoader.libsProperty);                        // libraryDir+"VCLIB.lib";
	String exeSuffix = System.getProperty(PropertyLoader.exesuffixProperty);              // ".exe";
	String HeaderFilename = baseName+".h";
	String ExeFilename = baseName+exeSuffix;
	String ObjFilename = baseName+System.getProperty(PropertyLoader.objsuffixProperty);

	// ** Dumping the functions of a simulation into a '.functions' file.
	String functionFileName = baseName+".functions";
	AnnotatedFunction[] annotatedFunctionList = createAnnotatedFunctionsList(getSimulation());
	FunctionFileGenerator functionFileGenerator = new FunctionFileGenerator(functionFileName, annotatedFunctionList);

	try {
		functionFileGenerator.generateFunctionFile();		
	}catch (Exception e){
		e.printStackTrace(System.out);
		throw new RuntimeException("autocode exception: Error creating .function file for "+functionFileGenerator.getBasefileName()+e.getMessage());
	}		
	
	try {
		cppCoderVCell.initialize();
	}catch (Exception e){
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_ABORTED, "autocode init exception: "+e.getMessage()));
		e.printStackTrace(System.out);
		throw new SolverException("autocode init exception: "+e.getMessage());
	}		
	setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "generating code"));
	
	java.io.FileOutputStream osCode = null;
	java.io.FileOutputStream osHeader = null;
	try {
		osCode = new java.io.FileOutputStream(CodeFilename);
	}catch (java.io.IOException e){
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_ABORTED, "error opening code file '"+CodeFilename+": "+e.getMessage()));
		e.printStackTrace(System.out);
		throw new SolverException("error opening code file '"+CodeFilename+": "+e.getMessage());
	}		
	
	try {
		osHeader = new java.io.FileOutputStream(HeaderFilename);
	}catch (java.io.IOException e){
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_ABORTED, "error opening header file '"+HeaderFilename+": "+e.getMessage()));
		e.printStackTrace(System.out);
		throw new SolverException("error opening header file '"+HeaderFilename+": "+e.getMessage());
	}		
	
	try {
		cppCoderVCell.code(osHeader,osCode);
		osCode.close();
		osHeader.close();
	}catch (Exception e){
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_ABORTED, "autocode coding exception: "+e.getMessage()));
		e.printStackTrace(System.out);
		throw new SolverException("autocode coding exception: "+e.getMessage());
	}	
	
	if (bNoCompile){
		return;
	}	
	
	setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "compiling code"));
	fireSolverStarting("compiling code...");
	try {		
		String compileCommand = Compile+" "+CodeFilename+" "+compileFlags+" "+objOutputSpecifier+ObjFilename;
System.out.println(compileCommand);
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "% "+compileCommand));
		
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(compileCommand);

		String stdoutString = "";
		String stderrString = "";

		InputStream inputStream = process.getInputStream();
		if (inputStream!=null){
			char charArray[] = new char[1000];
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			int numRead = inputStreamReader.read(charArray,0,charArray.length);
			if (numRead>0){
				stdoutString += new String(charArray,0,numRead);
				if (numRead == charArray.length){
					stdoutString += "\n(standard output truncated...)";
				}	
			}
			inputStreamReader.close();
		}	
		
		inputStream = process.getErrorStream();
		if (inputStream!=null){
			char charArray[] = new char[1000];
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			int numRead = inputStreamReader.read(charArray,0,charArray.length);
			if (numRead>0){
				stderrString += new String(charArray,0,numRead);
				if (numRead == charArray.length){
					stderrString += "\n(standard output truncated...)";
				}	
			}
			inputStreamReader.close();	
		}	
		
		try {
			process.waitFor();
//			throw new RemoteException("didn't wait for process");
		}catch (InterruptedException e){
		}	
		int retcode = 0;
		retcode = process.exitValue();
		if (retcode == 0){
			setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "compilation successful, return code = "+retcode));	
		}else{
			getSessionLog().print("stderr:\n"+stderrString);
			getSessionLog().print("stdout:\n"+stdoutString);
			throw new SolverException("compilation failed, return code = "+retcode+"\n"+stderrString);
		}		
		process = null;
		
	}catch (Exception e){
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_ABORTED, "error compiling: "+e.getMessage()));
		e.printStackTrace(System.out);
		throw new SolverException("error compiling: "+e.getMessage());		
	}

	
	setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "linking code"));
	fireSolverStarting("linking code...");
	try {		
		String linkCommand = Link+" "+exeOutputSpecifier+ExeFilename+" "+ObjFilename+" "+libs;
System.out.println(linkCommand);
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "% "+linkCommand));
		
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(linkCommand);

		String stdoutString = "";
		String stderrString = "";

		InputStream inputStream = process.getInputStream();
		if (inputStream!=null){
			char charArray[] = new char[1000];
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			int numRead = inputStreamReader.read(charArray,0,charArray.length);
			if (numRead>0){
				stdoutString += new String(charArray,0,numRead);
				if (numRead == charArray.length){
					stdoutString += "\n(standard output truncated...)";
				}	
			}
			inputStreamReader.close();
		}	
		
		inputStream = process.getErrorStream();
		if (inputStream!=null){
			char charArray[] = new char[1000];
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			int numRead = inputStreamReader.read(charArray,0,charArray.length);
			if (numRead>0){
				stderrString += new String(charArray,0,numRead);
				if (numRead == charArray.length){
					stderrString += "\n(standard output truncated...)";
				}	
			}
			inputStreamReader.close();	
		}	
		
		try {
			process.waitFor();
//			throw new RemoteException("didn't wait for process");
		}catch (InterruptedException e){
		}	
		int retcode = 0;
		retcode = process.exitValue();
		if (retcode == 0){
			setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "link successful, return code = "+retcode));	
		}else{
			getSessionLog().print("stderr:\n"+stderrString);
			getSessionLog().print("stdout:\n"+stdoutString);
			throw new SolverException("link failed, return code = "+retcode);
		}		
		process = null;
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "compile/link complete"));
		
	}catch (Exception e){
		setSolverStatus(new SolverStatus(SolverStatus.SOLVER_ABORTED, "error linking: "+e.getMessage()));
		e.printStackTrace(System.out);
		throw new SolverException("error linking: "+e.getMessage());		
	}	
}


/**
 * Insert the method's description here.
 * Creation date: (12/9/2002 4:53:30 PM)
 */
public void cleanup() {
	// nothing special needed
}


/**
 * Insert the method's description here.
 * Creation date: (2/2/2004 5:31:41 PM)
 * @return cbit.vcell.simdata.AnnotatedFunction[]
 * @param simulation cbit.vcell.solver.Simulation
 */
public static AnnotatedFunction[] createAnnotatedFunctionsList(Simulation simulation) {
	Function[] functions = simulation.getFunctions();

	// Get the list of (volVariables) in the simulation. Needed to determine 'type' of  functions
	Variable[] allVariables = simulation.getVariables();
	Vector varVector = new Vector();
	for (int i = 0; i < allVariables.length; i++){
		if ( (allVariables[i] instanceof VolVariable) || (allVariables[i] instanceof VolumeRegionVariable) || (allVariables[i] instanceof MemVariable) || 
			 (allVariables[i] instanceof MembraneRegionVariable) || (allVariables[i] instanceof FilamentVariable) || (allVariables[i] instanceof FilamentRegionVariable) ||
			 (allVariables[i] instanceof InsideVariable) || (allVariables[i] instanceof OutsideVariable) ) {
				 
			varVector.addElement(allVariables[i]);
		}
	}
	Variable[] variables = (Variable[])cbit.util.BeanUtils.getArray(varVector, Variable.class);
	String[] variableNames = new String[variables.length];
	for (int i = 0; i < variableNames.length; i++){
		variableNames[i] = variables[i].getName();
	}

	// Lookup table for variableType for each variable in 'variables' array.	
	VariableType[] variableTypes = new VariableType[variables.length];
	for (int i = 0; i < variables.length; i++){
		if (variables[i] instanceof VolVariable) {
			variableTypes[i] = VariableType.VOLUME;
		} else if (variables[i] instanceof VolumeRegionVariable) {
			variableTypes[i] = VariableType.VOLUME_REGION;
		} else if (variables[i] instanceof MemVariable) {
			variableTypes[i] = VariableType.MEMBRANE;
		} else if (variables[i] instanceof MembraneRegionVariable) {
			variableTypes[i] = VariableType.MEMBRANE_REGION;
		} else if (variables[i] instanceof FilamentVariable) {
			variableTypes[i] = VariableType.CONTOUR;
		} else if (variables[i] instanceof FilamentRegionVariable) {
			variableTypes[i] = VariableType.CONTOUR_REGION;
		} else if (variables[i] instanceof InsideVariable) {
			variableTypes[i] = VariableType.MEMBRANE;
		} else if (variables[i] instanceof OutsideVariable) {
			variableTypes[i] = VariableType.MEMBRANE;
		} else {
			variableTypes[i] = null;
		}
	}

	//
	// Bind and substitute functions to simulation before storing them in the '.functions' file
	//
	Vector annotatedFunctionVector = new Vector();
	for (int i = 0; i < functions.length; i++){
		if (isFunctionSaved(functions[i])) {
			String errString = "";
			VariableType funcType = null;		
			try {
				Expression substitutedExp = simulation.substituteFunctions(functions[i].getExpression());
				substitutedExp.bindExpression(simulation);
				functions[i].setExpression(substitutedExp.flatten());
			}catch (cbit.vcell.math.MathException e){
				e.printStackTrace(System.out);
				errString = errString+", "+e.getMessage();	
				// throw new RuntimeException(e.getMessage());
			}catch (cbit.vcell.parser.ExpressionException e){
				e.printStackTrace(System.out);
				errString = errString+", "+e.getMessage();				
				// throw new RuntimeException(e.getMessage());
			}

			//
			// get function's data type from the types of it's identifiers
			//
			funcType = getFunctionVariableType(functions[i], variableNames, variableTypes, simulation.getIsSpatial());

			AnnotatedFunction annotatedFunc = new AnnotatedFunction(functions[i].getName(), functions[i].getExpression(), errString, funcType, false);
			annotatedFunctionVector.addElement(annotatedFunc);
		}
	}

	AnnotatedFunction[] annotatedFunctionList = (AnnotatedFunction[])cbit.util.BeanUtils.getArray(annotatedFunctionVector, AnnotatedFunction.class);
	return annotatedFunctionList;	
}


/**
 * Insert the method's description here.
 * Creation date: (6/27/01 3:25:11 PM)
 * @return cbit.vcell.solvers.ApplicationMessage
 * @param message java.lang.String
 */
public ApplicationMessage getApplicationMessage(String message) {
	//
	// "data:iteration:time"  .... sent every time data written for FVSolver
	// "progress:xx.x%"        .... sent every 1% for FVSolver
	//
	//
	String SEPARATOR = ":";
	String DATA_PREFIX = "data:";
	String PROGRESS_PREFIX = "progress:";
	if (message.startsWith(DATA_PREFIX)){
		double timepoint = Double.parseDouble(message.substring(message.lastIndexOf(SEPARATOR)+1));
		setCurrentTime(timepoint);
		return new ApplicationMessage(ApplicationMessage.DATA_MESSAGE,getProgress(),timepoint,null,message);
	}else if (message.startsWith(PROGRESS_PREFIX)){
		String progressString = message.substring(message.lastIndexOf(SEPARATOR)+1,message.indexOf("%"));
		double progress = Double.parseDouble(progressString)/100.0;
		double startTime = getSimulation().getSolverTaskDescription().getTimeBounds().getStartingTime();
		double endTime = getSimulation().getSolverTaskDescription().getTimeBounds().getEndingTime();
		setCurrentTime(startTime + (endTime-startTime)*progress);
		return new ApplicationMessage(ApplicationMessage.PROGRESS_MESSAGE,progress,-1,null,message);
	}else{
		throw new RuntimeException("unrecognized message");
	}
}


/**
 * Insert the method's description here.
 * Creation date: (4/17/2001 8:47:08 AM)
 * @return java.lang.String
 */
public static String getDescription() {
	return "Finite Volume, Structured Grid";
}


/**
 * Insert the method's description here.
 * Creation date: (2/19/2004 11:17:15 AM)
 * @return cbit.vcell.simdata.VariableType
 * @param function cbit.vcell.math.Function
 * @param variableNames java.lang.String[]
 * @param variableTypes cbit.vcell.simdata.VariableType[]
 */
public static VariableType getFunctionVariableType(Function function, String[] variableNames, VariableType[] variableTypes, boolean isSpatial) {
	VariableType funcType = null;
	Expression exp = function.getExpression();
	String symbols[] = exp.getSymbols();
	if (symbols != null) {
		for (int j = 0; j < symbols.length; j++){
			boolean bFound = false;
			for (int k = 0; !bFound && k < variableNames.length; k++){
				if (symbols[j].equals(variableNames[k])) {
					bFound = true;
					if (funcType == null){
						funcType = variableTypes[k];
					}else{
						//
						// example: if VOLUME_REGION and VOLUME data are used in same function,
						// then function must be evaluated at each volume index (hence VOLUME wins).
						//
						if (variableTypes[k].isExpansionOf(funcType)){
							funcType = variableTypes[k];
						}
					}
				}
				if (symbols[j].equals(variableNames[k]+"_INSIDE") || symbols[j].equals(variableNames[k]+"_OUTSIDE")){
					bFound=true;
					if (variableTypes[k].equals(VariableType.VOLUME)){
						funcType = VariableType.MEMBRANE;
					}else if (funcType == null && variableTypes[k].equals(VariableType.VOLUME_REGION)){
						funcType = VariableType.MEMBRANE_REGION;
					}
				}
			}
		}
	}
	//
	// if determined to be a volume region or membrane region function, 
	// then if it is an explicit function of space, promote type to corresponding non-region type (e.g. volRegion --> volume)
	//
	boolean bExplicitFunctionOfSpace = false;
	if (symbols != null) {
		for (int i = 0; i < symbols.length; i++){
			if (symbols[i].equals(cbit.vcell.math.ReservedVariable.X.toString()) ||
				symbols[i].equals(cbit.vcell.math.ReservedVariable.Y.toString()) ||
				symbols[i].equals(cbit.vcell.math.ReservedVariable.Z.toString())){
				bExplicitFunctionOfSpace = true;
			}
		}
	}

		
	if (funcType == null){
		//
		// set default VariableType's for functions that have no variables (best guess).
		//
		if (!isSpatial) {
			funcType = VariableType.NONSPATIAL;
		} else {
			funcType = VariableType.VOLUME;
		}
	}else{
		if (funcType.equals(VariableType.MEMBRANE_REGION) && bExplicitFunctionOfSpace){
			funcType = VariableType.MEMBRANE;
		}else if (funcType.equals(VariableType.VOLUME_REGION) && bExplicitFunctionOfSpace){
			funcType = VariableType.VOLUME;
		}else if (funcType.equals(VariableType.CONTOUR_REGION) && bExplicitFunctionOfSpace){
			funcType = VariableType.CONTOUR;
		}
	}
	return funcType;
}


/**
 * This method was created by a SmartGuide.
 */
public void initialize() throws SolverException {
	long tm = System.currentTimeMillis();
	fireSolverStarting("processing geometry...");
	try {	
		cbit.vcell.geometry.Geometry geo = getSimulation().getMathDescription().getGeometry();
		PrintWriter pw = new PrintWriter(new FileWriter(new File(getSaveDirectory(), cppCoderVCell.getBaseFilename()+".vcg")));
		cbit.vcell.geometry.surface.GeometryFileWriter.write(geo, getSimulation().getMeshSpecification().getSamplingSize(),pw);
		pw.close();
	} catch (Exception ex) {
		throw new SolverException(ex.getMessage());
	}
	System.out.println("Writing geometry file takes " + (System.currentTimeMillis() - tm)/1000.0 + " sec");
	
	setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING, "PDESolver initializing"));
	fireSolverStarting("PDESolver initializing...");
	
	bInitializing = true;

	autoCode(false);
	
	String baseName = cppCoderVCell.getBaseFilename();
	String exeSuffix = System.getProperty(PropertyLoader.exesuffixProperty); // ".exe";
	File exeFile = new File(getSaveDirectory(), baseName + exeSuffix);
	boolean bCORBA = false;

	setSolverStatus(new SolverStatus(SolverStatus.SOLVER_RUNNING,"PDESolver starting"));
	
	try{
		bCORBA = Boolean.getBoolean(PropertyLoader.corbaEnabled);
	}catch (Throwable t){}

	if (bCORBA) {
		throw new RuntimeException("MathExecutableCORBA not supported");
		//executable = new MathExecutableCORBA(exeFile,mathDesc.getSimulationID(),getSessionLog());
	}else{
		setMathExecutable(new MathExecutable(exeFile));
	}

}


/**
 * Insert the method's description here.
 * Creation date: (6/27/2001 2:33:03 PM)
 */
public void propertyChange(java.beans.PropertyChangeEvent event) {
	super.propertyChange(event);
	
	if (event.getSource() == getMathExecutable() && event.getPropertyName().equals("applicationMessage")) {
		String messageString = (String)event.getNewValue();
		if (messageString==null || messageString.length()==0){
			return;
		}
		ApplicationMessage appMessage = getApplicationMessage(messageString);
		if (appMessage!=null && appMessage.getMessageType() == ApplicationMessage.DATA_MESSAGE) {
			fireSolverPrinted(appMessage.getTimepoint());
		}
	}
}
}