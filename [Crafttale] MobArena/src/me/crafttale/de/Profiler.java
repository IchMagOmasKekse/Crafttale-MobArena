package me.crafttale.de;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Entity;

import me.crafttale.de.mobarena.Lobby;

public class Profiler {
	
	private static HashMap<Entity, PlayerProfile> profiles = new HashMap<Entity, PlayerProfile>();
	
	/**
	 * Registriert ein neues Profil.
	 * @param ent
	 * @return
	 */
	public static boolean registerProfile(Entity ent) {
		if(profiles.containsKey(ent)) return false;
		profiles.put(ent, new PlayerProfile(ent));
		return true;
	}
	
	/**
	 * Gibt das Profil zurück.
	 * @param ent
	 * @return
	 */
	public static PlayerProfile getProfile(Entity ent) {
		if(profiles.containsKey(ent) == false) registerProfile(ent);
		
		return profiles.get(ent);
	}
	
	/**
	 * Schließt alle Profile ohne zu speichern.
	 */
	public static void disable() {
		profiles.clear();
	}
	
	
	public static class PlayerProfile {
		
		private Entity entity;
		private UUID uuid;
		private Lobby lobby;
		public PlayerProfile(Entity entity) {
			super();
			this.entity = entity;
			this.uuid = entity.getUniqueId();
		}
		public void setLobby(Lobby lobby) {
			this.lobby = lobby;
		}
		public Entity getEntity() {
			return entity;
		}
		public UUID getUuid() {
			return uuid;
		}
		public Lobby getLobby() {
			return lobby;
		}
		
		
		
	}
	
}
