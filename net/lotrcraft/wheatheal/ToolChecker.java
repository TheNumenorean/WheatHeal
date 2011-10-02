package net.lotrcraft.wheatheal;

import java.util.List;

import net.lotrcraft.wheatheal.tools.Tool;

public class ToolChecker {

	public static Tool checkTool(int id) {
		List<Tool> tools = Config.getToolList();
		if (tools == null)
			return null;
		for (int y = 0; y < tools.size(); y++){
			if (tools.get(y).getId() == id) {
				return tools.get(y);
			}
		}
		return null;
	}
}
