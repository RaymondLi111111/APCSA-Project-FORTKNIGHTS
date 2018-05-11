package fortknights;

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

public class WinScreen{
	
	private boolean start;
	private Image image;
	public WinScreen() {
	}
	
	public void paint(Graphics window) {
		try{
			image = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\fortknights\\win.png"));
		}
		catch (Exception e) {
			System.out.println("420 blaze it!");
		}
		window.drawImage(image, 0, 0, 800, 560, null);
		
	}
	public boolean getStart() {
		return start;
	}
	public void setStart(boolean b) {
		start = b;
	}
}

