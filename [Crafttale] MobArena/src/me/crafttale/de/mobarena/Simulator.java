package me.crafttale.de.mobarena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

import me.crafttale.de.MA;

public class Simulator {

	
	private static HashMap<String, BukkitRunnable> timers = new HashMap<String, BukkitRunnable>();
	private static HashMap<UUID, Entity> simEntity = new HashMap<UUID, Entity>();
	private static ArrayList<UUID> joinEntity = new ArrayList<UUID>();
	
	private static int secondsForJoining = 0;
	
	public Simulator() {
		
	}
	
	public static boolean processSimulateStart(String codename) {
		if(codename.equalsIgnoreCase("joins")) {
			simulateLobbyJoining(0, 30);
			return true;
		}
		
		return false;
	}
	public static boolean processSimulateStop(String codename) {
		if(codename.equalsIgnoreCase("joins")) {
			if(timers.containsKey("joins")) {
				timers.get("joins").cancel();
				timers.remove("joins");
				for(Player p : Bukkit.getOnlinePlayers()) p.sendMessage("§7Join §9Simulation §7stopped");
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * Simuliert Spieler-Joins für eine angegebene Zeit(in Sekunden).
	 * @param duration
	 */
	public static void simulateLobbyJoining(int delay, int duration) {
		if(timers.containsKey("joins")) return;
		secondsForJoining = 0;
		for(Player p : Bukkit.getOnlinePlayers()) p.sendMessage("§7Join §9Simulation §7is starting(Del.:"+delay+" Dur.:"+duration+")");
		timers.put("joins", new BukkitRunnable() {
			int counts = 0;
			@Override
			public void run() {
				if(secondsForJoining == 20 && counts < duration) {
					counts++;
					secondsForJoining = 0;
				}else if(counts >= duration) {
					for(UUID uuid : joinEntity) {
						simEntity.get(uuid).remove();
						simEntity.remove(uuid);
					}
					joinEntity.clear();
					timers.get("joins").cancel();
					timers.remove("joins");
					for(Player p : Bukkit.getOnlinePlayers()) p.sendMessage("§7Join §9Simulation §7is over");
					return;
				}else secondsForJoining++;
				
				Lobby lobby = LobbyManager.getRandomLobby();
				
				if(MA.random.nextBoolean()) {
					if(lobby.join(simEntity.get(createSimEntity()))) {
						for(Player p : Bukkit.getOnlinePlayers()) p.sendMessage("§9Simulator: §aJoined §7in Lobby#"+lobby.getId());
					}else for(Player p : Bukkit.getOnlinePlayers()) p.sendMessage("§9Simulator: §cLOBBY IS FULL §7Lobby#"+lobby.getId());
				}else {
					Entity player = lobby.getRandomPlayer();
					if(player == null) {
						for(Player p : Bukkit.getOnlinePlayers()) p.sendMessage("§9Simulator: §cQuited§7(§cERROR: §7EmptyLobby#"+lobby.getId()+")");
					}else {
						lobby.quit(player);
						for(Player p : Bukkit.getOnlinePlayers()) p.sendMessage("§9Simulator: §cQuited §7Lobby#"+lobby.getId());
					}
				}
			}
		});
		timers.get("joins").runTaskTimer(MA.ma(), (long)delay*20, (long)1l);
	}
	
	private static UUID createSimEntity() {
		Zombie z = (Zombie) Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 0, 200, 0), EntityType.ZOMBIE);
		z.setInvulnerable(true);
		z.setAI(false);
		z.setGravity(false);
		z.setCustomNameVisible(true);
		z.setCustomName("§9SIMULATION §7Player");
		simEntity.put(z.getUniqueId(), z);
		joinEntity.add(z.getUniqueId());
		return z.getUniqueId();
	}

}
