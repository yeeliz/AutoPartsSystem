package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;;

public class Part{
	// Notes: fix the price problem
	// check a solution for parameters
	// check a 
	private String name, price;
	private DataBase db;
	private logic.Console console; 
	public Part(String pName,Brand pBrand, Manufacturer pMadeBy, DataBase pDb
			,logic.Console pConsole){
		name=pName;
		price="0";
		db=pDb;
		console=pConsole;
		insertInDB(pBrand, pMadeBy);
	}
	private void insertInDB(Brand pBrand, Manufacturer pMadeBy){
		try{
			Connection dbConnection=db.getDbConnection();
			String query = "INSERT INTO [Parte] (Nombre,Precio, Fabricante) VALUES (?,?,?)";
			PreparedStatement  pst = dbConnection.prepareStatement(query);
			pst.setString(1, name);
			pst.setInt(2, Integer.parseInt(price));
			pst.setString(3, pMadeBy.toString());
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
					+ " set @BrandName =?"
					+ " set @PartName=?"
					+ " exec AssociateBrandAndPart @PartName ,@BrandName";
			pst = dbConnection.prepareStatement(query);
			pst.setString(1,pBrand.toString());
			pst.setString(2,name);
			pst.executeUpdate();
			pst.close();
			console.printConsole("The insertion of the Part has done");
		}	catch(Exception ex){
			console.errorMsg(ex.toString());
		}
	}	
}
