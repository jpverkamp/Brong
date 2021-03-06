package com.jverkamp.brong.thing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

/**
 * Bouncy bouncy.
 */
public class Ball extends Thing {
	Body Body;
	float Radius = 5.0f;
	
	/**
	 * Create a new ball at the given point.
	 * @param center The center of the ball.
	 */
	public Ball(World world, Point center) {
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set((float) center.x, (float) center.y);
        bd.linearDamping = 0.0f;
        bd.angularDamping = 0.01f;
        
        FixtureDef fd = new FixtureDef();
        fd.shape = new CircleShape();
        fd.shape.m_radius = Radius;
        fd.density = 100.0f;
        fd.friction = 0.0f;        
        fd.restitution = 1.0f;
        
        j2dBody = world.j2dWorld.createBody(bd);
        j2dBody.createFixture(fd);
        j2dBody.setUserData(this);
        
        j2dBody.setLinearVelocity(new Vec2(Random.nextFloat() * 1000 - 500, Random.nextFloat() * 1000 - 500));
	}

	/**
	 * Draw this ball.
	 * @param g2d Draw with me.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GRAY);
		g2d.fillOval(
			(int) (j2dBody.getPosition().x - Radius),
			(int) (j2dBody.getPosition().y - Radius),
			(int) (Radius * 2),
			(int) (Radius * 2)
		);
	}
}
