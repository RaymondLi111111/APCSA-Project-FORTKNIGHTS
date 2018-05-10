package fortknights;

import java.awt.Color;
import java.awt.Graphics;

public class PowerBar extends Bar{
	
	public PowerBar() {
		super();
	}
	public PowerBar(int x, int y, int width, int height, double percentfull, int s) {
		super(x,y,width,height,percentfull,s);
	}
	@Override
	public void draw(Graphics window) {
		window.setColor(Color.black);
		window.drawRect(getX(), getY(), getWidth(), getHeight());
		window.setColor(Color.BLUE);
		if (getPercentFull() > 1) {
			window.fillRect(getX(), getY(), getWidth(), getHeight());
		}
		else {
		window.fillRect(getX(), getY(), (int) (getPercentFull()*getWidth()), getHeight());	
		}
	}

}
