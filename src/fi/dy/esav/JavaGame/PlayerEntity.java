package fi.dy.esav.JavaGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.KeyEvent;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.JavaGame.enums.DIRECTION;

public class PlayerEntity extends LivingEntity {

	private int color = Color.BLUE;

	private long canShoot;
	private long shootDelay = 500;

	public PlayerEntity(GameEngine engine) {
		super(engine);

		height = 48;
		width = 48;

		canShoot = System.currentTimeMillis();
	}

	@Override
	public void draw(Canvas c) {
		Paint paint = new Paint();
		paint.setColor(color);
		c.drawRect(new RectF((int) x, (int) y, (int) x+width, (int) y+height), paint);
	}

	@Override
	public void act() {
		InputState input = ((Storage)engine.getCustomStorage()).inputstate;
		
		if (input.left) {
			this.xtarget = -1;
		} else if (input.right) {
			this.xtarget = +1;
		} else {
			this.xtarget = 0;
		}

		if (input.jump) {
			this.jump = true;
		} else {
			this.jump = false;
		}

		if (input.shoot) {
			if (canShoot <= System.currentTimeMillis()) {

				Point bulletPos = this.getPos();
				bulletPos.offset(0, 20);

				if (facing == DIRECTION.LEFT) {
					bulletPos.offset(-20, 0);
					this.engine.addEntity(new Bullet(bulletPos, DIRECTION.LEFT,
							this.engine));
				} else if (facing == DIRECTION.RIGHT) {
					bulletPos.offset(getWidth()+20, 0);
					this.engine.addEntity(new Bullet(bulletPos,
							DIRECTION.RIGHT, this.engine));
				}
				canShoot = System.currentTimeMillis() + shootDelay;
			}
		}

		super.act();

	}
}
