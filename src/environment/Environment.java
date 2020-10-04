package environment;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import banks.*;
import entities.base.Entity;
import entities.base.NullEntity;
import overlayInterface.Overlay;
import overlayInterface.OverlayComponent;

/*
 * 	The class that handles the behavior and status of all entities in the environment. 
 */

public class Environment extends JPanel
{
	private static final int START_MAP = 0;
	private static final int MAX_ENTITIES = 800;
	private static final long serialVersionUID = 1L;

	public static int UP = 0;
	public static int DOWN = 1;
	public static int LEFT = 2;
	public static int RIGHT = 3;

	private int entityCount;
	private int curMap;
	private boolean pause;
	private boolean playerAdded;
	private Listeners listeners;
	private Entity[] entities = new Entity[MAX_ENTITIES];
	private int oldPlayerX;
	private int oldPlayerY;
	private int mapXPosition;
	private int mapYPosition;
	private int bgXPosition;
	private int bgParaX;
	private boolean carrying;

	private Entity player;
	private SpriteBank spriteBank;
	private MapBank mapBank;
	private Map map;
	private Overlay overlay;

	private ArrayList<Entity> itemsGot = new ArrayList<Entity>();
	private ArrayList<String> itemList = new ArrayList<String>();

	public Environment()
	{
		spriteBank = new SpriteBank();
		mapBank = new MapBank(this);
		overlay = new Overlay(this);

		listeners = new Listeners(this);
		listeners.startTimer(30);
		setFocusable(true);

		mapXPosition = 0;
		mapYPosition = 0;
		bgXPosition = 0;
		bgParaX = 0;

		playerAdded = false;
		carrying = false;

		loadMap(START_MAP, -1);

		pause = false;
	}

	// Adds entity e to the environment
	public void addEntity(Entity e)
	{
		boolean add = true;

		if (entityCount < MAX_ENTITIES)
		{
			if (e.isPlayer())
			{
				if (!playerAdded)
				{
					player = e;
					playerAdded = true;
				} else
					add = false;
			}

			if (itemList.contains(e.getName()) && !carrying)
				add = false;

			if (add)
			{
				int index = 0;
				while (index < entityCount)
				{
					if (entities[index].getType().equals("Blank"))
						break;
					else
						index++;
				}

				entities[index] = e;

				if (index == entityCount)
					entityCount++;
			}

		} else
			System.out.println("Maximum entities");
	}

	// Removes entity e from the environment
	public void removeEntity(Entity e)
	{
		int index = 0;
		Entity nullEn = new NullEntity(this, "");

		while (!entities[index].equals(e) || index < MAX_ENTITIES)
		{
			index++;
			
			if(entities[index].equals(e))
				break;
		}

		if (entities[index].isPlayer())
		{
			addComponentToOverlayFromBank("DeathMessage");
			player = nullEn;
		}

		entities[index] = nullEn;
	}

	// Returns the entity at coordinates x,y
	public Entity entityAtCoordinates(int x, int y)
	{
		Entity foundEntity = null;

		for (int i = 0; i < entityCount; i++)
		{
			if (entities[i].getLeftCollision() < x && entities[i].getRightCollision() > x
					&& entities[i].getTopCollision() < y && entities[i].getBottomCollision() > y)
			{
				foundEntity = entities[i];
				break;
			}
		}

		return foundEntity;
	}

	public Overlay getOverlay()
	{
		return overlay;
	}

	public void addComponentToOverlay(OverlayComponent oc)
	{
		overlay.addComponent(oc);
	}

	public void addComponentToOverlayFromBank(String name)
	{
		overlay.addComponentFromBank(name);
	}

	public void removeComponentFromOverlay(String name)
	{
		overlay.removeComponentByName(name);
	}

	// Returns the array of all the entities in the environment
	public Entity[] getEntities()
	{
		return entities;
	}

	public void setEntities(Entity[] e)
	{
		entities = e;
	}

	public int getEntityCount()
	{
		return entityCount;
	}

	public int getCurMap()
	{
		return curMap;
	}

	// Pauses or unpauses the environment
	public void setPause(boolean p)
	{
		pause = p;

		if (pause)
		{
			addComponentToOverlayFromBank("PauseScreen");
			System.out.println("Player pos: " + player.getXPos() + ", " + player.getYPos());
			System.out.println("Map position: " + mapXPosition + ", " + mapYPosition);
		} else
			removeComponentFromOverlay("PauseScreen");
	}

