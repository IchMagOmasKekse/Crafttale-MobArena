package me.crafttale.de.events;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.crafttale.de.MA;
import me.crafttale.de.Profiler;
import me.crafttale.de.mobarena.LobbyManager;

public class SignEvents implements Listener {

	public SignEvents() {
		MA.ma().getServer().getPluginManager().registerEvents(this,  MA.ma());
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).contains(LobbyManager.lobbySignPrefix)) {
					if(Profiler.getProfile(e.getPlayer()).getLobby() != null) {
						int lobbyId = Integer.parseInt(sign.getLine(0).replace(LobbyManager.lobbySignPrefix, ""));
						if(LobbyManager.getLobby(lobbyId).join(e.getPlayer())) e.getPlayer().sendMessage(LobbyManager.lobbySignPrefix+lobbyId+" §adu bist beigetreten!");
						else e.getPlayer().sendMessage(LobbyManager.lobbySignPrefix+lobbyId+" §cdie Lobby ist voll oder du bist bereits in einer Lobby!");
					}else e.getPlayer().sendMessage("§2MobArena §7Du bist bereits in einer Lobby!");
				}
			}
		}
	}
	
}
