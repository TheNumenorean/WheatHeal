package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

public class WHListener extends EntityListener{

	// Create a new instance of our Healer class
	private Healer healer = WHMain.healer;;

	public void onEntityDamage(EntityDamageEvent event) {
		//if (WHMain.use) { 
			if (event instanceof EntityDamageByEntityEvent){
				EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;

				if ((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player)){
					Player puncher = (Player)e.getDamager();
					Player punchee = (Player)e.getEntity();
					int itemID = punchee.getItemInHand().getTypeId();

					// Only perform the code if one of the itemID's is detected in the puncher's hand
					if (itemID == 296 || itemID == 297 || itemID == 319 || itemID == 320 || itemID == 349 || itemID == 350
							|| itemID == 357 || itemID == 260 || itemID == 322) {
						// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
						WHMain.log.info("Healer - ItemID:" + itemID + " Punchee:" + punchee.getName()+ " Puncher:" + puncher.getName());
						// Cancel the event to prevent any damage being caused to the player being punched
						event.setCancelled(true);
						
						// Decrease itemInHand amount by 1 or remove if the player only had 1 of the item
						if (punchee.getItemInHand().getAmount() > 1) {
							punchee.setItemInHand(new ItemStack(itemID, punchee.getItemInHand().getAmount() -1));
						} else if (punchee.getItemInHand().getAmount() == 1) {
							punchee.setItemInHand(null); // Unsure if this should be 'null' or 'new ItemStack(null)'
						}
											
						// Finally call healPlayer in our healer class and pass in the punchee and the itemID
						healer.healPlayer(punchee, itemID);
					}
					
					// If block for punching with mushroom stew in hand
					if (itemID == 282) {
						// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
						WHMain.log.info("Healer - ItemID:" + itemID + " Punchee:" + punchee.getName()+ " Puncher:" + puncher.getName());
						event.setCancelled(true);
						
						// Remove 1 mushroom stew and add an empty bowl to inventory if more than 1 mushroom stew is in hand
						if (punchee.getItemInHand().getAmount() > 1) {
							punchee.setItemInHand(new ItemStack(itemID, punchee.getItemInHand().getAmount() - 1));
							punchee.getInventory().addItem(new ItemStack(281, 1));
						} // Remove mushroom stew and add an empty bowl to inventory if only 1 mushroom stew is in hand
						else if (punchee.getItemInHand().getAmount() == 1) {
							punchee.setItemInHand(null);
							punchee.getInventory().addItem(new ItemStack(281, 1));
						}
						
						// Finally call healPlayer in our healer class and pass in the punchee and the itemID
						healer.healPlayer(punchee, itemID);
					}
					
				}
			//}

		}
	}
}
