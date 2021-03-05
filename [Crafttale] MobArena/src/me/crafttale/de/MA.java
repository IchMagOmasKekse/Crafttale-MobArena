package me.crafttale.de;

import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.crafttale.de.commands.AdminCommands;
import me.crafttale.de.commands.SimCommands;
import me.crafttale.de.events.JoinAndQuit;
import me.crafttale.de.events.SignEvents;
import me.crafttale.de.mobarena.LobbyManager;

public class MA extends JavaPlugin {

	private static MA ma;
	public static MA ma() { return ma; }
	public static Random random = new Random();
	
	@Override
	public void onDisable() {
		Profiler.disable();
		super.onDisable();
	}
	
	@Override
	public void onEnable() {
		ma = this;
		preInit();
		init();
		postInit();
		
		super.onEnable();
	}
	
	public void preInit() {
		saveResource("config.yml", false);
	}
	public void init() {
		new SimCommands();
	}
	public void postInit() {
		/* Commands */
		new AdminCommands();
		
		/* Events */
		new SignEvents();
		new JoinAndQuit();
		
		/* Manager */
		new LobbyManager();
		
	}

	/**
	 * TODO: Benutzerdefinierte Permission-Abfrage
	 * 
	 * - Gibt eine benutzerdefinierte non-permission Message aus.
	 * - Diese Methode spart einige Zeilen an Code.
	 * @return
	 */
	public static boolean hasPermission(CommandSender p, String permission) {
		if(p.hasPermission(permission)) return true;
		else {
			if(p instanceof Player) sendNoPermissionTitle((Player)p);
			return false;
		}
	}
    /**
     * Sendet einen Title an ein Spieler, wenn er keine Permission zur getätigten Handlung hat
     * @param p
     */
    public static void sendNoPermissionTitle(Player p) {
    	p.sendTitle("", "§4§l✖", 2, 10, 1);
    }
	
}
