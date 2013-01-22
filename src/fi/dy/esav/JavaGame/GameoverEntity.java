package fi.dy.esav.JavaGame;


import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Canvas;
import android.view.KeyEvent;

import fi.dy.esav.GameEngineAndroid.Entity;
import fi.dy.esav.GameEngineAndroid.GameEngine;

public class GameoverEntity extends Entity {

	public GameoverEntity(GameEngine engine) {
		super(engine);
	}

	String maintext = "Your game is over...";
	String othertext = "Press ENTER to continue, or ESC to quit";

	@Override
	public void draw(Canvas c) {
		/*Font font = new Font("Arial", Font.ITALIC, 50);
		g.setFont(font);
		g.setColor(Color.RED);
		FontMetrics fm = g.getFontMetrics(font);
		g.drawString(maintext, engine.getStage().getWidth()
				/ 2 - fm.stringWidth(maintext) / 2, 200);
		
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		String time = format.format(new Date(JavaGame.getWorld().getScore().getTime()));
		
		String score = "Your score was: " + JavaGame.getWorld().getScore().getKills() + " in " + time; 
		Font font3 = new Font("Arial", Font.ITALIC, 35);
		g.setFont(font3);
		g.setColor(Color.RED);
		FontMetrics fm3 = g.getFontMetrics(font3);
		g.drawString(score, engine.getStage().getWidth()
				/ 2 - fm3.stringWidth(score) / 2, 300);
		
		Font font2 = new Font("Arial", Font.ITALIC, 35);
		g.setFont(font2);
		g.setColor(Color.RED);
		FontMetrics fm2 = g.getFontMetrics(font2);
		g.drawString(othertext, engine.getStage().getContentPane().getWidth()
				/ 2 - fm2.stringWidth(othertext) / 2, 400);*/ //TODO: Commented out
	}

	@Override
	public void act() {
		InputState input = ((Storage)engine.getCustomStorage()).inputstate;
		/*if(input.isKeyDown(KeyEvent.VK_ENTER)) {
			JavaGame.getWorld().reinitialize();
		} else if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			JavaGame.close();
		}*/ //TODO: Commented out
	}

}
