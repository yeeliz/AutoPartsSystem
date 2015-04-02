package logic;

import javax.swing.JTextArea;

public class Console {
	
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
		console.setText("<< " + msg + "\n" + text);
	}
}
