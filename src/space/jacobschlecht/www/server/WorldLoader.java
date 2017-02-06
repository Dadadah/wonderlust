package space.jacobschlecht.www.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WorldLoader {
	
	private static BufferedReader reader;
	
	private static String[] readFile(String worldName) throws IOException {
		reader = new BufferedReader(new FileReader(new File(worldName)));
		String line;
		ArrayList<String> lines = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			if (line.matches(" \t+")) continue;
			lines.add(line);
		}
		return lines.toArray(new String[lines.size()]);
	}
	
	private static Building[] parseBuildings();
	
	private static Town[] parseTowns(String[] townInstructions) {
		ArrayList<Town> towns = new ArrayList<>();
		Town tempTown;
		
		for (int i = 0; i < townInstructions.length; i++) {
			
		}
		
		return towns.toArray(new Town[towns.size()]);
	}
	
	public static World loadWorld(String worldName) {
		World world;
		String[] fileContents;
		try {
			fileContents = readFile(worldName);
		} catch (IOException e) {
			return null;
		}
		String[] townInstructions = new String[fileContents.length];
		for (int i = 2; i < fileContents.length; i++) {
			if (fileContents[i].contains("Dungeons:")) {
				break;
			} else {
				townInstructions[i-2] = fileContents[i];
			}
		}
		Town[] towns = parseTowns(townInstructions);
		world = new World("Default");
		return world;
	}

}
