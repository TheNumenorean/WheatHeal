/********************
 *
 * Loading/Creating Config file.
 * Refactored code by: Lathanael
 *
 *******************/


package net.lotrcraft.wheatheal;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.util.config.Configuration;

public class Config {

	public static HashMap<String,Integer> amounts = new HashMap<String,Integer>();
	public static HashMap<String,Boolean> use = new HashMap<String,Boolean>();
	//public static int[] amounts = new int[17];
	//public static boolean[] use = new boolean[17];
	public static boolean useBukkitPerms;

	@SuppressWarnings("unused")
	private static WHMain plugin;

		public Config(WHMain instance) {
			plugin = instance;
		}

	public static void loadConf(Configuration config) {
		//config.load();
		//log.info("" + config.getProperty("Version"));
		//if (config.getHeader() == null || !config.getHeader().equals("Version m"/* + this.getDescription().getVersion()*/)){ //check version
		//if (config.getProperty("Version") != "0.2"){
		//	log.severe("loadConf has called initConf.");
		//confInit(config);
		//}

		config.load();

		//Get heal amounts for each item
		//amounts[0] = config.getInt("Foods.Wheat.healValue", 1);
		amounts.put("Wheat",getInt("Foods.Wheat.healValue", 1));
		//amounts[1] = config.getInt("Foods.RawPork.healValue", 3);
		amounts.put("RawPork",getInt("Foods.RawPork.healValue", 3));
		//amounts[2] = config.getInt("Foods.CookedPork.healValue", 8);
		amounts.put("CookedPork",getInt("Foods.CookedPork.healValue", 8));
		//amounts[3] = config.getInt("Foods.RawFish.healValue", 4);
		amounts.put("RawFish",getInt("Foods.RawFish.healValue", 4));
		//amounts[4] = config.getInt("Foods.CookedFish.healValue", 8);
		amounts.put("CookedFish",getInt("Foods.CookedFish.healValue", 8));
		//amounts[5] = config.getInt("Foods.Bread.healValue", 3);
		amounts.put("Bread",getInt("Foods.Bread.healValue", 3));
		//amounts[6] = config.getInt("Foods.Cookie.healValue", 7);
		amounts.put("Cookie",getInt("Foods.Cookie.healValue", 7));
		//amounts[7] = config.getInt("Foods.Apple.healValue", 5);
		amounts.put("Apple",getInt("Foods.Apple.healValue", 5));
		//amounts[8] = config.getInt("Foods.GoldenApple.healValue", 20);
		amounts.put("GoldenApple",getInt("Foods.GoldenApple.healValue", 20));
		//amounts[9] = config.getInt("Foods.MushroomStew.healValue", 10);
		amounts.put("MushroomStew",getInt("Foods.MushroomStew.healValue", 10));
		//amounts[10] = config.getInt("Foods.Cake.healValue", 10);
		amounts.put("Cake",getInt("Foods.Cake.healValue", 10));
		//amounts[11] = config.getInt("Foods.BrownMushroom.healValue", 10);
		amounts.put("BrownMushroom",getInt("Foods.BrownMushroom.healValue", 6));
		//amounts[12] = config.getInt("Foods.RedMushroom.healValue", 10);
		amounts.put("RedMushroom",getInt("Foods.RedMushroom.healValue", 7));
		//amounts[13] = config.getInt("Foods.Milk.healValue", 10);
		amounts.put("Milk",getInt("Foods.Milk.healValue", 7));
		//amounts[14] = config.getInt("Foods.Egg.healValue", 10);
		amounts.put("Egg", getInt("Foods.Egg.healValue", 4));
		//amounts[15] = config.getInt("Foods.Sugar.healValue", 10);
		amounts.put("Sugar",getInt("Foods.Sugar.healValue", 3));
		//amounts[16] = config.getInt("Foods.Sugarcane.healValue", 10);
		amounts.put("Sugarcane",getInt("Foods.Sugarcane.healValue", 2));


		//Whether each should be used
		//use[0] = config.getBoolean("Foods.Wheat.enable", true);
		use.put("Wheat",getBoolean("Foods.Wheat.enable", true));
		use.put("WheatSelf", getBoolean("Foods.Wheat.selfHeal", true));
		//use[1] = config.getBoolean("Foods.RawPork.enable", true);
		use.put("RawPork",getBoolean("Foods.RawPork.enable", true));
		//use[2] = config.getBoolean("Foods.CookedPork.enable", true);
		use.put("CookedPork",getBoolean("Foods.CookedPork.enable", true));
		//use[3] = config.getBoolean("Foods.RawFish.enable", true);
		use.put("RawFish",getBoolean("Foods.RawFish.enable", true));
		//use[4] = config.getBoolean("Foods.CookedFish.enable", true);
		use.put("CookedFish",getBoolean("Foods.CookedFish.enable", true));
		//use[5] = config.getBoolean("Foods.Bread.enable", true);
		use.put("Bread",getBoolean("Foods.Bread.enable", true));
		//use[6] = config.getBoolean("Foods.Cookie.enable", true);
		use.put("Cookie",getBoolean("Foods.Cookie.enable", true));
		//use[7] = config.getBoolean("Foods.Apple.enable", true);
		use.put("Apple",getBoolean("Foods.Apple.enable", true));
		//use[8] = config.getBoolean("Foods.GoldenApple.enable", true);
		use.put("GoldenApple",getBoolean("Foods.GoldenApple.enable", true));
		//use[9] = config.getBoolean("Foods.MushroomStew.enable", true);
		use.put("MushroomStew",getBoolean("Foods.MushroomStew.enable", true));
		//use[10] = config.getBoolean("Foods.Cake.enable", true);
		use.put("Cake",getBoolean("Foods.Cake.enable", true));
		//use[11] = config.getBoolean("Foods.BrownMushroom.enable", true);
		use.put("BrownMushroom",getBoolean("Foods.BrownMushroom.enable", true));
		//use[12] = config.getBoolean("Foods.RedMushroom.enable", true);
		use.put("RedMushroom",getBoolean("Foods.RedMushroom.enable", true));
		//use[13] = config.getBoolean("Foods.Milk.enable", true);
		use.put("Milk",getBoolean("Foods.Milk.enable", true));
		//use[14] = config.getBoolean("Foods.Egg.enable", true);
		use.put("Egg",getBoolean("Foods.Egg.enable", true));
		//use[15] = config.getBoolean("Foods.Sugar.enable", true);
		use.put("Sugar",getBoolean("Foods.Sugar.enable", true));
		//use[16] = config.getBoolean("Foods.Sugarcane.enable", true);
		use.put("Sugarcane",getBoolean("Foods.Sugarcane.enable", true));


		// Get whether Bukkit's Official Permissions should be used or if Nijikokun's should be used
		use.put("useBukkit",getBoolean("Permissions.useBukkit", false));
		useBukkitPerms = getBoolean("Permissions.useBukkit", false);
		//WHMain.log.info(String.valueOf(use.get("useBukkit").booleanValue()));
		config.save();
		config.load();
	}

