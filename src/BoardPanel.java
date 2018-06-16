/*		  Team Name:	Bankrupt
* 		     
* 			Authors:    Andrew Fitzgerald  12376456
* 		     	 		Ray Hamill		   15342516
* 				 		Catherine Johnson  14475132
* 
* 			  Class:	BoardPanel.java
* 
* 	    Description:    A class which sets up a Board panel 
* 				        for a monopoly board game.
* 						
* 						It loads in a board image and paints it on the board Panel.
*/

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private final int BOARD_PANEL_HEIGHT = 650;
	private final int BOARD_PANEL_WIDTH = 650;
	
	private ArrayList<Player> players;
	private Image boardImage;
	
	//Array of point objects stores the co-ordinates for each token at each square.
	class Point{
		
		int[] point = {570, 590, 590, 590, 570, 610, 590, 610, 570, 630, 590, 630};
	}
	Point[] square = new Point[41];
	
	//Constructing the BoardPanel
	BoardPanel(ArrayList<Player> players){
		this.players = players;
		setPreferredSize(new Dimension(BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT));
		setBackground(new Color(219, 240, 213));
		
		square[0]=new Point();
		
		//for loop creates a point object for each square and sets the correct co-ordinates depending on each square location.
		for(int i=1; i<40; i++){
			
			square[i]=new Point();
			if(i<10){
				for(int j=0; j<12; j+=2){
					square[i].point[j]=(square[i-1].point[j])-53;
				}
			}
			
			else if(i==10){
				
				square[i].point[0]=45;
				square[i].point[1]=570;
				square[i].point[2]=45;
				square[i].point[3]=590;
				square[i].point[4]=25;
				square[i].point[5]=570;
				square[i].point[6]=25;
				square[i].point[7]=590;
				square[i].point[8]=5;
				square[i].point[9]=570;
				square[i].point[10]=5;
				square[i].point[11]=590;
				
			}

			else if(i<20 && i>9){
				
				square[i].point[0]=45;
				square[i].point[1]=570;
				square[i].point[2]=45;
				square[i].point[3]=590;
				square[i].point[4]=25;
				square[i].point[5]=570;
				square[i].point[6]=25;
				square[i].point[7]=590;
				square[i].point[8]=5;
				square[i].point[9]=570;
				square[i].point[10]=5;
				square[i].point[11]=590;
				
				for(int j=1; j<12; j+=2){
					square[i].point[j]=(square[i-1].point[j])-53;
				}
			}
			
			else if(i==20){
				
				square[i].point[0]=62;
				square[i].point[1]=45;
				square[i].point[2]=42;
				square[i].point[3]=45;
				square[i].point[4]=62;
				square[i].point[5]=25;
				square[i].point[6]=42;
				square[i].point[7]=25;
				square[i].point[8]=62;
				square[i].point[9]=5;
				square[i].point[10]=42;
				square[i].point[11]=5;
				
			}

			else if(i<30 && i>20){
			
				square[i].point[0]=62;
				square[i].point[1]=45;
				square[i].point[2]=42;
				square[i].point[3]=45;
				square[i].point[4]=62;
				square[i].point[5]=25;
				square[i].point[6]=42;
				square[i].point[7]=25;
				square[i].point[8]=62;
				square[i].point[9]=5;
				square[i].point[10]=42;
				square[i].point[11]=5;
				
				for(int j=0; j<12; j+=2){
					square[i].point[j]=(square[i-1].point[j])+53;
				}

			}
			
			else if(i==30){
				
				square[i].point[0]=590;
				square[i].point[1]=62;
				square[i].point[2]=590;
				square[i].point[3]=42;
				square[i].point[4]=610;
				square[i].point[5]=62;
				square[i].point[6]=610;
				square[i].point[7]=42;
				square[i].point[8]=630;
				square[i].point[9]=62;
				square[i].point[10]=630;
				square[i].point[11]=42;
				
			}
			
			else if(i<40 && i>30){
				
				square[i].point[0]=590;
				square[i].point[1]=62;
				square[i].point[2]=590;
				square[i].point[3]=42;
				square[i].point[4]=610;
				square[i].point[5]=62;
				square[i].point[6]=610;
				square[i].point[7]=42;
				square[i].point[8]=630;
				square[i].point[9]=62;
				square[i].point[10]=630;
				square[i].point[11]=42;
				
				for(int j=1; j<12; j+=2){
					square[i].point[j]=(square[i-1].point[j])+53;
				}
			}
		}
		
		boardImage = loadAndScale("res/ukmonop.jpg", BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT);
		return;
	}
	
	//Painting the board
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(boardImage, 0, 0,BOARD_PANEL_WIDTH, BOARD_PANEL_HEIGHT, this);	
		//j stores the location in the point array of the token, and in the following 
		//for loop j increments by two as each iteration of the loop uses the x co-ordinate and 
		//the proceeding y co-ordinate.
		int j=0;
		
		//Token Painting here
		for(int i=0; i<players.size(); i++){

			g2.setColor(players.get(i).getPlayerColor());
			
			int position = players.get(i).getPosition();
		
			g2.fill(new Ellipse2D.Double((square[position].point[j]), square[position].point[j+1], 15, 15));
			j+=2;
		}		
		
		//Runs through each player array and checks the rent level of each owned property.
		//Draws the appropriate number of houses or a hotel depending on this.
		for(int x=0; x<players.size(); x++){
			
			for(int y=0; y<players.get(x).getOwnedProperty().size(); y++){
				
				Graphics2D g3 = (Graphics2D) g;
				g3.setColor(Color.GREEN);
				
				if(players.get(x).getOwnedProperty().get(y).getRentLevel()>0 && players.get(x).getOwnedProperty().get(y).getRentLevel()<5){
					int pos = players.get(x).getOwnedProperty().get(y).getPosition();
					if(pos>0 && pos<10){
						double xVal=570;
						double yVal=570;
						
						g3.fill(new Rectangle2D.Double(xVal-(pos*53), yVal, 10, 10));
					}
					else if(pos>10 && pos<20){
						double xVal=70;
						double yVal=570;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal-((pos%10)*53), 10, 10));
					}
					else if(pos>20 && pos<30){
						double xVal=69;
						double yVal=71;
						
						g3.fill(new Rectangle2D.Double(xVal+((pos%20)*53), yVal, 10, 10));
					}
					else if(pos>30 && pos<40){
						double xVal=569;
						double yVal=69;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal+((pos%30)*53), 10, 10));
					}
				}
				
				if(players.get(x).getOwnedProperty().get(y).getRentLevel()>1 && players.get(x).getOwnedProperty().get(y).getRentLevel()<5){
					int pos = players.get(x).getOwnedProperty().get(y).getPosition();
					if(pos>0 && pos<10 && pos!=5){
						double xVal=581;
						double yVal=570;
						
						g3.fill(new Rectangle2D.Double(xVal-(pos*53), yVal, 10, 10));
					}
					else if(pos>10 && pos<20 && pos!=15 && pos!=12){
						double xVal=70;
						double yVal=581;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal-((pos%10)*53), 10, 10));
					}
					else if(pos>20 && pos<30 && pos!=25 && pos!=28){
						double xVal=58;
						double yVal=71;
						
						g3.fill(new Rectangle2D.Double(xVal+((pos%20)*53), yVal, 10, 10));
					}
					else if(pos>30 && pos<40 && pos!=35 ){
						double xVal=569;
						double yVal=58;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal+((pos%30)*53), 10, 10));
					}
				}
				
				if(players.get(x).getOwnedProperty().get(y).getRentLevel()>2 && players.get(x).getOwnedProperty().get(y).getRentLevel()<5){
					int pos = players.get(x).getOwnedProperty().get(y).getPosition();
					if(pos>0 && pos<10){
						double xVal=592;
						double yVal=570;
						
						g3.fill(new Rectangle2D.Double(xVal-(pos*53), yVal, 10, 10));
					}
					else if(pos>10 && pos<20){
						double xVal=70;
						double yVal=592;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal-((pos%10)*53), 10, 10));
					}
					else if(pos>20 && pos<30){
						double xVal=47;
						double yVal=71;
						
						g3.fill(new Rectangle2D.Double(xVal+((pos%20)*53), yVal, 10, 10));
					}
					else if(pos>30 && pos<40){
						double xVal=569;
						double yVal=47;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal+((pos%30)*53), 10, 10));
					}		
				}
				
				if(players.get(x).getOwnedProperty().get(y).getRentLevel()>3 && players.get(x).getOwnedProperty().get(y).getRentLevel()<5){
					int pos = players.get(x).getOwnedProperty().get(y).getPosition();
					if(pos>0 && pos<10){
						double xVal=603;
						double yVal=570;
						
						g3.fill(new Rectangle2D.Double(xVal-(pos*53), yVal, 10, 10));
					}
					else if(pos>10 && pos<20){
						double xVal=70;
						double yVal=603;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal-((pos%10)*53), 10, 10));
					}
					else if(pos>20 && pos<30){
						double xVal=36;
						double yVal=71;
						
						g3.fill(new Rectangle2D.Double(xVal+((pos%20)*53), yVal, 10, 10));
					}
					else if(pos>30 && pos<40){
						double xVal=569;
						double yVal=36;
						
						g3.fill(new Rectangle2D.Double(xVal, yVal+((pos%30)*53), 10, 10));
					}
				}
				
				Graphics2D g4 = (Graphics2D) g;
				g4.setColor(Color.RED);
				
				if(players.get(x).getOwnedProperty().get(y).getRentLevel()==5){
					int pos = players.get(x).getOwnedProperty().get(y).getPosition();
					if(pos>0 && pos<10){
						double xVal=587;
						double yVal=570;
						
						g4.fill(new Rectangle2D.Double(xVal-(pos*53)-17, yVal, 40, 10));
					}
					else if(pos>10 && pos<20){
						double xVal=70;
						double yVal=587;
						
						g4.fill(new Rectangle2D.Double(xVal, yVal-((pos%10)*53)-17, 10, 40));
					}
					else if(pos>20 && pos<30){
						double xVal=52;
						double yVal=71;
						
						g4.fill(new Rectangle2D.Double(xVal+((pos%20)*53)-12, yVal, 40, 10));
					}
					else if(pos>30 && pos<40){
						double xVal=569;
						double yVal=52;
						
						g4.fill(new Rectangle2D.Double(xVal, yVal+((pos%30)*53)-13, 10, 40));
					}
				}
			}
		}
	}
	
	//Reads in Board image and scales to appropriate size.
	public Image loadAndScale(String ImageName, int width, int height ){
			
			BufferedImage img = null;
			
			try{
				img = ImageIO.read(new File(ImageName));
			}catch (IOException e){
				e.printStackTrace();
			}
			
			Image dimg = img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
			
			return dimg;
	}	
	
	
	public void refresh(){
		revalidate();
		repaint();
		return;
	}
}

