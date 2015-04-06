package logic;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTextArea;

public class Console {
	private Calendar calendar = GregorianCalendar.getInstance();
	private JTextArea console;
	private String text = "";
	
	public Console(JTextArea console){
		this.console = console;
	}
	
	public void errorMsg(String msg){
		printConsole("An ERROR has occured!");
		printConsole(msg);
	}
	
	public void printConsole(String msg){
		text += "[" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + 
				calendar.get(Calendar.MINUTE)+"] << " + msg + "\n";
		
		console.setText(text);
		
	}
}
