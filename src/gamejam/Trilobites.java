package gamejam;

//initializes things 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.Random;

public class Trilobites extends JFrame implements ActionListener {
	Timer myTimer; //timer to keep track of how many milliseconds have passed within the game   
	GamePanel game = null; //Gamepanel for the main game, which has all the graphics and game functions
	//GameOver endGame=null; //page that shows up at the end of the game
	//Info howto=new Info(); //information page
	GameMenu gm = null; //main menu page
	public Trilobites() {
		super("Trilobites!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//TODO gm = new GameMenu(this, howto); //calls menu at the very beginning when you open the file that allows player to play/view info	
		gm = new GameMenu(this);
	}
	
	public void initialize(){ //initialize sets most of the variables needed for the frame
    	myTimer = new Timer(10, this);	 // trigger every 10 ms
    	setSize(625,658);
    	setResizable(false);
    	setLayout(null);
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	if (game == null){ //creates new game in the frame if one isn't currently running
    		game = new GamePanel(this);
    		game.setSize(625,658);
			game.setLocation(0,0);
    		add(game);
    	}
    	myTimer.start(); //starts timer
    }

	public void actionPerformed(ActionEvent evt){ 
		if (game != null){ //if there is a game going on
			game.move(); //things happen in the game
		}
		if (game != null){ //if there is a game going on
			game.repaint(); //the window is repainted with changing graphics
		}
	}
	
	/** public void gotoGameOver(int score){ //method is called after the player wins or loses, to activate the end page
		//takes in an int score from Gamepanel (because the variable score does not exist in the SpaceInvaders class)
		game.setVisible(false);
		remove(game); //removes game (game is reloaded if replayed)
		game=null;
		endGame = new GameOver(this, gm, score, 0); // calls EndGame panel
		endGame.setSize(625,658);
		endGame.setLocation(0,0);
		add(endGame);
		endGame.setVisible(true);
	} **/

    public static void main(String[] arguments) { 
    	Trilobites frame = new Trilobites();		
    }
}

class GamePanel extends JPanel implements KeyListener{
	Random rand = new Random();
	private int spotx,spoty; //(x,y) coordinates of player's location
	private boolean []keys; //keyboard keys
	private Image back; //background picture for the game
	private Trilobites mainFrame; 
	public static Image trilo1, trilo2, trilo3, food1, food2, food3, pred1, pred2, pred3, pebble, foodBubble1, foodBubble2, foodBubble3;
	//TODO can change
	public static ArrayList<Bubble> bubbles; //floating bubbles
	public static ArrayList<Bubble> poppedBubbles; //popped/passed bubbles
	public static ArrayList<Pebble> pebbles; //pebbles you throw
	public static ArrayList<DroppedFood> foodDrop; //dropped Food
	public static int movetimer; //keeps track of how many milliseconds have passed 
	public static int pebbletimer; //timer that controls when to create a new missile if you continuously press spacebar
	public static int score; //your score
	public static int level; //what level (out of 3) you are on
	public static int hP; // how full the trilobite is
	public static boolean gameOver;
	
