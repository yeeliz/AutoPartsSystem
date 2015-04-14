package gui;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import logic.Console;
import dataBase.Brand;
import dataBase.DataBase;
import dataBase.Manufacturer;
import dataBase.Part;
import dataBase.PartPerProvider;
import dataBase.Provider;

public class PartsTab {
	private JTextField txtPartName;
	private JTextField txtBrandName;
	private JTextField txtCost;
	private JTextField txtProfit;
	private JComboBox<Manufacturer> Manufacturers;
	private JComboBox<Brand> brands;
	private JTextField txtProfitInUpdate;
	private JTextField txtCostInUpdate;
	private JComboBox<Integer> partsYearComboBox;
	private JComboBox<Provider> comboProvidersInPartsTab;
	private JComboBox<String> comboAutomobilesInPartsTab;
	private JComboBox<Part> comboPartsToAssociateWithProvider;
	private JComboBox<Part> comboPartsToAssociateWithAutomobile;
	private JComboBox<PartPerProvider> comboPartPerProvider ;
	private Console console;
	private DataBase db;
	private JComboBox<Part> comboPartsToDelete;
	
	
	public PartsTab(JTabbedPane tabbedPane,Console pConsole,DataBase pDb){
		console=pConsole;
		db=pDb;
		JComponent partsTab = new JPanel();
		partsTab.setName("partsTab");
		tabbedPane.addTab("Parts", partsTab);
		
		partsTab.setLayout(null);
		
		JTabbedPane partOppsTabbedPane=new JTabbedPane(JTabbedPane.LEFT);
		partOppsTabbedPane.setBounds(0,0,679,269);
		partsTab.add(partOppsTabbedPane);

		JPanel mainPartsTab = new JPanel();
		partOppsTabbedPane.addTab("Main", null, mainPartsTab , null);
		mainPartsTab.setLayout(null);
		JLabel lblAddPart= new JLabel("ADD PART");
		lblAddPart.setBounds(49, 5, 100, 10);
		mainPartsTab.add(lblAddPart);
		
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
		
		Manufacturers=new JComboBox<Manufacturer>();
		Manufacturers.setBounds(138, 95, 100, 19);
		mainPartsTab.add(Manufacturers);
		
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
		
		partsYearComboBox = new JComboBox<Integer>();
		partsYearComboBox.setBounds(120, 212, 200, 20);
		mainPartsTab.add(partsYearComboBox);
		comboAutomobilesInPartsTab.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
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
		
		
		btnAplay.setBounds(263, 128, 100, 19);
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
		
		JPanel deletePartsTab = new JPanel();
		partOppsTabbedPane.addTab("Delete", null, deletePartsTab, null);
		deletePartsTab.setLayout(null);
		
		
		JLabel lblDelete= new JLabel("PARTS DELETION");
		lblDelete.setBounds(49,50,100,10);
		deletePartsTab.add(lblDelete);
		
		comboPartsToDelete= new JComboBox<Part>();
		comboPartsToDelete.setBounds(49,75,100,20);
		deletePartsTab.add(comboPartsToDelete);
				
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(49,100,100,19);
		deletePartsTab.add(btnDelete);
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnDeleteActionPerformed(evt);
            }
        });
		
	}
	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt){
		try{
			if(comboPartsToDelete.getItemCount()!=0){
				((Part)comboPartsToDelete.getSelectedItem()).deleteFromDb();
				load();
			}else{
				console.errorMsg("No Parts to delete");	
			}
			
		}catch(Exception ex){
			console.errorMsg("Invalid profit value");
		}
	}
	private void btnAssociateActionPerformed(java.awt.event.ActionEvent evt){
		try{
			int profit=Integer.parseInt(txtProfit.getText());
			try{
				int cost=Integer.parseInt(txtCost.getText());
				PartPerProvider p=new PartPerProvider((Part)comboPartsToAssociateWithProvider.getSelectedItem(),
						(Provider)comboProvidersInPartsTab.getSelectedItem(),profit,cost,db,console);
				load();
			}catch(Exception ex2){
				console.errorMsg("Invalid cost value");
			}
		}catch(Exception ex){
			console.errorMsg("Invalid profit value");
		}
	}
	private void btnAssociatePartAutomobileActionPerformed(java.awt.event.ActionEvent evt) {
		db.associatePartWithAutomobile((Part)comboPartsToAssociateWithAutomobile.getSelectedItem(),
				(String)comboAutomobilesInPartsTab.getSelectedItem(),(int) this.partsYearComboBox.getSelectedItem());
		load();
	}
	private void btnAddBrandActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Brand brand=new Brand(txtBrandName.getText(),db,console);
        load();
	} 
	private void btnAddPartActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Part part=new Part(txtPartName.getText(),(Brand) brands.getSelectedItem(), (Manufacturer)Manufacturers.getSelectedItem(), db, console);
		load();
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
	private void loadComboAutomobiles(){
		ArrayList<String> automobilesList = db.getAllAutomobile();
		comboAutomobilesInPartsTab.removeAllItems();
		this.partsYearComboBox.removeAllItems();
		for(String automobile: automobilesList){
			comboAutomobilesInPartsTab.addItem(automobile);
		}

	}
	private void btnAplayActionPerformed(java.awt.event.ActionEvent evt) {                                         
		PartPerProvider p=(PartPerProvider)comboPartPerProvider.getSelectedItem();
		p.update(Integer.valueOf((txtProfitInUpdate.getText())), 
				Integer.valueOf(txtCostInUpdate.getText()));
		load();
	}
	public void load() {
		loadComboBrand();
		loadComboManufacturers();
		loadComboParts();
		loadComboProvider();
		loadComboAutomobiles();
		loadList();
	}
	private void loadComboParts(){
		ArrayList<Part> partsList = db.getAllParts();
		comboPartsToAssociateWithProvider.removeAllItems();
		comboPartsToAssociateWithAutomobile.removeAllItems();
		comboPartsToDelete.removeAllItems();
		
		for(Part part: partsList){
			comboPartsToAssociateWithProvider.addItem(part);
			comboPartsToAssociateWithAutomobile.addItem(part);
			comboPartsToDelete.addItem(part);
		}			
	}
	private void loadList(){
		ArrayList<PartPerProvider> partsList = db.getAllPartsPerProvider();
		comboPartPerProvider.removeAllItems();
		for(PartPerProvider part: partsList){
			comboPartPerProvider.addItem(part);
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
	private void loadComboManufacturers(){
		ArrayList<Manufacturer> manuList = db.getAllManufacturers();
		Manufacturers.removeAllItems();
		for(Manufacturer manufacturer : manuList)
		{
			Manufacturers.addItem(manufacturer); //this combo is on the tab parts 
		}
	}
	
}
