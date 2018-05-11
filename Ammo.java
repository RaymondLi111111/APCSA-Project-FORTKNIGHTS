package fortknights;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Ammo extends MovingThing{
	private double hspeed;
	private double vspeed = 0;
	private double subx;
	private double suby;
	private Image image;
	private int damage;
	
	public Ammo() {
		this(0,0,0,50);
	}
	public Ammo(int x, int y) {
		this(x,y,0,50);
	}
	public Ammo(int x, int y, double hs, int d) {
		super(x,y);
		hspeed = hs;
		damage = d;
		try
		{
			image = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fortknights\\Cannonball.png"));
		}
		catch(Exception e)
		{
		}
	}
	public void setSpeed(double hs, double vs) {
		hspeed = hs;
		vspeed = vs;
	}
	public double gethSpeed() {
		return hspeed;
	}
	public double getvSpeed() {
		return vspeed;
	}
	public void draw(Graphics window) {
		window.drawImage(image,getX(),getY(),20,20,null);
	}
	public boolean didCollide(Object obj) {
		MovingThing p = (MovingThing) obj;
		if (getY() + 20 > p.getY() && getY() < p.getY() && getX() < p.getX()+25 && getX() + 20 > p.getX()+25) {
			return true;
		}
		return false;
	}
	public void move() {
		this.setPos((int) (getX()-hspeed+0.5), (int) (getY()+vspeed-0.5));
	}
	public void accelerate() {
		vspeed += 0.1;
	}
	@Override
	public void setSpeed(int s) {
	}
	@Override
	public int getSpeed() {
		return 0;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int d) {
		damage = d;
	}
}
