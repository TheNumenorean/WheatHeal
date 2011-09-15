package net.lotrcraft.wheatheal.api;

import net.lotrcraft.wheatheal.Config;
import net.lotrcraft.wheatheal.WHMain;

public class ValueChanger {

	public boolean setEnabled(String food, boolean value){
		return Config.confEditUse(food, value);
	}
	public boolean setHealvalue(String food, int value){
		return Config.confEditAmount(food, value);
	}
	public void reset(){
		Config.confRestore(WHMain.config);
	}
}
