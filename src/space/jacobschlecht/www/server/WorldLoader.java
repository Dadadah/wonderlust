package space.jacobschlecht.www.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WorldLoader {
	
	private static BufferedReader reader;
	
	public static World loadWorld(String worldName) {
		
		World world = new World(worldName);
		
		File worldDir = new File(worldName);
		
		if (worldDir.isDirectory()) {
			File townDir = new File(worldName + "/Towns");
			File dunDir = new File(worldName + "/Dungeons");
			if (townDir.isDirectory() && dunDir.isDirectory()) {
				world.setTowns(parseTowns(townDir, worldName));
				world.setDungeons(parseDungeons(dunDir, worldName));
			}
		}
		
		return world;
	}

	private static ArrayList<Town> parseTowns(File townDir, String worldName) {
		ArrayList<Town> towns = new ArrayList<>();
		
		String[] townDirs = townDir.list();
		
		for (int i = 0; i < townDirs.length; i++) {
			File tempTownDir = new File(worldName + "/Towns/" + townDirs[i]);
			Town tempTown = new Town(townDirs[i]);
			if (tempTownDir.isDirectory()) {
				tempTown.setBuildings(parseBuildings(new File(worldName + "/Towns/" + townDirs[i] + "/Buildings/"), worldName + townDirs[i]));
			}
			towns.add(tempTown);
		}
		
		return towns;
	}

	private static ArrayList<Dungeon> parseDungeons(File dunDir, String worldName) {
		ArrayList<Dungeon> duns = new ArrayList<>();
		
		String[] dunDirs = dunDir.list();
		
		for (int i = 0; i < dunDirs.length; i++) {
			File tempDunDir = new File(worldName + "/Dungeons/" + dunDirs[i]);
			Dungeon tempDun = new Dungeon(dunDirs[i]);
			if (tempDunDir.isDirectory()) {
				tempDun = parseDungeon(tempDunDir, dunDirs[i]);
			}
			duns.add(tempDun);
		}
		
		return duns;
	}
	
	private static Dungeon parseDungeon(File dunDir, String dunName) {
		try {
			reader = new BufferedReader(new FileReader(new File(dunDir.getAbsolutePath() + "ab.txt")));

			String dunPrefix = reader.readLine();
			reader.close();
			Dungeon dun = new Dungeon(dunPrefix + "1");
			
			
			
			return dun;
		} catch (FileNotFoundException e) {
			System.out.println("ab.txt not found for dungeon " + dunName);
			return new Dungeon(dunName);
		} catch (IOException e) {
			System.out.println("IO Exception found for dungeon " + dunName);
			return new Dungeon(dunName);
		}
	}
	
	private static ArrayList<Building> parseBuildings(File buildDir, String townDirName) {
		ArrayList<Building> buildings = new ArrayList<>();
		
		File[] buildingFiles = buildDir.listFiles();
		for (int i = 0; i < buildingFiles.length; i++) {
			String fileName = buildingFiles[i].getName();
			Building.buildingType type = Building.buildingType.DEFAULT;
			if (fileName.equals("blacksmith")) {
				type = Building.buildingType.BLACKSMITH;
			} else if (fileName.equals("townhall")) {
				type = Building.buildingType.TOWN_HALL;
			} else if (fileName.equals("weaponshop")) {
				type = Building.buildingType.WEAPONSHOP;
			} else if (fileName.equals("armorshop")) {
				type = Building.buildingType.ARMORSHOP;
			} else if (fileName.equals("enchantery")) {
				type = Building.buildingType.ENCHANTERY;
			} else if (fileName.equals("library")) {
				type = Building.buildingType.LIBRARY;
			}
			buildings.add(new Building(townDirName + fileName, type));
		}
		
		return buildings;
	}
}
