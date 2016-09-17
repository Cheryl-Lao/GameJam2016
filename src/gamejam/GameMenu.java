package gamejam;

/**
 * @(#)Menu.java
 * Displays menu and allows user to choose to play game or view instructions
 *
 * @author 
 * @version 1.00 2015/1/8
 */

//imports stuff ye
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import java.applet.*;

public class GameMenu extends JFrame implements MouseListener{
	AudioClip peoplesing; //background song
	private int destx,desty; //coordinates of mouse location
	private Rectangle gameButton; //rectange where you click to start the game
	private Rectangle infoButton; //rectangle where you click to view instructions

	Trilobites game; //Trilobites you pass through
	//TODO Info howto; //info page of Info class

    //public GameMenu(Trilobites g, Info ht) {
	public GameMenu(Trilobites g) {
    	super("Trilobites!");
    	game = g;
    	//howto = ht;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//peoplesing = Applet.newAudioClip(getClass().getResource("do you hear the people sing cut version.au"));
    	//peoplesing.loop(); //plays this song infinitely
    	pack(); 
    	
    	Image img = Toolkit.getDefaultToolkit().getImage("pg_main menu.png"); //loads background and displays it 
    	ImageIcon background = new ImageIcon(img.getScaledInstance(619,630,Image.SCALE_SMOOTH));
    	setContentPane(new JLabel(background));
    	
    	addMouseListener(this);
    	gameButton = new Rectangle(405, 546, 166, 50);
		infoButton = new Rectangle(50, 546, 166, 50);
    	
    	setLayout(null);
    
    	setResizable(false);
    	setSize(625,658);
    	setVisible(true);
    	System.out.println(game);
    }
    // mouse listener//
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}    
    public void mouseClicked(MouseEvent e){}  
    	
    public void mousePressed(MouseEvent e){ //if mouse is pressed
		destx = e.getX(); //gets x and y coords of mouse
		desty = e.getY();	
		game.initialize(); //creates a new game in Spaceinvaders
		game.setVisible(true); //sets it visible and sets menu invisible so you don't see it
		setVisible(false);
		/**if (gameButton.contains(destx,desty)){ //if mouse pressed the gameButton button
			game.initialize(); //creates a new game in Spaceinvaders
			game.setVisible(true); //sets it visible and sets menu invisible so you don't see it
    		setVisible(false);
		}
		
		if (infoButton.contains(destx,desty)){ //if mouse pressed the info button
			//howto.setVisible(true); //sets instructions to visible
		} **/
	}	
       
    public static void main(String [] args){ //just a test nothing important...
    	GameMenu menu = new GameMenu(null, null);
    }
}  
    

    
