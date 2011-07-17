package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class WHListener extends EntityListener{

	// Create a new instance of our Healer class
	private Healer healer = WHMain.healer;;

	public void onEntityDamage(EntityDamageEvent event) {

		if (event instanceof EntityDamageByEntityEvent){
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;

			if ((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player)){
				Player puncher = (Player)e.getDamager();
				Player punchee = (Player)e.getEntity();
				int itemID = punchee.getItemInHand().getTypeId();
				
				// Only perform the code if one of the itemID's is detected in the puncher's hand
				if (itemID == 296 || itemID == 297 || itemID == 319 || itemID == 320 || itemID == 349 || itemID == 350) {
					// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
					WHMain.log.info("Healer - ItemID:" + itemID + " Punchee:" + punchee.getName()+ " Puncher:" + puncher.getName());
					// Cancel the event to prevent any damage being caused to the player being punched
					event.setCancelled(true);
					// Finally call healPlayer in our healer class and pass in the punchee and the itemID
					healer.healPlayer(punchee, itemID);
				}
			}

		}
	}
}
