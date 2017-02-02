package space.jacobschlecht.www.server;

public class Player {

	ClientThread network;
	World world;
	int id;
	String username;
	Room curRoom;
	
	public Player(int id, String username, ClientThread network, World world) {
		this.id = id;
		this.username = username;
		this.network = network;
		this.world = world;
		world.addPlayer(this);
	}
	
	public void sentMessage(String message) {
		//parseMessage(message);
		//This is temporary
		if (message.equals("/disconnect")) {
			network.close();
		} else if (message.startsWith("/g ")) {
			world.sendMessage(username, message.substring(3));
		 }else {
			if (curRoom != null) curRoom.sendMessage(username, message);
		}
	}
	
	public void sendMessage(String message) {
		network.writeMessage(message);
	}
	
	public void disconnect() {
		if (curRoom != null) curRoom.removePlayer(this);
		world.removePlayer(this);
	}

}
