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
		init();
	}
	
	public Bullet(Point pos, DIRECTION direction, GameEngine engine) {
		super(pos, engine);
		this.direction = direction;
		init();
	}
	
	public Bullet(double x, double y, DIRECTION direction, GameEngine engine) {
		super(x, y, engine);
		this.direction = direction;
		init();
	}
	
	Paint paint;
	
	private void init() {
		paint = new Paint();
		paint.setColor(color);
		paint.setStyle(Paint.Style.FILL);
		
		width = 10;
		height = 5;
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
		if(this.x < 0 || this.x > ((Storage) engine.getCustomStorage()).display.getWidth()) {
			engine.removeEntity(this);
		}
	}
	
	@Override
	public void draw(Canvas c) {
		c.drawRect(new RectF((int)x, (int)y, (int)x+10, (int)y+5), paint);
	}
}
