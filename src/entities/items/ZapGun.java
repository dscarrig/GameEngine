package entities.items;

import entities.base.Entity;
import entities.players.Player;
import entities.projectiles.Bullet;
import environment.Environment;

// The Zap Gun item. Shoots plasma balls
public class ZapGun extends Entity
{
	private int maxMissileDelay;
	private int curMissileDelay;
	private boolean activatePressed;

	private Player player;

	public ZapGun(Environment e, String n)
	{
		super(e, n);

		setName("ZapGun");
		setType("Item");
		setSolid(false);

		setSprite("zapper");

		// Shot delay timer variables
		maxMissileDelay = 20;
		curMissileDelay = 0;

		activatePressed = false;
	}

	@Override
	public void behavior()
	{
		// Behavior for player picking up item
		if (getCollisionEntity(getXVelocity(), getYVelocity(), true).isPlayer() && !getState("carrying")
				&& !getState("dropping"))
		{
			player = (Player) getEnvironment().getPlayer();
			player.collisionEvent(this);
			getEnvironment().addItem(this);
			setState("carrying", true);
			getSprite().setPaused(false);
			moveToFront();
		}

		if (getState("carrying"))
		{
			setYPos(player.getYPos() + 60);

			if (player.getXVelocity() < 0)
			{
				setXPos(player.getXPos());
				setSpriteState("Left");
			} else if (player.getXVelocity() > 0)
			{
				setXPos(player.getXPos() + 10);
				setSpriteState("Right");
			}

			if (getState("activate"))
			{
				activate();
				setState("activate", false);
			}
		} else
			myBehavior().moveToGround();

		if (curMissileDelay > 0)
			curMissileDelay--;

		if (getState("release"))
		{
			activatePressed = false;
			setState("release", false);
		}
	}

	public void activate()
	{
		if (!activatePressed && curMissileDelay == 0)
		{
			curMissileDelay = maxMissileDelay;
			shootMissile();
			setState("missilePressed", true);
			activatePressed = true;
		}
	}

	public void shootMissile()
	{
		Bullet newMissile = new Bullet(getEnvironment(), "");

		if (player.getPrevXDirection() == LEFT)
		{
			newMissile.setDirection(LEFT);
			newMissile.setCoordinates(player.getXPos() - 12, player.getYPos() + 48);
		} else
		{
			newMissile.setDirection(RIGHT);
			newMissile.setCoordinates(player.getXPos() + 12, player.getYPos() + 48);
		}

		getEnvironment().addEntity(newMissile);
	}

}
