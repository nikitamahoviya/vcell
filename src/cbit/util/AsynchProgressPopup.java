package cbit.util;

import java.awt.*;

import javax.swing.SwingUtilities;

import cbit.gui.ZEnforcer;

/**
 * Insert the type's description here.
 * Creation date: (5/19/2004 3:08:59 PM)
 * @author: Ion Moraru

Swing thread-safe progress popup window, showing message and progress.
Both message and progress can be updated at any time by some other thread.
If long working thread doesn't know it's progress, automatic scrolling progress will be generated.
Popup can be modal or not.

Typical usage:

	AsynchProgressPopup pp = new AsynchProgressPopup(
			requester,			// Component - GUI parent, if applicable; can be null
			title,				// String - window title; can be null
			message,			// String - inside message; can be null
			inputBlocking,		// boolean - whether or not it should be modal
			knowsProgress		// boolean - whether or not we'll know the progress and update it ourselves 
			);
	pp.start();
	{
		// do stuff that takes a while
		....
		// call pp.setMessage(String) to update message string while doing work
		// call pp.setProgress(int) to update progress while doing work, unless automatic mode was used to construct it (will ignore in that case)
		....
	}
	pp.stop();
 
 
 */
public class AsynchProgressPopup extends AsynchGuiUpdater {
	private ProgressDialog dialog = null;
	private int progress = 0;
	private int autoProgress = 0;
	private boolean inputBlocking = false;
	private boolean knowsProgress = false;

	private Component requester = null;
	private String title = null;
	private String message = null;  
	private boolean bCancelable = false;
	private ProgressDialogListener progressDialogListener = null;
/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:11:01 PM)
 * @param title java.lang.String
 * @param message java.lang.String
 * @param inputBlocking boolean
 * @param knowsProgress boolean
 */
public AsynchProgressPopup(Component requester, String title, String message, boolean inputBlocking, boolean knowsProgress) {
	this(requester,title,message,inputBlocking,knowsProgress,false,null);
}

/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:11:01 PM)
 * @param title java.lang.String
 * @param message java.lang.String
 * @param inputBlocking boolean
 * @param knowsProgress boolean
 */
public AsynchProgressPopup(Component requester, String title, String message, boolean inputBlocking, 
		boolean knowsProgress, boolean cancelable, ProgressDialogListener progressDialogListener) {
	this.requester = requester;
	this.title = title;
	this.message = message;
	this.inputBlocking = inputBlocking;
	this.knowsProgress = knowsProgress;
	this.bCancelable = cancelable;
	this.progressDialogListener = progressDialogListener;
}

private ProgressDialog getDialog() {
	if (dialog == null) {
		Frame owner = javax.swing.JOptionPane.getFrameForComponent(requester);
		dialog = new ProgressDialog(owner);
		if (bCancelable && progressDialogListener!=null){
			dialog.setCancelButtonVisible(true);
			dialog.addProgressDialogListener(progressDialogListener);
		}else{
			dialog.setCancelButtonVisible(false);
		}
		dialog.setLocationRelativeTo(requester);
		dialog.setResizable(false);
		if (title != null) {
			dialog.setTitle(title);
		}
		if (message != null) {
			dialog.setMessage(message);
		}
		dialog.setModal(inputBlocking);
		if (! knowsProgress) {
			dialog.setProgressBarString("WORKING...");
		}
	}	
	return dialog;
}
/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:08:59 PM)
 */
protected void guiToDo() {
	new SwingDispatcherAsync (){
		public void runSwing() {
			if (knowsProgress) {
				getDialog().setProgress(progress);
			} else {
				getDialog().setProgress(autoProgress % 100);
				autoProgress += 5;
			}
		}
		public void handleException(Throwable e) {
			e.printStackTrace();
		}
	}.dispatch();

}


/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:08:59 PM)
 */
protected void guiToDo(final Object params) {
	new SwingDispatcherAsync (){
		public void runSwing() {
			if (params instanceof String) {
				getDialog().setMessage(params.toString());
			} else if (params instanceof Integer) {
				getDialog().setProgress(((Integer)params).intValue());
			}
		}
		public void handleException(Throwable e) {
			e.printStackTrace();
		}
	}.dispatch();
}


/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:24:20 PM)
 * @param newMessage java.lang.String
 */
public void setMessage(final String newMessage) {
	new SwingDispatcherAsync (){
		public void runSwing() {
			updateNow(newMessage);
		}
		public void handleException(Throwable e) {
			e.printStackTrace();
		}
	}.dispatch();

}


/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:27:21 PM)
 * @param progress int
 */
public void setProgress(final int argProgress) {
	new SwingDispatcherAsync (){
		public void runSwing() {
			// ignore if in auto mode
			if (knowsProgress) {
				int localProgress = argProgress;
				if (argProgress > 100) {
					localProgress = 100;
				}
				updateNow(new Integer(localProgress));
				AsynchProgressPopup.this.progress = localProgress;
			}
		}
		public void handleException(Throwable e) {
			e.printStackTrace();
		}
	}.dispatch();

}
/**
 * Insert the method's description here.
 * Creation date: (5/01/2008 5:46:18 PM)
 */
public int getProgress( ) {
	if (knowsProgress) {
		return progress;
	} else {
		return 0;
	}
}


/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:28:39 PM)
 */
private void startPrivate(final boolean bKeepOnTop) {
	new SwingDispatcherAsync (){
		public void runSwing() {
			if(bKeepOnTop){
				ZEnforcer.showModalDialogOnTop(getDialog());
			}else{
				getDialog().setVisible(true);
			}
			// start timer for auto progress
			if (! knowsProgress) {
				AsynchProgressPopup.super.start();
			}
		}
		public void handleException(Throwable e) {
			e.printStackTrace();
		}
	}.dispatch();
}

public void start() {
	startPrivate(false);
}

public void startKeepOnTop() {
	startPrivate(true);
}

/**
 * Insert the method's description here.
 * Creation date: (5/19/2004 3:28:39 PM)
 */
public void stop() {
	new SwingDispatcherAsync (){
		public void runSwing() {
			// stop timer for auto progress
			if (! knowsProgress) {
				AsynchProgressPopup.super.stop();
			}
			getDialog().dispose();
		}
		public void handleException(Throwable e) {
			e.printStackTrace();
		}
	}.dispatch();
}
}