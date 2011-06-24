package net.lotrcraft.wheatheal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class WHListener extends EntityListener{
	public void onEntityDamage(EntityDamageEvent event) {
		if (event instanceof EntityDamageByEntityEvent){
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
		        Player puncher = (Player)e.getDamager();
		        Player punchee = (Player)e.getEntity();
				if (puncher.getItemInHand().getType().equals(Material.WHEAT)){
					event.setCancelled(true);
					if (punchee.getHealth() < 20){
						if (puncher.getItemInHand().getAmount() == 1){
							puncher.getItemInHand().setType(Material.AIR);
						}else {
							puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount()-1);
						}
						punchee.setHealth(punchee.getHealth()+WHMain.heal);
					}
				}
				
			}
		}
	}

}