	public boolean isPaused()
	{
		return pause;
	}

	// Calls the behavior method of all entities in the environment
	public void entityBehavior()
	{
		for (int i = 0; i < entityCount; i++)
		{
			if (entities[i].hasBehavior() && !entities[i].getType().equals("Blank"))
				entities[i].behavior();
		}
	}

	// Loads the map in the map bank at mapIndex.
	// If fromDirection is > -1, it sets the location of all entities
	public void loadMap(int mapIndex, int fromDirection)
	{
		curMap = mapIndex;
		entityCount = 0;
		playerAdded = false;

		for (int i = 0; i < entities.length; i++)
			entities[i] = null;

		map = mapBank.mapAtIndex(mapIndex);
		Entity newEntity;
		Entity offBlock;
		Entity offBlock2;

		int count = 0;
		for (int i = 0; i < map.getRows(); i++)
		{
			for (int j = 0; j < map.getColumns(); j++)
			{
				if (map.entityExists(count))
				{
					newEntity = map.entityAt(count);
					newEntity.setCoordinates(j * map.getBlockSize(), i * map.getBlockSize());

					if (newEntity.getName().equals(""))
						newEntity.setName(newEntity.getType() + count);

					if (newEntity.getType().equals("Block"))
					{
						if (j == 0 || j == map.getColumns() - 1)
						{
							offBlock = map.entityAt(count);
							offBlock2 = map.entityAt(count);

							if (j == 0)
							{
								offBlock.setCoordinates(j * map.getBlockSize() - map.getBlockSize(),
										i * map.getBlockSize());
								offBlock2.setCoordinates(j * map.getBlockSize() - 2 * map.getBlockSize(),
										i * map.getBlockSize());
							} else
							{
								offBlock.setCoordinates(j * map.getBlockSize() + map.getBlockSize(),
										i * map.getBlockSize());
								offBlock2.setCoordinates(j * map.getBlockSize() + 2 * map.getBlockSize(),
										i * map.getBlockSize());
							}

							addEntity(offBlock);
							addEntity(offBlock2);
						}
						if (i % map.getRows() == 0 || i % map.getRows() == map.getRows() - 1)
						{
							offBlock = map.entityAt(count);
							offBlock2 = map.entityAt(count);

							if (i % map.getRows() == 0)
							{
								offBlock.setCoordinates(j * map.getBlockSize(),
										i * map.getBlockSize() - map.getBlockSize());
								offBlock2.setCoordinates(j * map.getBlockSize(),
										i * map.getBlockSize() - 2 * map.getBlockSize());
							} else
							{
								offBlock.setCoordinates(j * map.getBlockSize(),
										i * map.getBlockSize() + map.getBlockSize());
								offBlock.setCoordinates(j * map.getBlockSize(),
										i * map.getBlockSize() + 2 * map.getBlockSize());
							}

							addEntity(offBlock);
							addEntity(offBlock2);
						}
					}

					if (!newEntity.getType().equals("Blank"))
						addEntity(newEntity);
				}

				count++;
			}
		}

		if (fromDirection > -1)
		{
			setupFromTransition(fromDirection);
		}

		player.moveToFront();

		for (int i = 0; i < itemsGot.size(); i++)
		{
			if (itemsGot.get(i).getState("carrying"))
			{
				carrying = true;
				addEntity(itemsGot.get(i));
				itemsGot.get(i).setCoordinates(player.getXPos(), player.getYPos());
				itemsGot.get(i).moveToFront();
			}
		}
		carrying = false;
	}

