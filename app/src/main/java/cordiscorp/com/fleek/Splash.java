package cordiscorp.com.fleek;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cordiscorp.com.fleek.activity.HomeActivity;
import cordiscorp.com.fleek.pandora.NavigationManager;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;
    private NavigationManager navigationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        navigationManager = new NavigationManager();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigationManager.loadActivity(Splash.this, HomeActivity.class);
            }
        }, SPLASH_TIME_OUT);
    }
}
