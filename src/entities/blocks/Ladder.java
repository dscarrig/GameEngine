package entities.blocks;

import entities.base.Entity;
import environment.Environment;

public class Ladder extends Entity
{
	public Ladder(Environment e, String n)
	{
		super(e, n);
		
		setType("Ladder");
		setSprite("Ladder");
		setSolid(false);
	}

	@Override
	public void behavior()
	{
		
	}
}
