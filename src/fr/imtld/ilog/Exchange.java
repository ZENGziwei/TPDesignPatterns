package fr.imtld.ilog;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

public class Exchange implements Runnable {
	protected Socket sock; 
	DateFormat formater;
	public Exchange(Socket socket, DateFormat formater) {
		this.sock = socket;
		this.formater = formater;
	}
	
	public Socket getSock() {
		return this.sock;
	}
	
	public void run() {
		String strTime = this.formater.format(new Date());
		try {
		OutputStream os = this.sock.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true);
		pw.println(strTime);
		pw.close();
		sock.close();
		}
		catch(IOException e) {
			System.err.println("sock errur");
			}
		
	}
	
}
