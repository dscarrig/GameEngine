package entities.base;

import java.awt.Image;
import java.util.HashMap;

import banks.Behaviors;
import environment.*;

/*
 * 	Generic entity class. Contains functions and variables shared by all types of entities.
 */
public abstract class Entity implements Cloneable
{
	public static int UP = 0;
	public static int DOWN = 1;
	public static int LEFT = 2;
	public static int RIGHT = 3;

	public static int TERMINAL_VELOCITY = 32;

	private int xPos;
	private int yPos;
	private int xVelocity;
	private int yVelocity;
	private int tempXVelocity;
	private int tempYVelocity;
	private int baseSpeed;
	private int health;
	private int maxHealth;
	private int power;

	private boolean hasSprite;
	private boolean solid;
	private boolean isPlayer;
	private boolean isEnemy;
	private boolean xBlocked;
	private boolean yBlocked;
	private boolean hasBehavior;
	private boolean visible;

	private String name;
	private String type;
	private Environment env;
	private Sprite sprite;

	private HashMap<String, Boolean> states = new HashMap<>();

	private Behaviors behavior;

	public Entity(Environment e, String n)
	{
		xPos = 0;
		yPos = 0;
		xVelocity = 0;
		yVelocity = 0;
		tempXVelocity = 0;
		tempYVelocity = 0;
		maxHealth = -1;
		power = 0;
		name = n;
		type = "Blank";
		hasSprite = false;
		env = e;
		solid = false;
		isPlayer = false;
		isEnemy = false;
		xBlocked = false;
		yBlocked = false;
		hasBehavior = true;
		visible = true;

		behavior = new Behaviors(this);
	}

	// The name of this specific entity, should be unique for all entities in the
	// environment.
	public void setName(String n)
	{
		name = n;
	}

	public String getName()
	{
		return name;
	}

	// The type of this entity, such as Player, Enemy, Item, etc.
	public void setType(String n)
	{
		type = n;
	}

	public String getType()
	{
		return type;
	}

	public void setHealth(int h)
	{
		health = h;

		if (maxHealth == -1)
			maxHealth = h;
	}

	public int getHealth()
	{
		return health;
	}

	public void setPower(int p)
	{
		power = p;
	}

	public int getPower()
	{
		return power;
	}

	public void setState(String s, boolean v)
	{
		states.put(s, v);
	}

	public boolean getState(String s)
	{
		if (states.containsKey(s))
			return states.get(s);
		else
			return false;
	}

	public void setMaxHealth(int h)
	{
		maxHealth = h;
	}

	public void modifyHealth(int h)
	{
		if (health + h <= maxHealth && health + h > -1)
			health += h;

		else if (health + h > maxHealth)
			health = maxHealth;

		else if (health + h < 0)
			health = 0;
	}

	public void setHasBehavior(boolean hb)
	{
		hasBehavior = hb;
	}

	public boolean hasBehavior()
	{
		return hasBehavior;
	}

	public void setVisible(boolean v)
	{
		visible = v;
	}

	public boolean isVisible()
	{
		return visible;
	}

	// Gets the Behaviors class, which contains methods for common universal entity
	// behaviors.
	public Behaviors myBehavior()
	{
		return behavior;
	}

	// Tries to find Sprite s in the spriteBank, if it's there sets the sprite.
	public void setSprite(String s)
	{
		if (getEnvironment().bankContainsSprite(s))
		{
			sprite = getEnvironment().getSpriteFromBank(s);
			hasSprite = true;
		} else
			System.out.println("Couldn't find sprite " + s);
	}

	public Sprite getSprite()
	{
		if (hasSprite)
			return sprite;
		else
			return null;
	}

	public Image getImage()
	{
		if (hasSprite)
			return sprite.getCurrentImage();
		else
			return null;
	}

	public void setSpriteState(String spriteState)
	{
		sprite.setRow(spriteState, false);
	}

	public String getSpriteState()
	{
		return sprite.getCurrentState();
	}

	public void setSpriteStateTerminal(String spriteState)
	{
		sprite.setRow(spriteState, true);
	}

	public void setCoordinates(int x, int y)
	{
		xPos = x;
		yPos = y;
	}

	public int getXPos()
	{
		return xPos;
	}

	public int getYPos()
	{
		return yPos;
	}

