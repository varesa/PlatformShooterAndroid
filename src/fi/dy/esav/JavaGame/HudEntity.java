package fi.dy.esav.JavaGame;

import android.graphics.Canvas;
import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.GameEngineAndroid.enums.ENTITY;

public class HudEntity extends Entity {

	public HudEntity(GameEngine engine) {
		super(engine);
		this.setProperty(ENTITY.NO_ACT);
	}
	
	@Override
	public void draw(Canvas c) {
		//String score = String.valueOf(JavaGame.getWorld().getScore().getKills());
		/*Font font = new Font("Arial", Font.PLAIN, 20);
		g.setFont(font);
		g.drawString(score, 20, engine.getStage().getContentPane().getHeight()-20);*/ //TODO: commented out
	}
	
	

}
