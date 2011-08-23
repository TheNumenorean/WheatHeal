package net.lotrcraft.wheatheal.api;

import net.lotrcraft.wheatheal.Config;
import net.lotrcraft.wheatheal.WHMain;

public class ValueChanger {

	public void setEnabled(String food, boolean value){
		Config.confEditUse(food, value);
	}
	public void setHealvalue(String food, int value){
		Config.confEditAmount(food, value);
	}
	public void reset(){
		Config.confRestore(WHMain.config);
	}
}
