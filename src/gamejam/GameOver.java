
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.AudioSystem;
import java.applet.*;

public class GameOver extends JPanel implements ActionListener{
	Timer myTimer; //new timer to keep track of how long page should be visible
	Trilobites si; //Spaceinvaders to pass through
	public int finalscore; //player's score
	Image endBG; //bg pic
	GameMenu mainmenu; //main menu page
	
	public static int timer;

	public GameOver(Trilobites b, GameMenu g,int s, int t){
		super();
		myTimer = new Timer(10, this);	 // trigger every 10 ms
		myTimer.start(); //starts timer
		si=b;
		
		finalscore=s;
		mainmenu = g;
		timer = 0; //set at 0
		endBG = Toolkit.getDefaultToolkit().getImage("pg_end menu.png"); //gets the image with the Infotions on it

    	setLayout(null);
    	setVisible(true);
	}
	
	public void actionPerformed(ActionEvent evt){
		move();	//does timer stuff
		repaint(); //repaints panel	
   	}
   	public void move(){ //move controlls the increase of the timer
   		timer +=1;
   		if (timer == 200){ //once timer is 200
    		si.setVisible(false); //sets game to invisible
			mainmenu.setVisible(true); //sets menu to visible		
    	}
   	}
		
	public void paintComponent(Graphics g){
		//WRITES THE SCORE
		g.drawImage(endBG, 0, 0, this); //draws bg image
		g.setColor(new Color(215,161,0));
		g.setFont(new Font("Pristina", Font.PLAIN, 48));
		g.drawString("Your score is: ",336,246);
		g.drawString(""+finalscore,336,286);
		g.setFont(new Font("Pristina", Font.PLAIN, 28));
		g.drawString("You will be redirected in "+(200-timer)+"...", 336,346); //displays milliseconds until main menu is displayed
	}
	
	public static void main(String [] args){ //just testing...
    	GameOver gO = new GameOver(null, null, 35, 0);
    }
}