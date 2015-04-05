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
			if(name!=""){ //maybe move to gui || logic side
				pst.executeUpdate();	
			}else{
				//need to show msg's to console too 
				throw new Exception("No name to create a brand");
			}
			pst.close();
		}catch(Exception ex){
			//we need write our own custom msg
			console.errorMsg(ex.toString());
		}
	}
	public String getName(){
		return name;
	}
}

