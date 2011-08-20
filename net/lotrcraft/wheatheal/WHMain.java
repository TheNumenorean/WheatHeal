/*************************
 * WheatHeal Version 1.1
 *
 * Writen by Numenorean95 , EdTheLoon and Lathanael
 *
 * Todo list:
 * 	Add customisable tools
 * 	Add all foods
 *
 *
 *************************/

package net.lotrcraft.wheatheal;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import ru.tehkode.permissions.PermissionManager;

import com.nijiko.permissions.PermissionHandler;

public class WHMain extends JavaPlugin {

	public static Configuration config;
	public static Healer healer = new Healer();
	public static UseChecker checker = new UseChecker();
	public static Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler nijikoPermissions; // Nijikokun's Permissions Plugin
	public static PermissionManager permissionsEx; // t3hk0d3's PermissionsEx Plugin
	public static PluginManager pm;

	public void onDisable() {
		Config.confSave(config);
		log.info("WheatHeal " + this.getDescription().getVersion() + " disabled");
	}

	public void onEnable() {
		pm = this.getServer().getPluginManager();
		this.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new WHListener(), Event.Priority.Highest, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, new PluginListener(), Event.Priority.Monitor, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new PluginListener(), Event.Priority.Monitor, this);
		PluginListener.hookInit(this.getServer().getPluginManager());
		config = this.getConfiguration();
		Config.loadConf(config); // Loading configuration
		// For selfhealing with Wheat, no need to register the Listener if healing is not enabled!
		if(Config.use.get("Foods.Wheat.selfHeal")){
			this.getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, new WHPlayerListener(), Event.Priority.Highest, this);
		}
		getCommand("wh").setExecutor(new WHCommand(this));  // 'rerouting' to the new command class
		log.info("[WheatHeal] Version " + this.getDescription().getVersion() + " enabled");
	}

}
