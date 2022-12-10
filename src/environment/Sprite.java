package environment;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

/*
 * 	Class for sprites, contains info for sprite size and animation.
 */
public class Sprite implements Cloneable
{
	public static int WIDTH = 0;
	public static int HEIGHT = 1;

	private int frameHeight;
	private int frameWidth;

	private int[][] collisionArray;
	private String[] rowArray;

	private int frames;
	private int curColumn;
	private int curRow;
	private int numRows;
	private int frameDelay;
	private int frameCounter;

	private String name;

	private BufferedImage spriteSheet;
	private BufferedImage currentImage;
	private Timer timer;

	private boolean paused;
	private boolean playOnce;
	private boolean random;

	public Sprite(BufferedImage im, String n)
	{
		spriteSheet = im;

		frameHeight = 0;
		frameWidth = 0;
		frames = 0;

		curColumn = 0;
		curRow = 0;

		name = n;

		frameDelay = 1;
		frameCounter = 1;

		timer = new Timer(100, new SpriteTimerListener());

		collisionArray = new int[1][2];
		collisionArray[0][0] = 0;
		collisionArray[0][1] = 0;

		paused = true;
		playOnce = false;
	}

	public void setDimensions(int h, int w, int[][] ca, String[] ra, int fr)
	{
		frameHeight = h;
		frameWidth = w;
		collisionArray = ca;
		rowArray = ra;
		frames = fr;

		numRows = spriteSheet.getHeight() / frameHeight;

		setCurrentImage(0, 0);
	}

	public void setCurrentImage(int x, int y)
	{
		if (x + frameWidth <= spriteSheet.getWidth() && y + frameHeight <= spriteSheet.getHeight())
		{
			currentImage = spriteSheet.getSubimage(x, y, frameWidth, frameHeight);
		}
		else
		{
			System.out.println(
					"Something went wrong with " + getName() + " sprite at column " + curColumn + " and row " + curRow);
		}
	}

	public void setPaused(boolean p)
	{
		if (!p && frames > 1)
		{
			timer.start();
		}
		else if (frameDelay > 1)
		{
			frameCounter = 0;
		}

		paused = p;
	}

	public void setDelay(int delay)
	{
		frameDelay = delay;

		if (delay > 1)
		{
			frameCounter = 0;
		} else
		{
			frameCounter = 1;
		}
	}

	public int getFrameWidth()
	{
		return frameWidth;
	}

	public int getFrameHeight()
	{
		return frameHeight;
	}

	public int getCollisionHeight()
	{
		try
		{
			return collisionArray[curRow][HEIGHT];
		} catch (Exception e)
		{
			System.out.println("curRow: " + curRow + ", HEIGHT: " + HEIGHT);
			return 0;
		}
	}

	public int getCollisionWidth()
	{
		try
		{
			return collisionArray[curRow][WIDTH];
		} catch (Exception e)
		{
			System.out.println("curRow: " + curRow + ", WIDTH: " + WIDTH);
			return 0;
		}

	}

	public Image getCurrentImage()
	{
		return currentImage;
	}

	public String getName()
	{
		return name;
	}

	public String getCurrentState()
	{
		return rowArray[curRow];
	}

	public void randomize(boolean r)
	{
		random = r;
	}

	public boolean atRow(String direction)
	{
		return curRow == getRowIndex(direction);
	}

	public int getRowIndex(String direction)
	{
		int rowIndex = 0;
		boolean found = false;

		for (int i = 0; i < rowArray.length; i++)
		{
			if (rowArray[i].equals(direction))
			{
				found = true;
				break;
			}

			rowIndex++;
		}

		if (found)
		{
			return rowIndex;
		} else
		{
			return -1;
		}
	}

	public void setRow(String direction, boolean terminal)
	{
		if (getRowIndex(direction) == -1)
		{
			// System.out.println("--> Could not find " + direction);
			curRow = 0;
		} else
		{
			if (getRowIndex(direction) < numRows)
			{
				curRow = getRowIndex(direction);
				setCurrentImage(curColumn * frameWidth, curRow * frameHeight);
				playOnce = terminal;

				if (terminal)
				{
					curColumn = 0;
				}
			}
		}
	}

	public void setColumn(int column)
	{
		if (column < frames)
		{
			curColumn = column;
		}
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

	private class SpriteTimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (frameDelay > 1)
			{
				frameCounter++;
			}

			if (!paused && frameCounter % frameDelay == 0)
			{
				if (random)
				{
					curColumn = (int) (Math.random() * frames);
				}
				else
				{
					curColumn++;
				}

				if (curColumn >= frames - 1)
				{
					if (playOnce)
					{
						setPaused(true);
					} else
					{
						curColumn = 0;
					}
				}

				frameCounter = 0;
				setCurrentImage(curColumn * frameWidth, curRow * frameHeight);
			}
		}
	}

}
