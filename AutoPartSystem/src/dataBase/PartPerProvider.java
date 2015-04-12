package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.omg.IOP.ProfileIdHelper;

import logic.Console;
public class PartPerProvider {
	private Part part;
	private Provider provider;
	private String partName;
	private String providerName;
	private int cost;
	private int profitPorcentage;
	private DataBase db;
	private Console console;
	private int id;
	public PartPerProvider (Part pPart,Provider pProvider,int pProfitPorcentage,
			int pCost,DataBase pDb,Console pConsole){
		part=pPart;
		provider=pProvider;
		profitPorcentage=pProfitPorcentage;
		cost=pCost;
		db=pDb;
		console=pConsole;
		insertInDB();
		getID();
	}
	public PartPerProvider(int pId,String pPartName,int providerID,DataBase pDb){
		
		partName=pPartName;
		id=pId;
		db=pDb;
		console=db.console;
		providerName=db.getProviderName(providerID);
	}
	
	
	public PartPerProvider(int idProvider, String partsName, int price) {
		partName = partsName;
		cost = price;
		providerName=db.getProviderName(idProvider);
	}
	public void insertInDB(){
		try{
			String query = "INSERT INTO [PartesPorProveedor] (IDProveedor,NombreParte,PorcentajeDeGanancia,Precio) "
					+ "VALUES (?,?,?,?)";
			PreparedStatement pst = db.getDbConnection().prepareStatement(query);
			pst.setInt(1, provider.getId());
			pst.setString(2,part.toString());
			pst.setInt(3,profitPorcentage);
			pst.setInt(4,cost);
			pst.executeUpdate();
			pst.close();
		}catch(Exception ex){
			//we need write our own custom msg
			System.out.println(ex.toString());
			console.errorMsg("Not able to insert Asociate");
		}
	}
	public void getID(){

		try{
			//look for the brand in the database
			String query="SELECT IDENT_CURRENT('PartesPorProveedor') AS LAST_id";
			PreparedStatement pst=db.getDbConnection().prepareStatement(query);
			console.printConsole("Getting PartPerProvider id");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				id=rs.getInt("LAST_id");
			}
			
		}catch (Exception ex){
			System.out.println(ex.toString());
			console.errorMsg("Not able to get the PartPerProvider id");
		}		
	}
	public void update(int pProfit,int pCost){
		try{
			System.out.println(id);
			cost=pCost;
			profitPorcentage=pProfit;
			String query = "UPDATE [PartesPorProveedor] "
					+ "SET Precio=?, PorcentajeDeGanancia=? "
					+ "WHERE ID=?";
			PreparedStatement pst = db.getDbConnection().prepareStatement(query);
			pst.setInt(1, cost);
			pst.setInt(2,profitPorcentage);
			pst.setInt(3,id);
			pst.executeUpdate();
			pst.close();
			console.printConsole("Values updated");
		}catch(Exception ex){
			//we need write our own custom msg
			System.out.println(ex.toString());
			console.errorMsg("Not able to Update The cost");
		}
	}
	public String toString(){
		return partName+", "+providerName;
	}
	
}
