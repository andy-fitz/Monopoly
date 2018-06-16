/*		  Team Name:	Bankrupt
* 		      
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 
* 			  Class:	InfoPanel.java
* 
* 	    Description:    A class which sets up an Information panel 
* 				        for a monopoly board game.
* 						
* 						It will inform the player of game events and 
* 						what is generally happening in the game. 
* 
*/

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;

import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static final int TEXT_AREA_HEIGHT = 35;
	private static final int CHARACTER_WIDTH = 30;

	
	JTextArea infoTextArea = new JTextArea(TEXT_AREA_HEIGHT,CHARACTER_WIDTH);
	JScrollPane infoScroll = new JScrollPane(infoTextArea);
	DefaultCaret caret = (DefaultCaret)infoTextArea.getCaret();
	
	
	InfoPanel(){
		
		infoTextArea.setEditable(false);
		infoTextArea.setLineWrap(true);
		infoTextArea.setWrapStyleWord(true);
		infoScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setLayout(new BorderLayout());
	    TitledBorder InfoTitle = new TitledBorder("Information Panel");
	    infoTextArea.setBorder(InfoTitle);
	    infoTextArea.setBackground(new Color(219, 240, 213));
	    add(infoScroll, BorderLayout.CENTER);
	    return;
	}
	
	public void addText(String text){ 
		
		// Must make thread sleep very briefly to avoid deadlock
		try{
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		
		//if(text != null && ! text.trim().isEmpty())
			infoTextArea.append("\n" + text);	
	}
	
	public void clearInfoPanel(){
		try{
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
		infoTextArea.setText("");
	}
	
	

}
