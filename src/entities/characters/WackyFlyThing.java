package entities.characters;

import entities.base.Entity;
import environment.Environment;

public class WackyFlyThing extends Entity
{

	public WackyFlyThing(Environment e, String n)
	{
		super(e, n);

		setType("WackyFlyThing");
	}

	@Override
	public void behavior()
	{

		if (getXVelocity() != 0)
		{
			if (getXVelocity() < 0)
				modifyXVelocity(-1);
			else
				modifyXVelocity(1);

		} else
		{
			if (getYVelocity() < 0)
				modifyYVelocity(-1);
			else
				modifyYVelocity(1);
		}

		moveByVelocity(true, true);
	}

}
