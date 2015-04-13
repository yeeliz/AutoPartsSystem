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

	public ArrayList<String> getAllAutomobile(){
		ArrayList<String> values = new ArrayList<String>();
		try{
			//look for the brand in the database
			String query="SELECT DISTINCT ModelO from [Automovil]";
			PreparedStatement pst=dbConnection.prepareStatement(query);
			
			console.printConsole("Getting all automobile Names");
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				String a=rs.getString("Modelo");
			values.add(a);
			}
		}catch (Exception ex){
			console.errorMsg();
		}
		
		return values;
	}
	public ArrayList<PartPerProvider> getAllPartsPerProvider(){
		ArrayList<PartPerProvider> values = new ArrayList<PartPerProvider>();
		try{
			//look for the brand in the database
			String query="Select * from [PartesPorProveedor]";
			PreparedStatement pst=dbConnection.prepareStatement(query);
			
			console.printConsole("Getting all Parts per Provider");
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				PartPerProvider p=new PartPerProvider(rs.getInt("ID"), rs.getString("NombreParte"),
						rs.getInt("IDProveedor"),this);
				values.add(p);
			}
		}catch (Exception ex){
			console.errorMsg("Not able to get all PartPerProvider");
		}
		
		return values;
	}
	public String getProviderName(int ID){
		String name="";
		try{
			//look for the brand in the database
			String query="SELECT Nombre FROM [Proveedor]"
					+ " WHERE ID=?";
			PreparedStatement pst=dbConnection.prepareStatement(query);
			pst.setInt(1,ID);
			console.printConsole("Getting Provider Name");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				name=rs.getString("Nombre");				
			}
		}catch (Exception ex){
			console.errorMsg();
		}
		
		return name;
		
	}
	public ArrayList<Manufacturer> getAllManufacturers() {
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
	public ArrayList<Part> getAllParts(){
		//doesn't take the name of the manufacturer
		ArrayList<Part> values = new ArrayList<Part>();
		try{
			//look for the brand in the database
			String query="Select * from [Parte]";
			PreparedStatement pst=dbConnection.prepareStatement(query);
			
			console.printConsole("Getting all parts");
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()){				
				Part p=new Part(rs.getString("Nombre"),this,console);
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
	public void associatePartWithAutomobile(Part pPart,String pAutomobile, int  anioFrabricacion){
		try{
			String query="INSERT INTO [PartesDeAutomovil] (NombreParte,ModeloAutomovil, AnioDeFabricacion)"
					+ "VALUES(?,?,?)";
			PreparedStatement pst=dbConnection.prepareStatement(query);
			pst.setString(1,pPart.toString());
			pst.setString(2,pAutomobile);
			pst.setInt(3, anioFrabricacion);
			pst.executeUpdate();
			pst.close();
			console.printConsole("Association done");
			
		}catch(Exception ex){
			System.out.println(ex.toString());
			console.errorMsg("Not able to make de association, maybe already exist");
		}
	}
	
	public ArrayList<PartPerProvider> getProvidersSellPart(String partName){
		ArrayList<PartPerProvider> order = new ArrayList<PartPerProvider>();
		
		try{
			String query = "SELECT * FROM PartesPorProveedor " +
			"WHERE NombreParte = ?";
			
			PreparedStatement pst = dbConnection.prepareStatement(query);	
			pst.setString(1, partName);
			console.printConsole("Getting all Providers with that part");
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				int tId = rs.getInt("ID");
				int idProvider = rs.getInt("IDProveedor");
				String partsName = rs.getString("NombreParte");
				int earning = rs.getInt("PorcentajeDeGanancia");
				int price = rs.getInt("Precio");
				int realPrice = price + ((earning/100) * price);
				
				PartPerProvider or = new PartPerProvider(idProvider, 
						partsName,tId, realPrice, this);
				order.add(or);
			}
		
		}catch(Exception e){
			console.errorMsg("Not able to get providers for that part from DB");
			System.out.println(e);
		}
		
		return order;
	}
	public ArrayList<Integer> getAllFabricationYears(String model){
		ArrayList<Integer> years=new ArrayList<Integer>();
		String sql = "SELECT AnioDeFabricacion FROM Automovil" +
				" WHERE Modelo = ?";
		try{
			PreparedStatement pst= dbConnection.prepareStatement(sql);
			pst.setString(1, model);
			
			console.printConsole("Obtaining all fabrication years from " + model );
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				years.add(rs.getInt("AnioDeFabricacion"));
			}
		}catch(Exception e){
			console.errorMsg(e.toString() + "");
		}	
		return years;
	}

	public ArrayList<String> getAllPartsForAutoMobile(String model, String year) {
		ArrayList<String> partNames = new ArrayList<String>();
		
		
		String sql = "SELECT NombreParte FROM PartesDeAutoMovil " +
		"WHERE ModeloAutomovil = ? AND AnioFabricacion = ? ";
		try{
			PreparedStatement pst= dbConnection.prepareStatement(sql);
			
			//pass the variable PK we want to the query
			pst.setString(1, model);
			pst.setString(2, year);
			
			console.printConsole("Obtaining all parts for a Vehicle of model: " +
			model + " and year: " + year);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				partNames.add(rs.getString("NombreParte"));
				
			}
			
		}catch(Exception e){
			console.errorMsg(e + "");
		}
		
		
		return partNames;
	}
}
