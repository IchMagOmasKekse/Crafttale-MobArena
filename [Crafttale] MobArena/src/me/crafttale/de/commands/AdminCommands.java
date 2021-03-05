package me.crafttale.de.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.crafttale.de.MA;
import me.crafttale.de.mobarena.LobbyManager;

public class AdminCommands implements CommandExecutor{
	
	private String[] command = null; //Ein Array mit allen Commands, die in dieser Klasse verwendet werden.
	
	public AdminCommands() {
		//Initialisiere Commands
		command = new String[2];//Integer = Anzahl an Commands
		command[0] = "ma";
		command[1] = "mar";
		
		//Registriere Commands
		for(int i = 0; i != command.length; i++) {
			MA.ma().getCommand(command[i]).setExecutor(this);
		}
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			switch (args.length) {
			case 0:
				if(cmd.getName().equalsIgnoreCase("ma") && MA.hasPermission(p, "mobarena.command")) {
					sendCommandInfo(sender, cmd.getName());
				}else if(cmd.getName().equalsIgnoreCase("mar") && MA.hasPermission(p, "mobarena.reload")) {
					reloadSettings(sender);
				}
				break;
			default:
				p.sendMessage(" §7» §2/reload §fReloade Einstellungen");
				break;
			}
		}else {
			if(cmd.getName().equalsIgnoreCase("mar")) {
				reloadSettings(sender);
			}else sender.sendMessage("§cDieser Befehl ist nur für Spieler");
		}
		return false;
	}
	
	
	/**
	 * Sendet einen Reload Befehl an alle bedürftigen Klassen aus.
	 * @return
	 */
	public void reloadSettings(CommandSender sender)  {
		LobbyManager.reload();
		sender.sendMessage("§2MobArena §7Daten neu geladen!");
	}
	public void sendCommandInfo(CommandSender sender, String cmd)  {
		if(cmd.equalsIgnoreCase("ma")) {
			
		}
	}
}
