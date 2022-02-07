package entities.items;

import entities.base.Entity;
import entities.players.Player;
import environment.Environment;

// The Sword item for slashing
public class Sword extends Entity
{
	private int slashDelay;
	private boolean activatePressed;

	private Player player;

	public Sword(Environment e, String n)
	{
		super(e, n);

		setName("Sword");
		setType("Item");
		setSolid(false);
		setPower(2);

		setSprite("sword");
		getSprite().setPaused(true);

		// Shot delay timer variables
		slashDelay = 0;

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

			if (!getState("slashing"))
			{
				setYPos(player.getYPos() + 20);

				if (player.getXVelocity() == 0)
				{
					if (player.getPrevXDirection() == LEFT)
					{
						setXPos(player.getXPos() - 15);
					}
					else
					{
						setXPos(player.getXPos() + 30);
					}
				} else
				{
					setXPos(player.getXPos());
				}

				setSpriteState("PointedUp");
				getSprite().setPaused(true);
				getSprite().setColumn(0);
			} else
			{
				setYPos(player.getYPos() + 30);

				if (player.getPrevXDirection() == LEFT)
				{
					if (getSprite().atRow("RightSlash"))
					{
						setSpriteStateTerminal("LeftSlash");
					}

					setXPos(player.getXPos() - 30);
				} else
				{
					if (getSprite().atRow("LeftSlash"))
					{
						setSpriteStateTerminal("RightSlash");
					}

					setXPos(player.getXPos() + 50);
				}
			}

			if (getState("activate"))
			{
				activate();
				setState("activate", false);
			}
		} else
			myBehavior().moveToGround();

		if (getCollisionEntity(0, 0, false).isEnemy() && getState("slashing"))
		{
			getCollisionEntity(getXVelocity(), getYVelocity(), false).collisionEvent(this);
			setState("slashing", false);
		}

		if (slashDelay > 0)
		{
			slashDelay--;
		} else
		{
			setState("slashing", false);
		}

		if (getState("release"))
		{
			activatePressed = false;
			setState("release", false);
		}
	}

	public void activate()
	{
		if (!activatePressed && slashDelay == 0)
		{
			slashDelay = 10;
			slash();
			setState("slashing", true);
			getSprite().setPaused(false);
			activatePressed = true;
		}
	}

	public void slash()
	{
		if (player.getPrevXDirection() == LEFT)
		{
			setXPos(player.getXPos() - 50);
			setSpriteStateTerminal("LeftSlash");
		} else
		{
			setXPos(player.getXPos() + 50);
			setSpriteStateTerminal("RightSlash");
		}
	}

}
