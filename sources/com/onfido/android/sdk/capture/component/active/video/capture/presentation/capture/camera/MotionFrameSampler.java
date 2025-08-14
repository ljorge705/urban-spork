package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import android.graphics.Bitmap;
import android.widget.FrameLayout;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.preview.PreviewBitmapHelper;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import org.reactivestreams.Publisher;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0016J\"\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\n\u001a\u0015\u0012\f\u0012\n \f*\u0004\u0018\u00010\u00020\u00020\u000b¢\u0006\u0002\b\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionFrameSampler;", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImage;", "previewBitmapHelper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/preview/PreviewBitmapHelper;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/view/preview/PreviewBitmapHelper;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "frameSamplingSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", Constants.KEY_HIDE_CLOSE, "", "getPreviewBitmap", "Lio/reactivex/rxjava3/core/Flowable;", "Landroid/graphics/Bitmap;", "previewView", "Landroid/widget/FrameLayout;", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "onPreviewAvailable", "frameSamplingPeriod", "Lkotlin/time/Duration;", "onPreviewAvailable-HG0u8IE", "(Landroid/widget/FrameLayout;J)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionFrameSampler implements FrameSampler<MotionImage> {
    private final CompositeDisposable compositeDisposable;
    private final PublishSubject<MotionImage> frameSamplingSubject;
    private final PreviewBitmapHelper previewBitmapHelper;
    private final SchedulersProvider schedulersProvider;

    @Inject
    public MotionFrameSampler(PreviewBitmapHelper previewBitmapHelper, SchedulersProvider schedulersProvider) {
        Intrinsics.checkNotNullParameter(previewBitmapHelper, "previewBitmapHelper");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        this.previewBitmapHelper = previewBitmapHelper;
        this.schedulersProvider = schedulersProvider;
        this.compositeDisposable = new CompositeDisposable();
        PublishSubject<MotionImage> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.frameSamplingSubject = publishSubjectCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flowable<Bitmap> getPreviewBitmap(final FrameLayout previewView) {
        Flowable<Bitmap> flowableSubscribeOn = Flowable.defer(new Supplier() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionFrameSampler$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return MotionFrameSampler.getPreviewBitmap$lambda$0(this.f$0, previewView);
            }
        }).subscribeOn(this.schedulersProvider.getComputation());
        Intrinsics.checkNotNullExpressionValue(flowableSubscribeOn, "subscribeOn(...)");
        return flowableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Publisher getPreviewBitmap$lambda$0(MotionFrameSampler this$0, FrameLayout previewView) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(previewView, "$previewView");
        Bitmap bitmap = this$0.previewBitmapHelper.getBitmap(previewView);
        return bitmap != null ? Flowable.just(bitmap) : Flowable.empty();
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler
    public void close() {
        this.compositeDisposable.clear();
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler
    public Observable<? extends MotionImage> observeFrame() {
        Observable<MotionImage> observableHide = this.frameSamplingSubject.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        return observableHide;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler
    /* renamed from: onPreviewAvailable-HG0u8IE, reason: not valid java name */
    public void mo5541onPreviewAvailableHG0u8IE(final FrameLayout previewView, long frameSamplingPeriod) {
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = Flowable.interval(Duration.m7440getInWholeMillisecondsimpl(frameSamplingPeriod), TimeUnit.MILLISECONDS, this.schedulersProvider.getComputation()).onBackpressureLatest().flatMap(new Function() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionFrameSampler$onPreviewAvailable$1
            @Override // io.reactivex.rxjava3.functions.Function
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply(((Number) obj).longValue());
            }

            public final Publisher<? extends Bitmap> apply(long j) {
                return this.this$0.getPreviewBitmap(previewView);
            }
        }, false, 1).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionFrameSampler$onPreviewAvailable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Bitmap bitmap) {
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                this.this$0.frameSamplingSubject.onNext(new MotionImage(bitmap, OnfidoRectF.INSTANCE.toOnfidoRectF(bitmap)));
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionFrameSampler$onPreviewAvailable$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e("Error on video frames subscription", new Object[0]);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }
}
