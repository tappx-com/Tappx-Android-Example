package tappx_sdk_app.views;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;

import com.example.tappx_sample_app.R;

import tappx_sdk_app.ads.AdSubType;
import tappx_sdk_app.ads.AdType;
import tappx_sdk_app.ads.BannerAd;
import tappx_sdk_app.ads.MRECAd;

public class BannerView extends Activity {

    private AdType adFormat;
    private ViewGroup adContainer;
    private TextView statusLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_view);

        // Get the ad container view
        adContainer = findViewById(R.id.adContainer);

        // Get the status log
        statusLog = findViewById(R.id.statusLog);

        // Retrieve the ad format (banner or MREC) from the Intent
        adFormat = AdType.valueOf(getButtonClicked());

        // Set the text based on the ad type
        TextView adFormatText = findViewById(R.id.adFormatText);
        if (adFormat == AdType.Banner) {
            adFormatText.setText(AdType.Banner.name());
            loadBannerAd();
        } else {
            adFormatText.setText(AdType.MREC.name());
            loadMRECAd();
        }
    }

    // Method to get the clicked button's value from the Intent
    private String getButtonClicked() {
        Intent intent = getIntent();
        return intent.getStringExtra("buttonClicked");
    }

    // Method to load the Banner ad
    private void loadBannerAd() {
        // Create an instance of BannerAd and load the banner ad
        BannerAd bannerAd = new BannerAd(this, adContainer, statusLog);
        bannerAd.setAdSubType(AdSubType.phone_320X50);// "phone_320X50" is an example of AdSubType you can add "tablet_728x90" or "smart" too
        bannerAd.loadBanner();
    }

    // Method to load the MREC ad
    private void loadMRECAd() {
        // Create an instance of MRECAd and load the MREC ad
        MRECAd mrecAd = new MRECAd(this, adContainer,statusLog);
        mrecChangeDesign();
        mrecAd.loadMRECAd();
    }

    // Method to handle the back button click and navigate to MainView
    public void onClickGoMainView(android.view.View view) {
        // Navigate back to the main view
        Intent intent = new Intent(BannerView.this, MainView.class);
        startActivity(intent);
    }

    private void mrecChangeDesign(){

        //Apply new margins
        int marginTopInDp = 10;
        int marginTopInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                marginTopInDp,
                getResources().getDisplayMetrics()
        );

        //Top Margin
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) adContainer.getLayoutParams();
        params.topMargin = marginTopInPx;
        adContainer.setLayoutParams(params);

        //Bottom Linear layout
        LinearLayout statusContainer = findViewById(R.id.linearLayouBottom);
        ViewGroup.MarginLayoutParams statusParams = (ViewGroup.MarginLayoutParams) statusContainer.getLayoutParams();
        statusParams.topMargin = marginTopInPx;
        statusContainer.setLayoutParams(statusParams);
    }
}
