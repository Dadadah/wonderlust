package space.jacobschlecht.www.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class InputThread extends Thread {

	Socket socket;
	BufferedReader input;
	PrintWriter out;
	
	public InputThread(Socket socket) {
		this.socket = socket;
		input = new BufferedReader(new InputStreamReader(System.in));
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {

		while (true) {
		String message;
			try {
				if ((message = input.readLine()) != null) {
					out.println(message);
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
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
