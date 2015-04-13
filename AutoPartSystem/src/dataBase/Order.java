package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Order {
	private DataBase db;
	private String date, clientName;
	private int taxPer, saleAmount, totalCharged;
	
	private ArrayList<PartsPerOrder> ppo;
	
	public Order(DataBase db, ArrayList<PartsPerOrder> ppo,
			String clientName){
		this.ppo = ppo;
		this.db = db;
		this.clientName = clientName;
	}
	
	public void fillOrder(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Orden] (Fecha, Impuesto, Cliente, "
					+ "MontoDeVenta, TotalCobrado, Cliente) VALUES (?,?,?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, clientName);
			
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Inserted order into Order table.");
	
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
		}
	}
}
