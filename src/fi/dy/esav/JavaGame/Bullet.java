package fi.dy.esav.JavaGame;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.GameEngineAndroid.Utils;
import fi.dy.esav.JavaGame.enums.DIRECTION;

public class Bullet extends Entity {
	
	private double speed = 10;
	private double damage = 11;
	private DIRECTION direction = null;
	
	int color = Color.GRAY;

	public Bullet(GameEngine engine) {
		super(engine);
	}
	
	public Bullet(Point pos, DIRECTION direction, GameEngine engine) {
		super(pos, engine);
		this.direction = direction;
	}
	
	public Bullet(double x, double y, DIRECTION direction, GameEngine engine) {
		super(x, y, engine);
		this.direction = direction;
	}
	
	@Override
	public void act() {
		if(this.direction == DIRECTION.LEFT) {
			this.x -= this.speed;
		} else if (this.direction == DIRECTION.RIGHT) {
			this.x += this.speed;
		} else {
			try {
				throw new Exception("Direction for bullet not specified");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<Entity> entities = (ArrayList<Entity>) engine.getEntities().clone();
		for(Entity ent : entities) {
			if(ent instanceof EnemyEntity) {
				if(Utils.simpleHitTest(this, ent)) {
					((EnemyEntity) ent).damage(damage);
					engine.removeEntity(this);
				}
			}
		}
		if(this.x < 0 || this.x > engine.getStage().getWidth()) {
			engine.removeEntity(this);
		}
	}
	
	@Override
	public void draw(Canvas c) {
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		c.drawRect(new RectF((int)x, (int)y, 10, 5), paint);
	}
	
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * @return the direction
	 */
	public DIRECTION getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(DIRECTION direction) {
		this.direction = direction;
	}
	
	@Override
	public int getWidth() {
		return 10;
	}

	public int getHeight() {
		return 5;
	}

}
