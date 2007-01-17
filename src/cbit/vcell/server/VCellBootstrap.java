package cbit.vcell.server;

/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import cbit.vcell.model.*;
import java.rmi.*;
/**
 * This interface was generated by a SmartGuide.
 * 
 */
public interface VCellBootstrap extends java.rmi.Remote {
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.VCellConnection
 * @exception java.rmi.RemoteException The exception description.
 */
public VCellConnection getVCellConnection(String userid,String password) throws RemoteException, DataAccessException, AuthenticationException;
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.VCellConnection
 * @exception java.rmi.RemoteException The exception description.
 */
public VCellServer getVCellServer(User user,String password) throws RemoteException, DataAccessException, AuthenticationException, PermissionException;
/**
 * Insert the method's description here.
 * Creation date: (6/8/2006 2:50:55 PM)
 * @return java.lang.String
 */
String getVCellSoftwareVersion() throws RemoteException;
}
