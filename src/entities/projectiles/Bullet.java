package entities.projectiles;

import entities.base.Entity;
import environment.Environment;

public class Bullet extends Entity
{
	public Bullet(Environment e, String n)
	{
		super(e, n);

		setType("Projectile");
		setBaseSpeed(4);
		setPower(2);

		setSprite("plasma");
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
		
		if(getCollisionEntity(getXVelocity(), getYVelocity(), false).isEnemy() && getType().contentEquals("Projectile"))
		{
			getCollisionEntity(getXVelocity(), getYVelocity(), false).collisionEvent(this);
			delete();
		}
		
		if(getCollisionEntity(getXVelocity(), getYVelocity(), false).isPlayer() && getType().contentEquals("Enemy"))
		{
			getCollisionEntity(getXVelocity(), getYVelocity(), false).collisionEvent(this);
			delete();
		}
		
		if(this.hasSolidCollisionEntity(getXVelocity(), getYVelocity()) && !(getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer()))
		{
			delete();
		}
		
	}

	public void setDirection(int direction)
	{
		if (direction == UP)
			setYVelocity(-4);
		if (direction == DOWN)
			setYVelocity(4);
		if (direction == LEFT)
			setXVelocity(-4);
		if (direction == RIGHT)
			setXVelocity(4);
	}
}
