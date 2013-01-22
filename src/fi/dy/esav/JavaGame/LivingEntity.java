package fi.dy.esav.JavaGame;


import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.JavaGame.enums.DIRECTION;

public class LivingEntity extends Entity {

	protected int width = 64;
	protected int height = 64;
	
	protected int color = Color.GRAY;
	
	protected double xacc = 1.5;
	protected double xdec = 0.75;

	protected double jumpvel = 12;
	protected double ydec = 0.33;
	
	protected double maxxvel = 7;
	protected double maxyvel = 12;
	
	protected double xvel = 0;
	protected double yvel = 0;
	
	protected double xtarget = 0;
	protected boolean jump = false;
	
	protected DIRECTION facing = DIRECTION.RIGHT;
	
	public LivingEntity(GameEngine engine) {
		super(engine);
	}
	
	@Override
	public void draw(Canvas c) {
		Paint paint = new Paint();
		paint.setColor(color);
		c.drawRect(new RectF((int)x, (int)y, 64, 64), paint);
	}
	
	@Override
	public void act() {
		@SuppressWarnings("unchecked")
		ArrayList<Entity> entities = (ArrayList<Entity>) engine.getEntities().clone();
		if(xtarget < 0) {
			xvel -= xacc;
			if (xvel < -maxxvel) {
				xvel = -maxxvel;
			}
		} else if (xtarget > 0){
			xvel += xacc;
			if (xvel > maxxvel) {
				xvel =  maxxvel;
			}
		} else {
			double sign1 = Math.signum(xvel);
			xvel -= xdec * Math.signum(xvel);
			if(sign1 != Math.signum(xvel)) {
				xvel = 0;
			}
		}
		if(xtarget < 0) {
			facing = DIRECTION.LEFT;
		} else if(xtarget > 0){
			facing = DIRECTION.RIGHT;
		}
		x += xvel;
		
		for(Entity ent : entities) {
			if(!(ent instanceof GroundEntity)) break;
			if(this.y+this.height > ent.y && this.y < ent.y+ent.height) {
				if( this.x+this.width > ent.x && this.x+this.width < ent.x+ent.width) {
					this.xvel = 0;
					this.x = ent.x-this.width;
				}
				if( this.x-0.1 > ent.x && this.x-0.1 < ent.x+ent.width) {
					this.xvel = 0;
					this.x = ent.x + ent.width;
				}
			}
		}
		
		boolean collided = false;
		
		y -= yvel;
		
		for(Entity ent : entities) {
			if(!(ent instanceof GroundEntity)) break;
			if(this.x+this.width > ent.x && this.x < ent.x+ent.width) {
				if( this.y+this.height > ent.y && this.y+this.height < ent.y+ent.height) {
					yvel = 0;
					y = ent.y-this.height;
					collided = true;
				}
				if( this.y-0.1 > ent.y && this.y-0.1 < ent.y+ent.height) {
					yvel = 0;
					y = ent.y + ent.height+0.1;
				}
			}

			
		}
		if(!collided) {
			yvel -= ydec;
			if(Math.abs(yvel) > maxyvel) {
				yvel = Math.signum(yvel) * maxyvel;
			}
		}
		
		if(jump) {
			if(collided) {
				yvel = jumpvel;
			}
			jump = false;
		}
		

	}
}
