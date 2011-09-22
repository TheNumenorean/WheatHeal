package net.lotrcraft.wheatheal.tools;

import java.util.List;

import net.lotrcraft.wheatheal.Config;

public class ToolModifier {
	
	public static List<Tool> getToolList(){
		return Config.getToolList();
	}
	
	//Just takes the input and returns a Tool with those values. DOESNT add them to the lists.
	public static Tool createTool(String name, int id, int healValue, int type, int damageOnUse){
		Tool tmpTool = new Tool();
		tmpTool.setName(name);
		tmpTool.setId(id);
		tmpTool.setHealValue(healValue);
		tmpTool.setType(type);
		tmpTool.setDamageOnUse(damageOnUse);
		
		return tmpTool;
	}
	
	public static boolean addTool(String name, int id, int healValue, int type, int damageOnUse){
		return Config.addTool(createTool(name, id, healValue, type, damageOnUse));
	}
	
	public static boolean addTool(Tool tool){
		return Config.addTool(tool);
	}
	
	public static Tool getTool(String name){
		return Config.getTool(name);
	}
	
	public static boolean removeTool(Tool tool){
		return Config.removeTool(tool);
	}

}
