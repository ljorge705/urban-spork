package com.onfido.android.sdk.capture.detector.face;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "", "()V", "Error", "FaceDetected", "NoFaceDetected", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult$Error;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult$FaceDetected;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult$NoFaceDetected;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class FaceDetectionResult {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult$Error;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Error extends FaceDetectionResult {
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public Error() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.message;
            }
            return error.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Error copy(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new Error(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Error) && Intrinsics.areEqual(this.message, ((Error) other).message);
        }

        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            return "Error(message=" + this.message + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(String message) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }

        public /* synthetic */ Error(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str);
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult$FaceDetected;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "faceRect", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect;", "faceAngle", "", "frameRect", "cropRect", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "(Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect;FLcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect;Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;)V", "getCropRect", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "getFaceAngle", "()F", "getFaceRect", "()Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionRect;", "getFrameRect", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class FaceDetected extends FaceDetectionResult {
        private final OnfidoImage.CropRect cropRect;
        private final float faceAngle;
        private final FaceDetectionRect faceRect;
        private final FaceDetectionRect frameRect;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FaceDetected(FaceDetectionRect faceRect, float f, FaceDetectionRect frameRect, OnfidoImage.CropRect cropRect) {
            super(null);
            Intrinsics.checkNotNullParameter(faceRect, "faceRect");
            Intrinsics.checkNotNullParameter(frameRect, "frameRect");
            Intrinsics.checkNotNullParameter(cropRect, "cropRect");
            this.faceRect = faceRect;
            this.faceAngle = f;
            this.frameRect = frameRect;
            this.cropRect = cropRect;
        }

        public static /* synthetic */ FaceDetected copy$default(FaceDetected faceDetected, FaceDetectionRect faceDetectionRect, float f, FaceDetectionRect faceDetectionRect2, OnfidoImage.CropRect cropRect, int i, Object obj) {
            if ((i & 1) != 0) {
                faceDetectionRect = faceDetected.faceRect;
            }
            if ((i & 2) != 0) {
                f = faceDetected.faceAngle;
            }
            if ((i & 4) != 0) {
                faceDetectionRect2 = faceDetected.frameRect;
            }
            if ((i & 8) != 0) {
                cropRect = faceDetected.cropRect;
            }
            return faceDetected.copy(faceDetectionRect, f, faceDetectionRect2, cropRect);
        }

        /* renamed from: component1, reason: from getter */
        public final FaceDetectionRect getFaceRect() {
            return this.faceRect;
        }

        /* renamed from: component2, reason: from getter */
        public final float getFaceAngle() {
            return this.faceAngle;
        }

        /* renamed from: component3, reason: from getter */
        public final FaceDetectionRect getFrameRect() {
            return this.frameRect;
        }

        /* renamed from: component4, reason: from getter */
        public final OnfidoImage.CropRect getCropRect() {
            return this.cropRect;
        }

        public final FaceDetected copy(FaceDetectionRect faceRect, float faceAngle, FaceDetectionRect frameRect, OnfidoImage.CropRect cropRect) {
            Intrinsics.checkNotNullParameter(faceRect, "faceRect");
            Intrinsics.checkNotNullParameter(frameRect, "frameRect");
            Intrinsics.checkNotNullParameter(cropRect, "cropRect");
            return new FaceDetected(faceRect, faceAngle, frameRect, cropRect);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FaceDetected)) {
                return false;
            }
            FaceDetected faceDetected = (FaceDetected) other;
            return Intrinsics.areEqual(this.faceRect, faceDetected.faceRect) && Float.compare(this.faceAngle, faceDetected.faceAngle) == 0 && Intrinsics.areEqual(this.frameRect, faceDetected.frameRect) && Intrinsics.areEqual(this.cropRect, faceDetected.cropRect);
        }

        public final OnfidoImage.CropRect getCropRect() {
            return this.cropRect;
        }

        public final float getFaceAngle() {
            return this.faceAngle;
        }

        public final FaceDetectionRect getFaceRect() {
            return this.faceRect;
        }

        public final FaceDetectionRect getFrameRect() {
            return this.frameRect;
        }

        public int hashCode() {
            return (((((this.faceRect.hashCode() * 31) + Float.hashCode(this.faceAngle)) * 31) + this.frameRect.hashCode()) * 31) + this.cropRect.hashCode();
        }

        public String toString() {
            return "FaceDetected(faceRect=" + this.faceRect + ", faceAngle=" + this.faceAngle + ", frameRect=" + this.frameRect + ", cropRect=" + this.cropRect + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult$NoFaceDetected;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class NoFaceDetected extends FaceDetectionResult {
        public static final NoFaceDetected INSTANCE = new NoFaceDetected();

        private NoFaceDetected() {
            super(null);
        }
    }

    private FaceDetectionResult() {
    }

    public /* synthetic */ FaceDetectionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
