package net.lotrcraft.wheatheal;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class WHMain extends JavaPlugin {

	//public static ConfigHandler config = new ConfigHandler();
	public static Healer healer = new Healer();
	public static Logger log = Logger.getLogger("minecraft");
	public static int[] amounts;
	public static boolean use;
	Configuration config;

	public void onDisable() {
		log.info("[WheatHeal V" + this.getDescription().getVersion() + "] Plugin disabled");
	}

	public void onEnable() {
		this.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new WHListener(), Event.Priority.High, this);
		log.info("[WheatHeal V" + this.getDescription().getVersion() + "] Plugin enabled");
		loadConf();
	}
	

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if (args[0].equalsIgnoreCase("reload")) {
				loadConf();
				sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
				// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
				//config.prop.list(System.out);
				return true;
			}			
			return false;		//This needs a ConsoleCommandSender check
	}
	
	private void loadConf() {
		config = this.getConfiguration();
		
		if (config.getInt("minimum", 60) <= 0) {
			config.setProperty("minimum", 60);
		}
		if (config.getInt("maximum", 120) <= 0) {
			config.setProperty("maximum", 120);
		}
		if (config.getInt("maximum", 120) < config.getInt("minimum", 60)) {;
			config.setProperty("maximum", config.getInt("minimum", 60));
		}
		if (config.getProperty("useList") == null){
			config.setProperty("useList", false);
		}
		if (config.getProperty("Players") == null){
			config.setProperty("Players", null);
		}
		
		config.setHeader("#Version 0.2");
		config.save();
		
	}

}
