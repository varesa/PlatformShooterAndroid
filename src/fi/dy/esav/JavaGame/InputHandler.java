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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
	public boolean onTouch(View view, MotionEvent event) {
		int widthUnit = ((Storage) engine.getCustomStorage()).display
				.getWidth() / 4;
		InputState is = ((Storage) engine.getCustomStorage()).inputstate;
		for (int x = 1; x <= event.getPointerCount(); x++) {
			if (event.getX(x-1) < widthUnit) {
				is.left = true;
				is.right = false;
				is.leftLastUp = System.currentTimeMillis();
			} else if (event.getX(x-1) < 2 * widthUnit) {
				is.right = true;
				is.left = false;
				is.rightLastUp = System.currentTimeMillis();
			} else if (event.getX(x-1) < 3 * widthUnit) {
				is.shoot = true;
				is.shootLastUp = System.currentTimeMillis();
			} else if (event.getX(x-1) < 4 * widthUnit) {
				is.jump = true;
				is.jumpLastUp = System.currentTimeMillis();
			}
		}
		
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
