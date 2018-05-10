package fortknights;

import java.awt.Graphics;
import java.awt.Color;

public abstract class Bar extends MovingThing{
	private double percentfull;
	private int width;
	private int height;
	private int speed;
	public Bar() {
		this(0,0,0,0,1,0);
	}
	public Bar(int x, int y, int w, int h, double p, int s) {
		super(x,y);
		width = w;
		height = h;
		speed = s;
		percentfull = p;
	}
	@Override
	public void setSpeed(int s) {
		speed = s;
		
	}
	@Override
	public int getSpeed() {
		return speed;
	}
	public void setPercentFull(double p) {
		percentfull = p;
	}
	public double getPercentFull() {
		return percentfull;
	}
	public void setWidth(int w) {
		width = w;
	}
	public int getWidth() {
		return width;
	}
	public void setHeight(int h) {
		height = h;
	}
	public int getHeight() {
		return height;
	}
	@Override
	public abstract void draw(Graphics window);
}
