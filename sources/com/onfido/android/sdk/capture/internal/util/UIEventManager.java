package com.onfido.android.sdk.capture.internal.util;

import androidx.exifinterface.media.ExifInterface;
import com.onfido.android.sdk.capture.internal.util.UIEvent;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.sentry.protocol.SentryStackFrame;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0007¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0007¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u000b\u001a\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\f¢\u0006\u0002\b\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/UIEventManager;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/onfido/android/sdk/capture/internal/util/UIEvent;", "", "()V", SentryStackFrame.JsonKeys.LOCK, "uiEvents", "Lio/reactivex/rxjava3/core/Observable;", "", "getUiEvents", "()Lio/reactivex/rxjava3/core/Observable;", "uiEventsSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lio/reactivex/rxjava3/annotations/NonNull;", "consumeUIEvent", "", "uiEvent", "(Lcom/onfido/android/sdk/capture/internal/util/UIEvent;)V", "emitUIEvent", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UIEventManager<T extends UIEvent> {
    private final Object lock = new Object();
    private final Observable<List<T>> uiEvents;
    private final BehaviorSubject<List<T>> uiEventsSubject;

    public UIEventManager() {
        BehaviorSubject<List<T>> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(CollectionsKt.emptyList());
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.uiEventsSubject = behaviorSubjectCreateDefault;
        Observable<List<T>> observableDistinctUntilChanged = behaviorSubjectCreateDefault.hide().distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        this.uiEvents = observableDistinctUntilChanged;
    }

    public final void consumeUIEvent(T uiEvent) {
        Intrinsics.checkNotNullParameter(uiEvent, "uiEvent");
        synchronized (this.lock) {
            List<T> value = this.uiEventsSubject.getValue();
            if (value == null) {
                value = CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : value) {
                if (((UIEvent) obj).getId() != uiEvent.getId()) {
                    arrayList.add(obj);
                }
            }
            this.uiEventsSubject.onNext(arrayList);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void emitUIEvent(T uiEvent) {
        Intrinsics.checkNotNullParameter(uiEvent, "uiEvent");
        synchronized (this.lock) {
            BehaviorSubject<List<T>> behaviorSubject = this.uiEventsSubject;
            List<T> value = behaviorSubject.getValue();
            if (value == null) {
                value = CollectionsKt.emptyList();
            }
            behaviorSubject.onNext(CollectionsKt.plus((Collection<? extends T>) value, uiEvent));
            Unit unit = Unit.INSTANCE;
        }
    }

    public final Observable<List<T>> getUiEvents() {
        return this.uiEvents;
    }
}