	// Sets all the locations for the entities when transitioning from another map
	private void setupFromTransition(int fromDirection)
	{
		addEntity(player);

		if (fromDirection == UP)
		{
			mapYPosition = map.getMapHeight();

			if (map.spawnLocFromDirection(DOWN) > -1)
			{
				mapXPosition = map.spawnLocFromDirection(DOWN) - View.X_RESOLUTION;

				if (mapXPosition < 0)
					mapXPosition = 0;

				if (mapXPosition == 0)
					oldPlayerX = map.spawnLocFromDirection(DOWN);
				else if (map.spawnLocFromDirection(DOWN) - View.X_RESOLUTION / 2 >= map.getMapWidth())
				{
					mapXPosition = map.getMapWidth();
					oldPlayerX = -(map.getMapWidth() - map.spawnLocFromDirection(DOWN));
				} else
					oldPlayerX = View.X_RESOLUTION / 2;
			}

			for (int i = 0; i < entityCount; i++)
			{
				entities[i].setXPos(entities[i].getXPos() - mapXPosition);
				entities[i].setYPos(entities[i].getYPos() - mapYPosition);
			}

			player.setCoordinates(oldPlayerX, View.Y_RESOLUTION - player.getSpriteHeight());

		} else if (fromDirection == DOWN)
		{
			mapYPosition = 0;

			if (map.spawnLocFromDirection(UP) > -1)
			{
				mapXPosition = map.spawnLocFromDirection(UP) - View.X_RESOLUTION;

				if (mapXPosition < 0)
					mapXPosition = 0;

				if (mapXPosition == 0)
					oldPlayerX = map.spawnLocFromDirection(UP);
				else if (map.spawnLocFromDirection(UP) - View.X_RESOLUTION / 2 >= map.getMapWidth())
				{
					mapXPosition = map.getMapWidth();
					oldPlayerX = -(map.getMapWidth() - map.spawnLocFromDirection(UP));
				} else
					oldPlayerX = View.X_RESOLUTION / 2;
			}

			for (int i = 0; i < entityCount; i++)
				entities[i].setXPos(entities[i].getXPos() - mapXPosition);

			player.setCoordinates(oldPlayerX, -player.getSpriteWidth());
			
		} else if (fromDirection == LEFT)
		{
			mapXPosition = map.getMapWidth();

			if (map.spawnLocFromDirection(RIGHT) > -1)
			{
				mapYPosition = map.spawnLocFromDirection(RIGHT) - View.Y_RESOLUTION;

				if (mapYPosition < 0)
					mapYPosition = 0;

				if (mapYPosition == 0)
				{
					oldPlayerY = map.spawnLocFromDirection(RIGHT);
				}
				else if (map.spawnLocFromDirection(RIGHT) - View.Y_RESOLUTION / 2 >= map.getMapHeight())
				{
					mapYPosition = map.getMapHeight();
					oldPlayerY = -(map.getMapHeight() - map.spawnLocFromDirection(RIGHT));
				} else
				{
					oldPlayerY = View.Y_RESOLUTION / 2;
				}
			}
			
			for (int i = 0; i < entityCount; i++)
			{
				entities[i].setYPos(entities[i].getYPos() - mapYPosition);
				entities[i].setXPos(entities[i].getXPos() - mapXPosition);
			}

			player.setCoordinates(View.X_RESOLUTION, oldPlayerY);
			
		} else if (fromDirection == RIGHT)
		{
			mapXPosition = 0;

			if (map.spawnLocFromDirection(LEFT) > -1)
			{
				mapYPosition = map.spawnLocFromDirection(LEFT) - View.Y_RESOLUTION;

				if (mapYPosition < 0)
					mapYPosition = 0;

				if (mapYPosition == 0)
				{
					oldPlayerY = map.spawnLocFromDirection(LEFT);
				}
				else if (map.spawnLocFromDirection(LEFT) - View.Y_RESOLUTION / 2 >= map.getMapHeight())
				{
					mapYPosition = map.getMapHeight();
					oldPlayerY = -(map.getMapHeight() - map.spawnLocFromDirection(LEFT));
				} else
				{
					oldPlayerY = View.Y_RESOLUTION / 2;
				}
			}

			for (int i = 0; i < entityCount; i++)
				entities[i].setYPos(entities[i].getYPos() - mapYPosition);

			player.setCoordinates(-player.getSpriteWidth(), oldPlayerY);
			
		}

		if (map.hasBackground())
		{
			bgParaX = -mapXPosition;
			bgXPosition = -mapXPosition / 10;
		}
	}

	// Returns the entity that has been set as the player character
	public Entity getPlayer()
	{
		return player;
	}

	// Returns the index of the map transition in direction which
	public int getMapTransition(int which)
	{
		return map.getTransition(which);
	}

	public String getMapTheme()
	{
		return map.getTheme();
	}

	public Sprite getSpriteFromBank(String name)
	{
		return spriteBank.getSprite(name);
	}

	public boolean bankContainsSprite(String name)
	{
		return spriteBank.containsSprite(name);
	}

	public void addItem(Entity e)
	{
		itemsGot.add(e);
		itemList.add(e.getName());
	}

