package overlayInterface;

import environment.View;

public class PauseScreen extends OverlayComponent
{
	public PauseScreen(Overlay ov, String n)
	{
		super(ov, n);
		
		setSprite("Pause");
		setType("PauseScreen");
		
		setCoordinates(View.X_RESOLUTION / 2 - getSprite().getFrameWidth() / 2, View.Y_RESOLUTION / 2 - getSprite().getFrameHeight() / 2);
	}
}
