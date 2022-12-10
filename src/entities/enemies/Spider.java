package entities.enemies;

import entities.base.Entity;
import environment.Environment;

public class Spider extends Entity
{

	public Spider(Environment e, String n)
	{
		super(e, n);

		setType("Enemy");
		setBaseSpeed(3);
		setPower(1);
		setHealth(3);
		setIsEnemy(true);
		setSolid(false);

		setSprite("Bandit");
	}

	@Override
	public void behavior()
	{
		// TODO Auto-generated method stub
		
	}

}
