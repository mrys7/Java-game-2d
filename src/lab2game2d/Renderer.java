package lab2game2d;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Renderer extends JPanel
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Lab2game2d.lab2game2d.repaint(g);
	}
	
}
