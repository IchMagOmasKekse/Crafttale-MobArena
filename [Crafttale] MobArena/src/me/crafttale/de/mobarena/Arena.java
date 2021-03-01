package me.crafttale.de.mobarena;

import java.util.HashMap;

import org.bukkit.Location;

import me.crafttale.de.MA;

public abstract class Arena {

	protected int maxPlayers = 2;
	protected int minPlayers = 1;
	protected HashMap<Integer, Location> pSpawns = new HashMap<Integer, Location>(); // <SpawnSlot , SpawnLocation> für Spieler
	protected HashMap<Location, MobSpawner> mSpawns = new HashMap<Location, MobSpawner>(); // <SpawnSlot , SpawnLocation> für Mobs
	protected boolean isJoinable = false;
	protected Map choosenMap = Map.UNCHOSEN;
	
	public Arena(Map map) {
		this.maxPlayers = map.maxP;
		this.minPlayers = map.minP;
		this.choosenMap = map;
		this.isJoinable = false;
	}
	
	public void activate() { this.isJoinable = true; }
	public void deactivate() { this.isJoinable = false; }

	public static enum Map {
		//         Codename        Mapname     MaxPlayers   MinPlayers    Difficulty
		UNCHOSEN(  "map_unchosen", "Keine",    0,           0,            0),
		FOREST(    "map_forest",   "§2Forest",   2,           1,            0),
		DESERT(    "map_desert",   "§eWüste",   12,           1,            0),
		TEMPLE(    "map_temple",   "§8Tempel",  24,           1,            0),
		SKY(       "map_sky",      "§bHimmel",  16,           1,            0),
		ROM(       "map_rom",      "§cRom",      8,           1,            0);
		
		String codename, mapname;
		int maxP, minP, difficulty;
		
		private Map(String codename, String mapname, int maxP, int minP, int difficulty) {
			this.codename = codename;
			this.mapname = mapname;
			this.maxP = maxP;
			this.minP = minP;
			this.difficulty = difficulty;
		}

		public String getCodename() {
			return codename;
		}

		public String getMapname() {
			return mapname;
		}

		public int getMaxPlayers() {
			return maxP;
		}

		public int getMinPlayers() {
			return minP;
		}

		public int getDifficulty() {
			return difficulty;
		}
		
		public static Map randomMap() {
			int slot = MA.random.nextInt(Map.values().length);
			if(slot == 0) slot = 1;
			return Map.values()[slot];
		}
		
	}
	
}
