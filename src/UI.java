/*		  Team Name:	Bankrupt
* 		      
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
*
* 			  Class:	UI.java
* 
* 	    Description:    A class which sets up the User interface for a monopoly board game.	
* 						It adds all necessary panels to the frame.	
* 
*/

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.util.ArrayList;


public class UI {
	
	private static final int FRAME_WIDTH = 1030;
	private static final int FRAME_HEIGHT = 700;
	
	private JFrame mainframe = new JFrame();
	private BoardPanel boardPanel;
	private InfoPanel infoPanel = new InfoPanel();
	private CommandPanel commandPanel = new CommandPanel();
	private InputOutputPanel IOPanel = new InputOutputPanel();
	
	UI (ArrayList<Player> players){
		boardPanel = new BoardPanel(players);
		mainframe.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		mainframe.setTitle("Monopoly");
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		IOPanel.add(commandPanel, BorderLayout.SOUTH);
		IOPanel.add(infoPanel, BorderLayout.NORTH);
		
		mainframe.add(IOPanel, BorderLayout.EAST);
		mainframe.add(boardPanel, BorderLayout.WEST);
		
		mainframe.setResizable(false);
		mainframe.setVisible(true);
		//mainframe.pack();
		return;
	}
	
	public  String getCommand(){
		return commandPanel.getCommand();
	}
	
	public void display(){
		boardPanel.refresh();
		return;
	}
	
	public void displayString(String string){
		infoPanel.addText(string);
		return;
	}
	
	public void clearInfo(){
		infoPanel.clearInfoPanel();
	}
	
	
}
