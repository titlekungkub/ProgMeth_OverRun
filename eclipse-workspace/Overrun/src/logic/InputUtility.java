// This class deals with input.
package logic;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class InputUtility {

	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();

	public static boolean getKeyPressed(KeyCode keycode) {
		if(!(keyPressed.contains(keycode))){
			return false;
		}
		return true;
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {		
		if(!(keyPressed.contains(keycode))){
			keyPressed.add(keycode);
		}else if(!pressed && keyPressed.contains(keycode)){
			keyPressed.remove(keycode);
		}
	}

	public static boolean getKeyTriggered(KeyCode keycode) {
		if(!(keyTriggered.contains(keycode))){
			return false;
		}
		return true;
	}

	public static void setKeyTriggered(KeyCode keycode, boolean pressed) {
		if(pressed&&!(keyTriggered.contains(keycode))){
			InputUtility.keyTriggered.add(keycode);
		}else if(!pressed && keyTriggered.contains(keycode)){
			keyTriggered.remove(keycode);
		}
	}

	public static void clear(){
		keyPressed.clear();
		keyTriggered.clear();
	}
}
