package space.jacobschlecht.www.server;

import java.util.ArrayList;

public class World extends Room {
	
	private ArrayList<Town> towns;
	private ArrayList<Dungeon> dungeons;
	
	public World(String name) {
		super(name);
		towns = new ArrayList<>();
	}
	
	@Override
	public boolean isWorld() {
		return true;
	}
	
	public Player getPlayerByUsername(String name) {
		for (Player ply : players) {
			if (ply.getUsername().equals(name)) return ply;
		}
		return null;
	}
	
	public Player getPlayerByUsername(Player caller, String name) {
		for (Player ply : players) {
			if (ply.getUsername().equals(name)) return ply;
		}
		caller.sendMessage("Player " + name + " not found.");
		return null;
	}
	
	public Town getTownByName(Player caller, String name) {
		for (Town t : towns) {
			if (t.getName().equals(name)) return t;
		}
		return null;
	}
	
	public Dungeon getDungeonByName(Player caller, String name) {
		for (Dungeon d : dungeons) {
			if (d.getName().equals(name)) return d;
		}
		return null;
	}
}
