package banks;

import entities.base.Entity;

/*
 * 	General behavior functions shared by various types of entities.
 */
public class Behaviors
{
	private Entity en;

	public Behaviors(Entity e)
	{
		en = e;
	}

	public void movementWithGravity()
	{
		boolean xBlocked = false;
		boolean yBlocked = false;
		boolean falling = false;
		int modifier = 0;

		Entity belowEnt;

		if (en.hasSolidCollisionEntity(en.getXVelocity(), 0))
			xBlocked = true;

		if (en.atScreenEdge(en.getSpriteWidth() / 2) == 2 && en.getEnvironment().getMapTransition(2) == -1)
			xBlocked = true;

		if (en.atScreenEdge(en.getSpriteWidth() / 2) == 3 && en.getEnvironment().getMapTransition(3) == -1)
			xBlocked = true;

		if (en.hasSolidCollisionEntity(0, en.getYVelocity() + 1))
		{
			belowEnt = en.getCollisionEntity(0, en.getSpriteHeight() / 2, true);

			if (en.getYVelocity() > 0)
				falling = true;

			if (en.getYVelocity() >= 0)
				en.setState("bottomSolid", true);

			yBlocked = true;
			en.setYVelocity(0);

			if (falling)
			{
				while (belowEnt.getTopCollision() != en.getBottomCollision() + modifier)
				{
					if (belowEnt.getTopCollision() > en.getBottomCollision() + modifier)
						modifier++;
					else
						modifier--;
				}

				en.setYPos(en.getYPos() + modifier);
			}

		} else
		{
			en.setState("bottomSolid", false);
		}

		if (!en.getState("bottomSolid") && !en.getState("onLadder"))
		{
			en.setYVelocity(en.getYVelocity() + 1);

			if (en.getYVelocity() == 0)
			{
				en.setYVelocity(en.getYVelocity() + 1);
			}
		}

		en.moveByVelocity(!xBlocked, !yBlocked);

		en.setXBlocked(xBlocked);
		en.setYBlocked(yBlocked);
	}

	public void movementWithGravityNoCollision()
	{
		boolean xBlocked = false;
		boolean yBlocked = false;

		if (en.atScreenEdge(en.getSpriteWidth() / 2) == 2 && en.getEnvironment().getMapTransition(2) == -1)
			xBlocked = true;

		if (en.atScreenEdge(en.getSpriteWidth() / 2) == 3 && en.getEnvironment().getMapTransition(3) == -1)
			xBlocked = true;

		en.setYVelocity(en.getYVelocity() + 1);

		if (en.getYVelocity() == 0)
		{
			en.setYVelocity(en.getYVelocity() + 1);
		}

		en.moveByVelocity(!xBlocked, !yBlocked);

		en.setXBlocked(xBlocked);
		en.setYBlocked(yBlocked);
	}

	public void moveTowardSideScroll(Entity otherEn)
	{
		if (en.getXVelocity() == 0)
			en.setXVelocity(en.getBaseSpeed());

		if ((otherEn.getXPos() > en.getXPos() && en.getXVelocity() < 0)
				|| (otherEn.getXPos() < en.getXPos() && en.getXVelocity() > 0))
		{
			en.setXVelocity(-en.getXVelocity());
		}
	}

