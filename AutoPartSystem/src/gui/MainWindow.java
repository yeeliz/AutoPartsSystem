package gui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dataBase.Brand;
import dataBase.DataBase;
import dataBase.Manufacturer;
import dataBase.Part;

import javax.swing.JTabbedPane;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import logic.Console;

import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.FlowLayout;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MainWindow extends JFrame{
	
	//gui stuff
	private JTextArea consoleTextArea;

	//logic stuff
	private Console console;
	
	//db stuff
	private DataBase db = new DataBase(console); //pass console so db can show msg's
	
	
	private static final long serialVersionUID = 1L;
	private JTextField TfPartName;
	private JTable tableResults;
	private DefaultTableModel model;
	private JTextField textField;
	private JTextField txtClientFullName;
	private JTextField txtClientCity;
	private JTextField textField_1;
	private JTextField txtManagerID;
	private JTextField txtPartName;
	private JTextField txtBrandName;
	private JTextField txtManufacturerName;
	private JButton btnAddManufacturer;
	private JButton btnAddPart;
	private JButton btnAddBrand;
	private JComboBox<Manufacturer> Manufactures;
	private JComboBox<Brand> brands;
	public MainWindow(){
		createGui();
		
		console =  new Console(this.consoleTextArea);
		loadComboBrand();
		loadComboManufactures();
		db.connect();
	}
	
	
	private void createGui(){
		setTitle("Autopart System");
	    setSize(700,500);
		
		/*
		 * Tabs for multiple views
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//panels
		JComponent clientTab = new JPanel();
		JComponent partsTab = new JPanel();
		JComponent ordersTab = new JPanel();
		
		partsTab.addMouseListener(new MouseAdapter() {// empty implementation of all
            // MouseListener`s methods
		public void mousePressed(MouseEvent e) {
			System.out.println(e.getX() + "," + e.getY());
		}
		});
		//tabs
		tabbedPane.addTab("Client", clientTab);
		tabbedPane.addTab("Parts", partsTab);
		tabbedPane.addTab("Orders", ordersTab);
		ordersTab.setLayout(null);
		
		JTabbedPane nestedTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		nestedTabbedPane.setBounds(0, 0, 679, 269);
		ordersTab.add(nestedTabbedPane);
		
		JPanel providerTab = new JPanel();
		nestedTabbedPane.addTab("Providers", null, providerTab, null);
		providerTab.setLayout(null);
		
		JLabel lbSearch = new JLabel("Search Provider");
		lbSearch.setFont(new Font("Consolas", Font.PLAIN, 16));
		lbSearch.setBounds(199, 11, 141, 27);
		providerTab.add(lbSearch);
		
		JLabel lblPartName = new JLabel("Part Name:");
		lblPartName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPartName.setBounds(109, 54, 80, 14);
		providerTab.add(lblPartName);
		
		TfPartName = new JTextField();
		TfPartName.setBounds(199, 53, 155, 20);
		providerTab.add(TfPartName);
		TfPartName.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(377, 52, 89, 23);
		providerTab.add(btnSearch);
		
		JLabel lbResults = new JLabel("Results\r\n");
		lbResults.setFont(new Font("Consolas", Font.PLAIN, 14));
		lbResults.setBounds(238, 77, 99, 53);
		providerTab.add(lbResults);
		
		//Table show provider results
		TableColumn temp = new TableColumn();
		model = new DefaultTableModel(); 
		tableResults = new JTable();
		tableResults.setModel(model);
		//add default columns
		model.addColumn("Name");
		model.addColumn("Identifier");
		model.addRow(new Object[]{"Name", "Provider"});
		
		tableResults.setBounds(109, 127, 348, 100);
		
		providerTab.add(tableResults);
		
		JTabbedPane newOrderTab = new JTabbedPane(JTabbedPane.TOP);
		nestedTabbedPane.addTab("New Order", null, newOrderTab, null);
		clientTab.setLayout(null);
		
		JList listClients = new JList();
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
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(10, 235, 89, 23);
		clientTab.add(btnSelect);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(580, 235, 89, 23);
		clientTab.add(btnUpdate);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(114, 3, 46, 14);
		clientTab.add(lblType);
		
		JComboBox clientTypeComboBox = new JComboBox();
		clientTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Personal", "Business"}));
		clientTypeComboBox.setBounds(163, 0, 76, 20);
		clientTab.add(clientTypeComboBox);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(114, 46, 46, 14);
		clientTab.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(163, 43, 152, 20);
		clientTab.add(textField);
		textField.setColumns(10);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(114, 83, 46, 14);
		clientTab.add(lblFullName);
		
		txtClientFullName = new JTextField();
		txtClientFullName.setBounds(163, 80, 152, 20);
		clientTab.add(txtClientFullName);
		txtClientFullName.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(114, 114, 46, 14);
		clientTab.add(lblCity);
		
		txtClientCity = new JTextField();
		txtClientCity.setBounds(163, 111, 152, 20);
		clientTab.add(txtClientCity);
		txtClientCity.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(114, 139, 46, 14);
		clientTab.add(lblAddress);
		
		JTextArea txtClientAddress = new JTextArea();
		txtClientAddress.setBounds(114, 164, 201, 48);
		clientTab.add(txtClientAddress);
		
		JLabel lbTels = new JLabel("Tel #'s:");
		lbTels.setBounds(356, 3, 89, 14);
		clientTab.add(lbTels);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(114, 239, 46, 14);
		clientTab.add(lblState);
		
		JComboBox clientStateComboBox = new JComboBox();
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
		lblManagerName.setBounds(366, 105, 79, 14);
		clientTab.add(lblManagerName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(455, 102, 152, 20);
		clientTab.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblManagerId = new JLabel("Manager ID:");
		lblManagerId.setBounds(376, 139, 69, 14);
		clientTab.add(lblManagerId);
		
		txtManagerID = new JTextField();
		txtManagerID.setBounds(454, 136, 153, 20);
		clientTab.add(txtManagerID);
		txtManagerID.setColumns(10);
		
		partsTab.setLayout(null);
		
		
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
		
		Manufactures=new JComboBox<Manufacturer>();
		Manufactures.setBounds(138, 95, 100, 19);
		partsTab.add(Manufactures);
		
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
		
		consoleTextArea = new JTextArea();
		consoleTextArea.setEditable(false);
		consoleTextArea.setRows(8);
		Font f = new Font("Consolas",Font.PLAIN, 16);
		consoleTextArea.setFont(f);
		getContentPane().add(consoleTextArea, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void btnAddPartActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Part part=new Part(txtPartName.getText(),(Brand) brands.getSelectedItem(), (Manufacturer)Manufactures.getSelectedItem(), db, console);
    }  
	private void btnAddBrandActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Brand brand=new Brand(txtBrandName.getText(),db,console);
        brands.addItem(brand);
	}  
	private void btnAddManufacturerActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Manufacturer manufact=new Manufacturer(txtManufacturerName.getText(),db,console);
        Manufactures.addItem(manufact);
	}  
	private void loadComboBrand(){
		try{
			//look for the brand in the database and set them in the combobox
			String query="Select * from [Marca]";
			PreparedStatement pst=db.getDbConnection().prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Brand brand=new Brand(rs.getString("Nombre"));
				brands.addItem(brand);
			}
		}catch (Exception ex){
			console.errorMsg(ex.getStackTrace().toString());
		}
		
	}
	private void loadComboManufactures(){
		try{
			//look for the brand in the database and set them in the combobox
			String query="Select * from [Fabricante]";
			PreparedStatement pst=db.getDbConnection().prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				Manufacturer manufact=new Manufacturer(rs.getString("Nombre"));
				Manufactures.addItem(manufact);
			}
		}catch (Exception ex){
			console.errorMsg(ex.getStackTrace().toString());
		}
	}
	
	public static void main(String args[]){
		//open main window
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
