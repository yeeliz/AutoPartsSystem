package gui;

import java.awt.event.MouseAdapter;

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

import dataBase.Brand;
import dataBase.Client;
import dataBase.Company;
import dataBase.DataBase;
import dataBase.Manufacturer;
import dataBase.Part;
import dataBase.Person;
import dataBase.Provider;

import javax.swing.JTabbedPane;

import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import logic.Console;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.EtchedBorder;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;


public class MainWindow extends JFrame{
	
	//gui stuff
	private JTextArea consoleTextArea;

	//logic stuff
	private Console console;
	
	//db stuff
	private DataBase db ;
	
	
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
	ArrayList<Object> modelClientList = new ArrayList<Object>();
	//--<<
	
	private JTextField TfPartName;
	private JTable tableResults;
	private DefaultTableModel model;
	private JTextField txtContactName;
	private JTextField txtPartName;
	private JTextField txtBrandName;
	private JTextField txtManufacturerName;
	private JTextField txtProviderName;
	private JTextField txtContactProviderName;
	private JTextField txtDirectionProvider;
	private JTextField txtCityProvider;
	private JTextField txtCost;
	private JTextField txtProfit;
	private JFormattedTextField txtProviderPhone;
	private JFormattedTextField txtProviderPhone2;
	private JComboBox<Provider> providers;
	private JComboBox<Provider> comboProvidersInPartsTab;
	private JComboBox<Part> comboPartsInPartsTab;

	
	
	private JComboBox<Manufacturer> Manufacturers;
	private JComboBox<Manufacturer> Manufacturers2;
	private JComboBox<Brand> brands;
	
