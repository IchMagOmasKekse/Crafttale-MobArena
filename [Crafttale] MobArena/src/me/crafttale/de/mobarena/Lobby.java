package me.crafttale.de.mobarena;

import java.util.HashMap;

import org.bukkit.entity.Entity;

import me.crafttale.de.MA;

public class Lobby {

	private int id = LobbyManager.getRandomID();
	private Arena arena;
	public String status = "[JOIN]";
	private HashMap<Entity, Boolean> players = new HashMap<Entity, Boolean>(); // <Spieler , HatKitAusgewählt?>
	
	public Lobby(Arena arena) {
		this.arena = arena;
	}
	
	public boolean join(Entity entity) {
		if(isFull()) return false;
		
		players.put(entity, false);
		if(isFull()) status = "..in game..";
		LobbyManager.updateSign(this);
		return true;
	}
	public boolean quit(Entity entity) {
		if(players.containsKey(entity) == false) return false;
		
		players.remove(entity);
		if(isFull()) status = "..in game..";
		else status = "[JOIN]";
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
