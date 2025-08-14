package com.onfido.android.sdk.capture.detector.barcode;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.detector.helper.DocumentDetectionExtensionKt;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.validation.device.BarcodeValidationResult;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Provider;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000  2\u00020\u0001:\u0001 B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019H\u0012J\u001a\u0010\u001a\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u000f0\u00192\u0006\u0010\u001b\u001a\u00020\u000fH\u0012J\u0012\u0010\u001c\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0012J\u0014\u0010\u001f\u001a\u00020\u0017*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0012R\u001b\u0010\b\u001a\u00020\u00068RX\u0092\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/barcode/BarcodeDetectorGoogle;", "Lcom/onfido/android/sdk/capture/detector/barcode/BarcodeDetector;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "barcodeDetectorProvider", "Lcom/onfido/javax/inject/Provider;", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "(Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/javax/inject/Provider;)V", "barcodeDetector", "getBarcodeDetector", "()Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "barcodeDetector$delegate", "Lkotlin/Lazy;", "detect", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/validation/device/BarcodeValidationResult;", "documentFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "isRealtimeAnalysis", "", "processImage", "", "firebaseImage", "Lcom/google/mlkit/vision/common/InputImage;", "emitter", "Lio/reactivex/rxjava3/core/SingleEmitter;", "onSuccessIfNotDisposed", OnfidoLauncher.KEY_RESULT, "toBarcodeResult", "", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "toInputImage", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class BarcodeDetectorGoogle implements BarcodeDetector {
    private static final BarcodeValidationResult OMITTED_FRAME_ANALYSE_RESULT = new BarcodeValidationResult(null, true, 1, null);

    /* renamed from: barcodeDetector$delegate, reason: from kotlin metadata */
    private final Lazy barcodeDetector;
    private final Provider<BarcodeScanner> barcodeDetectorProvider;
    private final OnfidoRemoteConfig remoteConfig;

    @Inject
    public BarcodeDetectorGoogle(OnfidoRemoteConfig remoteConfig, Provider<BarcodeScanner> barcodeDetectorProvider) {
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        Intrinsics.checkNotNullParameter(barcodeDetectorProvider, "barcodeDetectorProvider");
        this.remoteConfig = remoteConfig;
        this.barcodeDetectorProvider = barcodeDetectorProvider;
        this.barcodeDetector = LazyKt.lazy(new Function0<BarcodeScanner>() { // from class: com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle$barcodeDetector$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BarcodeScanner invoke() {
                return (BarcodeScanner) this.this$0.barcodeDetectorProvider.get();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void detect$lambda$0(BarcodeDetectorGoogle this$0, DocumentDetectionFrame documentFrame, boolean z, SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(documentFrame, "$documentFrame");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        try {
            this$0.processImage(this$0.toInputImage(documentFrame, z), emitter);
        } catch (Exception unused) {
            this$0.onSuccessIfNotDisposed(emitter, OMITTED_FRAME_ANALYSE_RESULT);
        }
    }

    private BarcodeScanner getBarcodeDetector() {
        Object value = this.barcodeDetector.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (BarcodeScanner) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSuccessIfNotDisposed(SingleEmitter<BarcodeValidationResult> singleEmitter, BarcodeValidationResult barcodeValidationResult) {
        if (singleEmitter.isDisposed()) {
            return;
        }
        singleEmitter.onSuccess(barcodeValidationResult);
    }

    private void processImage(InputImage firebaseImage, final SingleEmitter<BarcodeValidationResult> emitter) {
        Task<List<Barcode>> taskProcess = getBarcodeDetector().process(firebaseImage);
        final Function1<List<Barcode>, Unit> function1 = new Function1<List<Barcode>, Unit>() { // from class: com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle.processImage.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<Barcode> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<Barcode> barcodeVisionList) {
                Intrinsics.checkNotNullParameter(barcodeVisionList, "barcodeVisionList");
                BarcodeDetectorGoogle.this.onSuccessIfNotDisposed(emitter, BarcodeDetectorGoogle.this.toBarcodeResult(barcodeVisionList));
            }
        };
        taskProcess.addOnSuccessListener(new OnSuccessListener() { // from class: com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                BarcodeDetectorGoogle.processImage$lambda$1(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                BarcodeDetectorGoogle.processImage$lambda$2(this.f$0, emitter, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processImage$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processImage$lambda$2(BarcodeDetectorGoogle this$0, SingleEmitter emitter, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "$emitter");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.onSuccessIfNotDisposed(emitter, OMITTED_FRAME_ANALYSE_RESULT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BarcodeValidationResult toBarcodeResult(List<? extends Barcode> list) {
        StringBuilder sb = new StringBuilder();
        if (!list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                String rawValue = ((Barcode) it.next()).getRawValue();
                if (rawValue != null) {
                    sb.append(rawValue);
                }
            }
        }
        return StringsKt.isBlank(sb) ^ true ? new BarcodeValidationResult(sb.toString(), true) : OMITTED_FRAME_ANALYSE_RESULT;
    }

    private InputImage toInputImage(DocumentDetectionFrame documentDetectionFrame, boolean z) {
        return z ? DocumentDetectionExtensionKt.toInputImageFromYuv(documentDetectionFrame) : DocumentDetectionExtensionKt.toInputImageFromJpeg$default(documentDetectionFrame, 0, this.remoteConfig.getDocumentPostCaptureValidationTargetWidth(), 1, null);
    }

    @Override // com.onfido.android.sdk.capture.detector.barcode.BarcodeDetector
    public synchronized Single<BarcodeValidationResult> detect(final DocumentDetectionFrame documentFrame, final boolean isRealtimeAnalysis) {
        Single<BarcodeValidationResult> singleCreate;
        Intrinsics.checkNotNullParameter(documentFrame, "documentFrame");
        singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                BarcodeDetectorGoogle.detect$lambda$0(this.f$0, documentFrame, isRealtimeAnalysis, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }
}
