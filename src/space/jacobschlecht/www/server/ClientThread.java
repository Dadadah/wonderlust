package space.jacobschlecht.www.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	
	private Socket socket;
	private BufferedReader instream;
	private PrintWriter out;
	private int id;
	private long time;
	private String message;
	private Player ply;
	private boolean connected = true;
	private World world;

	public ClientThread(Socket socket, int id, long time, World world) {
		this.socket = socket;
		this.id = id;
		this.time = time;
		this.world = world;
		
		try {
			instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch (Exception e) {
			System.out.println("Exception in ClientThread: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void run() {
		writeMessage("Please login!");
		writeMessage("Just use /login username for now");
		while (connected) {
			try {
				if ((message = instream.readLine()) != null) {
					if (ply == null) {
						if (message.startsWith("/login ")) {
							if (world.getPlayerByUsername(message.substring(7)) == null) 
								ply = new Player(id, message.substring(7), this, world);
							else writeMessage("Username taken, try again.");
						} else {
							writeMessage("Please login!");
							writeMessage("Just use /login username for now");
						}
					} else {
						ply.sentMessage(message);
					}
				}
			} catch (IOException e) {
				System.out.println("IO Exception in ClientThread: " + e.getMessage());
				e.printStackTrace();
				ply.disconnect();
				break;
			}
		}
		ply.disconnect();
	}
	
	public void writeMessage (String message) {
		out.println(message);
	}
	
	public void close() {
		try {
			connected = false;
			instream.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			//TODO: Catch this error if it actually needs to be caught?
		}
	}
}
