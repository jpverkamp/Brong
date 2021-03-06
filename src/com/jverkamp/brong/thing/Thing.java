package com.jverkamp.brong.thing;

import java.awt.Graphics2D;
import java.util.Random;

import org.jbox2d.dynamics.Body;

/**
 * Base for all drawable, movable things.
 */
public abstract class Thing {
	Body j2dBody;
	static Random Random = new Random();
	
	/**
	 * Draw this thing with the given graphics object.
	 * @param g2d Draw with me!
	 */
	public abstract void draw(Graphics2D g2d);
}
