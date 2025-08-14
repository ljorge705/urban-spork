package com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEvent;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventType;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsPropertyKey;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/FlowExitPublicEventMapper;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicAnalyticsEventMapper;", "()V", "map", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEvent;", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FlowExitPublicEventMapper implements PublicAnalyticsEventMapper {
    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicAnalyticsEventMapper
    public OnfidoAnalyticsEvent map(AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String str = (String) event.getProperties().get(AnalyticsPropertyKeys.STEP);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (str != null) {
            linkedHashMap.put(OnfidoAnalyticsPropertyKey.STEP, str);
        }
        return new OnfidoAnalyticsEvent(OnfidoAnalyticsEventType.ACTION, linkedHashMap);
    }
}
