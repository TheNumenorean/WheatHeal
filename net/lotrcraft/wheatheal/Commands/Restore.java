package net.lotrcraft.wheatheal.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.util.config.Configuration;

import net.lotrcraft.wheatheal.Config;
import net.lotrcraft.wheatheal.WHCommand;
import net.lotrcraft.wheatheal.WHMain;
import net.lotrcraft.wheatheal.permissionsCheck;

public class Restore extends WHCommand {

	public Restore(WHMain whMain) {
		super(whMain);
	}

	public static void restore (Configuration config, CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			Config.confRestore(config);
			WHMain.log.info("[WheatHeal] Config restored.");
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.restore")){
			Config.confRestore(config);
			sender.sendMessage(ChatColor.GREEN + "Config restored.");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}

}
