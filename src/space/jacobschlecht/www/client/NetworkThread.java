package space.jacobschlecht.www.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetworkThread extends Thread {

	Socket socket;
	BufferedReader instream;
	InputThread input;

	public void run() {
		try {
			socket = new Socket("localhost", 11100);
			input = new InputThread(socket);
			input.start();
		} catch (Exception e) {
			System.out.println("Error in NetworkThread: " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		System.out.println("Connected to server.");
		
		try {
			instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (Exception e) {
			System.out.println("Exception in NetworkThread: " + e.getMessage());
			e.printStackTrace();
		}
		
		while (true) {
			String message;
			try {
				if ((message = instream.readLine()) != null) {
					System.out.println(message);
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
			input.close();
			instream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			//TODO: Catch this error if it actually needs to be caught?
		}
	}
	
}
