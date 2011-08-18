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

		if (args[0].equalsIgnoreCase("reload")){
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
		else if (args[0].equalsIgnoreCase("get")){
			Settings.get(args[1], sender);
			return true;
		}
		else if (args[0].equalsIgnoreCase("version")){
			Version.getVer(sender);
			return true;
		}
		else if (args[0].equalsIgnoreCase("edit")){
			boolean use = Boolean.valueOf(args[2]);
			if (args.length >= 3){
				if ((use || !use)){
					Settings.editUse(sender, args[1], use);
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
