package entities.projectiles;

import entities.base.Entity;
import environment.Environment;

public class LightningBolt extends Entity
{
	public LightningBolt(Environment e, String n)
	{
		super(e, n);

		setType("Enemy");
		setBaseSpeed(16);
		setPower(2);

		setSprite("lightningbolt");
		getSprite().randomize(true);
		getSprite().setPaused(false);
	}

	@Override
	public void behavior()
	{
		setYVelocity(getBaseSpeed());
		
		moveByVelocity(true, true);

		if (isOffScreen())
		{
			getEnvironment().removeEntity(this);
		}
		
		if(getCollisionEntity(getXVelocity(), getYVelocity(), false).isPlayer())
		{
			getCollisionEntity(getXVelocity(), getYVelocity(), false).collisionEvent(this);
			delete();
		}
		
		if(this.hasSolidCollisionEntity(getXVelocity(), getYVelocity()) && !(getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer()))
		{
			delete();
		}
		
	}
}
