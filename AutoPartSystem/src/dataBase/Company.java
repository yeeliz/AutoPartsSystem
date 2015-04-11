package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Company extends Client{
	private int telephone;
	private String contactName, contactDoes;

	public Company(DataBase db, String fullName, 
			String address, String state, String city, 
			int businessId, String contactDoes, int telephone, String contactName) {
		this.db = db;
		this.isPerson = false;
		this.fullName = fullName;
		this.state = state;
		this.address = address;
		this.city = city;
		this.id = businessId;
		this.contactDoes = contactDoes;
		this.telephone = telephone;
		this.contactName = contactName;
	}
	
	//used as a partial fill for listing
	public Company(String name){
		this.fullName = name;
	}

	/*
	 * Add Company specific data Called from super
	 */
	@Override
	protected void addSubData() {
		try{
			addManager();
			
			Connection dbConnection = db.getDbConnection();
			String query = "DECLARE @ID int\n" +
			"SELECT @ID = @@IDENTITY FROM [Engargado]\n" +
			"INSERT INTO [Organizaciones] (CedulaJuridica, Nombre, Direccion, Ciudad, IDEncargado)"+
			"VALUES (?,?,?,?,@ID)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			
			pst.setInt(1, this.id);
			pst.setString(2, this.fullName);
			pst.setString(3, this.address);
			pst.setString(4, this.city);
			
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Inserted BUSINESS Client subclass data into PERSONA table.");
			
			
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
	
	private void addManager(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Engargado] (Nombre,Telefono,Cargo,Organizacion) VALUES (?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, this.contactName);
			pst.setInt(2, this.telephone);
			pst.setString(3, this.contactDoes);
			pst.setString(4, this.fullName);//Business Name
			pst.executeUpdate();
			pst.close();
			db.console.printConsole("Inserted Business Manager");
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
		
		
		
	}

	@Override
	public void fillClient() {
		// TODO Auto-generated method stub
		
	}

	public String getContactName() {
		return this.contactName;
	}

	public String getContactDoes() {
		return this.contactDoes;
	}



}
