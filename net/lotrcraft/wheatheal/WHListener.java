package net.lotrcraft.wheatheal;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class WHListener extends EntityListener{

	private Healer healer = WHMain.healer;;

	public void onEntityDamage(EntityDamageEvent event) {

		if (event instanceof EntityDamageByEntityEvent){
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;

			if ((e.getEntity() instanceof Player) && (e.getDamager() instanceof Player)){
				Player puncher = (Player)e.getDamager();
				Player punchee = (Player)e.getEntity();
				int itemID = punchee.getItemInHand().getTypeId();
				
				if (itemID == 296 || itemID == 297 || itemID == 319 || itemID == 320 || itemID == 349 || itemID == 350) {
					WHMain.log.info("ItemID:" + itemID + " Punchee:" + punchee.getName()+ " Puncher:" + puncher.getName());
					healer.healPlayer(punchee, itemID);
				}
			}

		}
	}
}
