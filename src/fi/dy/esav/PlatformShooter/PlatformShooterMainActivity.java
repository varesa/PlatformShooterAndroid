package fi.dy.esav.PlatformShooter;

import fi.dy.esav.GameEngineAndroid.GameEngine;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PlatformShooterMainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GameEngine gameEngine = new GameEngine(getApplicationContext());
        setContentView(gameEngine.getStage());
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
