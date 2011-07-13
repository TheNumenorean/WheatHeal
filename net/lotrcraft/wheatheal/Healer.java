package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;

public class Healer {
	
	private ConfigHandler config = WHMain.config;
	
	// CONSTRUCTOR - Not in use
	/*public void ConfigHandler() {
		
	}*/

	public void healPlayer(Player player, int itemID) {
		
		switch (itemID) {
		// Use the itemID passed to determine what the heal amount is
		case 296: player.setHealth(player.getHealth() + config.amounts[0]); // WHEAT
		case 319: player.setHealth(player.getHealth() + config.amounts[1]); // RAW_PORKCHOP
		case 320: player.setHealth(player.getHealth() + config.amounts[2]); // COOKED_PORKCHOP
		case 349: player.setHealth(player.getHealth() + config.amounts[3]); // RAW_FISH
		case 350: player.setHealth(player.getHealth() + config.amounts[4]); // COOKED_FISH
		case 297: player.setHealth(player.getHealth() + config.amounts[5]); // BREAD		
		}
		
	}
	
}
