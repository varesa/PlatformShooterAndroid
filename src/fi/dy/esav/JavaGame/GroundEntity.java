package fi.dy.esav.JavaGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.GameEngineAndroid.Rectangle;
import fi.dy.esav.GameEngineAndroid.enums.ENTITY;


public class GroundEntity extends Entity {
	
	private int width = 10, height = 10;
	private int color = Color.GRAY;

	public GroundEntity(GameEngine engine) {
		super(engine);
	}
	
	public GroundEntity(GameEngine engine, Rectangle rect) {
		super(engine);
		
		this.setProperty(ENTITY.NO_ACT);
		
		x = rect.x;
		y = rect.y;
		width = (int) rect.width;
		height = (int) rect.height;
	}
	
	@Override
	public void draw(Canvas c) {
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		c.drawRect(new RectF((int)x, (int)y, (int)x+width, (int)y+height), paint);
	}

	/**
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
