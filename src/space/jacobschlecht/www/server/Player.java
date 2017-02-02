package space.jacobschlecht.www.server;

import space.jacobschlecht.www.server.commands.BaseCommand;

public class Player {

	ClientThread network;
	public World world;
	int id;
	String username;
	public Room curRoom;
	
	public Player(int id, String username, ClientThread network, World world) {
		this.id = id;
		this.username = username;
		this.network = network;
		this.world = world;
		world.addPlayer(this);
	}
	
	public void sentMessage(String message) {
		BaseCommand.executeCommand(message, this);
	}
	
	public void sendMessage(String message) {
		network.writeMessage(message);
	}
	
	public void disconnect() {
		if (curRoom != null && curRoom != world) curRoom.removePlayer(this);
		world.removePlayer(this);
		network.close();
	}

}
