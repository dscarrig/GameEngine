package banks;

import environment.*;

/*
 * 	Bank containing the arrays of entities for each map.
 */
public class MapBank
{
	private static int NUMBER_OF_MAPS = 20;
	public static int TOP = 0;
	public static int BOTTOM = 1;
	public static int LEFT = 2;
	public static int RIGHT = 3;

	private Map[] allMaps;
	
	private Environment env;

	public MapBank(Environment e)
	{
		env = e;
		
		allMaps = new Map[NUMBER_OF_MAPS];

		setMaps();
	}

	public void setMaps()
	{
		allMaps[0] = map00();
		allMaps[1] = map01();
		allMaps[2] = map02();
		allMaps[3] = map03();
		allMaps[4] = map04();
		allMaps[5] = map05();
	}
	
	public Map mapAtIndex(int index)
	{
		return allMaps[index];
	}
	
	public Map map00()
	{
		int rows = 12;
		int columns = 61;
		int blockSize = 64;
		
		String bg = "kirbysized";
		String theme = "Grass";
		
		int[] transitions = {-1, -1, -1, -1};
		int[] transitionLocs = {0, 0, 0, 0};
		int[] spawnLocs = {-1, -1, -1, -1};
		
		final int OO = -1;
		final int BL = 0;
		final int PL = 1;
		//final int ME = 2;
		final int KI = 3;
		final int WE = 4;
		final int FB = 5;
		final int LA = 6;
		//final int JF = 7;
		final int SG = 8;
		//final int WM = 9;
		//final int JS = 10;
		final int SW = 11;
		
		String[] newMapEntities = {"Block", "SideScrollPlayer", "TopDownPlayer", "Kitten", "SideScrollEnemy", "FallBlock", "Ladder", "JumpingFireball", "ShootyGuy", "WheelMan", "JumpShoes",
				"Sword"};
		
		int[] newMap = 
		{
			BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, LA, OO, BL,
			BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, LA, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			OO, OO, OO, PL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, FB, OO, BL, OO, BL, LA, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, LA, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, LA, OO, OO, OO, OO, OO, OO, LA, OO, OO,
			BL, BL, BL, BL, OO, OO, OO, OO, OO, SW, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, OO, BL, BL, BL, OO, BL, BL, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, LA, OO, OO, OO, OO, OO, OO, BL, OO, OO,
			BL, BL, BL, BL, BL, OO, KI, OO, OO, BL, OO, OO, OO, SG, OO, OO, OO, BL, BL, BL, BL, OO, OO, OO, OO, WE, BL, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, SG, OO, BL, BL, BL, BL, OO, OO, OO, OO, WE, BL, OO, OO,
			BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO
		};
		
		transitions[TOP] = 5;
		spawnLocs[TOP] = 58;
		
		return new Map(newMap, newMapEntities, rows, columns, blockSize, env, transitions, transitionLocs, spawnLocs, bg, theme);
	}
	
	public Map map01()
	{
		int rows = 12;
		int columns = 32;
		int blockSize = 64;
		
		String bg = "kirbysized";
		String theme = "Grass";
		
		int[] transitions = {-1, -1, -1, -1};
		int[] transitionLocs = {0, 0, 0, 0};
		int[] spawnLocs = {-1, -1, -1, -1};
		
		final int OO = -1;
		final int BL = 0;
		final int PL = 1;
		//final int ME = 2;
		final int KI = 3;
		final int WE = 4;
		final int FB = 5;
		final int LA = 6;
		final int JF = 7;
		final int HP = 8;
		final int SG = 9;
		
		String[] newMapEntities = {"Block", "SideScrollPlayer", "TopDownPlayer", "Kitten", "SideScrollEnemy", "FallBlock", "Ladder", "JumpingFireball", "HeartPickup",
				"ShootyGuy"};
		
		int[] newMap = 
		{
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, BL, OO, OO, BL, OO, OO, BL, OO, OO,
			OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO,
			OO, OO, KI, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, JF, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, HP, WE,
			OO, OO, OO, OO, OO, OO, OO, PL, OO, OO, FB, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, BL, LA, BL, BL, BL,
			OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO,
			OO, OO, BL, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO,
			BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, OO, LA, OO, OO, OO,
			OO, BL, BL, BL, BL, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, OO, OO, OO, OO, SG, BL, OO, LA, OO, OO, OO,
			OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO
		};
		
		transitions[BOTTOM] = 2;
		
		return new Map(newMap, newMapEntities, rows, columns, blockSize, env, transitions, transitionLocs, spawnLocs, bg, theme);
	}
	
