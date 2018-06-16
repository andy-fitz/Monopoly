	
	
/*		  Team Name:	Bankrupt
* 		      
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 
* 			  Class:	CommandPanel.java
* 
* 	    Description:    A class which sets up a command panel 
* 				        for a monopoly board game.
* 						Player Commands can be entered in the command panel. 
* 
*/

import java.awt.event.ActionEvent;
import java.util.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.*;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class CommandPanel extends JPanel implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	private JTextField cmdTextField = new JTextField();
	private LinkedList<String> commandBuffer = new LinkedList<String>();
	
	
	
	CommandPanel () {
		
		TitledBorder cmdTitle = new TitledBorder("Command Panel");
	    cmdTextField.setBorder(cmdTitle);
	    cmdTextField.setBackground(new Color(219, 240, 213));
	    setLayout(new BorderLayout());
		add(cmdTextField, BorderLayout.SOUTH);	
		cmdTextField.addActionListener(this);
	    return;
	}
	
	public  String getCommand() {
		String command;
		synchronized (commandBuffer) {
			
			while (commandBuffer.isEmpty()) {
				try {
					commandBuffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
			}
			command = commandBuffer.pop();
		}
		return command;
	}

			
	public void actionPerformed(ActionEvent event)	{
		   
		  synchronized(commandBuffer){
			 if(cmdTextField.getText() != null && ! cmdTextField.getText().trim().isEmpty())
			 {
				 try{
					  commandBuffer.add(cmdTextField.getText());
					  cmdTextField.setText("");
					  commandBuffer.notify();
				  } catch(NullPointerException e){
					  e.printStackTrace();
					  System.out.println("null pointer caught");
				  } 
			 }
	     	else{
	     		cmdTextField.setText("");
	     	 }
			 return;
   
		  	}		  
	}
}



