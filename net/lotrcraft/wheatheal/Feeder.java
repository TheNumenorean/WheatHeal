package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;

import com.herocraftonline.dev.heroes.persistence.Hero;

public class Feeder {

	public void feedPlayer(Player player, int itemID) {
		int foodLevel = player.getFoodLevel();
		switch (itemID) {
		// Use the itemID passed to determine what the heal amount is
		case 296: player.setFoodLevel(foodLevel + Config.amounts.get("Wheat")); break;  // WHEAT
		case 319: player.setHealth(foodLevel + Config.amounts.get("RawPork")); break;  // RAW_PORKCHOP
		case 320: player.setHealth(foodLevel + Config.amounts.get("CookedPork")); break;  // COOKED_PORKCHOP
		case 349: player.setHealth(foodLevel + Config.amounts.get("RawFish")); break;  // RAW_FISH
		case 350: player.setHealth(foodLevel + Config.amounts.get("CookedFish")); break;  // COOKED_FISH
		case 297: player.setHealth(foodLevel + Config.amounts.get("Bread")); break;  // BREAD
		case 357: player.setHealth(foodLevel + Config.amounts.get("Cookie")); break;  // COOKIE
		case 260: player.setHealth(foodLevel + Config.amounts.get("Apple")); break;  // APPLE
		case 322: player.setHealth(foodLevel + Config.amounts.get("GoldenApple")); break;  // GOLDEN APPLE
		case 282: player.setHealth(foodLevel + Config.amounts.get("MushroomStew")); break;  // MUSHROOM SOUP/STEW
		case 354: player.setHealth(foodLevel + Config.amounts.get("Cake")); break; // Cake
		case 39:  player.setHealth(foodLevel + Config.amounts.get("BrownMushroom")); break; // MUSHROOM (Brown)
		case 40:  player.setHealth(foodLevel + Config.amounts.get("RedMushroom")); break; // MUSHROOM (Red)
		case 335: player.setHealth(foodLevel + Config.amounts.get("Milk")); break; // Milk
		case 344: player.setHealth(foodLevel + Config.amounts.get("Egg")); break; // Egg
		case 353: player.setHealth(foodLevel + Config.amounts.get("Sugar")); break; // Sugar
		case 338: player.setHealth(foodLevel + Config.amounts.get("Sugarcane")); break; // Sugar Cane
		}
		if (foodLevel > 20){
			player.setFoodLevel(20);
		}
	}

	// TODO: Change for food, need to see how Heroes handles it.
	public void feedPlayer(Hero hero, int itemID, double maxHealth){
		double health = hero.getHealth();
		switch (itemID) {
		// Use the itemID passed to determine what the heal amount is
		case 296: hero.setHealth(health + Config.amounts.get("Wheat")); break;  // WHEAT
		case 319: hero.setHealth(health + Config.amounts.get("RawPork")); break;  // RAW_PORKCHOP
		case 320: hero.setHealth(health + Config.amounts.get("CookedPork")); break;  // COOKED_PORKCHOP
		case 349: hero.setHealth(health + Config.amounts.get("RawFish")); break;  // RAW_FISH
		case 350: hero.setHealth(health + Config.amounts.get("CookedFish")); break;  // COOKED_FISH
		case 297: hero.setHealth(health + Config.amounts.get("Bread")); break;  // BREAD
		case 357: hero.setHealth(health + Config.amounts.get("Cookie")); break;  // COOKIE
		case 260: hero.setHealth(health + Config.amounts.get("Apple")); break;  // APPLE
		case 322: hero.setHealth(health + Config.amounts.get("GoldenApple")); break;  // GOLDEN APPLE
		case 282: hero.setHealth(health + Config.amounts.get("MushroomStew")); break;  // MUSHROOM SOUP/STEW
		case 354: hero.setHealth(health + Config.amounts.get("Cake")); break; // Cake
		case 39:  hero.setHealth(health + Config.amounts.get("BrownMushroom")); break; // MUSHROOM (Brown)
		case 40:  hero.setHealth(health + Config.amounts.get("RedMushroom")); break; // MUSHROOM (Red)
		case 335: hero.setHealth(health + Config.amounts.get("Milk")); break; // Milk
		case 344: hero.setHealth(health + Config.amounts.get("Egg")); break; // Egg
		case 353: hero.setHealth(health + Config.amounts.get("Sugar")); break; // Sugar
		case 338: hero.setHealth(health + Config.amounts.get("Sugarcane")); break; // Sugar Cane
		}
		if (health > maxHealth){
			hero.setHealth(maxHealth);
		}
	}

}
/**************************
*Foods still to be added:
*chicken
*steak
*
*
***************************/