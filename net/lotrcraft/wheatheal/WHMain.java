/*************************
 * WheatHeal Version 2.0
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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import com.nijiko.permissions.PermissionHandler;
import org.bukkit.entity.Player;

public class WHMain extends JavaPlugin {

	public static Healer healer = new Healer();
	public static UseChecker checker = new UseChecker();
	public static Logger log = Logger.getLogger("minecraft");
	public static int[] amounts = new int[17]; // Config variable
	public static boolean[] use = new boolean[17]; // Config variable
	public static boolean useBukkitPerms; // Config variable
	public static PermissionHandler nijikoPermissions; // Nijikokun's Permissions Plugin
	public static boolean bukkitPermissions; // Bukkit's Official Permissions Plugin
	Configuration config;

	public void onDisable() {
		log.info("WheatHeal " + this.getDescription().getVersion() + " disabled");
	}

	public void onEnable() {
		this.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new WHListener(), Event.Priority.Highest, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, new PluginListener(), Event.Priority.Monitor, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new PluginListener(), Event.Priority.Monitor, this);
		PluginListener.hookInit(this.getServer().getPluginManager());
		loadConf();
		log.info("WheatHeal " + this.getDescription().getVersion() + " enabled");
	}


	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (args[0].equalsIgnoreCase("reload")) { // More efficient to check if the command send was reload first.
			if (sender instanceof ConsoleCommandSender) {
				loadConf();
				log.info(ChatColor.GREEN + "[WheatHeal] Config reloaded");
				return true;
			} else {
				if (useBukkitPerms) { // If using PermissionsBukkit
					if (bukkitPermissions) { // Check that PermissionsBukkit is enabled first.
						if (sender.isPermissionSet("wheatheal.commands.reload")) { // Its good
							loadConf();
							sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission to do this");
							return true;
						}
					} else if (sender.isOp()) { // If PermissionsBukkit is disabled check if sender is an op
						loadConf();
						sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
						return true;
					} else { // If PermissionsBukkit is disabled and sender isn't op
						sender.sendMessage(ChatColor.RED + "You do not have permission to do this");
						return true;
					}
				} else { // If using Nijikokun's Permissions
					if (nijikoPermissions != null) { // Check that Permissions is enabled
						if (nijikoPermissions.has((Player)sender, "wheatheal.commands.reload") || sender.isOp()) { // Again you can change this node if you want
							loadConf();
							sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
							return true;
						}
					} else if (sender.isOp()) { // If player doesn't have the permission but is an op
						loadConf();
						sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
						return true;
					} else { // If Nijikokun's Permissions is disabled and player isn't op
						sender.sendMessage(ChatColor.RED + "You do not have permission to do this");
						return true;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	private void loadConf() {
		config = this.getConfiguration();
		config.load();
		//log.info("" + config.getProperty("Version"));
		//if (config.getHeader() == null || !config.getHeader().equals("Version m"/* + this.getDescription().getVersion()*/)){ //check version
		//if (config.getProperty("Version") != "0.2"){
		//	log.severe("loadConf has called initConf.");
			confInit();
		//}
		
		config.load();

		//Get heal amounts for each item
		amounts[0] = config.getInt("Foods.Wheat.healValue", 1);
		amounts[1] = config.getInt("Foods.RawPork.healValue", 3);
		amounts[2] = config.getInt("Foods.CookedPork.healValue", 8);
		amounts[3] = config.getInt("Foods.RawFish.healValue", 4);
		amounts[4] = config.getInt("Foods.CookedFish.healValue", 8);
		amounts[5] = config.getInt("Foods.Bread.healValue", 3);
		amounts[6] = config.getInt("Foods.Cookie.healValue", 7);
		amounts[7] = config.getInt("Foods.Apple.healValue", 5);
		amounts[8] = config.getInt("Foods.GoldenApple.healValue", 20);
		amounts[9] = config.getInt("Foods.MushroomStew.healValue", 10);
		amounts[10] = config.getInt("Foods.Cake.healValue", 10);
		amounts[11] = config.getInt("Foods.BrownMushroom.healValue", 10);
		amounts[12] = config.getInt("Foods.RedMushroom.healValue", 10);
		amounts[13] = config.getInt("Foods.Milk.healValue", 10);
		amounts[14] = config.getInt("Foods.Egg.healValue", 10);
		amounts[15] = config.getInt("Foods.Sugar.healValue", 10);
		amounts[16] = config.getInt("Foods.Sugarcane.healValue", 10);
		

		//Whether each should be used
		use[0] = config.getBoolean("Foods.Wheat.enable", true);
		use[1] = config.getBoolean("Foods.RawPork.enable", true);
		use[2] = config.getBoolean("Foods.CookedPork.enable", true);
		use[3] = config.getBoolean("Foods.RawFish.enable", true);
		use[4] = config.getBoolean("Foods.CookedFish.enable", true);
		use[5] = config.getBoolean("Foods.Bread.enable", true);
		use[6] = config.getBoolean("Foods.Cookie.enable", true);
		use[7] = config.getBoolean("Foods.Apple.enable", true);
		use[8] = config.getBoolean("Foods.GoldenApple.enable", true);
		use[9] = config.getBoolean("Foods.MushroomStew.enable", true);
		use[10] = config.getBoolean("Foods.Cake.enable", true);
		use[11] = config.getBoolean("Foods.BrownMushroom.enable", true);
		use[12] = config.getBoolean("Foods.RedMushroom.enable", true);
		use[13] = config.getBoolean("Foods.Milk.enable", true);
		use[14] = config.getBoolean("Foods.Egg.enable", true);
		use[15] = config.getBoolean("Foods.Sugar.enable", true);
		use[16] = config.getBoolean("Foods.Sugarcane.enable", true);
		

		// Get whether Bukkit's Official Permissions should be used or if Nijikokun's should be used
		useBukkitPerms = config.getBoolean("Permissions.useBukkit", false);

	}

	private void confInit() {
		config.removeProperty("healAmt");
		//log.info("" + config.getProperty("Foods.Wheat.healValue"));

		//config.setProperty("Version", this.getDescription().getVersion());
		
		if (config.getProperty("Foods.Wheat.enable") == null){
			config.setProperty("Foods.Wheat.enable", true);
			//log.info("A " + config.getProperty("Foods.Wheat.healValue"));
		}
		if (config.getProperty("Foods.Wheat.healValue") == null){
			config.setProperty("Foods.Wheat.healValue", 1);
		}

		if (config.getProperty("Foods.RawPork.enable") == null){
			config.setProperty("Foods.RawPork.enable", true);
		}
		if (config.getProperty("Foods.RawPork.healValue") ==  null){
			config.setProperty("Foods.RawPork.healValue", 3);
		}

		if (config.getProperty("Foods.CookedPork.enable") == null){
			config.setProperty("Foods.CookedPork.enable", true);
		}
		if (config.getProperty("Foods.CookedPork.healValue") == null){
			config.setProperty("Foods.CookedPork.healValue", 8);
		}

		if (config.getProperty("Foods.RawFish.enable") == null){
			config.setProperty("Foods.RawFish.enable", true);
		}
		if (config.getProperty("Foods.RawFish.healValue") == null){
			config.setProperty("Foods.RawFish.healValue", 4);
		}

		if (config.getProperty("Foods.CookedFish.enable") == null){
			config.setProperty("Foods.CookedFish.enable", true);
		}
		if (config.getProperty("Foods.CookedFish.healValue") == null){
			config.setProperty("Foods.CookedFish.healValue", 8);
		}

		if (config.getProperty("Foods.Bread.enable") == null){
			config.setProperty("Foods.Bread.enable", true);
		}
		if (config.getProperty("Foods.Bread.healValue") == null){
			config.setProperty("Foods.Bread.healValue", 3);
		}

		if (config.getProperty("Foods.Cookie.enable") == null){
			config.setProperty("Foods.Cookie.enable", true);
		}
		if (config.getProperty("Foods.Cookie.healValue") == null){
			config.setProperty("Foods.Cookie.healValue", 7);
		}

		if (config.getProperty("Foods.Apple.enable") == null){
			config.setProperty("Foods.Apple.enable", true);
		}
		if (config.getProperty("Foods.Apple.healValue") == null){
			config.setProperty("Foods.Apple.healValue", 5);
		}

		if (config.getProperty("Foods.GoldenApple.enable") == null){
			config.setProperty("Foods.GoldenApple.enable", true);
		}
		if (config.getProperty("Foods.GoldenApple.healValue") == null){
			config.setProperty("Foods.GoldenApple.healValue", 20);
		}

		if (config.getProperty("Foods.MushroomStew.enable") == null){
			config.setProperty("Foods.MushroomStew.enable", true);
		}
		if (config.getProperty("Foods.MushroomStew.healValue") == null){
			config.setProperty("Foods.MushroomStew.healValue", 10);
		}
		
		if (config.getProperty("Foods.Cake.enable") == null){
			config.setProperty("Foods.Cake.enable", true);
		}
		if (config.getProperty("Foods.Cake.healValue") == null){
			config.setProperty("Foods.Cake.healValue", 20);
		}
		
		if (config.getProperty("Foods.BrownMushroom.enable") == null){
			config.setProperty("Foods.BrownMushroom.enable", true);
		}
		if (config.getProperty("Foods.BrownMushroom.healValue") == null){
			config.setProperty("Foods.BrownMushroom.healValue", 6);
		}
		
		if (config.getProperty("Foods.RedMushroom.enable") == null){
			config.setProperty("Foods.RedMushroom.enable", true);
		}
		if (config.getProperty("Foods.RedMushroom.healValue") == null){
			config.setProperty("Foods.RedMushroom.healValue", 7);
		}
		
		if (config.getProperty("Foods.Milk.enable") == null){
			config.setProperty("Foods.Milk.enable", true);
		}
		if (config.getProperty("Foods.Milk.healValue") == null){
			config.setProperty("Foods.Milk.healValue", 7);
		}
		
		if (config.getProperty("Foods.Egg.enable") == null){
			config.setProperty("Foods.Egg.enable", true);
		}
		if (config.getProperty("Foods.Egg.healValue") == null){
			config.setProperty("Foods.Egg.healValue", 4);
		}
		
		if (config.getProperty("Foods.Sugar.enable") == null){
			config.setProperty("Foods.Sugar.enable", true);
		}
		if (config.getProperty("Foods.Sugar.healValue") == null){
			config.setProperty("Foods.Sugar.healValue", 3);
		}
		
		if (config.getProperty("Foods.Sugarcane.enable") == null){
			config.setProperty("Foods.Sugarcane.enable", true);
		}
		if (config.getProperty("Foods.Sugarcane.healValue") == null){
			config.setProperty("Foods.Sugarcane.healValue", 2);
		}

		if(config.getProperty("Permissions.useBukkit") == null){
			config.setProperty("Permissions.useBukkit", false);
		}
		//log.info("B " + config.getProperty("Foods.Wheat.healValue"));
		//config.setHeader("Version " + this.getDescription().getVersion());
		
		config.save();
		//log.info("C " + config.getProperty("Foods.Wheat.healValue"));
	}

}
