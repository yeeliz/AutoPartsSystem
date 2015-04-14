package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Manufacturer {
	private String name;
	private logic.Console console;
	private DataBase db;
	public Manufacturer(String pName,DataBase pDb,logic.Console pConsole){//this constructor is 
		//for inserting into the database
		name=pName;
		db=pDb;
		console=pConsole;
		insertInDB();
	}
	public Manufacturer(String pName){ //this constructor is for loading from the database
		name=pName;
	}
	private void insertInDB(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Fabricante] (Nombre) VALUES (?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, name);
			if(name!=""){
				pst.executeUpdate();	
				console.printConsole("Manufacturer Added");
			}else{
				console.errorMsg("Please fill up the name");
				throw new Exception("No name to create a Manufacturer");
			}
			pst.close();
		}catch(Exception ex){
			System.out.println(ex.toString());
			console.errorMsg("Not posible to add a manufacture, maybe already exist");
		}
	}
	public String toString(){
		return name;
	}
}
