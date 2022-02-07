package entities.players;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.base.Entity;
import environment.*;
import overlayInterface.HealthHeart;

// The class for the player character for side scrolling games
public class WheelMan extends Player
{

	public WheelMan(Environment e, String n)
	{
		super(e, n);

		setBaseSpeed(7);

		setSprite("WheelBot");

		setIsPlayer(true);
		setSolid(true);

		setJumpHeight(18);
		
		if(getItems().contains(("JumpShoes")))
		{
			setJumpHeight(22);
		}

		setMaxHealth(4);
		setHealth(4);
	}

}
