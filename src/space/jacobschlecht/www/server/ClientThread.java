package space.jacobschlecht.www.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	
	Socket socket;
	BufferedReader instream;
	PrintWriter out;
	int id;
	String username;
	long time;
	String message;

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
		while (true) {
			try {
				if ((message = instream.readLine()) != null) {
					//parseMessage(message);
					// This is temporary
					if (message.startsWith("/setusername ")) {
						username = message.substring(13);
					} else if (message.equals("/disconnect")) {
						break;
					} else {
						Wonderlust.server.sendMessage(message, username);
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
