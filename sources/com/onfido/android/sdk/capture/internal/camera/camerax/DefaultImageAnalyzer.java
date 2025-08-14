package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.camera.view.PreviewView;
import androidx.media3.common.MimeTypes;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.utils.ImageUtilsExtKt;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0003J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013H\u0016R!\u0010\u0004\u001a\u0015\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00020\u00020\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/DefaultImageAnalyzer;", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageAnalyzer;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "()V", "imageAnalysisFrameSubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "analyze", "", MimeTypes.BASE_TYPE_IMAGE, "Landroid/graphics/Bitmap;", "previewView", "Landroidx/camera/view/PreviewView;", "scaleType", "Landroidx/camera/view/PreviewView$ScaleType;", "getBitmapForFillCenter", "getBitmapForFitCenter", "observeFrame", "Lio/reactivex/rxjava3/core/Observable;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultImageAnalyzer implements ImageAnalyzer<OnfidoImage> {
    private final BehaviorSubject<OnfidoImage> imageAnalysisFrameSubject;

    @Inject
    public DefaultImageAnalyzer() {
        BehaviorSubject<OnfidoImage> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create(...)");
        this.imageAnalysisFrameSubject = behaviorSubjectCreate;
    }

    private final Bitmap getBitmapForFillCenter(Bitmap image, PreviewView previewView) {
        Bitmap bitmapCreateBitmap;
        float width = previewView.getWidth() / previewView.getHeight();
        if (width > image.getWidth() / image.getHeight()) {
            int iRoundToInt = MathKt.roundToInt((image.getWidth() / width) / 2) * 2;
            bitmapCreateBitmap = Bitmap.createBitmap(image, 0, (image.getHeight() - iRoundToInt) / 2, image.getWidth(), iRoundToInt);
        } else {
            int iRoundToInt2 = MathKt.roundToInt((image.getHeight() * width) / 2) * 2;
            bitmapCreateBitmap = Bitmap.createBitmap(image, (image.getWidth() - iRoundToInt2) / 2, 0, iRoundToInt2, image.getHeight());
        }
        Intrinsics.checkNotNull(bitmapCreateBitmap);
        return bitmapCreateBitmap;
    }

    private final Bitmap getBitmapForFitCenter(Bitmap image, PreviewView previewView) {
        float width = previewView.getWidth() / previewView.getHeight();
        if (width > image.getWidth() / image.getHeight()) {
            int iRoundToInt = MathKt.roundToInt((image.getHeight() * width) / 2) * 2;
            int width2 = (iRoundToInt - image.getWidth()) / 2;
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iRoundToInt, image.getHeight(), image.getConfig());
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
            new Canvas(bitmapCreateBitmap).drawBitmap(image, width2, 0.0f, (Paint) null);
            return bitmapCreateBitmap;
        }
        int iRoundToInt2 = MathKt.roundToInt((image.getWidth() / width) / 2) * 2;
        int height = (iRoundToInt2 - image.getHeight()) / 2;
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(image.getWidth(), iRoundToInt2, image.getConfig());
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(...)");
        new Canvas(bitmapCreateBitmap2).drawBitmap(image, 0.0f, height, (Paint) null);
        return bitmapCreateBitmap2;
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer
    public void analyze(Bitmap image, PreviewView previewView, PreviewView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(previewView, "previewView");
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        Bitmap bitmapForFitCenter = scaleType == PreviewView.ScaleType.FIT_CENTER ? getBitmapForFitCenter(image, previewView) : getBitmapForFillCenter(image, previewView);
        float width = previewView.getWidth() / bitmapForFitCenter.getWidth();
        this.imageAnalysisFrameSubject.onNext(new OnfidoImage(ImageUtilsExtKt.toNV21(bitmapForFitCenter), bitmapForFitCenter.getWidth(), bitmapForFitCenter.getHeight(), 0, new OnfidoImage.CropRect(width, (int) ((-((int) previewView.getTranslationY())) / width), (int) ((-((int) previewView.getTranslationX())) / width), previewView.getWidth(), previewView.getHeight()), bitmapForFitCenter));
    }

    @Override // com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer
    public Observable<? extends OnfidoImage> observeFrame() {
        Observable<OnfidoImage> observableHide = this.imageAnalysisFrameSubject.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        return observableHide;
    }
}
