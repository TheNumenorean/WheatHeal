package net.lotrcraft.wheatheal;

import java.io.File;
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
	public static Logger log = Logger.getLogger("minecraft");
	public static int[] amounts = new int[10]; // Config variable
	public static boolean[] use = new boolean[10]; // Config variable
	public static boolean useBukkitPerms; // Config variable
	public static PermissionHandler nijikoPermissions; // Nijikokun's Permissions Plugin
	public static boolean bukkitPermissions; // Bukkit's Official Permissions Plugin
	Configuration config;

	public void onDisable() {
		log.info("[WheatHeal V" + this.getDescription().getVersion() + "] Plugin disabled");
		config.save();
	}

	public void onEnable() {
		this.getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DAMAGE, new WHListener(), Event.Priority.High, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, new PluginListener(), Event.Priority.Monitor, this);
		this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new PluginListener(), Event.Priority.Monitor, this);
		PluginListener.hookInit(this.getServer().getPluginManager());
		loadConf();
		log.info("[WheatHeal V" + this.getDescription().getVersion() + "] Plugin enabled");
	}


	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (args[0].equalsIgnoreCase("reload")) { // More efficient to check if the command send was reload first.
			if (sender instanceof ConsoleCommandSender) {
				loadConf();
				sender.sendMessage(ChatColor.GREEN + "[WheatHeal] Config reloaded");
				// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
				//config.prop.list(System.out);
				return true;
			} else if (sender instanceof Player) {
				if (useBukkitPerms) { // If using PermissionsBukkit
					if (bukkitPermissions) { // Check that PermissionsBukkit is enabled first.
						if (sender.isPermissionSet("wheatheal.commands.reload")) { // You can change the node this looks for if you want
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
		
		// JUST an idea
		try {
			wait(20L*5L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (config.getHeader() == null || !config.getHeader().equals("#Version 0.2"/* + this.getDescription().getVersion()*/)){ //check version
			log.severe("loadConf has called initConf. Header = " + config.getHeader());
			confInit();
			config.load();
		}

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
		

		// Get whether Bukkit's Official Permissions should be used or if Nijikokun's should be used
		useBukkitPerms = config.getBoolean("Permissions.useBukkit", false);
		// DEBUG LINE
		log.info("[WheatHeal] useBukkit = " + useBukkitPerms);

	}

	private void confInit() {
		config.removeProperty("healAmt");
		
		config.setProperty("Foods.Wheat.enable", true);
		config.setProperty("Foods.Wheat.healValue", 1);

		config.setProperty("Foods.RawPork.enable", true);
		config.setProperty("Foods.RawPork.healValue", 3);

		config.setProperty("Foods.CookedPork.enable", true);
		config.setProperty("Foods.CookedPork.healValue", 8);

		config.setProperty("Foods.RawFish.enable", true);
		config.setProperty("Foods.RawFish.healValue", 4);

		config.setProperty("Foods.CookedFish.enable", true);
		config.setProperty("Foods.CookedFish.healValue", 8);

		config.setProperty("Foods.Bread.enable", true);
		config.setProperty("Foods.Bread.healValue", 3);

		config.setProperty("Foods.Cookie.enable", true);
		config.setProperty("Foods.Cookie.healValue", 7);

		config.setProperty("Foods.Apple.enable", true);
		config.setProperty("Foods.Apple.healValue", 5);

		config.setProperty("Foods.GoldenApple.enable", true);
		config.setProperty("Foods.GoldenApple.healValue", 20);

		config.setProperty("Foods.MushroomStew.enable", true);
		config.setProperty("Foods.MushroomStew.healValue", 10);

		config.setProperty("Permissions.useBukkit", false);
		
		config.setHeader("#Version " + this.getDescription().getVersion());
		config.save();
	}

}
