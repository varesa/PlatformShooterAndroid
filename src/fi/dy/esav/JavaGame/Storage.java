/**
 * 
 */
package fi.dy.esav.JavaGame;

import android.view.Display;
import fi.dy.esav.GameEngineAndroid.CustomStorage;
import fi.dy.esav.GameEngineAndroid.GameEngine;

/**
 * @author Oppilas
 *
 */
public class Storage extends CustomStorage {

	/**
	 * @param engine
	 */
	public Storage(GameEngine engine) {
		super(engine);
	}
	
	public World world;
	public Display display;
	public InputState inputstate = new InputState();

}
