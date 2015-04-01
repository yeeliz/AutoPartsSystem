package gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import dataBase.DataBase;

public class MainWindow extends JFrame{
	
	private DataBase db = new DataBase();
	
	private static final long serialVersionUID = 1L;

	public MainWindow(){
		createGui();
		db.connect();
	}
	
	private void createGui(){
		setTitle("Autopart System");
	    setSize(700,500);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{254, 232, 215, 0};
		gridBagLayout.rowHeights = new int[]{157, 0, 119, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnClientes = new JButton("CLIENTES");
		GridBagConstraints gbc_btnClientes = new GridBagConstraints();
		gbc_btnClientes.insets = new Insets(0, 0, 5, 5);
		gbc_btnClientes.gridx = 0;
		gbc_btnClientes.gridy = 0;
		getContentPane().add(btnClientes, gbc_btnClientes);
		
		JButton btnOrdenes = new JButton("ORDENES");
		GridBagConstraints gbc_btnOrdenes = new GridBagConstraints();
		gbc_btnOrdenes.insets = new Insets(0, 0, 5, 5);
		gbc_btnOrdenes.gridx = 1;
		gbc_btnOrdenes.gridy = 0;
		getContentPane().add(btnOrdenes, gbc_btnOrdenes);
		
		JButton btnPartes = new JButton("PARTES");
		GridBagConstraints gbc_btnPartes = new GridBagConstraints();
		gbc_btnPartes.insets = new Insets(0, 0, 5, 0);
		gbc_btnPartes.gridx = 2;
		gbc_btnPartes.gridy = 0;
		getContentPane().add(btnPartes, gbc_btnPartes);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		getContentPane().add(separator, gbc_separator);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 2;
		getContentPane().add(textArea, gbc_textArea);
		
		
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String args[]){
		//open main window
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
	
	
}
