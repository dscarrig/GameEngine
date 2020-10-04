package entities.enemies;

import entities.base.Entity;
import entities.projectiles.LightningBolt;
import environment.Environment;

public class ThunderCloud extends Entity
{
	int turnTimer;
	int attackTimer;

	public ThunderCloud(Environment e, String n)
	{
		super(e, n);

		setType("Enemy");
		setBaseSpeed(3);
		setPower(1);
		setHealth(4);
		setIsEnemy(true);
		setSolid(false);

		setSprite("thundercloud");

		turnTimer = 0;
		attackTimer = 0;

		startAnim();
	}

	@Override
	public void behavior()
	{
		if (!isOffScreen())
		{
			if (!getState("Attacking"))
			{
				if (turnTimer == 0 || hasSolidCollisionEntity(getXVelocity(), 0))
				{
					turnTimer = (int) (Math.random() * 200) + 100;

					if (getState("MovingRight"))
					{
						setXVelocity(-getBaseSpeed());
						setState("MovingRight", false);
					} else
					{
						setXVelocity(getBaseSpeed());
						setState("MovingRight", true);
					}
				} else
					turnTimer--;

				if (myBehavior().canSee(getEnvironment().getPlayer(), 500, true) == 1)
				{
					attack();
				}
			} else
			{
				turnTimer = 0;

				if (attackTimer == 0)
					setState("Attacking", false);
				else
					attackTimer--;
			}

			startAnim();
			myBehavior().freeMovement();

		}

		if (getHealth() <= 0)
			delete();

	}

	@Override
	public void collisionEvent(Entity en)
	{
		if (en.getType().contentEquals("Projectile") || en.getType().contentEquals("Item"))
		{
			modifyHealth(-en.getPower());
		}
	}

	public void startAnim()
	{
		if (getXVelocity() > 0)
			setSpriteState("Right");
		if (getXVelocity() < 0)
			setSpriteState("Left");
		if (getXVelocity() == 0)
			setSpriteState("Attack");

		getSprite().setPaused(false);
		getSprite().randomize(true);
	}

	public void attack()
	{
		if (!getState("Attacking"))
		{
			setXVelocity(0);
			setSpriteState("Attack");
			setState("Attacking", true);
			attackTimer = 30;

			LightningBolt newBolt = new LightningBolt(getEnvironment(), "");

			newBolt.setCoordinates(getXPos() + 20, getYPos());

			getEnvironment().addEntity(newBolt);
		}
	}

}
