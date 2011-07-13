package net.lotrcraft.wheatheal;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class WHMain extends JavaPlugin {

	public static ConfigHandler config = new ConfigHandler();
	public static Healer healer = new Healer();
	public static Logger log = Logger.getLogger("minecraft");

	public void onDisable() {
		log.info("[WheatHeal V" + this.getDescription().getVersion() + "] Plugin disabled");
	}

	public void onEnable() {
		this.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new WHListener(), Event.Priority.High, this);
		log.info("[WheatHeal V" + this.getDescription().getVersion() + "] Plugin enabled");
	}

}
