package fr.imtld.ilog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class Factory {
	 private static Factory INSTANCE = new Factory();
	 protected Shell sshell;
	 private Group grpServer;
	 protected DayTimeServer daytimeserver;
	 
	 
	    public static Factory getFactory() {
	        return INSTANCE;
	    }
	    public static void setFactory( Factory fact ) {
	        INSTANCE = fact;
	    }
	    public DayTimeUI createDayTimeUI() {
	    	return new DayTimeUI();
	    }

	    public DayTimeServer createDayTimeServer() {
	    	if (daytimeserver == null) {
	    		daytimeserver = new DayTimeServer();
	    	}
	    	return daytimeserver;
	    }
	    
//	    public Shell getShell() {
//	    	if (sshell == null) {
//	    		sshell = new Shell();
//	    	}
//	    	return sshell;	
//	    }
	    
//	    public Group getGroup(Shell sShell) {
//	    	if (grpServer == null) {
//	    		grpServer = new Group(sShell, SWT.NONE);
//	    	}
//	    	return grpServer;	
//	    }

}
