package space.jacobschlecht.www.server;

public class World extends Room {
	
	public World(String name) {
		super(name);
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
}
