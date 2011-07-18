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
		}

	}

}
