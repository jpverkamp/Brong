package com.jverkamp.brong.thing;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.*;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Store the world and everything in it.
 */
public class World implements ContactListener {
	public List<Thing> Things;
	public Dimension Bounds;
	
	org.jbox2d.dynamics.World j2dWorld;
	
	/**
	 * Create a new world.
	 * @param size The world's size.
	 */
	public World(Dimension size) {
		// Create the world
		j2dWorld = new org.jbox2d.dynamics.World(new Vec2(0, 0), false);
		j2dWorld.clearForces();
		
		// Create the border
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
		
        // Add custom collision handling
        j2dWorld.setContactListener(this);
        
        // Create the level
		Things = new ArrayList<Thing>();
		Bounds = size;
		
		for (int i = 0; i < 10; i++)
			Things.add(
				new Block(this, 
					new Point(
						(int) (Thing.Random.nextDouble() * size.width), 
						(int) (Thing.Random.nextDouble() * size.height)
					)
				)
			);
		
		Things.add(new PaddleWithBall(this, 25, Paddle.MovementMode.AD));
		Things.add(new PaddleWithBall(this, size.height - 25, Paddle.MovementMode.Arrows));
	}
	
	/**
	 * Update this world
	 * @param time Seconds since last update
	 */
	public void update(float time) {
		j2dWorld.step(time, 1, 1);
	}	
	
	/**
	 * Draw this ball.
	 * @param g2d Draw with me.
	 */
	public void draw(Graphics2D g2d) {
		for (Thing t : Things) 
			t.draw(g2d);
	}

	@Override
	public void endContact(Contact contact) {
		Block block = null;
		Thing other = null;
		
		if (contact.getFixtureA().getBody().getUserData() instanceof Block) {
			block = (Block) contact.getFixtureA().getBody().getUserData();
			other = (Thing) contact.getFixtureB().getBody().getUserData();
		} else if (contact.getFixtureB().getBody().getUserData() instanceof Block) {
			block = (Block) contact.getFixtureB().getBody().getUserData();
			other = (Thing) contact.getFixtureA().getBody().getUserData();
		}
		
		if (block == null) 
			return;
		
		System.out.println(block + " was hit by " + (other == null ? "???" : other + " moving at " + other.j2dBody.getLinearVelocity()));
		
		Things.remove(block);
		j2dWorld.destroyBody(block.j2dBody);
	}

	@Override public void beginContact(Contact contact) {}
	@Override public void postSolve(Contact contact, ContactImpulse impulse) {}
	@Override public void preSolve(Contact contact, Manifold manifold) {}
}
