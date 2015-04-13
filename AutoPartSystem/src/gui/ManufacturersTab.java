package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import logic.Console;
import dataBase.DataBase;
import dataBase.Manufacturer;

public class ManufacturersTab {
	
	private JTabbedPane tabbedPane;
	private DataBase db;
	private Console console;
	
	private JTextField txtManufacturerName;
	private JComboBox<Manufacturer> Manufacturers;
	
	
	public ManufacturersTab(JTabbedPane tabbedPane, DataBase db, Console console) {
		this.tabbedPane = tabbedPane;
		this.db = db;
		this.console = console;
		
		gui();
	}

	private void gui() {
		JComponent manufacturersTab=new JPanel();
		manufacturersTab.setName("manufacturersTab");
		tabbedPane.addTab("Manufacturers",manufacturersTab);
		
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
		
		
		
	}
	
	
	
	
	//BUTTON STUFF
	private void btnAddManufacturerActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Manufacturer manufact=new Manufacturer(txtManufacturerName.getText(),db,console);
        Manufacturers.addItem(manufact);
	} 

}
