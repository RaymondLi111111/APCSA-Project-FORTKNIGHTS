package fortknights;

import java.awt.Graphics;
import java.awt.Color;

public class HealthBar extends Bar {
	private Color color;
	public HealthBar() {
		super();
	}
	public HealthBar(int x, int y, int width, int height, double percentfull, int s, Color c) {
		super(x,y,width,height,percentfull,s);
		color = c;
	}
	@Override
	public void draw(Graphics window) {
		window.setColor(Color.black);
		window.drawRect(getX(), getY(), getWidth(), getHeight());
		window.setColor(Color.GREEN);
		window.fillRect(getX(), getY(), (int) (getPercentFull()*getWidth()), getHeight());	
	}
}
