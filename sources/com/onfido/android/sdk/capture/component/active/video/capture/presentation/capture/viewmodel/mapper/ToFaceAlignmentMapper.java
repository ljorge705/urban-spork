package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.model.FaceAlignment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.FaceAlignmentChecks;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/mapper/ToFaceAlignmentMapper;", "", "faceAlignmentChecks", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/FaceAlignmentChecks;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/FaceAlignmentChecks;)V", "map", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment;", "ovalRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "faceRect", "faceAngle", "", "isRecording", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ToFaceAlignmentMapper {
    private final FaceAlignmentChecks faceAlignmentChecks;

    @Inject
    public ToFaceAlignmentMapper(FaceAlignmentChecks faceAlignmentChecks) {
        Intrinsics.checkNotNullParameter(faceAlignmentChecks, "faceAlignmentChecks");
        this.faceAlignmentChecks = faceAlignmentChecks;
    }

    public final FaceAlignment map(OnfidoRectF ovalRect, OnfidoRectF faceRect, float faceAngle, boolean isRecording) {
        Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
        Intrinsics.checkNotNullParameter(faceRect, "faceRect");
        FaceAlignmentChecks.CenterAlignmentDelta centerAlignmentDelta = new FaceAlignmentChecks.CenterAlignmentDelta(ovalRect, faceRect, faceAngle, isRecording);
        return this.faceAlignmentChecks.isFaceCenterAligned(centerAlignmentDelta) ? this.faceAlignmentChecks.isFaceTooSmall(ovalRect, faceRect, isRecording) ? FaceAlignment.FaceCenterIsAligned.FaceIsTooSmall.INSTANCE : this.faceAlignmentChecks.isFaceTooBig(ovalRect, faceRect, isRecording) ? FaceAlignment.FaceCenterIsAligned.FaceIsTooBig.INSTANCE : FaceAlignment.FaceCenterIsAligned.FaceIsAligned.INSTANCE : this.faceAlignmentChecks.isFaceTooLeft(centerAlignmentDelta) ? FaceAlignment.FaceCenterIsNotAligned.FaceIsTooLeft.INSTANCE : this.faceAlignmentChecks.isFaceTooRight(centerAlignmentDelta) ? FaceAlignment.FaceCenterIsNotAligned.FaceIsTooRight.INSTANCE : this.faceAlignmentChecks.isFaceTooUp(centerAlignmentDelta) ? FaceAlignment.FaceCenterIsNotAligned.FaceIsTooUp.INSTANCE : FaceAlignment.FaceCenterIsNotAligned.FaceIsTooDown.INSTANCE;
    }
}
