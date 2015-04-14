package gui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import dataBase.DataBase;
import dataBase.Provider;
import logic.Console;

public class ProvidersTab {
	private JTextField txtProviderName;
	private JTextField txtContactProviderName;
	private JTextField txtDirectionProvider;
	private JTextField txtCityProvider;
	
	private Console console;
	
	private DataBase db;
	private JFormattedTextField txtProviderPhone;
	private JFormattedTextField txtProviderPhone2;
	private JComboBox<Provider> providers;
	public ProvidersTab(JTabbedPane tabbedPane,Console pConsole, DataBase pDb){
		JComponent providersTab=new JPanel();
		providersTab.setName("providersTab");

		tabbedPane.addTab("Providers",providersTab);
		
		providersTab.setLayout(null);
		console=pConsole;
		db=pDb;
		
		JTabbedPane proviOppsTabbedPane=new JTabbedPane(JTabbedPane.LEFT);
		proviOppsTabbedPane.setBounds(0, 0, 679, 269);
		providersTab.add(proviOppsTabbedPane);
		
		
		
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
		
		
	}
	private void btnAddProviderActionPerformed(java.awt.event.ActionEvent evt) {      
		Provider provider=new Provider(txtProviderName.getText(),txtContactProviderName.getText(),
				txtDirectionProvider.getText(),txtCityProvider.getText(),txtProviderPhone.getText(),db,console);
		load();
	}
	private void btnAddPhoneToProviderActionPerformed(java.awt.event.ActionEvent evt) {                                         
		Provider selected=(Provider)providers.getSelectedItem();
		selected.addPhoneNumber(txtProviderPhone2.getText());
		load();
	}
	public void load(){
		ArrayList<Provider> providerList = db.getAllProviders();
		providers.removeAllItems();
		
		for(Provider provider: providerList)
			providers.addItem(provider);
		
	}
	
}
