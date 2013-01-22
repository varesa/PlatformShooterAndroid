package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;

/**
 * This is a tool for debugging / map creation
 */

public class MouseTrackerEntity extends Entity {

	public MouseTrackerEntity(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	int a = 0;
	
	@Override
	public void act() {
		if(a == 10) { 
			System.out.println("X: " + engine.getInputState().getMouseX() +
					           ", Y: " + engine.getInputState().getMouseY());
			a = 0;
		} else {
			a++;
		}
	}

}
