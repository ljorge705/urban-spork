package com.onfido.android.sdk.capture.utils;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.reactivestreams.Publisher;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a#\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0006H\u0080\b\u001a!\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002H\u0000¢\u0006\u0002\u0010\u0004\u001a#\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\tH\u0080\b\u001a#\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0006H\u0087\b\u001a#\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u0006\u0012\u0002\b\u00030\u0006H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\u0002\u001aI\u0010\u0010\u001a\r\u0012\u0004\u0012\u0002H\u00020\u0001¢\u0006\u0002\b\u0011\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0000\u001ah\u0010\u0019\u001a\r\u0012\u0004\u0012\u0002H\u00020\u0006¢\u0006\u0002\b\u0011\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00182#\b\u0002\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020#0\u001eH\u0007¨\u0006$"}, d2 = {"asSingle", "Lio/reactivex/rxjava3/core/Single;", ExifInterface.GPS_DIRECTION_TRUE, "", "(Ljava/lang/Object;)Lio/reactivex/rxjava3/core/Single;", "cast", "Lio/reactivex/rxjava3/core/Observable;", "defer", "filterIsInstance", "Lio/reactivex/rxjava3/core/Flowable;", "filterIsNotInstance", "plusAssign", "", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "disposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "retryWithDelay", "Lio/reactivex/rxjava3/annotations/NonNull;", "retryCount", "", "retryDelay", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "retryWithExponentialBackOff", "retryFactor", "", "maxNumberOfRetries", "skipRetry", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "throwable", "", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RxExtensionsKt {
    public static final <T> Single<T> asSingle(T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Single<T> singleJust = Single.just(t);
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }

    public static final /* synthetic */ <T> Observable<T> cast(Observable<?> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCast = observable.cast(Object.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        return observableCast;
    }

    public static final <T> Single<T> defer(T t) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Single<T> singleJust = Single.just(t);
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }

    public static final /* synthetic */ <T> Flowable<T> filterIsInstance(Flowable<?> flowable) {
        Intrinsics.checkNotNullParameter(flowable, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Flowable flowableOfType = flowable.ofType(Object.class);
        Intrinsics.checkNotNullExpressionValue(flowableOfType, "ofType(...)");
        return flowableOfType;
    }

    public static final /* synthetic */ <T> Observable<T> filterIsInstance(Observable<?> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.needClassReification();
        Observable<?> observableFilter = observable.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.utils.RxExtensionsKt.filterIsInstance.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                return it instanceof Object;
            }
        });
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCast = observableFilter.cast(Object.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        return observableCast;
    }

    public static final /* synthetic */ <T> Observable<T> filterIsNotInstance(Observable<?> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.needClassReification();
        Observable<?> observableFilter = observable.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.utils.RxExtensionsKt.filterIsNotInstance.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                return !(it instanceof Object);
            }
        });
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCast = observableFilter.cast(Object.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        return observableCast;
    }

    public static final void plusAssign(CompositeDisposable compositeDisposable, Disposable disposable) {
        Intrinsics.checkNotNullParameter(compositeDisposable, "<this>");
        Intrinsics.checkNotNullParameter(disposable, "disposable");
        compositeDisposable.add(disposable);
    }

    public static final <T> Single<T> retryWithDelay(Single<T> single, final long j, final long j2, final TimeUnit timeUnit, final Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(single, "<this>");
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        Single<T> singleRetryWhen = single.retryWhen(new Function() { // from class: com.onfido.android.sdk.capture.utils.RxExtensionsKt.retryWithDelay.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Publisher<?> apply(Flowable<Throwable> f) {
                Intrinsics.checkNotNullParameter(f, "f");
                return f.take(j).delay(j2, timeUnit, scheduler);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleRetryWhen, "retryWhen(...)");
        return singleRetryWhen;
    }

    public static /* synthetic */ Single retryWithDelay$default(Single single, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, int i, Object obj) {
        if ((i & 4) != 0) {
            timeUnit = TimeUnit.MILLISECONDS;
        }
        return retryWithDelay(single, j, j2, timeUnit, scheduler);
    }

    public static final <T> Observable<T> retryWithExponentialBackOff(Observable<T> observable, final int i, final int i2, final Scheduler scheduler, final Function1<? super Throwable, Boolean> skipRetry) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        Intrinsics.checkNotNullParameter(skipRetry, "skipRetry");
        Observable<T> observableRetryWhen = observable.retryWhen(new Function() { // from class: com.onfido.android.sdk.capture.utils.RxExtensionsKt.retryWithExponentialBackOff.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<?> apply(Observable<Throwable> errors) {
                Intrinsics.checkNotNullParameter(errors, "errors");
                Observable<R> observableZipWith = errors.zipWith(Observable.range(1, i2 + 1), new BiFunction() { // from class: com.onfido.android.sdk.capture.utils.RxExtensionsKt.retryWithExponentialBackOff.2.1
                    @Override // io.reactivex.rxjava3.functions.BiFunction
                    public final Pair<Throwable, Integer> apply(Throwable p0, Integer p1) {
                        Intrinsics.checkNotNullParameter(p0, "p0");
                        Intrinsics.checkNotNullParameter(p1, "p1");
                        return new Pair<>(p0, p1);
                    }
                });
                final int i3 = i2;
                final Function1<Throwable, Boolean> function1 = skipRetry;
                final int i4 = i;
                final Scheduler scheduler2 = scheduler;
                return observableZipWith.flatMap(new Function() { // from class: com.onfido.android.sdk.capture.utils.RxExtensionsKt.retryWithExponentialBackOff.2.2
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final ObservableSource<? extends Long> apply(Pair<? extends Throwable, Integer> pair) {
                        Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                        Throwable thComponent1 = pair.component1();
                        Integer numComponent2 = pair.component2();
                        int i5 = i3 + 1;
                        if (numComponent2 == null || numComponent2.intValue() != i5) {
                            Function1<Throwable, Boolean> function12 = function1;
                            Intrinsics.checkNotNull(thComponent1);
                            if (!function12.invoke(thComponent1).booleanValue()) {
                                return Observable.timer(MathKt.roundToLong(Math.pow(i4, numComponent2.intValue())), TimeUnit.SECONDS, scheduler2);
                            }
                        }
                        return Observable.error(thComponent1);
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableRetryWhen, "retryWhen(...)");
        return observableRetryWhen;
    }

    public static /* synthetic */ Observable retryWithExponentialBackOff$default(Observable observable, int i, int i2, Scheduler scheduler, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 2;
        }
        if ((i3 & 2) != 0) {
            i2 = 3;
        }
        if ((i3 & 8) != 0) {
            function1 = new Function1<Throwable, Boolean>() { // from class: com.onfido.android.sdk.capture.utils.RxExtensionsKt.retryWithExponentialBackOff.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(Throwable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.FALSE;
                }
            };
        }
        return retryWithExponentialBackOff(observable, i, i2, scheduler, function1);
    }
}
