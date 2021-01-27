package entities.enemies;

import entities.base.Entity;
import environment.Environment;

public class SideScrollEnemy extends Entity
{
	private int jumpHeight;
	private int checkLos;

	public SideScrollEnemy(Environment e, String n)
	{
		super(e, n);

		setType("Enemy");
		setBaseSpeed(5);
		setPower(1);
		setHealth(4);
		setIsEnemy(true);
		setSolid(false);

		setSprite("Bandit");

		jumpHeight = 18;
		checkLos = 0;

		startAnim();
	}

	@Override
	public void behavior()
	{	
		int randNum;
		
		if (!isOffScreen())
		{
			checkLos++;
			if (checkLos % 10 == 0)
			{
				if (myBehavior().canSee(getEnvironment().getPlayer(), 500, false) > -1)
				{
					myBehavior().moveTowardSideScroll(getEnvironment().getPlayer());
					setState("seesPlayer", true);
				}
				else
				{
					if (checkLos % 100 == 0)
					{
						randNum = (int) (Math.random() * 50);
						
						if(randNum == 0)
						{
							setXVelocity(getBaseSpeed());
							setState("seesPlayer", false);
						}
						else if(randNum == 1)
						{
							setXVelocity(-getBaseSpeed());
							setState("seesPlayer", false);
						}
							
						myBehavior().freeMovement();
						checkLos = 0;
					}
				}
				
				if(getState("seesPlayer"))
					setXVelocity(getAbsoluteXVelocity());
			}
			
			if(getState("seesPlayer"))
			{
				randNum = (int) (Math.random() * 10);
				if(myBehavior().canSee(getEnvironment().getPlayer(), 500, false) == 0 && randNum == 0)
				{
					jump();
				}
			}

			if(getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer())
			{
				getEnvironment().getPlayer().collisionEvent(this);

				myBehavior().knockBack(getEnvironment().getPlayer(), 12);

			}
			else if (hasSolidCollisionEntity(getXVelocity(), 0))
			{
				jump();
			}
			
			if(getXVelocity() != 0)
				startAnim();
			else
				getSprite().setPaused(true);	
		}
		else
		{
			setXVelocity(0);
			myBehavior().freeMovement();
		}
		
		if(getHealth() <= 0)
			delete();

		myBehavior().movementWithGravity();
	}
	
	@Override
	public void collisionEvent(Entity en)
	{
		if (en.getType().contentEquals("Projectile") || en.getType().contentEquals("Item"))
		{
			modifyHealth(-en.getPower());
			
			myBehavior().knockBack(en, 12);
		}
	}

	public void startAnim()
	{
		if(getYVelocity() == 0)
		{
			if (getXVelocity() > 0)
				setSpriteState("Right");
			if (getXVelocity() < 0)
				setSpriteState("Left");
		}
		else
		{
			if (getXVelocity() < 0)
				setSpriteState("JumpLeft");
			else
				setSpriteState("JumpRight");
		}
		

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
