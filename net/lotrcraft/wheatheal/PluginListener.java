package net.lotrcraft.wheatheal;

import org.bukkit.event.server.ServerListener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PluginListener extends ServerListener {

	public PluginListener() {

	}

	public void onPluginDisable(PluginDisableEvent event) {
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

	public void onPluginEnable(PluginEnableEvent event) {
		if (!WHMain.useBukkitPerms) {
			if(event.getPlugin().getDescription().getName().equals("Permissions")) {
				Plugin plugin = event.getPlugin();
				WHMain.nijikoPermissions = ((Permissions)plugin).getHandler();
				WHMain.log.info("[WheatHeal] Permissions was enabled. Hooked in");
			}
		} else {
			if(event.getPlugin().getDescription().getName().equals("PermissionsBukkit")) {
				WHMain.bukkitPermissions = true;
				WHMain.log.info("[Wheatheal] PermissionsBukkit was enabled. Hooked in");
			}
		}
	}

}
