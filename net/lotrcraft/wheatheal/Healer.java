package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;

public class Healer {

	public void healPlayer(Player player, int itemID) {
		switch (itemID) {
		// Use the itemID passed to determine what the heal amount is
		case 296: player.setHealth(player.getHealth() + Config.amounts.get("Wheat")); break;  // WHEAT
		case 319: player.setHealth(player.getHealth() + Config.amounts.get("RawPork")); break;  // RAW_PORKCHOP
		case 320: player.setHealth(player.getHealth() + Config.amounts.get("CookedPork")); break;  // COOKED_PORKCHOP
		case 349: player.setHealth(player.getHealth() + Config.amounts.get("RawFish")); break;  // RAW_FISH
		case 350: player.setHealth(player.getHealth() + Config.amounts.get("CookedFish")); break;  // COOKED_FISH
		case 297: player.setHealth(player.getHealth() + Config.amounts.get("Bread")); break;  // BREAD
		case 357: player.setHealth(player.getHealth() + Config.amounts.get("Cookie")); break;  // COOKIE
		case 260: player.setHealth(player.getHealth() + Config.amounts.get("Apple")); break;  // APPLE
		case 322: player.setHealth(player.getHealth() + Config.amounts.get("GoldenApple")); break;  // GOLDEN APPLE
		case 282: player.setHealth(player.getHealth() + Config.amounts.get("MushroomStew")); break;  // MUSHROOM SOUP/STEW
		case 354: player.setHealth(player.getHealth() + Config.amounts.get("Cake")); break; // Cake
		case 39:  player.setHealth(player.getHealth() + Config.amounts.get("BrownMushroom")); break; // MUSHROOM (Brown)
		case 40:  player.setHealth(player.getHealth() + Config.amounts.get("RedMushroom")); break; // MUSHROOM (Red)
		case 335: player.setHealth(player.getHealth() + Config.amounts.get("Milk")); break; // Milk
		case 344: player.setHealth(player.getHealth() + Config.amounts.get("Egg")); break; // Egg
		case 353: player.setHealth(player.getHealth() + Config.amounts.get("Sugar")); break; // Sugar
		case 338: player.setHealth(player.getHealth() + Config.amounts.get("Sugarcane")); break; // Sugar Cane
		}
		if (player.getHealth() > 20){
			player.setHealth(20);
		}

	}

}
/**************************
*Foods still to be added:
*
*
***************************/