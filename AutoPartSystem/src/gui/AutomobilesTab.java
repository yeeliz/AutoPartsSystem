package gui;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import logic.Console;
import dataBase.Automobile;
import dataBase.DataBase;
import dataBase.Manufacturer;

public class AutomobilesTab {

	private JTabbedPane tabbedPane;
	private DataBase db;
	private Console console;
	
	private JTextField txtDetail;
	private JTextField txtModel;
	private JTextField txtFabricationYear;
	private JComboBox<Manufacturer> comboManufacturersOfAutomobile;
	
	public AutomobilesTab(JTabbedPane tabbedPane, DataBase db, Console console) {
		this.tabbedPane = tabbedPane;
		this.db = db;
		this.console = console;
		
		gui();
	}

	private void gui() {
		JComponent automobileTab=new JPanel();
		automobileTab.setName("automobilesTab");
		tabbedPane.addTab("Automobiles",automobileTab);
		
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
	}
	
	//GUI HELPER STUFF
	public void load(){
		ArrayList<Manufacturer> manuList = db.getAllManufacturers();
		comboManufacturersOfAutomobile.removeAllItems();
		for(Manufacturer manufacturer : manuList)
			comboManufacturersOfAutomobile.addItem(manufacturer); //this combo is on the Automobile tab 
	}
	
	//BUTTON STUFF
	private void btnAddAutomobileActionPerformed(java.awt.event.ActionEvent evt) {  
		try{
			if(validateEntry()){
				Automobile automobile= new Automobile(txtModel.getText(),txtDetail.getText(),
						Integer.valueOf(txtFabricationYear.getText()),
						(Manufacturer)comboManufacturersOfAutomobile.getSelectedItem(),
						db,console);				
			}else{
				System.out.println("Here");
				console.errorMsg("Please insert good data");				
			}
		}catch (Exception ex){
			System.out.println(ex.toString());
			console.errorMsg("Please insert good data");
		}
	}
	private boolean validateEntry(){
		if(!txtModel.getText().equals("")&& !txtDetail.getText().equals("") &&
				!txtFabricationYear.getText().equals("")
				&&comboManufacturersOfAutomobile.getItemCount()!=0){
			return true;
		}else{
			return false;
		}
	}
	
}
