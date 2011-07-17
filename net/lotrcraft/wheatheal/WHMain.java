package net.lotrcraft.wheatheal;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if (args[0].equalsIgnoreCase("reload")) {
				config.loadConfig();
				sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
				// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
				//config.prop.list(System.out);
				return true;
			}			
			return false;		
	}

}
