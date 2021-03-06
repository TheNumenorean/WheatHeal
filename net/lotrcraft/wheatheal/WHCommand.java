package net.lotrcraft.wheatheal;

import net.lotrcraft.wheatheal.commands.Reload;
import net.lotrcraft.wheatheal.commands.Restore;
import net.lotrcraft.wheatheal.commands.Settings;
import net.lotrcraft.wheatheal.commands.Version;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WHCommand implements CommandExecutor {

	@SuppressWarnings("unused")
	private final WHMain plugin;

	public WHCommand(WHMain plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

		// To prevent ArrayOutOfBounds error, the null check is just in case Bukkit makes a null Array!
		if (args.length == 0 || args == null) return false;

		if (args[0].equalsIgnoreCase("reload")){
			Reload.reload(WHMain.config, sender);
			return true;
		}
		else if (args[0].equalsIgnoreCase("restore")){
			if (args.length >= 2){
				Restore.restore(WHMain.config, sender, args[1]);
				return true;
			}
			else {
				Restore.confirm(label, sender);
				return true;
			}
		}
		else if (args[0].equalsIgnoreCase("get") && args.length > 1){
			Settings.get(args[1], sender);
			return true;
		}
		else if (args[0].equalsIgnoreCase("version")){
			Version.getVer(sender);
			return true;
		}
		else if (args[0].equalsIgnoreCase("edit") && args.length > 1){
			boolean use = false;
			if (args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){
				use = true;
			}
			if (args.length >= 3){
				if ((use)){
					Settings.editUse(sender, args[1], Boolean.parseBoolean(args[2]));
					return true;
				}
				else{
					Settings.editAmount(sender, args[1], Integer.valueOf(args[2]));
					return true;
				}
			}
		}
		return false;
	}

}
