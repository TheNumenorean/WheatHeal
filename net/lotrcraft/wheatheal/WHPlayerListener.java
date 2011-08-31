package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class WHPlayerListener extends PlayerListener {

	private Healer healer = WHMain.healer;

	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		int item = player.getItemInHand().getTypeId();

		if (item == 296 && event.getAction() == Action.RIGHT_CLICK_BLOCK){ // If Right-Click with Wheat in hand continue

			// Cancel the event, just so nothing bad can happen
			event.setCancelled(true);

			if (player.getHealth() == 20) return; //If your health is 20 you cant heal yourself obviously!
			healer.healPlayer(player, item); //Now heal the player

				// Decrease itemInHand amount by 1 or remove if the player only had 1 of the item
			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}
			else {
				player.setItemInHand(null);
			}
		}
		else if (item == 296 && event.getAction()== Action.RIGHT_CLICK_AIR){ // If Right-Click with Wheat in hand continue

			// Cancel the event, just so nothing bad can happen
			event.setCancelled(true);

			if (player.getHealth() == 20) return; //If your health is 20 you cant heal yourself obviously!
			healer.healPlayer(player, item); //Now heal the player

			// Decrease itemInHand amount by 1 or remove if the player only had 1 of the item
			if (player.getItemInHand().getAmount() > 1) {
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			}
			else {
				player.setItemInHand(null);
			}
		}
	}
}
