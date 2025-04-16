package tappx_sdk_app.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tappx_sample_app.R;
import com.tappx.sdk.android.Tappx;

import tappx_sdk_app.ads.AdType;

public class MainView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        // SDK Version
        TextView sdkVersionText = findViewById(R.id.sdk_version);
        sdkVersionText.setText(this.getString(R.string.tappx_sdk_version) + Tappx.getVersion());
    }



    // Banner onClick
    public void onBannerButtonClick(android.view.View view) {
        Intent intent = new Intent(MainView.this, BannerView.class);
        intent.putExtra("buttonClicked", AdType.Banner.name());
        startActivity(intent);
    }
    // MREC onClick
    public void onMrecButtonClick(android.view.View view) {
        Intent intent = new Intent(MainView.this, BannerView.class);
        intent.putExtra("buttonClicked", AdType.MREC.name());
        startActivity(intent);
    }

    // Interstitial onClick
    public void onInterstitialButtonClick(android.view.View view) {
        Intent intent = new Intent(MainView.this, FullScreenView.class);
        intent.putExtra("buttonClicked", AdType.Interstitial.name());
        startActivity(intent);
    }

    // Rewarded onClick
    public void onRewardedButtonClick(android.view.View view) {
        Intent intent = new Intent(MainView.this, FullScreenView.class);
        intent.putExtra("buttonClicked", AdType.Rewarded.name());
        startActivity(intent);
    }
}
