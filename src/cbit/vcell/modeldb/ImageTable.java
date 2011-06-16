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
import cbit.sql.*;
import cbit.image.*;
import cbit.vcell.server.*;
import java.sql.*;

import org.vcell.util.DataAccessException;
import org.vcell.util.SessionLog;
import org.vcell.util.document.KeyValue;
import org.vcell.util.document.User;
import org.vcell.util.document.Version;
import org.vcell.util.document.VersionInfo;

import cbit.image.GifParsingException;
/**
 * This type was created in VisualAge.
 */
public class ImageTable extends cbit.vcell.modeldb.VersionTable {
	private static final String TABLE_NAME = "vc_image";
	public static final String REF_TYPE = "REFERENCES " + TABLE_NAME + "(" + Table.id_ColumnName + ")";

	public final Field numX			= new Field("numX",			"integer",	"NOT NULL");
	public final Field numY			= new Field("numY",			"integer",	"NOT NULL");
	public final Field numZ			= new Field("numZ",			"integer",	"NOT NULL");
	public final Field extentRef	= new Field("extentRef",	"integer",	"NOT NULL "+ExtentTable.REF_TYPE);

	private final Field fields[] = {numX,numY,numZ,extentRef};
	
	public static final ImageTable table = new ImageTable();

/**
 * ModelTable constructor comment.
 */
private ImageTable() {
	super(TABLE_NAME,ImageTable.REF_TYPE);
	addFields(fields);
}


/**
 * This method was created in VisualAge.
 * @return VCImage
 * @param rset ResultSet
 * @param log SessionLog
 */
public VCImageCompressed getImage(ResultSet rset,Connection con,SessionLog log,ImageDataTable imageDataTable) throws SQLException, DataAccessException{
	
	byte data[] = imageDataTable.getData(rset,log);
	
	int nx = rset.getInt(numX.toString());
	int ny = rset.getInt(numY.toString());
	int nz = rset.getInt(numZ.toString());
	
	double ex = rset.getBigDecimal(ExtentTable.table.extentX.toString()).doubleValue();
	double ey = rset.getBigDecimal(ExtentTable.table.extentY.toString()).doubleValue();
	double ez = rset.getBigDecimal(ExtentTable.table.extentZ.toString()).doubleValue();

	java.math.BigDecimal groupid = rset.getBigDecimal(VersionTable.privacy_ColumnName);
	Version version = getVersion(rset,DbDriver.getGroupAccessFromGroupID(con,groupid),log);
	try {
		org.vcell.util.Extent extent = new org.vcell.util.Extent(ex,ey,ez);
		VCImageCompressed vcImage = new VCImageCompressed(version,data,extent,nx,ny,nz);
		return vcImage;
	}catch (ImageException e){
		log.exception(e);
		throw new DataAccessException(e.getMessage());
	}
}


/**
 * This method was created in VisualAge.
 * @return VCImage
 * @param rset ResultSet
 * @param log SessionLog
 */
public VersionInfo getInfo(ResultSet rset, Connection con,SessionLog log) throws SQLException,DataAccessException {

	GIFImage gifImage = null;
	try{
		//gifImage = new GIFImage(rset.getBytes(BrowseImageDataTable.table.data.toString()));
		byte[] gifData = (byte[]) DbDriver.getLOB(rset,BrowseImageDataTable.table.data.toString());
		gifImage = new GIFImage(gifData);
		//
	}catch (GifParsingException e){
		throw new DataAccessException("Error Parsing browseImage");
	}
	
	java.math.BigDecimal groupid = rset.getBigDecimal(VersionTable.privacy_ColumnName);
	Version version = getVersion(rset,DbDriver.getGroupAccessFromGroupID(con,groupid),log);
	
	int x = rset.getInt(ImageTable.table.numX.toString());
	int y = rset.getInt(ImageTable.table.numY.toString());
	int z = rset.getInt(ImageTable.table.numZ.toString());
	org.vcell.util.ISize size = new org.vcell.util.ISize(x,y,z);
	
	double extentX = rset.getBigDecimal(ExtentTable.table.extentX.toString()).doubleValue();
	double extentY = rset.getBigDecimal(ExtentTable.table.extentY.toString()).doubleValue();
	double extentZ = rset.getBigDecimal(ExtentTable.table.extentZ.toString()).doubleValue();
	org.vcell.util.Extent extent = new org.vcell.util.Extent(extentX,extentY,extentZ);

	return new VCImageInfo(version,size,extent,gifImage);
}


/**
 * This method was created in VisualAge.
 * @return java.lang.String
 */
public String getInfoSQL(User user,String extraConditions,String special,boolean bCheckPermission) {
	UserTable userTable = UserTable.table;
	ImageTable iTable = this;
	ExtentTable eTable = ExtentTable.table;
	BrowseImageDataTable bTable = BrowseImageDataTable.table;
	
	String sql;
	Field[] f = {userTable.userid,new cbit.sql.StarField(iTable),eTable.extentX,eTable.extentY,eTable.extentZ,bTable.data};
	Table[] t = {iTable,userTable,eTable,bTable};	
	String condition = iTable.extentRef.getQualifiedColName() + " = " + eTable.id.getQualifiedColName() +
			" AND " + bTable.imageRef.getQualifiedColName() + " = " + iTable.id.getQualifiedColName() +
			" AND " + userTable.id.getQualifiedColName() + " = " + iTable.ownerRef.getQualifiedColName();
	if (extraConditions != null && extraConditions.trim().length()>0){
		condition += " AND "+extraConditions;
	}
	sql = DatabasePolicySQL.enforceOwnershipSelect(user,f,t,condition,special,bCheckPermission);
	return sql;
}


/**
 * This method was created in VisualAge.
 * @return java.lang.String
 * @param key KeyValue
 * @param modelName java.lang.String
 */
public String getSQLValueList(VCImage image,KeyValue keySizeRef,Version version) {
							
	StringBuffer buffer = new StringBuffer();
	buffer.append("(");
	buffer.append(getVersionGroupSQLValue(version) + ",");
	buffer.append(image.getNumX()+",");
	buffer.append(image.getNumY()+",");
	buffer.append(image.getNumZ()+",");
	buffer.append(keySizeRef+")");

	return buffer.toString();
}
}
