package net.lotrcraft.wheatheal;

import net.lotrcraft.wheatheal.Commands.Reload;
import net.lotrcraft.wheatheal.Commands.Restore;
import net.lotrcraft.wheatheal.Commands.Save;
import net.lotrcraft.wheatheal.Commands.Settings;
import net.lotrcraft.wheatheal.Commands.Version;

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

		if ((label.equalsIgnoreCase("wh") || label.equalsIgnoreCase("wheatheal")) && args.length == 0){
			return false;
		}
		else if (args[0].equalsIgnoreCase("reload")){
			Reload.reload(WHMain.config, sender);
			return true;
		}
		else if (args[0].equalsIgnoreCase("save")){
			Save.save(WHMain.config, sender);
			return true;
		}
		else if (args[0].equalsIgnoreCase("restore")){
			Restore.restore(WHMain.config, sender);
			return true;
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
			else{
				return false;
			}
		}
		else {
			return false;
		}
	}

}