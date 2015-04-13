package gui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.*;

import dataBase.Automobile;
import dataBase.Client;
import dataBase.Company;
import dataBase.DataBase;
import dataBase.Manufacturer;
import dataBase.Order;
import dataBase.PartPerProvider;
import dataBase.PartsPerOrder;
import dataBase.Person;
import dataBase.Provider;

import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import logic.Console;
import logic.Parser;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.EtchedBorder;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.DefaultCaret;
import javax.swing.JFormattedTextField;



public class MainWindow extends JFrame{
	
	//gui stuff
	private JTextArea consoleTextArea;

	//logic stuff
	private Console console;
	private Parser parser;
	
	//db stuff
	private DataBase db;
	
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	
	//Client Tab Vars
	JComboBox clientTypeComboBox;
	private JButton btnUpdate;
	private JButton btnSelect;
	private JTextField txtFieldID;
	private JTextField txtClientFullName;
	private JTextArea txtClientAddress;
	private JComboBox clientStateComboBox;
	private JTextArea txtTels;
	private JTextField txtClientCity;
	private JTextField txtContactDoes;
	private JList<String> jListClients;
	private ArrayList<Object> modelClientList = new ArrayList<Object>();
	//--<<
	
	
	//Orders TAB Vars
	private JComboBox<Client> orderClientBox;
	private JButton btnSearch;
	private JTable orderTable;
	private JTextField txtOrderVehicleModel;
	private JTextField txtOrderVehicleYear;
	private DefaultTableModel searchResultsModel;
	private DefaultTableModel orderTableModel;
	private JButton btnAdd;
	private ArrayList<String> orderParts = new ArrayList<String>();
	//--<<
	
	
	private JTextField txtOrderPartName;
	private JTable searchResults;
	private JTextField txtContactName;
	
	private JTextField txtManufacturerName;
	
	
	
	private JTextField txtModel;
	private JTextField txtDetail;
	private JTextField txtFabricationYear;
	private JComboBox<Manufacturer> comboManufacturersOfAutomobile;
	private JComboBox<Provider> providers;
	
	
	


