package overlayInterface;

public class HealthHeart extends OverlayComponent
{	
	public static int X_OFFSET = 5;
	public static int Y_OFFSET = 5;
	
	public static int FULL = 2;
	public static int HALF = 1;
	public static int EMPTY = 0;
	
	private int heartIndex;
	private int heartState;
	
	public HealthHeart(Overlay ov, String n)
	{
		super(ov, n);
		
		setSprite("HealthHearts");
		setType("Heart");
		
		heartState = 2;
	}
	
	public void setHeartIndex(int in)
	{
		heartIndex = in;
		setCoordinates(heartIndex * getSprite().getFrameWidth() + X_OFFSET, Y_OFFSET);
	}
	
	public int getHeartIndex()
	{
		return heartIndex;
	}
	
	public void setHeartState(int state)
	{
		heartState = state;
		
		if(heartState == FULL)
		{
			setSpriteState("Full");
		}
		else if(heartState == HALF)
		{
			setSpriteState("Half");
		}
		else if(heartState == EMPTY)
		{
			setSpriteState("Empty");
		}
	}
	
	public int getHeartState()
	{
		return heartState;
	}
	
}
