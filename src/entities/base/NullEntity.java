package entities.base;

import environment.Environment;

public class NullEntity extends Entity
{

	public NullEntity(Environment e, String n)
	{
		super(e, n);
		
		setType("Blank");
	}

	@Override
	public void behavior()
	{
		
	}

}