	private PartsTab partsTab;
	private ProvidersTab providersTab;
	public MainWindow(){
		createGui();
		
		console =  new Console(this.consoleTextArea);
		parser = new Parser(console);
		db=new DataBase(console); //pass console so db can show msg's
		db.connect();
		
		partsTab=new PartsTab(tabbedPane, console, db);
		providersTab= new ProvidersTab(tabbedPane, console, db);
		//first tab to show needs to preload
		this.loadOrdersStuff();
	}
	
	
	@SuppressWarnings("unchecked")
	private void createGui(){
		setTitle("Autopart System");
	    setSize(700,500);

		
		/*
		 * Tabs for multiple views
		 */
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//panels
		JComponent clientTab = new JPanel();
		clientTab.setName("clientTab");
		
		JComponent automobileTab=new JPanel();
		automobileTab.setName("automobilesTab");
		JComponent manufacturersTab=new JPanel();
		manufacturersTab.setName("manufacturersTab");
		JComponent ordersTab = new JPanel();
		ordersTab.setName("ordersTab");
		
		
		//tabs
		tabbedPane.addTab("Orders", ordersTab);
		ordersTab.setLayout(null);
		
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
		clientTab.setLayout(null);
		
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
		tabbedPane.addTab("Client", clientTab);
		tabbedPane.addTab("Automobiles",automobileTab);
		tabbedPane.addTab("Manufacturers",manufacturersTab);
				
	     /*
	        * Tab Listener
	        * We load basic DB Content when every time a tab is switched
	     */
	    tabbedPane.addChangeListener(new ChangeListener()
	    {
	      public void stateChanged(ChangeEvent e)
	      {
	        loadTabsStuff();
	      }
	    });
		
				
		/*
		 * Begin Client GUI stuff
		 */
		
		ListModel clientListModel = new AbstractListModel() {
			public int getSize() {
				return modelClientList.size();
			}
			public Object getElementAt(int index) {
				return modelClientList.get(index);
			}
		};
		
		jListClients = new JList(clientListModel);
		jListClients.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jListClients.setBounds(0, 0, 104, 231);
		clientTab.add(jListClients);
		
		//type of client
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(114, 3, 46, 14);
		clientTab.add(lblType);
		
		clientTypeComboBox = new JComboBox();
		clientTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Personal", "Business"}));
		clientTypeComboBox.setBounds(163, 0, 89, 20);
		clientTab.add(clientTypeComboBox);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(114, 46, 46, 14);
		clientTab.add(lblId);
		
		txtFieldID = new JTextField();
		txtFieldID.setBounds(173, 43, 152, 20);
		clientTab.add(txtFieldID);
		txtFieldID.setColumns(10);
		
		JLabel lblFullName = new JLabel("Full \r\nName");
		lblFullName.setBounds(114, 83, 60, 14);
		clientTab.add(lblFullName);
		
		txtClientFullName = new JTextField();
		txtClientFullName.setBounds(173, 80, 152, 20);
		clientTab.add(txtClientFullName);
		txtClientFullName.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(114, 114, 46, 14);
		clientTab.add(lblCity);
		
		txtClientCity = new JTextField();
		txtClientCity.setBounds(173, 111, 152, 20);
		clientTab.add(txtClientCity);
		txtClientCity.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(114, 139, 75, 14);
		clientTab.add(lblAddress);
		
		txtClientAddress = new JTextArea();
		txtClientAddress.setBounds(114, 164, 201, 48);
		clientTab.add(txtClientAddress);
		
		JLabel lbTels = new JLabel("Tel #'s:");
		lbTels.setBounds(356, 3, 89, 14);
		clientTab.add(lbTels);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(114, 239, 46, 14);
		clientTab.add(lblState);
		
		clientStateComboBox = new JComboBox();
		clientStateComboBox.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "INACTIVE", "SUSPENDED"}));
		clientStateComboBox.setBounds(164, 236, 88, 20);
		clientTab.add(clientStateComboBox);
		
		txtTels = new JTextArea();
		txtTels.setBounds(417, 3, 123, 59);
		clientTab.add(txtTels);
		
		JLabel lblNewLabel = new JLabel("If Business:");
		lblNewLabel.setBounds(356, 80, 76, 14);
		clientTab.add(lblNewLabel);
		
		JLabel lblManagerName = new JLabel("Manager Name:") ;
		lblManagerName.setBounds(356, 114, 89, 14);
		clientTab.add(lblManagerName);
		
		txtContactName = new JTextField();
		txtContactName.setBounds(455, 111, 152, 20);
		clientTab.add(txtContactName);
		txtContactName.setColumns(10);
		
		JLabel lblManagerId = new JLabel("Cargo:");
		lblManagerId.setBounds(399, 139, 46, 14);
		clientTab.add(lblManagerId);
		
		txtContactDoes = new JTextField();
		txtContactDoes.setBounds(455, 136, 153, 20);
		clientTab.add(txtContactDoes);
		txtContactDoes.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(580, 235, 89, 23);
		clientTab.add(btnUpdate);
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	updateClientBtn(evt);
            }
        });
		
		btnSelect = new JButton("Select");
		btnSelect.setBounds(10, 235, 89, 23);
		clientTab.add(btnSelect);
		btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	selectClientBtn(evt);
            }
        });
		
		
		//---<<<< END OF CLIENT TAB
		
		/*
		 * Begin Part GUI stuff
		 */
		
				
		
		/*
		 *Begin automobile tab stuff 
		 * 
		 */
		automobileTab.setLayout(null);
		
		JLabel lblCreateAutomobile= new JLabel("CREATE AUTOMOBILE");
		lblCreateAutomobile.setBounds(200, 20, 220, 15);
		automobileTab.add(lblCreateAutomobile);
		
		JLabel lblModel=new JLabel("Model:");
		lblModel.setBounds(200,50,100,10);
		automobileTab.add(lblModel);
		
		txtModel=new JTextField();
		txtModel.setBounds(350,50,200,19);
		txtModel.setColumns(10);
		automobileTab.add(txtModel);
		
		JLabel lblDetail = new JLabel("Detail:");
		lblDetail.setBounds(200,70,100,10);
		automobileTab.add(lblDetail);
		
		txtDetail= new JTextField();
		txtDetail.setBounds(350,70,200,19);
		txtDetail.setColumns(10);
		automobileTab.add(txtDetail);
		
		JLabel lblFabricationYear=new JLabel("Fabrication Year:");
		lblFabricationYear.setBounds(200,90,200,10);
		automobileTab.add(lblFabricationYear);
		
		txtFabricationYear=new JTextField();
		txtFabricationYear.setBounds(350,90,200,19);
		txtFabricationYear.setColumns(10);
		automobileTab.add(txtFabricationYear);
		
		JLabel lblAutomobileManufacturer=new JLabel("Manufacturer:");
		lblAutomobileManufacturer.setBounds(200,110,150,10);
		automobileTab.add(lblAutomobileManufacturer);
		
		comboManufacturersOfAutomobile=new JComboBox<Manufacturer>();
		comboManufacturersOfAutomobile.setBounds(350,110,200,19);
		automobileTab.add(comboManufacturersOfAutomobile);
		
		JButton btnAddAutomobile=new JButton("Add");
		btnAddAutomobile.setBounds(200,130,100,19);
		automobileTab.add(btnAddAutomobile);
		
		btnAddAutomobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAutomobileActionPerformed(evt);
            }
        });

		/*
		 *Begin Manufacturers tab stuff 
		 * 
		 */
		manufacturersTab.setLayout(null);
		JLabel lblAddManufacturer= new JLabel("ADD MANUFACTURER");
		lblAddManufacturer.setBounds(200, 5, 150, 10);
		manufacturersTab.add(lblAddManufacturer);
		
		JLabel lblManufacturerName= new JLabel("Manufac Name:");
		lblManufacturerName.setBounds(200, 32, 100, 10);
		manufacturersTab.add(lblManufacturerName);
		
		txtManufacturerName=new JTextField();
		txtManufacturerName.setBounds(350,27, 100, 19);
		manufacturersTab.add(txtManufacturerName);
		txtManufacturerName.setColumns(10);
		
		JButton btnAddManufacturer=new JButton("Add");
		btnAddManufacturer.setBounds(200, 59, 60, 19);
		manufacturersTab.add(btnAddManufacturer);
		btnAddManufacturer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddManufacturerActionPerformed(evt);
            }
        });
		
		//add tabs to this frame
		getContentPane().add(tabbedPane);
		
		
		//consoleAreaGuiProperties
		consoleTextArea = new JTextArea();
		consoleTextArea.setEditable(false);
		consoleTextArea.setRows(8);
		Font f = new Font("Consolas",Font.PLAIN, 16);
		consoleTextArea.setFont(f);
		DefaultCaret caret = (DefaultCaret)consoleTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		//console area's scroll bar
		JScrollPane consoleScroll = new JScrollPane(consoleTextArea);
		consoleScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(consoleScroll, BorderLayout.SOUTH);
	
		
		
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        JLabel lblActualProvider=new JLabel("Actual provider");
        lblActualProvider.setBounds(400,1,100,10); 
	}
	
	/*
	 * Methods to get data from the DB for the different views
	 */
	
	//which TAB?
	private void loadTabsStuff(){
		Component tab = tabbedPane.getSelectedComponent();
		String tName = tab.getName();

		switch (tName){
			case "clientTab":
				loadClientStuff();
				break;
			case "partsTab":
				partsTab.load();
				break;
			case "ordersTab":
				loadOrdersStuff();
				break;
			case "providersTab":
				providersTab.load();
				break;
			case "automobilesTab":
				loadAutomobileStuff();
				break;
		}
	}	
	
	//reload TAB's GUI contents from DB
	
	private void loadClientStuff(){
		//fill JClientList
		ArrayList<Client> clientList = db.getAllClients();
		modelClientList.clear(); //clear list first
		
		for(Client client : clientList)
				modelClientList.add(client);
		
		modelClientList.add((String) "New Client");
	}
	
	private void loadOrdersStuff(){
		loadComboClients();
		orderParts.clear();
		clearTable(this.searchResultsModel);
		clearTable(this.orderTableModel);
	}
	
	private void clearTable(DefaultTableModel model){
		int numRows = model.getRowCount();
		for(int i = numRows; i >= 2; i--)
			model.removeRow(i-1);
	}
	
	

	private void loadAutomobileStuff(){
		loadComboManufacturers();
	}
	
	private void loadComboClients(){
		ArrayList<Client> client = db.getAllClients();
		orderClientBox.removeAllItems();
		for(Client c: client)
			orderClientBox.addItem(c);	
	}
	
	
	
	
	
	
	
	
	
	private void loadComboManufacturers(){
		ArrayList<Manufacturer> manuList = db.getAllManufacturers();
		comboManufacturersOfAutomobile.removeAllItems();
		for(Manufacturer manufacturer : manuList)
		{
			comboManufacturersOfAutomobile.addItem(manufacturer); //this combo is on the Automobile tab 

		}
	}
	
	/*
	 * Client gui event/action section
	 */
	
	//add part to the order table
	private void addPartToOrder(ActionEvent eve){
		int selectedRow = searchResults.getSelectedRow();
		String partName = this.searchResultsModel.getValueAt(selectedRow, 0).toString();
		String providerName = this.searchResultsModel.getValueAt(selectedRow, 1).toString();
		int providerID = (int) this.searchResultsModel.getValueAt(selectedRow, 2);
		int price = (int) this.searchResultsModel.getValueAt(selectedRow, 3);
		if(providerName != ""){
			if(orderParts.contains(partName+providerID)){
				int index = orderParts.indexOf(partName+providerID) + 1;
				int amount = (int) this.orderTableModel.getValueAt(index, 3);
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
			
			//add all the parts with their provider
			for(PartPerProvider ppp: partProvider){
				searchResultsModel.addRow(new Object[]{part, ppp.getProviderName()
						,ppp.getProviderId(), ppp.getPrice()});
			}
		}
	}
	
	private void btnFilterByPart(ActionEvent evt){
		clearTable(this.searchResultsModel);
		String partName = this.txtOrderPartName.getText();
		
		ArrayList<PartPerProvider> partProvider = db.getProvidersSellPart(partName);
		//add all the parts with their provider
		for(PartPerProvider ppp: partProvider){
			searchResultsModel.addRow(new Object[]{partName, ppp.getProviderName()
					,ppp.getProviderId(), ppp.getPrice()});
		}
	}
	
	private void selectClientBtn(java.awt.event.ActionEvent evt){
		int selected = jListClients.getSelectedIndex();
	
		if(modelClientList.size() == selected + 1){
			System.out.println("New Client selected");
			this.txtClientFullName.setEditable(true);
			this.clientTypeComboBox.setEnabled(true);
		}else{
			
			//get selected client name
			String clientName = this.modelClientList.get(selected).toString();
			
			Client client;
			//if it is a person type client
			if(this.modelClientList.get(selected).getClass().toString().contains("Person")){
				Person per = new Person(db, clientName);
				per.fillClient();
				client = per;
			}else{
				Company comp = new Company(db, clientName);
				comp.fillClient();
				client = comp;
				try{
					this.clientTypeComboBox.getModel().setSelectedItem("Business");;
					this.txtContactName.setText(comp.getContactName());
					this.txtContactDoes.setText(comp.getContactDoes());
				}catch(Exception e){
					console.printConsole("one or more of the business fields is null!");
				}
			}
			
		    try{
				this.txtFieldID.setText(client.getId());
				this.txtClientFullName.setText(client.getFullName());
				this.txtClientCity.setText(client.getCity());
				this.txtClientAddress.setText(client.getAddress());
				this.txtTels.setText(client.getTels());
		    }catch(Exception e){
		    	console.printConsole("One or more of the client fields is null!");
		    }
		    this.txtClientFullName.setEditable(false); //we can't edit this
			//maybe we should be able to edit this?
			this.clientTypeComboBox.setEnabled(false);
		    //set the state of the client (ACTIVE, etc)
		    this.clientStateComboBox.getModel().setSelectedItem(client.getState());
		}
	}
	private void updateClientBtn(java.awt.event.ActionEvent evt){

		int id = -1;
		ArrayList<Integer> telephones;
		try{
			id = Integer.parseInt(txtFieldID.getText().toString());
		}catch(Exception e){
			console.printConsole("Could not parse Identification to int");
			return;
		}
		
		//Parse TEL's
		telephones= parser.telParser(this.txtTels.getText());
		
		Client per;
		//personal Client
		if(this.clientTypeComboBox.getSelectedItem().toString().compareTo("Personal") == 0){
			//get info
			
			per = new Person(db, this.txtClientFullName.getText(),
					this.txtClientAddress.getText(), 
					this.clientStateComboBox.getSelectedItem().toString(),
					id,this.txtClientCity.getText(), telephones);
			
			//save it to the server
			console.printConsole("Saving Personal Client to DB");
			
		}else{ //business Client
			
			per = new Company(db, this.txtClientFullName.getText(),
					this.txtClientAddress.getText(), 
					this.clientStateComboBox.getSelectedItem().toString(),
				this.txtClientCity.getText(), id, this.txtContactDoes.getText(),
				telephones.indexOf(0), this.txtContactName.getText());
			
			//save it to the server
			console.printConsole("Saving a COMPANY type Client to DB");
			
		}
		

		if(this.txtClientFullName.isEditable()){
			per.addClient();
			this.txtClientFullName.setEditable(false);
		}else{
			per.updateClient();
		}
	}
	private void btnAddAutomobileActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Automobile automobile= new Automobile(txtModel.getText(),txtDetail.getText(),
				Integer.valueOf(txtFabricationYear.getText()),
				(Manufacturer)comboManufacturersOfAutomobile.getSelectedItem(),
				db,console);
	}
	
	private void btnAddManufacturerActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Manufacturer manufact=new Manufacturer(txtManufacturerName.getText(),db,console);
	} 
	
	
	public static void main(String args[]){
		//open main window
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
