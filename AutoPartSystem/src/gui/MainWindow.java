package gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dataBase.DataBase;

import javax.swing.JTabbedPane;

import com.sun.glass.events.KeyEvent;

import javax.swing.JFormattedTextField;

import java.awt.BorderLayout;
import java.awt.Font;

import logic.Console;

public class MainWindow extends JFrame{
	
	//gui stuff
	private JTextArea consoleTextArea;
	//db stuff
	private DataBase db = new DataBase();
	//logic stuff
	private Console console;
	
	
	private static final long serialVersionUID = 1L;

	public MainWindow(){
		createGui();
		console =  new Console(this.consoleTextArea);
		db.connect(console);
	}
	
	private void createGui(){
		setTitle("Autopart System");
	    setSize(700,500);
		
		/*
		 * Tabs for multiple views
		 */
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//panels
		JComponent panel1 = new JPanel();
		JComponent panel2 = new JPanel();
		JComponent panel3 = new JPanel();
		//tabs
		tabbedPane.addTab("Cliente", panel1);
		tabbedPane.addTab("Partes", panel2);
		tabbedPane.addTab("Ordenes", panel3);
		
		
		JTextField textField = new JTextField();
		textField.setText("Cool Text");
		panel1.add(textField);
		
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
	
	
	public static void main(String args[]){
		//open main window
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
