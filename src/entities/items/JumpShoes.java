package entities.items;

import entities.base.Entity;
import entities.players.SideScrollPlayer;
import environment.Environment;

// The Zap Gun item. Shoots plasma balls
public class JumpShoes extends Entity
{

	private SideScrollPlayer player;

	public JumpShoes(Environment e, String n)
	{
		super(e, n);

		setName("JumpShoes");
		setType("Power");
		setSolid(false);

		setSprite("jumpshoes");
	}

	@Override
	public void behavior()
	{
		// Behavior for player picking up item
		if (getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer())
		{
			player = (SideScrollPlayer) getEnvironment().getPlayer();
			player.collisionEvent(this);
			getEnvironment().addItem(this);
			delete();
		}
		
		myBehavior().moveToGround();
	}


}
