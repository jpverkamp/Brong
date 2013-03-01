package com.jverkamp.brong.thing;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Base for all drawable, movable things.
 */
public abstract class Thing {
	Point2D.Double Center;
	
	/**
	 * Create a new thing.
	 * @param center Put it here.
	 */
	public Thing(Point2D.Double center) {
		Center = center;
	}
	
	/**
	 * Update this thing.
	 * @param time The number of seconds that have passed since the last update.
	 */
	public abstract void update(World world, double time);
	
	/**
	 * Draw this thing with the given graphics object.
	 * @param g2d Draw with me!
	 */
	public abstract void draw(Graphics2D g2d);
}
