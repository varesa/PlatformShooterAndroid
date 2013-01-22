/**
 * This code is created by:
 * @author Esa Varemo (2012-2013)
 * It is released with license: 
 * @license This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 *          View it at: http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package fi.dy.esav.GameEngineAndroid;

import java.util.ArrayList;

import android.content.Context;

public class GameEngine {
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> entitiesToAdd = new ArrayList<Entity>();
	private ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
	public Stage stage;
	
	public RenderThread renderer;
	public Thread rendererThread;
	
	public Context context;
	CustomStorage storage;

	/**
	 * Constructor method.
	 */
	public GameEngine(Context context) {
		stage = new Stage(this, context);
		this.context = context;
	}
	
	public CustomStorage getCustomStorage() {
		return storage;
	}
	
	public void setCustomStorage(CustomStorage storage) {
		this.storage = storage;
	}
	
	/**
	 * Start the engine (initialization + show)
	 */
	public void start() {
		renderer = new RenderThread(this);
		rendererThread = new Thread(renderer);
		rendererThread.start();
	}
	
	/**
	 * Stop the engine
	 */
	public void stop() {
		renderer.stop();
	}
	
	/** 
	 * Add an entity to the entities
	 * @param ent Entity to be added
	 * @return finishing status of the operation
	 */
	public synchronized boolean addEntity(Entity ent) {
		return entitiesToAdd.add(ent);
	}
	
	/**
	 * Remove an entity from entities
	 * @param ent entity to be removed
	 * @return finishing status of the operation
	 */
	public synchronized boolean removeEntity(Entity ent) {
		return entitiesToRemove.add(ent);
	}
	
	/**
	 * An method to be run after a render/physics cycle
	 * to maintain things like the entity arrays
	 */
	void maintain() {
		for(Entity ent: entitiesToAdd) {
			entities.add(ent);
		}
		entitiesToAdd.clear();
		for(Entity ent: entitiesToRemove) {
			entities.remove(ent);
		}
		entitiesToRemove.clear();
	}
	
	/**
	 * Get all entities
	 * @return ArrayList containing all the entities
	 */
	public synchronized ArrayList<Entity> getEntities() {
		return this.entities;
	}
	
}
