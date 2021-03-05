package me.crafttale.de.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.crafttale.de.MA;
import me.crafttale.de.Profiler;

public class JoinAndQuit implements Listener {

	public JoinAndQuit() {
		MA.ma().getServer().getPluginManager().registerEvents(this,  MA.ma());
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		Profiler.registerProfile(e.getPlayer());
	}
	
}