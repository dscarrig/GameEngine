package overlayInterface;

import java.awt.Image;

import environment.Sprite;

public abstract class OverlayComponent implements Cloneable
{

	private int xPos;
	private int yPos;
	private boolean hasSprite;

	private String name;
	private String type;
	private Overlay overlay;
	private Sprite sprite;

	public OverlayComponent(Overlay ov, String n)
	{
		xPos = 0;
		yPos = 0;
		name = n;
		hasSprite = false;
		overlay = ov;

		type = "Blank";

		ov.addComponent(this);
	}

	public void setName(String n)
	{
		name = n;
	}

	public String getName()
	{
		return name;
	}

	public void setType(String t)
	{
		type = t;
	}

	public String getType()
	{
		return type;
	}

	public void setSprite(String s)
	{
		if (overlay.getEnvironment().bankContainsSprite(s))
		{
			sprite = (Sprite) overlay.getEnvironment().getSpriteFromBank(s).clone();
			hasSprite = true;
		} else
		{
			System.out.println("--> Couldn't find sprite " + s);
		}
	}

	public boolean hasSprite()
	{
		return hasSprite;
	}

	public Sprite getSprite()
	{
		if (hasSprite())
			return sprite;
		else
			return null;
	}

	public void setSpriteState(String spriteState)
	{
		sprite.setRow(spriteState, false);
	}

	public Image getImage()
	{
		return sprite.getCurrentImage();
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

	public Overlay getOverlay()
	{
		return overlay;
	}

	public boolean isOver(int xSpot, int ySpot)
	{
		if (xSpot < getXPos() + sprite.getCollisionWidth() && xSpot > getXPos()
				&& ySpot < getYPos() + sprite.getCollisionHeight() && ySpot > getYPos())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void clickPressedAt(int xSpot, int ySpot)
	{
	}

	public void clickReleasedAt(int xSpot, int ySpot)
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
