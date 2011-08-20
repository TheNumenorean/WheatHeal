package net.lotrcraft.wheatheal.Commands;

import net.lotrcraft.wheatheal.Config;
import net.lotrcraft.wheatheal.WHCommand;
import net.lotrcraft.wheatheal.WHMain;
import net.lotrcraft.wheatheal.permissionsCheck;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.util.config.Configuration;

//TODO Delete this

public class Save extends WHCommand {

	public Save(WHMain whMain) {
		super(whMain);
	}

	public static void save (Configuration config, CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			Config.confSave(config);
			WHMain.log.info("[WheatHeal] Config saved.");
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.save")){
			Config.confSave(config);
			sender.sendMessage(ChatColor.GREEN + "Config saved.");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}
}
