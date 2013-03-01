package com.jverkamp.brong.thing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

/**
 * Break these.
 */
public class Block extends Thing {
	public static final Dimension SIZE = new Dimension(25, 10);
	public static final Color[] COLORS = new Color[]{
		Color.RED,
		Color.ORANGE,
		Color.YELLOW,
		Color.GREEN,
		Color.BLUE,
	};
	
	Color BoxColor;
	Body Body;
	
	/**
	 * Create a new block at the given point.
	 * @param center The center of the block.
	 */
	public Block(World world, Point center) {
		// Give me a color.
		BoxColor = COLORS[Random.nextInt(COLORS.length)];
		
		// Set up the physics.
        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set((float) center.x, (float) center.y);
        
        FixtureDef fd = new FixtureDef();
        fd.shape = new PolygonShape();
        ((PolygonShape) fd.shape).setAsBox(SIZE.width / 2, SIZE.height / 2);
        fd.density = 0.0f;
        fd.friction = 0.0f;        
        fd.restitution = 1.0f;
        
        j2dBody = world.j2dWorld.createBody(bd);
        j2dBody.createFixture(fd);
        j2dBody.setUserData(this);
	}

	/**
	 * Draw this ball.
	 * @param g2d Draw with me.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(BoxColor);
		g2d.fillRect(
			(int) (j2dBody.getPosition().x - SIZE.width),
			(int) (j2dBody.getPosition().y - SIZE.height),
			SIZE.width,
			SIZE.height
		);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawRect(
			(int) (j2dBody.getPosition().x - SIZE.width),
			(int) (j2dBody.getPosition().y - SIZE.height),
			SIZE.width,
			SIZE.height
		);
	}
}
