package entities.players;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.base.Entity;
import environment.Environment;
import overlayInterface.HealthHeart;

public class Player extends Entity
{
	private static final int MAX_HEALTH = 40;

	private int jumpHeight;
	private int invincibility;
	private int numHearts;
	private int deathDelay;
	private int prevXDirection;
	private int maxHealth;
	private boolean hasItem;

	private HealthHeart[] lifeArray = new HealthHeart[MAX_HEALTH]; // Array of the health heart components
	private ArrayList<String> items = new ArrayList<String>(); // List of the names of all picked up items
	private Entity carriedItem; // The currently carried item

	public Player(Environment e, String n)
	{
		super(e, n);

		setType("Player");

		setIsPlayer(true);
		setSolid(true);

		numHearts = 0;
		deathDelay = 0;
		invincibility = 0;
		prevXDirection = 0;
		hasItem = false;
	}

	@Override
	public void behavior()
	{
		// Behavior for climbing ladders
		if (getState("onLadder"))
		{
			if (!getCollisionEntity(getXVelocity(), getYVelocity(), false).getType().equals("Ladder"))
			{
				setState("climbingUp", false);
				setState("climbingDown", false);
				setState("onLadder", false);
			} else if (getState("climbingUp"))
			{
				setYVelocity(-5);
			} else if (getState("climbingDown"))
			{
				setYVelocity(5);
			}
		}

		if (!isOffScreen())
			myBehavior().movementWithGravity();

		// Temporary invincibility timer after being hit
		if (invincibility > 0)
			invincibility--;

		// Death behavior
		if (getHealth() == 0)
		{
			setState("immobile", true);
			setXVelocity(0);

			if (deathDelay == 0)
			{
				deathDelay = 1;
				setSpriteStateTerminal("Death");
			}
		}

		// Transitions maps if at screen edge
		if (atScreenEdge(getSpriteWidth()) != -1)
		{
			getEnvironment().transitionMap(atScreenEdge(getSpriteWidth()));
		}

		// Death delay for final dying animation
		if (deathDelay == 0)
			setCurrentSprite();
		else
			deathDelay++;

		// Removes player after delay finishes
		if (deathDelay == 50)
		{
			delete();
		}

		if (getState("fallingOffScreen") && isOffScreen())
			setHealth(0);
	}

	// Sets current character sprite depending on behavior
	public void setCurrentSprite()
	{
		if (getYVelocity() == 0)
		{
			if (invincibility == 0)
			{
				if (getXVelocity() == 0)
					setSpriteState("Idle");
				else if (getXVelocity() > 0)
					setSpriteState("Right");
				else
					setSpriteState("Left");
			}
		} else
		{
			if (getXVelocity() < 0)
				setSpriteState("JumpLeft");
			else
				setSpriteState("JumpRight");
		}
	}
	
	@Override
	public void collisionEvent(Entity en)
	{
		// Event for getting hit by an enemy
		if (en.getType().contentEquals("Enemy") && deathDelay == 0)
		{
			modifyHealth(-en.getPower());

			if (getHealth() < 0)
				setHealth(0);

			myBehavior().knockBack(en, 12);

			if (getXVelocity() > 0)
				setSpriteState("PainRight");
			else
				setSpriteState("PainLeft");
		}

		// Event for collecting an item
		if (en.getType().contentEquals("Item"))
		{
			hasItem = true;
			carriedItem = en;
			items.add(en.getName());
		}

		if (en.getType().contentEquals("Power"))
		{
			items.add(en.getName());

			if (en.getName().contentEquals("JumpShoes"))
				setJumpHeight(getJumpHeight() + 4);
		}

		if (en.getName().contentEquals("HeartPickup"))
		{
			modifyHealth(2);
			en.delete();
		}
	}
	
	public void jump()
	{
		// Jumps if on solid ground or on a ladder
		if (getState("bottomSolid") || getState("onLadder"))
		{
			setYVelocity(-jumpHeight);
			
			if(getXVelocity() > 0)
				setTempXVelocity(4);
			
			if(getXVelocity() < 0)
				setTempXVelocity(-4);
			
			setState("bottomSolid", false);
			setState("onLadder", false);
			setState("climbingUp", false);
			setState("climbingDown", false);
		}
	}

	public void setJumpHeight(int jh)
	{
		jumpHeight = jh;
	}

	public int getJumpHeight()
	{
		return jumpHeight;
	}

	public int getPrevXDirection()
	{
		return prevXDirection;
	}
	
	public ArrayList<String> getItems()
	{
		return items;
	}
	
	@Override
	public void setHealth(int h)
	{
		super.setHealth(h);

		updateHearts(true);
	}
	
	public void setMaxHealth(int h)
	{
		maxHealth = h;
	}

	// Modifies health up or down from given h int
	@Override
	public void modifyHealth(int h)
	{
		if (h < 0)
		{
			if (invincibility == 0)
			{
				super.modifyHealth(h);
				updateHearts(false);
				invincibility = 50;
			}
		} else
		{
			if (h + getHealth() > maxHealth)
				h = maxHealth - getHealth();

			super.modifyHealth(h);
			updateHearts(true);
		}
	}

