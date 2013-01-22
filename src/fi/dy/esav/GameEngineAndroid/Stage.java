/**
 * This code is created by:
 * @author Esa Varemo (2012-2013)
 * It is released with license: 
 * @license This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 *          View it at: http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package fi.dy.esav.GameEngine;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

import fi.dy.esav.GameEngine.enums.ENTITY;

public class Stage extends JFrame {
	
	/**
	 * Generated class serial version UID
	 */
	private static final long serialVersionUID = -2864189451696026929L;

	private GameEngine engine;
	private InputHandler inputhandler;
	
	private Image backgroundImage;
	
	/**
	 * Disabled the default constructor method
	 */
	@SuppressWarnings("unused")
	private Stage() {}
	
	/**
	 * Constructor
	 * @param The main GameEngine instance
	 */
	public Stage(GameEngine engine) {
		this.engine = engine;
		
		InputState inputstate = new InputState(); 
		this.inputhandler = new InputHandler(inputstate);
		this.addKeyListener(inputhandler);
		this.addMouseListener(inputhandler);
		this.addMouseMotionListener(inputhandler);
		engine.setInputState(inputstate);
		
		this.pack();
	}

	/**
	 * @return the background
	 */
	public Image getBackgroundImage() {
		return this.backgroundImage;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackgroundImage(Image background) {
		this.backgroundImage = background;
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
	 * @param display Frame graphics to draw on
	 */
	@Override
	public void paint(Graphics graphics) {
		Image buffer = createImage(getWidth(), getHeight());
		Graphics bg = buffer.getGraphics();
	
		@SuppressWarnings("unchecked")
		ArrayList<Entity> entities = (ArrayList<Entity>) engine.getEntities().clone();
		Collections.sort(entities);
		for(Entity ent : entities) {
			if(!ent.getProperties().contains(ENTITY.NO_DRAW)) {
				ent.draw(bg);
			}
		}
		this.getContentPane().getGraphics().drawImage(buffer, 0, 0, this);
	}
}
