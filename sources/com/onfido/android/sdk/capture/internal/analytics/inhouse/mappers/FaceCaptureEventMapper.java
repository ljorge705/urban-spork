package com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEvent;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventType;
import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsPropertyKey;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsPropertyKeys;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016R\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/FaceCaptureEventMapper;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicAnalyticsEventMapper;", "()V", "screenName", "", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "getScreenName", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;)Ljava/lang/String;", "map", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEvent;", "event", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FaceCaptureEventMapper implements PublicAnalyticsEventMapper {
    private final String getScreenName(AnalyticsEvent analyticsEvent) {
        if (Intrinsics.areEqual(analyticsEvent.getEvent().getName(), EventNames.Face.FACE_VIDEO_CAPTURE)) {
            Object obj = analyticsEvent.getProperties().get(AnalyticsPropertyKeys.VIDEO_CAPTURE_STEP);
            String str = obj instanceof String ? (String) obj : null;
            if (str != null) {
                return "VIDEO_FACIAL_CAPTURE_STEP_" + StringsKt.takeLast(str, 1);
            }
        }
        return null;
    }

    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicAnalyticsEventMapper
    public OnfidoAnalyticsEvent map(AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        boolean z = !SetsKt.setOf((Object[]) new String[]{EventNames.Face.FACE_SELFIE_CAPTURE, EventNames.Face.FACE_VIDEO_CAPTURE, EventNames.Face.FACE_SELFIE_CONFIRMATION, EventNames.Face.FACE_VIDEO_CONFIRMATION}).contains(event.getEvent().getName());
        OnfidoAnalyticsEventType publicType = event.getEvent().getPublicType();
        String screenName = getScreenName(event);
        if (screenName == null) {
            screenName = event.getEvent().getPublicName();
        }
        if (z || publicType == null || screenName == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(OnfidoAnalyticsPropertyKey.SCREEN_NAME, screenName);
        linkedHashMap.put(OnfidoAnalyticsPropertyKey.SCREEN_MODE, EventMappingHelper.INSTANCE.getScreenMode(event));
        Object obj = event.getProperties().get(AnalyticsPropertyKeys.LIVENESS_CHALLENGE_TYPE);
        String str = obj instanceof String ? (String) obj : null;
        if (str != null) {
            linkedHashMap.put(OnfidoAnalyticsPropertyKey.VIDEO_CHALLENGE_TYPE, str);
        }
        return new OnfidoAnalyticsEvent(publicType, linkedHashMap);
    }
}
