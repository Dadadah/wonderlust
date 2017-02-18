package space.jacobschlecht.www.server;

import java.util.ArrayList;

public class Town extends Room {

	private ArrayList<Building> buildings;
	
	public Town(String name) {
		super(name);
		buildings = new ArrayList<>();
	}
	
	public boolean isTown() {
		return true;
	}
	
	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

}
