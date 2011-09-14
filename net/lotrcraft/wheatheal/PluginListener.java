package net.lotrcraft.wheatheal;

import org.bukkit.event.server.ServerListener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.herocraftonline.dev.heroes.Heroes;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PluginListener extends ServerListener {

	public static void hookInit(PluginManager _pm) {
		if(!Config.useBukkitPerms && _pm.isPluginEnabled("Permissions")) {
			Plugin plugin = _pm.getPlugin("Permissions");
			WHMain.nijikoPermissions = ((Permissions) plugin).getHandler();
			WHMain.log.info("[WheatHeal] Permissions detected. Hooked in");
		}
		else if (!Config.useBukkitPerms && _pm.isPluginEnabled("PermissionsEx")){
			WHMain.permissionsEx = PermissionsEx.getPermissionManager();
			WHMain.log.info("[WheatHeal] PermissionsEx detected. Hooked in");
		}
		if (_pm.isPluginEnabled("Heroes")){
			WHMain.log.info("[WheatHeal] Heroes detected. Hooked in");
			WHMain.heroes = (Heroes)_pm.getPlugin("Heroes");
		}
	}

	public void onPluginDisable(PluginDisableEvent event) {
		if (!Config.useBukkitPerms) {
			if(event.getPlugin().getDescription().getName().equals("Permissions") && WHMain.nijikoPermissions != null) {
				WHMain.log.info("[WheatHeal] Permissions was disabled. Falling back to OP only");
				WHMain.nijikoPermissions = null;
			}
			if(event.getPlugin().getDescription().getName().equals("PermissionsEx") && WHMain.permissionsEx != null) {
				WHMain.log.info("[WheatHeal] PermissionsEx was disabled. Falling back to OP only");
				WHMain.permissionsEx = null;
			}
		}
		if (event.getPlugin().getDescription().getName().equals("Heroes") && WHMain.heroes != null){
			WHMain.log.info("[WheatHeal] Heroes was disabled. Disabling support.");
			WHMain.heroes = null;
		}
	}

	public void onPluginEnable(PluginEnableEvent event) {
		if (!Config.useBukkitPerms) {
			if(event.getPlugin().getDescription().getName().equals("Permissions") && WHMain.nijikoPermissions == null) {
				Plugin plugin = event.getPlugin();
				WHMain.nijikoPermissions = ((Permissions)plugin).getHandler();
				WHMain.log.info("[WheatHeal] Permissions was enabled. Hooked in");
			}
			if(event.getPlugin().getDescription().getName().equals("PermissionsEx") && WHMain.permissionsEx == null) {
				WHMain.permissionsEx = PermissionsEx.getPermissionManager();
				WHMain.log.info("[WheatHeal] PermissionsEx was enabled. Hooked in");
			}
		}
		if (event.getPlugin().getDescription().getName().equals("Heroes") && WHMain.heroes != null){
			WHMain.log.info("[WheatHeal] Heroes enabled, hooking in.");
			WHMain.heroes = (Heroes) event.getPlugin();
		}
	}
}
