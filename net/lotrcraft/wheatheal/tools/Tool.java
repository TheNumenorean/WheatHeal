package net.lotrcraft.wheatheal.tools;

public class Tool {
	private String name;
	private int id, type, healValue, damageOnUse;
	
	
	public int getDamageOnUse() {
		return damageOnUse;
	}
	
	public void setDamageOnUse(int damageOnUse) {
		this.damageOnUse = damageOnUse;
	}
	
	public int getHealValue() {
		return healValue;
	}
	
	public void setHealValue(int healValue) {
		this.healValue = healValue;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
