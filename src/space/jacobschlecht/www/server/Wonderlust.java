package space.jacobschlecht.www.server;

public class Wonderlust {
	
	public static ServerThread server;

	public static void main(String[] args) {
		server = new ServerThread();
		server.start();
	}
}
