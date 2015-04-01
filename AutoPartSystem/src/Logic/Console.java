package Logic;

public final class Console {
	
	public static void errorMsg(String msg){
		printConsole("An ERROR has occured!");
		printConsole(msg);
	}
	
	public static void printConsole(String msg){
		System.out.println(msg); //change for gui
	}
}
