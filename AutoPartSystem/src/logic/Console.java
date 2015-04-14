package logic;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTextArea;

public class Console {
	private JTextArea console;
	
	public Console(JTextArea console){
		this.console = console;
		
	}
	
	public void errorMsg(String msg){
		printConsole("V--ERROR!--V");
		printConsole(msg);
	}
	
	public void errorMsg(){
		printConsole("ERROR. Could not do last request!");
	}
	
	public void printConsole(String msg){
		Calendar calendar = GregorianCalendar.getInstance();
		console.append("[" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + 
				calendar.get(Calendar.MINUTE)+","+calendar.get(Calendar.SECOND)+"] << " + msg + "\n");
	}
	

}
