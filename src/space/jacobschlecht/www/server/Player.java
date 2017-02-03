package space.jacobschlecht.www.server;

import space.jacobschlecht.www.server.commands.BaseCommand;

public class Player {

	private ClientThread network;
	private World world;
	private int id;
	private String username;
	private Room curRoom;
	
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
		if (curRoom != null) curRoom.removePlayer(this);
		world.removePlayer(this);
		network.close();
	}
	
	// GETTERS
	
	public World getWorld() {
		return world;
	}
	
	public ClientThread getNetwork() {
		return network;
	}
	
	public int getID() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public Room getRoom() {
		return curRoom;
	}
	
	// SETTERS
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void setRoom(Room room) {
		if (!room.isWorld()) {
			this.curRoom = room;
		}
	}

}
