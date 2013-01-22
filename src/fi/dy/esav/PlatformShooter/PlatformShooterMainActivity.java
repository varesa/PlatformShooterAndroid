package fi.dy.esav.PlatformShooter;

import fi.dy.esav.GameEngineAndroid.GameEngine;
import fi.dy.esav.JavaGame.InputHandler;
import fi.dy.esav.JavaGame.Storage;
import fi.dy.esav.JavaGame.World;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class PlatformShooterMainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        GameEngine engine = new GameEngine(getApplicationContext());
        setContentView(engine.stage);
        
        engine.setCustomStorage(new Storage(engine));
        
        ((Storage)engine.getCustomStorage()).display = getWindowManager().getDefaultDisplay();
        
        World world = new World(engine);
        ((Storage)engine.getCustomStorage()).world = world;
        
        engine.stage.setOnTouchListener(new InputHandler(engine));
        world.initialize();

        engine.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
