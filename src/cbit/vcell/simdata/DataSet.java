package cbit.vcell.simdata;
/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.io.*;
import java.util.*;

public class DataSet implements java.io.Serializable
{

   private Vector dataBlockList;
   private FileHeader fileHeader;
   private String fileName;

DataSet() {
	dataBlockList = new Vector();
	fileHeader = new FileHeader();
}


/**
 * This method was created by a SmartGuide.
 * @return double[]
 * @param varName java.lang.String
 */
public static double[] fetchSimData(String varName, File file) throws IOException {
	DataSet dataSet = new DataSet();
	dataSet.read(file, null);
	return dataSet.getData(varName, null);
}


/**
 * This method was created by a SmartGuide.
 * @return double[]
 * @param varName java.lang.String
 */
double[] getData(String varName, File zipFile) throws IOException {
	double data[] = null;
	for (int i=0;i<dataBlockList.size();i++){
		DataBlock dataBlock = (DataBlock)dataBlockList.elementAt(i);
		if (varName.trim().equals(dataBlock.getVarName().trim())){
			File pdeFile = new File(fileName);
			InputStream is = null;
			long length = 0;
			java.util.zip.ZipFile zipZipFile = null;

			if (zipFile == null && !pdeFile.exists()) {
				throw new FileNotFoundException("file "+fileName+" does not exist");
			}
			if (zipFile != null) {
				zipZipFile = openZipFile(zipFile);
				java.util.zip.ZipEntry dataEntry = zipZipFile.getEntry(pdeFile.getName());
				length = dataEntry.getSize();
				is = zipZipFile.getInputStream(dataEntry);
			} else {
				length = pdeFile.length();
				is = new FileInputStream(pdeFile);
			}

			// read data from zip file
			BufferedInputStream bis = new BufferedInputStream(is);	
			DataInputStream dis = new DataInputStream(bis);
			try {
				dis.skip(dataBlock.getDataOffset());
				int size = dataBlock.getSize();
				data = new double[size];
				for (int j=0;j<size;j++){
					data[j] = dis.readDouble();
				}	
			}finally{
				dis.close();
				if (zipZipFile != null) {
					zipZipFile.close();
				}
			}	
			break;
		}		
	}	
	if (data == null){
		throw new IOException("DataSet.getData(), data not found for variable '" + varName + "'");
	}	
	return data;
}


/**
 * This method was created by a SmartGuide.
 * @return double[]
 * @param varName java.lang.String
 */
int getDataLength(String varName) throws IOException {
	for (int i=0;i<dataBlockList.size();i++){
		DataBlock dataBlock = (DataBlock)dataBlockList.elementAt(i);
		if (varName.trim().equals(dataBlock.getVarName().trim())){
			return dataBlock.getSize();
		}
	}
	throw new IOException("DataSet.getData("+varName+"), data not found");
}


/**
 * This method was created by a SmartGuide.
 * @return java.lang.String[]
 */
String[] getDataNames() {
	String nameArray[] = new String[dataBlockList.size()];
	for (int i=0;i<dataBlockList.size();i++){
		nameArray[i] = ((DataBlock)dataBlockList.elementAt(i)).getVarName();
	}	
	return nameArray;
}


/**
 * This method was created by a SmartGuide.
 * @return int
 */
int getSizeX() {
	return fileHeader.sizeX;
}


/**
 * This method was created by a SmartGuide.
 * @return int
 */
int getSizeY() {
	return fileHeader.sizeY;
}


/**
 * This method was created by a SmartGuide.
 * @return int
 */
int getSizeZ() {
	return fileHeader.sizeZ;
}


/**
 * This method was created by a SmartGuide.
 * @return double[]
 * @param varName java.lang.String
 */
int getVariableTypeInteger(String varName) throws IOException {
	for (int i=0;i<dataBlockList.size();i++){
		DataBlock dataBlock = (DataBlock)dataBlockList.elementAt(i);
		if (varName.trim().equals(dataBlock.getVarName().trim())){
			return dataBlock.getVariableTypeInteger();
		}
	}
	throw new IOException("DataSet.getData("+varName+"), data not found");
}


/**
 * This method was created by a SmartGuide.
 * @return java.lang.String[]
 */
int[] getVariableTypeIntegers() {
	int typeArray[] = new int[dataBlockList.size()];
	for (int i=0;i<dataBlockList.size();i++){
		typeArray[i] = ((DataBlock)dataBlockList.elementAt(i)).getVariableTypeInteger();
	}	
	return typeArray;
}


/**
 * Insert the method's description here.
 * Creation date: (6/23/2004 9:37:26 AM)
 * @return java.util.zip.ZipFile
 */
protected static java.util.zip.ZipFile openZipFile(File zipFile) throws IOException {
	for (int i = 0; i < 20; i ++) {
		try {
			return new java.util.zip.ZipFile(zipFile);
		} catch (java.util.zip.ZipException ex) {			
			if (i < 19) {
				try {
					System.out.println("<ALERT> Failed reading zip file " + zipFile + ", try again");
					Thread.sleep(50);
				} catch (InterruptedException iex) {
				}
			} else {
				throw ex;
			}
		}
	}
	throw new IOException("Opening zip file " + zipFile + " failed");
}


/**
 * This method was created by a SmartGuide.
 * 
 */
void read(File file, File zipFile) throws IOException, OutOfMemoryError {
	
	this.fileName = file.getPath();	
	
	InputStream is = null;
	long length  = 0;
	java.util.zip.ZipFile zipZipFile = null;	
	
	if (zipFile != null) {
		System.out.println("DataSet.read() open " + zipFile + " for " + file.getName());
		zipZipFile = openZipFile(zipFile);
		java.util.zip.ZipEntry dataEntry = zipZipFile.getEntry(file.getName());
		is = zipZipFile.getInputStream(dataEntry);
		length = dataEntry.getSize();
	} else {		
		if (!file.exists()){
			File compressedFile = new File(fileName+".Z");
			if (compressedFile.exists()){
				Runtime.getRuntime().exec("uncompress "+fileName+".Z");
				file = new File(fileName);
				if (!file.exists()){
					throw new IOException("file "+fileName+".Z could not be uncompressed");
				}	
			}else{
				throw new FileNotFoundException("file "+fileName+" does not exist");
			}
		}	
		System.out.println("DataSet.read() open '" + fileName + "'"); 
		is = new FileInputStream(file);
		length = file.length();
	}

	BufferedInputStream bis = new BufferedInputStream(is);
	DataInputStream fp = new DataInputStream(bis);
	try {
		fileHeader.read(fp);
		for (int i = 0; i < fileHeader.numBlocks; i++) {
			DataBlock dataBlock = new DataBlock();
			dataBlock.readBlockHeader(fp);
			dataBlockList.addElement(dataBlock);
		}
	}finally{
		fp.close();
		if (zipZipFile != null) {
			zipZipFile.close();
		}
	}	

}


/**
 * Insert the method's description here.
 * Creation date: (9/21/2006 1:33:24 PM)
 * @param file java.io.File
 * @param varnames java.lang.String
 * @param data double[]
 */
public static void write(File file, String varname, int varType, cbit.util.ISize size, double[] data) throws IOException {
	RandomAccessFile raf = new RandomAccessFile(file, "rw");
	FileHeader fileHeader = new FileHeader();
	DataBlock dataBlock = null;
	double[] writeBuffer = new double[data.length];
	
	fileHeader.sizeX = size.getX();
	fileHeader.sizeY = size.getY();
	fileHeader.sizeZ = size.getZ();
	fileHeader.numBlocks = 1;
	fileHeader.firstBlockOffset = FileHeader.headerSize;

	fileHeader.write(raf);
   
	dataBlock = DataBlock.createDataBlock(varname, varType, data.length, fileHeader.firstBlockOffset + DataBlock.blockSize);
	dataBlock.writeBlockHeader(raf);	   

	for (int i = 0; i < data.length; i ++) {
		raf.writeDouble(data[i]);
	}
	raf.close();		
}
}