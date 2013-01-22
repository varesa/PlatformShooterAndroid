package fi.dy.esav.JavaGame;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.GameEngineAndroid.enums.ENTITY;

public class AiNode extends Entity {
	
	//Disabled default constructor
	private AiNode() { super(null); }

	public AiNode(GameEngine engine) {
		super(engine);
		init();
	}

	public AiNode(GameEngine engine, int x, int y) {
		super(engine);
		
		this.x = x;
		this.y = y;
		
		init();
	}
	
	private void init() {
		this.properties.add(ENTITY.NO_ACT);
		this.properties.add(ENTITY.NO_DRAW);
		
		width = 1;
		height = 1;
	}
	
	Paint paint = new Paint();
	
	@Override
	public void draw(Canvas c) {
		c.drawOval(new RectF((int)x-5, (int)y-5, 10, 10), paint);
	}
	
}
