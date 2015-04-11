package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public abstract class Client {
	 protected String fullName, address;
	 /*
	  * Current activity state:
	  * ACTIVE, INACTIVE or SUSPENDED
	  */
	protected String state;
	protected String city;
	protected boolean isPerson;
	protected int id;
	protected ArrayList<Integer> telephones = new ArrayList<Integer>(); 
	 
	protected DataBase db;

	 
	/*
	 * Add this client to the DataBase
	 */
	public void addClient(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Cliente] (Nombre, Estado, EsPersona) VALUES (?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, this.fullName);
			pst.setString(2, this.state);
			pst.setBoolean(3,this.isPerson );
			pst.executeUpdate();
			pst.close();
			db.console.printConsole("Inserted general cliente info into Client table.");
			
			this.addSubData(); //add the data in subClass
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
	
	public abstract void fillClient();
	
	//Add data specific to subClass
	protected abstract void addSubData();
	
	public String toString(){
		return this.fullName;
	}
	
	public String getFullName(){
		return this.fullName;
	}
	
	public String getState(){
		return this.state;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getId(){
		return Integer.toString(this.id);
	}
	
	public String getCity(){
		return this.city;
	}
	
	public String getTels(){
		String answer = "";
		for(Integer tel : this.telephones)
			answer += Integer.toString(tel) + "\n";
		return answer;
	}
}
