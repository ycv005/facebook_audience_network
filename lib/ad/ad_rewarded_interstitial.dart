import 'dart:io';

import 'package:flutter/services.dart';

import '../constants.dart';

enum RewardedInterstitialAdResult {
  /// Rewarded interstitial ad.
  /// Error.
  ERROR,

  /// Rewarded interstitial loaded successfully.
  LOADED,

  /// Rewarded interstitial clicked.
  CLICKED,

  /// Rewarded interstitial impression logged.
  LOGGING_IMPRESSION,

  /// Rewarded interstitial played till the end. Use it to reward the user.
  VIDEO_COMPLETE,

  /// Rewarded interstitial closed.
  VIDEO_CLOSED,
}

class FacebookRewardedInterstitialAd {
  static void Function(RewardedInterstitialAdResult, dynamic)? _listener;

  static const _channel = const MethodChannel(REWARDED_INTERSTITIAL_CHANNEL);

  /// Loads a rewarded video Ad in background. Replace the default [placementId]
  /// with the one which you obtain by signing-up for Facebook Audience Network.
  ///
  /// [listener] passes [RewardedInterstitialAdResult] and information associated with
  /// the result to the implemented callback.
  ///
  /// Information will generally be of type Map with details such as:
  ///
  /// ```dart
  /// {
  ///   'placement\_id': "YOUR\_PLACEMENT\_ID",
  ///   'invalidated': false,
  ///   'error\_code': 2,
  ///   'error\_message': "No internet connection",
  /// }
  /// ```
  static Future<bool?> loadRewardedInterstitialAd({
    String placementId = "YOUR_PLACEMENT_ID",
    Function(RewardedInterstitialAdResult, dynamic)? listener,
  }) async {
    try {
      final args = <String, dynamic>{"id": placementId};

      if (Platform.isIOS) return false;

      final result = await _channel.invokeMethod(
        LOAD_REWARDED_INTERSTITIAL_METHOD,
        args,
      );
      _channel.setMethodCallHandler(_rewardedMethodCall);
      _listener = listener;

      return result;
    } on PlatformException {
      return false;
    }
  }

  /// Shows a rewarded video Ad after it has been loaded. (This needs to be
  /// called only after calling [loadRewardedInterstitialAd] function). [delay] is in
  /// milliseconds.
  ///
  /// Example:
  ///
  /// ```dart
  /// FacebookRewardedInterstitialAd.loadRewardedInterstitialAd(
  ///   listener: (result, value) {
  ///     if(result == RewardedInterstitialAdResult.LOADED)
  ///       FacebookRewardedInterstitialAd.showRewardedInterstitialAd();
  ///   },
  /// );
  /// ```
  static Future<bool?> showRewardedInterstitialAd({int delay = 0}) async {
    try {
      final args = <String, dynamic>{"delay": delay};

      final result = await _channel.invokeMethod(
        SHOW_REWARDED_INTERSTITIAL_METHOD,
        args,
      );

      return result;
    } on PlatformException {
      return false;
    }
  }

  /// Removes the rewarded video Ad.
  static Future<bool?> destroyRewardedInterstitialAd() async {
    try {
      final result = await _channel.invokeMethod(
        DESTROY_REWARDED_INTERSTITIAL_METHOD,
      );
      return result;
    } on PlatformException {
      return false;
    }
  }

  static Future<dynamic> _rewardedMethodCall(MethodCall call) {
    switch (call.method) {
      case REWARDED_INTERSTITIAL_COMPLETE_METHOD:
        if (_listener != null)
          _listener!(
            RewardedInterstitialAdResult.VIDEO_COMPLETE,
            call.arguments,
          );
        break;
      case REWARDED_INTERSTITIAL_CLOSED_METHOD:
        if (_listener != null)
          _listener!(RewardedInterstitialAdResult.VIDEO_CLOSED, call.arguments);
        break;
      case ERROR_METHOD:
        if (_listener != null)
          _listener!(RewardedInterstitialAdResult.ERROR, call.arguments);
        break;
      case LOADED_METHOD:
        if (_listener != null)
          _listener!(RewardedInterstitialAdResult.LOADED, call.arguments);
        break;
      case CLICKED_METHOD:
        if (_listener != null)
          _listener!(RewardedInterstitialAdResult.CLICKED, call.arguments);
        break;
      case LOGGING_IMPRESSION_METHOD:
        if (_listener != null)
          _listener!(
            RewardedInterstitialAdResult.LOGGING_IMPRESSION,
            call.arguments,
          );
        break;
    }
    return Future.value(true);
  }
}
