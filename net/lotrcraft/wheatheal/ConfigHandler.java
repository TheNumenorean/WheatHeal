package net.lotrcraft.wheatheal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {

	// Configuration file properties
	static String pluginDir = "plugins/WheatHeal";
	static File config = new File(pluginDir + File.separator + "config.cfg");
	static Properties prop = new Properties();

	// Default properties
	public int[] amounts;

	// CONSTRUCTOR
	public ConfigHandler() {		

		if (checkConfig()) {
			loadConfig();
		} else {
			// Initialise variables
			amounts[0] = 1; // WHEAT
			amounts[1] = 2; // RAW_PORKCHOP
			amounts[2] = 3; // COOKED_PORKHOP
			amounts[3] = 1; // RAW_FISH
			amounts[4] = 2; // COOKED FISH
			amounts[5] = 2; // BREAD
			createConfig();
		}

	}

	// Check to see if config exists, returns true or false
	public boolean checkConfig() {
		if (!config.exists())
		{
			return false;
		} else {
			return true;
		}
	}

	// Create the configuration file and insert default values
	public void createConfig() {
		try {
			new File(pluginDir).mkdir();
			config.createNewFile();
			FileOutputStream out = new FileOutputStream(config);
			//prop.put("propertyName", defaultValue);
			prop.put("wheat",amounts[0]);
			prop.put("raw_porkchop", amounts[1]);
			prop.put("cooked_porkchop", amounts[2]);
			prop.put("raw_fish", amounts[3]);
			prop.put("cooked_fish", amounts[4]);
			prop.put("bread", amounts[5]);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Load configuration file
	public void loadConfig() {
		try {
			FileInputStream in = new FileInputStream(config);
			prop.load(in);
			//propertyVariable = variableType.parseType(prop.getProperty("propertyName"));
			amounts[0] = Integer.parseInt(prop.getProperty("wheat"));
			amounts[1] = Integer.parseInt(prop.getProperty("raw_porkchop"));
			amounts[2] = Integer.parseInt(prop.getProperty("cooked_porkchop"));
			amounts[3] = Integer.parseInt(prop.getProperty("raw_fish"));
			amounts[4] = Integer.parseInt(prop.getProperty("cooked_fish"));
			amounts[5] = Integer.parseInt(prop.getProperty("bread"));
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
