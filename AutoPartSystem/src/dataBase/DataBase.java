package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	/* 
	 * Connect to server 
	 * Return: True/False
	 */
	public boolean connect(){
		try {
			dbConnection = DriverManager.getConnection(dataBaseUrl);
			
			//Successful connection
			if(dbConnection != null){
				Console.printConsole(conSuccess);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Console.errorMsg(conError);
		}
		return false;
	}
}
