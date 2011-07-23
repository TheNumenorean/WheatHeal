package net.lotrcraft.wheatheal;

public class UseChecker {
	public static boolean useChecker(int itemVal){
		switch (itemVal) {
		// Use the itemID passed to determine what the heal amount is
		case 296: 
			if (WHMain.use[0]) return true;
			break;  // WHEAT
		case 319: 
			if (WHMain.use[1]) return true;
			break;  // RAW_PORKCHOP
		case 320: 
			if (WHMain.use[2]) return true;
			break;  // COOKED_PORKCHOP
		case 349: 
			if (WHMain.use[3]) return true;
			break;  // RAW_FISH
		case 350: 
			if (WHMain.use[4]) return true;
			break;  // COOKED_FISH
		case 297: 
			if (WHMain.use[5]) return true;
			break;  // BREAD
		case 357: 
			if (WHMain.use[6]) return true;
			break;  // COOKIE
		case 260: 
			if (WHMain.use[7]) return true;
			break;  // APPLE
		case 322: 
			if (WHMain.use[8]) return true;
			break;  // GOLDEN APPLE
		case 282: 
			if (WHMain.use[9]) return true;
			break;  // MUSHROOM SOUP/STEW
		}
		return false;
	}
}
