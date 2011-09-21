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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
	public static File confFile = new File("plugins/WheatHeal/config.yml");
	public static File confDir = new File("plugins/WheatHeal");
	//public static File confCopy = new File("config.yml");

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
		
		if (!confFile.exists()){
			log.info("[WheatHeal] Config nonexistant! Creating...");
			try {
				newConf();
			} catch (IOException e) {
				log.warning("[WheatHeal] Couldn't annotate conf! To get fully annotated configuration file, download it from the WheatHeal Bukkit thread.");
			}
		}
		
		Config.loadConf(config); // Loading configuration
		// For selfhealing with Wheat, no need to register the Listener if healing is not enabled!
		if(Config.useSelfHeal){
			pm.registerEvent(Type.PLAYER_INTERACT, new WHPlayerListener(), Priority.Highest, this);
		}
		getCommand("wh").setExecutor(new WHCommand(this));  // 'rerouting' to the new command class
		
		log.info("WheatHeal Version " + this.getDescription().getVersion() + " enabled");
	}

	private void newConf() throws IOException {
		InputStream from = null;
	    FileOutputStream to = null;
	    
	    confDir.mkdirs();
		if (confFile.createNewFile() && !confFile.canWrite()) {
			log.warning("[WheatHeal] Can't write to file! You will not have a fully annotated conf.");
			return;
		}
	    
	    try {
	      from = getClass().getClassLoader().getResourceAsStream("config.yml");
	      to = new FileOutputStream(confFile);
	      byte[] buffer = new byte[4096];
	      int bytesRead;

	      while ((bytesRead = from.read(buffer)) != -1)
	        to.write(buffer, 0, bytesRead); // write
	    } finally {
	      if (from != null)
	        try {
	          from.close();
	        } catch (IOException e) {
	          ;
	        }
	      if (to != null)
	        try {
	          to.close();
	        } catch (IOException e) {
	          ;
	        }
	    }
		
	}

}
