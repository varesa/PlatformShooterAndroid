package fi.dy.esav.JavaGame;

import java.util.ArrayList;

import android.graphics.Point;

import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.GameEngineAndroid.Rectangle;
import fi.dy.esav.JavaGame.enums.AINODE;

public class World {
	
	private GameEngine engine;
	
	public PlayerEntity player;
	
	public Score score;

	int width, height;
	
	int storyHeight;
	int widthUnit;
	
	public World(GameEngine engine) {
		this.engine = engine;
	}

	public void initialize() {
		initGround();
		
		Point playerSpawn = new Point(20, 100);
		player = new PlayerEntity(engine);
		player.setPos(playerSpawn);
		
		engine.addEntity(player);
		
		/*ArrayList<Point> enemySpawns = new ArrayList<Point>();
		enemySpawns.add(new Point(500,50));
		enemySpawns.add(new Point(50,50));
		enemySpawns.add(new Point(100,500));
		enemySpawns.add(new Point(700,300));
		
		
		long initialDelay = 0;
		for(Point p : enemySpawns) {
			EntitySpawner e = new EntitySpawner(p, 20000, initialDelay, engine);
			initialDelay += 5000;
			e.setSpawnable(EnemyEntity.class.getCanonicalName());
			engine.addEntity(e);
		}
		
		Point[] jumpsUp = {new Point(85,  500),
				           new Point(400, 500),
						   new Point(715, 500),
						   
						   new Point(245, 380),
						   new Point(555, 380),
						   
						   new Point(85,  250),
				           new Point(400, 250),
						   new Point(715, 250),};
		for (Point point : jumpsUp) {
			engine.addEntity(new JumpAiNode(engine, point.x, point.y, AINODE.DIR_UP));
		}
		
		score = new Score();
		HudEntity hud = new HudEntity(engine);
		engine.addEntity(hud);*/
		
		engine.addEntity(new ButtonsOverlayEntity(engine));
		
	}
	
	private void initGround() {
		int stageWidth = ((Storage)engine.getCustomStorage()).display.getWidth(); //engine.getStage().getWidth();
		int stageHeight = ((Storage)engine.getCustomStorage()).display.getHeight(); //engine.getStage().getHeight();
		
		storyHeight = stageHeight/4;
		widthUnit = stageWidth/16;
		
		Rectangle[] ground = {  new Rectangle(0, 				0, 				10, 		stageHeight), 
								new Rectangle(0, 				0, 				stageWidth, 10),
								new Rectangle(0, 				stageHeight-10, stageWidth, 10),
								new Rectangle(stageWidth-10, 	0, 				10, 		stageHeight),
								
								new Rectangle(3*widthUnit,				storyHeight,	4*widthUnit,	10),
								new Rectangle(stageWidth-7*widthUnit,	storyHeight,	4*widthUnit,	10),
								
								new Rectangle(0, 						storyHeight*2, 4*widthUnit, 10),
								new Rectangle(6*widthUnit, 				storyHeight*2, 4*widthUnit, 10),
								new Rectangle(stageWidth-4*widthUnit, 	storyHeight*2, 4*widthUnit, 10),
								
								new Rectangle(3*widthUnit,				storyHeight*3,	4*widthUnit,	10),
								new Rectangle(stageWidth-7*widthUnit,	storyHeight*3,	4*widthUnit,	10),
								};
		
		for (Rectangle rect : ground) {
			engine.addEntity(new GroundEntity(engine, rect));
		}
	}
	
	public void clear() {
		for (Entity ent : engine.getEntities()) {
			if (ent instanceof EntitySpawner) {
				((EntitySpawner) ent).shutdown();
			}
			engine.removeEntity(ent);
		}
	}
	
	public void gameOver() {
		
		score.stopCounting();
		
		for (Entity ent : engine.getEntities()) {
			if (ent instanceof EntitySpawner) {
				((EntitySpawner) ent).shutdown();
			}
			engine.removeEntity(ent);
		}
		engine.addEntity(new GameoverEntity(engine));
		
		/*world.clear()
		world.initialize();*/
		}
	
	public void reinitialize() {		
		clear();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		initialize();
	}
	
	public int getStory(Entity ent) {
		int entCenter = (int) (ent.y + ent.height/2);
		return (int) (3 - Math.floor(entCenter/storyHeight));
	}
	
	public ArrayList<AiNode> getNodes() {
		ArrayList<AiNode> nodes = new ArrayList<AiNode>();
		for(Entity ent: engine.getEntities()) {
			if (ent instanceof AiNode) {
				nodes.add((AiNode) ent);
			}
		}
		return nodes;
	}
}
