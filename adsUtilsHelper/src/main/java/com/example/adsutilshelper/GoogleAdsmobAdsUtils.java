package com.example.adsutilshelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class GoogleAdsmobAdsUtils {
    Context context;
    InterstitialAd mInterstitialAd;

    public GoogleAdsmobAdsUtils(Context context) {
        this.context = context;
    }

    public InterstitialAd loadInterstitialAd(String adID){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, adID, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.e("Ads", "onAdLoaded" );
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        Log.e("Ads", "onAdFailedToLoad: "+loadAdError.getMessage() );
                    }
                });

        return mInterstitialAd;

    }

    public void showInterstitialAd(Activity activity, Intent intent, boolean needFinish){
        //if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when fullscreen content is dismissed.
                    if (intent != null){
                        activity.startActivity(intent);
                        if (needFinish){ activity.finish(); }
                    }

                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when fullscreen content failed to show.
                    Log.e("TAG", "onAdFailedToShowFullScreenContent: "+adError.getMessage() );
                    Log.e("TAG", "onAdFailedToShowFullScreenContent: "+adError.getCode() );
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    mInterstitialAd = null;
                }
            });
        /*} else {
            if (intent != null){
                activity.startActivity(intent);
                if (needFinish){ activity.finish(); }
            }
        }*/
    }

}
