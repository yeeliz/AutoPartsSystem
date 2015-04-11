package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Automobile {
	private String model;
	private String detail;
	private java.sql.Date fabricationYear;
	private Manufacturer manufacturer;
	private logic.Console console;
	private DataBase db;
	
	@SuppressWarnings("deprecation")
	public Automobile(String pModel,String pDetail,int pYear,Manufacturer pManufacturer,
			DataBase pDb,logic.Console pConsole){ //this constructor is 
			//for inserting into the database
		model=pModel;
		detail=pDetail;
		fabricationYear=new Date(pYear,1,1);
		manufacturer=pManufacturer;
		db=pDb;
		console=pConsole;
		insertInDB();
	}
//	@SuppressWarnings("deprecation")
//	public Automobile(String pModel,String pDetail,int pYear,
//			DataBase pDb,logic.Console pConsole){ //this constructor is for loading from the database
//		model=pModel;
//		detail=pDetail;
//		fabricationYear=new Date(pYear,1,1);
//		db=pDb;
//		console=pConsole;
//	}
	private void insertInDB(){
		try{
			//verify the date !!!
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Automovil] (Modelo,Detalle,AnioDeFabricacion,Fabricante)"
					+ " VALUES (?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, model);
			pst.setString(2, detail);
			pst.setDate(3, fabricationYear);
			pst.setString(4, manufacturer.toString());
			pst.executeUpdate();	
			console.printConsole("Automobile inserted");
			pst.close();
		}catch(Exception ex){
			//we need write our own custom msg
			System.out.println(ex.toString());
			console.errorMsg("Not able to insert an automobile");
		}
	}
	public String toString(){
		return model;
	}

}
