package tappx_sdk_app.ads;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tappx_sample_app.R;
import com.tappx.sdk.android.TappxAdError;

import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxBanner;
import com.tappx.sdk.android.TappxBannerListener;

public class BannerAd {
    private Context context;
    private AdSubType adSubType;
    private ViewGroup bannerContainer;
    private TextView statusLog;
    private TappxBanner banner;

    public BannerAd(Context context, ViewGroup bannerContainer,TextView statusLog) {
        this.context = context;
        this.bannerContainer = bannerContainer;
        this.statusLog = statusLog;
    }
    public AdSubType getAdSubType() {
        return adSubType;
    }
    public void setAdSubType(AdSubType adSubType){
        this.adSubType = adSubType;
    }
    public void loadBanner() {
        banner = new TappxBanner(context, context.getString(R.string.tappx_key));
        switch (adSubType) {
            case phone_320X50:
                banner.setAdSize(TappxBanner.AdSize.BANNER_320x50);
                break;
            case tablet_728x90:
                banner.setAdSize(TappxBanner.AdSize.BANNER_728x90);
                break;
            case smart:
                banner.setAdSize(TappxBanner.AdSize.SMART_BANNER);
                break;
            default:
                banner.setAdSize(TappxBanner.AdSize.BANNER_320x50);
                break;
        }

        // Add Banner to container
        bannerContainer.addView(banner);

        // Set refresh configuration
        banner.setRefreshTimeSeconds(45);
        banner.setEnableAutoRefresh(false);

        // LoadAd
        banner.loadAd(new AdRequest()
                .useTestAds(context.getResources().getBoolean(R.bool.useTestAds))
                .setEndpoint(context.getString(R.string.endpoint))
        );

        // Add Listeners
        banner.setListener(new TappxBannerListener() {
            @Override
            public void onBannerLoaded(TappxBanner tappxBanner) {
                // Update the log when the banner is successfully loaded
                String message = "Banner Loaded Successfully!";
                updateLog(message);
            }

            @Override
            public void onBannerLoadFailed(TappxBanner tappxBanner, TappxAdError tappxAdError) {
                // Log the error when banner load fails
                String message = "Banner Load Failed";
                updateLog(message);
            }

            @Override
            public void onBannerClicked(TappxBanner tappxBanner) {
                // Log the click event
                String message = "Banner Clicked!";
                updateLog(message);
            }

            @Override
            public void onBannerExpanded(TappxBanner tappxBanner) {
                // Log the banner expansion
                String message = "Banner Expanded!";
                updateLog(message);
            }

            @Override
            public void onBannerCollapsed(TappxBanner tappxBanner) {
                // Log the banner collapse
                String message = "Banner Collapsed!";
                updateLog(message);
            }
        });
    }
        // Method to update the TextView inside the ScrollView with log messages
        private void updateLog (String message){
            // Find the TextView inside the ScrollView
            String currentLog = statusLog.getText().toString();
            statusLog.setText(message + "\n" + currentLog);
            Log.e("Tappx SDK",message);
        }


}