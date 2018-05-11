package fortknights;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Fort {
	
	private int health;
	private int maxHealth;
	private Image image;
	private HealthBar healthbar;
	private PowerBar powerbar;
	
	public Fort() {
		this(100);
	}
	public Fort(int h) {
		health = h;
		maxHealth = h;
		healthbar = new HealthBar(625,100,100,20,1,0,Color.GREEN);
		powerbar = new PowerBar(625,120,100,20,0,0);
		try
		{
			image = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fortknights\\tower.png"));
		}
		catch(Exception e)
		{
		}
	}
	public void draw( Graphics window )
	{
		window.drawImage(image,600,150,200,300,null);
		healthbar.draw(window);
		powerbar.draw(window);
	}
	public void setHealth(int h) {
		health = h;
		healthbar.setPercentFull((1.0*health)/maxHealth);
	}
	public int getHealth() {
		return health;
	}
	public void setMaxHealth(int mh) {
		maxHealth = mh;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void updatePowerBar(double p) {
		powerbar.setPercentFull(p);
	}
	
}
