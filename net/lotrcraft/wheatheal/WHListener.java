package net.lotrcraft.wheatheal;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import net.lotrcraft.wheatheal.tools.Tool;

import com.herocraftonline.dev.heroes.persistence.Hero;

public class WHListener extends EntityListener{

	// Create a new instance of our Healer class
	private Healer healer = WHMain.healer;
	private Feeder feeder = WHMain.feeder;
	private UseChecker checker = WHMain.checker;
	private Tool tool = new Tool();

	public void onEntityDamage(EntityDamageEvent event) {
			if (event instanceof EntityDamageByEntityEvent){
				EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;

				if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
					Hero hero = null;
					if (WHMain.heroes != null){
						hero = WHMain.heroes.getHeroManager().getHero((Player)e.getEntity());
					}
					Player puncher = (Player)e.getDamager();
					Player punchee = (Player)e.getEntity();
					int itemID = puncher.getItemInHand().getTypeId();


					// Only perform the code if one of the itemID's is detected in the puncher's hand and is set to be used
					if (checker.useChecker(itemID)) {
						// DEBUG LINE BELOW - REMOVE ONCE TESTING IS COMPLETE
						//WHMain.log.info("Healer - ItemID:" + itemID + " Punchee:" + punchee.getName()+ " Puncher:" + puncher.getName());

						// Cancel the event to prevent any damage being caused to the player being punched
						event.setCancelled(true);

						if (punchee.getHealth() == Config.maxHealth && hero == null) return;  //If punchee health is at max you cant heal them
						else if (hero != null && hero.getHealth() == hero.getMaxHealth()) return; //If the health of the hero is at max do not heal him

						if (!permissionsCheck.check(puncher, "wheatheal.heal")){
							// DEBUG LINES. COMMENT OUT IF NOT WANTED IN MAIN RELEASES
							//puncher.sendMessage(ChatColor.GREEN + "[WheatHeal] " + ChatColor.RED + "You don't have permission to heal players!");
							//punchee.sendMessage(ChatColor.GREEN + "[WheatHeal] " + ChatColor.AQUA + puncher.getName() +
							//		ChatColor.RED + " tried to heal you but doesn't have permission!");
							return;
						}

						if (itemID == 282) { 					// If block for punching with mushroom stew in hand

							// Remove 1 mushroom stew and add an empty bowl to inventory if more than 1 mushroom stew is in hand
							if (puncher.getItemInHand().getAmount() > 1) {
								puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount() - 1);
								puncher.getInventory().addItem(new ItemStack(281, 1));
							} // Remove mushroom stew and add an empty bowl to inventory if only 1 mushroom stew is in hand
							else if (puncher.getItemInHand().getAmount() == 1) {
								puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount() - 1);
								puncher.getInventory().addItem(new ItemStack(281, 1));
							}
						}
						else {
							// Decrease itemInHand amount by 1 or remove if the player only had 1 of the item
							if (puncher.getItemInHand().getAmount() > 1) {
								puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount() - 1);
							}
							else {
								puncher.setItemInHand(null);
							}
						}

						// Finally call healPlayer in our healer class and pass in the punchee and the itemID
						if (hero == null){
							if (Config.oldHeal)
								healer.healPlayer(punchee, itemID);
							else
								feeder.feedPlayer(punchee, itemID);
						}
						else {
							// TODO: Heroes and Hunger/Food!
							healer.healPlayer(hero, itemID, hero.getMaxHealth());
						}

						//else {
						//	  puncher.sendMessage(ChatColor.RED + "You do not have permission to do this");
						//}
					}

					if ((tool = ToolChecker.checkTool(itemID)) != null){
						if (tool.getType() == 1){
							if (puncher.getItemInHand().getAmount() == 1){
								puncher.setItemInHand(null);
							} else {
								puncher.getItemInHand().setAmount(puncher.getItemInHand().getAmount() - 1);
							}
						} else {
							puncher.getItemInHand().setDurability((short) (puncher.getItemInHand().getDurability() - tool.getDamageOnUse()));
						}

						if (hero == null)
							punchee.setHealth(punchee.getHealth() + tool.getHealValue());
						else {
							hero.setHealth(hero.getHealth() + tool.getHealValue());
							if (hero.getHealth() > hero.getMaxHealth())
								hero.setHealth(hero.getMaxHealth());
						}
					}
				}

				// Called if someone throws an egg on someone else
				if (e.getEntity() instanceof Player && e.getDamager() instanceof Egg){

					// Check if healing with egg is enabled, return if false
					if (!Config.use.get("Egg").booleanValue()){
						return;
					}
					Hero hero = null;
					if (WHMain.heroes != null){
						hero = WHMain.heroes.getHeroManager().getHero((Player)e.getEntity());
					}
					event.setCancelled(true);
					Egg egg = (Egg) e.getDamager();
					Player puncher = (Player) egg.getShooter();
					Player punchee = (Player) e.getEntity();

					if (punchee.getHealth() == Config.maxHealth) return;  //If punchee health is 20 you cant heal them

					// Check if puncher has permission to heal other players
					if (!permissionsCheck.check(puncher, "wheatheal.heal")){
						return;
					}

					// Finally call healPlayer in our healer class and pass in the punchee and the itemID
					if (hero == null){
						if (Config.oldHeal)
							healer.healPlayer(punchee, 344);
						else
							feeder.feedPlayer(punchee, 344);
					}
					else {
						// TODO: Heroes and Hunger/Food!
						healer.healPlayer(hero, 344, hero.getMaxHealth());
					}

					// DEBUG LINE
					//puncher.sendMessage("You did hit and heal with an egg!");
				}
			}
	}

	@Override
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (event.isCancelled())
			return;
		if (!(event.getEntity() instanceof Player))
			return;
		// Disabling dropping of the FoodBar
		if (Config.oldHeal)
			event.setCancelled(true);
	}

	@Override
	public void onEntityRegainHealth (EntityRegainHealthEvent event) {
		if (event.isCancelled())
			return;
		if (!(event.getEntity() instanceof Player))
			return;
		// Disabling regaining health with a full FoodBar
		if (Config.oldHeal && event.getRegainReason().equals("SATIATED"))
			event.setCancelled(true);
	}
}
