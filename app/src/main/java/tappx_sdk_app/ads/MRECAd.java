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

public class MRECAd {
    private Context context;
    private ViewGroup mrecContainer;
    private TextView statusLog;
    private TappxBanner mrec;

    public MRECAd(Context context, ViewGroup mrecContainer,TextView statusLog) {
        this.context = context;
        this.mrecContainer = mrecContainer;
        this.statusLog = statusLog;
    }

    public void loadMRECAd() {
        mrec = new TappxBanner(context, context.getString(R.string.tappx_key));
        mrec.setAdSize(TappxBanner.AdSize.BANNER_300x250);

        // Add Banner to container
        mrecContainer.addView(mrec);

        // Set refresh configuration
        mrec.setRefreshTimeSeconds(45);
        mrec.setEnableAutoRefresh(false);

        // LoadAd
        mrec.loadAd(new AdRequest()
                .useTestAds(context.getResources().getBoolean(R.bool.useTestAds))
                .setEndpoint(context.getString(R.string.endpoint))
        );

        // Add Listeners
        mrec.setListener(new TappxBannerListener() {
            @Override
            public void onBannerLoaded(TappxBanner tappxBanner) {
                // Update the log when the banner is successfully loaded
                String message = "MREC Loaded Successfully!";
                updateLog(message);
            }

            @Override
            public void onBannerLoadFailed(TappxBanner tappxBanner, TappxAdError tappxAdError) {
                // Log the error when banner load fails
                String message = "MREC Load Failed";
                updateLog(message);
            }

            @Override
            public void onBannerClicked(TappxBanner tappxBanner) {
                // Log the click event
                String message = "MREC Clicked!";
                updateLog(message);
            }

            @Override
            public void onBannerExpanded(TappxBanner tappxBanner) {
                // Log the banner expansion
                String message = "MREC Expanded!";
                updateLog(message);
            }

            @Override
            public void onBannerCollapsed(TappxBanner tappxBanner) {
                // Log the banner collapse
                String message = "MREC Collapsed!";
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