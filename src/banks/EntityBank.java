package banks;

import entities.base.Entity;
import entities.blocks.Block;
import entities.blocks.FallBlock;
import entities.blocks.GrassBlock;
import entities.blocks.Ladder;
import entities.characters.Kitten;
import entities.characters.WackyFlyThing;
import entities.enemies.JumpingFireball;
import entities.enemies.ShootyGuy;
import entities.enemies.SideScrollEnemy;
import entities.enemies.ThunderCloud;
import entities.items.HeartPickup;
import entities.items.JumpShoes;
import entities.items.Sword;
import entities.items.ZapGun;
import entities.players.SideScrollPlayer;
import entities.players.TopDownPlayer;
import entities.players.WheelMan;
import entities.projectiles.CatMissile;
import environment.*;

/*
 * 	Bank containing the names and initialization methods for each type of entity.
 */
public class EntityBank
{

	public EntityBank()
	{
	}

	public Entity getEntity(String name, Environment env)
	{
		Entity newEntity = null;

		switch (name)
		{
		// Players
		
		case "SideScrollPlayer":
			newEntity = new SideScrollPlayer(env, "");
			break;
			
		case "TopDownPlayer":
			newEntity = new TopDownPlayer(env, "");
			break;
			
		case "WheelMan":
			newEntity = new WheelMan(env, "");
			break;
		
		// Enemies
			
		case "SideScrollEnemy":
			newEntity = new SideScrollEnemy(env, "");
			break;
			
		case "JumpingFireball":
			newEntity = new JumpingFireball(env, "");
			break;
			
		case "ShootyGuy":
			newEntity = new ShootyGuy(env, "");
			break;
			
		case "ThunderCloud":
			newEntity = new ThunderCloud(env, "");
			break;
		
		// Items
			
		case "ZapGun":
			newEntity = new ZapGun(env, "");
			break;
			
		case "HeartPickup":
			newEntity = new HeartPickup(env, "");
			break;
			
		case "JumpShoes":
			newEntity = new JumpShoes(env, "");
			break;
			
		case "Sword":
			newEntity = new Sword(env, "");
			break;
		
		// Projectiles
			
		case "CatMissile":
			newEntity = new CatMissile(env, "");
			break;
		
		// Blocks
			
		case "Block":
			newEntity = new Block(env, "");
			break;
			
		case "FallBlock":
			newEntity = new FallBlock(env, "");
			break;
			
		case "GrassBlock":
			newEntity = new GrassBlock(env, "");
			break;
			
		case "Ladder":
			newEntity = new Ladder(env, "");
			break;
			
		// Characters
		
		case "WackyFlyThing":
			newEntity = new WackyFlyThing(env, "");
			break;
			
		case "Kitten":
			newEntity = new Kitten(env, "");
			break;

		default:
			System.out.println("Entity: " + name + " does not exist.");
		}

		return newEntity;
	}
}
