package space.jacobschlecht.www.server;

public class Building extends Room {

	public enum buildingType {
		BLACKSMITH, WEAPONSHOP, ARMORSHOP, ENCHANTERY, LIBRARY, TOWN_HALL, DEFAULT
	}
	
	buildingType type;
	
	public Building(String name) {
		this(name, buildingType.DEFAULT);
	}
	
	public Building(String name, buildingType type) {
		super(name);
		this.type = type;
	}
	
	public boolean isBuilding() {
		return true;
	}


}
