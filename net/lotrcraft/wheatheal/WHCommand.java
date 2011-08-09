/*****************
 *
 * Which command has to executed.
 * Refactored code by: Lathanael
 *
 ****************/

package net.lotrcraft.wheatheal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WHCommand implements CommandExecutor {

	@SuppressWarnings("unused")
	private final WHMain plugin;

	public WHCommand(WHMain plugin){
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args[0].equalsIgnoreCase("reload")) { // More efficient to check if the command send was reload first.
			// DEBUG LINE:
			//WHMain.log.info("Checking command args.");
			Reload.reload(sender);
			return true;
		}
		return false;
	}

}