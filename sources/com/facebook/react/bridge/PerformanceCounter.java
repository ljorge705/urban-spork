package com.facebook.react.bridge;

import java.util.Map;

/* loaded from: classes5.dex */
interface PerformanceCounter {
    Map<String, Long> getPerformanceCounters();

    void profileNextBatch();
}
