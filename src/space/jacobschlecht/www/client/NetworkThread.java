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
		
		while (true) {
			String message;
			try {
				if ((message = instream.readLine()) != null) {
					client.console.appendText(message + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		close();
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
	
	public void sendMessageToServer(String message) {
		out.println(message);
	}
	
}
