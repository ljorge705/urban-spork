package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.AnalyticsRequest;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAnalyticsApi;
import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/InhouseAnalyticsRepository;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/AnalyticsRepository;", "analyticsApi", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/OnfidoAnalyticsApi;", "onfidoAnalyticsMapper", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalyticsMapper;", "errorHandler", "Lcom/onfido/android/sdk/capture/network/error/ErrorHandler;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/OnfidoAnalyticsApi;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalyticsMapper;Lcom/onfido/android/sdk/capture/network/error/ErrorHandler;)V", "track", "Lio/reactivex/rxjava3/core/Completable;", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InhouseAnalyticsRepository implements AnalyticsRepository {
    private final OnfidoAnalyticsApi analyticsApi;
    private final ErrorHandler errorHandler;
    private final OnfidoAnalyticsMapper onfidoAnalyticsMapper;

    @Inject
    public InhouseAnalyticsRepository(OnfidoAnalyticsApi analyticsApi, OnfidoAnalyticsMapper onfidoAnalyticsMapper, ErrorHandler errorHandler) {
        Intrinsics.checkNotNullParameter(analyticsApi, "analyticsApi");
        Intrinsics.checkNotNullParameter(onfidoAnalyticsMapper, "onfidoAnalyticsMapper");
        Intrinsics.checkNotNullParameter(errorHandler, "errorHandler");
        this.analyticsApi = analyticsApi;
        this.onfidoAnalyticsMapper = onfidoAnalyticsMapper;
        this.errorHandler = errorHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnalyticsRequest track$lambda$0(InhouseAnalyticsRepository this$0, AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(event, "$event");
        return this$0.onfidoAnalyticsMapper.mapEventToRequest(event);
    }

    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.AnalyticsRepository
    public Completable track(final AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Single singleFromCallable = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.internal.analytics.inhouse.InhouseAnalyticsRepository$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return InhouseAnalyticsRepository.track$lambda$0(this.f$0, event);
            }
        });
        final OnfidoAnalyticsApi onfidoAnalyticsApi = this.analyticsApi;
        Completable completableCompose = singleFromCallable.flatMapCompletable(new Function() { // from class: com.onfido.android.sdk.capture.internal.analytics.inhouse.InhouseAnalyticsRepository.track.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Completable apply(AnalyticsRequest p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return onfidoAnalyticsApi.track(p0);
            }
        }).compose(this.errorHandler.handleError());
        Intrinsics.checkNotNullExpressionValue(completableCompose, "compose(...)");
        return completableCompose;
    }
}
