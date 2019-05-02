package fr.imtld.ilog;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
public class watch {
	Date date = new Date();
    protected void displayLocalTime() {
            System.out.println(date);
    }
    public static void main( String args[] ) {
            watch watch = new watch();

          //  watch.displayLocalTime();
            watch.displayServerTime(args[0]);
            
    }
    
    public void displayServerTime(String strServer) {
    	try {
    		InetAddress addr = InetAddress.getByName(strServer);
        	Socket sock = new Socket( addr, 2013 );
        	InputStream is = sock.getInputStream();
        	InputStreamReader isr = new InputStreamReader( is );
        	BufferedReader br = new BufferedReader( isr );
        	System.out.println(is);
 	
    		String strDateTime = br.readLine();
    		System.out.println("ServerTime:"+strDateTime);
    		
        	sock.close();
    	}
    		catch(IOException e) {System.out.println("erreur!");}
    }
    
}

