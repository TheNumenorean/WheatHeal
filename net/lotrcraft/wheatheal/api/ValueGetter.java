package net.lotrcraft.wheatheal.api;

import net.lotrcraft.wheatheal.Config;

public class ValueGetter {

	public boolean getStatus(String food){
		return Config.confGetEnabled(food);
	}
	
	public int getHealValue(String food){
		return Config.confGetHealValue(food);
	}
}
