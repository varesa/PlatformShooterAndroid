/**
 * 
 */
package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngineAndroid.GameEngine;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * @author Oppilas
 *
 */
public class InputHandler implements OnTouchListener {

	GameEngine engine;
	
	/**
	 * @param engine
	 */
	public InputHandler(GameEngine engine) {
		this.engine = engine;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
	 */
	public boolean onTouch(View v, MotionEvent event) {
		int widthUnit = ((Storage)engine.getCustomStorage()).display.getHeight()/4;
		InputState is = ((Storage)engine.getCustomStorage()).inputstate;
		if(event.getX() < widthUnit) {
			is.left = true;
			is.right = false;
			is.leftLastUp = System.currentTimeMillis();
		} else if (event.getX() < 2*widthUnit) {
			is.right = true;
			is.left = false;
			is.rightLastUp = System.currentTimeMillis();
		} else if (event.getX() < 3*widthUnit) {
			is.shoot = true;
			is.shootLastUp = System.currentTimeMillis();
		} else if (event.getX() < 4*widthUnit) {
			is.jump = true;
			is.jumpLastUp = System.currentTimeMillis();
		}
		
		if(is.left && is.leftLastUp > System.currentTimeMillis() + 5) {
			is.left = false;
		}
		if(is.right && is.rightLastUp > System.currentTimeMillis() + 5) {
			is.right = false;
		}
		if(is.shoot && is.shootLastUp > System.currentTimeMillis() + 5) {
			is.shoot = false;
		}
		if(is.jump && is.jumpLastUp > System.currentTimeMillis() + 5) {
			is.jump = false;
		}
		
		return false;
	}

}
