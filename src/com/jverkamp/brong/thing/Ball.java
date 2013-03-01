package com.jverkamp.brong.thing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Bouncy bouncy.
 */
public class Ball extends Thing {
	Point2D.Double Velocity;
	int Radius = 10;
	
	/**
	 * Create a new ball at the given point.
	 * @param center The center of the ball.
	 */
	public Ball(Point2D.Double center) {
		super(center);
		Velocity = new Point2D.Double(100.0, 45.0);
	}

	/**
	 * Update the ball
	 * @param time The amount of time that has passed. 
	 */
	@Override
	public void update(World world, double time) {
		Center.x += Velocity.x * time;
		Center.y += Velocity.y * time;
		
		// Edge collision
		if (Center.x - Radius < 0 || Center.x + Radius >= world.Bounds.width) {
			Velocity.x *= -1;
			Center.x += Velocity.x * time;
		}
		
		if (Center.y - Radius < 0 || Center.y + Radius >= world.Bounds.height) {
			Velocity.y *= -1;
			Center.y += Velocity.y * time;
		}
	}
	
	/**
	 * Draw this ball.
	 * @param g2d Draw with me.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GRAY);
		g2d.fillOval(
			(int) (Center.x - Radius),
			(int) (Center.y - Radius),
			Radius * 2,
			Radius * 2
		);
	}	
}
