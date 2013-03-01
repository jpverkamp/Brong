package com.jverkamp.brong;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import com.jverkamp.brong.thing.World;

/**
 * Main panel for the game.
 */
public class PongPanel extends JPanel {
	private static final long serialVersionUID = -7729856330212355685L;

	public final static int WIDTH = 400;
	public final static int HEIGHT = 600;
	public final static int TARGET_FPS = 60;
	public final static double TARGET_TIME = 1.0 / TARGET_FPS;
	
	Image Buffer;
	Graphics2D BufferGraphics;
	
	World Small;
	int Frames;
	double FPS;
	
	/**
	 * Create a default sized panel.
	 */
	public PongPanel() {
		setSize(WIDTH, HEIGHT);
		setMinimumSize(getSize());
		setMaximumSize(getSize());
		setPreferredSize(getSize());

		Small = new World(getSize()); // afterall
		
		final long start = System.currentTimeMillis();
		final PongPanel me = this;
		Thread t = new Thread(new Runnable() {
			public void run() {
				long then = System.currentTimeMillis();
				while (true) {
					long now = System.currentTimeMillis();
					double time = 1.0 * (now - then) / 1000.0;
					
					Frames += 1;
					Small.update(time);
					me.repaint(); 
					
					FPS = (1000.0 * Frames / (now - start));
					then = now;
					
					try {
						Thread.sleep((int) (1000 * TARGET_TIME - time));
					} catch (InterruptedException e) {
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
	}
	
	/**
	 * Override paint method
	 * @param g Draw with this
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		if (Buffer == null) {
			Buffer = createImage(WIDTH, HEIGHT);
			BufferGraphics = (Graphics2D) Buffer.getGraphics();
		}
		
		BufferGraphics.setColor(Color.WHITE);
		BufferGraphics.clearRect(0, 0, WIDTH, HEIGHT);
		
		BufferGraphics.setColor(Color.BLACK);
		BufferGraphics.drawString("FPS: " + FPS, 20, 20);
		
		Small.draw(BufferGraphics);
		
		g2d.drawImage(Buffer, 0, 0, this);
	}
}
