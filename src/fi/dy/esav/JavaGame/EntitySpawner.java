package fi.dy.esav.JavaGame;

import java.lang.reflect.InvocationTargetException;

import android.graphics.Point;
import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;

public class EntitySpawner extends Entity {

	public long interval = 9000;
	public long intervalDelta = 50;
	public long initialDelay = 500;

	public String spawnable;

	public EntitySpawner(GameEngine engine) {
		super(engine);
		init();
	}

	public EntitySpawner(Point position, GameEngine engine) {
		super(position, engine);
		init();
	}

	public EntitySpawner(double x, double y, GameEngine engine) {
		super(x, y, engine);
		init();
	}

	public EntitySpawner(Point position, long interval, GameEngine engine) {
		super(position, engine);
		this.interval = interval;
		init();
	}

	public EntitySpawner(double x, double y, long interval, GameEngine engine) {
		super(x, y, engine);
		this.interval = interval;
		init();
	}

	public EntitySpawner(Point position, long interval, long initialDelay,
			GameEngine engine) {
		super(position, engine);
		this.interval = interval;
		this.initialDelay = initialDelay;
		init();
	}

	public EntitySpawner(double x, double y, long interval, long initialDelay,
			GameEngine engine) {
		super(x, y, engine);
		this.interval = interval;
		this.initialDelay = initialDelay;
		init();
	}

	private SpawnerThread spawner = new SpawnerThread(this, engine);
	private Thread spawnerThread = new Thread(spawner);

	private void init() {
		spawnerThread.start();
	}

	public void shutdown() {
		spawner.shutdown();
		spawnerThread.interrupt();
	}

}

class SpawnerThread implements Runnable {

	private boolean run = true;

	private EntitySpawner main = null;
	private GameEngine engine = null;

	public SpawnerThread(EntitySpawner main, GameEngine game) {
		this.main = main;
		this.engine = game;
	}

	
	public void run() {
		try {
			Thread.sleep(main.initialDelay);
		} catch (InterruptedException e1) {
			// Interrupted the thread, allow it to end
		}

		while (run) {
			spawn();
			try {
				Thread.sleep(main.interval);
			} catch (InterruptedException e) {
				// Interrupted the thread, allow it to end
			}
			main.interval = main.interval - main.intervalDelta;

		}

	}

	private void spawn() {
		try {
			Entity ent = (Entity) Class.forName(main.spawnable)
					.getConstructor(GameEngine.class)
					.newInstance(new Object[] { engine });
			ent.setPos(main.getPos());
			engine.addEntity(ent);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		run = false;
	}
}
