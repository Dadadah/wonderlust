package space.jacobschlecht.www.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkThread extends Thread {

	Socket socket;
	BufferedReader instream;
	PrintWriter out;
	WonderlustClient client;
	boolean connected = true;
	
	public NetworkThread(WonderlustClient client) {
		this.client = client;
	}

	public void run() {
		try {
			socket = new Socket("localhost", 11100);
		} catch (Exception e) {
			System.out.println("Error in NetworkThread: " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		System.out.println("Connected to server.");
		
		try {
			instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch (Exception e) {
			System.out.println("Exception in NetworkThread: " + e.getMessage());
			e.printStackTrace();
		}
		
		while (connected) {
			String message;
			try {
				instream.mark(8);
				if (instream.read() == -1) connected = false;
				else instream.reset();
				if ((message = instream.readLine()) != null) {
					client.console.appendText(message + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		close();
		client.console.appendText("You have been disconnected. Please restart the program to reconnect.");
	}
	
	public void close() {
		try {
			connected = false;
			instream.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			//TODO: Catch this error if it actually needs to be caught?
		}
	}
	
	public void sendMessageToServer(String message) {
		out.println(message);
	}
	
}
