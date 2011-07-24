package net.lotrcraft.wheatheal;

import org.bukkit.event.server.ServerListener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PluginListener extends ServerListener {
	
	public static void hookInit(PluginManager _pm) {
		if(!WHMain.useBukkitPerms && _pm.isPluginEnabled("Permissions")) {
			Plugin plugin = _pm.getPlugin("Permissions");
			WHMain.nijikoPermissions = ((Permissions) plugin).getHandler();
			WHMain.log.info("[WheatHeal] Permissions detected. Hooked in");
		} else if (WHMain.useBukkitPerms && _pm.isPluginEnabled("PermissionsBukkit")){
			WHMain.bukkitPermissions = true;
			WHMain.log.info("[WheatHeal] PermissionsBukkit detected. Hooked in");
		}
	}
	
	
	public void onPluginDisable(PluginDisableEvent event) {
		if (!WHMain.useBukkitPerms) {
			if(event.getPlugin().getDescription().getName().equals("Permissions") && WHMain.nijikoPermissions != null) {
				WHMain.log.info("[WheatHeal] Permissions was disabled. Falling back to OP only");
				WHMain.nijikoPermissions = null;
			}
		} else {
			if(event.getPlugin().getDescription().getName().equals("PermissionsBukkit") && WHMain.bukkitPermissions) {
				WHMain.log.info("[WheatHeal] PermissionsBukkit was disabled. Falling back to OP only");
				WHMain.bukkitPermissions = false;
			}
		}
	}

	public void onPluginEnable(PluginEnableEvent event) {
		if (!WHMain.useBukkitPerms) {
			if(event.getPlugin().getDescription().getName().equals("Permissions") && WHMain.nijikoPermissions == null) {
				Plugin plugin = event.getPlugin();
				WHMain.nijikoPermissions = ((Permissions)plugin).getHandler();
				WHMain.log.info("[WheatHeal] Permissions was enabled. Hooked in");
			}
		} else {
			if(event.getPlugin().getDescription().getName().equals("PermissionsBukkit") && WHMain.bukkitPermissions == false) {
				WHMain.bukkitPermissions = true;
				WHMain.log.info("[Wheatheal] PermissionsBukkit was enabled. Hooked in");
			}
		}
	}

}
