package fortknights;

import java.awt.Graphics;

public class Button extends Bar{

	public Button() {
		super();
	}
	@Override
	public void draw(Graphics window) {
		window.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
