/**
 * This code is created by:
 * @author Esa Varemo (2012-2013)
 * It is released with license: 
 * @license This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 *          View it at: http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package fi.dy.esav.GameEngineAndroid;

import fi.dy.esav.GameEngineAndroid.enums.ENTITY;

public class RenderThread implements Runnable{
	
	private GameEngine engine;
	
	private int FPS = 40;
	private boolean running = true;
	private boolean paused = false;
	
	/** 
	 * Disabled default constructor
	 */
	@SuppressWarnings("unused")
	private RenderThread() {}
	
	/**
	 * Constructor
	 * @param engine Engine to get the stage, entities & etc. from
	 */
	public RenderThread(GameEngine engine) {
		this.engine = engine;
	}

	public void run() {
		long oldTime = System.currentTimeMillis();
		while(running) {
			
			while(paused) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for(Entity ent : engine.getEntities()) {
				if(!ent.properties.contains(ENTITY.NO_ACT)) {
					ent.act();
				}
			}
			engine.stage.draw();
			
			engine.maintain();
			
			if(System.currentTimeMillis() < oldTime + 1000/FPS) {
				try {
					Thread.sleep(oldTime + 1000/FPS - System.currentTimeMillis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			oldTime = System.currentTimeMillis();
		}
	}
	
	/*
	 * Pause the rendering (but not the thread)
	 */
	public void pause() {
		paused = true;
	}
	
	/*
	 * Resume the rendering (if paused)
	 */
	public void resume() {
		paused = false;
	}
	
	/*
	 * Stop the rendering (and the thread)
	 */
	public void stop() {
		running = false;
	}
	
	
	
}
