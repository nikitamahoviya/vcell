package cbit.vcell.server;

/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import cbit.vcell.modeldb.ResultSetCrawler;
import cbit.vcell.modeldb.ResultSetDBTopLevel;
import cbit.sql.*;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
/**
 * This class was generated by a SmartGuide.
 * 
 */
public class LocalVCellBootstrap extends UnicastRemoteObject implements VCellBootstrap {
	private LocalVCellServer localVCellServer = null;
	private AdminDatabaseServer adminDbServer = null;
	private SessionLog sessionLog = new StdoutSessionLog(PropertyLoader.ADMINISTRATOR_ACCOUNT);
/**
 * This method was created by a SmartGuide.
 * @exception java.rmi.RemoteException The exception description.
 */
private LocalVCellBootstrap(boolean bPrimaryServer, String hostName, AdminDatabaseServer adminDbServer, ResultSetCrawler argResultSetCrawler, cbit.vcell.messaging.JmsConnectionFactory jmsConnFactory, boolean bRemoteMode) throws RemoteException, FileNotFoundException, DataAccessException {
	super(PropertyLoader.getIntProperty(PropertyLoader.rmiPortVCellBootstrap,0));
	this.adminDbServer = adminDbServer;
	this.localVCellServer = new LocalVCellServer(bPrimaryServer, hostName, jmsConnFactory, adminDbServer, argResultSetCrawler, bRemoteMode);
	User user = adminDbServer.getUser(PropertyLoader.ADMINISTRATOR_ACCOUNT);
	String pwd = adminDbServer.getUserInfo(user.getID()).password;
	localVCellServer.setAdminAccount(user, pwd);
}
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.DataSetController
 * @exception java.lang.Exception The exception description.
 */
public VCellConnection getVCellConnection(String userid, String password) throws DataAccessException, AuthenticationException {
	try {
		VCellConnection vcConn = localVCellServer.getVCellConnection(userid,password);
		if (vcConn!=null){
			sessionLog.print("LocalVCellBootstrap.getVCellConnection(" + userid + "," + password + ") <<<<SUCCESS>>>>");
		}else{
			sessionLog.print("LocalVCellBootstrap.getVCellConnection(" + userid + "," + password + ") <<<<RETURNED NULL>>>>");
		}
		return vcConn;
	}catch (RemoteException e){
		sessionLog.exception(e);
		throw new DataAccessException(e.getMessage());
	}catch (FileNotFoundException e){
		sessionLog.exception(e);
		throw new DataAccessException(e.getMessage());
	}catch (java.sql.SQLException e){
		sessionLog.exception(e);
		throw new DataAccessException(e.getMessage());
	} catch (javax.jms.JMSException ex) {
		sessionLog.exception(ex);
		throw new DataAccessException(ex.getMessage());
	}
}
/**
 * This method was created by a SmartGuide.
 * @return cbit.vcell.server.DataSetController
 * @exception java.lang.Exception The exception description.
 */
public VCellServer getVCellServer(User user, String password) throws DataAccessException, AuthenticationException, PermissionException {
	//
	// Authenticate User
	//
	boolean bAuthenticated = false;
	
	try{
		bAuthenticated = adminDbServer.getUser(user.getName(),password).compareEqual(user);
	}catch(RemoteException e){
		sessionLog.exception(e);
		throw new DataAccessException("Failure authenticating user "+user.getName()+", RemoteException: " + e.getMessage());
	}
	if (!bAuthenticated){
		sessionLog.print("LocalVCellBootstrap.getVCellServer(" + user + "," + password + "), didn't authenticate");
		throw new AuthenticationException("Authentication Failed for user " + user.getName());
	}else if (user.getName().equals(PropertyLoader.ADMINISTRATOR_ACCOUNT)){
		sessionLog.print("LocalVCellBootstrap.getVCellServer(" + user + "), returning remote copy of VCellServer");
		localVCellServer.setAdminAccount(user,password);
		return localVCellServer;
	}else{
		sessionLog.print("LocalVCellBootstrap.getVCellServer(" + user + "), insufficient privilege for user "+user.getName());
		throw new PermissionException("insufficient privilege for user "+user.getName());
	}
}
/**
 * Insert the method's description here.
 * Creation date: (6/8/2006 3:25:26 PM)
 * @return java.lang.String
 */
public java.lang.String getVCellSoftwareVersion() {
	String ver = PropertyLoader.getRequiredProperty(PropertyLoader.vcellSoftwareVersion);
	sessionLog.print("LocalVCellBootstrap.getVCellSoftwareVersion() : " + ver);
	return ver;
}
/**
 * main entrypoint - starts the application
 * @param args java.lang.String[]
 */
public static void main(java.lang.String[] args) {
	String PRIMARY = "primary";
	String SLAVE = "slave";
	String MESSAGING = "messaging";
	if (args.length != 4) {
		System.out.println("usage: cbit.vcell.server.LocalVCellBootstrap host port ("+PRIMARY+"|"+SLAVE+"|"+MESSAGING+") [logfile|-] \n");
		System.out.println(" example -  cbit.vcell.server.LocalVCellBootstrap nrcam.vcell.uchc.edu 40099 messaging server.log");
		System.exit(1);
	}
	try {
		//
		// Redirect output to the logfile (append if exists)
		//
		if (!args[3].equals("-")){
			System.setOut(new PrintStream(new FileOutputStream(args[3], true), true));
		}
		
		//
		// Create and install a security manager
		//
		System.setSecurityManager(new RMISecurityManager());

		
		Thread.currentThread().setName("Application");
		new PropertyLoader();

		//
		// get Host and Port
		//
		String host = args[0];
		if (host.equals("localhost")){
			try {
				host = java.net.InetAddress.getLocalHost().getHostName();
			}catch (java.net.UnknownHostException e){
				// do nothing, "localhost" is ok
			}
		}
		int argRmiPort = Integer.parseInt(args[1]);
		int rmiPort = PropertyLoader.getIntProperty(PropertyLoader.rmiPortRegistry, argRmiPort);

		if (argRmiPort!=rmiPort){
			System.out.println("RMI Registry using port ("+rmiPort+") from propertyfile ");
		}
		

		//
		// decide whether it will be a Primary or Slave Server
		//
		cbit.vcell.messaging.JmsConnectionFactory jmsConnFactory = null;
		String serverConfig = args[2];
		boolean bPrimary = false;
		if (serverConfig.equals(PRIMARY)){
			bPrimary = true;
		}else if (serverConfig.equals(SLAVE)){
			bPrimary = false;
		}else if (serverConfig.equals(MESSAGING)){
			bPrimary = false;
			jmsConnFactory = new cbit.vcell.messaging.JmsConnectionFactoryImpl();
		}else{
			throw new Exception("expecting either "+PRIMARY+" or "+SLAVE+" as third argument");
		}
		
		boolean bRemoteMode = true;
		SessionLog log = new StdoutSessionLog("local(unauthenticated)_administrator");
		
		ConnectionFactory conFactory = new cbit.sql.OraclePoolingConnectionFactory(log);
		KeyFactory keyFactory = new cbit.sql.OracleKeyFactory();
		cbit.vcell.modeldb.DatabasePolicySQL.bSilent=true;
		//
		// don't timeout entries, and use vcell.properties for cacheSize
		//
		DBCacheTable dbCacheTable = new cbit.sql.DBCacheTable(-1,0);
		LocalVCellConnection.setDatabaseResources(conFactory,keyFactory,dbCacheTable);
		
		AdminDatabaseServer adminDbServer = new cbit.vcell.modeldb.LocalAdminDbServer(conFactory,keyFactory,log);
		ResultSetCrawler rsCrawler = new ResultSetCrawler(conFactory,adminDbServer,log,dbCacheTable);
		LocalVCellBootstrap localVCellBootstrap = new LocalVCellBootstrap(bPrimary,host+":"+rmiPort,adminDbServer,rsCrawler,jmsConnFactory,bRemoteMode);
		
		//
		// spawn the WatchdogMonitor (which spawns the RMI registry, and binds the localVCellBootstrap)
		//
		long minuteMS = 60000;
		long monitorSleepTime = 20*minuteMS;
		String rmiUrl = "//" + host + ":" + rmiPort + "/VCellBootstrapServer";
		Thread watchdogMonitorThread = new Thread(new WatchdogMonitor(monitorSleepTime,rmiPort,rmiUrl,localVCellBootstrap,serverConfig),"WatchdogMonitor");
		watchdogMonitorThread.setDaemon(true);
		watchdogMonitorThread.setName("WatchdogMonitor");
		watchdogMonitorThread.start();
	} catch (Throwable e) {
		System.out.println("LocalVCellBootstrap err: " + e.getMessage());
		e.printStackTrace();
	}
}
}
