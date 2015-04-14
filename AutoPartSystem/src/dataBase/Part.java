package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;;

public class Part{
	// Notes: fix the price problem
	// check a solution for parameters
	// check a 
	private String name;
	private DataBase db;
	private logic.Console console; 
	private Manufacturer madeBy;
	public Part(String pName,Brand pBrand, Manufacturer pMadeBy, DataBase pDb
			,logic.Console pConsole){
		name=pName;
		db=pDb;
		console=pConsole;
		madeBy=pMadeBy;
		insertInDB(pBrand);
	}
	public Part(String pName, DataBase pDb
			,logic.Console pConsole){
		name=pName;
		db=pDb;
		console=pConsole;
	}

	private void insertInDB(Brand pBrand){
		try{
			Connection dbConnection=db.getDbConnection();
			String query = "INSERT INTO [Parte] (Nombre,Fabricante) VALUES (?,?)";
			PreparedStatement  pst = dbConnection.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, madeBy.toString());
			if (name!=""){
				pst.executeUpdate();
			}else{
				throw new Exception("No name or price");
			}
			pst.close();
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
			System.out.println(ex.toString());
			console.errorMsg("Not able to insert a part, maybe the name already exist");
		}
	}
	public void deleteFromDb(){
		try{
			Connection dbConnection=db.getDbConnection();
			String query = "delete from Parte  where nombre=? and nombre not in("
					+ "select p.Nombre from Parte p inner join PartesPorProveedor pp "
					+ "on p.Nombre=pp.NombreParte inner join PartesPorOrden po on "
					+ "pp.ID=po.IdPartesPorProveedor)";
			PreparedStatement  pst = dbConnection.prepareStatement(query);
			pst = dbConnection.prepareStatement(query);
			pst.setString(1,name);
			pst.executeUpdate();
			pst.close();
		}catch (Exception ex){
			System.out.println(ex.toString());
			console.errorMsg("Not able to delete");
		}
	}
	public String toString(){
		return name;
	}
}
