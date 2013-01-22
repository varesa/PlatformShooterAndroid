package fi.dy.esav.GameEngineAndroid;

import java.util.ArrayList;

public class InputState {
	
	ArrayList<Integer> keysDown = new ArrayList<Integer>();
	
	/**
	 * Return the state of a key
	 * @param ADDME The key to be checked
	 * @return Is the key down?
	 */
	public boolean isKeyDown(int key) {
		return keysDown.contains(key);
	}
	
	/**
	 * Return true if key is up
	 * @param ADDME The key to be checked
	 * @return Is the key up?
	 */
	public boolean isKeyUp(int key) {
		return !isKeyDown(key);
	}
	
	/**
	 * @return A copy of the keyboard state
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getKeyboardState() {
		return (ArrayList<Integer>) keysDown.clone();
	}	
}
