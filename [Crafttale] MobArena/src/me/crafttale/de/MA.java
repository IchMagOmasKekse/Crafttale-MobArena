package me.crafttale.de;

import java.util.Random;

import org.bukkit.plugin.java.JavaPlugin;

import me.crafttale.de.commands.SimCommands;
import me.crafttale.de.mobarena.LobbyManager;
import me.crafttale.de.mobarena.Simulator;

public class MA extends JavaPlugin {

	private static MA ma;
	public static MA ma() { return ma; }
	public static Random random = new Random();
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
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
		
	}
	public void init() {
		new SimCommands();
	}
	public void postInit() {
		new LobbyManager();
		Simulator.simulateLobbyJoining(10, 30);
	}

}