	// Scrolls the view in the X direction by a value of scrollSpeed
	public void scrollXView(int scrollSpeed)
	{
		boolean scroll = true;

		if (map.isXScrollable())
		{
			if (player.getXVelocity() < 0)
			{
				if (mapXPosition <= 0 || player.getXPos() > View.X_RESOLUTION / 2)
					scroll = false;
			}

			if (player.getXVelocity() > 0)
			{
				if (mapXPosition >= map.getMapWidth() || player.getXPos() < View.X_RESOLUTION / 2)
					scroll = false;

			}

			if (scroll)
			{
				for (int i = 0; i < entityCount; i++)
				{
					entities[i].setXPos(entities[i].getXPos() - scrollSpeed);
				}

				bgParaX -= scrollSpeed;

				bgXPosition = bgParaX / 10;

				mapXPosition += scrollSpeed;
			}
		}
	}

	// Scrolls the view in the Y direction by a value of scrollSpeed
	public void scrollYView(int scrollSpeed)
	{
		boolean scroll = true;

		if (map.isYScrollable())
		{
			if (player.getYVelocity() < 0)
			{
				if (mapYPosition <= 0 || player.getYPos() > View.Y_RESOLUTION / 2)
					scroll = false;
			}

			if (player.getYVelocity() > 0)
			{
				if (mapYPosition >= map.getMapHeight() || player.getYPos() < View.Y_RESOLUTION / 2)
					scroll = false;
			}

			if (scroll)
			{
				for (int i = 0; i < entityCount; i++)
				{
					entities[i].setYPos(entities[i].getYPos() - scrollSpeed);
				}

				mapYPosition += scrollSpeed;
			}
		}
	}

	// Transitions the map to the new map in the given direction
	public void transitionMap(int direction)
	{
		boolean canTransition = true;

		// Checks if the map has a set spot where transitions must occur
		if(map.spawnLocFromDirection(direction) > -1)
		{
			if(direction == UP || direction == DOWN)
			{
				if (player.getXPos() + mapXPosition < map.spawnLocFromDirection(direction) - map.getBlockSize()
						|| player.getXPos() + mapXPosition > map.spawnLocFromDirection(direction) + map.getBlockSize())
					canTransition = false;
			}
			else
			{
				if (player.getYPos() + mapYPosition < map.spawnLocFromDirection(direction) - map.getBlockSize()
						|| player.getYPos() + mapYPosition > map.spawnLocFromDirection(direction) + map.getBlockSize())
					canTransition = false;
			}
		}

		if (map.getTransition(direction) != -1 && canTransition)
		{
			oldPlayerX = player.getXPos();
			oldPlayerY = player.getYPos();
			loadMap(map.getTransition(direction), direction);
		} else if (direction == DOWN)
		{
			player.setState("fallingOffScreen", true);
		}
	}

	// Resets the game, reloading the start map
	public void reset()
	{
		mapXPosition = 0;
		mapYPosition = 0;
		bgXPosition = 0;
		bgParaX = 0;

		playerAdded = false;
		carrying = false;

		Entity nullEn;
		for (int i = 0; i < entityCount; i++)
		{
			nullEn = new NullEntity(this, "");
			entities[i] = nullEn;
		}

		for (int i = 0; i < itemsGot.size(); i++)
		{
			itemsGot.remove(i);
			itemList.remove(i);
		}

		this.getOverlay().removeComponentByName("DeathMessage");

		loadMap(START_MAP, -1);
	}

	// Progresses the frame by calling all entity behaviors and scrolls
	public void frameProgress()
	{
		if (!pause)
		{
			entityBehavior();

			if (!player.isXBlocked())
				scrollXView(player.getXVelocity());

			if (!player.isYBlocked())
				scrollYView(player.getYVelocity());

			repaint();
		} else
			overlay.paintOverlay(getGraphics());
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Font big = new Font("Helvetica", Font.BOLD, 50);
		String toDraw;

		g.setFont(big);

		if (map.hasBackground())
			g.drawImage(map.getBGImage(), bgXPosition, 0, this);

		for (int i = 0; i < entityCount; i++)
		{
			Entity curEntity = entities[i];

			if (curEntity.isVisible())
			{
				if (curEntity.hasSprite())
				{
					g.drawImage(curEntity.getImage(), curEntity.getXPos(), curEntity.getYPos(), this);
				} else
				{
					toDraw = curEntity.getName();
					g.drawString(toDraw, curEntity.getXPos(), curEntity.getYPos());
				}
			}
		}

		overlay.paintOverlay(g);
	}

}
