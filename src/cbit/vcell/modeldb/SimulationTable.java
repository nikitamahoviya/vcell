/*
 * Copyright (C) 1999-2011 University of Connecticut Health Center
 *
 * Licensed under the MIT License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/mit-license.php
 */

package cbit.vcell.modeldb;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.vcell.util.BeanUtils;
import org.vcell.util.CommentStringTokenizer;
import org.vcell.util.DataAccessException;
import org.vcell.util.ISize;
import org.vcell.util.SessionLog;
import org.vcell.util.TokenMangler;
import org.vcell.util.document.KeyValue;
import org.vcell.util.document.SimulationVersion;
import org.vcell.util.document.User;
import org.vcell.util.document.VCellSoftwareVersion;
import org.vcell.util.document.Version;
import org.vcell.util.document.VersionInfo;
import org.vcell.util.document.VersionableType;

import cbit.sql.Field;
import cbit.sql.QueryHashtable;
import cbit.sql.Table;
import cbit.vcell.math.MathDescription;
import cbit.vcell.solver.DataProcessingInstructions;
import cbit.vcell.solver.MeshSpecification;
import cbit.vcell.solver.Simulation;
import cbit.vcell.solver.SimulationInfo;
import cbit.vcell.solver.SolverResultSetInfo;
import cbit.vcell.solver.SolverTaskDescription;
import cbit.vcell.solver.VCSimulationDataIdentifier;
import cbit.vcell.solver.VCSimulationIdentifier;
/**
 * This type was created in VisualAge.
 */
public class SimulationTable extends VersionTable {
	private static final String TABLE_NAME = "vc_simulation";
	public static final String REF_TYPE = "REFERENCES " + TABLE_NAME + "(" + Table.id_ColumnName + ")";

	public final Field mathRef				= new Field("mathRef",			"integer",			"NOT NULL "+MathDescTable.REF_TYPE);
	public final Field mathOverridesOrig	= new Field("mathOverrides",	"CLOB",				"");
	public final Field mathOverridesLarge	= new Field("mathOverridesLRG",	"CLOB",				"");
	public final Field mathOverridesSmall	= new Field("mathOverridesSML",	"VARCHAR2(4000)",	"");
	public final Field taskDescription		= new Field("taskDesc",			"VARCHAR2(4000)",	"");
	public final Field meshSpecX			= new Field("meshspecx",		"integer",			"");
	public final Field meshSpecY			= new Field("meshspecy",		"integer",			"");
	public final Field meshSpecZ			= new Field("meshspecz",		"integer",			"");
	public final Field dataProcInstr		= new Field("dataProcInstr",	"VARCHAR2(4000)",	"");
	
	private final Field fields[] = {mathRef,mathOverridesOrig,mathOverridesLarge,mathOverridesSmall,taskDescription,meshSpecX,meshSpecY,meshSpecZ,dataProcInstr };
	
