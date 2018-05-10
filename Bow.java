package fortknights;

import java.awt.Graphics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Bow extends MovingThing{
	
	private int speed;
	private int firerate;
	private Image image;
	
	public Bow() {
		this(0,0,0);
	}
	public Bow(int x, int y) {
		this(x,y,0);
	}
	public Bow(int x, int y, int s) {
		super(x,y);
		speed = s;
		try
		{
			image = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fortknights\\crossbow.png"));
		}
		catch(Exception e)
		{
			System.out.println("420 Glaze it!");
		}
	}
	public void setSpeed(int s) {
		speed = s;
	}
	public int getSpeed() {
		return speed;
	}
	public void draw(Graphics window) {
		window.drawImage(image,getX(),getY(),160,100,null);
	}
	public void setfirerate(int f) {
		firerate = f;
	}
	public int getfirerate() {
		return firerate;
	}
}
