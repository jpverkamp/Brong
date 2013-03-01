import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Main panel for the game.
 */
public class PongPanel extends JPanel {
	private static final long serialVersionUID = -7729856330212355685L;

	/**
	 * Create a default sized panel.
	 */
	public PongPanel() {
		setSize(400, 600);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		setPreferredSize(getSize());
	}
	
	/**
	 * Override paint method
	 * @param g Draw with this
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.PINK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
}
