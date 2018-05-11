package fortknights;

//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.Scanner;
import java.awt.event.MouseListener;

public class Battleground extends Canvas implements KeyListener, Runnable
{
	private boolean[] keys;
	private BufferedImage back;
	private Knight arthur;
	private Bow bow;
	private Fort fort;
	private ArrayList<Ammo> arrows;
	private int reload;
	private ArrayList<Knight> knights;
	private Image image;
	private ArrayList<Long> timePressed;
	private double power;
	private long timeReleased;
	private int level;
	private int score;
	private int money;
	private int damage;
	private int health;
	private boolean title = true;
	private TitleScreen titlescreen;
	private LoseScreen losescreen;
	private WinScreen winscreen;
	
	public Battleground()
	{
		titlescreen = new TitleScreen();
		losescreen = new LoseScreen();
		winscreen = new WinScreen();
		setBackground(Color.black);
		keys = new boolean[5];
		bow = new Bow(600,200,0);
		bow.setfirerate(100);
		arrows = new ArrayList<Ammo>();
		knights = new ArrayList<Knight>();
		timePressed = new ArrayList<Long>();
		level = 0;
		score = 0;
		reload = 0;
		money = 1000;
		damage = 50;
		health = 100;
		fort = new Fort(health);
		for (int i = 0; i < 10; i++) {
			knights.add(new Knight(-100*i,350,1,100,100));
		}
		try{
			image = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fortknights\\background.jpg"));
		}
		catch(Exception e){
		}
		this.addKeyListener(this);

		new Thread(this).start();

		setVisible(true);
	}

 public void update(Graphics window)
 {
	   paint(window);
 }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		if (title) {
			titlescreen.paint(graphToBack);
			if (keys[4]) {
				title = false;
			}
			twoDGraph.drawImage(back, null, 0, 0);
		}
		else if (fort.getHealth() <= 0) {
			losescreen.paint(graphToBack);
			twoDGraph.drawImage(back,null,0,0);
		}
		else if (level >= 5) {
			winscreen.paint(graphToBack);
			twoDGraph.drawImage(back, null, 0, 0);
		}
		else {
		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("FortKnights ", 25, 50 );
		if (keys[1] && money >= damage*10) {
			money -= damage*10;
			damage += 20;
			keys[1] = false;
		}
		if (keys[2] && money >= health*5) {
			money -= health*5;
			health += 20;
			fort.setMaxHealth(health);
			fort.setHealth(fort.getHealth()+20);
			keys[2] = false;
		}
		if (keys[3] && money >= 100000/bow.getfirerate()) {
			money -= 100000/bow.getfirerate();
			bow.setfirerate((int) (bow.getfirerate()/1.1));
			keys[3] = false;
		}
		if (keys[4] && reload < 0) {
			fort.updatePowerBar(1.0*(System.currentTimeMillis()-timePressed.get(0))*0.005/10);
		}
		reload -= 1;
		for (Ammo a: arrows) {
			a.move();
			a.accelerate();
		}
		for (Knight k: knights) {
			if (reload % 2 == 0) {
				k.move("RIGHT");
			}
		}

		for (Knight k: knights) {
			for (Ammo a: arrows) {
				if (k.didCollide(a) || a.didCollide(k)) {
					double actualdamage = damage*Math.pow(Math.pow(a.gethSpeed(),2)+Math.pow(a.getvSpeed(), 2)/Math.pow(a.getvSpeed(), 2), 0.17);
					k.setHP(k.getHP()-(int)actualdamage);
					a.setPos(-10000, -10000);
					a.setSpeed(0);
				}
			}
		}

		for (Knight k: knights) {
			if (k.getHP() <= 0) {
				k.die();
				score += k.maxhp;
				money += k.maxhp;
			}
		}
		
		for (Knight k: knights) {
			if (k.getX() > 800) {
				k.die();
				fort.setHealth(fort.getHealth()-20);
			}
		}
		
		ArrayList<Knight> newKnights = new ArrayList<Knight>();
		for (int i = 0; i < knights.size(); i++) {
			if (knights.get(i).getX() <= 800 && knights.get(i).getY() >= 0 && knights.get(i).getY() <= 600) {
				newKnights.add(knights.get(i));
			}
		}
		knights = new ArrayList<Knight>();
		for (Knight k: newKnights) {
			knights.add(k);
		}
		graphToBack.drawImage(image,0,0,800,600,null);
		fort.draw(graphToBack);
		bow.draw(graphToBack);
		for (Ammo a: arrows) {
			a.draw(graphToBack);
		}
		for (Knight k: knights) {
			k.draw(graphToBack);
		}
		graphToBack.setColor(Color.black);
		graphToBack.drawString("Level: " + level, 30, 30);
		graphToBack.drawString("Score: " + score, 230, 30);
		graphToBack.drawString("Money: " + money, 430, 30);
		graphToBack.drawString("Damage: " + damage, 30, 80);
		graphToBack.drawString("Max Health: " + health, 230, 80);
		graphToBack.drawString("Firerate: " + bow.getfirerate(), 430, 80);
		twoDGraph.drawImage(back,null,0,0);
		if (knights.size() == 0 && fort.getHealth() > 0) {
			level += 1;
			newLevel(level);
		}
		}
	}

	public void newLevel(int level) {
		for (int i = 0; i < 10+level*5; i++) {
			knights.add(new Knight(-(100-10*level)*i,350,1,100+20*level,100+20*level));
		}
		fort.setHealth(fort.getMaxHealth());
		
	}
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_1)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_2)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_3)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && reload <= 0)
		{	
			timePressed.add(System.currentTimeMillis());
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_1)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_2)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_3)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
			if (reload < 0) {
				timeReleased = System.currentTimeMillis();
				if (timePressed.size() == 0) {
					power = 0;
				}
				else if ((timeReleased-timePressed.get(0)) * 0.005 > 10) {
					power = 10;
				}
				else {
					power = (timeReleased-timePressed.get(0)) * 0.005;
				}
				arrows.add(new Ammo(bow.getX(),bow.getY()+50,power,50));
				reload = bow.getfirerate();
			}
			timePressed = new ArrayList<Long>();
			fort.updatePowerBar(0);
			
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{

	}
 public void run()
 {
 	try
 	{
 		while(true)
 		{
 		   Thread.currentThread().sleep(10);
          repaint();
       }
    }catch(Exception e)
    {
    }
 }
}