	public Map map02()
	{
		int rows = 12;
		int columns = 32;
		int blockSize = 64;
		
		String bg = "mess";
		String theme = "Cave";
		
		int[] transitions = {-1, -1, -1, -1};
		int[] transitionLocs = {0, 0, 0, 0};
		int[] spawnLocs = {-1, -1, -1, -1};
		
		final int OO = -1;
		final int BL = 0;
		//final int PL = 1;
		//final int ME = 2;
		final int KI = 3;
		final int WE = 4;
		final int FB = 5;
		final int LA = 6;
		final int JF = 7;
		final int ZG = 8;
		
		String[] newMapEntities = {"Block", "SideScrollPlayer", "TopDownPlayer", "Kitten", "SideScrollEnemy", "FallBlock", "Ladder", "JumpingFireball",
				"ZapGun"};
		
		int[] newMap = 
		{
			OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
			OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, BL, OO, OO, OO, OO, BL, OO, OO, BL, BL, LA, OO, OO, OO,
			OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO,
			BL, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, JF, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO, OO,
			OO, OO, ZG, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, KI, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, BL, BL, OO, BL, OO, OO, KI, OO, BL, OO, WE, OO, OO, WE, OO, LA, BL, BL, BL, BL, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO,
			BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL
		};
		
		transitions[TOP] = 1;
		transitions[LEFT] = 3;
		transitions[RIGHT] = 3;
		spawnLocs[TOP] = 29;
		
		return new Map(newMap, newMapEntities, rows, columns, blockSize, env, transitions, transitionLocs, spawnLocs, bg, theme);
	}
	 
	public Map map03()
	{
		int rows = 12;
		int columns = 32;
		int blockSize = 64;
		
		String bg = "jiggy";
		String theme = "Jiggly";
		
		int[] transitions = {-1, -1, -1, -1};
		int[] transitionLocs = {0, 0, 0, 0};
		int[] spawnLocs = {-1, -1, -1, -1};
		
		final int OO = -1;
		final int BL = 0;
		//final int PL = 1;
		//final int ME = 2;
		final int KI = 3;
		final int WE = 4;
		final int FB = 5;
		final int SG = 6;
		final int HP = 7;
		
		String[] newMapEntities = {"Block", "SideScrollPlayer", "TopDownPlayer", "Kitten", "SideScrollEnemy", "FallBlock", "ShootyGuy", "HeartPickup"};
		
		int[] newMap = 
		{
			BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, BL, BL, OO, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO,
			BL, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, KI, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, KI, BL, OO, OO, SG, OO, OO, WE, OO, HP, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO,
			BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL
		};
		
		transitions[TOP] = 1;
		transitions[LEFT] = 2;
		transitions[RIGHT] = 2;
		
		return new Map(newMap, newMapEntities, rows, columns, blockSize, env, transitions, transitionLocs, spawnLocs, bg, theme);
	}
	
