package fi.dy.esav.PlatformShooter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PlatformShooterMainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PlatformShooterGameView(getApplicationContext()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
