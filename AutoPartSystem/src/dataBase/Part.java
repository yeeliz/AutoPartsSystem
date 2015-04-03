package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;;

public class Part{
	// Notes: fix the price problem
	// check a solution for parameters
	// check a 
	private String name, price,brand, madeBy;
	private DataBase db;
	private logic.Console console; 
	public Part(String pName,String pPrice,Brand pBrand, String pMadeBy, DataBase pDb
			,logic.Console pConsole){
		name=pName;
		price=pPrice;
		madeBy=pMadeBy;
		db=pDb;
		console=pConsole;
		insertInDB(pBrand);
	}
	private void insertInDB(Brand pBrand){
		try{
			Connection dbConnection=db.getDbConnection();
			String query = "INSERT INTO [Parte] (Nombre,Precio) VALUES (?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, price);
			if (name!="" && price!=""){
				pst.executeUpdate();
			}else{
				throw new Exception("No name or price");
			}
			pst.close();
			//is a database bug, in the parameter they can fill with a big sql server
			//code to be excecuted !!!!!
			query = " declare @BrandName varchar(50) "
					+ " declare @PartName varchar(50) "
					+ " set @BrandName = "+pBrand.getName()
					+ " set @PartName= "+name+
					" exec getSalesperson @BrandName, @PartName ";
			pst = dbConnection.prepareStatement(query);
			pst.executeUpdate();		
			
		}	catch(Exception ex){
			console.errorMsg(ex.toString());
		}
	}	
}
