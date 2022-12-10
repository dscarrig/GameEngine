package banks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import environment.Sprite;


/*
 * 	Bank containing the info for each sprite for each entity.
 */
public class SpriteBank
{
	public static int WIDTH = 0;
	public static int HEIGHT = 1;

	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();

	public SpriteBank()
	{
		// Blocks
		addToBank("images/entityImages/mWall64.png", "mWall", 64, 64, 1);
		addToBank("images/entityImages/grass.png", "grass", 64, 64, 1);
		addToBank("images/entityImages/dirt.png", "dirt", 64, 64, 1);
		addToBank("images/entityImages/jiggly.png", "jiggly", 64, 64, 1);
		addToBank("images/entityImages/ladder.png", "Ladder", 60, 128, 1);
		addToBank("images/entityImages/cloudblock.png", "cloudblock", 64, 64, 1);
		
		// Characters
		addToBank("images/entityImages/CatBlack.png", "CatBlack", 32, 32, 3);
		addToBank("images/entityImages/CatTabby.png", "CatTabby", 64, 64, 3);
		
		// Enemies
		addToBank("images/entityImages/Octorok.png", "Octorok", 36, 36, 4);
		addToBank("images/entityImages/Bandit.png", "Bandit", 54, 60, 4);
		addToBank("images/entityImages/PinkBandit.png", "PinkBandit", 54, 60, 4);
		addToBank("images/entityImages/thundercloud.png", "thundercloud", 72, 57, 4);
		
		// Items
		addToBank("images/overlayImages/HealthHearts.png", "HealthHearts", 32, 28, 1);
		addToBank("images/entityImages/zapper.png", "zapper", 54, 23, 4);
		addToBank("images/entityImages/jumpshoes.png", "jumpshoes", 40, 40, 1);
		addToBank("images/entityImages/sword.png", "sword", 54, 54, 5);
		
		// Players
		addToBank("images/entityImages/linkWalking.png", "Link", 66, 72, 6);
		addToBank("images/entityImages/wheel.png", "WheelBot", 70, 71, 8);
		addToBank("images/entityImages/HumanRobot.png", "HumanBot", 71, 103, 6);
		
		// Projectiles
		addToBank("images/entityImages/fireball.png", "fireball", 35, 70, 1);
		addToBank("images/entityImages/plasma.png", "plasma", 30, 30, 1);
		addToBank("images/entityImages/lightningbolt.png", "lightningbolt", 36, 47, 4);
		
		
		// Components
		addToBank("images/overlayImages/pause.png", "Pause", 200, 200, 1);
		addToBank("images/overlayImages/youDied.png", "Death", 293, 54, 1);
	
	}
	
