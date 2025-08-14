package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import io.reactivex.rxjava3.core.Completable;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/AnalyticsRepository;", "", "track", "Lio/reactivex/rxjava3/core/Completable;", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface AnalyticsRepository {
    Completable track(AnalyticsEvent event);
}
