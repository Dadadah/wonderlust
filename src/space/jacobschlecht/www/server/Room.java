package space.jacobschlecht.www.server;

import java.util.ArrayList;

public class Room {
	
	protected ArrayList<Player> players;
	private String name;

	public Room(String name) {
		this.name = name;
		players = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}

	public void addPlayer(Player player) {
		if (!this.isWorld()) player.setRoom(this);
		players.add(player);
	}

	public void removePlayer(Player player) {
		if (!this.isWorld()) player.setRoom(null);
		players.remove(player);
	}
	
	public void sendMessage(Player ply, String message) {
		String formattedMessage;
		if (this.isWorld()) formattedMessage = "Global> " + ply.getUsername() + ": " + message;
		else formattedMessage = ply.getUsername() + ": " + message;
		for (Player ply1 : players) {
			ply1.sendMessage(formattedMessage);
		}
	}

	
	public void sendMessage(String username, String message) {
		String formattedMessage = username + ": " + message;
		for (Player ply : players) {
			ply.sendMessage(formattedMessage);
		}
	}
	
	public boolean isWorld() {
		return false;
	}
	
	public boolean isDungeon() {
		return false;
	}
	
	public boolean isTown() {
		return false;
	}
	
	public boolean isBuilding() {
		return false;
	}



}
