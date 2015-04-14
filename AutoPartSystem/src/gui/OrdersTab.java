package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import logic.Console;
import dataBase.Client;
import dataBase.DataBase;
import dataBase.Order;
import dataBase.PartPerProvider;
import dataBase.PartsPerOrder;

public class OrdersTab {
	
	private JTabbedPane tabbedPane;
	private DataBase db;
	private Console console;
	
	//Orders TAB Vars
	private JComboBox<Client> orderClientBox;
	private JButton btnSearch;
	private JTable orderTable;
	private JTextField txtOrderVehicleModel;
	private JTextField txtOrderVehicleYear;
	private DefaultTableModel searchResultsModel;
	private DefaultTableModel orderTableModel;
	private JButton btnAdd;
	private JTextField txtOrderPartName;
	private ArrayList<String> orderParts = new ArrayList<String>();
	private JTable searchResults;
	//--<<
	
	
	public OrdersTab(JTabbedPane tabbedPane, DataBase db, Console console){
		this.tabbedPane = tabbedPane;
		this.db = db;
		this.console = console;
		
		gui();
	}
	
	private void gui(){
		JComponent ordersTab = new JPanel();
		ordersTab.setName("ordersTab");
		//tabs
		tabbedPane.addTab("Orders", ordersTab);
		
		
		JLabel lblPartName = new JLabel("Part Name (Filter):");
		lblPartName.setBounds(10, 198, 121, 14);
		ordersTab.add(lblPartName);
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtOrderPartName = new JTextField();
		txtOrderPartName.setBounds(10, 223, 121, 20);
		ordersTab.add(txtOrderPartName);
		txtOrderPartName.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(303, 42, 89, 23);
		ordersTab.add(btnSearch);
		btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	loadSearchResults(evt);
            }
        });
		
		//Table show provider results
		searchResultsModel = new DefaultTableModel(); 
		//add default columns
		searchResultsModel.addColumn("PName");
		searchResultsModel.addColumn("Provider");
		searchResultsModel.addColumn("ProviderID");
		searchResultsModel.addColumn("Price");
		searchResultsModel.addRow(new Object[]{"PName", "Provider", "ProviderID", "Price"});
		
		
		JLabel lbResults = new JLabel("Results\r\n");
		lbResults.setBounds(10, 44, 99, 53);
		ordersTab.add(lbResults);
		lbResults.setFont(new Font("Consolas", Font.PLAIN, 14));
		searchResults = new JTable();
		searchResults.setBounds(20, 83, 361, 104);
		ordersTab.add(searchResults);
		searchResults.setModel(searchResultsModel);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(250, 196, 89, 23);
		ordersTab.add(btnAdd);
		btnAdd.addActionListener(
			new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	addPartToOrder(evt);
	            }
	        }
				);
		
		//order Table model
		orderTableModel = new DefaultTableModel();
		orderTableModel.addColumn("PartName");
		orderTableModel.addColumn("providerID");
		orderTableModel.addColumn("price");
		orderTableModel.addColumn("#");
		orderTableModel.addRow(new Object[]{"PartName", "providerID", "price", "#"});
		
		orderTable = new JTable();
		orderTable.setBounds(391, 83, 261, 104);
		ordersTab.add(orderTable);
		orderTable.setModel(orderTableModel);
		
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(568, 196, 89, 23);
		ordersTab.add(btnOrder);
		btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnFillOrder(evt);
            }
        }		
				);
		
		orderClientBox = new JComboBox<Client>();
		orderClientBox.setBounds(490, 11, 162, 20);
		ordersTab.add(orderClientBox);
		
		JLabel lblClient = new JLabel("Client:");
		lblClient.setBounds(429, 11, 46, 14);
		ordersTab.add(lblClient);
		
		JLabel lblVehiculo = new JLabel("Vehicle:");
		lblVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVehiculo.setBounds(10, 13, 58, 14);
		ordersTab.add(lblVehiculo);
		
		txtOrderVehicleModel = new JTextField();
		txtOrderVehicleModel.setBounds(69, 43, 86, 20);
		ordersTab.add(txtOrderVehicleModel);
		txtOrderVehicleModel.setColumns(10);
		
		JLabel lblModel_1 = new JLabel("Model:");
		lblModel_1.setBounds(22, 46, 46, 14);
		ordersTab.add(lblModel_1);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(165, 46, 46, 14);
		ordersTab.add(lblYear);
		
		txtOrderVehicleYear = new JTextField();
		txtOrderVehicleYear.setBounds(207, 43, 86, 20);
		ordersTab.add(txtOrderVehicleYear);
		txtOrderVehicleYear.setColumns(10);
		
		JButton btnFilterParts = new JButton("Filter");
		btnFilterParts.setBounds(141, 222, 89, 23);
		ordersTab.add(btnFilterParts);
		btnFilterParts.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	btnFilterByPart(evt);
	            }
	        }
			);
		
		JLabel lblPartsByVehicle = new JLabel("Parts by Vehicle");
		lblPartsByVehicle.setBounds(297, 17, 122, 14);
		ordersTab.add(lblPartsByVehicle);
		
		ordersTab.setLayout(null);
	}
	
	public void load(){
		loadComboClients();
		orderParts.clear();
		clearTable(this.searchResultsModel);
		clearTable(this.orderTableModel);
	}
	
	private void loadComboClients(){
		ArrayList<Client> client = db.getAllClients();
		orderClientBox.removeAllItems();
		for(Client c: client)
			orderClientBox.addItem(c);	
	}
	
	
	
	
	//BUTTON EVENTS
	
	
	//add part to the order table
	private void addPartToOrder(ActionEvent eve){
		int selectedRow = searchResults.getSelectedRow();
		String partName = this.searchResultsModel.getValueAt(selectedRow, 0).toString();
		
		String providerName = "";
		int providerID = 0, price = 0;
		try{
			providerName = this.searchResultsModel.getValueAt(selectedRow, 1).toString();
		    providerID = (int) this.searchResultsModel.getValueAt(selectedRow, 2);
		    price = (int) this.searchResultsModel.getValueAt(selectedRow, 3);
		}catch(Exception e){
			
		}
		
		
		if(providerName != ""){
			if(orderParts.contains(partName+providerID)){
				int index = orderParts.indexOf(partName+providerID) + 1;
				int amount = (int)this.orderTableModel.getValueAt(index, 3);
				this.orderTableModel.setValueAt(amount + 1, index, 3);
			}else{
				orderParts.add(partName+providerID);
				this.orderTableModel.addRow(new Object[]{partName, providerID, price, 1});
			}
			console.printConsole("Parte agregado a la orden");
		}else{
			console.printConsole("Parte no tiene un provedor no se pudo agregar a la orden!");
		}
		
	}
	
	
	private void btnFillOrder(ActionEvent eve){
		//if there is a part in the order
		if(orderTableModel.getRowCount() > 1){
			ArrayList<PartsPerOrder> ppo = new ArrayList<PartsPerOrder>();
			//get everything in the order
			for(int i = 2; i <= orderTableModel.getRowCount(); i++){
				//String name = orderTableModel.getValueAt(i -1, 0).toString();
				int providerId = (int) orderTableModel.getValueAt(i-1, 1);
				int price = (int) orderTableModel.getValueAt(i-1, 2);
				int amount = (int) orderTableModel.getValueAt(i-1, 3);
				PartsPerOrder partOrder = new PartsPerOrder(providerId, price, amount);
				ppo.add(partOrder);
			}
			
			Order order = new Order(db,ppo,
					this.orderClientBox.getSelectedItem().toString());
			order.fillOrder();
		}
	}
	
	//get all providers that have x part
	private void loadSearchResults(ActionEvent evt){
		clearTable(this.searchResultsModel);
		
		String model = this.txtOrderVehicleModel.getText();
		String year = this.txtOrderVehicleYear.getText();
		
		ArrayList<String> nameParts;
		nameParts = db.getAllPartsForAutoMobile(model,year);
	
		for(String part: nameParts){
			//all providers with that part
			ArrayList<PartPerProvider> partProvider;
			partProvider = db.getProvidersSellPart(part);
			
			
			boolean entered = false;
		
			//add all the parts with their provider
			for(PartPerProvider ppp: partProvider){
				searchResultsModel.addRow(new Object[]{part, ppp.getProviderName()
						,ppp.getProviderId(), ppp.getPrice()});
				entered = true;
				System.out.println(ppp.getProviderName());
			}
			if(!entered){
				searchResultsModel.addRow(new Object[]{part});
				entered = true;
			}
		}
	}
	
	private void btnFilterByPart(ActionEvent evt){
		clearTable(this.searchResultsModel);
		String partName = this.txtOrderPartName.getText();
		
		ArrayList<PartPerProvider> partProvider = db.getProvidersSellPart(partName);
		//add all the parts with their provider
		for(PartPerProvider ppp: partProvider){
			//System.out.println(partName);
			searchResultsModel.addRow(new Object[]{partName, ppp.getProviderName()
					,ppp.getProviderId(), ppp.getPrice()});
		}
	}
	
	
	//--- GUI HELPERS
	
	private void clearTable(DefaultTableModel model){
		int numRows = model.getRowCount();
		for(int i = numRows; i >= 2; i--)
			model.removeRow(i-1);
	}
	
}
