package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class Company extends Client{
	private int businessId;
	private int managerId;
	private String address;
	private String city;

	public Company(DataBase db, String fullName, 
			String address, String state, String city, 
			int businessId, int managerId) {
		super(db);
		this.isPerson = false;
		this.fullName = fullName;
		this.state = state;
		this.address = address;
		this.city = city;
		this.businessId = businessId;
		this.managerId = managerId;
	}

	/*
	 * Add Company specific data Called from super
	 */
	@Override
	protected void addSubData() {
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Organizaciones] (CedulaJuridica, Nombre, Direccion, Ciudad) VALUES (?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setInt(2, this.businessId);
			pst.setString(1, this.fullName);
			pst.setString(3, this.address);
			pst.setString(4, this.city);
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Inserted BUSINESS Client subclass data into PERSONA table.");
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}





}
