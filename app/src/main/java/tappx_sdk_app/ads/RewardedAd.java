package tappx_sdk_app.ads;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.tappx_sample_app.R;
import com.tappx.sdk.android.TappxAdError;

import com.tappx.sdk.android.AdRequest;

import com.tappx.sdk.android.TappxRewardedVideo;
import com.tappx.sdk.android.TappxRewardedVideoListener;

public class RewardedAd {
    private Context context;
    private TextView statusLog;
    private TappxRewardedVideo rewardedVideo;

    public RewardedAd(Context context,TextView statusLog) {
        this.context = context;
        this.statusLog = statusLog;
    }

    /*
    * REWARDED AD
    */
    public void loadRewardedAd() {
        rewardedVideo = new TappxRewardedVideo(context, context.getString(R.string.tappx_key));
        rewardedVideo.loadAd(new AdRequest()
                .useTestAds(context.getResources().getBoolean(R.bool.useTestAds))
                .setEndpoint(context.getString(R.string.endpoint))
        );

        // Add Listeners
        rewardedVideo.setListener(new TappxRewardedVideoListener() {
            @Override
            public void onRewardedVideoLoaded(TappxRewardedVideo tappxRewardedVideo) {
                // Log when the rewarded video is successfully loaded
                String message = "Rewarded Video Loaded Successfully!";
                updateLog(message);
            }

            @Override
            public void onRewardedVideoLoadFailed(TappxRewardedVideo tappxRewardedVideo, TappxAdError tappxAdError) {
                // Log when the rewarded video fails to load
                String message = "Rewarded Video Load Failed!";
                updateLog(message);
            }

            @Override
            public void onRewardedVideoStart(TappxRewardedVideo tappxRewardedVideo) {
                // Log when the rewarded video starts playing
                String message = "Rewarded Video Started!";
                updateLog(message);
            }

            @Override
            public void onRewardedVideoPlaybackFailed(TappxRewardedVideo tappxRewardedVideo) {
                // Log when the rewarded video playback fails
                String message = "Rewarded Video Playback Failed!";
                updateLog(message);
            }

            @Override
            public void onRewardedVideoClicked(TappxRewardedVideo tappxRewardedVideo) {
                // Log when the rewarded video is clicked
                String message = "Rewarded Video Clicked!";
                updateLog(message);
            }

            @Override
            public void onRewardedVideoClosed(TappxRewardedVideo tappxRewardedVideo) {
                // Log when the rewarded video is closed
                String message = "Rewarded Video Closed!";
                updateLog(message);
            }

            @Override
            public void onRewardedVideoCompleted(TappxRewardedVideo tappxRewardedVideo) {
                // Log when the rewarded video is completed
                String message = "Rewarded Video Completed!";
                updateLog(message);
            }
        });

    }
    public void showRewardedAd(){
        if (rewardedVideo != null && rewardedVideo.isReady()) {
            rewardedVideo.show();
        }else{
            updateLog("Rewarded Ad is not loaded");
        }
    }
    public void showRewardedStatus(){
        if (rewardedVideo == null) {
            updateLog("Rewarded Ad is not loaded");
            return;
        }
        if(rewardedVideo.isReady()){
            updateLog("Rewarded Ad is ready to show!");
        }else {
            updateLog("Rewarded Ad is not ready yet");
        }
    }

    private void updateLog (String message){
        // Find the TextView inside the ScrollView
        String currentLog = statusLog.getText().toString();
        statusLog.setText(message + "\n" + currentLog);
        Log.e("Tappx SDK",message);
    }
}