	public int canSee(Entity otherEn, int sightRange, boolean directLine)
	{
		int result = -1;
		Entity losEntity = null;

		boolean checkLeft = true;
		boolean checkRight = true;
		boolean checkUp = true;
		boolean checkDown = true;

		boolean checkUpLeft = true;
		boolean checkUpRight = true;
		boolean checkDownLeft = true;
		boolean checkDownRight = true;

		for (int i = 0; i < sightRange; i++)
		{
			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos(), en.getYPos() - i);
			if (losEntity != null)
			{
				if (losEntity.equals(otherEn) && checkUp)
				{
					result = 0;
					break;
				}
				if (losEntity.isSolid())
					checkUp = false;
			}

			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos() - i, en.getYPos() - i);
			if (losEntity != null && checkUpLeft)
			{
				if (losEntity.equals(otherEn))
				{
					result = 0;
					break;
				}
				if (losEntity.isSolid())
					checkUpLeft = false;
			}

			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos() + i, en.getYPos() - i);
			if (losEntity != null && checkUpRight && !directLine)
			{
				if (losEntity.equals(otherEn))
				{
					result = 0;
					break;
				}
				if (losEntity.isSolid())
					checkUpRight = false;
			}

			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos() + i, en.getYPos());
			if (losEntity != null && checkRight)
			{
				if (losEntity.equals(otherEn))
				{
					result = 3;
					break;
				}
				if (losEntity.isSolid())
					checkRight = false;
			}

			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos() - i, en.getYPos());
			if (losEntity != null && checkLeft)
			{
				if (losEntity.equals(otherEn))
				{
					result = 2;
					break;
				}
				if (losEntity.isSolid())
					checkLeft = false;
			}

			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos(), en.getYPos() + i);
			if (losEntity != null && checkDown)
			{
				if (losEntity.equals(otherEn))
				{
					result = 1;
					break;
				}
				if (losEntity.isSolid())
					checkDown = false;
			}

			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos() - i, en.getYPos() + i);
			if (losEntity != null && checkDownLeft && !directLine)
			{
				if (losEntity.equals(otherEn))
				{
					result = 1;
					break;
				}
				if (losEntity.isSolid())
					checkDownLeft = false;
			}

			losEntity = en.getEnvironment().entityAtCoordinates(en.getXPos() + i, en.getYPos() - i);
			if (losEntity != null && checkDownRight && !directLine)
			{
				if (losEntity.equals(otherEn))
				{
					result = 1;
					break;
				}
				if (losEntity.isSolid())
					checkDownRight = false;
			}
		}

		return result;
	}

	public void topdownMovement()
	{
		boolean xBlocked = false;
		boolean yBlocked = false;

		if (en.getXVelocity() != 0 || en.getYVelocity() != 0)
		{
			if (en.hasSolidCollisionEntity(en.getXVelocity(), 0))
				xBlocked = true;

			if (en.hasSolidCollisionEntity(0, en.getYVelocity()))
				yBlocked = true;

			if (!xBlocked || !yBlocked)
			{
				en.getSprite().setPaused(false);
				en.moveByVelocity(!xBlocked, !yBlocked);
			} else
			{
				en.setXVelocity(0);
				en.setYVelocity(0);
				en.getSprite().setPaused(true);
			}
		}

		if (xBlocked)
			en.setXVelocity(0);

		if (yBlocked)
			en.setYVelocity(0);
	}

	public void freeMovement()
	{
		en.moveByVelocity(true, true);
	}

	public void knockBack(Entity otherEn, int amount)
	{
		int xPush = en.getXVelocity() * amount + amount;
		int yPush = en.getYVelocity() * amount + amount;

		if (!otherEn.hasSolidCollisionEntity(otherEn.getXVelocity() + xPush, otherEn.getYVelocity() + yPush))
		{
			otherEn.setXPos(otherEn.getXPos() + xPush);
			otherEn.setYPos(otherEn.getYPos() + yPush);

			if (otherEn.isPlayer())
			{
				otherEn.getEnvironment().scrollXView(xPush);
				otherEn.getEnvironment().scrollYView(yPush);
			}
		}
	}

	public void moveToGround()
	{
		while (!en.hasSolidCollisionEntity(0, 2))
			en.setYPos(en.getYPos() + 1);
	}
	
	public void bounce(int upSpeed)
	{
		if(en.hasSolidCollisionEntity(0, 2))
			en.setYVelocity(-upSpeed);
		else
			en.modifyYVelocity(1);
		
		en.moveByVelocity(true, true);
	}
	
	public boolean atBlockEdge()
	{
		if(en.getXVelocity() > 0)
		{
			if(en.hasSolidCollisionEntity(en.getXVelocity() + en.getSpriteWidth() / 2, en.getSpriteHeight()))
				return false;
			else
				return true;
		}
		else
		{
			if(en.hasSolidCollisionEntity(en.getXVelocity() - en.getSpriteWidth() / 2, en.getSpriteHeight()))
				return false;
			else
				return true;
		}
		
	}

}