	public GamePanel(Trilobites m){
		//constructs GamePanel with settings at beginning of game and creates new lists----------------------------------------------
		score = 0;
		level = 1;
		hP = 0;
		movetimer = 0;
		pebbletimer = 0;
		bubbles = new ArrayList<Bubble>();
		poppedBubbles = new ArrayList<Bubble>();
		pebbles = new ArrayList<Pebble>();
		foodDrop = new ArrayList<DroppedFood>();
		
		//============================================================================================================================
		
		keys = new boolean[KeyEvent.KEY_LAST+1];
		
		// UPLOADS ALL IMAGES ========================================================================================================
		back = new ImageIcon("images/layout_game screen real smaller.png").getImage();
		foodBubble1 = new ImageIcon("images/bubble_small.jpg").getImage();
		food1 = new ImageIcon("images/icon_gunbaguette1.png").getImage();
		trilo1 = new ImageIcon("images/anomalocaris_small.jpg").getImage();
		pebble = new ImageIcon("images/bullet.png").getImage(); //TODO change to actual pebble
		/**javert = new ImageIcon("small_ch_javert.png").getImage();
		soldier1 = new ImageIcon("small_ch_soldier1.png").getImage(); //grey 
		soldier2 = new ImageIcon("small_ch_soldier2.png").getImage(); //red
		soldier3 = new ImageIcon("small_ch_soldier3.png").getImage(); //green
		soldier12 = new ImageIcon("small_ch_soldier1 2.png").getImage(); //grey 2nd frame
		soldier22 = new ImageIcon("small_ch_soldier2 2.png").getImage(); //red 2nd frame
		soldier32 = new ImageIcon("small_ch_soldier3 2.png").getImage(); //green 2nd frame
		bullet = new ImageIcon("bullet.png").getImage(); //bullet image soldiers fire
		marius = new ImageIcon("small_ch_marius.png").getImage();
		baguette = new ImageIcon("icon_gunbaguette1.png").getImage();
		blood1 = new ImageIcon("icon_bloodsplatter.png").getImage(); //blood variation 1
		blood2 = new ImageIcon("icon_bloodsplatter2.png").getImage(); //blood variation 2
		nextlevel = new ImageIcon("icon_nextlevel.png").getImage(); //banner displaying "next level" shown after you advance levels
		finished = new ImageIcon("icon_fin.png").getImage(); // banner displaying "fin" displayed after the game is done
		lifebaguette = new ImageIcon("icon_lifebaguette smallest.png").getImage(); //baguettes representing lives at the top bar
		
		**/
		mainFrame = m;	
	    spotx = 700;
        spoty = 533;
		setSize(625,658);
        addKeyListener(this);
        //loadBubbles(); //loads up new soldiers for the aliveSoldiers list
        gameOver = false;
	}
	
	public void loadBubbles(){
		int bX = 0; //x coordinate 
		int bY = rand.nextInt(270); //y coordinate
		Image imag = foodBubble1;
		Image fP = food1;
		int predOrPrey = rand.nextInt(2); //choose whether to generate pred or prey
		/**if (predOrPrey == 0){ //create predator
			int imagNumber = rand.nextInt(3);
			
		}
		
		else if (predOrPrey == 1){ //create plant
			
		}**/
		
		bubbles.add(new Bubble(bX, bY, "food", foodBubble1, food1)); //TODO change to variables
	
	}
	
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    public void bubbleMove(){ //movement of soldiers
    	/**Random randomGenerator = new Random(); //allows to get random numbers
    	boolean advanced = false;
    	if (movetimer%(30-(deadcount/5))==0){ //soldiers move every 30 milliseconds and gets faster the more soldiers you kill
    		direction = moveSideways(direction); //direction becomes what direction the soldiers go after moving sideways (it may change
    												//in this method
    		displayedsol= 3 - displayedsol; //switches up the soldier pic used (to create animated effect)
    	}	
    	if (movetimer%150 == 0){ //every 150 ms enemies fire a bullet
    		if (firingSoldiers.size()>=1){ //allows a random soldier from the first row to fire
    			Soldiers firing = firingSoldiers.get(randomGenerator.nextInt(firingSoldiers.size()));
    			smissiles.add(new SMissile(firing.soldierx, firing.soldiery+42)); //creates new enemy missile at the enemy's coordinates
    		}
    	}**/	
    	
    	//TODO parabolic motion
    	
    	if (movetimer%11 == 0){
    		for (int i = 0; i<bubbles.size(); i++){
    			
    			bubbles.get(i).bubX+=2; //all soldiers move across 7 px to right every time
        	}
    		
    	}
    }
    
    
   
    
    public void pop(Bubble popped){ //takes in dead soldiers and removes/adds it to lists
    	poppedBubbles.add(popped); 
    	bubbles.remove(popped);	
    	
    	//create DroppedFood 
    	foodDrop.add(new DroppedFood(food1, popped.bubX+ 20, popped.bubY + 20));
		
		
		//gives points based on what colour soldiers where
		/**if (dead.type.equals(soldier3)){ 
			score+=10;
			System.out.println("+"+10+" points!");
		}
		if (dead.type.equals(soldier2)){
			score+=40;
			System.out.println("+"+40+" points!");
		}
		if (dead.type.equals(soldier1)){
			score+=100;
			System.out.println("+"+100+" points!");
		}	**/
    	
    	//TODO pop animation
    }
    //next level----------------------------------------
    public void restart(){ //called when something occurs that ended the level
    	if (gameOver == false && level<3){ //if you are not dead and level is less than 3, you advance to next level
    		//all these settings are reset========================================================================================
    		level+=1;
	    	pebbles.clear();
	    	foodDrop.clear();
	    	hP = 0;
	    	movetimer = 0; //sets movetimer to 0 so that soldiers dont move forward right when you start the game
	    	//nextLevel(); //displays next level banner //TODO nextlevel
	    	//loadBubbles(); //soldiers are reloaded
	    	
    	}
    	
    	/**else{ //the game is over either you died or you finished all 3 levels
    		fin(); //calls fin method that leads to gameover screen
    	}**/
    }
    
