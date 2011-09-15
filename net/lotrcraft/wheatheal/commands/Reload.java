package net.lotrcraft.wheatheal.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.util.config.Configuration;

import net.lotrcraft.wheatheal.Config;
import net.lotrcraft.wheatheal.WHCommand;
import net.lotrcraft.wheatheal.WHMain;
import net.lotrcraft.wheatheal.permissionsCheck;

public class Reload extends WHCommand {

	public Reload(WHMain whMain) {
		super(whMain);
	}

	public static void reload (Configuration config,CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			Config.loadConf(config);
			WHMain.log.info("[WheatHeal] Config reloaded.");
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.reload")){
			Config.loadConf(config);
			sender.sendMessage(ChatColor.GREEN + "Config reloaded.");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}
}
