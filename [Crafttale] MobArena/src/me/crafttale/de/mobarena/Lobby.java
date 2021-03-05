package me.crafttale.de.mobarena;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import me.crafttale.de.Cuboid;
import me.crafttale.de.MA;
import me.crafttale.de.Profiler;
import me.crafttale.de.fileio.FileIO;

public class Lobby {

	private int id = LobbyManager.getRandomID();
	private Arena arena;
	public static String status = "§7[§aJOIN§7]";
	private HashMap<Entity, Boolean> players = new HashMap<Entity, Boolean>(); // <Spieler , HatKitAusgewählt?>
	private Location spawn;
	private Cuboid spawnRegion;
	
	public Lobby(Arena arena) {
		this.arena = arena;
		spawnRegion = new Cuboid(FileIO.readLocation("Locations.Arena Lobby.Position 1"), FileIO.readLocation("Locations.Arena Lobby.Position 2"));
		spawn = spawnRegion.getRandomLocation();
	}
	
	public boolean join(Entity entity) {
		if(isFull() || Profiler.getProfile(entity).getLobby() != null) return false;
		Profiler.getProfile(entity).setLobby(this);
		players.put(entity, false);
		entity.teleport(spawn);
		if(isFull()) status = "§c..in game..";
		LobbyManager.updateSign(this);
		return true;
	}
	public boolean quit(Entity entity) {
		if(players.containsKey(entity) == false) return false;
		
		players.remove(entity);
		if(isFull()) status = "§c..in game..";
		else status = "§7[§aJOIN§7]";
		LobbyManager.updateSign(this);
		return true;
	}
	
	public int getId() {
		return id;
	}
	public int playerAmount() {
		return players.size();
	}
	public int getMaxPlayers() {
		return arena.maxPlayers;
	}
	public String getArenaName() {
		return arena.choosenMap.getMapname();
	}
	
	public String startIfReady() {
		for(boolean b : players.values()) 
			if(b == false) return "§cAlle Spieler müssen sich ein Kit aussuchen.";
		return "§aStart in 10 Sekunden.";
	}
	public boolean isFull() {
		return (players.size() == arena.maxPlayers ? true : false);
	}
	
	public Entity getRandomPlayer() {
		if(players.size() != 0) {
			for(Entity ent : players.keySet()) {
				if(MA.random.nextInt(2) == 1) {
					return ent;
				}
			}
			for(Entity ent : players.keySet()) return ent;
		}
		return null;
	}

}
