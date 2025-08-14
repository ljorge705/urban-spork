package com.onfido.android.sdk.capture.detector.mrz;

import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.validation.DocumentCodeValidator;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetectorEmpty;", "Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetector;", "()V", "detect", "Lio/reactivex/rxjava3/core/Single;", "", "frame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "validator", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRZDetectorEmpty implements MRZDetector {
    @Inject
    public MRZDetectorEmpty() {
    }

    @Override // com.onfido.android.sdk.capture.detector.mrz.MRZDetector
    public Single<Boolean> detect(DocumentDetectionFrame frame, DocumentCodeValidator validator) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(validator, "validator");
        Single<Boolean> singleJust = Single.just(Boolean.TRUE);
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(...)");
        return singleJust;
    }
}