	// Updates the health hearts to correct state after modifying health
	public void updateHearts(boolean positive)
	{
		if (numHearts == 0)
		{
			for (int i = 0; i < maxHealth / 2; i++)
			{
				if (lifeArray[i] == null)
				{
					createNewHeartAt(i);
				}
			}
		} else if (positive)
		{
			for (int i = numHearts * 2 - 1; i >= 0; i--)
			{
				if (getHealth() - 1 == i)
				{
					lifeArray[i / 2].setHeartState(i % 2 + 1);

					for (int j = 0; j < i / 2; j++)
						lifeArray[j].setHeartState(2);

					break;
				}
			}
		} else
		{
			for (int i = 0; i < numHearts * 2; i++)
			{
				if (getHealth() == i)
				{
					lifeArray[i / 2].setHeartState(i % 2);

					for (int j = i / 2 + 1; j < numHearts; j++)
						lifeArray[j].setHeartState(0);

					break;
				}
			}
		}
	}

	// Adds a new health heart
	public void createNewHeartAt(int heartIndex)
	{
		HealthHeart newHeart = new HealthHeart(getEnvironment().getOverlay(), "Heart_" + heartIndex);

		newHeart.setHeartIndex(heartIndex);
		lifeArray[heartIndex] = (HealthHeart) newHeart;
		numHearts++;
	}

	public void keyPressed(int key)
	{
		if (!getState("immobile"))
		{
			// A and D for left and right, W and S for climbing

			if (key == KeyEvent.VK_A)
			{
				if (getState("running"))
					setXVelocity(-getBaseSpeed() - 4);
				else
					setXVelocity(-getBaseSpeed());

				prevXDirection = LEFT;
			}

			if (key == KeyEvent.VK_D)
			{
				if (getState("running"))
					setXVelocity(getBaseSpeed() + 4);
				else
					setXVelocity(getBaseSpeed());

				prevXDirection = RIGHT;
			}

			if (key == KeyEvent.VK_W)
			{
				if (getCollisionEntity(getXVelocity(), getXVelocity(), false).getType().equals("Ladder"))
				{
					setState("climbingDown", false);
					setState("climbingUp", true);
					setState("onLadder", true);
					setYVelocity(-5);
				}
			}

			if (key == KeyEvent.VK_S)
			{
				if (getCollisionEntity(getXVelocity(), getXVelocity(), false).getType().equals("Ladder"))
				{
					setState("climbingUp", false);
					setState("climbingDown", true);
					setState("onLadder", true);
					setYVelocity(5);
				}
			}

			// The Right key activates a held item
			if (key == KeyEvent.VK_RIGHT)
			{
				if (hasItem)
				{
					carriedItem.setState("activate", true);
				}
			}

			// Up key triggers a jump
			if (key == KeyEvent.VK_UP)
			{
				if (!getState("jumpPressed"))
				{
					getSprite().setPaused(false);
					jump();

					setState("jumpPressed", true);
				}
			}

			// Left makes go fast
			if (key == KeyEvent.VK_LEFT)
			{
				setState("running", true);

				if (getState("bottomSolid"))
				{
					if (getAbsoluteXVelocity() > 0)
						setXVelocity(getBaseSpeed() + 4);

					if (getAbsoluteXVelocity() < 0)
						setXVelocity(-getBaseSpeed() - 4);
				}
			}

			// Space to pause
			if (key == KeyEvent.VK_SPACE)
			{
				if (getEnvironment().isPaused())
					getEnvironment().setPause(false);
				else
					getEnvironment().setPause(true);
			}
		}
	}

	public void keyReleased(int key)
	{
		if (!getState("immobile"))
		{
			if (key == KeyEvent.VK_A)
			{
				if (getAbsoluteXVelocity() < 0)
					setXVelocity(0);
			}
			if (key == KeyEvent.VK_D)
			{
				if (getAbsoluteXVelocity() > 0)
					setXVelocity(0);
			}
			if (key == KeyEvent.VK_W)
			{
				if (getState("climbingUp"))
				{
					setYVelocity(0);
					setState("climbingUp", false);
				}
			}
			if (key == KeyEvent.VK_S)
			{
				if (getState("climbingDown"))
				{
					setYVelocity(0);
					setState("climbingDown", false);
				}
			}

			if (key == KeyEvent.VK_LEFT)
			{
				setState("running", false);

				if (getAbsoluteXVelocity() > 0)
					setXVelocity(getBaseSpeed());

				if (getAbsoluteXVelocity() < 0)
					setXVelocity(-getBaseSpeed());
			}

			if (key == KeyEvent.VK_UP)
			{
				setState("jumpPressed", false);
			}

			if (key == KeyEvent.VK_RIGHT)
			{
				if (hasItem)
				{
					carriedItem.setState("release", true);
				}
			}

		}
	}

}
