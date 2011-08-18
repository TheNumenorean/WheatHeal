package net.lotrcraft.wheatheal;

public class UseChecker {
	public boolean useChecker(int itemVal){
		// DEBUG LINE:
		//WHMain.log.info("[WheatHeal] checking item.");

		switch (itemVal) {
		// Use the itemID passed to determine what the heal amount is
		case 296:
			if (Config.use.get("Wheat").booleanValue()) return true;
			break;  // WHEAT
		case 319:
			if (Config.use.get("RawPork").booleanValue()) return true;
			break;  // RAW_PORKCHOP
		case 320:
			if (Config.use.get("CookedPork").booleanValue()) return true;
			break;  // COOKED_PORKCHOP
		case 349:
			if (Config.use.get("RawFish").booleanValue()) return true;
			break;  // RAW_FISH
		case 350:
			if (Config.use.get("CookedFish").booleanValue()) return true;
			break;  // COOKED_FISH
		case 297:
			if (Config.use.get("Bread").booleanValue()) return true;
			break;  // BREAD
		case 357:
			if (Config.use.get("Cookie").booleanValue()) return true;
			break;  // COOKIE
		case 260:
			if (Config.use.get("Apple").booleanValue()) return true;
			break;  // APPLE
		case 322:
			if (Config.use.get("GoldenApple").booleanValue()) return true;
			break;  // GOLDEN APPLE
		case 282:
			if (Config.use.get("MushroomStew").booleanValue()) return true;
			break;  // MUSHROOM SOUP/STEW
		case 354:
			if (Config.use.get("Cake").booleanValue()) return true;
			break;  // Cake
		case 39:
			if (Config.use.get("BrownMushroom").booleanValue()) return true;
			break;  // MUSHROOM (Brown)
		case 40:
			if (Config.use.get("RedMushroom").booleanValue()) return true;
			break;  // MUSHROOM (Red)
		case 335:
			if (Config.use.get("Milk").booleanValue()) return true;
			break; // Milk
		case 344:
			if (Config.use.get("Egg").booleanValue()) return true;
			break; // Egg
		case 353:
			if (Config.use.get("Sugar").booleanValue()) return true;
			break; // Sugar
		case 338:
			if (Config.use.get("Sugarcane").booleanValue()) return true;
			break; // Sugar Cane
		}
		return false;
	}
}
