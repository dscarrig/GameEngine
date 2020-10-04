package entities.enemies;

import entities.base.Entity;
import entities.projectiles.Bullet;
import environment.Environment;

public class ShootyGuy extends Entity
{
	private int jumpHeight;
	private int checkLos;

	public ShootyGuy(Environment e, String n)
	{
		super(e, n);

		setType("Enemy");
		setBaseSpeed(3);
		setPower(1);
		setHealth(5);
		setIsEnemy(true);
		setSolid(false);

		setSprite("PinkBandit");
		setState("MovingLeft", true);

		jumpHeight = 10;
		checkLos = 0;

		startAnim();
	}

	@Override
	public void behavior()
	{
		if (!isOffScreen())
		{
			int randNum = (int) (Math.random() * 100);

			if (getState("MovingLeft"))
				setXVelocity(-getBaseSpeed());

			if (getState("MovingRight"))
				setXVelocity(getBaseSpeed());

			if (!getState("MovingLeft") && !getState("MovingRight"))
			{
				setXVelocity(0);
				getSprite().setPaused(true);
			}

			if (hasSolidCollisionEntity(getXVelocity(), 0) || myBehavior().atBlockEdge())
			{
				if (getState("MovingLeft"))
				{
					setState("MovingLeft", false);
					setState("MovingRight", true);
				} else
				{
					setState("MovingLeft", true);
					setState("MovingRight", false);
				}
			}

			if (randNum == 0)
			{
				if (getState("MovingLeft"))
				{
					setState("MovingLeft", false);
					setState("MovingRight", true);
				} else
				{
					setState("MovingLeft", true);
					setState("MovingRight", false);
				}
			}

			if (checkLos == 0)
			{
				if (!getState("MovingLeft") && !getState("MovingRight"))
				{
					if (getState("PrevMoveRight"))
					{
						setState("MovingRight", true);
						setState("PrevMoveRight", false);
					}
					if (getState("PrevMoveLeft"))
					{
						setState("MovingLeft", true);
						setState("PrevMoveLeft", false);
					}
				}

				if (myBehavior().canSee(getEnvironment().getPlayer(), 500, false) == LEFT && getState("MovingLeft"))
				{
					setState("MovingLeft", false);
					setState("MovingRight", false);
					setState("PrevMoveLeft", true);
					setSpriteState("Left");
					shootMissile(LEFT);
				}

				if (myBehavior().canSee(getEnvironment().getPlayer(), 500, false) == RIGHT && getState("MovingRight"))
				{
					setState("MovingLeft", false);
					setState("MovingRight", false);
					setState("PrevMoveRight", true);
					setSpriteState("Right");
					shootMissile(RIGHT);
				}

				checkLos = 25;
			} else
				checkLos--;

			if (getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer())
			{
				if(getEnvironment().getPlayer().getXVelocity() > 0)
					myBehavior().knockBack(this, 12, 1);
				else
					myBehavior().knockBack(this, 12, -1);
				
				getEnvironment().getPlayer().collisionEvent(this);
			}

			if (getState("MovingLeft") || getState("MovingRight"))
				startAnim();
		}

		if (getHealth() == 0)
			delete();

		myBehavior().movementWithGravity();
	}

	public void shootMissile(int direction)
	{
		Bullet newMissile = new Bullet(getEnvironment(), "");

		newMissile.setType("Enemy");

		if (direction == LEFT)
		{
			newMissile.setDirection(LEFT);
			newMissile.setCoordinates(getXPos() - 12, getYPos());
		} else
		{
			newMissile.setDirection(RIGHT);
			newMissile.setCoordinates(getXPos() + 12, getYPos());
		}

		getEnvironment().addEntity(newMissile);
	}

	@Override
	public void collisionEvent(Entity en)
	{
		if (en.getType().contentEquals("Projectile") || en.getType().contentEquals("Item"))
		{
			modifyHealth(-en.getPower());
			
			if(getXVelocity() > 0)
				myBehavior().knockBack(this, 12, 1);
			else
				myBehavior().knockBack(this, 12, -1);
		}
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

			if (getXVelocity() > 0)
				setSpriteState("JumpRight");
			if (getXVelocity() < 0)
				setSpriteState("JumpLeft");
		}
	}
}
