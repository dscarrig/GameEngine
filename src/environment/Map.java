package environment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import banks.EntityBank;
import entities.base.Entity;

/*
 * 	The Map class contains the information for every entity present in the current area.
 */

public class Map
{
	private int rows;
	private int columns;
	private int blockSize;
	private int xViewSize;
	private int yViewSize;

	private boolean xScrollable;
	private boolean yScrollable;
	private boolean hasBackground;

	private String theme;

	private int[] transitions;
	private int[] spawnLocs;

	private String[] entityTypes;
	private int[] mapArray;

	private Environment env;
	private EntityBank entityBank;

	private BufferedImage bgImage;

	// Initializes the map array, which contains all the starting entities and their
	// location.
	public Map(int[] ma, String[] et, int r, int c, int bs, Environment e, int[] tr, int[] sl, String bg, String th)
	{
		rows = r;
		columns = c;
		blockSize = bs;

		mapArray = ma;
		entityTypes = et;
		env = e;

		theme = th;

		xViewSize = View.X_RESOLUTION / blockSize;
		yViewSize = View.Y_RESOLUTION / blockSize;

		xScrollable = xViewSize < columns;
		yScrollable = yViewSize < rows;

		transitions = tr;
		spawnLocs = sl;

		if (bg.equals(""))
			hasBackground = false;
		else
		{
			hasBackground = true;

			try
			{
				bgImage = ImageIO.read(new File("images/backgrounds/" + bg + ".png"));
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}

		entityBank = new EntityBank();
	}

	// Returns number of rows in the map.
	public int getRows()
	{
		return rows;
	}

	// Returns number of columns in the map.
	public int getColumns()
	{
		return columns;
	}

	// The blocksize is the size of each entity block.
	public int getBlockSize()
	{
		return blockSize;
	}

	public int getXViewSize()
	{
		return xViewSize;
	}

	public int getYViewSize()
	{
		return yViewSize;
	}

	public int getMapHeight()
	{
		return rows * blockSize - View.Y_RESOLUTION;
	}

	public int getMapWidth()
	{
		return columns * blockSize - View.X_RESOLUTION;
	}

	public BufferedImage getBGImage()
	{
		return bgImage;
	}

	public String getTheme()
	{
		return theme;
	}

	public boolean hasBackground()
	{
		return hasBackground;
	}

	public boolean entityExists(int index)
	{
		return mapArray[index] > -1;
	}

	public boolean isDeathZone(int index)
	{
		return mapArray[index] == -2;
	}

	public int spawnLocFromDirection(int direction)
	{
		if (spawnLocs[direction] > -1)
		{
			return spawnLocs[direction] * blockSize;
		} else
			return -1;
	}

	public void setXScrollable(boolean s)
	{
		xScrollable = s;
	}

	public boolean isXScrollable()
	{
		return xScrollable;
	}

	public void setYScrollable(boolean s)
	{
		yScrollable = s;
	}

	public boolean isYScrollable()
	{
		return yScrollable;
	}

	public Entity entityAt(int index)
	{
		return entityBank.getEntity(entityTypes[mapArray[index]], env);
	}

	public int getTransition(int which)
	{
		return transitions[which];
	}
}
