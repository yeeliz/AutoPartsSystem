package dataBase;

import java.sql.PreparedStatement;
import java.sql.Connection;

public class Brand {
	private String name;
	private logic.Console console;
	private DataBase db;
	public Brand(String pName,DataBase pDb,logic.Console pConsole){
		name=pName;
		db=pDb;
		console=pConsole;
		insertInDB();
	}
	private void insertInDB(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Marca] (Nombre) VALUES (?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, name);
			if(name!=""){
				pst.executeUpdate();	
			}else{
				throw new Exception("No name to create a brand");
			}
			pst.close();
		}catch(Exception ex){
			console.errorMsg(ex.toString());
		}
	}
	public String getName(){
		return name;
	}
}

