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

	public ClientThread(Socket socket, int id, long time) {
		this.socket = socket;
		this.id = id;
		this.time = time;
		
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
		while (true) {
			try {
				if ((message = instream.readLine()) != null) {
					if (ply == null) {
						if (message.startsWith("/login ")) {
							ply = new Player(id, message.substring(7), this);
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
				break;
			}
		}
		close();
	}
	
	public void writeMessage (String message) {
		out.println(message);
	}
	
	public void close() {
		try {
			instream.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			//TODO: Catch this error if it actually needs to be caught?
		}
	}
}
