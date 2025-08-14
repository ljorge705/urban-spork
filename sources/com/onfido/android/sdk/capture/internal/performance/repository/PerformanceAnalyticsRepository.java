package com.onfido.android.sdk.capture.internal.performance.repository;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.AnalyticsRepository;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.AnalyticsRequest;
import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsRepository;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/AnalyticsRepository;", "performanceAnalyticsApi", "Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsApi;", "onfidoAnalyticsMapper", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalyticsMapper;", "errorHandler", "Lcom/onfido/android/sdk/capture/network/error/ErrorHandler;", "(Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsApi;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalyticsMapper;Lcom/onfido/android/sdk/capture/network/error/ErrorHandler;)V", "track", "Lio/reactivex/rxjava3/core/Completable;", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PerformanceAnalyticsRepository implements AnalyticsRepository {
    private final ErrorHandler errorHandler;
    private final OnfidoAnalyticsMapper onfidoAnalyticsMapper;
    private final PerformanceAnalyticsApi performanceAnalyticsApi;

    @Inject
    public PerformanceAnalyticsRepository(PerformanceAnalyticsApi performanceAnalyticsApi, OnfidoAnalyticsMapper onfidoAnalyticsMapper, ErrorHandler errorHandler) {
        Intrinsics.checkNotNullParameter(performanceAnalyticsApi, "performanceAnalyticsApi");
        Intrinsics.checkNotNullParameter(onfidoAnalyticsMapper, "onfidoAnalyticsMapper");
        Intrinsics.checkNotNullParameter(errorHandler, "errorHandler");
        this.performanceAnalyticsApi = performanceAnalyticsApi;
        this.onfidoAnalyticsMapper = onfidoAnalyticsMapper;
        this.errorHandler = errorHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnalyticsRequest track$lambda$0(PerformanceAnalyticsRepository this$0, AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(event, "$event");
        return this$0.onfidoAnalyticsMapper.mapEventToRequest(event);
    }

    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.AnalyticsRepository
    public Completable track(final AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Single singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsRepository$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return PerformanceAnalyticsRepository.track$lambda$0(this.f$0, event);
            }
        });
        final PerformanceAnalyticsApi performanceAnalyticsApi = this.performanceAnalyticsApi;
        Completable completableCompose = singleFromCallable.flatMapCompletable(new Function() { // from class: com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsRepository.track.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Completable apply(AnalyticsRequest p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return performanceAnalyticsApi.track(p0);
            }
        }).compose(this.errorHandler.handleError());
        Intrinsics.checkNotNullExpressionValue(completableCompose, "compose(...)");
        return completableCompose;
    }
}
