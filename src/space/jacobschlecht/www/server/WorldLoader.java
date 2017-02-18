package space.jacobschlecht.www.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WorldLoader {
	
	private static BufferedReader reader;
	
	public static World loadWorld(String worldName) {
		
		World world = new World("worldName");
		
		File worldDir = new File(worldName);
		
		if (worldDir.isDirectory()) {
			File townDir = new File(worldName + "/Towns");
			File dunDir = new File(worldName + "/Dungeons");
			if (townDir.isDirectory() && dunDir.isDirectory()) {
				parseTowns(townDir, worldName);
				parseDungeons(dunDir, worldName);
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
				
			}
		}
		
		return towns;
	}

	private static ArrayList<Dungeon> parseDungeons(File dunDir, String worldName) {
		ArrayList<Dungeon> duns = new ArrayList<>();
		
		return duns;
	}
}
