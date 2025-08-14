package com.onfido.android.sdk.capture.analytics;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: OnfidoAnalyticsEventResultReceiver.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000e*\u0004\u0018\u00010\fH\u0012R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventResultReceiver;", "Landroid/os/ResultReceiver;", "onfidoAnalyticsEventListener", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventListener;", "(Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventListener;)V", "getOnfidoAnalyticsEventListener", "()Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventListener;", "onReceiveResult", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "toPropertiesMap", "", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsPropertyKey;", "", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class OnfidoAnalyticsEventResultReceiver extends ResultReceiver {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String KEY_EVENT_TYPE = "event_type";
    public static final String KEY_PROPERTIES = "properties";
    private final OnfidoAnalyticsEventListener onfidoAnalyticsEventListener;

    public OnfidoAnalyticsEventListener getOnfidoAnalyticsEventListener() {
        return this.onfidoAnalyticsEventListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnfidoAnalyticsEventResultReceiver(OnfidoAnalyticsEventListener onfidoAnalyticsEventListener) {
        super(new Handler(Looper.getMainLooper()));
        Intrinsics.checkNotNullParameter(onfidoAnalyticsEventListener, "onfidoAnalyticsEventListener");
        this.onfidoAnalyticsEventListener = onfidoAnalyticsEventListener;
    }

    @Override // android.os.ResultReceiver
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Intrinsics.checkNotNullParameter(resultData, "resultData");
        super.onReceiveResult(resultCode, resultData);
        String string = resultData.getString("event_type");
        if (string == null) {
            string = "";
        }
        getOnfidoAnalyticsEventListener().onEvent(new OnfidoAnalyticsEvent(OnfidoAnalyticsEventType.valueOf(string), toPropertiesMap(resultData.getBundle(KEY_PROPERTIES))));
    }

    private Map<OnfidoAnalyticsPropertyKey, String> toPropertiesMap(Bundle bundle) {
        Set<String> setKeySet;
        if (bundle == null || (setKeySet = bundle.keySet()) == null) {
            return MapsKt.emptyMap();
        }
        Set<String> set = setKeySet;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set, 10)), 16));
        for (String str : set) {
            Intrinsics.checkNotNull(str);
            Pair pair = TuplesKt.to(OnfidoAnalyticsPropertyKey.valueOf(str), bundle.getString(str));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    /* compiled from: OnfidoAnalyticsEventResultReceiver.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventResultReceiver$Companion;", "", "()V", "KEY_EVENT_TYPE", "", "getKEY_EVENT_TYPE$annotations", "KEY_PROPERTIES", "getKEY_PROPERTIES$annotations", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getKEY_EVENT_TYPE$annotations() {
        }

        public static /* synthetic */ void getKEY_PROPERTIES$annotations() {
        }

        private Companion() {
        }
    }
}
