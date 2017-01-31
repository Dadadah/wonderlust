package space.jacobschlecht.www.client;

public class WonderlustClient {
	
	public static NetworkThread client;

	public static void main(String[] args) {
		client = new NetworkThread();
		client.start();
	}
}
