/**
 * 
 */
package fi.dy.esav.JavaGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region.Op;
import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;

public class ButtonsOverlayEntity extends Entity {

	public ButtonsOverlayEntity(GameEngine engine) {
		super(engine);
		init();
	}

	Paint paintBorder;
	Paint paintBackground;
	
	private void init() {
		paintBorder = new Paint();
		paintBorder.setColor(Color.GRAY);
		paintBorder.setStyle(Paint.Style.STROKE);
		paintBorder.setAlpha(40);
		
		paintBackground = new Paint();
		paintBackground.setColor(Color.GRAY);
		paintBackground.setStyle(Paint.Style.FILL);
		paintBackground.setAlpha(15);
	}
	
	@Override
	public void draw(Canvas c) {
		int dispWidth = ((Storage) engine.getCustomStorage()).display.getWidth();
		int dispHeight = ((Storage) engine.getCustomStorage()).display.getHeight();
		
		c.drawRect(new Rect(0, dispHeight, dispWidth, dispHeight-100), paintBackground);
		
		for(int i = 0; i <= 3; i++) {
			int x = i* (dispWidth / 4);
			int x2 = x + (dispWidth/4);
			c.drawRect(new Rect(x,  dispHeight, x2, dispHeight-100), paintBorder);
		}
	}
}
