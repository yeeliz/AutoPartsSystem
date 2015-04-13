package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	private DataBase db;
	private String date, clientName;
	private int taxPer = 13, saleAmount = 0, totalCharged;
	
	private ArrayList<PartsPerOrder> ppo;
	
	public Order(DataBase db, ArrayList<PartsPerOrder> ppo,
			String clientName){
		this.ppo = ppo;
		this.db = db;
		this.clientName = clientName;
		
		calcTotal();
		setDate();
	}
	
	private void calcTotal(){
		for(PartsPerOrder partsOrder: ppo){
			this.saleAmount += partsOrder.price * partsOrder.amount;
		}
		//this needs work!
		this.totalCharged = (saleAmount * (taxPer / 100)) + saleAmount;
	}
	
	private void setDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.date = dateFormat.format(date);
	}
	
	public void fillOrder(){
		try{
			Connection dbConnection = db.getDbConnection();
			String query = "INSERT INTO [Orden] (Fecha, Impuesto, "
					+ "MontoDeVenta, TotalCobrado, Cliente) VALUES (?,?,?,?,?)";
			PreparedStatement pst = dbConnection.prepareStatement(query);
			
			pst.setString(1, date);
			pst.setInt(2, taxPer); //tax %
			pst.setInt(3, saleAmount);//sale amount
			pst.setFloat(4, totalCharged);//total
			pst.setString(5, clientName);
			
			pst.executeUpdate();	
			pst.close();
			db.console.printConsole("Inserted order into Order table.");
			insertPartsPerOrders();
		}catch(Exception ex){ //need to add custom msg's
			db.console.errorMsg(ex.toString());
			System.out.println(ex);
		}
	}
	
	private void insertPartsPerOrders(){
		Connection dbConnection = db.getDbConnection();
		String query = "DECLARE @ID int " +
		"SELECT @ID = @@IDENTITY FROM [Orden] " +
		"INSERT INTO [PartesPorOrden] (IdOrden, IdPartesPorProveedor, "+
		"Precio, Cantidad)"+
		"VALUES (@ID,?,?,?)";
		
		for(PartsPerOrder partsOrder: ppo){
			try{
				PreparedStatement pst = dbConnection.prepareStatement(query);
				
				
				pst.setInt(1, partsOrder.idPartsPerProvider);
				pst.setInt(2, partsOrder.price);
				pst.setInt(3, partsOrder.amount);
				
				pst.executeUpdate();	
				pst.close();
				db.console.printConsole("Inserted a \"PartPerOrder\" relation");
			}catch(Exception e){
				System.out.println(e);
				db.console.printConsole("Could not insert PartPerOrder relation");
			}
		}
	}
}
