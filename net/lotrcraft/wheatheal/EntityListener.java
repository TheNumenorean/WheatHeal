package net.lotrcraft.wheatheal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityListener implements Listener {
	public void onEntityDamage(EntityDamageEvent event) {
		if (event instanceof EntityDamageByEntityEvent){
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
			if (event.getEntity() instanceof Player && e.getDamager() instanceof Player){
		        Player puncher = (Player)e.getDamager();
		        Player punchee = (Player)e.getEntity();
				if (puncher.getItemInHand().equals(Material.WHEAT)){
					e.setCancelled(true);
					puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount()-1);
					punchee.setHealth(punchee.getHealth()+WHMain.heal);
				}
				
			}
		}
	}

}
