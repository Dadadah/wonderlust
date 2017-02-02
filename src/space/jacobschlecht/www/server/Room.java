package space.jacobschlecht.www.server;

import java.util.ArrayList;

public class Room {
	
	private ArrayList<Player> players;
	String name;

	public Room(String name) {
		this.name = name;
		players = new ArrayList<>();
	}

	public void addPlayer(Player player) {
		player.curRoom = this;
		players.add(player);
	}

	public void removePlayer(Player player) {
		players.remove(player);
	}
	
	public void sendMessage(String username, String message) {
		String formattedMessage = username + ": " + message;
		for (Player ply : players) {
			ply.sendMessage(formattedMessage);
		}
	}

}
