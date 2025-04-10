package tappx_sdk_app.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.example.tappx_sample_app.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * SplashView is an activity that displays a splash screen for a few seconds before launching MainView.
 */
public class SplashView extends Activity {

    // Duration for the splash screen (in milliseconds)
    private static final long SPLASH_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);

        // Design part
        TextView sloganText = findViewById(R.id.sloganText);
        SpannableString spannableString = new SpannableString(this.getString(R.string.slogan));
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, 11, 0);
        sloganText.setText(spannableString);

        startMainActivityWithDelay();
    }

    /**
     * Starts MainView after a specified delay using a TimerTask.
     */
    private void startMainActivityWithDelay() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // Create an intent to transition from SplashView to MainView
                Intent intent = new Intent(getApplicationContext(), MainView.class);
                startActivity(intent);
                finish();
            }
        };

        // Schedule the task to run after SPLASH_TIME milliseconds
        new Timer().schedule(timerTask, SPLASH_TIME);
    }
}
