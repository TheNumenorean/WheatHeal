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
import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import ru.tehkode.permissions.PermissionManager;

import com.herocraftonline.dev.heroes.Heroes;
import com.nijiko.permissions.PermissionHandler;

public class WHMain extends JavaPlugin {

	public static Configuration config;
	public static Healer healer = new Healer();
	public static UseChecker checker = new UseChecker();
	public static Logger log = Logger.getLogger("Minecraft");
	public static PermissionHandler nijikoPermissions; // Nijikokun's Permissions Plugin
	public static PermissionManager permissionsEx; // t3hk0d3's PermissionsEx Plugin
	public static PluginManager pm;
	public static Heroes heroes = null;
	public static File confFile = new File("/WheatHeal/config.yml");

	public void onDisable() {
		Config.confSave(config);
		log.info("[WheatHeal] Version" + this.getDescription().getVersion() + " disabled");
	}

	public void onEnable() {
		pm = this.getServer().getPluginManager();
		pm.registerEvent(Type.ENTITY_DAMAGE, new WHListener(), Priority.Highest, this);
		pm.registerEvent(Type.PLUGIN_DISABLE, new PluginListener(), Priority.Monitor, this);
		pm.registerEvent(Type.PLUGIN_ENABLE, new PluginListener(), Priority.Monitor, this);
		PluginListener.hookInit(pm);
		config = this.getConfiguration();
		Config.loadConf(config); // Loading configuration
		// For selfhealing with Wheat, no need to register the Listener if healing is not enabled!
		if(Config.useSelfHeal){
			pm.registerEvent(Type.PLAYER_INTERACT, new WHPlayerListener(), Priority.Highest, this);
		}
		getCommand("wh").setExecutor(new WHCommand(this));  // 'rerouting' to the new command class
		log.info("[WheatHeal] Version " + this.getDescription().getVersion() + " enabled");
		
		if (!confFile.exists()){
			//TODO: Write config.yml to plugin folder.
		}
	}

}
