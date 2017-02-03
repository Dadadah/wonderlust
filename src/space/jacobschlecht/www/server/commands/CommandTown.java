package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;
import space.jacobschlecht.www.server.Room;
import space.jacobschlecht.www.server.Town;
import space.jacobschlecht.www.server.World;

public class CommandTown extends BaseCommand {

	public String execute(String[] args, Player ply) {
		
		Room room = ply.getRoom();
		World world = ply.getWorld();
		
		if (room.isDungeon()) return "You can't teleport to a town from a dungeon.";
		Town town = world.getTownByName(ply, args[1]);
		if (town != null) {
			ply.setRoom(town);
		} else {
			return "Town not found: " + args[1];
		}
		
		return "You have teleported to " + args[1];
	}

}
