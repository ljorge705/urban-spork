package com.google.android.play.core.review;

import android.os.Bundle;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.SentryStackFrame;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.play:review@@2.0.1 */
/* loaded from: classes3.dex */
public final class zzj {
    private static final Set zza = new HashSet(Arrays.asList(SentryStackFrame.JsonKeys.NATIVE, "unity"));
    private static final Map zzb = new HashMap();
    private static final com.google.android.play.core.review.internal.zzi zzc = new com.google.android.play.core.review.internal.zzi("PlayCoreVersion");

    public static Bundle zza() {
        Bundle bundle = new Bundle();
        Map mapZzb = zzb();
        bundle.putInt("playcore_version_code", ((Integer) mapZzb.get(SentryBaseEvent.DEFAULT_PLATFORM)).intValue());
        if (mapZzb.containsKey(SentryStackFrame.JsonKeys.NATIVE)) {
            bundle.putInt("playcore_native_version", ((Integer) mapZzb.get(SentryStackFrame.JsonKeys.NATIVE)).intValue());
        }
        if (mapZzb.containsKey("unity")) {
            bundle.putInt("playcore_unity_version", ((Integer) mapZzb.get("unity")).intValue());
        }
        return bundle;
    }

    public static synchronized Map zzb() {
        Map map;
        map = zzb;
        map.put(SentryBaseEvent.DEFAULT_PLATFORM, 11004);
        return map;
    }
}
