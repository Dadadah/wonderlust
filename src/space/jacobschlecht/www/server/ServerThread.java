package space.jacobschlecht.www.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {

	ServerSocket serversocket;
	private World world;
	ArrayList<ClientThread> clients;
	int idTicker = 0;
	boolean online = true;
	
	public ServerThread(World world) {
		clients = new ArrayList<>();
		this.world = world;
	}
	
	public void run() {
		try {
			serversocket = new ServerSocket(11100);
			
			while (online) {
				System.out.println("Waiting on clients...");
				Socket socket = serversocket.accept();
				if (!online) break;
				ClientThread client = new ClientThread(socket, idTicker++, System.currentTimeMillis(), world);
				clients.add(client);
				client.start();
			}
			
			try {
				serversocket.close();
				for (ClientThread c : clients) {
					c.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Exception in ServerThread: " + e);
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void stopServer() {
		online = false;
		
		try {
            new Socket("localhost", 11100);
        }
        catch(Exception e) {
			e.printStackTrace();
		}

	}

}
