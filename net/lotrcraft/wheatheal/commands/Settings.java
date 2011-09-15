package net.lotrcraft.wheatheal.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import net.lotrcraft.wheatheal.Config;
import net.lotrcraft.wheatheal.WHCommand;
import net.lotrcraft.wheatheal.WHMain;
import net.lotrcraft.wheatheal.permissionsCheck;

public class Settings extends WHCommand{

	public Settings(WHMain whMain) {
		super(whMain);
	}

	public static void get (String item, CommandSender sender){
		int amount = 0;
		Boolean use = false;
		if (sender instanceof ConsoleCommandSender){
			use = Config.confGetEnabled(item);
			if (item.equalsIgnoreCase("usebukkit")){
				WHMain.log.info("[WheatHeal] Using BukkitPermissions is set to: " + String.valueOf(use));
				return;
			}
			if (use){
				amount = Config.confGetHealValue(item);
				WHMain.log.info("[WheatHeal] Healing set to true and " + item + " heals for: " + String.valueOf(amount));
			}
			else {
				WHMain.log.info("[WheatHeal] Healing with " + item + " is disabled.");
			}
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.get")){
			use = Config.confGetEnabled(item);
			if (item.equalsIgnoreCase("usebukkit")){
				sender.sendMessage(ChatColor.GREEN+ "Using BukkitPermissions is set to: " + String.valueOf(use));
				return;
			}
			if (use){
				amount = Config.confGetHealValue(item);
				sender.sendMessage(ChatColor.GREEN + "Healing set to true and " + ChatColor.RED + item + ChatColor.GREEN + " heals for: " + String.valueOf(amount));
			}
			else {
				sender.sendMessage(ChatColor.GREEN + "Healing with " + ChatColor.RED + item + ChatColor.GREEN + " is disabled.");
			}
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}

	public static void editAmount (CommandSender sender, String item, int value){
		if (sender instanceof ConsoleCommandSender){
			Config.confEditAmount(item, value);
			WHMain.log.info("[WheatHeal] Healing amount of " + item + " changed to: " + value);
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.edit")){
			Config.confEditAmount(item, value);
			sender.sendMessage(ChatColor.GREEN + "Healingamount of " + ChatColor.RED + item + ChatColor.GREEN + " changed to: " + value);
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}

	public static void editUse (CommandSender sender, String item, boolean use){
		if (sender instanceof ConsoleCommandSender){
			if (use){
				Config.confEditUse(item, use);
				if (item.equalsIgnoreCase("usebukkit")){
					WHMain.log.info("[WheatHeal] Enabled the use of BukkitPermissions.");
				}
				else{
					WHMain.log.info("[WheatHeal] Enabled healing with " + item + ".");
				}
			}
			else{
				Config.confEditUse(item, use);
				if (item.equalsIgnoreCase("usebukkit")){
					WHMain.log.info("[WheatHeal] Disabled the use of BukkitPermissions.");
				}
				else{
					WHMain.log.info("[WheatHeal] Disabled healing with " + item + ".");
				}
			}
			return;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.edit")){
			if (use){
				Config.confEditUse(item, use);
				sender.sendMessage(ChatColor.GREEN + "Enabled healing with " + item + ".");
			}
			else{
				Config.confEditUse(item, use);
				if (item.equalsIgnoreCase("usebukkit")){
					sender.sendMessage(ChatColor.RED + "Disabled the use of BukkitPermissions.");
				}
				else{
					sender.sendMessage(ChatColor.RED + "Disabled healing with " + item + ".");;
				}
			}
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to do this!");
			return;
		}
	}
}
