package entities.projectiles;

import entities.base.Entity;
import environment.Environment;

public class CatMissile extends Entity
{

	public CatMissile(Environment e, String n)
	{
		super(e, n);

		setType("Projectile");
		setBaseSpeed(2);

		setSprite("CatBlack");
	}

	@Override
	public void behavior()
	{
		if (getXVelocity() > 0)
			setXVelocity(getXVelocity() + 1);
		else if (getXVelocity() < 0)
			setXVelocity(getXVelocity() - 1);

		if (getYVelocity() > 0)
			setYVelocity(getYVelocity() + 1);
		else if (getYVelocity() < 0)
			setYVelocity(getYVelocity() - 1);

		moveByVelocity(true, true);

		if (isOffScreen())
			getEnvironment().removeEntity(this);
	}

	public void setDirection(int direction)
	{
		if (direction == UP)
			setYVelocity(-2);
		if (direction == DOWN)
			setYVelocity(2);
		if (direction == LEFT)
			setXVelocity(-2);
		if (direction == RIGHT)
			setXVelocity(2);
	}
}
