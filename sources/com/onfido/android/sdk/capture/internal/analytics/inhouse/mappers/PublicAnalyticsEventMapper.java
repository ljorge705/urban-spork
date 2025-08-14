package com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicAnalyticsEventMapper;", "", "map", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEvent;", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface PublicAnalyticsEventMapper {
    OnfidoAnalyticsEvent map(AnalyticsEvent event);
}
