package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Provider {
	private String name;
	private String contactName;
	private String direction;
	private String city;
	private ArrayList<String> phoneNumbers;
	private DataBase db;
	private logic.Console console;
	private int id;
	
	public Provider(String pName, String pContactName, String pDirection, String pCity,
			String pNumber,DataBase pDb, logic.Console pConsole) {
		name= new String();
		contactName=new String();
		direction=new String();
		city=new String();
		phoneNumbers=new ArrayList<>();
		name=pName;
		contactName=pContactName;
		direction=pDirection;
		city=pCity;
		db=pDb;
		console=pConsole;
		insertInDB();
		getIdFromDb();
		addPhoneNumber(pNumber);
	}
	public Provider(String pName, String pContactName, String pDirection, String pCity,
			int pId,DataBase pDb,logic.Console pConsole) {
		name= new String();
		contactName=new String();
		direction=new String();
		city=new String();
		phoneNumbers=new ArrayList<>();
		name=pName;
		contactName=pContactName;
		direction=pDirection;
		city=pCity;
		id=pId;
		db=pDb;
		console=pConsole;
		System.out.println("Here 1234567");
		loadPhoneNumbers();
		System.out.println("Here Joshua");		
	}
	public void addPhoneNumber(String pNumber){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [TelefonoProveedor] (IDProveedor,Numero) VALUES(?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setInt(1, id);
			pst.setString(2, pNumber);
			pst.executeUpdate();	
			pst.close();
			phoneNumbers.add(pNumber);
			console.printConsole("Provider phone added");
		}catch(Exception ex){
			console.errorMsg("It was not posible to add the provider phone in the database");
			System.out.println(ex.toString());
		}
	}
	private void insertInDB(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Proveedor] (Direccion,Ciudad,Nombre,NombreContacto)"
					+ " VALUES (?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, direction);
			pst.setString(2, city);
			pst.setString(3, name);
			pst.setString(4, contactName);
			pst.executeUpdate();	
			pst.close();
			console.printConsole("Provider added");
		}catch(Exception ex){
			console.errorMsg("It was not posible to insert in the database");
			System.out.println(ex.toString());
		}
	}
	private void getIdFromDb(){
		try{
			//look for the brand in the database
			String query="SELECT IDENT_CURRENT('Proveedor') AS LAST_id";
			PreparedStatement pst=db.getDbConnection().prepareStatement(query);
			console.printConsole("Getting provider id");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				id=rs.getInt("LAST_id");
			}
			
		}catch (Exception ex){
			System.out.println(ex.toString());
			console.errorMsg("Not able to get the provider id");
		}
	}
	private void loadPhoneNumbers(){
		try{
			//look for the brand in the database
			String query="SELECT Numero FROM [TelefonoProveedor] "
					+ "WHERE IDProveedor= ?";
			PreparedStatement pst=db.getDbConnection().prepareStatement(query);
			pst.setInt(1, id);
			console.printConsole("Getting provider phones");
			ResultSet rs=pst.executeQuery();
			String number="";
			while(rs.next()){
				
				number=rs.getString("Numero");
				phoneNumbers.add(number);
			}
		}catch (Exception ex){
			System.out.println(ex.toString());
			console.errorMsg("Not able to get the provider phones");
		}
	}

	public String toString(){
		return name+", "+city;
	}
	public int getId(){
		return id;
	}
		
}
