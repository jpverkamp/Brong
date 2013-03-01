import javax.swing.JFrame;

/**
 * Main interface as a JFrame
 */
public class Frame extends JFrame {
	private static final long serialVersionUID = 3846013496073409L;

	/**
	 * Run from the command line.
	 * @param args Parameters.
	 */
	public static void main(String[] args) {
		new Frame();
	}
	
	/**
	 * Create the Brong window.
	 */
	public Frame() {
		add(new PongPanel());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setTitle("Brong");
		setVisible(true);
	}
}
