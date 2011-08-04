package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;

public class Healer {

	public void healPlayer(Player player, int itemID) {
		switch (itemID) {
		// Use the itemID passed to determine what the heal amount is
		case 296: player.setHealth(player.getHealth() + WHMain.amounts[0]); break;  // WHEAT
		case 319: player.setHealth(player.getHealth() + WHMain.amounts[1]); break;  // RAW_PORKCHOP
		case 320: player.setHealth(player.getHealth() + WHMain.amounts[2]); break;  // COOKED_PORKCHOP
		case 349: player.setHealth(player.getHealth() + WHMain.amounts[3]); break;  // RAW_FISH
		case 350: player.setHealth(player.getHealth() + WHMain.amounts[4]); break;  // COOKED_FISH
		case 297: player.setHealth(player.getHealth() + WHMain.amounts[5]); break;  // BREAD
		case 357: player.setHealth(player.getHealth() + WHMain.amounts[6]); break;  // COOKIE
		case 260: player.setHealth(player.getHealth() + WHMain.amounts[7]); break;  // APPLE
		case 322: player.setHealth(player.getHealth() + WHMain.amounts[8]); break;  // GOLDEN APPLE
		case 282: player.setHealth(player.getHealth() + WHMain.amounts[9]); break;  // MUSHROOM SOUP/STEW
		case 354: player.setHealth(player.getHealth() + WHMain.amounts[10]); break; // Cake
		case 39: player.setHealth(player.getHealth() + WHMain.amounts[11]); break;  // MUSHROOM (Brown)
		case 40: player.setHealth(player.getHealth() + WHMain.amounts[12]); break;  // MUSHROOM (Red)
		case 335: player.setHealth(player.getHealth() + WHMain.amounts[13]); break; // Milk
		case 344: player.setHealth(player.getHealth() + WHMain.amounts[14]); break; // Egg
		case 353: player.setHealth(player.getHealth() + WHMain.amounts[15]); break; // Sugar
		case 338: player.setHealth(player.getHealth() + WHMain.amounts[16]); break; // Sugar Cane
		}
		if (player.getHealth() > 20){
			player.setHealth(20);
		}

	}

}
/**************************
*Foods still to be added:
*Milk
*Egg
*Sugar
*
***************************/