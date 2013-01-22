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
import android.view.SurfaceView;

import fi.dy.esav.GameEngineAndroid.enums.ENTITY;

public class Stage extends SurfaceView {
	
	/**
	 * Generated class serial version UID
	 */
	private static final long serialVersionUID = -2864189451696026929L;

	private GameEngine engine;
	private InputHandler inputhandler;
		
	/**
	 * Constructor
	 * @param The main GameEngine instance
	 */
	public Stage(GameEngine engine, Context context) {
		super(context);
		
		this.engine = engine;
		
		InputState inputstate = new InputState(); 
		this.inputhandler = new InputHandler(engine, inputstate);
		//this.addKeyListener(inputhandler);
		//this.addMouseListener(inputhandler);
		//this.addMouseMotionListener(inputhandler);
		engine.setInputState(inputstate);
	}
	
	/**
	 * Get the highest Z value
	 * @return the highest Z value
	 */
	public double getMaxZ() {
		double maxz = -1000;
		for(Entity ent : engine.getEntities()) {
			if(ent.getZ() > maxz) {
				maxz = ent.getZ();
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
			if(ent.getZ() < minz) {
				minz = ent.getZ();
			}
		}
		return minz;
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
		for(Entity ent : entities) {
			if(!ent.getProperties().contains(ENTITY.NO_DRAW)) {
				ent.draw(canvas);
			}
		}
		getHolder().unlockCanvasAndPost(canvas);
	}
}