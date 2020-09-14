package entities.characters;

import entities.base.Entity;
import environment.Environment;

public class Kitten extends Entity
{
	private int jumpHeight;
	private int direction;
	private int jumpCount;

	public Kitten(Environment e, String n)
	{
		super(e, n);

		setType("Ally");
		setBaseSpeed(3);
		setSolid(false);

		setSprite("CatBlack");

		jumpHeight = 15;

		direction = 1;
		jumpCount = 0;

		startAnim();
	}

	@Override
	public void behavior()
	{
		int randNum = (int) (Math.random() * 100);

		if (getXVelocity() == 0)
			setXVelocity(getBaseSpeed() * direction);

		if (randNum == 0)
			myBehavior().moveTowardSideScroll(getEnvironment().getPlayer());

		myBehavior().movementWithGravity();

		if (hasSolidCollisionEntity(getXVelocity(), 0))
			jump();
	}

	public void startAnim()
	{
		if (getXVelocity() > 0)
			setSpriteState("Right");
		if (getXVelocity() < 0)
			setSpriteState("Left");

		getSprite().setPaused(false);
	}

	public void jump()
	{
		if (getState("bottomSolid"))
		{
			setYVelocity(-jumpHeight);
			setState("bottomSolid", false);
			;
			jumpCount++;

			if (jumpCount > 7)
			{
				direction = direction * -1;
				jumpCount = 0;
			}

		}
	}
}
