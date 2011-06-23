package net.lotrcraft.wheatheal;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class WHMain extends JavaPlugin {

	Configuration config;
	public static int heal = 0;
	Logger log = Logger.getLogger("minecraft");

	public void onDisable() {
		log.info("WheatHeal Disabled");
	}

	public void onEnable() {
		config = this.getConfiguration();
		this.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new WHListener(), Event.Priority.High, this);
		log.info("WheatHeal Enabled");
		if (config.getInt("healAmt", 1) <= 0 || config.getInt("healAmt", 1) > 20){
			config.setProperty("healAmt", 1);
		}
		config.save();
		heal = config.getInt("healAmt", 1);
	}

}
