package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetector;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.android.sdk.capture.utils.ThrowableExtensionsKt;
import com.onfido.android.sdk.capture.validation.device.BarcodeValidationResult;
import com.onfido.android.sdk.capture.validation.device.BlurValidationResult;
import com.onfido.android.sdk.capture.validation.device.EdgeDetectionValidationResult;
import com.onfido.android.sdk.capture.validation.device.FaceOnDocumentValidationResult;
import com.onfido.android.sdk.capture.validation.device.GlareValidationResult;
import com.onfido.android.sdk.capture.validation.device.MRZValidationResult;
import com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult;
import com.onfido.dagger.Lazy;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B9\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0012J5\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016J)\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0010¢\u0006\u0002\b J\u001c\u0010!\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationTransformer;", "", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "lazyMRZDetector", "Lcom/onfido/dagger/Lazy;", "Lcom/onfido/android/sdk/capture/detector/mrz/MRZDetector;", "lazyBarcodeDetector", "Lcom/onfido/android/sdk/capture/detector/barcode/BarcodeDetector;", "lazyFaceOnDocumentDetector", "Lcom/onfido/android/sdk/capture/detector/face/FaceOnDocumentDetector;", "(Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/dagger/Lazy;Lcom/onfido/dagger/Lazy;Lcom/onfido/dagger/Lazy;)V", "checkForMLKitDownloadError", "error", "", "onDeviceValidationType", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "getResults", "", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "validations", "", "results", "([Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;[Ljava/lang/Object;)Ljava/util/Map;", "stop", "", "transformPostCaptureValidations", "Lio/reactivex/rxjava3/core/Single;", "documentDetectionFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "validator", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "transformPostCaptureValidations$onfido_capture_sdk_core_release", "transformRealTimeValidations", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class OnDeviceValidationTransformer {
    private final Lazy<BarcodeDetector> lazyBarcodeDetector;
    private final Lazy<FaceOnDocumentDetector> lazyFaceOnDocumentDetector;
    private final Lazy<MRZDetector> lazyMRZDetector;
    private final NativeDetector nativeDetector;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OnDeviceValidationType.values().length];
            try {
                iArr[OnDeviceValidationType.BLUR_DETECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OnDeviceValidationType.MRZ_DETECTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[OnDeviceValidationType.PDF_417_BARCODE_DETECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[OnDeviceValidationType.FACE_ON_DOCUMENT_DETECTION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[OnDeviceValidationType.GLARE_DETECTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[OnDeviceValidationType.EDGES_DETECTION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public OnDeviceValidationTransformer(NativeDetector nativeDetector, Lazy<MRZDetector> lazyMRZDetector, Lazy<BarcodeDetector> lazyBarcodeDetector, Lazy<FaceOnDocumentDetector> lazyFaceOnDocumentDetector) {
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(lazyMRZDetector, "lazyMRZDetector");
        Intrinsics.checkNotNullParameter(lazyBarcodeDetector, "lazyBarcodeDetector");
        Intrinsics.checkNotNullParameter(lazyFaceOnDocumentDetector, "lazyFaceOnDocumentDetector");
        this.nativeDetector = nativeDetector;
        this.lazyMRZDetector = lazyMRZDetector;
        this.lazyBarcodeDetector = lazyBarcodeDetector;
        this.lazyFaceOnDocumentDetector = lazyFaceOnDocumentDetector;
    }

    private Object checkForMLKitDownloadError(Throwable error, OnDeviceValidationType onDeviceValidationType) {
        Timber.INSTANCE.e(error);
        if (ThrowableExtensionsKt.isMLKitModelUnavailableException(error)) {
            int i = WhenMappings.$EnumSwitchMapping$0[onDeviceValidationType.ordinal()];
            if (i == 2) {
                return Boolean.TRUE;
            }
            if (i == 3) {
                return new BarcodeValidationResult(null, false, 3, null);
            }
            if (i == 4) {
                return new FaceOnDocumentValidationResult(Boolean.TRUE, false);
            }
        }
        return Boolean.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SingleSource transformPostCaptureValidations$lambda$0(OnDeviceValidationType onDeviceValidationType, OnDeviceValidationTransformer this$0, DocumentDetectionFrame documentDetectionFrame, DocumentCodeValidator validator) {
        Single singleJust;
        String str;
        Intrinsics.checkNotNullParameter(onDeviceValidationType, "$onDeviceValidationType");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documentDetectionFrame, "$documentDetectionFrame");
        Intrinsics.checkNotNullParameter(validator, "$validator");
        int i = WhenMappings.$EnumSwitchMapping$0[onDeviceValidationType.ordinal()];
        if (i == 1) {
            singleJust = Single.just(Boolean.valueOf(this$0.nativeDetector.isBlurDetectedWithRotation(documentDetectionFrame)));
            str = "just(...)";
        } else {
            if (i == 2) {
                return this$0.lazyMRZDetector.get().detect(documentDetectionFrame, validator);
            }
            if (i == 3) {
                return this$0.lazyBarcodeDetector.get().detect(documentDetectionFrame, false);
            }
            if (i == 4) {
                return this$0.lazyFaceOnDocumentDetector.get().detect(documentDetectionFrame);
            }
            singleJust = Single.fromObservable(Observable.empty());
            str = "fromObservable(...)";
        }
        Intrinsics.checkNotNullExpressionValue(singleJust, str);
        return singleJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object transformPostCaptureValidations$lambda$1(OnDeviceValidationTransformer this$0, OnDeviceValidationType onDeviceValidationType, Throwable it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onDeviceValidationType, "$onDeviceValidationType");
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.checkForMLKitDownloadError(it, onDeviceValidationType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SingleSource transformRealTimeValidations$lambda$2(OnDeviceValidationType onDeviceValidationType, OnDeviceValidationTransformer this$0, DocumentDetectionFrame documentDetectionFrame) {
        boolean zIsBlurDetected;
        Object objValueOf;
        Single singleAsSingle;
        Intrinsics.checkNotNullParameter(onDeviceValidationType, "$onDeviceValidationType");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documentDetectionFrame, "$documentDetectionFrame");
        int i = WhenMappings.$EnumSwitchMapping$0[onDeviceValidationType.ordinal()];
        if (i == 1) {
            zIsBlurDetected = this$0.nativeDetector.isBlurDetected(documentDetectionFrame);
        } else {
            if (i == 3) {
                BarcodeDetector barcodeDetector = this$0.lazyBarcodeDetector.get();
                Intrinsics.checkNotNullExpressionValue(barcodeDetector, "get(...)");
                return BarcodeDetector.DefaultImpls.detect$default(barcodeDetector, documentDetectionFrame, false, 2, null);
            }
            if (i == 4) {
                return this$0.lazyFaceOnDocumentDetector.get().detect(documentDetectionFrame);
            }
            if (i != 5) {
                if (i != 6) {
                    objValueOf = Boolean.FALSE;
                } else {
                    EdgeDetectionResults edgeDetectionResultsDetectEdges = this$0.nativeDetector.detectEdges(documentDetectionFrame);
                    if (edgeDetectionResultsDetectEdges != null && (singleAsSingle = RxExtensionsKt.asSingle(edgeDetectionResultsDetectEdges)) != null) {
                        return singleAsSingle;
                    }
                    objValueOf = new EdgeDetectionResults(false, false, false, false, 15, null);
                }
                return RxExtensionsKt.asSingle(objValueOf);
            }
            zIsBlurDetected = this$0.nativeDetector.isGlareDetected(documentDetectionFrame);
        }
        objValueOf = Boolean.valueOf(zIsBlurDetected);
        return RxExtensionsKt.asSingle(objValueOf);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object transformRealTimeValidations$lambda$3(OnDeviceValidationTransformer this$0, OnDeviceValidationType onDeviceValidationType, Throwable it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onDeviceValidationType, "$onDeviceValidationType");
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.checkForMLKitDownloadError(it, onDeviceValidationType);
    }

    public Map<OnDeviceValidationType, OnDeviceValidationResult> getResults(OnDeviceValidationType[] validations, Object[] results) {
        OnDeviceValidationResult blurValidationResult;
        Intrinsics.checkNotNullParameter(validations, "validations");
        Intrinsics.checkNotNullParameter(results, "results");
        HashMap map = new HashMap();
        int length = validations.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            OnDeviceValidationType onDeviceValidationType = validations[i];
            int i3 = i2 + 1;
            Object obj = results[i2];
            switch (WhenMappings.$EnumSwitchMapping$0[onDeviceValidationType.ordinal()]) {
                case 1:
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
                    blurValidationResult = new BlurValidationResult(((Boolean) obj).booleanValue(), true);
                    break;
                case 2:
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
                    blurValidationResult = new MRZValidationResult(((Boolean) obj).booleanValue(), true);
                    break;
                case 3:
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.onfido.android.sdk.capture.validation.device.BarcodeValidationResult");
                    blurValidationResult = (BarcodeValidationResult) obj;
                    break;
                case 4:
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.onfido.android.sdk.capture.validation.device.FaceOnDocumentValidationResult");
                    blurValidationResult = (FaceOnDocumentValidationResult) obj;
                    break;
                case 5:
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
                    blurValidationResult = new GlareValidationResult(((Boolean) obj).booleanValue(), true);
                    break;
                case 6:
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults");
                    blurValidationResult = new EdgeDetectionValidationResult((EdgeDetectionResults) obj, true);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            map.put(onDeviceValidationType, blurValidationResult);
            i++;
            i2 = i3;
        }
        return map;
    }

    public void stop() {
        this.lazyFaceOnDocumentDetector.get().close();
    }

    public Single<?> transformPostCaptureValidations$onfido_capture_sdk_core_release(final DocumentDetectionFrame documentDetectionFrame, final OnDeviceValidationType onDeviceValidationType, final DocumentCodeValidator validator) {
        Intrinsics.checkNotNullParameter(documentDetectionFrame, "documentDetectionFrame");
        Intrinsics.checkNotNullParameter(onDeviceValidationType, "onDeviceValidationType");
        Intrinsics.checkNotNullParameter(validator, "validator");
        Single<?> singleOnErrorReturn = Single.defer(new Supplier() { // from class: com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return OnDeviceValidationTransformer.transformPostCaptureValidations$lambda$0(onDeviceValidationType, this, documentDetectionFrame, validator);
            }
        }).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return OnDeviceValidationTransformer.transformPostCaptureValidations$lambda$1(this.f$0, onDeviceValidationType, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleOnErrorReturn, "onErrorReturn(...)");
        return singleOnErrorReturn;
    }

    public Single<?> transformRealTimeValidations(final DocumentDetectionFrame documentDetectionFrame, final OnDeviceValidationType onDeviceValidationType) {
        Intrinsics.checkNotNullParameter(documentDetectionFrame, "documentDetectionFrame");
        Intrinsics.checkNotNullParameter(onDeviceValidationType, "onDeviceValidationType");
        Single<?> singleOnErrorReturn = Single.defer(new Supplier() { // from class: com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return OnDeviceValidationTransformer.transformRealTimeValidations$lambda$2(onDeviceValidationType, this, documentDetectionFrame);
            }
        }).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return OnDeviceValidationTransformer.transformRealTimeValidations$lambda$3(this.f$0, onDeviceValidationType, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleOnErrorReturn, "onErrorReturn(...)");
        return singleOnErrorReturn;
    }
}