	public int[][] setCollisionArray(String name, int frameWidth, int frameHeight)
	{
		int[][] collisionArray = new int[0][0];
		
		switch (name)
		{
		case "CatBlack":
			collisionArray = new int[4][2];
			
			// UP
			collisionArray[0][WIDTH] = 16;
			collisionArray[0][HEIGHT] = 32;
			
			// LEFT
			collisionArray[1][WIDTH] = 32;
			collisionArray[1][HEIGHT] = 30;
			
			// RIGHT
			collisionArray[2][WIDTH] = 32;
			collisionArray[2][HEIGHT] = 30;
			
			// DOWN
			collisionArray[3][WIDTH] = 16;
			collisionArray[3][HEIGHT] = 32;
			
			break;

		case "CatTabby":
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = 24;
			collisionArray[0][HEIGHT] = 52;
			
			collisionArray[1][WIDTH] = 48;
			collisionArray[1][HEIGHT] = 44;
			
			collisionArray[2][WIDTH] = 48;
			collisionArray[2][HEIGHT] = 44;
			
			collisionArray[3][WIDTH] = 24;
			collisionArray[3][HEIGHT] = 44;
			
			break;
			
		case "WheelBot":
			collisionArray = new int[6][2];
			
			collisionArray[0][WIDTH] = 50;
			collisionArray[0][HEIGHT] = 65;
			
			collisionArray[1][WIDTH] = 50;
			collisionArray[1][HEIGHT] = 65;
			
			collisionArray[2][WIDTH] = 50;
			collisionArray[2][HEIGHT] = 65;
			
			collisionArray[3][WIDTH] = 50;
			collisionArray[3][HEIGHT] = 65;
			
			collisionArray[4][WIDTH] = 50;
			collisionArray[4][HEIGHT] = 65;
			
			collisionArray[5][WIDTH] = 50;
			collisionArray[5][HEIGHT] = 65;
			
			break;
			
		case "HumanBot":
			collisionArray = new int[9][2];
			
			collisionArray[0][WIDTH] = 50;
			collisionArray[0][HEIGHT] = 90;
			
			collisionArray[1][WIDTH] = 50;
			collisionArray[1][HEIGHT] = 90;
			
			collisionArray[2][WIDTH] = 50;
			collisionArray[2][HEIGHT] = 90;
			
			collisionArray[3][WIDTH] = 50;
			collisionArray[3][HEIGHT] = 90;
			
			collisionArray[4][WIDTH] = 50;
			collisionArray[4][HEIGHT] = 90;
			
			collisionArray[5][WIDTH] = 50;
			collisionArray[5][HEIGHT] = 90;
			
			collisionArray[6][WIDTH] = 50;
			collisionArray[6][HEIGHT] = 90;
			
			collisionArray[7][WIDTH] = 50;
			collisionArray[7][HEIGHT] = 90;
			
			collisionArray[8][WIDTH] = 50;
			collisionArray[8][HEIGHT] = 90;
			
			break;

		case "Octorok":
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = 32;
			collisionArray[0][HEIGHT] = 32;
			
			collisionArray[1][WIDTH] = 32;
			collisionArray[1][HEIGHT] = 32;
			
			collisionArray[2][WIDTH] = 32;
			collisionArray[2][HEIGHT] = 32;
			
			collisionArray[3][WIDTH] = 32;
			collisionArray[3][HEIGHT] = 32;
			
			break;
			
		case "Link":
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = 64;
			collisionArray[0][HEIGHT] = 72;
			
			collisionArray[1][WIDTH] = 64;
			collisionArray[1][HEIGHT] = 72;
			
			collisionArray[2][WIDTH] = 64;
			collisionArray[2][HEIGHT] = 72;
			
			collisionArray[3][WIDTH] = 64;
			collisionArray[3][HEIGHT] = 72;
			
			break;
			
		case "Bandit":
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = 48;
			collisionArray[0][HEIGHT] = 60;
			
			collisionArray[1][WIDTH] = 48;
			collisionArray[1][HEIGHT] = 60;
			
			collisionArray[2][WIDTH] = 48;
			collisionArray[2][HEIGHT] = 60;
			
			collisionArray[3][WIDTH] = 48;
			collisionArray[3][HEIGHT] = 60;
			
			break;
			
		case "PinkBandit":
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = 48;
			collisionArray[0][HEIGHT] = 60;
			
			collisionArray[1][WIDTH] = 48;
			collisionArray[1][HEIGHT] = 60;
			
			collisionArray[2][WIDTH] = 48;
			collisionArray[2][HEIGHT] = 60;
			
			collisionArray[3][WIDTH] = 48;
			collisionArray[3][HEIGHT] = 60;
			
			break;
			
		case "fireball":
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = frameWidth;
			collisionArray[0][HEIGHT] = frameHeight;
			
			collisionArray[1][WIDTH] = frameWidth;
			collisionArray[1][HEIGHT] = frameHeight;
			
			break;
			
		case "zapper":
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = frameWidth;
			collisionArray[0][HEIGHT] = frameHeight;
			
			collisionArray[1][WIDTH] = frameWidth;
			collisionArray[1][HEIGHT] = frameHeight;
			
			break;
			
		default:
			collisionArray = new int[4][2];
			
			collisionArray[0][WIDTH] = frameWidth;
			collisionArray[0][HEIGHT] = frameHeight;
			
			collisionArray[1][WIDTH] = frameWidth;
			collisionArray[1][HEIGHT] = frameHeight;
			
			collisionArray[2][WIDTH] = frameWidth;
			collisionArray[2][HEIGHT] = frameHeight;
			
			collisionArray[3][WIDTH] = frameWidth;
			collisionArray[3][HEIGHT] = frameHeight;

			break;
		}
		
		return collisionArray;
	}
	
