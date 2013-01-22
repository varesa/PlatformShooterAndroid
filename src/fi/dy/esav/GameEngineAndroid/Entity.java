/**
 * This code is created by:
 * @author Esa Varemo (2012-2013)
 * It is released with license: 
 * @license This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 *          View it at: http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package fi.dy.esav.GameEngineAndroid;

import java.util.EnumSet;

import android.graphics.Canvas;
import android.graphics.Point;

import fi.dy.esav.GameEngineAndroid.enums.ENTITY;

public class Entity implements Comparable<Entity>{
	
	protected GameEngine engine;
	
	public EnumSet<ENTITY> properties;
	public double x = 0, y = 0, z = 0;
	
	public double width = 0;
	public double height = 0;
	
	
	/**
	 * Default constructor
	 * @param engine Reference to the GameEngine
	 */
	public Entity(GameEngine engine) {
		this.engine = engine;
		init();
	}
	
	/**
	 * Additional constructor
	 * @param engine Reference to the GameEngine
	 * @param position point to position the entity at
	 */
	public Entity(Point position, GameEngine engine) {
		init();
		this.engine = engine;
		this.setPos(position);
	}
	
	/**
	 * Additional constructor
	 * @param engine Reference to the GameEngine
	 * @param x X-Coordinate to position the entity at
	 * @param y Y-Coordinate to position the entity at
	 */
	public Entity(double x, double y, GameEngine engine) {
		init();
		this.engine = engine;
		this.setPos(x, y);
	}
	
	/**
	 * A initialization method that is common to all constructors
	 */
	private void init() {
		properties = EnumSet.noneOf(ENTITY.class);
	}
	
	/**
	 * Method that is used to draw the entity on the screen (if applicable).
	 * To be implemented by a subclass.
	 * @param g The instance of graphics to draw on.
	 */
	public void draw(Canvas c) {	}
	
	/**
	 * Method to process input, calculate actions & etc. (if applicable)
	 * To be implemented by a subclass.
	 */
	public void act() {	}
		
	/**
	 * Remove special property from entity
	 * @param property Property to be removed
	 * @return If the operation succeeded
	 */
	public boolean removeProperty(ENTITY property) {
		if(this.properties.contains(property)) {
			this.properties.remove(property);
			return true;
		}
		return false;
	}
		
	/**
	 * @return the Point where the Entity is
	 */
	public Point getPos() {
		return new Point((int)this.x, (int)this.y);
	}
	
	/**
	 * @param point  The Point where to set the new position
	 */
	public void setPos(Point newPos) {
		this.x = newPos.x;
		this.y = newPos.y;
	}
	
	/**
	 * @param x X-coordinate for new location
	 * @param y Y-coordinate for new location
	 */
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
		
	/**
	 * Put the selected object to front
	 */
	public void toFront() {
		this.z = engine.stage.getMaxZ() + 10;
	}
	
	/**
	 * Put the selected object to back
	 */
	public void toBack() {
		this.z = engine.stage.getMinZ() - 10;
	}
	
	/**
	 * Method to compare the "depths" of different entities,
	 * for arraylist.sort() to work
	 * @param another entity to compare to
	 * @return difference between the z coordinates of this and other entity
	 */
	public int compareTo(Entity otherEnt) {
		return (int) (this.z - ((Entity)otherEnt).z);
	}
}
