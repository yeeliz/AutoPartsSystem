package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class Client {
	 protected String fullName, address;
	 /*
	  * Current activity state:
	  * ACTIVE, INACTIVE or SUSPENDED
	  */
	protected String state;
	protected boolean isPerson;
	 
	protected DataBase db;
	 
	public Client(DataBase db){
		this.db = db;
	}
	 
	/*
	 * Add this client to the Data base
	 */
	public void addClient(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Cliente] (Nombre, Estado, EsPersona) VALUES (?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, this.fullName);
			pst.setString(2, this.state);
			pst.setBoolean(3, this.isPerson);
			pst.executeUpdate();	
			
			db.console.printConsole("Inserted general cliente info into Client table.");
			pst.close();
			this.addSubData(); //add the data in subClass
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
	
	//Add data specific to subClass
	protected abstract void addSubData();
}
