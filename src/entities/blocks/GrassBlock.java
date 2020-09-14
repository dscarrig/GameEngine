package entities.blocks;

import entities.base.Entity;
import environment.Environment;


public class GrassBlock extends Entity
{
	public GrassBlock(Environment e, String n)
	{
		super(e, n);
		
		setType("Block");
		setSprite("grass");
		setSolid(true);
		setHasBehavior(false);
	}

	@Override
	public void behavior()
	{
		
	}
}
