package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Person extends Client{
	
	private int id;
	private String address;
	private String city;

	public Person(DataBase db, String fullName, String address, 
			String state,int id,String city){
		this.db = db;
		this.fullName = fullName;
		this.state = state;
		this.isPerson = true;
		this.address = address;
		this.city = city;
		this.id = id;
	}
	
	//used as a partial fill for listing
	public Person(String name){
		this.fullName = name;
	}
	
	@Override
	protected void addSubData() {
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Persona] (Nombre, Cedula, Direccion, Ciudad) VALUES (?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, this.fullName);
			pst.setInt(2, this.id);
			pst.setString(3, this.address);
			pst.setString(4, this.city);
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Inserted Personal Client subclass data into PERSONA table.");
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
}
