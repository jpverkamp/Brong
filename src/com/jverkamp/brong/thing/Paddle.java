package com.jverkamp.brong.thing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Create a new paddle.
 */
public class Paddle extends Thing {
	public final static double FRICTION = 0.9; // 1.0 is no friction, 0 is maximum
	public final static double ACCELERATION = 10.0;
	public final static double MAX_VELOCITY = 100.0;
	public final static Dimension SIZE = new Dimension(50, 10);
	
	public final static int MOVE_ON_ARROWS = 0;
	public final static int MOVE_ON_AD = 1;
	int MovementMode;
	
	Point2D.Double Velocity;
	
	/**
	 * Create a new paddle.
	 * @param center The center point of the paddle. Y will never change.
	 * @param movementMode Move on arrows or A/D.
	 */
	public Paddle(Point2D.Double center, int movementMode) {
		MovementMode = movementMode;
		Velocity = new Point2D.Double(0, 0);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
//		g2d.fillRect(
//			(int) (Center.x - SIZE.width / 2), 
//			(int) (Center.y - SIZE.height / 2), 
//			SIZE.width,
//			SIZE.height
//		);
	}
}
