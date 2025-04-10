package tappx_sdk_app.views;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.tappx_sample_app.R;

import tappx_sdk_app.ads.AdType;
import tappx_sdk_app.ads.InterstitialAd;
import tappx_sdk_app.ads.RewardedAd;

public class FullScreenView extends Activity {

    private AdType adFormat;
    private TextView statusLog;
    private RewardedAd rewardedAd;
    private InterstitialAd interstitialAd;
    private SwitchCompat interstitialSwitch;
    private TextView autoShowText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_view);

        // Get the status log
        statusLog = findViewById(R.id.statusLog);

        // Get the switch Button & AutoShow text
        interstitialSwitch = findViewById(R.id.interstitialSwitch);
        autoShowText = findViewById(R.id.autoShowText);

        // Retrieve the ad format (Rewarded or Interstitial) from the Intent
        adFormat = AdType.valueOf(getAdFormatFromIntent());
        // Set up the UI based on the ad format
        setupAdUI();

        if (adFormat == AdType.Interstitial) {
            interstitialAd = new InterstitialAd(this, statusLog, interstitialSwitch.isChecked());
        }
    }

    private String getAdFormatFromIntent() {
        Intent intent = getIntent();
        return intent.getStringExtra("buttonClicked");
    }

    private void setupAdUI() {
        TextView adFormatText = findViewById(R.id.adFormatText);
        if (adFormat == AdType.Rewarded) {
            interstitialSwitch.setVisibility(View.GONE);
            autoShowText.setVisibility(View.GONE);
            adFormatText.setText(AdType.Rewarded.name());
            rewardedAd = new RewardedAd(this, statusLog);
        } else {
            adFormatText.setText(AdType.Interstitial.name());
            configureAutoShowButton();
        }
    }

    public void onLoadFullScreenAdClick(View view) {
        if (adFormat == AdType.Rewarded) {
            rewardedAd.loadRewardedAd();
        } else {
            //✅ Check if interstitialAd is null before calling it
            if (interstitialAd == null) {
                interstitialAd = new InterstitialAd(this, statusLog, interstitialSwitch.isChecked());
            }
            interstitialAd.loadInterstitialAd();
        }
    }

    public void onCheckStatusFullScreeAdClick(View view) {
        if (adFormat == AdType.Rewarded & rewardedAd!=null) {
            rewardedAd.showRewardedStatus();
        } else {
            if (interstitialAd != null) {
                interstitialAd.showInterstitialStatus();
            }
        }
    }

    public void onShowFullScreenAdClick(View view) {
        if (adFormat == AdType.Rewarded) {
            rewardedAd.showRewardedAd();
        } else {
            if (interstitialAd != null) {
                interstitialAd.showInterstitialAd();
            }
        }
    }

    private void configureAutoShowButton() {
        interstitialSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            interstitialSwitch.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)));
            int color = isChecked ? ContextCompat.getColor(this, R.color.green) : ContextCompat.getColor(this, R.color.gray);
            interstitialSwitch.setTrackTintList(ColorStateList.valueOf(color));

            LinearLayout checkStatusButton = findViewById(R.id.checkStatusButton);
            LinearLayout showAdButton = findViewById(R.id.showAdButton);
            LinearLayout linearLayoutBottom = findViewById(R.id.linearLayouBottom);
            ConstraintLayout.LayoutParams constraintParams = (ConstraintLayout.LayoutParams) linearLayoutBottom.getLayoutParams();

            if (isChecked) {
                checkStatusButton.setVisibility(View.GONE);
                showAdButton.setVisibility(View.GONE);
                constraintParams.topMargin = convertDpToPx(150);
            } else {
                checkStatusButton.setVisibility(View.VISIBLE);
                showAdButton.setVisibility(View.VISIBLE);
                constraintParams.topMargin = convertDpToPx(280);
            }
            linearLayoutBottom.setLayoutParams(constraintParams);

            interstitialAd = new InterstitialAd(this, statusLog, isChecked);
        });
    }

    private int convertDpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }
    public void onClickGoMainView(View view) {
        Intent intent = new Intent(FullScreenView.this, MainView.class);
        startActivity(intent);
    }
}