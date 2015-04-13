package gui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EtchedBorder;

import logic.Console;
import logic.Parser;
import dataBase.Client;
import dataBase.Company;
import dataBase.DataBase;
import dataBase.Person;

public class ClientTab {

	private JTabbedPane tabbedPane;
	private DataBase db;
	private Console console;
	private Parser parser;
	
	
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
	private JTextField txtContactName;
	private JList<String> jListClients;
	private ArrayList<Object> modelClientList = new ArrayList<Object>();
	//--<<
	
	public ClientTab(JTabbedPane tabbedPane, DataBase db, Console console){
		this.tabbedPane = tabbedPane;
		this.db = db;
		this.console = console;
		parser = new Parser(console);
		
		gui();
	}

	private void gui() {
		JComponent clientTab = new JPanel();
		clientTab.setName("clientTab");
		tabbedPane.addTab("Client", clientTab);
		
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
		
		
		
		clientTab.setLayout(null);
		//---<<<< END OF CLIENT TAB
		
	}
	
	
	
	//BEGIN GUI HELPERS
	
	//reload TAB's GUI contents from DB
	
	public void load(){
		//fill JClientList
		ArrayList<Client> clientList = db.getAllClients();
		modelClientList.clear(); //clear list first
		
		for(Client client : clientList)
				modelClientList.add(client);
		
		modelClientList.add((String) "New Client");
	}
	
	
	
	//BUTTON ACTIONS
	
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
	
}
