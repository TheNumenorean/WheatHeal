package net.lotrcraft.wheatheal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

public class WHListener extends EntityListener{

	// Create a new instance of our Healer class
	private Healer healer = WHMain.healer;
	private UseChecker checker = WHMain.checker;

	public void onEntityDamage(EntityDamageEvent event) {
			if (event instanceof EntityDamageByEntityEvent){
				EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;

				if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
					Player puncher = (Player)e.getDamager();
					Player punchee = (Player)e.getEntity();
					int itemID = puncher.getItemInHand().getTypeId();


					// Only perform the code if one of the itemID's is detected in the puncher's hand and is set to be used
					if (checker.useChecker(itemID)) {
						// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
						WHMain.log.info("Healer - ItemID:" + itemID + " Punchee:" + punchee.getName()+ " Puncher:" + puncher.getName());
						// Cancel the event to prevent any damage being caused to the player being punched
						event.setCancelled(true);
						
						if (punchee.getHealth() == 20) return;//If punchee health is 20 you cant heal them
						
						if (itemID == 282) { 					// If block for punching with mushroom stew in hand
							// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
							WHMain.log.info("Healer - ItemID:" + itemID + " Punchee:" + punchee.getName()+ " Puncher:" + puncher.getName());
							event.setCancelled(true);
							
							// Remove 1 mushroom stew and add an empty bowl to inventory if more than 1 mushroom stew is in hand
							if (puncher.getItemInHand().getAmount() > 1) {
								puncher.setItemInHand(new ItemStack(itemID, punchee.getItemInHand().getAmount() - 1));
								puncher.getInventory().addItem(new ItemStack(281, 1));
							} // Remove mushroom stew and add an empty bowl to inventory if only 1 mushroom stew is in hand
							else if (puncher.getItemInHand().getAmount() == 1) {
								puncher.setItemInHand(null);
								puncher.getInventory().addItem(new ItemStack(281, 1));
							}
						} else {
							// Decrease itemInHand amount by 1 or remove if the player only had 1 of the item
							if (puncher.getItemInHand().getAmount() > 1) {
								puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount() - 1);
							} else {
								puncher.getItemInHand().setType(Material.AIR);
							}
						}
											
						// Finally call healPlayer in our healer class and pass in the punchee and the itemID
						healer.healPlayer(punchee, itemID);
					}
					


					
				}
			//}

		}
	}
}
