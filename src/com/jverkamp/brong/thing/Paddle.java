package com.jverkamp.brong.thing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.joints.*;

/**
 * Create a new paddle.
 */
public class Paddle extends Thing implements KeyEventDispatcher {
	public final static float IMPULSE = 10000.0f;
	public final static Dimension SIZE = new Dimension(50, 10);
	
	public enum MovementMode {
		Arrows,
		AD
	};
	MovementMode Mode;
	
	Point2D.Double Velocity;
	
	/**
	 * Create a new paddle.
	 * @param world The world the paddle will be added to.
	 * @param y The height of the paddle (starts centered on the x)
	 * @param mode The keys that will move this paddle
	 */
	public Paddle(World world, int y, MovementMode mode) {
		Mode = mode;
		
		// Respond to the keyboard
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
		
		// Add the physics body
		BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(world.Bounds.width / 2, y);
        
        FixtureDef fd = new FixtureDef();
        fd.shape = new PolygonShape();
        ((PolygonShape) fd.shape).setAsBox(SIZE.width / 2, SIZE.height / 2);
        fd.density = 0.1f;
        fd.friction = 0.0f;        
        fd.restitution = 1.0f;
        
        j2dBody = world.j2dWorld.createBody(bd);
        j2dBody.createFixture(fd);
        j2dBody.setUserData(this);
        
        // Limit it to only x movement
        BodyDef gbd = new BodyDef();
		gbd.type = BodyType.STATIC;
		bd.position.set(world.Bounds.width / 2, y);
        
		PrismaticJointDef pjd = new PrismaticJointDef();
		pjd.collideConnected = true;
		pjd.initialize(j2dBody, world.j2dWorld.createBody(gbd), j2dBody.getWorldCenter(), new Vec2(1.0f, 0.0f));
		world.j2dWorld.createJoint(pjd);
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(
			(int) (j2dBody.getPosition().x - SIZE.width / 2), 
			(int) (j2dBody.getPosition().y - SIZE.height / 2),
			SIZE.width,
			SIZE.height
		);
	}

	/**
	 * Deal with key events.
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// Unpack the id and code once.
		int id = event.getID();
		int code = event.getKeyCode();
		
		// Skip events we don't care about.
		if (!(id == KeyEvent.KEY_PRESSED || id == KeyEvent.KEY_RELEASED)) 
			return true;
		
		// Are we moving left?
		boolean left = false;
		left |= (Mode == MovementMode.AD && code == KeyEvent.VK_A);
		left |= (Mode == MovementMode.Arrows && code == KeyEvent.VK_LEFT);
		
		// Are we moving right?
		boolean right = false;
		right |= (Mode == MovementMode.AD && code == KeyEvent.VK_D);
		right |= (Mode == MovementMode.Arrows && code == KeyEvent.VK_RIGHT);
		
		// Are we starting to move or stopping?
		boolean pressed = (id == KeyEvent.KEY_PRESSED);
		
		// Apply the velocity.
		if (!pressed) {
			j2dBody.setLinearVelocity(new Vec2(0, 0));
		} else if (left) {
			j2dBody.setLinearVelocity(new Vec2(-IMPULSE, 0));
//			j2dBody.applyLinearImpulse(new Vec2(-IMPULSE, 0), j2dBody.getPosition());
		} else if (right) {
			j2dBody.setLinearVelocity(new Vec2(IMPULSE, 0));
//			j2dBody.applyLinearImpulse(new Vec2(IMPULSE, 0), j2dBody.getPosition());
		}
		
		// Done.
		return false;
	}
}
