package tappx_sdk_app.ads;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.tappx_sample_app.R;
import com.tappx.sdk.android.TappxAdError;

import com.tappx.sdk.android.AdRequest;

import com.tappx.sdk.android.TappxInterstitial;
import com.tappx.sdk.android.TappxInterstitialListener;


public class InterstitialAd {
    private Context context;
    private TextView statusLog;
    private Boolean autoShow;
    private TappxInterstitial interstitialAd;


    public InterstitialAd(Context context,TextView statusLog, Boolean autoShow) {
        this.context = context;
        this.statusLog = statusLog;
        this.autoShow = autoShow;
    }

    public void loadInterstitialAd() {
        interstitialAd = new TappxInterstitial(context, context.getString(R.string.tappx_key));
        interstitialAd.setAutoShowWhenReady(autoShow);
        // Add Listeners
        interstitialAd.setListener(new TappxInterstitialListener() {
            @Override
            public void onInterstitialLoaded(TappxInterstitial tappxInterstitial) {
                String message = "Interstitial Ad Loaded Successfully!";
                updateLog(message);
            }

            @Override
            public void onInterstitialLoadFailed(TappxInterstitial tappxInterstitial, TappxAdError tappxAdError) {
                String message = "Interstitial Ad Load Failed!";
                updateLog(message);
            }

            @Override
            public void onInterstitialShown(TappxInterstitial tappxInterstitial) {
                String message = "Interstitial Ad Shown!";
                updateLog(message);
            }

            @Override
            public void onInterstitialClicked(TappxInterstitial tappxInterstitial) {
                String message = "Interstitial Ad Clicked!";
                updateLog(message);
            }

            @Override
            public void onInterstitialDismissed(TappxInterstitial tappxInterstitial) {
                String message = "Interstitial Ad Dismissed!";
                updateLog(message);
            }
        });
        interstitialAd.loadAd(new AdRequest()
                .useTestAds(context.getResources().getBoolean(R.bool.useTestAds))
                .setEndpoint(context.getString(R.string.endpoint))
        );

    }
    public void showInterstitialAd(){
        if (interstitialAd != null && interstitialAd.isReady()) {
            interstitialAd.show();
        }else{
            updateLog("Interstitial Ad is not loaded");
        }
    }
    public void showInterstitialStatus(){
        if (interstitialAd == null) {
            updateLog("Interstitial Ad is not loaded");
            return;
        }
        if (interstitialAd.isReady()) {
            updateLog("Interstitial Ad is ready to show");

        } else {
            updateLog("Interstitial Ad is not ready yet");
        }
    }


    private void updateLog (String message){
        // Find the TextView inside the ScrollView
        String currentLog = statusLog.getText().toString();
        statusLog.setText(message + "\n" + currentLog);
        Log.e("Tappx SDK",message);
    }
}