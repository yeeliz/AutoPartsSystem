package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Person extends Client{
	

	public Person(DataBase db, String fullName, String address, 
			String state,int id,String city, ArrayList<Integer> telephones){
		this.db = db;
		this.fullName = fullName;
		this.state = state;
		this.isPerson = true;
		this.address = address;
		this.city = city;
		this.id = id;
		this.telephones = telephones;
	}
	
	//used as a partial fill for listing
	public Person(String name){
		this.fullName = name;
	}
	
	public Person(DataBase db, String name){
		this.db = db;
		this.fullName = name;
	}
	
	@Override
	protected void addSubData() {
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Persona] (Nombre, Cedula, Direccion, Ciudad) VALUES (?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, this.fullName);
			pst.setInt(2, this.id);
			pst.setString(3, this.address);
			pst.setString(4, this.city);
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Inserted Personal Client subclass data into PERSONA table.");
			
			addTelephones();
			db.console.printConsole("Personal client telephones added!");
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
	
	protected void updateSubData(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "UPDATE [Persona] "+
			"SET Cedula = ?, Direccion = ?, Ciudad =? "+
			"WHERE Nombre = ? ";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setInt(1, this.id);
			pst.setString(2, this.address);
			pst.setString(3, this.city);
			pst.setString(4, this.fullName);
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Updated Personal Client subclass data into PERSONA table.");
			
			updateTels();
			db.console.printConsole("Personal client telephones updated!");
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
	
	private void addTelephones() throws SQLException{
		Connection dbConnection = db.getDbConnection();
		String query = "INSERT INTO [Telefonos] (NombrePersona, numero) VALUES (?,?)";
		for(Integer tel : telephones){
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(1, this.fullName);
			pst.setInt(2, tel);
			pst.executeUpdate();
			pst.close();
		}
	}
	
	private void updateTels() throws SQLException{
		Connection dbConnection = db.getDbConnection();
		String query = "UPDATE [Telefonos] "+
		"SET numero = ? WHERE NombrePersona = ?";
		for(Integer tel : telephones){
			PreparedStatement pst = dbConnection.prepareStatement(query);
			pst.setString(2, this.fullName);
			pst.setInt(1, tel);
			pst.executeUpdate();
			pst.close();
		}
	}

	@Override
	public void fillClient(){
		db.console.printConsole("Obteniendo los datos del Cliente tipo persona");
		
		try{
			Connection dbConnection = db.getDbConnection();
			String sql = "SELECT c.*, p.* FROM Cliente c "+
			"INNER JOIN Persona p ON c.Nombre = p.Nombre " +
			"WHERE c.Nombre = ? ";
	       
			PreparedStatement pst=dbConnection.prepareStatement(sql);
			
			//pass the variable PK we want to the query
			pst.setString(1, this.fullName.toString());			
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				this.state  = rs.getString("Estado");
	           	this.isPerson = rs.getBoolean("EsPersona");
	           	this.id = rs.getInt("Cedula");
	           	this.address = rs.getString("Direccion");
	           	this.city = rs.getString("Ciudad");
			}
			pst.close();
			
			//Now we need to get the telephone numbers
			sql = "SELECT * FROM Telefonos WHERE NombrePersona = ?";
			
			pst=dbConnection.prepareStatement(sql);
			pst.setString(1, this.fullName);
			
			rs = pst.executeQuery();
			while(rs.next()){
				this.telephones.add(rs.getInt("Numero"));
			}
			pst.close();
			
		}catch(Exception e){
	    	 db.console.errorMsg("Not able to insert the client");
	    	 System.out.println(e.toString());
	     }
	}
}