	public MainWindow(){
		createGui();
		
		console =  new Console(this.consoleTextArea);
		db=new DataBase(console); //pass console so db can show msg's
		db.connect();
		
		//this.loadClientStuff();
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
		JComponent partsTab = new JPanel();
		partsTab.setName("partsTab");
		JComponent ordersTab = new JPanel();
		ordersTab.setName("ordersTab");
		JComponent providersTab=new JPanel();
		providersTab.setName("providersTab");
		JComponent automobileTab=new JPanel();
		automobileTab.setName("Automobiles");
		JComponent manufacturersTab=new JPanel();
		manufacturersTab.setName("Manufacturers");
		
		
		//tabs
		tabbedPane.addTab("Orders", ordersTab);
		tabbedPane.addTab("Client", clientTab);
		tabbedPane.addTab("Parts", partsTab);
		tabbedPane.addTab("Providers",providersTab);
		tabbedPane.addTab("Automobiles",automobileTab);
		tabbedPane.addTab("Manufacturers",manufacturersTab);
		ordersTab.setLayout(null);
				
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
		
		//--<<<<<<
		
		JTabbedPane nestedTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		nestedTabbedPane.setBounds(0, 0, 679, 269);
		ordersTab.add(nestedTabbedPane);
		
		JTabbedPane proviOppsTabbedPane=new JTabbedPane(JTabbedPane.LEFT);
		proviOppsTabbedPane.setBounds(0, 0, 679, 269);
		providersTab.add(proviOppsTabbedPane);
		
		JPanel newProviderTab = new JPanel();
		proviOppsTabbedPane.addTab("New", null, newProviderTab , null);
		newProviderTab.setLayout(null);
		
		partsTab.addMouseListener(new MouseAdapter() {// empty implementation of all
            // MouseListener`s methods
		public void mousePressed(MouseEvent e) {
			System.out.println(e.getX() + "," + e.getY());
		}
		});
		
		JLabel lblNewProvider=new JLabel("New Provider");
		lblNewProvider.setBounds(100,1,100,15);
		lblNewProvider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newProviderTab.add(lblNewProvider);
		
		JLabel lblProviderName=new JLabel("Name:");
		lblProviderName.setBounds(49,23,100,10);
		newProviderTab.add(lblProviderName);
		
		txtProviderName=new JTextField();
		txtProviderName.setBounds(142, 23, 100, 19);
		txtProviderName.setColumns(10);
		newProviderTab.add(txtProviderName);
		
		JLabel lblContactProviderName=new JLabel("Contact Name:");
		lblContactProviderName.setBounds(49, 43, 100, 10);
		newProviderTab.add(lblContactProviderName);
		
		txtContactProviderName= new JTextField();
		txtContactProviderName.setBounds(142,43,100,19);
		txtContactProviderName.setColumns(10);
		newProviderTab.add(txtContactProviderName);
		
		JLabel lblDireccionProvider=new JLabel("Direccion:");
		lblDireccionProvider.setBounds(49,63,100,10);
		newProviderTab.add(lblDireccionProvider);
		
		txtDirectionProvider=new JTextField();
		txtDirectionProvider.setBounds(142,63,100,19);
		txtDirectionProvider.setColumns(10);
		newProviderTab.add(txtDirectionProvider);
		
		JLabel lblCiudadProvider= new JLabel("Ciudad:");
		lblCiudadProvider.setBounds(49,83,100,10);
		newProviderTab.add(lblCiudadProvider);
		
		txtCityProvider= new JTextField();
		txtCityProvider.setBounds(142,83,100,19);
		txtCityProvider.setColumns(10);
		newProviderTab.add(txtCityProvider);
		
		JLabel lblNumber= new JLabel("Number:");
		lblNumber.setBounds(49,103,100,10);
		newProviderTab.add(lblNumber);
		MaskFormatter mf = null;
		
		try{
			mf= new MaskFormatter("(###) ####-####");
			mf.setPlaceholderCharacter('_');
			txtProviderPhone= new JFormattedTextField(mf);
			txtProviderPhone.setBounds(142,103,100,19);
			newProviderTab.add(txtProviderPhone);
		}catch(Exception ex){
			System.out.println(ex.getStackTrace().toString());
		}
		
		JButton btnAddProvider= new JButton("Add provider");
		btnAddProvider.setBounds(49,133,120,19);
		newProviderTab.add(btnAddProvider);
		btnAddProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddProviderActionPerformed(evt);
            }
        });
		
		
		JLabel lblNewPhoneToProvider=new JLabel("New Phone to provider");
		lblNewPhoneToProvider.setBounds(100,174,150,15);
		lblNewPhoneToProvider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newProviderTab.add(lblNewPhoneToProvider);
		
		JLabel lblProvider=new JLabel("Provider:");
		lblProvider.setBounds(49, 194, 100, 10);
		newProviderTab.add(lblProvider);
		
		providers= new JComboBox<Provider>();
		providers.setBounds(142, 194, 200, 19);
		newProviderTab.add(providers);
		
		JLabel lblNumber2= new JLabel("Number:");
		lblNumber2.setBounds(49,214,100,10);
		newProviderTab.add(lblNumber2);
		
		txtProviderPhone2= new JFormattedTextField(mf);
		txtProviderPhone2.setBounds(142,214,100,19);
		newProviderTab.add(txtProviderPhone2);
		
		
		JButton btnAddPhoneToProvider= new JButton("Add");
		btnAddPhoneToProvider.setBounds(49,234,60,19);
		newProviderTab.add(btnAddPhoneToProvider);
		btnAddPhoneToProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddPhoneToProviderActionPerformed(evt);
            }
        });
		

		
		
		
				
		JPanel searchProviderTab = new JPanel();
		proviOppsTabbedPane.addTab("Seach", null, searchProviderTab, null);
		searchProviderTab.setLayout(null);
		
		JLabel lbSearch = new JLabel("Search Provider");
		lbSearch.setFont(new Font("Consolas", Font.PLAIN, 16));
		lbSearch.setBounds(199, 11, 141, 27);
		searchProviderTab.add(lbSearch);
		
		JLabel lblPartName = new JLabel("Part Name:");
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPartName.setBounds(109, 54, 80, 14);
		searchProviderTab.add(lblPartName);
		
		TfPartName = new JTextField();
		TfPartName.setBounds(199, 53, 155, 20);
		searchProviderTab.add(TfPartName);
		TfPartName.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(377, 52, 89, 23);
		searchProviderTab.add(btnSearch);
		
		JLabel lbResults = new JLabel("Results\r\n");
		lbResults.setFont(new Font("Consolas", Font.PLAIN, 14));
		lbResults.setBounds(238, 77, 99, 53);
		searchProviderTab.add(lbResults);
		
		//Table show provider results
		model = new DefaultTableModel(); 
		tableResults = new JTable();
		tableResults.setModel(model);
		//add default columns
		model.addColumn("Name");
		model.addColumn("Identifier");
		model.addRow(new Object[]{"Name", "Provider"});
		
		tableResults.setBounds(109, 127, 348, 100);
		
		searchProviderTab.add(tableResults);
		
		JTabbedPane newOrderTab = new JTabbedPane(JTabbedPane.TOP);
		nestedTabbedPane.addTab("New Order", null, newOrderTab, null);
		clientTab.setLayout(null);	
		
		
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
		
		btnSelect = new JButton("Select");
		btnSelect.setBounds(10, 235, 89, 23);
		clientTab.add(btnSelect);
		
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
		clientStateComboBox.setBounds(164, 236, 75, 20);
		clientTab.add(clientStateComboBox);
		
		txtTels = new JTextArea();
		txtTels.setBounds(417, 3, 123, 59);
		clientTab.add(txtTels);
		
		JLabel lblNewLabel = new JLabel("If Business:");
		lblNewLabel.setBounds(356, 80, 76, 14);
		clientTab.add(lblNewLabel);
		
		JLabel lblManagerName = new JLabel("Manager Name:");
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
		
		partsTab.setLayout(null);
		//---<<<< END OF CLIENT TAB
		
	
		
		JLabel lblAddPart= new JLabel("ADD PART");
		lblAddPart.setBounds(49, 5, 100, 10);
		partsTab.add(lblAddPart);
		
		JLabel lblPartName1= new JLabel("Part Name:");
		lblPartName1.setBounds(49, 32, 100, 10);
		partsTab.add(lblPartName1);
		
		txtPartName=new JTextField();
		txtPartName.setBounds(138,27, 100, 19);
		partsTab.add(txtPartName);
		txtPartName.setColumns(10);
		
		JLabel lblBrands= new JLabel("Brands:");
		lblBrands.setBounds(49, 59, 100, 10);
		partsTab.add(lblBrands);
		
		brands=new JComboBox<Brand>();
		brands.setBounds(138, 59, 100, 19);
		partsTab.add(brands);
		
		JLabel lblManufacturers= new JLabel("Manufacturers:");
		lblManufacturers.setBounds(49, 95, 100, 10);
		partsTab.add(lblManufacturers);
		
		Manufacturers=new JComboBox<Manufacturer>();
		Manufacturers.setBounds(138, 95, 100, 19);
		partsTab.add(Manufacturers);
		
		JButton btnAddPart=new JButton("Add");
		btnAddPart.setBounds(49, 123, 60, 19);
		partsTab.add(btnAddPart);
		btnAddPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddPartActionPerformed(evt);
            }
        });
		
		
		JLabel lblAddBrand= new JLabel("ADD BRAND");
		lblAddBrand.setBounds(283, 5, 100, 10);
		partsTab.add(lblAddBrand);
				
		JLabel lblBrandName= new JLabel("Brand Name:");
		lblBrandName.setBounds(283, 32, 100, 10);
		partsTab.add(lblBrandName);
		
		txtBrandName=new JTextField();
		txtBrandName.setBounds(367,27, 100, 19);
		partsTab.add(txtBrandName);
		txtBrandName.setColumns(10);
		
		JButton btnAddBrand=new JButton("Add");
		btnAddBrand.setBounds(283, 59, 60, 19);
		partsTab.add(btnAddBrand);
		btnAddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddBrandActionPerformed(evt);
            }
        });
		
		
		
		
		JLabel lblAddProviderForAPart=new JLabel("Add Provider for a part");
		lblAddProviderForAPart.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblAddProviderForAPart.setBounds(340, 115, 220, 15);
		partsTab.add(lblAddProviderForAPart);
		
		JLabel  lblParts= new JLabel("Part:");
		lblParts.setBounds(340,135,100,15);
		partsTab.add(lblParts);
		
		comboPartsInPartsTab =new JComboBox<Part>();
		comboPartsInPartsTab.setBounds(420,135,220,19);
		partsTab.add(comboPartsInPartsTab);

		JLabel  lblProviders= new JLabel("Provider:");
		lblProviders.setBounds(340,155,100,15);
		partsTab.add(lblProviders);
		
		comboProvidersInPartsTab =new JComboBox<Provider>();
		comboProvidersInPartsTab.setBounds(420,155,220,19);
		partsTab.add(comboProvidersInPartsTab);
		
		JLabel lblCost=new JLabel("Cost:");
		lblCost.setBounds(340,175,100,15);
		partsTab.add(lblCost);
		
		txtCost=new JTextField();
		txtCost.setBounds(420,175,220,15);
		txtCost.setColumns(10);
		partsTab.add(txtCost);
		
		
		JLabel lblProfit= new JLabel("Profit %: ");
		lblProfit.setBounds(340,195,100,15);
		partsTab.add(lblProfit);
		
		txtProfit=new JTextField();
		txtProfit.setBounds(420,195,220,15);
		txtProfit.setColumns(10);
		partsTab.add(txtProfit);
		
		JButton btnAssociate=new JButton("Associate");
		btnAssociate.setBounds(340,220,100,19);
		partsTab.add(btnAssociate);
		
		btnAssociate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssociateActionPerformed(evt);
            }
        });
		
		
		/*
		 *Begin automobile tab stuff 
		 * 
		 */
		automobileTab.setLayout(null);
		
		JLabel lblCreateAutomobile= new JLabel("CREATE AUTOMOBILE");
		lblCreateAutomobile.setBounds(200, 20, 220, 15);
		automobileTab.add(lblCreateAutomobile);
		
		
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
		
		//console area's scroll bar
		JScrollPane consoleScroll = new JScrollPane(consoleTextArea);
		consoleScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(consoleScroll, BorderLayout.SOUTH);
	
		
		
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        providersTab.setLayout(null);
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
			case "partsTab":
				loadPartsStuff();
			case "ordersTab":
				//loadOrdersStuff();
			case "providersTab":
				loadProvidersStuff();
		}
	}	
	
	private void loadClientStuff(){
		//fill JClientList
		ArrayList<Client> clientList = db.getAllClients();
		modelClientList.clear(); //clear list first
		
		for(Client client : clientList)
				modelClientList.add(client);
		
		modelClientList.add((String) "New Client");
	
	}
	
	//reload comboBox's from DB
	private void loadPartsStuff() {
		loadComboBrand();
		loadComboManufacturers();
		loadComboParts();
		loadComboProvider();
	}
	private void loadProvidersStuff(){
		ArrayList<Provider> providerList = db.getAllProviders();
		providers.removeAllItems();
		
		for(Provider provider: providerList)
			providers.addItem(provider);
	}
	private void loadComboParts(){
		ArrayList<Part> partsList = db.getAllParts();
		comboPartsInPartsTab.removeAllItems();
		for(Part part: partsList)
			comboPartsInPartsTab.addItem(part);
	}
	private void loadComboProvider(){
		ArrayList<Provider> providerList = db.getAllProviders();
		providers.removeAllItems();
		for(Provider provider: providerList)
			comboProvidersInPartsTab.addItem(provider);		
	}
	
	
	private void loadComboBrand(){
		ArrayList<Brand> brandList = db.getAllBrands();
		brands.removeAllItems();
		
		for(Brand brand : brandList)
			brands.addItem(brand);
	}
	private void loadComboManufacturers(){
		ArrayList<Manufacturer> manuList = db.getAllManufacturers();
		Manufacturers.removeAllItems();
		
		for(Manufacturer manufacturer : manuList)
		{
			Manufacturers.addItem(manufacturer); //this combo is on the tab parts
			Manufacturers2.addItem(manufacturer); //this combo is on the Automobile tab 
			
		}
	}
	
	//--<<<<<<
	
	/*
	 * Client gui event/action section
	 */
	private void updateClientBtn(java.awt.event.ActionEvent evt){

		int id = -1;
		try{
			id = Integer.parseInt(txtFieldID.getText().toString());
		}catch(Exception e){
			console.printConsole("Could not parse Identification to int");
		}
		
		//Parse TEL's
		ArrayList<Integer> telephones = new <Integer>ArrayList();
		String tels = this.txtTels.getText();
		
		try{
			if(!tels.contains("\n")){
				telephones.add(Integer.parseInt(tels));
			}else{
				while(tels.contains("\n")){
					telephones.add(Integer.parseInt(tels.substring(0,tels.indexOf("\n"))));
					tels = tels.substring(tels.indexOf("\n") + 1, tels.length());
				}
				telephones.add(Integer.parseInt(tels));
				
			}
		}catch(Exception e){
			console.errorMsg("Could not parse tels to int");
		}
		
		//personal Client
		if(this.clientTypeComboBox.getSelectedItem().toString().compareTo("Personal") == 0){
			//get info
			
			
			Person per = new Person(db, this.txtClientFullName.getText(),
					this.txtClientAddress.getText(), 
					this.clientStateComboBox.getSelectedItem().toString(),
					id,this.txtClientCity.getText(), telephones);
			
			//save it to the server
			console.printConsole("Saving Personal Client to DB");
			per.addClient();
		}else{ //business Client
			
			Company per = new Company(db, this.txtClientFullName.getText(),
					this.txtClientAddress.getText(), 
					this.clientStateComboBox.getSelectedItem().toString(),
				this.txtClientCity.getText(), id, this.txtContactDoes.getText(),
				tels.indexOf(0), this.txtContactName.getText());
			
			//save it to the server
			console.printConsole("Saving a COMPANY type Client to DB");
			per.addClient();
		}
		
		//add it to client list
		this.loadClientStuff();
	}

	
	private void btnAddPartActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Part part=new Part(txtPartName.getText(),(Brand) brands.getSelectedItem(), (Manufacturer)Manufacturers.getSelectedItem(), db, console);
    }  
	private void btnAddBrandActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Brand brand=new Brand(txtBrandName.getText(),db,console);
        brands.addItem(brand);
	}  
	private void btnAddProviderActionPerformed(java.awt.event.ActionEvent evt) {      
		Provider provider=new Provider(txtProviderName.getText(),txtContactProviderName.getText(),
				txtDirectionProvider.getText(),txtCityProvider.getText(),txtProviderPhone.getText(),db,console);
	}
	private void btnAddPhoneToProviderActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Provider selected=(Provider)providers.getSelectedItem();
		selected.addPhoneNumber(txtProviderPhone2.getText());
	} 
	private void btnAddManufacturerActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Manufacturer manufact=new Manufacturer(txtManufacturerName.getText(),db,console);
        Manufacturers.addItem(manufact);
	} 
	private void btnAssociateActionPerformed(java.awt.event.ActionEvent evt){
		try{
			int profit=Integer.parseInt(txtProfit.getText());
			try{
				int cost=Integer.parseInt(txtCost.getText());
				db.associatePartWithProvider((Part)comboPartsInPartsTab.getSelectedItem(),
						(Provider)comboProvidersInPartsTab.getSelectedItem(),profit,cost);
			}catch(Exception ex2){
				console.errorMsg("Invalid cost value");
			}
		}catch(Exception ex){
			console.errorMsg("Invalid profit value");
		}
	}
	
	
	public static void main(String args[]){
		//open main window
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
