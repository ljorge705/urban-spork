package com.airbnb.lottie;

/* loaded from: classes5.dex */
public class Lottie {
    private Lottie() {
    }

    public static void initialize(LottieConfig lottieConfig) {
        L.setFetcher(lottieConfig.networkFetcher);
        L.setCacheProvider(lottieConfig.cacheProvider);
        L.setTraceEnabled(lottieConfig.enableSystraceMarkers);
    }
}
