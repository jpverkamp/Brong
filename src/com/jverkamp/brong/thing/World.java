package com.jverkamp.brong.thing;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.*;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

/**
 * Store the world and everything in it.
 */
public class World {
	public List<Thing> Things;
	public Dimension Bounds;
	
	org.jbox2d.dynamics.World j2dWorld;
	
	/**
	 * Create a new world.
	 * @param size The world's size.
	 */
	public World(Dimension size) {
		j2dWorld = new org.jbox2d.dynamics.World(new Vec2(0, 0), false);
		
		BodyDef bd = new BodyDef();
        bd.position.set(0, 0);
        
        Body body = j2dWorld.createBody(bd);
        
        PolygonShape edge = new PolygonShape();
        
        edge.setAsEdge(new Vec2(0, 0), new Vec2(size.width, 0));
        body.createFixture(edge, 0);
        
        edge.setAsEdge(new Vec2(0, 0), new Vec2(0, size.height));
        body.createFixture(edge, 0);
        
        edge.setAsEdge(new Vec2(size.width, 0), new Vec2(size.width, size.height));
        body.createFixture(edge, 0);
        
        edge.setAsEdge(new Vec2(0, size.height), new Vec2(size.width, size.height));
        body.createFixture(edge, 0);
		
		Things = new ArrayList<Thing>();
		Bounds = size;
		
		Things.add(new Ball(this, new Point(size.width / 2, size.height / 2)));
		
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			Things.add(new Ball(this, new Point((int) (r.nextDouble() * size.width), (int) (r.nextDouble() * size.height))));
		
		Things.add(new Paddle(this, 25, Paddle.MovementMode.AD));
		Things.add(new Paddle(this, size.height - 25, Paddle.MovementMode.Arrows));
	}
	
	/**
	 * Update this world
	 * @param time Seconds since last update
	 */
	public void update(float time) {
		j2dWorld.step(time, 6, 2);
	}	
	
	/**
	 * Draw this ball.
	 * @param g2d Draw with me.
	 */
	public void draw(Graphics2D g2d) {
		for (Thing t : Things) 
			t.draw(g2d);
	}
}
