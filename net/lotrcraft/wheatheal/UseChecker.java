package net.lotrcraft.wheatheal;

public class UseChecker {
	public boolean useChecker(int itemVal){
		switch (itemVal) {
		// Use the itemID passed to determine what the heal amount is
		case 296:
			if (Config.use[0]) return true;
			break;  // WHEAT
		case 319:
			if (Config.use[1]) return true;
			break;  // RAW_PORKCHOP
		case 320:
			if (Config.use[2]) return true;
			break;  // COOKED_PORKCHOP
		case 349:
			if (Config.use[3]) return true;
			break;  // RAW_FISH
		case 350:
			if (Config.use[4]) return true;
			break;  // COOKED_FISH
		case 297:
			if (Config.use[5]) return true;
			break;  // BREAD
		case 357:
			if (Config.use[6]) return true;
			break;  // COOKIE
		case 260:
			if (Config.use[7]) return true;
			break;  // APPLE
		case 322:
			if (Config.use[8]) return true;
			break;  // GOLDEN APPLE
		case 282:
			if (Config.use[9]) return true;
			break;  // MUSHROOM SOUP/STEW
		case 354:
			if (Config.use[10]) return true;
			break;  // Cake
		case 39:
			if (Config.use[11]) return true;
			break;  // MUSHROOM (Brown)
		case 40:
			if (Config.use[12]) return true;
			break;  // MUSHROOM (Red)
		case 335:
			if (Config.use[13]) return true;
			break; // Milk
		case 344:
			if (Config.use[14]) return true;
			break; // Egg
		case 353:
			if (Config.use[15]) return true;
			break; // Sugar
		case 338:
			if (Config.use[16]) return true;
			break; // Sugar Cane
		}
		return false;
	}
}