	public static final SimulationTable table = new SimulationTable();

/**
 * ModelTable constructor comment.
 */
private SimulationTable() {
	super(TABLE_NAME,SimulationTable.REF_TYPE,true);
	addFields(fields);
}
/**
 * This method was created in VisualAge.
 * @return cbit.vcell.geometry.GeometryInfo
 * @param rset java.sql.ResultSet
 * @param log cbit.vcell.server.SessionLog
 */
public VersionInfo getInfo(ResultSet rset,Connection con,SessionLog log) throws SQLException,DataAccessException {
	
	KeyValue mathRef = new KeyValue(rset.getBigDecimal(SimulationTable.table.mathRef.toString()));
	java.math.BigDecimal groupid = rset.getBigDecimal(VersionTable.privacy_ColumnName);
	Version version = getVersion(rset,DbDriver.getGroupAccessFromGroupID(con,groupid),log);
	SimulationVersion simulationVersion = (SimulationVersion)version;
	String softwareVersion = rset.getString(SoftwareVersionTable.table.softwareVersion.toString());
	
	return new SimulationInfo(mathRef,simulationVersion,VCellSoftwareVersion.fromString(softwareVersion));
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getInfoSQL(User user,String extraConditions,String special) {
	
	UserTable userTable = UserTable.table;
	SimulationTable vTable = SimulationTable.table;
	SoftwareVersionTable swvTable = SoftwareVersionTable.table;
	String sql;
	//Field[] f = {userTable.userid,new cbit.sql.StarField(vTable)};
	Field[] f = new Field[] {vTable.id,userTable.userid,swvTable.softwareVersion};
	f = (Field[])BeanUtils.addElements(f,vTable.versionFields);
	f = (Field[])BeanUtils.addElement(f,vTable.mathRef);

	Table[] t = {vTable,userTable,swvTable};

	String condition = userTable.id.getQualifiedColName() + " = " + vTable.ownerRef.getQualifiedColName() +  // links in the userTable
	           " AND " + vTable.id.getQualifiedColName() + " = " + swvTable.versionableRef.getQualifiedColName()+"(+) ";
	if (extraConditions != null && extraConditions.trim().length()>0){
		condition += " AND "+extraConditions;
	}

	sql = DatabasePolicySQL.enforceOwnershipSelect(user,f,t,condition,special);
	return sql;
}
/**
 * This method was created in VisualAge.
 * @return cbit.vcell.geometry.GeometryInfo
 * @param rset java.sql.ResultSet
 * @param log cbit.vcell.server.SessionLog
 */
public SolverResultSetInfo getResultSetInfo(ResultSet rset,Connection con,SessionLog log) throws SQLException,DataAccessException {
	
	java.math.BigDecimal groupid = rset.getBigDecimal(VersionTable.privacy_ColumnName);
	SimulationVersion simulationVersion = (SimulationVersion)getVersion(rset,DbDriver.getGroupAccessFromGroupID(con,groupid),log);
	
	VCSimulationIdentifier vcSimID = new VCSimulationIdentifier(simulationVersion.getVersionKey(),simulationVersion.getOwner());
	VCSimulationDataIdentifier vcSimDataID = new VCSimulationDataIdentifier(vcSimID, rset.getInt(ResultSetMetaDataTable.table.jobIndex.toString()));
	SolverResultSetInfo rsetInfo = new SolverResultSetInfo(vcSimDataID);
	try {
		String path = rset.getString(ResultSetMetaDataTable.table.dataFilePath.toString());
		rsetInfo.setDataFilePath(path);
		rsetInfo.setEndingDate(getDate(rset,ResultSetMetaDataTable.table.endDate.toString()));
		rsetInfo.setStartingDate(getDate(rset,ResultSetMetaDataTable.table.startDate.toString()));
	}catch (PropertyVetoException e){
		log.exception(e);
	}
	return rsetInfo;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getResultSetInfoSQL(User user,String extraConditions,String special) {
	
	UserTable userTable = UserTable.table;
	SimulationTable vTable = SimulationTable.table;
	ResultSetMetaDataTable rsetTable = ResultSetMetaDataTable.table;
	String sql;
	//Field[] f = {userTable.userid,new cbit.sql.StarField(vTable),
		         //rsetTable.dataFilePath,rsetTable.startDate,rsetTable.endDate};
	Field[] f = new Field[] {vTable.id,userTable.userid};
	f = (Field[])BeanUtils.addElements(f,vTable.versionFields);
	f = (Field[])BeanUtils.addElement(f,vTable.mathRef);
	f = (Field[])BeanUtils.addElements(f,new Field[] {rsetTable.dataFilePath,rsetTable.startDate,rsetTable.endDate,rsetTable.jobIndex});
	
	Table[] t = {vTable,userTable,rsetTable};
	
	String condition =	vTable.ownerRef.getQualifiedColName() + " = " + userTable.id.getQualifiedColName() +		// links in the userTable
						" AND " + rsetTable.simRef.getQualifiedColName() + " = " + vTable.id.getQualifiedColName();	// links in the resultSetTable
	if (extraConditions != null && extraConditions.trim().length()>0){
		condition += " AND "+extraConditions;
	}
	sql = DatabasePolicySQL.enforceOwnershipSelect(user,f,t,condition,special);
	return sql;
}
/**
 * This method was created in VisualAge.
 * @return cbit.vcell.math.MathDescription
 * @param user cbit.vcell.server.User
 * @param rset java.sql.ResultSet
 */
public Simulation getSimulation(QueryHashtable dbc, ResultSet rset, SessionLog log, Connection con, User user, MathDescriptionDbDriver mathDB) 
										throws SQLException,DataAccessException,PropertyVetoException {

	//
	// get TaskDescription Data into parsable form
	//
	//
	//String taskDescriptionString = new String(solverTaskDescData);
	//
//	System.out.println("taskDescriptionString '"+taskDescriptionString+"'");
	String taskDescriptionString = rset.getString(SimulationTable.table.taskDescription.getUnqualifiedColName());
	taskDescriptionString = TokenMangler.getSQLRestoredString(taskDescriptionString);
	CommentStringTokenizer solverTaskDescTokens = new CommentStringTokenizer(taskDescriptionString);
	
	//
	// get MathOverride Data (language) (MUST BE READ FIRST)
	//
	/*
	byte[] mathOverridesData = null;
	mathOverridesData = rset.getBytes(SimulationTable.table.mathOverrides.toString());
	if (rset.wasNull() || mathOverridesData==null || mathOverridesData.length==0){
		throw new DataAccessException("no data stored for MathOverrides");
	}
	String mathOverridesString = new String(mathOverridesData);
	*/
	//
	String mathOverridesString =
		DbDriver.varchar2_CLOB_get(rset,SimulationTable.table.mathOverridesSmall,SimulationTable.table.mathOverridesLarge);
	if(mathOverridesString == null || mathOverridesString.length() == 0){
		throw new DataAccessException("no data stored for MathOverrides");
	}
	//
	//	System.out.println("mathOverridesString '"+mathOverridesString+"'");
	if (mathOverridesString.endsWith(";}\n")){
		StringBuffer buffer = new StringBuffer(mathOverridesString.substring(0,mathOverridesString.length()-2));
		buffer.append("\n}\n");
		mathOverridesString = buffer.toString();
	}
	CommentStringTokenizer mathOverrideTokens = new CommentStringTokenizer(mathOverridesString);

	String dataProcessingInstructionString = rset.getString(dataProcInstr.getUnqualifiedColName());
	DataProcessingInstructions dpi = null;
	if(!rset.wasNull() && dataProcessingInstructionString != null && dataProcessingInstructionString.length() > 0){
		dataProcessingInstructionString = TokenMangler.getSQLRestoredString(dataProcessingInstructionString);
		dpi = DataProcessingInstructions.fromDbXml(dataProcessingInstructionString);
	}
	//
	// Get Version
	//
	java.math.BigDecimal groupid = rset.getBigDecimal(VersionTable.privacy_ColumnName);
	SimulationVersion simulationVersion = (SimulationVersion)getVersion(rset,DbDriver.getGroupAccessFromGroupID(con,groupid),log);

	java.math.BigDecimal bigD = rset.getBigDecimal(SimulationTable.table.mathRef.toString());
	KeyValue mathKey = null;
	if (!rset.wasNull()) {
		mathKey = new KeyValue(bigD);
	}else{
		throw new DataAccessException("Error:  MathDescription Reference Cannot be Null for Simulation");
	}
	MathDescription mathDesc = (MathDescription)mathDB.getVersionable(dbc, con,user,VersionableType.MathDescription,mathKey);
	
	Simulation simulation = new Simulation(simulationVersion,mathDesc,mathOverrideTokens,solverTaskDescTokens);
	simulation.setDataProcessingInstructions(dpi);
	
	
	//MeshSpec (Is This Correct?????)
	if (mathDesc != null && mathDesc.getGeometry() != null && mathDesc.getGeometry().getDimension()>0){
		int msX = rset.getInt(SimulationTable.table.meshSpecX.getUnqualifiedColName());
		int msY = rset.getInt(SimulationTable.table.meshSpecY.getUnqualifiedColName());
		int msZ = rset.getInt(SimulationTable.table.meshSpecZ.getUnqualifiedColName());
		MeshSpecification meshSpec = new MeshSpecification(simulation.getMathDescription().getGeometry());
		meshSpec.setSamplingSize(new ISize(msX,msY,msZ));
		simulation.getMeshSpecification().copyFrom(meshSpec);
	}
	
	//
	// setMathDescription is done in calling parent
	//
	//simulation.setMathDescription(mathDesc);

	return simulation;
}
/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param key KeyValue
 * @param modelName java.lang.String
 */
public String getSQLValueList(Simulation simulation,KeyValue mathKey,Version version) {

	SolverTaskDescription 	solverTD 		= simulation.getSolverTaskDescription();
	String					mathOverrides	= simulation.getMathOverrides().getVCML();
	
	StringBuffer buffer = new StringBuffer();
	buffer.append("(");
	buffer.append(getVersionGroupSQLValue(version) + ",");
	buffer.append(mathKey+",");
	buffer.append("EMPTY_CLOB()"+","); // MathOverridesOrig keep for compatibility with old VCell
	
	if(DbDriver.varchar2_CLOB_is_Varchar2_OK(mathOverrides)){
		buffer.append("null"+","+DbDriver.INSERT_VARCHAR2_HERE+",");
	}else{
		buffer.append(DbDriver.INSERT_CLOB_HERE+","+"null"+",");
	}
	
	buffer.append((solverTD != null?"'"+TokenMangler.getSQLEscapedString(solverTD.getVCML())+"'":"null")+",");
	if (simulation.getMathDescription() != null &&
		simulation.getMathDescription().getGeometry() != null &&
		simulation.getMathDescription().getGeometry().getDimension()>0){
		MeshSpecification	meshSpec = simulation.getMeshSpecification();
		buffer.append(meshSpec.getSamplingSize().getX()+","+meshSpec.getSamplingSize().getY()+","+meshSpec.getSamplingSize().getZ());
	}else{
		buffer.append("null,null,null");
	}
	if (simulation.getDataProcessingInstructions()!=null){
		buffer.append(",'"+TokenMangler.getSQLEscapedString(simulation.getDataProcessingInstructions().getDbXml())+"'");
	}else{
		buffer.append(",null");
	}
	buffer.append(")");
	return buffer.toString();
}
}
