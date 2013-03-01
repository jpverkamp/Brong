package com.jverkamp.brong.thing;

import java.awt.Point;

import org.jbox2d.common.Vec2;

/**
 * Paddle that creates a ball for itself.
 */
public class PaddleWithBall extends Paddle {
	/**
	 * Create a paddle as normal than add a ball.
	 * @param world The world to put the items in.
	 * @param y The height of the paddle.
	 * @param mode The control scheme for the paddle.
	 */
	public PaddleWithBall(World world, int y, MovementMode mode) {
		super(world, y, mode);
		
		int ballY = y;
		if (y > world.Bounds.height / 2)
			ballY -= SIZE.height * 2;
		else
			ballY += SIZE.height * 2;
		
		Ball myBall = new Ball(world, new Point(world.Bounds.width / 2, ballY));
		myBall.j2dBody.setLinearVelocity(new Vec2(Random.nextFloat() * 100 - 50, 10.0f * (ballY - y)));
		world.Things.add(myBall);
	}
}