	public void setXPos(int xp)
	{
		xPos = xp;
	}

	public void setYPos(int yp)
	{
		yPos = yp;
	}

	public void setXVelocity(int xv)
	{
		xVelocity = xv;
	}

	public void setYVelocity(int yv)
	{
		yVelocity = yv;
	}

	public void modifyXVelocity(int xv)
	{
		xVelocity += xv;
	}

	public void modifyYVelocity(int yv)
	{
		yVelocity += yv;
	}

	public int getXVelocity()
	{
		return xVelocity + tempXVelocity;
	}

	public int getYVelocity()
	{
		return yVelocity + tempYVelocity;
	}
	
	public int getAbsoluteXVelocity()
	{
		return xVelocity;
	}

	public int getAbsoluteYVelocity()
	{
		return yVelocity;
	}
	
	public void setTempXVelocity(int xv)
	{
		tempXVelocity = xv;
	}

	public void setTempYVelocity(int yv)
	{
		tempYVelocity = yv;
	}
	
	public int getTempXVelocity()
	{
		return tempXVelocity;
	}

	public int getTempYVelocity()
	{
		return tempYVelocity;
	}

	public void setBaseSpeed(int s)
	{
		baseSpeed = s;
	}

	public int getBaseSpeed()
	{
		return baseSpeed;
	}

	public boolean hasSprite()
	{
		return hasSprite;
	}

	public void setSolid(boolean s)
	{
		solid = s;
	}

	public boolean isSolid()
	{
		return solid;
	}

	public void setIsPlayer(boolean s)
	{
		isPlayer = s;
	}

	public boolean isPlayer()
	{
		return isPlayer;
	}

	public void setIsEnemy(boolean s)
	{
		isEnemy = s;
	}

	public boolean isEnemy()
	{
		return isEnemy;
	}

	public void setXBlocked(boolean s)
	{
		xBlocked = s;
	}

	public boolean isXBlocked()
	{
		return xBlocked;
	}

	public void setYBlocked(boolean s)
	{
		yBlocked = s;
	}

	public boolean isYBlocked()
	{
		return yBlocked;
	}

	public int getSpriteHeight()
	{
		return sprite.getFrameHeight();
	}

	public int getSpriteWidth()
	{
		return sprite.getFrameWidth();
	}

	public int getTopCollision()
	{
		if (hasSprite())
			return yPos + (getSpriteHeight() / 2) - (sprite.getCollisionHeight() / 2);
		else
			return -1;
	}

	public int getBottomCollision()
	{
		if (hasSprite())
			return yPos + (getSpriteHeight() / 2) + (sprite.getCollisionHeight() / 2);
		else
			return -1;
	}

	public int getLeftCollision()
	{
		if (hasSprite())
			return xPos + (getSpriteWidth() / 2) - (sprite.getCollisionWidth() / 2);
		else
			return -1;
	}

	public int getRightCollision()
	{
		if (hasSprite())
			return xPos + (getSpriteWidth() / 2) + (sprite.getCollisionWidth() / 2);
		else
			return -1;
	}

	// Returns the Environment the entity currently exists in.
	public Environment getEnvironment()
	{
		return env;
	}

	public void moveByVelocity(boolean xMove, boolean yMove)
	{
		int xTotal = xVelocity + tempXVelocity;
		int yTotal = yVelocity + tempYVelocity;
		
		if (xMove)
		{
			if (xTotal <= TERMINAL_VELOCITY)
				xPos += xTotal;
			else
				xPos += TERMINAL_VELOCITY;
		}
		if (yMove)
		{
			if (yTotal <= TERMINAL_VELOCITY)
				yPos += yTotal;
			else
				yPos += TERMINAL_VELOCITY;
		}

	}

	// Returns the Entity that this Entity is colliding with when moving at the
	// given x/y velocity. If mustBeSolid is true, only
	// returns the found entity if it's solid.
	public Entity getCollisionEntity(int xVelo, int yVelo, boolean mustBeSolid)
	{
		Entity collisionEntity = null;
		boolean found = false;
		Entity[] allEntities = env.getEntities();

		for (int i = 0; i < env.getEntityCount(); i++)
		{
			if (allEntities[i].isSolid() || !mustBeSolid)
			{
				if (checkCollisionWith(allEntities[i], xVelo, yVelo))
				{
					collisionEntity = allEntities[i];
					found = true;
					break;
				}
			}
		}

		if (found)
			return collisionEntity;
		else
			return this;
	}

