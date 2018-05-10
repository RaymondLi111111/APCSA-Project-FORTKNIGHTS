package fortknights;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import javax.swing.JFrame;
import java.awt.Component;

public class FortKnights extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	public FortKnights()
	{
		super("FORTKNIGHTS");
		setSize(WIDTH,HEIGHT);
		Battleground theGame = new Battleground();
		((Component)theGame).setFocusable(true);
		getContentPane().add(theGame);
		setVisible(true);
		theGame.run();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main( String args[] )
	{
		FortKnights run = new FortKnights();
	}
}