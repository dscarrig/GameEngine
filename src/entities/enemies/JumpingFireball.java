package entities.enemies;

import entities.base.Entity;
import environment.Environment;

public class JumpingFireball extends Entity
{
	private int jumpHeight;

	public JumpingFireball(Environment e, String n)
	{
		super(e, n);

		setType("Enemy");
		setBaseSpeed(5);
		setPower(2);
		setIsEnemy(true);
		setSolid(false);

		setSprite("fireball");

		setSpriteState("Down");

		jumpHeight = 40;
	}

	@Override
	public void behavior()
	{
		if (atScreenEdge(getSpriteWidth()) == DOWN)
		{
			setYVelocity(-jumpHeight);
			setSpriteState("Up");
		}

		if (getYVelocity() == 1)
		{
			setSpriteState("Down");
		}

		if (getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer())
		{
			getEnvironment().getPlayer().collisionEvent(this);
		}

		myBehavior().movementWithGravityNoCollision();
	}

}
