/*************************
 * WheatHeal Version 1.0
 *
 * Writen by Numenorean95 and EdTheLoon
 *
 * Todo list:
 * 	Add customisable tools
 * 	Add all foods
 *
 *
 *************************/

package net.lotrcraft.wheatheal;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import com.nijiko.permissions.PermissionHandler;

public class WHMain extends JavaPlugin {

	public static Configuration config;
	public static Healer healer = new Healer();
	public static UseChecker checker = new UseChecker();
	public static Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler nijikoPermissions; // Nijikokun's Permissions Plugin
	public static boolean bukkitPermissions; // Bukkit's Official Permissions Plugin

	public void onDisable() {
		log.info("WheatHeal " + this.getDescription().getVersion() + " disabled");
	}

	public void onEnable() {
		this.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new WHListener(), Event.Priority.Highest, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, new PluginListener(), Event.Priority.Monitor, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new PluginListener(), Event.Priority.Monitor, this);
		PluginListener.hookInit(this.getServer().getPluginManager());
		config = this.getConfiguration();
		Config.loadConf(config); // Loading configuration
		//getCommand("wh").setExecutor(new WHCommand(this));  // 'rerouting' to the new command class
		log.info("[WheatHeal] Version " + this.getDescription().getVersion() + " enabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if (!args[0].equalsIgnoreCase("reload")){
			return false;
		}
		if (sender instanceof ConsoleCommandSender){
			Config.loadConf(config);
			WHMain.log.info("[WheatHeal] Config reloaded");
			return true;
		}
		if (permissionsCheck.check(sender, "wheatheal.commands.reload")){
			Config.loadConf(config);
			sender.sendMessage("[WheatHeal] Config reloaded");
			return true;
		} else {
			sender.sendMessage("You don't have permission to do this!");
		}
		
		
		
		return false;
	}
}
