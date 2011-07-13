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
			
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
		        Player puncher = (Player)e.getDamager();
		        Player punchee = (Player)e.getEntity();
		        
		        int itemID = punchee.getItemInHand().getTypeId();
		        
		        if (itemID == (296 | 319 | 320 | 349 | 350 | 297)){
					event.setCancelled(true);
					if (punchee.getHealth() < 20){
						if (puncher.getItemInHand().getAmount() == 1){
							puncher.setItemInHand(null);
						}else {
							puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount()-1);
						}
						//punchee.setHealth(punchee.getHealth()+WHMain.heal);
						healer.healPlayer(punchee, punchee.getItemInHand().getTypeId());
					}
				// DEBUG ONLY CODE
				} else {
					WHMain.log.info("itemID:" + Integer.toString(itemID));
				// END OF DEBUG ONLY CODE - COMMENT OUT ABOVE LINES 
				}
				
			}
		}
	}

}
