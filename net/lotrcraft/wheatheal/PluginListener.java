package net.lotrcraft.wheatheal;

import org.bukkit.event.server.ServerListener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import com.nijiko.permissions.PermissionHandler;

public class PluginListener extends ServerListener {

	public PluginListener() {

	}

	public void onDisable(PluginDisableEvent event) {
		if (!WHMain.useBukkitPerms) {
			if(event.getPlugin().getDescription().getName().equals("Permissions")) {
				WHMain.log.info("[WheatHeal] Permissions was disabled. Falling back to OP only");
				WHMain.nijikoPermissions = null;
			}
		} else {
			if(event.getPlugin().getDescription().getName().equals("PermissionsBukkit")) {
				WHMain.log.info("[WheatHeal] PermissionsBukkit was disabled. Falling back to OP only");
				WHMain.bukkitPermissions = false;
			}
		}
	}

	public void onEnable(PluginEnableEvent event) {
		if (!WHMain.useBukkitPerms) {
			if(event.getPlugin().getDescription().equals("Permissions")) {
				WHMain.nijikoPermissions = (PermissionHandler)event.getPlugin();
				WHMain.log.info("[WheatHeal] Permissions was enabled. Hooked in");
			}
		} else {
			if(event.getPlugin().getDescription().equals("PermissionsBukkit")) {
				WHMain.bukkitPermissions = true;
				WHMain.log.info("[Wheatheal] PermissionsBukkit was enabled. Hooked in");
			}
		}
	}

}
