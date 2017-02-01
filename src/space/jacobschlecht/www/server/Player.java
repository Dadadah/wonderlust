package space.jacobschlecht.www.server;

public class Player {

	ClientThread network;
	int id;
	String username;
	Room curRoom;
	
	public Player(int id, String username, ClientThread network) {
		this.id = id;
		this.username = username;
		this.network = network;
	}
	
	public void sentMessage(String message) {
		//parseMessage(message);
		//This is temporary
		if (message.equals("/disconnect")) {
			network.close();
		} else if (message.startsWith("/g ")) {
			Wonderlust.server.sendGlobalMessage(username, message);
		 }else {
			curRoom.sendMessage(username, message);
		}
	}
	
	public void sendMessage(String message) {
		network.writeMessage(message);
	}

}
