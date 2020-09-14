package entities.blocks;

import entities.base.Entity;
import environment.Environment;

public class Block extends Entity
{
	public Block(Environment e, String n)
	{
		super(e, n);

		setType("Block");

		setSprite("mWall");

		setSolid(true);
	}

	@Override
	public void behavior()
	{
		setBlockImage();
		setHasBehavior(false);
	}

	public void setBlockImage()
	{
		if (getEnvironment().getMapTheme().equals("Grass"))
		{
			if (hasSolidCollisionEntity(0, -getSprite().getFrameHeight() / 2))
			{
				if (getCollisionEntity(0, -getSprite().getFrameHeight() / 2, true).getType().equals("Block"))
					setSprite("dirt");
				else
					setSprite("grass");
			} else
				setSprite("grass");
		} else if (getEnvironment().getMapTheme().equals("Jiggly"))
			setSprite("jiggly");
		else if (getEnvironment().getMapTheme().equals("cloud"))
			setSprite("cloudblock");
		else
			setSprite("mWall");
	}
}