	// Returns true if a solid entity exists with the given x/y velocity.
	public boolean hasSolidCollisionEntity(int xVelo, int yVelo)
	{
		Entity otherEnt = getCollisionEntity(xVelo, yVelo, true);

		if (otherEnt.isSolid() && !getName().equals(otherEnt.getName()))
			return true;
		else
			return false;
	}

	// Returns true if this Entity is colliding with the given otherEnt entity.
	public boolean checkCollisionWith(Entity otherEnt, int xVelo, int yVelo)
	{
		boolean collision = false;

		if (getTopCollision() + yVelo < otherEnt.getBottomCollision()
				&& getBottomCollision() + yVelo > otherEnt.getTopCollision()
				&& getLeftCollision() + xVelo < otherEnt.getRightCollision()
				&& getRightCollision() + xVelo > otherEnt.getLeftCollision())
			collision = true;

		if (getName().equals(otherEnt.getName()))
			collision = false;

		return collision;
	}

	// Returns true if this Entity is beyond the view boundary.
	public boolean isOffScreen()
	{
		boolean result = false;

		if (xPos > View.X_RESOLUTION + 2 * getSpriteWidth() || xPos < 0 - 2 * getSpriteWidth()
				|| yPos > View.Y_RESOLUTION + 2 * getSpriteHeight() || yPos < 0 - 2 * getSpriteHeight())
			result = true;

		return result;
	}

	// Returns the direction int of which edge of the screen this Entity is at, or
	// -1 if not at an edge.
	public int atScreenEdge(int tolerance)
	{
		boolean atXEdge = false;
		boolean atYEdge = false;

		int xDirection = -1;
		int yDirection = -1;

		if (getXVelocity() > 0)
			xDirection = RIGHT;
		if (getXVelocity() < 0)
			xDirection = LEFT;
		if (getYVelocity() > 0)
			yDirection = DOWN;
		if (getYVelocity() < 0)
			yDirection = UP;

		if (xDirection == RIGHT && getXPos() - tolerance > View.X_RESOLUTION - getSpriteWidth())
			atXEdge = true;
		if (xDirection == LEFT && getXPos() + tolerance < 0)
			atXEdge = true;
		if (yDirection == UP && getYPos() + tolerance < 0)
			atYEdge = true;
		if (yDirection == DOWN && getYPos() - tolerance > View.Y_RESOLUTION - getSpriteWidth())
			atYEdge = true;

		if (atYEdge)
			return yDirection;
		else if (atXEdge)
			return xDirection;
		else
			return -1;
	}

	public void delete()
	{
		visible = false;
		
		env.removeEntity(this);
	}

	public void moveToFront()
	{
		Entity[] enArray = getEnvironment().getEntities().clone();
		Entity temp = null;

		for (int i = 0; i < getEnvironment().getEntityCount(); i++)
		{
			if (enArray[i].equals(this))
			{
				enArray[i] = enArray[getEnvironment().getEntityCount() - 1];
				break;
			}
		}

		enArray[getEnvironment().getEntityCount() - 1] = this;

		if (!this.getType().contentEquals("Player"))
		{
			for (int i = 0; i < getEnvironment().getEntityCount() - 1; i++)
			{
				if (enArray[i].getType().contentEquals("Player"))
				{
					temp = enArray[i];
					enArray[i] = enArray[getEnvironment().getEntityCount() - 2];
					break;
				}
			}

			enArray[getEnvironment().getEntityCount() - 2] = temp;
		}

		getEnvironment().setEntities(enArray.clone());

		for (int i = 0; i < enArray.length; i++)
		{
			enArray[i] = null;
		}
	}

	// Abstract method for all Entities individual behavior.
	public abstract void behavior();

	public void keyPressed(int key)
	{
	}

	public void keyReleased(int key)
	{
	}

	public void clickPressedAt(int xSpot, int ySpot)
	{
	}

	public void clickReleasedAt(int xSpot, int ySpot)
	{
	}

	public void collisionEvent(Entity e)
	{
	}

	public Object clone()
	{
		try
		{
			return super.clone();
		} catch (Exception e)
		{
			return null;
		}
	}

}
