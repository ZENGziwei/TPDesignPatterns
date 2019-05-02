package fr.imtld.ilog;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

public class DayTimeServerMulti extends DayTimeServer {
	Factorymulti f;
	public DayTimeServerMulti() {
		super();
	//	Factory.setFactory(new Factorymulti());
		this.f = (Factorymulti) Factory.getFactory();
	}
	@Override
	protected void changeinfo(Socket sock, DateFormat formater) throws IOException {
	
		Thread thr = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Serveurmulti");
				String strTime = formater.format(new Date());
				try {
				OutputStream os = sock.getOutputStream();
				PrintWriter pw = new PrintWriter(os, true);
				pw.println(strTime);
				pw.close();
				sock.close();
				}catch(IOException e) {
					System.err.println("sock errur");
				}
			}
			
		});
		thr.start();
		
	}
	
//	public void run() {
//		System.out.println("DayTimeServerMulti starting");
//		init();
//		try {
//			fireStarted(port);
//			DateFormat formater = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
//			_sockSvr = new ServerSocket(port);
//			try {
//				while (_bRun) {
//					Socket sock = _sockSvr.accept();
//					Exchange exc = f.createExchange(sock,formater);
//					Thread thr = new Thread(exc);
//					thr.start();
//					this.pause(1000);
//				}
//			} catch (IOException e) {
//				fireStateChange(NORMAL, port);
//			}
//		} catch (IOException e) {
//			fireStateChange(PORT_CONTENTION, port);
//		}
//		synchronized (this) {
//			_thr = null;
//		}
//	
//	
//		
//	//	super.run();
//	}
	protected void pause(long ms) {
		synchronized (this) {
			try {
				wait(ms);
				System.out.println("Wait");
			} catch (InterruptedException e) {
			}
		}
	}
}