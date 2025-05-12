package com.dsi.facebook_audience_network;

final class FacebookConstants {
    static final String MAIN_CHANNEL = "fb.audience.network.io";
    static final String BANNER_AD_CHANNEL = MAIN_CHANNEL + "/bannerAd";
    static final String INTERSTITIAL_AD_CHANNEL = MAIN_CHANNEL + "/interstitialAd";
    static final String NATIVE_AD_CHANNEL = MAIN_CHANNEL + "/nativeAd";
    static final String REWARDED_VIDEO_CHANNEL = MAIN_CHANNEL + "/rewardedAd";
    static final String REWARDED_INTERSTITIAL_CHANNEL = MAIN_CHANNEL + "/rewardedInterstitialAd";

    static final String INIT_METHOD = "init";
    static final String SHOW_INTERSTITIAL_METHOD = "showInterstitialAd";
    static final String LOAD_INTERSTITIAL_METHOD = "loadInterstitialAd";
    static final String DESTROY_INTERSTITIAL_METHOD = "destroyInterstitialAd";

    static final String SHOW_REWARDED_VIDEO_METHOD = "showRewardedAd";
    static final String LOAD_REWARDED_VIDEO_METHOD = "loadRewardedAd";
    static final String DESTROY_REWARDED_VIDEO_METHOD = "destroyRewardedAd";

    // Rewarded Interstitial Methods
    static final String LOAD_REWARDED_INTERSTITIAL_METHOD = "loadRewardedInterstitialAd";
    static final String SHOW_REWARDED_INTERSTITIAL_METHOD = "showRewardedInterstitialAd";
    static final String DESTROY_REWARDED_INTERSTITIAL_METHOD = "destroyRewardedInterstitialAd";


    static final String DISPLAYED_METHOD = "displayed";
    static final String DISMISSED_METHOD = "dismissed";
    static final String ERROR_METHOD = "error";
    static final String LOADED_METHOD = "loaded";
    static final String CLICKED_METHOD = "clicked";
    static final String LOGGING_IMPRESSION_METHOD = "logging_impression";
    static final String REWARDED_VIDEO_COMPLETE_METHOD = "rewarded_complete";
    static final String REWARDED_VIDEO_CLOSED_METHOD = "rewarded_closed";
    static final String REWARDED_INTERSTITIAL_CLOSED_METHOD = "onRewardedInterstitialAdClosed";
    static final String REWARDED_INTERSTITIAL_COMPLETE_METHOD = "onRewardedInterstitialAdComplete";

    static final String MEDIA_DOWNLOADED_METHOD = "media_downloaded";
    static final String LOAD_SUCCESS_METHOD = "load_success";
}