	public Map map04()
	{
		int rows = 24;
		int columns = 64;
		int blockSize = 64;
		
		String bg = "greengreens";
		String theme = "Grass";
		
		int[] transitions = {-1, -1, -1, -1};
		int[] transitionLocs = {0, 0, 0, 0};
		int[] spawnLocs = {-1, -1, -1, -1};
		
		final int OO = -1;
		final int BL = 0;
		final int PL = 1;
		//final int ME = 2;
		final int KI = 3;
		final int WE = 4;
		final int FB = 5;
		final int LA = 6;
		final int JF = 7;
		final int SG = 8;
		final int ZG = 9;
		final int HP = 10;
		
		String[] newMapEntities = {"Block", "SideScrollPlayer", "TopDownPlayer", "Kitten", "SideScrollEnemy", "FallBlock", "Ladder", "JumpingFireball", "ShootyGuy",
				"ZapGun", "HeartPickup"};
		
		int[] newMap = 
			{
				OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
				OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, BL, OO, OO, BL, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, BL, OO, OO, BL, OO, OO, BL, OO, OO,
				OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
				OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO,
				OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO,
				OO, OO, KI, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, JF, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, JF, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, BL, LA, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, BL, LA, BL, BL, BL,
				OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO,
				OO, OO, BL, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO, OO, BL, BL, BL, BL, BL, KI, OO, KI, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, LA, OO, OO, OO,
				BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, OO, LA, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, OO, LA, OO, OO, OO,
				OO, BL, BL, BL, BL, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, OO, OO, OO, OO, WE, BL, OO, LA, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, ZG, OO, OO, WE, BL, OO, LA, OO, OO, OO,
				OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, OO, OO, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, OO, OO, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				BL, OO, OO, OO, BL, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, BL, OO, OO, OO, OO, OO, LA, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, OO, OO, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, KI, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				OO, OO, OO, OO, OO, OO, OO, KI, BL, LA, OO, SG, OO, OO, WE, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, HP, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
				BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, LA, OO, OO, OO,
			};
		
		transitions[LEFT] = 5;
		transitions[BOTTOM] = 2;
		transitionLocs[BOTTOM] = 59;
		spawnLocs[LEFT] = 21;
		
		return new Map(newMap, newMapEntities, rows, columns, blockSize, env, transitions, transitionLocs, spawnLocs, bg, theme);
	}
	
	public Map map05()
	{
		int rows = 12;
		int columns = 61;
		int blockSize = 64;
		
		String bg = "greengreens";
		String theme = "cloud";
		
		int[] transitions = {-1, -1, -1, -1};
		int[] transitionLocs = {0, 0, 0, 0};
		int[] spawnLocs = {-1, -1, -1, -1};
		
		final int OO = -1;
		final int BL = 0;
		//final int PL = 1;
		//final int ME = 2;
		final int KI = 3;
		final int WE = 4;
		final int FB = 5;
		final int LA = 6;
		//final int JF = 7;
		final int SG = 8;
		final int TC = 9;
		final int HP = 10;
		final int JS = 11;
		
		
		
		String[] newMapEntities = {"Block", "SideScrollPlayer", "TopDownPlayer", "Kitten", "SideScrollEnemy", "FallBlock", "Ladder", "JumpingFireball", "ShootyGuy", "ThunderCloud", 
				"HeartPickup", "JumpShoes"};
		
		int[] newMap = 
		{
			BL, BL, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, BL, BL,
			BL, OO, OO, OO, OO, BL, BL, BL, OO, OO, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, TC, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, BL, BL,
			OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL,
			OO, OO, OO, OO, OO, TC, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, HP, SG, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, FB, FB, BL, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, BL, BL, OO, OO, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO,
			OO, HP, BL, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, BL, BL, OO, BL, BL, OO, OO, OO, FB, FB, FB, OO, OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, WE, OO, OO,
			BL, BL, BL, LA, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, BL, BL, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, OO, OO, BL, BL, OO, OO, OO, OO, BL, BL, BL, BL, BL,
			BL, BL, BL, LA, BL, OO, KI, OO, OO, BL, OO, OO, OO, SG, OO, OO, OO, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, OO, OO, OO, JS, BL, BL, BL, OO, OO, OO, BL, BL, BL, BL, BL,
			BL, BL, BL, LA, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, OO, BL, BL, BL, BL, BL, BL, BL, BL, BL, OO, OO, BL, BL, BL, BL, BL
		};
		
		transitions[BOTTOM] = 0;
		spawnLocs[BOTTOM] = 3;
		
		transitions[RIGHT] = 4;
		spawnLocs[RIGHT] = 7;
		
		return new Map(newMap, newMapEntities, rows, columns, blockSize, env, transitions, transitionLocs, spawnLocs, bg, theme);
	}
}
