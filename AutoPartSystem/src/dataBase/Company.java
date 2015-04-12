package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Company extends Client{
	private int telephone, contactsId;
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
	
	public Company(DataBase db, String name){
		this.fullName = name;
		this.db = db;
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
			"SELECT @ID = @@IDENTITY FROM [Encargado]\n" +
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
			String query = "INSERT INTO [Encargado] (Nombre,Telefono,Cargo,Organizacion) VALUES (?,?,?,?)";
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
	
	protected void updateSubData(){
		try{
			updateManager();
			
			Connection dbConnection = db.getDbConnection();
			String query ="UPDATE [Organizaciones] "+
			"SET CedulaJuridica = ?, Direccion = ?, Ciudad = ? "
			+ "WHERE Nombre = ?";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			
			pst.setInt(1, this.id);
			
			pst.setString(2, this.address);
			pst.setString(3, this.city);
			pst.setString(4, this.fullName);
			
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Updated BUSINESS Client subclass data into PERSONA table.");
			
			
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
	
	private void updateManager(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "UPDATE [Encargado] " +
			"SET Nombre = ?, Telefono = ?, Cargo = ?, Organizacion = ? " +
			"WHERE ID = ?";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, this.contactName);
			pst.setInt(2, this.telephone);
			pst.setString(3, this.contactDoes);
			pst.setString(4, this.fullName);//Business Name
			pst.setInt(5, this.contactsId);
			pst.executeUpdate();
			pst.close();
			db.console.printConsole("Updated Business Manager");
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
	

	@Override
	public void fillClient() {
		db.console.printConsole("Obteniendo los datos del Cliente tipo Empresarial");
		
		try{
			Connection dbConnection = db.getDbConnection();
			String sql = "SELECT c.*, o.* FROM Cliente c "+
			"INNER JOIN Organizaciones o ON c.Nombre = o.Nombre " +
			"WHERE c.Nombre = ? ";
	       
			PreparedStatement pst=dbConnection.prepareStatement(sql);
			
			//pass the variable PK we want to the query
			pst.setString(1, this.fullName.toString());			
			
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				this.state  = rs.getString("Estado");
	           	this.isPerson = rs.getBoolean("EsPersona");
	           	this.id = rs.getInt("CedulaJuridica");
	           	this.address = rs.getString("Direccion");
	           	this.city = rs.getString("Ciudad");
	           	this.contactsId = rs.getInt("IDEncargado");
			}
			pst.close();
			
			//Now we need to get copany contacts info
			sql = "SELECT * FROM Encargado En WHERE ID = ?";
			
			pst=dbConnection.prepareStatement(sql);
			pst.setInt(1, this.contactsId);
			
			rs = pst.executeQuery();
			while(rs.next()){
				this.telephones.add(rs.getInt("Telefono"));
			}
			pst.close();
			
		}catch(Exception e){
	    	 db.console.errorMsg();
	    	 System.out.println(e);
	     }
		
	}

	public String getContactName() {
		return this.contactName;
	}

	public String getContactDoes() {
		return this.contactDoes;
	}



}
