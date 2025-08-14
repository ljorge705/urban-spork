package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.tfmodel;

import com.clevertap.android.sdk.Constants;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.onfido.android.sdk.capture.internal.util.OnfidoPointF;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013¨\u0006)"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/tfmodel/Detection;", "", OptionalModuleUtils.FACE, "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "leftEye", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;", "rightEye", "nose", "mouth", "leftEar", "rightEar", "confidence", "", "(Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;F)V", "getConfidence", "()F", "getFace", "()Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "getLeftEar", "()Lcom/onfido/android/sdk/capture/internal/util/OnfidoPointF;", "getLeftEye", "getMouth", "getNose", "getRightEar", "getRightEye", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Detection {
    private final float confidence;
    private final OnfidoRectF face;
    private final OnfidoPointF leftEar;
    private final OnfidoPointF leftEye;
    private final OnfidoPointF mouth;
    private final OnfidoPointF nose;
    private final OnfidoPointF rightEar;
    private final OnfidoPointF rightEye;

    public Detection(OnfidoRectF face, OnfidoPointF leftEye, OnfidoPointF rightEye, OnfidoPointF nose, OnfidoPointF mouth, OnfidoPointF leftEar, OnfidoPointF rightEar, float f) {
        Intrinsics.checkNotNullParameter(face, "face");
        Intrinsics.checkNotNullParameter(leftEye, "leftEye");
        Intrinsics.checkNotNullParameter(rightEye, "rightEye");
        Intrinsics.checkNotNullParameter(nose, "nose");
        Intrinsics.checkNotNullParameter(mouth, "mouth");
        Intrinsics.checkNotNullParameter(leftEar, "leftEar");
        Intrinsics.checkNotNullParameter(rightEar, "rightEar");
        this.face = face;
        this.leftEye = leftEye;
        this.rightEye = rightEye;
        this.nose = nose;
        this.mouth = mouth;
        this.leftEar = leftEar;
        this.rightEar = rightEar;
        this.confidence = f;
    }

    /* renamed from: component1, reason: from getter */
    public final OnfidoRectF getFace() {
        return this.face;
    }

    /* renamed from: component2, reason: from getter */
    public final OnfidoPointF getLeftEye() {
        return this.leftEye;
    }

    /* renamed from: component3, reason: from getter */
    public final OnfidoPointF getRightEye() {
        return this.rightEye;
    }

    /* renamed from: component4, reason: from getter */
    public final OnfidoPointF getNose() {
        return this.nose;
    }

    /* renamed from: component5, reason: from getter */
    public final OnfidoPointF getMouth() {
        return this.mouth;
    }

    /* renamed from: component6, reason: from getter */
    public final OnfidoPointF getLeftEar() {
        return this.leftEar;
    }

    /* renamed from: component7, reason: from getter */
    public final OnfidoPointF getRightEar() {
        return this.rightEar;
    }

    /* renamed from: component8, reason: from getter */
    public final float getConfidence() {
        return this.confidence;
    }

    public final Detection copy(OnfidoRectF face, OnfidoPointF leftEye, OnfidoPointF rightEye, OnfidoPointF nose, OnfidoPointF mouth, OnfidoPointF leftEar, OnfidoPointF rightEar, float confidence) {
        Intrinsics.checkNotNullParameter(face, "face");
        Intrinsics.checkNotNullParameter(leftEye, "leftEye");
        Intrinsics.checkNotNullParameter(rightEye, "rightEye");
        Intrinsics.checkNotNullParameter(nose, "nose");
        Intrinsics.checkNotNullParameter(mouth, "mouth");
        Intrinsics.checkNotNullParameter(leftEar, "leftEar");
        Intrinsics.checkNotNullParameter(rightEar, "rightEar");
        return new Detection(face, leftEye, rightEye, nose, mouth, leftEar, rightEar, confidence);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Detection)) {
            return false;
        }
        Detection detection = (Detection) other;
        return Intrinsics.areEqual(this.face, detection.face) && Intrinsics.areEqual(this.leftEye, detection.leftEye) && Intrinsics.areEqual(this.rightEye, detection.rightEye) && Intrinsics.areEqual(this.nose, detection.nose) && Intrinsics.areEqual(this.mouth, detection.mouth) && Intrinsics.areEqual(this.leftEar, detection.leftEar) && Intrinsics.areEqual(this.rightEar, detection.rightEar) && Float.compare(this.confidence, detection.confidence) == 0;
    }

    public final float getConfidence() {
        return this.confidence;
    }

    public final OnfidoRectF getFace() {
        return this.face;
    }

    public final OnfidoPointF getLeftEar() {
        return this.leftEar;
    }

    public final OnfidoPointF getLeftEye() {
        return this.leftEye;
    }

    public final OnfidoPointF getMouth() {
        return this.mouth;
    }

    public final OnfidoPointF getNose() {
        return this.nose;
    }

    public final OnfidoPointF getRightEar() {
        return this.rightEar;
    }

    public final OnfidoPointF getRightEye() {
        return this.rightEye;
    }

    public int hashCode() {
        return (((((((((((((this.face.hashCode() * 31) + this.leftEye.hashCode()) * 31) + this.rightEye.hashCode()) * 31) + this.nose.hashCode()) * 31) + this.mouth.hashCode()) * 31) + this.leftEar.hashCode()) * 31) + this.rightEar.hashCode()) * 31) + Float.hashCode(this.confidence);
    }

    public String toString() {
        return "Detection(face=" + this.face + ", leftEye=" + this.leftEye + ", rightEye=" + this.rightEye + ", nose=" + this.nose + ", mouth=" + this.mouth + ", leftEar=" + this.leftEar + ", rightEar=" + this.rightEar + ", confidence=" + this.confidence + ')';
    }
}
