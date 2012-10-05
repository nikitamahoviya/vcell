/*
 * Copyright (C) 1999-2011 University of Connecticut Health Center
 *
 * Licensed under the MIT License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *  http://www.opensource.org/licenses/mit-license.php
 */

package cbit.vcell.server;

import java.rmi.*;

import org.vcell.util.DataAccessException;
import org.vcell.util.PermissionException;
import org.vcell.util.document.KeyValue;
import org.vcell.util.document.User;
import org.vcell.util.document.UserInfo;
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
public VCellConnection getVCellConnection(UserLoginInfo userLoginInfo) throws RemoteException, DataAccessException, AuthenticationException;
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.VCellConnection
 * @exception java.rmi.RemoteException The exception description.
 */
public VCellServer getVCellServer(User user,UserLoginInfo.DigestedPassword digestedPassword) throws RemoteException, DataAccessException, AuthenticationException, PermissionException;
/**
 * Insert the method's description here.
 * Creation date: (6/8/2006 2:50:55 PM)
 * @return java.lang.String
 */
String getVCellSoftwareVersion() throws RemoteException;

public UserInfo insertUserInfo(UserInfo newUserInfo)throws RemoteException, DataAccessException;
public void sendLostPassword(String userid) throws RemoteException, DataAccessException;
}
