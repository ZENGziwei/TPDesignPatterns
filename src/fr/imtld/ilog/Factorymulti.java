package fr.imtld.ilog;

import java.net.Socket;
import java.text.DateFormat;

public class Factorymulti extends Factory{

	@Override
	  public DayTimeServer createDayTimeServer() {
		if(daytimeserver==null) {
			daytimeserver = new DayTimeServerMulti();
		}
	    	return daytimeserver;
	    }

//	   public Exchange createExchange(Socket socket, DateFormat formater) {
//		   return new Exchange(socket,formater);
//	   }
}
