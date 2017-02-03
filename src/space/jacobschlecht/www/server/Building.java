package space.jacobschlecht.www.server;

public class Building extends Room {

	public Building(String name) {
		super(name);
	}
	
	public boolean isBuilding() {
		return true;
	}


}
