package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.graphics.Bitmap;
import android.widget.FrameLayout;
import androidx.camera.view.PreviewView;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.ImageUtilsExtKt;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.time.Duration;
import org.reactivestreams.Publisher;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\b\u001a\u0015\u0012\f\u0012\n \n*\u0004\u0018\u00010\u00020\u00020\t¢\u0006\u0002\b\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/DefaultFrameSampler;", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "imageAnalysisFrameSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", Constants.KEY_HIDE_CLOSE, "", "getPreviewBitmap", "Lio/reactivex/rxjava3/core/Flowable;", "Landroid/graphics/Bitmap;", "previewView", "Landroidx/camera/view/PreviewView;", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "onPreviewAvailable", "Landroid/widget/FrameLayout;", "frameSamplingPeriod", "Lkotlin/time/Duration;", "onPreviewAvailable-HG0u8IE", "(Landroid/widget/FrameLayout;J)V", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultFrameSampler implements FrameSampler<OnfidoImage> {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int DESIRED_FRAME_WIDTH = 480;
    private final CompositeDisposable compositeDisposable;
    private final BehaviorSubject<OnfidoImage> imageAnalysisFrameSubject;
    private final SchedulersProvider schedulersProvider;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/DefaultFrameSampler$Companion;", "", "()V", "DESIRED_FRAME_WIDTH", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public DefaultFrameSampler(SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.schedulersProvider = schedulersProvider;
        BehaviorSubject<OnfidoImage> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create(...)");
        this.imageAnalysisFrameSubject = behaviorSubjectCreate;
        this.compositeDisposable = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flowable<Bitmap> getPreviewBitmap(final PreviewView previewView) {
        Flowable<Bitmap> flowableSubscribeOn = Flowable.defer(new Supplier() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return DefaultFrameSampler.getPreviewBitmap$lambda$0(previewView);
            }
        }).subscribeOn(this.schedulersProvider.getComputation());
        Intrinsics.checkNotNullExpressionValue(flowableSubscribeOn, "subscribeOn(...)");
        return flowableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Publisher getPreviewBitmap$lambda$0(PreviewView previewView) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(previewView, "$previewView");
        Bitmap frame = CameraXExtKt.getFrame(previewView);
        return frame != null ? Flowable.just(frame) : Flowable.empty();
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler
    public void close() {
        this.compositeDisposable.clear();
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler
    public Observable<? extends OnfidoImage> observeFrame() {
        Observable<OnfidoImage> observableSkip = this.imageAnalysisFrameSubject.hide().skip(1L);
        Intrinsics.checkNotNullExpressionValue(observableSkip, "skip(...)");
        return observableSkip;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler
    /* renamed from: onPreviewAvailable-HG0u8IE */
    public void mo5541onPreviewAvailableHG0u8IE(final FrameLayout previewView, long frameSamplingPeriod) {
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = Flowable.interval(Duration.m7440getInWholeMillisecondsimpl(frameSamplingPeriod), TimeUnit.MILLISECONDS, this.schedulersProvider.getComputation()).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler$onPreviewAvailable$1
            public final boolean test(long j) {
                return this.this$0.imageAnalysisFrameSubject.hasObservers();
            }

            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Number) obj).longValue());
            }
        }).onBackpressureLatest().flatMap(new Function() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler$onPreviewAvailable$2
            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }

            public final Publisher<? extends Bitmap> apply(long j) {
                DefaultFrameSampler defaultFrameSampler = this.this$0;
                FrameLayout frameLayout = previewView;
                Intrinsics.checkNotNull(frameLayout, "null cannot be cast to non-null type androidx.camera.view.PreviewView");
                return defaultFrameSampler.getPreviewBitmap((PreviewView) frameLayout);
            }
        }, false, 1).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler$onPreviewAvailable$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Bitmap bitmap) {
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, DefaultFrameSampler.DESIRED_FRAME_WIDTH, MathKt.roundToInt((DefaultFrameSampler.DESIRED_FRAME_WIDTH * (previewView.getHeight() / previewView.getWidth())) / 2) * 2, true);
                Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(...)");
                float width = previewView.getWidth() / bitmapCreateScaledBitmap.getWidth();
                this.imageAnalysisFrameSubject.onNext(new OnfidoImage(ImageUtilsExtKt.toNV21(bitmapCreateScaledBitmap), bitmapCreateScaledBitmap.getWidth(), bitmapCreateScaledBitmap.getHeight(), 0, new OnfidoImage.CropRect(width, (int) ((-((int) previewView.getTranslationY())) / width), (int) ((-((int) previewView.getTranslationX())) / width), previewView.getWidth(), previewView.getHeight()), bitmapCreateScaledBitmap));
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.DefaultFrameSampler$onPreviewAvailable$4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it, "Error on video frames subscription", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }
}
