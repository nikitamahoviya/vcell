package cbit.vcell.messaging.admin;
import cbit.vcell.messaging.*;

/**
 * Insert the type's description here.
 * Creation date: (8/8/2003 10:23:15 AM)
 * @author: Fei Gao
 */
public interface ManageConstants {
	public static final int SECOND = MessageConstants.SECOND; 
	public static final int MINUTE = MessageConstants.MINUTE;	

	public static final long INTERVAL_PING_BOOTSTRAP = 30 * MINUTE; // in minutes
	public static final long INTERVAL_PING_SERVICE = 10 * MINUTE; // in minutes
	public static final long INTERVAL_PING_RESPONSE = 30 * SECOND; // in milliseconds

	public static final String MESSAGE_TYPE_PROPERTY = MessageConstants.MESSAGE_TYPE_PROPERTY;
	public static final String MESSAGE_TYPE_ISSERVICEALIVE_VALUE	= "IsServiceAlive";
	public static final String MESSAGE_TYPE_IAMALIVE_VALUE	= "IAmAlive";		
	public static final String MESSAGE_TYPE_ASKPERFORMANCESTATUS_VALUE	= "AskPerformance";
	public static final String MESSAGE_TYPE_CHANGECONFIG_VALUE	= "ChangeConfig";
	public static final String MESSAGE_TYPE_REPLYPERFORMANCESTATUS_VALUE	= "ReplyPerformance";
	public static final String MESSAGE_TYPE_STARTBOOTSTRAP_VALUE	= "StartBootstrap";
	public static final String MESSAGE_TYPE_STOPBOOTSTRAP_VALUE	= "StopBootstrap";
	public static final String MESSAGE_TYPE_STARTSERVICE_VALUE	= "StartService";
	public static final String MESSAGE_TYPE_STOPSERVICE_VALUE	= "StopService";
	public static final String MESSAGE_TYPE_OPENSERVICELOG_VALUE	= "OpenServiceLog";
	public static final String MESSAGE_TYPE_OPENSERVERLOG_VALUE	= "OpenServerLog";
	public static final String MESSAGE_TYPE_ISSERVERALIVE_VALUE	= "IsServerAlive";

	public static final String MESSAGE_TYPE_BROADCASTMESSAGE_VALUE	= "BroadcastMessage";
	public static final String BROADCASTMESSAGE_CONTENT_PROPERTY = "BroadcastMessageContent";

	public static final String STATUS_OPENLOG_PROPERTY = "openLog";
	public static final String STATUS_OPENLOG_NOTEXIST= "openLog_notExist";
	public static final String STATUS_OPENLOG_EXIST = "openLog_Exit";

	public static final String FILE_NAME_PROPERTY = "FileName";
	public static final String FILE_LENGTH_PROPERTY = "FileLength";

	// XML tags begin
	public static final String BOOTSTRAP_ROOT_TAG = "bootstrap";
	public static final String SERVERMANAGER_ROOT_TAG = "server-manager";
	public static final String SERVICE_TAG = "service";
	public static final String SERVICE_TYPE_TAG = "service-type";
	public static final String SERVICE_NAME_TAG = "service-name";
	public static final String SERVICE_START_TAG = "start-command";
	public static final String SERVICE_STOP_TAG = "stop-command";
	public static final String SERVICE_AUTOSTART_TAG = "auto-start";

	public static final String RMISERVICE_TAG = "rmi-service";
	public static final String RMISERVICE_NAME_TAG = "rmi-service-name";
	
	public static final String BOOTSTRAP_TAG = "bootstrap";
	public static final String SERVER_TAG = "server";
	public static final String HOST_NAME_TAG = "host-name";
	public static final String SERVER_TYPE_TAG = "server-type";
	public static final String SERVER_NAME_TAG = "server-name";
	public static final String BOOTSTRAP_NAME_TAG = "bootstrap-name";
	public static final String LOGFILE_TAG = "log-file";
	public static final String ARCHIVEDIR_TAG = "archive-dir";
	// XML tags end

	public static final String HOSTNAME_PROPERTY	= MessageConstants.HOSTNAME_PROPERTY;
	public static final String SERVICETYPE_PROPERTY	= MessageConstants.SERVICETYPE_PROPERTY;
	public static final String SERVICENAME_PROPERTY	= "ServiceName";
	public static final String SERVER_TYPE_PROPERTY = "ServerType";
	public static final String SERVER_NAME_PROPERTY = "ServerName";
	
	public static final String SERVER_TYPE_BOOTSTRAP = "Bootstrap";
	public static final String SERVER_TYPE_SERVERMANAGER = "ServerManager";
	public static final String SERVER_TYPE_RMISERVICE = "RMIService";
	public static final String ARCHIVE_DIR_PROPERTY = "ArchiveDir";

	public static final String NEW_SERVICE = "New services";
	public static final String MODIFY_SERVICE = "Modified services";
	public static final String DELETE_SERVICE = "Deleted services";

	public static final int SERVICE_MODIFIER_NEW = 0;
	public static final int SERVICE_MODIFIER_MODIFY = 1;
	public static final int SERVICE_MODIFIER_DELETE = 2;	
}