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
			PreparedStatement pst=dbConnection.prepareStatement(query);
			
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
			PreparedStatement pst=dbConnection.prepareStatement(query);
			
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
	public ArrayList<Provider> getAllProviders() {
		ArrayList<Provider> values = new ArrayList<Provider>();
		try{
			//look for the brand in the database
			String query="Select * from [Proveedor]";
			PreparedStatement pst=dbConnection.prepareStatement(query);
			
			console.printConsole("Getting all providers");
			
			ResultSet rs=pst.executeQuery();
			System.out.println("Here i am");
			while(rs.next()){				
				Provider p=new Provider(rs.getString("Nombre"),rs.getString("NombreContacto"),
						rs.getString("Direccion"),rs.getString("Ciudad"),rs.getInt("ID"),this,console);


				values.add(p);
			}
		}catch (Exception ex){
			console.errorMsg("Not able to load all the providers");
		}
		return values;
	}
	
	public ArrayList<Client> getAllClients(){
		ArrayList<Client> values = new ArrayList<Client>();
		Person per;
		Company comp;
		try{
			//look for the brand in the database
			String query="Select * from [Cliente]";
			PreparedStatement pst=dbConnection.prepareStatement(query);
			
			console.printConsole("Getting all Client Names");
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				if(rs.getBoolean("EsPersona")){
					per =new Person(rs.getString("Nombre"));
					values.add(per);
				}else{
					comp = new Company(rs.getString("Nombre"));
					values.add(comp);
				}
			}
		}catch (Exception ex){
			console.errorMsg("Not able to get all the Clients");
			System.out.println(ex.toString());
		}
		
		return values;
	}
	public void associatePartWithProvider(Part pPart,Provider pProvider){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Marca] (Nombre) VALUES (?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, name);
			if(name!=""){
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
}
