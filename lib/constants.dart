const String MAIN_CHANNEL = "fb.audience.network.io";
const String BANNER_AD_CHANNEL = MAIN_CHANNEL + "/bannerAd";
const String INTERSTITIAL_AD_CHANNEL = MAIN_CHANNEL + "/interstitialAd";
const String NATIVE_AD_CHANNEL = MAIN_CHANNEL + "/nativeAd";
const String NATIVE_BANNER_AD_CHANNEL = MAIN_CHANNEL + "/nativeBannerAd";
const String REWARDED_VIDEO_CHANNEL = MAIN_CHANNEL + "/rewardedAd";
const String REWARDED_INTERSTITIAL_CHANNEL =
    MAIN_CHANNEL + "/rewardedInterstitialAd";

// Method names
const String LOAD_REWARDED_INTERSTITIAL_METHOD = "loadRewardedInterstitialAd";
const String SHOW_REWARDED_INTERSTITIAL_METHOD = "showRewardedInterstitialAd";
const String DESTROY_REWARDED_INTERSTITIAL_METHOD =
    "destroyRewardedInterstitialAd";

// Events
const String REWARDED_INTERSTITIAL_COMPLETE_METHOD =
    "onRewardedInterstitialAdComplete";
const String REWARDED_INTERSTITIAL_CLOSED_METHOD =
    "onRewardedInterstitialAdClosed";

const String INIT_METHOD = "init";

const String SHOW_INTERSTITIAL_METHOD = "showInterstitialAd";
const String LOAD_INTERSTITIAL_METHOD = "loadInterstitialAd";
const String DESTROY_INTERSTITIAL_METHOD = "destroyInterstitialAd";

const String SHOW_REWARDED_VIDEO_METHOD = "showRewardedAd";
const String LOAD_REWARDED_VIDEO_METHOD = "loadRewardedAd";
const String DESTROY_REWARDED_VIDEO_METHOD = "destroyRewardedAd";

const String DISPLAYED_METHOD = "displayed";
const String DISMISSED_METHOD = "dismissed";
const String ERROR_METHOD = "error";
const String LOADED_METHOD = "loaded";
const String CLICKED_METHOD = "clicked";
const String LOGGING_IMPRESSION_METHOD = "logging_impression";

const String REWARDED_VIDEO_COMPLETE_METHOD = "rewarded_complete";
const String REWARDED_VIDEO_CLOSED_METHOD = "rewarded_closed";

const String MEDIA_DOWNLOADED_METHOD = "media_downloaded";

const String LOAD_SUCCESS_METHOD = "load_success";