	public static void confInit(Configuration config) {
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

	public static void confSave(Configuration config){
		for (Map.Entry<String, Integer> entry : amounts.entrySet()){
			config.setProperty("Foods." + entry.getKey() + ".healValue", entry.getValue());
		}
		for (Map.Entry<String, Boolean> entry : use.entrySet()){
			if (!entry.getKey().equalsIgnoreCase("usebukkit")){
				config.setProperty("Foods." + entry.getKey() + ".enable", entry.getValue().booleanValue());
			}
			else {
				config.setProperty("Permissions.useBukkit", entry.getValue().booleanValue());
			}
		}
		config.save();
	}

	public static void confSave (String node, Boolean value){
		WHMain.config.setProperty(node, value);
		WHMain.config.save();
	}

	public static void confSave (String node, int value){
		WHMain.config.setProperty(node, value);
		WHMain.config.save();
	}

	public static void confEditAmount (String item, int amount){
		amounts.put(item, amount);
		confSave(item, amount);
	}

	public static void confEditUse (String item, Boolean allowed){
		use.put(item, allowed);
		confSave(item, allowed);
	}

	public static void confRestore(Configuration config){
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
		config.setProperty("Foods.Cake.enable", true);
		config.setProperty("Foods.Cake.healValue", 10);
		config.setProperty("Foods.BrownMushroom.enable", true);
		config.setProperty("Foods.BrownMushroom.healValue", 6);
		config.setProperty("Foods.RedMushroom.enable", true);
		config.setProperty("Foods.RedMushroom.healValue", 7);
		config.setProperty("Foods.Milk.enable", true);
		config.setProperty("Foods.Milk.healValue", 7);
		config.setProperty("Foods.Egg.enable", true);
		config.setProperty("Foods.Egg.healValue", 4);
		config.setProperty("Foods.Sugar.enable", true);
		config.setProperty("Foods.Sugar.healValue", 3);
		config.setProperty("Foods.Sugarcane.enable", true);
		config.setProperty("Foods.Sugarcane.healValue", 2);
		config.setProperty("Permissions.useBukkit", false);
		config.save();
	}

	public static int confGetHealValue (String value){
		return amounts.get(value);
	}

	public static boolean confGetEnabled (String value){
		return use.get(value).booleanValue();
	}

	// Functions for AutoUpdating the Config.yml
	public Object getProperty(String path, Object def) {
		if(isNull(path))
			return addProperty(path, def);
		return WHMain.config.getProperty(path);
	}

	public static int getInt(String path, Integer def) {
		if(isNull(path))
			return (Integer) addProperty(path, def);
		return WHMain.config.getInt(path, def);
	}

	public static boolean getBoolean(String path, Boolean def) {
		if(isNull(path))
			return (Boolean) addProperty(path, def);
		return WHMain.config.getBoolean(path, def);
	}

	private static Object addProperty(String path, Object val) {
		WHMain.config.setProperty(path, val);
		return val;
	}

	private static boolean isNull(String path) {
		return WHMain.config.getProperty(path) == null;
	}
}
