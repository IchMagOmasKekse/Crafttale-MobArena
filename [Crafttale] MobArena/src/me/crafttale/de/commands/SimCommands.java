package me.crafttale.de.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.crafttale.de.MA;
import me.crafttale.de.mobarena.Simulator;

public class SimCommands implements CommandExecutor {

	String[] commands = new String[1];
	
	public SimCommands() {	
		commands[0] = "sim";
		for(String command : commands) {
			MA.ma().getCommand(command).setExecutor(this);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		switch(args.length) {
		case 0:
			if(cmd.getName().equalsIgnoreCase("sim") && sender.hasPermission("mobarena.simulation")) {
				sender.sendMessage("§9/sim start <Simulation> §fStarte Simulation");
				sender.sendMessage("§9/sim stop <Simulation> §fStoppe Simulation");
			}
			break;
		case 1:	
			if(cmd.getName().equalsIgnoreCase("sim") && sender.hasPermission("mobarena.simulation")) {
				sender.sendMessage("§9/sim start <Simulation> §fStarte Simulation");
				sender.sendMessage("§9/sim stop <Simulation> §fStoppe Simulation");
			}
			break;
		case 2:
			if(cmd.getName().equalsIgnoreCase("sim") && sender.hasPermission("mobarena.simulation")) {
				if(args[0].equalsIgnoreCase("start") && sender.hasPermission("mobarena.simulation.start")) {
					Simulator.processSimulateStart(args[1]);
				}else if(args[0].equalsIgnoreCase("stop") && sender.hasPermission("mobarena.simulation.stop")) {
					Simulator.processSimulateStop(args[1]);
				}
			}
			break;
		}
		
		return false;
	}

}
