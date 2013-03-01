package com.jverkamp.brong.thing;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.*;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

/**
 * Store the world and everything in it.
 */
public class World implements KeyListener {
	public List<Thing> Things;
	public Dimension Bounds;
	boolean[] Keys = new boolean[256]; 
	
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
		
		Things.add(new Paddle(new Point2D.Double(size.width / 2, 25), Paddle.MOVE_ON_AD));
		Things.add(new Paddle(new Point2D.Double(size.width / 2, size.height - 25), Paddle.MOVE_ON_ARROWS));
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

	/**
	 * Track when a key is pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code >= 0 && code < Keys.length)
			Keys[code] = true; 
	}

	/**
	 * Track when a key is released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code >= 0 && code < Keys.length)
			Keys[code] = false;
	}

	/**
	 * Ignore when a key is typed.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
