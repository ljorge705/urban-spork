package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", "", "()V", "Error", "FaceDetected", "FaceNotDetected", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$FaceDetected;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$FaceNotDetected;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class FaceDetectorResult {

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\r\u0010\r\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", "message", "", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/String;Ljava/lang/Exception;)V", "getEx", "()Ljava/lang/Exception;", "getMessage", "()Ljava/lang/String;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Error extends FaceDetectorResult {
        private final Exception ex;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(String message, Exception ex) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(ex, "ex");
            this.message = message;
            this.ex = ex;
        }

        public static /* synthetic */ Error copy$default(Error error, String str, Exception exc, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.message;
            }
            if ((i & 2) != 0) {
                exc = error.ex;
            }
            return error.copy(str, exc);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* renamed from: component2, reason: from getter */
        public final Exception getEx() {
            return this.ex;
        }

        public final Error copy(String message, Exception ex) {
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(ex, "ex");
            return new Error(message, ex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return Intrinsics.areEqual(this.message, error.message) && Intrinsics.areEqual(this.ex, error.ex);
        }

        public final Exception getEx() {
            return this.ex;
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return (this.message.hashCode() * 31) + this.ex.hashCode();
        }

        public String toString() {
            return "Error(message=" + this.message + ", ex=" + this.ex + ')';
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$FaceDetected;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", "faceRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "faceAngle", "", "faceLandmarks", "", "(Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;FLjava/util/List;)V", "getFaceAngle", "()F", "getFaceLandmarks", "()Ljava/util/List;", "getFaceRect", "()Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceDetected extends FaceDetectorResult {
        private final float faceAngle;
        private final List<OnfidoRectF> faceLandmarks;
        private final OnfidoRectF faceRect;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaceDetected(OnfidoRectF faceRect, float f, List<OnfidoRectF> faceLandmarks) {
            super(null);
            Intrinsics.checkNotNullParameter(faceRect, "faceRect");
            Intrinsics.checkNotNullParameter(faceLandmarks, "faceLandmarks");
            this.faceRect = faceRect;
            this.faceAngle = f;
            this.faceLandmarks = faceLandmarks;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FaceDetected copy$default(FaceDetected faceDetected, OnfidoRectF onfidoRectF, float f, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                onfidoRectF = faceDetected.faceRect;
            }
            if ((i & 2) != 0) {
                f = faceDetected.faceAngle;
            }
            if ((i & 4) != 0) {
                list = faceDetected.faceLandmarks;
            }
            return faceDetected.copy(onfidoRectF, f, list);
        }

        /* renamed from: component1, reason: from getter */
        public final OnfidoRectF getFaceRect() {
            return this.faceRect;
        }

        /* renamed from: component2, reason: from getter */
        public final float getFaceAngle() {
            return this.faceAngle;
        }

        public final List<OnfidoRectF> component3() {
            return this.faceLandmarks;
        }

        public final FaceDetected copy(OnfidoRectF faceRect, float faceAngle, List<OnfidoRectF> faceLandmarks) {
            Intrinsics.checkNotNullParameter(faceRect, "faceRect");
            Intrinsics.checkNotNullParameter(faceLandmarks, "faceLandmarks");
            return new FaceDetected(faceRect, faceAngle, faceLandmarks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceDetected)) {
                return false;
            }
            FaceDetected faceDetected = (FaceDetected) other;
            return Intrinsics.areEqual(this.faceRect, faceDetected.faceRect) && Float.compare(this.faceAngle, faceDetected.faceAngle) == 0 && Intrinsics.areEqual(this.faceLandmarks, faceDetected.faceLandmarks);
        }

        public final float getFaceAngle() {
            return this.faceAngle;
        }

        public final List<OnfidoRectF> getFaceLandmarks() {
            return this.faceLandmarks;
        }

        public final OnfidoRectF getFaceRect() {
            return this.faceRect;
        }

        public int hashCode() {
            return (((this.faceRect.hashCode() * 31) + Float.hashCode(this.faceAngle)) * 31) + this.faceLandmarks.hashCode();
        }

        public String toString() {
            return "FaceDetected(faceRect=" + this.faceRect + ", faceAngle=" + this.faceAngle + ", faceLandmarks=" + this.faceLandmarks + ')';
        }

        public /* synthetic */ FaceDetected(OnfidoRectF onfidoRectF, float f, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(onfidoRectF, f, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$FaceNotDetected;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class FaceNotDetected extends FaceDetectorResult {
        public static final FaceNotDetected INSTANCE = new FaceNotDetected();

        private FaceNotDetected() {
            super(null);
        }
    }

    private FaceDetectorResult() {
    }

    public /* synthetic */ FaceDetectorResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
