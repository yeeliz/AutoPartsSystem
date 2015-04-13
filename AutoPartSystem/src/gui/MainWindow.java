package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
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

import dataBase.Automobile;
import dataBase.Brand;
import dataBase.Client;
import dataBase.Company;
import dataBase.DataBase;
import dataBase.Manufacturer;
import dataBase.Order;
import dataBase.Part;
import dataBase.PartPerProvider;
import dataBase.PartsPerOrder;
import dataBase.Person;
import dataBase.Provider;

import javax.swing.JTabbedPane;

import java.awt.event.MouseEvent;
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
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;


public class MainWindow extends JFrame{
	
	//gui stuff
	private JTextArea consoleTextArea;

	//logic stuff
	private Console console;

	
	//db stuff
	private DataBase db;
	
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	

	
	
	


	private JTextField txtPartName;
	private JTextField txtBrandName;

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
	private JComboBox<Part> comboPartsToAssociateWithProvider;
	private JComboBox<String> comboAutomobilesInPartsTab;
	private JComboBox<Part> comboPartsToAssociateWithAutomobile;
	private JComboBox<PartPerProvider> comboPartPerProvider ;
	private JComboBox<Integer> partsYearComboBox;
	
	private JComboBox<Manufacturer> Manufacturers;
	private JComboBox<Brand> brands;
	private JTextField txtProfitInUpdate;
	private JTextField txtCostInUpdate;
	

	private OrdersTab orders;
	private ClientTab clientsTb;
	private AutomobilesTab autosTb;
	private ManufacturersTab manuTb;
	
	public MainWindow(){
		createGui();
		
		console =  new Console(this.consoleTextArea);
		
		db=new DataBase(console); //pass console so db can show msg's
		db.connect();
		
		orders = new OrdersTab(tabbedPane, db, console);
		clientsTb = new ClientTab(tabbedPane, db, console);
		autosTb = new AutomobilesTab(tabbedPane, db, console);
		manuTb = new ManufacturersTab(tabbedPane, db, console);
		
		//first tab to show needs to preload
		orders.load();
		
		
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

		JComponent partsTab = new JPanel();
		partsTab.setName("partsTab");
		JComponent providersTab=new JPanel();
		providersTab.setName("providersTab");


		
		
		
		
		
		tabbedPane.addTab("Parts", partsTab);
		tabbedPane.addTab("Providers",providersTab);
		
		
		Manufacturers=new JComboBox<Manufacturer>();
		Manufacturers.setBounds(138, 95, 100, 19);
		
		
		
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
		
		JTabbedPane proviOppsTabbedPane=new JTabbedPane(JTabbedPane.LEFT);
		proviOppsTabbedPane.setBounds(0, 0, 679, 269);
		providersTab.add(proviOppsTabbedPane);
		
		JTabbedPane partOppsTabbedPane=new JTabbedPane(JTabbedPane.LEFT);
		partOppsTabbedPane.setBounds(0,0,679,269);
		partsTab.add(partOppsTabbedPane);
		
		
		JPanel newProviderTab = new JPanel();
		proviOppsTabbedPane.addTab("New", null, newProviderTab , null);
		newProviderTab.setLayout(null);
		
//		partsTab.addMouseListener(new MouseAdapter() {// empty implementation of all
//            // MouseListener`s methods
//		public void mousePressed(MouseEvent e) {
//			System.out.println(e.getX() + "," + e.getY());
//		}
//		});
		
	
		/*
		 * Begin Providers GUI Stuff
		 */
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
			System.out.println(ex.toString());
		}
		
