/**
 * This code is created by:
 * @author Esa Varemo (2012-2013)
 * It is released with license: 
 * @license This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 *          View it at: http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package fi.dy.esav.GameEngine;

import java.util.ArrayList;

public class GameEngine {
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> entitiesToAdd = new ArrayList<Entity>();
	private ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();
	private Stage stage;
	
	private RenderThread renderer;
	private Thread rendererThread;
	private InputHandler inputhandler;
	private InputState	 inputstate;
	

	/**
	 * Constructor method.
	 */
	public GameEngine() {
		stage = new Stage(this);
		stage.setSize(800, 600);
	}
	
	/**
	 * Start the engine (initialization + show)
	 */
	public void start() {
		
		renderer = new RenderThread(this);
		rendererThread = new Thread(renderer);
		
		this.getStage().setVisible(true);
		rendererThread.start();
	}
	
	/**
	 * Close the window
	 */
	public void close() {
		this.getStage().setVisible(false);
	}
	
	/**
	 * Stop the engine
	 */
	public void stop() {
		renderer.stop();
	}
	
	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
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
	
	/**
	 * Returns the input handler
	 * Visibility: package-private
	 * @return the input handler
	 */
	InputHandler getInputhandler() {
		return inputhandler;
	}

	/**
	 * Sets the inputHandler
	 * Visibility: package-private
	 * @param inputhandler the inputhandler to set
	 */
	synchronized void setInputhandler(InputHandler inputhandler) {
		this.inputhandler = inputhandler;
	}

	/**
	 * Return the state of inputs
	 * @return the inputstate
	 */
	public InputState getInputState() {
		return inputstate;
	}

	/**
	 * Sets the input state container
	 * @param inputstate the input state container
	 */
	synchronized void setInputState(InputState inputstate) {
		this.inputstate = inputstate;
	}
	
}
