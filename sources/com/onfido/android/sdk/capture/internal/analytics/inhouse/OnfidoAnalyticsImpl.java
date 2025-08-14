package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers.PublicEventMapper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.utils.EventCache;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalyticsImpl;", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/UserAnalytics;", "analyticsRepository", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/AnalyticsRepository;", "schedulers", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "eventCache", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/utils/EventCache;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "publicEventMapper", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicEventMapper;", "analyticsEventListener", "Landroid/os/ResultReceiver;", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/AnalyticsRepository;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/utils/EventCache;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/PublicEventMapper;Landroid/os/ResultReceiver;)V", "disposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "clear", "", "logFailedEvent", "event", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "error", "", "sendPendingEvents", "track", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoAnalyticsImpl extends UserAnalytics {
    private final AnalyticsRepository analyticsRepository;
    private final CompositeDisposable disposable;
    private final EventCache eventCache;
    private final OnfidoRemoteConfig remoteConfig;
    private final SchedulersProvider schedulers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnfidoAnalyticsImpl(AnalyticsRepository analyticsRepository, SchedulersProvider schedulers, EventCache eventCache, OnfidoRemoteConfig remoteConfig, PublicEventMapper publicEventMapper, ResultReceiver resultReceiver) {
        super(publicEventMapper, resultReceiver);
        Intrinsics.checkNotNullParameter(analyticsRepository, "analyticsRepository");
        Intrinsics.checkNotNullParameter(schedulers, "schedulers");
        Intrinsics.checkNotNullParameter(eventCache, "eventCache");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(publicEventMapper, "publicEventMapper");
        this.analyticsRepository = analyticsRepository;
        this.schedulers = schedulers;
        this.eventCache = eventCache;
        this.remoteConfig = remoteConfig;
        this.disposable = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logFailedEvent(AnalyticsEvent event, Throwable error) {
        Map<String, Object> properties = event.getProperties();
        ArrayList arrayList = new ArrayList(properties.size());
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            arrayList.add(entry.getKey() + " : " + entry.getValue());
        }
        Timber.INSTANCE.e(error, "Failed to track event: " + (event.getEvent().getName() + '(' + arrayList + ')'), new Object[0]);
    }

    private final void sendPendingEvents() {
        for (final AnalyticsEvent analyticsEvent : CollectionsKt.take(this.eventCache.getEvents(), 2)) {
            CompositeDisposable compositeDisposable = this.disposable;
            Disposable disposableSubscribe = this.analyticsRepository.track(analyticsEvent).subscribeOn(this.schedulers.getIo()).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsImpl$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    OnfidoAnalyticsImpl.sendPendingEvents$lambda$2$lambda$1(this.f$0, analyticsEvent);
                }
            }, new Consumer() { // from class: com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsImpl$sendPendingEvents$1$2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    this.this$0.logFailedEvent(analyticsEvent, error);
                }
            });
            Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
            RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendPendingEvents$lambda$2$lambda$1(OnfidoAnalyticsImpl this$0, AnalyticsEvent pendingEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pendingEvent, "$pendingEvent");
        this$0.eventCache.removeEvent(pendingEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void track$lambda$0(OnfidoAnalyticsImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendPendingEvents();
    }

    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.UserAnalytics, com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics
    public void clear() {
        super.clear();
        this.disposable.clear();
    }

    @Override // com.onfido.android.sdk.capture.internal.analytics.inhouse.UserAnalytics, com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics
    public void track(final AnalyticsEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        super.track(event);
        if (this.remoteConfig.isInhouseAnalyticsEnabled()) {
            CompositeDisposable compositeDisposable = this.disposable;
            Disposable disposableSubscribe = this.analyticsRepository.track(event).subscribeOn(this.schedulers.getIo()).subscribe(new Action() { // from class: com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsImpl$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    OnfidoAnalyticsImpl.track$lambda$0(this.f$0);
                }
            }, new Consumer() { // from class: com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalyticsImpl.track.2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    OnfidoAnalyticsImpl.this.eventCache.addEvent(event);
                    OnfidoAnalyticsImpl.this.logFailedEvent(event, error);
                }
            });
            Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
            RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
        }
    }
}
