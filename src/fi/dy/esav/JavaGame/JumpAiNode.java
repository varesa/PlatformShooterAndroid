package fi.dy.esav.JavaGame;

import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.JavaGame.enums.AINODE;

public class JumpAiNode extends AiNode {

	public AINODE direction;
	
	//Removed default constructor
	private JumpAiNode() { super(null);	}
	
	public JumpAiNode(GameEngine engine) {
		super(engine);
	}

	public JumpAiNode(GameEngine engine, int x, int y, AINODE direction) {
		super(engine, x, y);
		
		if(!(direction == AINODE.DIR_DOWN || direction == AINODE.DIR_UP)) {
			throw new IllegalArgumentException("Direction parameter is invalid");
		}
		this.direction = direction;
	}
}
