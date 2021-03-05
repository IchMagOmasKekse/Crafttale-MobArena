package me.crafttale.de.fileio;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.crafttale.de.MA;

public class FileIO {
	
	/**
	 * Liest eine Location aus einer Datei aus.
	 * Gibt null zurück, wenn keine Welt gefunden werden kann.
	 * Die Welt ist in dem Falle entweder gelöscht oder in der Config ist ein Falsche Weltenname eingetragen.
	 * @param key
	 * @return
	 */
	public static Location readLocation(String key) {
		return readLocation(FileType.CONFIG, key);
	}
	
	/**
	 * Liest eine Location aus einer Datei aus.
	 * Gibt null zurück, wenn keine Welt gefunden werden kann.
	 * Die Welt ist in dem Falle entweder gelöscht oder in der Config ist ein Falsche Weltenname eingetragen.
	 * @param type
	 * @param key
	 * @return
	 */
	public static Location readLocation(FileType type, String key) {
		File file = new File(type.getPath());
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		World w;
		double x,y,z;
		float yaw,pitch;
		
		w = Bukkit.getWorld(cfg.getString(key+".World"));
		if(w == null) return null;
		x = cfg.getDouble(key+".X");
		y = cfg.getDouble(key+".Y");
		z = cfg.getDouble(key+".Z");
		yaw = (float) cfg.getInt(key+".Yaw");
		pitch = (float) cfg.getInt(key+".Pitch");
		
		return new Location(w, x, y, z, yaw, pitch);
	}
	
	public static enum FileType {
		
		CONFIG("plugins/" + MA.ma().getDescription().getName() + "/config.yml");
		
		private String path = "";
		
		FileType(String path) {
			this.path = path;
		}
		
		public String getPath() { return path; }
	}
	
}