    public boolean checkLevel(){ //checks if something occured that advances the game to the next level or ends it
    	//if (aliveSoldiers.size()==0 || life == 0){ // if you kill all soldiers or if you're dead
    	
    	if (gameOver == true){
    		return true; //yes something hapend
    	}
    	return false;
    }
    
    /**public void nextLevel(){ //just displays next level banner
    	Graphics g = getGraphics();
    	g.drawImage(nextlevel,0,0, this);
    	delay(2000); //waits 2000 millisecs
    }
    public void fin(){ //displays end game banner
    	//TODO gameOver boolean change
    	Graphics g = getGraphics();
    	g.drawImage(finished,0,0, this);
    	delay(2000); //waits 2000 millisecs
    	mainFrame.gotoGameOver(score); //calls gotogameover in Spaceinvaders class
    }**/
    
    public static void delay (long len){ //delays game for set amount of milliseconds as inputted in parameter
    	try{
    		Thread.sleep (len);
    	}
    	catch (InterruptedException ex){
    		System.out.println("lmao");
    	}
    }
   	//----------------------------------------------------------
	
	public void move(){ //stuff that happens every millisecond
		Random randomGenerator = new Random();
		spotx = Math.max(0,spotx); //the max and min x coordinates at which your trilobite can travel
    	spotx = Math.min(getWidth()-32, spotx);
		if(keys[KeyEvent.VK_RIGHT] ){ //move right when right key is pressed
			spotx += 5;
		}
		if(keys[KeyEvent.VK_LEFT] ){ //move left when left key is pressed
			spotx -= 5;
		}
		if(keys[KeyEvent.VK_SPACE] ){ //fire pebbles when you press space
			if (pebbletimer%50== 0 ){ //when you have space bar continuously pressed you can only fire one pebble every 50 ms
				pebbles.add(new Pebble(spotx, spoty)); //creates new missile at your (x,y), adds to list 
			}
			pebbletimer+=1; //to make sure you can't fire pebbles continuously if you press spacebar continuously
		}
		else{
			pebbletimer = 0; //resets pebbletimer at 0 so that when space is pressed a pebble is fired immediately
		}
		
		bubbleMove(); //moves bubbles
		
		
		for (int i = 0; i < foodDrop.size(); i ++){
			foodDrop.get(i).dfY+=3;
		}
		
		
		if (movetimer % 100 == 0){ //every __ ms a new bubble is created
			loadBubbles();
		}
	
		movetimer+=1; 
		for (int m = 0; m < pebbles.size();m++){ //if there are pebbles flying on screen
			pebbles.get(m).posy-=3; //increases y coordinate of your missiles by 5
		}
		
		//kill soldiers ------------------------------------------------------------------
		ArrayList<Bubble> fallenBubbles= new ArrayList<Bubble>(); //soldiers to be removed after they are hit
		ArrayList<Pebble> sand= new ArrayList<Pebble>(); //pebbles to be removed if they hit something
		for (Bubble bub : bubbles){ //for every bubble
			if (pebbles.size()>=1){ //if there are pebbles flying on screen
				for (Pebble peb:pebbles){ //checks for every missile flying
				
					//if the pebble touches it, that bubble will be placed into fallenBubbles to die and 
					//pebble will be placed in sand list to be removed
					if (peb.posx>=bub.bubX && peb.posx<=bub.bubX+28
					&& peb.posy>=bub.bubY && peb.posy<=bub.bubY+42){
						fallenBubbles.add(bub);
						sand.add(peb);
					}
					if (peb.posy<=40){ 
						sand.add(peb); //removes pebble as it goes out of bounds
					}
				}
			
			}	
			if (bub.bubX>625) { //if bubble moves all the way across screen
				fallenBubbles.add(bub); //it will be removed
			}
				
		}

		pebbles.removeAll(sand); //removes all sand from pebbles
		for (Bubble b : fallenBubbles){
			pop(b); //every bubble in fallenBubbles go through pop method
		}
		
		ArrayList<DroppedFood> crumbs = new ArrayList<DroppedFood>(); //list of bullets to be removed
		if (foodDrop.size()>=1){ //checks location of every missile on board
			for (DroppedFood foo:foodDrop){
				if (foo.dfX>=spotx && foo.dfX<=spotx+32 //TODO dimensions of trilobite
					&& foo.dfY>=spoty && foo.dfY<=spoty+63){
						//if you catch the food, you gain one health point
						//TODO health point
						hP += 1;
						crumbs.add(foo);
						System.out.println(hP);
				}
				
				if (foo.dfX>=658){ //removes food if it goes out of bounds
					crumbs.add(foo); 
				}
			}
		}
		foodDrop.removeAll(crumbs); //removes food from the list of food on screen
		


		Point mouse = MouseInfo.getPointerInfo().getLocation(); //location of your mouse on screen
		if (checkLevel()){ //checks if something happened to advance levels or end game
			//does restart method if true is returned
			restart();
		}
		
		//TODO fin method 
		
		/**for (int p = 0; p < firingSoldiers.size(); p++){// if the soldiers advance past furniture
    		if (firingSoldiers.get(p).soldiery > 451){
    			fin(); //fin is automatically called to display end screen
    		}
    	}**/	
	}
	//does key stuff like what you pressed============================================================================================
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    //================================================================================================================================
    public void paintComponent(Graphics g){ //draws stuff on screen
    	g.drawImage(back,0,0,this); //background
    
    	for (int i = 0; i<bubbles.size(); i++){ //draws all bubbles soldiers
    		g.drawImage(bubbles.get(i).bubPic, bubbles.get(i).bubX, bubbles.get(i).bubY, this);
    	}
    	
    	for (int i = 0; i<pebbles.size();i++){ //draws all pebbles
    		g.drawImage(pebble,pebbles.get(i).posx, pebbles.get(i).posy,this);
    	}
    	
    	for (int i = 0; i<foodDrop.size();i++){ //draws dropped food 
    		g.drawImage(foodDrop.get(i).foodPic,foodDrop.get(i).dfX, foodDrop.get(i).dfY,this);
    	}
    	
    	g.drawImage(trilo1,spotx,spoty,this); //draws your marius
    	
    	//displays score
    	g.setColor(new Color(74,18,18));
    	g.setFont(new Font("Old English Text MT",Font.PLAIN, 24));
    	g.drawString(""+score, 111,26);
    }
}
