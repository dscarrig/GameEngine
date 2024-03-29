package entities.items;

import entities.base.Entity;
import entities.players.Player;
import environment.Environment;

public class HeartPickup extends Entity
{
	private Player player;

	public HeartPickup(Environment e, String n)
	{
		super(e, n);

		setName("HeartPickup");
		setType("Pickup");
		setSolid(false);

		setSprite("HealthHearts");
	}

	@Override
	public void behavior()
	{
		// Behavior for player picking up item
		if (getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer())
		{
			player = (Player) getEnvironment().getPlayer();
			player.collisionEvent(this);
		}
		
		myBehavior().bounce(3);
	}

}
