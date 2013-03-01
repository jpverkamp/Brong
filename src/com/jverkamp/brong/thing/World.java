package com.jverkamp.brong.thing;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.*;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Store the world and everything in it.
 */
public class World extends Thing {
	public List<Thing> Things;
	public Dimension Bounds;
	
	/**
	 * Create a new world.
	 * @param size The world's size.
	 */
	public World(Dimension size) {
		super(new Point2D.Double(size.width / 2, size.height / 2));
		
		Things = new ArrayList<Thing>();
		Bounds = size;
		
		Things.add(new Ball(Center));
	}
	
	/**
	 * Update this world
	 * @param time Seconds since last update
	 */
	public void update(double time) {
		for (Thing t : Things) 
			t.update(this, time);
	}	
	
	/**
	 * Update the world
	 * @param time The amount of time that has passed. 
	 */
	@Override
	public void update(World world, double time) {
		throw new NotImplementedException();
	}
	
	/**
	 * Draw this ball.
	 * @param g2d Draw with me.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		for (Thing t : Things) 
			t.draw(g2d);
	}
}
