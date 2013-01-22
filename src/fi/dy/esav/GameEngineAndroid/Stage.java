/**
 * This code is created by:
 * @author Esa Varemo (2012-2013)
 * It is released with license: 
 * @license This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 *          View it at: http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package fi.dy.esav.GameEngineAndroid;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.SurfaceView;

import fi.dy.esav.GameEngineAndroid.enums.ENTITY;
import fi.dy.esav.JavaGame.InputHandler;
import fi.dy.esav.JavaGame.Storage;

public class Stage extends SurfaceView {
	
	/**
	 * Generated class serial version UID
	 */
	private static final long serialVersionUID = -2864189451696026929L;

	private GameEngine engine;
	private InputHandler inputhandler;
	
	Paint paint;
	Display display;
		
	/**
	 * Constructor
	 * @param The main GameEngine instance
	 */
	public Stage(GameEngine engine, Context context) {
		super(context);
		
		this.engine = engine;
		
		
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);
		
		display = ((Storage) engine.getCustomStorage()).display;
	}
	
	/**
	 * Get the highest Z value
	 * @return the highest Z value
	 */
	public double getMaxZ() {
		double maxz = -1000;
		for(Entity ent : engine.getEntities()) {
			if(ent.z > maxz) {
				maxz = ent.z;
			}
		}
		return maxz;
	}
	
	/**
	 * Get the smallest Z value
	 * @return the smallest Z value  
	 */
	public double getMinZ() {
		double minz = 1000;
		for(Entity ent : engine.getEntities()) {
			if(ent.z < minz) {
				minz = ent.z;
			}
		}
		return minz;
	}
	
	public int stageWidth = 0;
	public int stageHeight = 0;
	
	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);
		stageWidth  = getWidth();
		stageHeight = getHeight();
	}
	
	/**
	 * Draw all entities on screen
	 */
	public void draw() {
		while(!getHolder().getSurface().isValid()) {}
		
		Canvas canvas = getHolder().lockCanvas();
	
		@SuppressWarnings("unchecked")
		ArrayList<Entity> entities = (ArrayList<Entity>) engine.getEntities().clone();
		Collections.sort(entities);

		canvas.drawRect(new Rect(0, 0, display.getWidth(), display.getHeight()), paint);
		canvas.drawColor(Color.WHITE);
		for(Entity ent : entities) {
			if(!ent.properties.contains(ENTITY.NO_DRAW)) {
				ent.draw(canvas);
			}
		}
		getHolder().unlockCanvasAndPost(canvas);
	}
}