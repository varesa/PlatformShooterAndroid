package fi.dy.esav.JavaGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.GameEngineAndroid.Utils;
import fi.dy.esav.JavaGame.enums.AINODE;

public class EnemyEntity extends LivingEntity {

	private int color = Color.RED;

	protected final double xacc = 0.5;
	protected final double xdec = 0.5;
	protected final double maxxvel = 6;

	private int hitpoints = 30;
	private long fullOpacity = -1;

	private long startTime = -1;
	private long spawnTime = 1000;

	private int fadetime = 400;

	private boolean dead = false;

	private boolean ai = true;
	
	private World world;

	public EnemyEntity(GameEngine engine) {
		super(engine);

		height = 48;
		width = 48;

		super.xacc = this.xacc;
		super.xdec = this.xdec;
		super.maxxvel = this.maxxvel;

		startTime = System.currentTimeMillis() + spawnTime;
		
		world = ((Storage) engine.getCustomStorage()).world;
	}

	@Override
	public void act() {
		if (System.currentTimeMillis() < startTime) {
			// Do nothing
		} else {
			if (ai) {
				PlayerEntity player = world.player;

				int myStory = world.getStory(this);
				int playerStory = world.getStory(player);
				int target = playerStory - myStory;

				JumpAiNode node = null;
				if (target > 0) {
					node = findNode(AINODE.DIR_UP);
					if (node == null) {
						xtarget = player.x - this.x;
					} else {
						xtarget = node.x - this.x;
					}
				} else if (target < 0) {
					node = findNode(AINODE.DIR_DOWN);
					if (node == null) {
						xtarget = player.x - this.x;
					} else {
						xtarget = node.x - this.x;
					}

				} else {
					xtarget = player.x - this.x;
				}

				if (node != null) {
					if (Utils.getXDistanceCenters(this, node) < 10) {
						jump = true;
					}
				}

				if (Utils.simpleHitTest(this, player)) {

					world.gameOver();
				}
			}

			if (dead && System.currentTimeMillis() > fullOpacity) {
				engine.removeEntity(this);
			}

			super.act();

		}

	}

	private JumpAiNode findNode(AINODE direction) {

		int dist = -1;
		JumpAiNode closestNode = null;
		World world = ((Storage) engine.getCustomStorage()).world;
		for (AiNode node : world.getNodes()) {
			if (node instanceof JumpAiNode) {
				if ((dist == -1 || Utils.getDistanceCenters(node, this) < dist)
						&& (world.getStory(node) == world.getStory(this))
						&& ((JumpAiNode) node).direction == direction) {
					closestNode = (JumpAiNode) node;
					dist = (int) Utils.getDistanceCenters(node, this);
				}

			}
		}

		return closestNode;
	}

	public void damage(double damage) {
		if (System.currentTimeMillis() > startTime) {
			if (damage > hitpoints) {
				dead = true;
				ai = false;
				world.score.kills++;
			} else {
				hitpoints -= damage;
			}
			fullOpacity = System.currentTimeMillis() + fadetime;
		}
	}
	
	@Override
	public void draw(Canvas c) {
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setStyle(Paint.Style.STROKE);

		if (System.currentTimeMillis() < startTime) {
			double time = (double) (startTime - System.currentTimeMillis())
					/ (double) spawnTime;

			double rotation = time * 2 * Math.PI;

			double originx = x + width / 2;
			double originy = y + height / 2;

			double sizex = width + 2 * time * width;
			double sizey = height + 2 * time * height;

			Paint newpaint = new Paint();
			newpaint.setARGB((int) (255 - 255 * time), Color.red(color),
					Color.green(color), Color.blue(color));

			c.rotate((float) rotation, (float) originx, (float) originy);
			c.drawRect(new RectF((int) (originx - sizex / 2),
					(int) (originy - sizey / 2),
					(int) ((originx - sizex / 2) + sizex),
					(int) ((originy - sizey / 2) + sizey)), newpaint);
			c.rotate((float) -rotation, (float) originx, (float) originy);

		} else {
			if (System.currentTimeMillis() < fullOpacity) {
				int opacity = (int) ((double) (fullOpacity - System
						.currentTimeMillis()) / (double) fadetime * (double) 255);
				Paint opaque = new Paint();
				opaque.setARGB(opacity, 255, 0, 0);
				opaque.setStyle(Paint.Style.FILL);
				c.drawRect(new RectF((int) x, (int) y, (int) x + width, (int) y
						+ height), opaque);
			}

			c.drawRect(new RectF((int) x, (int) y, (int) x + width, (int) y
					+ height), paint);
		}
	}
}
