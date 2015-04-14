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

	
	//db stuff
	private DataBase db;
	
	
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	

	private PartsTab partsTab;
	private ProvidersTab providersTab;
	private OrdersTab orders;
	private ClientTab clientsTb;
	private ManufacturersTab manuTb;
	private AutomobilesTab autosTb;

	
	public MainWindow(){
		createGui();
		
		console =  new Console(this.consoleTextArea);
		
		db=new DataBase(console); //pass console so db can show msg's
		db.connect();
		
		orders = new OrdersTab(tabbedPane, db, console);
		clientsTb = new ClientTab(tabbedPane, db, console);
		partsTab=new PartsTab(tabbedPane, console, db);
		providersTab= new ProvidersTab(tabbedPane, console, db);
		manuTb = new ManufacturersTab(tabbedPane, db, console);
		autosTb = new AutomobilesTab(tabbedPane, db, console);
		//first tab to show needs to preload
		orders.load();
	}
	
	
	@SuppressWarnings("unchecked")
	private void createGui(){
		setTitle("Autopart System");
	    setSize(700,500);

		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
	     /*
	        * Tab Listener
	        * We load basic DB Content when every time a tab is switched
	     */
		
	    tabbedPane.addChangeListener(new ChangeListener()
	    {
	      public void stateChanged(ChangeEvent e)
	      {
	    	 try{
	    		  loadTabsStuff();
	    	 }catch(Exception eve){
	        	
	        }
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
				partsTab.load();
				break;
			case "ordersTab":
				orders.load();
				break;
			case "providersTab":
				providersTab.load();
				break;
			case "automobilesTab":
				autosTb.load();
				break;
		}	
	}
	
	
	
	public static void main(String args[]){
		//open main window
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
