package cbit.util;

/*�
 * (C) Copyright University of Connecticut Health Center 2001.
 * All rights reserved.
�*/
import java.io.Serializable;
/**
 * This interface was generated by a SmartGuide.
 * 
 */
final public class ExecutableStatus implements Serializable {
	private static final int STATUS_READY		= 0;
	private static final int STATUS_RUNNING		= 1;
	private static final int STATUS_COMPLETE	= 2;
	private static final int STATUS_ERROR		= 3;
	private static final int STATUS_STOPPED		= 4;
	private static final String STATUS_ARRAY[] = { "ready", "running", "complete", "error", "stopped" };

	private int status;
	private String msg;
	
	public static final ExecutableStatus READY    = new ExecutableStatus(STATUS_READY,null);
	public static final ExecutableStatus RUNNING  = new ExecutableStatus(STATUS_RUNNING,null);
	public static final ExecutableStatus COMPLETE = new ExecutableStatus(STATUS_COMPLETE,null);
	public static final ExecutableStatus STOPPED  = new ExecutableStatus(STATUS_STOPPED,null);
/**
 * This method was created in VisualAge.
 * @param status int
 */
private ExecutableStatus(int status, String msg) {
	this.status = status;
	this.msg = msg;
}
/**
 * This method was created in VisualAge.
 * @return boolean
 * @param executableStatus cbit.vcell.solvers.ExecutableStatus
 */
public boolean equals(ExecutableStatus executableStatus) {
	return status == executableStatus.status;
}
/**
 * This method was created in VisualAge.
 * @return cbit.vcell.solvers.ExecutableStatus
 * @param error java.lang.String
 */
public final static ExecutableStatus getError(String error) {
	return new ExecutableStatus(STATUS_ERROR, error);
}
/**
 * This method was created in VisualAge.
 * @return boolean
 */
public boolean isError() {
	return (status == STATUS_ERROR);
}
/**
 * This method was created by a SmartGuide.
 * @return java.lang.String
 */
public String toString()
{
	if (status == STATUS_ERROR){
		return STATUS_ARRAY[status]+": "+msg;
	}else{
		return STATUS_ARRAY[status];
	}
}
}
