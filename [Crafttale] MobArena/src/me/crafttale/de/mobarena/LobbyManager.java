package me.crafttale.de.mobarena;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import me.crafttale.de.Cuboid;
import me.crafttale.de.MA;

public class LobbyManager {
	
	private static HashMap<Integer, Sign> signs = new HashMap<Integer, Sign>();
	private static HashMap<Integer, Lobby> lobbys = new HashMap<Integer, Lobby>();
	private Location signWallPos1, signWallPos2;
	private Cuboid signWall;
	
	public LobbyManager() {
		Lobby lobby = null;
		for(int i = 0; i != 15; i++) {
			lobby = new Lobby(new PlayArena());
			lobbys.put(lobby.getId(), lobby);
		}
		
		signWallPos1 = new Location(Bukkit.getWorld("world"), 85, 55, 174);
		signWallPos2 = new Location(Bukkit.getWorld("world"), 89, 53, 174);
		signWall = new Cuboid(signWallPos1, signWallPos2);
		loadSigns();
	}
	
	public void loadSigns() {
		signs = signWall.loadSigns();
		for(int slot : signs.keySet()) {
			if(signs.get(slot).getBlock().getState() instanceof Sign) {
				if(lobbys.size() <= slot) {				
					signs.get(slot).setLine(0, "KEINE");
					signs.get(slot).setLine(1, "LOBBY");
					signs.get(slot).setLine(2, "GEFUNDEN");
					signs.get(slot).setLine(3, "--------");				
				}else {
					signs.get(slot).setLine(0, "§9Lobby # "+lobbys.get(slot).getId());
					signs.get(slot).setLine(1, "§a"+lobbys.get(slot).getArenaName());
					signs.get(slot).setLine(2, "§a"+lobbys.get(slot).playerAmount()+"§7/§a"+lobbys.get(slot).getMaxPlayers());
					signs.get(slot).setLine(3, "[JOIN]");				
				}
				signs.get(slot).update(true);
			}
		}
	}
	
	public static void updateSign(Lobby lobby) {
		Sign sign = signs.get(lobby.getId());
		int slot = lobby.getId();
		if(signs.get(slot).getBlock().getState() instanceof Sign) {
			if(lobbys.size() <= slot) {				
				sign.setLine(0, "KEINE");
				sign.setLine(1, "LOBBY");
				sign.setLine(2, "GEFUNDEN");
				sign.setLine(3, "--------");				
			}else {
				sign.setLine(0, "§9Lobby # "+lobbys.get(slot).getId());
				sign.setLine(1, "§a"+lobbys.get(slot).getArenaName());
				sign.setLine(2, "§a"+lobbys.get(slot).playerAmount()+"§7/§a"+lobbys.get(slot).getMaxPlayers());
				sign.setLine(3, lobbys.get(slot).status);				
			}
			sign.update(true);
		}
	}
	
	public static int getRandomID() {
		return lobbys.size();
	}
	public static Lobby getRandomLobby() {
		return lobbys.get(MA.random.nextInt(lobbys.size()-1));
	}

}
