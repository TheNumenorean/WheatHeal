package net.lotrcraft.wheatheal.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import net.lotrcraft.wheatheal.WHCommand;
import net.lotrcraft.wheatheal.WHMain;
import net.lotrcraft.wheatheal.permissionsCheck;

public class Version extends WHCommand {

	public Version(WHMain whMain) {
		super(whMain);
	}

	public static void getVer(CommandSender sender) {
		PluginDescriptionFile pdf = WHMain.pm.getPlugin("WheatHeal").getDescription();
		if (sender instanceof ConsoleCommandSender){
			WHMain.log.info("[WheatHeal] version: " + pdf.getVersion() + ".");
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.version")){
			sender.sendMessage(ChatColor.GREEN + "WheatHeal version: "+ pdf.getVersion()+ ".");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}
}
