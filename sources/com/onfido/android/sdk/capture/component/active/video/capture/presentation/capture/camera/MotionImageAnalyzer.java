package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import android.graphics.Bitmap;
import androidx.camera.view.PreviewView;
import androidx.media3.common.MimeTypes;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0003J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0016R!\u0010\u0004\u001a\u0015\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00020\u00020\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImageAnalyzer;", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImage;", "()V", "frameSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "analyze", "", MimeTypes.BASE_TYPE_IMAGE, "Landroid/graphics/Bitmap;", "previewView", "Landroidx/camera/view/PreviewView;", "scaleType", "Landroidx/camera/view/PreviewView$ScaleType;", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionImageAnalyzer implements ImageAnalyzer<MotionImage> {
    private final PublishSubject<MotionImage> frameSubject;

    @Inject
    public MotionImageAnalyzer() {
        PublishSubject<MotionImage> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.frameSubject = publishSubjectCreate;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer
    public void analyze(Bitmap image, PreviewView previewView, PreviewView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        this.frameSubject.onNext(new MotionImage(image, OnfidoRectF.INSTANCE.toOnfidoRectF(previewView)));
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer
    public Observable<? extends MotionImage> observeFrame() {
        Observable<MotionImage> observableHide = this.frameSubject.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        return observableHide;
    }
}
