package fortknights;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.awt.Color;
import javax.imageio.ImageIO;


public class Knight extends MovingThing
	{
		private int speed;
		private Image image;
		private HealthBar healthbar;
		private int hp;
		public int maxhp;
		
		public Knight()
		{
			this(0,0,0,0,0);
		}

		public Knight(int x, int y)
		{
			this(x,y,0,0,0);
		}

		public Knight(int x, int y, int s, int h, int maxh)
		{
			super(x, y);
			speed=s;
			healthbar = new HealthBar(getX()+15,getY()-10,50,10,1.0,s,Color.green);
			hp = h;
			maxhp = maxh;
			try
			{
				image = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fortknights\\metaknight.png"));
			}
			catch(Exception e)
			{
			}
		}

		public void setSpeed(int s)
		{
		   speed = s;
		}

		public int getSpeed()
		{
		   return speed;
		}

		public void draw( Graphics window )
		{
			window.drawImage(image,getX(),getY(),80,50,null);
			healthbar.draw(window);
		}
		public boolean didCollide(Object obj) {
			MovingThing p = (MovingThing) obj;
			if (getY() + 80 > p.getY() && getY() < p.getY() && getX()+25 < p.getX() && getX() + 80 > p.getX()) {
				return true;
			}
			return false;
		}
		public void move(String direction) {
			super.move(direction);
			healthbar.move(direction);
		}
		public String toString()
		{
			return super.toString() + " Speed: " + speed;
		}
		public int getHP() {
			return hp;
		}
		public void setHP(int h) {
			hp = h;
			healthbar.setPercentFull((getHP()*1.0)/maxhp);
		}
		public void die() {
			setPos(-10000,10000); 
			setSpeed(0);
			healthbar.setPos(10000, 10000);
			healthbar.setSpeed(0);
		}
}

