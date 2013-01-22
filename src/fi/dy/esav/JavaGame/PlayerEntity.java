package fi.dy.esav.JavaGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.KeyEvent;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.GameEngineAndroid.InputState;
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
		c.drawRect(new RectF((int) x, (int) y, width, height), paint);
	}

	@Override
	public void act() {
		InputState input = engine.getInputState();
		
		/*if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			this.xtarget = -1;
		} else if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.xtarget = +1;
		} else {
			this.xtarget = 0;
		}

		if (input.isKeyDown(KeyEvent.VK_UP)) {
			this.jump = true;
		} else {
			this.jump = false;
		}

		if (input.isKeyDown(KeyEvent.VK_SPACE)) {
			if (canShoot <= System.currentTimeMillis()) {

				Point bulletPos = this.getPos();
				bulletPos.translate(0, 20);

				if (facing == DIRECTION.LEFT) {
					bulletPos.translate(-20, 0);
					this.engine.addEntity(new Bullet(bulletPos, DIRECTION.LEFT,
							this.engine));
				} else if (facing == DIRECTION.RIGHT) {
					bulletPos.translate(getWidth()+20, 0);
					this.engine.addEntity(new Bullet(bulletPos,
							DIRECTION.RIGHT, this.engine));
				}
				canShoot = System.currentTimeMillis() + shootDelay;
			}
		} */ //TODO: Commented out

		super.act();

	}
}
