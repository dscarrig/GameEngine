package entities.players;

import environment.*;

// The class for the player character for side scrolling games
public class SideScrollPlayer extends Player
{
	public SideScrollPlayer(Environment e, String n)
	{
		super(e, n);

		setBaseSpeed(5);

		setSprite("HumanBot");
		
		setJumpHeight(20);

		if(getItems().contains(("JumpShoes")))
		{
			setJumpHeight(24);
		}
		

		setMaxHealth(6);
		setHealth(6);
		
		setSpriteState("Idle");
		getSprite().setPaused(false);
	}


}
