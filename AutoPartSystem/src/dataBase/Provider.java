package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Provider {
	private String name;
	private String contactName;
	private String direction;
	private String city;
	private ArrayList<String> phoneNumbers;
	private DataBase db;
	private logic.Console console;
	
	public Provider(String pName, String pContactName, String pDirection, String pCity,
			String pNumber,DataBase pDb, logic.Console pConsole) {
		name= new String();
		contactName=new String();
		direction=new String();
		city=new String();
		phoneNumbers=new ArrayList<>();
		name=pName;
		contactName=pContactName;
		direction=pDirection;
		city=pCity;
		db=pDb;
		console=pConsole;
		insertInDB();
		addPhoneNumber(pNumber);
	}
	private void addPhoneNumber(String pNumber){
		phoneNumbers.add(pNumber);
		
		//The number has to be inserted on the database
	}
	private void insertInDB(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Proveedor] (Direccion,Ciudad,Nombre,NombreContacto)"
					+ " VALUES (?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, direction);
			pst.setString(2, city);
			pst.setString(3, name);
			pst.setString(4, contactName);
			pst.executeUpdate();	
			pst.close();
			console.printConsole("Provider added");
		}catch(Exception ex){
			console.errorMsg("It was not posible to insert in the database");
			System.out.println(ex.toString());
		}
	}

	
}
