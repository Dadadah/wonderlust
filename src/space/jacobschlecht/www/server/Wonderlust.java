package space.jacobschlecht.www.server;

public class Wonderlust {
	
	public static ServerThread server;

	public static void main(String[] args) {
		World world = new World("Default");
		server = new ServerThread(world);
		server.start();
	}
}
