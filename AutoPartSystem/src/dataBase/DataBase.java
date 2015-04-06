package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logic.Console;

/*
 * DataBase object to handle
 * all communication with SQL database using JDBC 
 */
public class DataBase {
	
	//Default DB console msgs
	private String conError = "Error establishing DB connection";
	private String conSuccess = "Connection Established!";
	
	private String dataBaseUrl =
"jdbc:sqlserver://localhost;integratedSecurity=true;database=PartsSystem";
	
	//Connection to DB
	Connection dbConnection;
	Console	console;
	
	public DataBase(Console console){
		this.console = console;
	}
	
	/* 
	 * Connect to server 
	 * Return: True/False
	 */
	public boolean connect(){
		try {
			dbConnection = DriverManager.getConnection(dataBaseUrl);
			
			//Successful connection
			if(dbConnection != null){
				console.printConsole(conSuccess);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			console.errorMsg(conError);
		}
		return false;
	}
	public Connection getDbConnection(){
		return dbConnection;
	}
	
	public ArrayList<Brand> getAllBrands(){
		ArrayList<Brand> values = new ArrayList<Brand>();
		try{
			//look for the brand in the database
			String query="Select * from [Marca]";
			PreparedStatement pst=getDbConnection().prepareStatement(query);
			
			console.printConsole("Getting all brand Names");
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				Brand brand=new Brand(rs.getString("Nombre"));
				values.add(brand);
			}
		}catch (Exception ex){
			console.errorMsg();
		}
		
		return values;
	}

	public ArrayList<Manufacturer> getAllManufactures() {
		ArrayList<Manufacturer> values = new ArrayList<Manufacturer>();
		try{
			//look for the brand in the database
			String query="Select * from [Fabricante]";
			PreparedStatement pst=getDbConnection().prepareStatement(query);
			
			console.printConsole("Getting all Manufacturer Names");
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				Manufacturer m=new Manufacturer(rs.getString("Nombre"));
				values.add(m);
			}
		}catch (Exception ex){
			console.errorMsg();
		}
		
		return values;
	}
}