		JButton btnAddProvider= new JButton("Add provider");
		btnAddProvider.setBounds(49,133,120,19);
		newProviderTab.add(btnAddProvider);
		btnAddProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddProviderActionPerformed(evt);
            }
        });
		partsTab.setLayout(null);
		
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
		
		
		
		/*
		 * Begin Part GUI stuff
		 */
		
		JPanel mainPartsTab = new JPanel();
		partOppsTabbedPane.addTab("Main", null, mainPartsTab , null);
		mainPartsTab.setLayout(null);
		JLabel lblAddPart= new JLabel("ADD PART");
		lblAddPart.setBounds(49, 5, 100, 10);
		mainPartsTab.add(lblAddPart);
		mainPartsTab.add(Manufacturers);
		JLabel lblPartName1= new JLabel("Part Name:");
		lblPartName1.setBounds(49, 32, 100, 10);
		mainPartsTab.add(lblPartName1);
		
		txtPartName=new JTextField();
		txtPartName.setBounds(138,27, 100, 19);
		mainPartsTab.add(txtPartName);
		txtPartName.setColumns(10);
		
		JLabel lblBrands= new JLabel("Brands:");
		lblBrands.setBounds(49, 59, 100, 10);
		mainPartsTab.add(lblBrands);
		
		brands=new JComboBox<Brand>();
		brands.setBounds(138, 59, 100, 19);
		mainPartsTab.add(brands);
		
		JLabel lblManufacturers= new JLabel("Manufacturers:");
		lblManufacturers.setBounds(49, 95, 100, 10);
		mainPartsTab.add(lblManufacturers);
		

		
		JButton btnAddPart=new JButton("Add");
		btnAddPart.setBounds(49, 123, 60, 19);
		mainPartsTab.add(btnAddPart);
		btnAddPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddPartActionPerformed(evt);
            }
        });
		
		JLabel lblSetAutomobile= new JLabel("SET AUTOMOBILE TO A PART");
		lblSetAutomobile.setBounds(49, 153, 220, 10);
		mainPartsTab.add(lblSetAutomobile);
		
		JLabel lblPart=new JLabel("Part:");
		lblPart.setBounds(49,173,100,10);
		mainPartsTab.add(lblPart);
		comboPartsToAssociateWithAutomobile=new JComboBox<Part>();
		comboPartsToAssociateWithAutomobile.setBounds(120,173,200,19);
		mainPartsTab.add(comboPartsToAssociateWithAutomobile);
		
		JLabel lblAutomobile =new JLabel("Automobile:");
		lblAutomobile.setBounds(49,193,100,10);
		mainPartsTab.add(lblAutomobile);
		
		comboAutomobilesInPartsTab= new JComboBox<String>();
		comboAutomobilesInPartsTab.setBounds(120,193,200,19);
		mainPartsTab.add(comboAutomobilesInPartsTab);
		
		JButton btnAssociatePartAutomobile=new JButton("Associate");
		btnAssociatePartAutomobile.setBounds(49,240, 100, 19);
		mainPartsTab.add(btnAssociatePartAutomobile);
		btnAssociatePartAutomobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAssociatePartAutomobileActionPerformed(evt);
            }
        });	
		
		JLabel lblAddBrand= new JLabel("ADD BRAND");
		lblAddBrand.setBounds(283, 5, 100, 10);
		mainPartsTab.add(lblAddBrand);
		
		JLabel lblBrandName= new JLabel("Brand Name:");
		lblBrandName.setBounds(283, 32, 100, 10);
		mainPartsTab.add(lblBrandName);
		
		txtBrandName=new JTextField();
		txtBrandName.setBounds(367,27, 100, 19);
		mainPartsTab.add(txtBrandName);
		txtBrandName.setColumns(10);
		
		JButton btnAddBrand=new JButton("Add");
		btnAddBrand.setBounds(283, 59, 60, 19);
		mainPartsTab.add(btnAddBrand);
		btnAddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAddBrandActionPerformed(evt);
            }
        });
		
		JLabel lblAddProviderForAPart=new JLabel("Add Provider for a part");
		lblAddProviderForAPart.setFont(new Font("Consolas", Font.PLAIN, 14));
		lblAddProviderForAPart.setBounds(330, 108, 220, 15);
		mainPartsTab.add(lblAddProviderForAPart);
		
		JLabel  lblParts= new JLabel("Part:");
		lblParts.setBounds(330,128,100,15);
		mainPartsTab.add(lblParts);
		
		comboPartsToAssociateWithProvider =new JComboBox<Part>();
		comboPartsToAssociateWithProvider.setBounds(390,128,220,19);
		mainPartsTab.add(comboPartsToAssociateWithProvider);
		
		JLabel  lblProviders= new JLabel("Provider:");
		lblProviders.setBounds(330,148,100,15);
		mainPartsTab.add(lblProviders);
		
		comboProvidersInPartsTab =new JComboBox<Provider>();
		comboProvidersInPartsTab.setBounds(390,148,220,19);
		mainPartsTab.add(comboProvidersInPartsTab);
		
		JLabel lblCost=new JLabel("Cost:");
		lblCost.setBounds(330,168,100,15);
		mainPartsTab.add(lblCost);
		
		txtCost=new JTextField();
		txtCost.setBounds(390,168,220,19);
		txtCost.setColumns(10);
		mainPartsTab.add(txtCost);
		
		
		JLabel lblProfit= new JLabel("Profit %: ");
		lblProfit.setBounds(330,188,100,15);
		mainPartsTab.add(lblProfit);
		
		txtProfit=new JTextField();
		txtProfit.setBounds(390,188,220,19);
		txtProfit.setColumns(10);
		mainPartsTab.add(txtProfit);
		
		JButton btnAssociate=new JButton("Associate");
		btnAssociate.setBounds(330,213,100,19);
		mainPartsTab.add(btnAssociate);
		
		JLabel lblYear_1 = new JLabel("Year:");
		lblYear_1.setBounds(49, 215, 46, 14);
		mainPartsTab.add(lblYear_1);
		
		partsYearComboBox = new JComboBox();
		partsYearComboBox.setBounds(120, 212, 200, 20);
		mainPartsTab.add(partsYearComboBox);
		comboAutomobilesInPartsTab.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				partsYearComboActionPerformed(e);
			}
        });
		btnAssociate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssociateActionPerformed(evt);
            }
        });
		
		JPanel updatePartsTab = new JPanel();
		partOppsTabbedPane.addTab("Update", null, updatePartsTab , null);
		updatePartsTab.setLayout(null);
		
		JLabel lblChangePartproviderData = new JLabel("CHANGE PART-PROVIDER DATA");
		lblChangePartproviderData.setBounds(45, 22, 255, 22);
		updatePartsTab.add(lblChangePartproviderData);
		
		JLabel lblProfitPorcentage = new JLabel("Profit Porcentage:");
		lblProfitPorcentage.setBounds(263, 62, 108, 22);
		updatePartsTab.add(lblProfitPorcentage);
		
		JLabel lblCost_1 = new JLabel("Cost:");
		lblCost_1.setBounds(263, 95, 88, 22);
		updatePartsTab.add(lblCost_1);
		
		JButton btnAplay = new JButton("Aplay");
		btnAplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnAplayActionPerformed(evt);
            }
        });
		
		
		btnAplay.setBounds(263, 128, 60, 19);
		updatePartsTab.add(btnAplay);
		
		txtProfitInUpdate = new JTextField();
		txtProfitInUpdate.setColumns(10);
		txtProfitInUpdate.setBounds(376, 62, 100, 19);
		updatePartsTab.add(txtProfitInUpdate);
		
		txtCostInUpdate = new JTextField();
		txtCostInUpdate.setColumns(10);
		txtCostInUpdate.setBounds(376, 96, 100, 19);
		updatePartsTab.add(txtCostInUpdate);
		
		comboPartPerProvider = new JComboBox<PartPerProvider>();
		comboPartPerProvider.setBounds(45, 63, 208, 21);
		updatePartsTab.add(comboPartPerProvider);
		
		
		


		
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
				clientsTb.load();
				break;
			case "partsTab":
				loadPartsStuff();
				break;
			case "ordersTab":
				orders.load();
				break;
			case "providersTab":
				loadProvidersStuff();
				break;
			case "automobilesTab":
				orders.load();
				break;
		}
	}	
	

	

	

	
	private void loadPartsStuff() {
		loadComboBrand();
	
		loadComboParts();
		loadComboProvider();
		loadComboAutomobiles();
		loadList();
	}
	private void loadProvidersStuff(){
		ArrayList<Provider> providerList = db.getAllProviders();
		providers.removeAllItems();
		
		for(Provider provider: providerList)
			providers.addItem(provider);
	}


	
	private void loadComboParts(){
		ArrayList<Part> partsList = db.getAllParts();
		comboPartsToAssociateWithProvider.removeAllItems();
		comboPartsToAssociateWithAutomobile.removeAllItems();
		
		for(Part part: partsList){
			comboPartsToAssociateWithProvider.addItem(part);
			comboPartsToAssociateWithAutomobile.addItem(part);
		}			
	}
	
	private void loadList(){
		ArrayList<PartPerProvider> partsList = db.getAllPartsPerProvider();
		comboPartPerProvider.removeAllItems();
		for(PartPerProvider part: partsList){
			comboPartPerProvider.addItem(part);
		}
	};
	
	private void loadComboAutomobiles(){
		ArrayList<String> automobilesList = db.getAllAutomobile();
		comboAutomobilesInPartsTab.removeAllItems();
		this.partsYearComboBox.removeAllItems();
		for(String automobile: automobilesList){
			comboAutomobilesInPartsTab.addItem(automobile);
		}
	}
	
	private void loadComboProvider(){
		ArrayList<Provider> providerList = db.getAllProviders();
		comboProvidersInPartsTab.removeAllItems();
		for(Provider provider: providerList)
			comboProvidersInPartsTab.addItem(provider);		
	}
	private void loadComboBrand(){
		ArrayList<Brand> brandList = db.getAllBrands();
		brands.removeAllItems();
		
		for(Brand brand : brandList)
			brands.addItem(brand);
	}

	
	/*
	 * Client gui event/action section
	 */
	

	
	
	
	private void btnAddPartActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Part part=new Part(txtPartName.getText(),(Brand) brands.getSelectedItem(), (Manufacturer)Manufacturers.getSelectedItem(), db, console);
    }
	private void partsYearComboActionPerformed(ItemEvent e) {   
		partsYearComboBox.removeAllItems();
		String automobile=(String)comboAutomobilesInPartsTab.getSelectedItem();
		if(automobile!=null){
			ArrayList<Integer> years=db.getAllFabricationYears(automobile.toString());
			for(Integer i:years)
				this.partsYearComboBox.addItem(i);	
		}
    }
	private void btnAddBrandActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Brand brand=new Brand(txtBrandName.getText(),db,console);
        brands.addItem(brand);
	}  
	private void btnAssociatePartAutomobileActionPerformed(java.awt.event.ActionEvent evt) {
		db.associatePartWithAutomobile((Part)comboPartsToAssociateWithAutomobile.getSelectedItem(),
				(String)comboAutomobilesInPartsTab.getSelectedItem(),(int) this.partsYearComboBox.getSelectedItem());
	}
	private void btnAddProviderActionPerformed(java.awt.event.ActionEvent evt) {      
		Provider provider=new Provider(txtProviderName.getText(),txtContactProviderName.getText(),
				txtDirectionProvider.getText(),txtCityProvider.getText(),txtProviderPhone.getText(),db,console);
	}
	private void btnAddPhoneToProviderActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Provider selected=(Provider)providers.getSelectedItem();
		selected.addPhoneNumber(txtProviderPhone2.getText());
	}

	private void btnAplayActionPerformed(java.awt.event.ActionEvent evt) {                                         
		PartPerProvider p=(PartPerProvider)comboPartPerProvider.getSelectedItem();
		p.update(Integer.valueOf((txtProfitInUpdate.getText())), 
				Integer.valueOf(txtCostInUpdate.getText()));
	}

	private void btnAssociateActionPerformed(java.awt.event.ActionEvent evt){
		try{
			int profit=Integer.parseInt(txtProfit.getText());
			try{
				int cost=Integer.parseInt(txtCost.getText());
				PartPerProvider p=new PartPerProvider((Part)comboPartsToAssociateWithProvider.getSelectedItem(),
						(Provider)comboProvidersInPartsTab.getSelectedItem(),profit,cost,db,console);
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