	public String[] setRowArray(String name)
	{
		String[] rowArray = new String[0];
		
		switch (name)
		{
		case "CatBlack":
			rowArray = new String[4];
			
			rowArray[0] = "Down";
			rowArray[1] = "Left";
			rowArray[2] = "Right";
			rowArray[3] = "Up";
			
			break;

		case "CatTabby":
			rowArray = new String[4];
			
			rowArray[0] = "Down";
			rowArray[1] = "Left";
			rowArray[2] = "Right";
			rowArray[3] = "Up";
			
			break;
			
		case "WheelBot":
			rowArray = new String[6];
			
			rowArray[0] = "Idle";
			rowArray[1] = "Left";
			rowArray[2] = "Right";
			rowArray[3] = "JumpRight";
			rowArray[4] = "PainLeft";
			rowArray[5] = "PainRight";
			//rowArray[7] = "Death";
			//rowArray[8] = "DeathRight";
			
			break;
			
		case "HumanBot":
			rowArray = new String[9];
			
			rowArray[0] = "Idle";
			rowArray[1] = "Left";
			rowArray[2] = "Right";
			rowArray[3] = "JumpLeft";
			rowArray[4] = "JumpRight";
			rowArray[5] = "PainLeft";
			rowArray[6] = "PainRight";
			rowArray[7] = "Death";
			rowArray[8] = "DeathRight";
			
			break;

		case "Octorok":
			rowArray = new String[4];
			
			rowArray[0] = "Down";
			rowArray[1] = "Left";
			rowArray[2] = "Right";
			rowArray[3] = "Up";
			
			break;
			
		case "Link":
			rowArray = new String[4];
			
			rowArray[0] = "Down";
			rowArray[1] = "Left";
			rowArray[2] = "Right";
			rowArray[3] = "Up";
			
			break;
			
		case "Bandit":
			rowArray = new String[4];
			
			rowArray[0] = "Left";
			rowArray[1] = "Right";
			rowArray[2] = "JumpRight";
			rowArray[3] = "JumpLeft";
			
			break;
			
		case "PinkBandit":
			rowArray = new String[4];
			
			rowArray[0] = "Left";
			rowArray[1] = "Right";
			rowArray[2] = "JumpRight";
			rowArray[3] = "JumpLeft";
			
			break;
			
		case "HealthHearts":
			rowArray = new String[3];
			
			rowArray[0] = "Full";
			rowArray[1] = "Half";
			rowArray[2] = "Empty";
			
			break;
			
		case "fireball":
			rowArray = new String[2];
			
			rowArray[0] = "Up";
			rowArray[1] = "Down";
			
			break;
			
		case "zapper":
			rowArray = new String[2];
			
			rowArray[0] = "Right";
			rowArray[1] = "Left";
			
			break;
			
		case "thundercloud":
			rowArray = new String[3];
			
			rowArray[0] = "Right";
			rowArray[1] = "Left";
			rowArray[2] = "Attack";
			
			break;
			
		case "sword":
			rowArray = new String[4];
			
			rowArray[0] = "PointedUp";
			rowArray[1] = "PointedDown";
			rowArray[2] = "RightSlash";
			rowArray[3] = "LeftSlash";
			
			break;
			
		default:
			rowArray = new String[1];
			
			rowArray[0] = "Default";
			
			break;
		}
		
		return rowArray;
	}

	public Sprite getSprite(String name)
	{
		Sprite result = null;

		for (int i = 0; i < spriteList.size(); i++)
		{
			if (spriteList.get(i).getName().equals(name))
				result = spriteList.get(i);
		}

		return result;
	}

	public boolean containsSprite(String name)
	{
		boolean result = false;

		for (int i = 0; i < spriteList.size(); i++)
		{
			if (spriteList.get(i).getName().equals(name))
				result = true;
		}

		return result;
	}

	private void addToBank(String path, String name, int frameWidth, int frameHeight, int frames)
	{
		BufferedImage spriteSheet = null;
		Sprite newSprite;
		int[][] collisionArray = setCollisionArray(name, frameWidth, frameHeight);
		String[] rowArray = setRowArray(name);

		try
		{
			spriteSheet = ImageIO.read(new File(path));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		newSprite = new Sprite(spriteSheet, name);

		newSprite.setDimensions(frameHeight, frameWidth, collisionArray, rowArray, frames);

		spriteList.add(newSprite);
	}
}
