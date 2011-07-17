package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;

public class Healer {
	
	//private ConfigHandler config = WHMain.config;
	
	// CONSTRUCTOR - Not in use
	public void ConfigHandler() {
		
	}

	public void healPlayer(Player player, int itemID) {
		switch (itemID) {
		// Use the itemID passed to determine what the heal amount is
		case 296: player.setHealth(player.getHealth() + WHMain.amounts[0]); break;  // WHEAT
		case 319: player.setHealth(player.getHealth() + WHMain.amounts[1]); break;  // RAW_PORKCHOP
		case 320: player.setHealth(player.getHealth() + WHMain.amounts[2]); break;  // COOKED_PORKCHOP
		case 349: player.setHealth(player.getHealth() + WHMain.amounts[3]); break;  // RAW_FISH
		case 350: player.setHealth(player.getHealth() + WHMain.amounts[4]); break;  // COOKED_FISH
		case 297: player.setHealth(player.getHealth() + WHMain.amounts[5]); break;  // BREAD		
		}//Need to add check whether should use.
		//if WHMain.use[0]
		
	}
	
}
