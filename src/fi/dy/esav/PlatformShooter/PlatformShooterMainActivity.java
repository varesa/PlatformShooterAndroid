package fi.dy.esav.PlatformShooter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PlatformShooterMainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.asd, menu);
        return true;
    }
}
