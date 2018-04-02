package com.objectteam.willynilly;

import com.objectteam.framework.GameObject;
import com.objectteam.willynilly.levels.*;

public class LevelGenerator {
	
	public void generateSavedLevel(World world, int levelNumber) {

		Level thisLevel = getLevel(levelNumber);
		String[] levelData = getLevelData(thisLevel);
		
		world.levelName = thisLevel.getName();
        world.platforms.clear();
        world.springs.clear();
        world.bats.clear();
        world.waftyBirds.clear();
        world.opals.clear();
        world.fans.clear();
        world.wombatSigns.clear();
        world.echidnas.clear();
        world.foregroundDecoration.clear();
        world.backgroundDecoration.clear();
        world.finishes.clear();
        world.setWorldHeight(levelData.length);
        world.willy.position.set(1, 6);
        world.willy.velocity.y = 2;
		for (int y=0; y<levelData.length; y++) {
			for (int x=0; x<levelData[y].length(); x++) {
				switch (levelData[y].charAt(x)) {
				case '1': 
				case '#': 
				{
			        Platform platform = new Platform(Platform.PLATFORM_TYPE_GRASSY_ROCK, Platform.PLATFORM_MOTION_TYPE_STATIC, x * 2,  y, world);
			        world.platforms.add(platform);
					break;
				}
				case '2': 
				{
			        Platform platform = new Platform(Platform.PLATFORM_TYPE_ROCK_TUNNEL_LEFT, Platform.PLATFORM_MOTION_TYPE_STATIC, x * 2,  y, world);
			        world.platforms.add(platform);
					break;
				}
				case '3': 
				{
			        Platform platform = new Platform(Platform.PLATFORM_TYPE_ROCK_TUNNEL_CENTER, Platform.PLATFORM_MOTION_TYPE_STATIC, x * 2,  y, world);
			        world.platforms.add(platform);
					break;
				}
				case '4': 
				{
			        Platform platform = new Platform(Platform.PLATFORM_TYPE_ROCK_TUNNEL_RIGHT, Platform.PLATFORM_MOTION_TYPE_STATIC, x * 2,  y, world);
			        world.platforms.add(platform);
					break;
				}
				case '6': {
					Decoration tunnelBackground = new Decoration(Decoration.DECORATION_TYPE_TUNNEL_ENTRANCE_LEFT, Decoration.PLATFORM_MOTION_TYPE_STATIC, x * 2, y, world);
					world.backgroundDecoration.add(tunnelBackground);
					break;
				}
				case '7': {
					Decoration tunnelBackground = new Decoration(Decoration.DECORATION_TYPE_ROCK_TUNNEL_CENTRE, Decoration.PLATFORM_MOTION_TYPE_STATIC, x * 2, y, world);
					world.backgroundDecoration.add(tunnelBackground);
					break;
				}
				case '8': {
					Decoration tunnelBackground = new Decoration(Decoration.DECORATION_TYPE_TUNNEL_EXIT_RIGHT, Decoration.PLATFORM_MOTION_TYPE_STATIC, x * 2, y, world);
					world.backgroundDecoration.add(tunnelBackground);
					break;
				}
				case 'T': {
			        Decoration tunnel = new Decoration(Decoration.DECORATION_TYPE_TUNNEL_BACKGROUND, Decoration.PLATFORM_MOTION_TYPE_STATIC, x * 2, y, world);
			        world.backgroundDecoration.add(tunnel);
					break;
				}
				case 'C': {
		        	world.opals.add(new Opal(x * 2, y));
					break;
				}
				case 'B': {
		        	world.bats.add(new Bat(x * 2, y, world));
					break;
				}
				case 'W': {
		        	world.waftyBirds.add(new WaftyBird(x * 2, y));
					break;
				}
				case '5': {
			        Platform platform = new Platform(Platform.PLATFORM_TYPE_GRASSY_ROCK, Platform.PLATFORM_MOTION_TYPE_MOVING, x * 2, y, world);
			        world.platforms.add(platform);
					break;
				}
				case 'F':
					world.finishes.add(new Finish(x * 2, y + 1));
					break;
				case 'E':
		        	world.echidnas.add(new Echidna(x * 2, y));
					break;
				case 'S':
					world.wombatSigns.add(new GameObject(x * 2, y + 1, 2, 3));
					break;
				case '!':
					world.willy.position.set(x * 2, y);
					break;
				default:
					break;
				}
			}
		}
	}

	private String[] getLevelData(Level level) {
		return level.getLevel();
	}

	private Level getLevel(int level) {
		Level thisLevel;
		switch(level) {
		case 1:
			thisLevel = new Level1();
			break;
		case 2:
			thisLevel = new Level2();
			break;
		case 3:
			thisLevel = new Level3();
			break;
		case 4:
			thisLevel = new Level4();
			break;
		case 5:
			thisLevel = new Level5();
			break;
		case 6:
			thisLevel = new Level6();
			break;
		case 7:
			thisLevel = new Level7();
			break;
		case 8:
			thisLevel = new Level8();
			break;
		case 9:
			thisLevel = new Level9();
			break;
		case 10:
			thisLevel = new Level10();
			break;
		case 11:
			thisLevel = new Level11();
			break;
		case 12:
			thisLevel = new Level12();
			break;
		case 13:
			thisLevel = new Level13();
			break;
		case 14:
			thisLevel = new Level14();
			break;
		case 15:
			thisLevel = new Level15();
			break;
		case 16:
			thisLevel = new Level16();
			break;
		case 17:
			thisLevel = new Level17();
			break;
		case 18:
			thisLevel = new Level18();
			break;
		case 19:
			thisLevel = new Level19();
			break;
		case 20:
			thisLevel = new Level20();
			break;
		default:
			thisLevel = new Level1();
			break;
		}
		return thisLevel;
	}

}
