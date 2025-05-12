package com.dsi.facebook_audience_network;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedInterstitialAd;
import com.facebook.ads.RewardedInterstitialAdListener;

import java.util.HashMap;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

class FacebookRewardedInterstitialAdPlugin implements MethodChannel.MethodCallHandler,
        RewardedInterstitialAdListener {

    private RewardedInterstitialAd rewardedInterstitialAd = null;

    private Context context;
    private MethodChannel channel;

    private Handler _delayHandler;

    FacebookRewardedInterstitialAdPlugin(Context context, MethodChannel channel) {
        this.context = context;
        this.channel = channel;

        _delayHandler = new Handler();
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case FacebookConstants.SHOW_REWARDED_INTERSTITIAL_METHOD:
                result.success(showAd((HashMap) methodCall.arguments));
                break;
            case FacebookConstants.LOAD_REWARDED_INTERSTITIAL_METHOD:
                result.success(loadAd((HashMap) methodCall.arguments));
                break;
            case FacebookConstants.DESTROY_REWARDED_INTERSTITIAL_METHOD:
                result.success(destroyAd());
                break;
            default:
                result.notImplemented();
        }
    }

    private boolean loadAd(HashMap args) {
        final String placementId = (String) args.get("id");

        if (rewardedInterstitialAd == null) {
            rewardedInterstitialAd = new RewardedInterstitialAd(context, placementId);
        }

        try {
            if (!rewardedInterstitialAd.isAdLoaded()) {
                RewardedInterstitialAd.RewardedInterstitialLoadAdConfig loadAdConfig =
                        rewardedInterstitialAd.buildLoadAdConfig().withAdListener(this).build();

                rewardedInterstitialAd.loadAd(loadAdConfig);
            }
        } catch (Exception e) {
            Log.e("RewardedInterstError", e.getMessage());
            return false;
        }

        return true;
    }

    private boolean showAd(HashMap args) {
        final int delay = (int) args.get("delay");

        if (rewardedInterstitialAd == null || !rewardedInterstitialAd.isAdLoaded())
            return false;

        if (rewardedInterstitialAd.isAdInvalidated())
            return false;

        if (delay <= 0) {
            RewardedInterstitialAd.RewardedInterstitialShowAdConfig showAdConfig =
                    rewardedInterstitialAd.buildShowAdConfig().build();

            rewardedInterstitialAd.show(showAdConfig);
        } else {
            _delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (rewardedInterstitialAd == null || !rewardedInterstitialAd.isAdLoaded())
                        return;

                    if (rewardedInterstitialAd.isAdInvalidated())
                        return;

                    RewardedInterstitialAd.RewardedInterstitialShowAdConfig showAdConfig =
                            rewardedInterstitialAd.buildShowAdConfig().build();

                    rewardedInterstitialAd.show(showAdConfig);
                }
            }, delay);
        }
        return true;
    }

    private boolean destroyAd() {
        if (rewardedInterstitialAd == null)
            return false;
        else {
            rewardedInterstitialAd.destroy();
            rewardedInterstitialAd = null;
        }
        return true;
    }

    // --- RewardedInterstitialAdListener methods ---

    @Override
    public void onError(Ad ad, AdError adError) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("placement_id", ad.getPlacementId());
        args.put("invalidated", ad.isAdInvalidated());
        args.put("error_code", adError.getErrorCode());
        args.put("error_message", adError.getErrorMessage());

        channel.invokeMethod(FacebookConstants.ERROR_METHOD, args);
    }

    @Override
    public void onAdLoaded(Ad ad) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("placement_id", ad.getPlacementId());
        args.put("invalidated", ad.isAdInvalidated());

        channel.invokeMethod(FacebookConstants.LOADED_METHOD, args);
    }

    @Override
    public void onAdClicked(Ad ad) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("placement_id", ad.getPlacementId());
        args.put("invalidated", ad.isAdInvalidated());

        channel.invokeMethod(FacebookConstants.CLICKED_METHOD, args);
    }

    @Override
    public void onLoggingImpression(Ad ad) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("placement_id", ad.getPlacementId());
        args.put("invalidated", ad.isAdInvalidated());

        channel.invokeMethod(FacebookConstants.LOGGING_IMPRESSION_METHOD, args);
    }

    @Override
    public void onRewardedInterstitialClosed() {
        channel.invokeMethod(FacebookConstants.REWARDED_INTERSTITIAL_CLOSED_METHOD, true);
    }

    @Override
    public void onRewardedInterstitialCompleted() {
        channel.invokeMethod(FacebookConstants.REWARDED_INTERSTITIAL_COMPLETE_METHOD, true);
    }
}
