package entities.players;

import java.awt.event.KeyEvent;

import entities.base.Entity;
import environment.Environment;

public class TopDownPlayer extends Entity
{

	public TopDownPlayer(Environment e, String n)
	{
		super(e, n);

		setType("Player");
		setBaseSpeed(4);

		setSprite("Link");

		setIsPlayer(true);
		setSolid(true);
		
		System.out.println("--> Made a topdown player");
	}

	@Override
	public void behavior()
	{
		myBehavior().topdownMovement();
	}

	public void keyPressed(int key)
	{
		if (key == KeyEvent.VK_A)
		{
			setXVelocity(-getBaseSpeed());
			setSpriteState("Left");
		}
		if (key == KeyEvent.VK_D)
		{
			setXVelocity(getBaseSpeed());
			setSpriteState("Right");
		}
		if (key == KeyEvent.VK_W)
		{
			setYVelocity(-getBaseSpeed());
			setSpriteState("Up");
		}
		if (key == KeyEvent.VK_S)
		{
			setYVelocity(getBaseSpeed());
			setSpriteState("Down");
		}

		if (key == KeyEvent.VK_SPACE)
		{
			if (getEnvironment().isPaused())
				getEnvironment().setPause(false);
			else
				getEnvironment().setPause(true);
		}
	}

	public void keyReleased(int key)
	{

		if (key == KeyEvent.VK_A)
		{
			if (getXVelocity() < 0)
				setXVelocity(0);

			if (getYVelocity() != 0)
			{
				if (getYVelocity() < 0)
					setSpriteState("Up");
				else
					setSpriteState("Down");
			}
		}
		if (key == KeyEvent.VK_D)
		{
			if (getXVelocity() > 0)
				setXVelocity(0);

			if (getYVelocity() != 0)
			{
				if (getYVelocity() < 0)
					setSpriteState("Up");
				else
					setSpriteState("Down");
			}
		}
		if (key == KeyEvent.VK_W)
		{
			if (getYVelocity() < 0)
				setYVelocity(0);

			if (getXVelocity() != 0)
			{
				if (getXVelocity() < 0)
					setSpriteState("Left");
				else
					setSpriteState("Right");
			}
		}
		if (key == KeyEvent.VK_S)
		{
			if (getYVelocity() > 0)
				setYVelocity(0);

			if (getXVelocity() != 0)
			{
				if (getXVelocity() < 0)
					setSpriteState("Left");
				else
					setSpriteState("Right");
			}
		}

		if (getXVelocity() == 0 && getYVelocity() == 0)
			getSprite().setPaused(true);
	}

}
