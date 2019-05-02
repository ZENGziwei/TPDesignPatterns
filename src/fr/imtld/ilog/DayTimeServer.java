package fr.imtld.ilog;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DayTimeServer implements Runnable {
	public static final int NORMAL = 0, PORT_CONTENTION = 1;
	protected volatile int port = 13;
	protected ServerSocket _sockSvr;
	protected Thread _thr;
	protected ArrayList<ServerListener> _lsn =new ArrayList<ServerListener>();
	protected boolean _bRun;

	public DayTimeServer() {
	
	}
	public void addServerlistener(ServerListener lsn) {
		_lsn.add(lsn);
		
	}
	
	public void remove(ServerListener lsn) {
		_lsn.remove(lsn);	
	}
	
	public void start(int iPort) {
		synchronized (this) {
			if (_thr == null) {
				this.port = iPort;
				_thr = new Thread(this);
				_thr.setDaemon(true); // exits if no non-daemon threads remain
				_thr.start();
			}
		}
	}

	public void stop() {
		try {
			_sockSvr.close();
		} catch (Exception e) {
		}
	}

	protected void fireStarted(int iPort) {
		for(ServerListener lsn : _lsn) {
		lsn.serverStarted(port);}
	}

	protected void fireStateChange(int iCause, int iPort) {
		for(ServerListener lsn : _lsn) {
		lsn.serverStopped(iCause, iPort);}
	}
	
	protected void init() {
		_bRun = true; 
		}

	protected void changeinfo(Socket sock, DateFormat formater) throws IOException {
		String strTime = formater.format(new Date());
		OutputStream os = sock.getOutputStream();
		PrintWriter pw = new PrintWriter(os, true);
		pw.println(strTime);
		pw.close();
		sock.close();
		
	}
	public void run() {
		init();
		try {
			fireStarted(port);
			DateFormat formater = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
			_sockSvr = new ServerSocket(port);
			try {
				while (_bRun) {
					Socket sock = _sockSvr.accept();
					changeinfo(sock,formater);
//					String strTime = formater.format(new Date());
//					OutputStream os = sock.getOutputStream();
//					PrintWriter pw = new PrintWriter(os, true);
//					pw.println(strTime);
//					pw.close();
//					sock.close();
				}
			} catch (IOException e) {
				fireStateChange(NORMAL, port);
			}
		} catch (IOException e) {
			fireStateChange(PORT_CONTENTION, port);
		}
		synchronized (this) {
			_thr = null;
		}
	}
}
