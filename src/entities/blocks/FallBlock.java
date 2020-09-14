package entities.blocks;

import entities.base.Entity;
import environment.Environment;

public class FallBlock extends Entity
{
	private int fallDelay;

	public FallBlock(Environment e, String n)
	{
		super(e, n);

		setType("Block");
		
		if(e.getMapTheme().contentEquals("Grass"))
			setSprite("grass");
		else if(e.getMapTheme().contentEquals("Jiggly"))
			setSprite("jiggly");
		else if (getEnvironment().getMapTheme().equals("cloud"))
			setSprite("cloudblock");
		else
			setSprite("mWall");
		
		setSolid(true);

		fallDelay = 0;
	}

	@Override
	public void behavior()
	{
		if (getCollisionEntity(0, -1, false).getType().equals("Player"))
		{
			setState("startFall", true);
		}

		if (getState("startFall"))
			fallDelay++;

		if (fallDelay > 10)
		{
			setState("startFall", false);
			setState("falling", true);
		} else if (getState("startFall"))
		{
			if (getState("shakeLeft"))
			{
				setXPos(getXPos() - 2);
				setState("shakeLeft", false);
			} else
			{
				setXPos(getXPos() + 2);
				setState("shakeLeft", true);
			}
		}

		if (getState("falling"))
			myBehavior().movementWithGravity();
		
		if (getCollisionEntity(0, getYVelocity(), false).getYVelocity() == 0)
		{
			getCollisionEntity(0, getYVelocity(), false).setHealth(0);
		}
	}
}