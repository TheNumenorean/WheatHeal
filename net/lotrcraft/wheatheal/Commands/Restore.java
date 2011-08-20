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

	private static boolean confirmed = false;

	public Restore(WHMain whMain) {
		super(whMain);
	}

	public static void restore (Configuration config, CommandSender sender, String confirm){

		if (confirmed && confirm.equalsIgnoreCase("yes")){
			if (sender instanceof ConsoleCommandSender){
				Config.confRestore(config);
				
				WHMain.log.info("[WheatHeal] Config restored.");
				confirmed = false;
				return;
			}
			if (permissionsCheck.check(sender, "wheatheal.commands.restore")){
				Config.confRestore(config);
				sender.sendMessage(ChatColor.GREEN + "Config restored.");
				confirmed = false;
				return;
			}
			else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
				confirmed = false;
				return;
			}
		}
		else if (confirmed && confirm.equalsIgnoreCase("no")) {
			if (sender instanceof ConsoleCommandSender){
				WHMain.log.info("[WheatHeal] You discarded the restore.");
				confirmed = false;
				return;
			}
			else if (permissionsCheck.check(sender, "wheatheal.commands.restore")){
				sender.sendMessage(ChatColor.RED +"You discarded the restore.");
				confirmed = false;
				return;
			}
			else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
				confirmed = false;
				return;
			}
		}
		else {
			// Someone tried to restore without doing /wh restore first
			if (sender instanceof ConsoleCommandSender){
				WHMain.log.info("[WheatHeal] You did not confirm the restore properly.");
				return;
			}
			else if (permissionsCheck.check(sender, "wheatheal.commands.restore")){
				sender.sendMessage(ChatColor.RED +"You did not confirm the restore properly.");
				return;
			}
			else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
				return;
			}
		}
	}


	public static void confirm (String label, CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			WHMain.log.info("[WheatHeal] Do you really want to restore the default config.yml?.");
			WHMain.log.info("[WheatHeal] Enter '" + label + " restore yes' to confirm or 'no' to discard.");
			confirmed = true;
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.restore")){
			sender.sendMessage(ChatColor.GREEN + "Do you really want to restore the default config.yml?.");
			sender.sendMessage(ChatColor.GREEN + "Enter '/" + label + " restore yes' to confirm or 'no' to discard.");
			confirmed = true;
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}

}
