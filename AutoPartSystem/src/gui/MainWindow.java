package gui;

import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.*;

import dataBase.Brand;
import dataBase.Client;
import dataBase.DataBase;
import dataBase.Manufacturer;
import dataBase.Part;
import dataBase.Person;

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
	private JTextField txtClientCity;
	private JTextField txtManagerID;
	//--<<
	
	private JTextField TfPartName;
	private JTable tableResults;
	private DefaultTableModel model;
	private JTextField textField_1;
	private JTextField txtPartName;
	private JTextField txtBrandName;
	private JTextField txtManufacturerName;
	private JTextField txtProviderName;
	private JButton btnAddManufacturer;
	private JButton btnAddPart;
	private JButton btnAddBrand;
	
	private JComboBox<Manufacturer> manufactures;
	private JComboBox<Brand> brands;
	
	private JPanel providersInfo;
	
	public MainWindow(){
		createGui();
		
		console =  new Console(this.consoleTextArea);
		db=new DataBase(console); //pass console so db can show msg's
		db.connect();
	}
	
	
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
		
		
		//tabs
		tabbedPane.addTab("Client", clientTab);
		tabbedPane.addTab("Parts", partsTab);
		tabbedPane.addTab("Orders", ordersTab);
		tabbedPane.addTab("Providers",providersTab);
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
		
		newProviderTab.addMouseListener(new MouseAdapter() {// empty implementation of all
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
		txtProviderName.setBounds(99, 23, 100, 19);
		txtProviderName.setColumns(10);
		newProviderTab.add(txtProviderName);
		
		
		
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
		JList<String> listClients = new JList<String>();
		listClients.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listClients.setBounds(0, 0, 104, 231);
		listClients.setModel(new AbstractListModel() {
			String[] values = new String[] {"New Client"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		clientTab.add(listClients);
		
		//type of client
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(114, 3, 46, 14);
		clientTab.add(lblType);
		
		clientTypeComboBox = new JComboBox();
		clientTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Personal", "Business"}));
		clientTypeComboBox.setBounds(163, 0, 76, 20);
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
		
		JTextArea txtTels = new JTextArea();
		txtTels.setBounds(417, 3, 123, 59);
		clientTab.add(txtTels);
		
		JLabel lblNewLabel = new JLabel("If Business:");
		lblNewLabel.setBounds(356, 80, 76, 14);
		clientTab.add(lblNewLabel);
		
		JLabel lblManagerName = new JLabel("Manager Name:");
		lblManagerName.setBounds(356, 114, 89, 14);
		clientTab.add(lblManagerName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(455, 111, 152, 20);
		clientTab.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblManagerId = new JLabel("Manager ID:");
		lblManagerId.setBounds(366, 139, 69, 14);
		clientTab.add(lblManagerId);
		
		txtManagerID = new JTextField();
		txtManagerID.setBounds(455, 136, 153, 20);
		clientTab.add(txtManagerID);
		txtManagerID.setColumns(10);
		
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
		
		JLabel lblManufactures= new JLabel("Manufactures:");
		lblManufactures.setBounds(49, 95, 100, 10);
		partsTab.add(lblManufactures);
		
		manufactures=new JComboBox<Manufacturer>();
		manufactures.setBounds(138, 95, 100, 19);
		partsTab.add(manufactures);
		
		btnAddPart=new JButton("Add");
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
		
		btnAddBrand=new JButton("Add");
		btnAddBrand.setBounds(283, 59, 60, 19);
		partsTab.add(btnAddBrand);
		btnAddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddBrandActionPerformed(evt);
            }
        });
		
		
		JLabel lblAddManufacturer= new JLabel("ADD MANUFACTURER");
		lblAddManufacturer.setBounds(486, 5, 150, 10);
		partsTab.add(lblAddManufacturer);
		
		JLabel lblManufacturerName= new JLabel("Manufac Name:");
		lblManufacturerName.setBounds(486, 32, 100, 10);
		partsTab.add(lblManufacturerName);
		
		txtManufacturerName=new JTextField();
		txtManufacturerName.setBounds(581,27, 100, 19);
		partsTab.add(txtManufacturerName);
		txtManufacturerName.setColumns(10);
		
		btnAddManufacturer=new JButton("Add");
		btnAddManufacturer.setBounds(486, 59, 60, 19);
		partsTab.add(btnAddManufacturer);
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
				//loadClientStuff();
			case "partsTab":
				loadPartsStuff();
			case "ordersTab":
				//loadOrdersStuff();
			case "providersTab":
				//loadProvidersStuff();
		}
	}	
	
	//reload comboBox's from DB
	private void loadPartsStuff() {
		loadComboBrand();
		loadComboManufactures();
	}	
	
	
	private void loadComboBrand(){
		ArrayList<Brand> brandList = db.getAllBrands();
		brands.removeAllItems();
		
		for(Brand brand : brandList)
			brands.addItem(brand);
	}
	private void loadComboManufactures(){
		ArrayList<Manufacturer> manuList = db.getAllManufactures();
		manufactures.removeAllItems();
		
		for(Manufacturer manufacturer : manuList)
			manufactures.addItem(manufacturer);
	}
	
	//--<<<<<<
	
	/*
	 * Client gui event/action section
	 */
	private void updateClientBtn(java.awt.event.ActionEvent evt){
		if(this.clientTypeComboBox.getSelectedItem().toString().compareTo("Personal") == 0){
		
			int id = -1;
			try{
				id = Integer.parseInt(txtFieldID.getText().toString());
			}catch(Exception e){
				console.printConsole("Could not parse ID to int");
			}
			
			//get info
			Person per = new Person(db, this.txtClientFullName.getText(),
					this.txtClientAddress.getText(), 
					this.clientStateComboBox.getSelectedItem().toString(),
					id,this.txtClientCity.getText());
			
			//save it to the server
			console.printConsole("Saving Personal Client to DB");
			per.addClient();
		}else{
			console.printConsole("Non personal not implemented");
		}
	}
	
	private void btnAddPartActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Part part=new Part(txtPartName.getText(),(Brand) brands.getSelectedItem(), (Manufacturer)manufactures.getSelectedItem(), db, console);
    }  
	private void btnAddBrandActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Brand brand=new Brand(txtBrandName.getText(),db,console);
        brands.addItem(brand);
	}  
	private void btnAddManufacturerActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Manufacturer manufact=new Manufacturer(txtManufacturerName.getText(),db,console);
        manufactures.addItem(manufact);
	} 
	
	
	public static void main(String args[]){
		//open main window
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
