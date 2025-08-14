package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.core.os.BundleKt;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEvent;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsPropertyKey;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicEventMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\r\u001a\u00020\u000e*\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000fH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/UserAnalytics;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "publicEventMapper", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicEventMapper;", "analyticsEventListener", "Landroid/os/ResultReceiver;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicEventMapper;Landroid/os/ResultReceiver;)V", "clear", "", "track", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "trackUserEvent", "toBundle", "Landroid/os/Bundle;", "", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsPropertyKey;", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class UserAnalytics implements OnfidoAnalytics {
    private final ResultReceiver analyticsEventListener;
    private final PublicEventMapper publicEventMapper;

    public UserAnalytics(PublicEventMapper publicEventMapper, ResultReceiver resultReceiver) {
        Intrinsics.checkNotNullParameter(publicEventMapper, "publicEventMapper");
        this.publicEventMapper = publicEventMapper;
        this.analyticsEventListener = resultReceiver;
    }

    private final Bundle toBundle(Map<OnfidoAnalyticsPropertyKey, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<OnfidoAnalyticsPropertyKey, String> entry : map.entrySet()) {
            arrayList.add(TuplesKt.to(entry.getKey().name(), entry.getValue()));
        }
        Pair[] pairArr = (Pair[]) CollectionsKt.toList(arrayList).toArray(new Pair[0]);
        return BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
    }

    private final void trackUserEvent(AnalyticsEvent event) {
        ResultReceiver resultReceiver;
        OnfidoAnalyticsEvent map = this.publicEventMapper.map(event);
        if (map == null || (resultReceiver = this.analyticsEventListener) == null) {
            return;
        }
        resultReceiver.send(-1, BundleKt.bundleOf(TuplesKt.to("event_type", map.getType().name()), TuplesKt.to(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, toBundle(map.getProperties()))));
    }

    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics
    public void clear() {
    }

    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics
    public void track(AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        trackUserEvent(event);
    }
}
