package me.crafttale.de.mobarena;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.block.Sign;

import me.crafttale.de.Cuboid;
import me.crafttale.de.MA;
import me.crafttale.de.fileio.FileIO;

public class LobbyManager {
	
	private static HashMap<Integer, Sign> signs = new HashMap<Integer, Sign>();
	private static HashMap<Integer, Lobby> lobbys = new HashMap<Integer, Lobby>();
	private static Location signWallPos1, signWallPos2;
	private static Cuboid signWall;
	public static String lobbySignPrefix = "§bLobby §3# §b";
	
	public LobbyManager() {
		Lobby lobby = null;
		for(int i = 0; i != 15; i++) {
			lobby = new Lobby(new PlayArena());
			lobbys.put(lobby.getId(), lobby);
		}
		
		signWallPos1 = FileIO.readLocation("Locations.LobbyWall.Wall A.Position 1");
		signWallPos2 = FileIO.readLocation("Locations.LobbyWall.Wall A.Position 2");
		signWall = new Cuboid(signWallPos1, signWallPos2);
		loadSigns();
	}
	
	private static void loadSigns() {
		signs.clear();
		signs = signWall.loadSigns();
		for(int slot : signs.keySet()) {
			if(signs.get(slot).getBlock().getState() instanceof Sign) {
				if(lobbys.size() <= slot) {				
					signs.get(slot).setLine(0, "KEINE");
					signs.get(slot).setLine(1, "LOBBY");
					signs.get(slot).setLine(2, "GEFUNDEN");
					signs.get(slot).setLine(3, "--------");				
				}else {
					signs.get(slot).setLine(0, lobbySignPrefix+lobbys.get(slot).getId());
					signs.get(slot).setLine(1, "§a"+lobbys.get(slot).getArenaName());
					signs.get(slot).setLine(2, "§a"+lobbys.get(slot).playerAmount()+"§7/§a"+lobbys.get(slot).getMaxPlayers());
					signs.get(slot).setLine(3, Lobby.status);				
				}
				signs.get(slot).update(true);
			}
		}
	}
	
	public static void updateSign(Lobby lobby) {
		Sign sign = signs.get(lobby.getId());
		int slot = lobby.getId();
		if(signs.get(slot) != null && signs.get(slot).getBlock() != null && signs.get(slot).getBlock().getState() != null) {
			if(signs.get(slot).getBlock().getState() instanceof Sign) {
				if(lobbys.size() <= slot) {				
					sign.setLine(0, "KEINE");
					sign.setLine(1, "LOBBY");
					sign.setLine(2, "GEFUNDEN");
					sign.setLine(3, "--------");				
				}else {
					sign.setLine(0, lobbySignPrefix+lobbys.get(slot).getId());
					sign.setLine(1, "§a"+lobbys.get(slot).getArenaName());
					sign.setLine(2, "§a"+lobbys.get(slot).playerAmount()+"§7/§a"+lobbys.get(slot).getMaxPlayers());
					sign.setLine(3, Lobby.status);				
				}
				sign.update(true);
			}
		}
	}
	
	public static int getRandomID() {
		return lobbys.size();
	}
	public static Lobby getRandomLobby() {
		return lobbys.get(MA.random.nextInt(lobbys.size()-1));
	}
	public static Lobby getLobby(int id) {
		if(lobbys.containsKey(id)) return lobbys.get(id);
		return null;
	}
	
	/**
	 * Lädt LobbyWalls und Einstellungen neu.
	 */
	public static void reload() {
		signWallPos1 = FileIO.readLocation("Locations.LobbyWall.Wall A.Position 1");
		signWallPos2 = FileIO.readLocation("Locations.LobbyWall.Wall A.Position 2");
		signWall = new Cuboid(signWallPos1, signWallPos2);
		loadSigns();
	}

}
