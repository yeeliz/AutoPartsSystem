package logic;

import java.util.ArrayList;

public class Parser {
	
	private Console console;
	
	public Parser(Console console){
		this.console = console;
	}
	
	
	//get telephones or telephone from textarea
	public ArrayList<Integer> telParser(String tels){
		ArrayList<Integer> telephones = new ArrayList<Integer>();
		
		try{
			if(!tels.contains("\n")){
				telephones.add(Integer.parseInt(tels));
			}else{
				while(tels.contains("\n")){
					telephones.add(Integer.parseInt(tels.substring(0,tels.indexOf("\n"))));
					tels = tels.substring(tels.indexOf("\n") + 1, tels.length());
				}
				telephones.add(Integer.parseInt(tels));
				
			}
		}catch(Exception e){
			console.errorMsg("Could not parse tels to int");
		}
		
		return telephones;
	}
}
