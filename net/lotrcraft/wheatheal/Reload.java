/**********************
 *
 * Reloading the configuration.
 * Refactored code by: Lathanael
 *
 *********************/


package net.lotrcraft.wheatheal;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Reload extends WHCommand {

	public Reload(WHMain plugin) {
		super(plugin);

	}

	public static void reload(CommandSender sender){
		// DEBUG LINE:
		//WHMain.log.info("Executing reload command.");
		if (sender instanceof ConsoleCommandSender) {
			Config.loadConf(WHMain.config);
			WHMain.log.info("[WheatHeal] Config reloaded"); // No need for chat colors in console, most are still B/W
		}
		else {
			if (Config.useBukkitPerms) { // If using PermissionsBukkit
				if (WHMain.bukkitPermissions) { // Check that PermissionsBukkit is enabled first.
					if (sender.isPermissionSet("wheatheal.commands.reload")) { // Its good
						Config.loadConf(WHMain.config);
						sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
					}
					else {
						sender.sendMessage(ChatColor.RED + "You do not have permission to do this");
					}
				}
				else if (sender.isOp()) { // If PermissionsBukkit is disabled check if sender is an op
					Config.loadConf(WHMain.config);
					sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
				}
				else { // If PermissionsBukkit is disabled and sender isn't op
					sender.sendMessage(ChatColor.RED + "You do not have permission to do this");
				}
			}
			else { // If using Nijikokun's Permissions
				if (WHMain.nijikoPermissions != null) { // Check that Permissions is enabled
					if (WHMain.nijikoPermissions.has((Player)sender, "wheatheal.commands.reload") || sender.isOp()) { // Again you can change this node if you want
						Config.loadConf(WHMain.config);
						sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
					}
				}
				else if (sender.isOp()) { // If player doesn't have the permission but is an op
					Config.loadConf(WHMain.config);
					sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
				}
				else { // If Nijikokun's Permissions is disabled and player isn't op
					sender.sendMessage(ChatColor.RED + "You do not have permission to do this");
				}
			}
		}
	}
}
