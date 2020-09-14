package overlayInterface;

import environment.View;

public class DeathMessage extends OverlayComponent
{
	public DeathMessage(Overlay ov, String n)
	{
		super(ov, n);

		setSprite("Death");
		setType("DeathMessage");

		setCoordinates(View.X_RESOLUTION / 2 - getSprite().getFrameWidth() / 2,
				View.Y_RESOLUTION / 2 - getSprite().getFrameHeight() / 2);
	}

	public void clickReleasedAt(int xPos, int yPos)
	{
		if (isOver(xPos, yPos))
			getOverlay().getEnvironment().reset();
	}
}
