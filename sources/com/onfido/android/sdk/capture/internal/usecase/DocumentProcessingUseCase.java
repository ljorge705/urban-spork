package com.onfido.android.sdk.capture.internal.usecase;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer;
import com.onfido.android.sdk.capture.validation.OnDeviceValidationType;
import com.onfido.android.sdk.capture.validation.RealTimeDocumentValidationsManager;
import com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \"2\u00020\u0001:\u0001\"B?\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J3\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0000¢\u0006\u0002\b\u001aJ$\u0010\u001b\u001a\u00020\u00132\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010 \u001a\u00020!H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCase;", "", "nativeDetector", "Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;", "realTimeDocumentValidationsManager", "Lcom/onfido/android/sdk/capture/validation/RealTimeDocumentValidationsManager;", "onDeviceValidationTransformer", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationTransformer;", "retainableValidationsResult", "Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;", "barcodeValidationSuspender", "Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/native_detector/NativeDetector;Lcom/onfido/android/sdk/capture/validation/RealTimeDocumentValidationsManager;Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationTransformer;Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "execute", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCaseResult;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "docSide", "Lcom/onfido/api/client/data/DocSide;", "execute$onfido_capture_sdk_core_release", "getResults", "validationTypeToResultMap", "", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "cameraFrameData", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentProcessingUseCase {
    private static final Companion Companion = new Companion(null);
    private static final long SAMPLING_PERIOD_MS = 350;
    private final BarcodeValidationSuspender barcodeValidationSuspender;
    private final NativeDetector nativeDetector;
    private final OnDeviceValidationTransformer onDeviceValidationTransformer;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final RealTimeDocumentValidationsManager realTimeDocumentValidationsManager;
    private final RetainableValidationsResult retainableValidationsResult;
    private final SchedulersProvider schedulersProvider;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCase$Companion;", "", "()V", "SAMPLING_PERIOD_MS", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public DocumentProcessingUseCase(NativeDetector nativeDetector, RealTimeDocumentValidationsManager realTimeDocumentValidationsManager, OnDeviceValidationTransformer onDeviceValidationTransformer, RetainableValidationsResult retainableValidationsResult, BarcodeValidationSuspender barcodeValidationSuspender, SchedulersProvider schedulersProvider, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(nativeDetector, "nativeDetector");
        Intrinsics.checkNotNullParameter(realTimeDocumentValidationsManager, "realTimeDocumentValidationsManager");
        Intrinsics.checkNotNullParameter(onDeviceValidationTransformer, "onDeviceValidationTransformer");
        Intrinsics.checkNotNullParameter(retainableValidationsResult, "retainableValidationsResult");
        Intrinsics.checkNotNullParameter(barcodeValidationSuspender, "barcodeValidationSuspender");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.nativeDetector = nativeDetector;
        this.realTimeDocumentValidationsManager = realTimeDocumentValidationsManager;
        this.onDeviceValidationTransformer = onDeviceValidationTransformer;
        this.retainableValidationsResult = retainableValidationsResult;
        this.barcodeValidationSuspender = barcodeValidationSuspender;
        this.schedulersProvider = schedulersProvider;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public static /* synthetic */ Observable execute$onfido_capture_sdk_core_release$default(DocumentProcessingUseCase documentProcessingUseCase, DocumentType documentType, CountryCode countryCode, DocSide docSide, int i, Object obj) {
        if ((i & 2) != 0) {
            countryCode = null;
        }
        if ((i & 4) != 0) {
            docSide = null;
        }
        return documentProcessingUseCase.execute$onfido_capture_sdk_core_release(documentType, countryCode, docSide);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DocumentProcessingUseCaseResult getResults(Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> validationTypeToResultMap, DocumentDetectionFrame cameraFrameData) {
        Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> mutableMap = MapsKt.toMutableMap(validationTypeToResultMap);
        for (Map.Entry<OnDeviceValidationType, OnDeviceValidationResult> entry : this.retainableValidationsResult.getRetainedValidationResults$onfido_capture_sdk_core_release().entrySet()) {
            mutableMap.put(entry.getKey(), entry.getValue());
        }
        return new DocumentProcessingUseCaseResult(DocumentProcessingResults.INSTANCE.mapFromValidationTypeToResult(mutableMap), cameraFrameData);
    }

    public final Observable<DocumentProcessingUseCaseResult> execute$onfido_capture_sdk_core_release(DocumentType documentType, CountryCode countryCode, DocSide docSide) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        final OnDeviceValidationType[] requiredValidations = this.realTimeDocumentValidationsManager.getRequiredValidations(documentType, countryCode, docSide);
        Observable observableDoOnNext = this.nativeDetector.getFrameData().sample(350L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase$execute$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends Pair<Map<OnDeviceValidationType, OnDeviceValidationResult>, DocumentDetectionFrame>> apply(final DocumentDetectionFrame documentDetectionFrame) {
                OnDeviceValidationType[] onDeviceValidationTypeArr = requiredValidations;
                RetainableValidationsResult retainableValidationsResult = this.retainableValidationsResult;
                ArrayList arrayList = new ArrayList();
                for (OnDeviceValidationType onDeviceValidationType : onDeviceValidationTypeArr) {
                    if (!retainableValidationsResult.wasValidationRetained$onfido_capture_sdk_core_release(onDeviceValidationType)) {
                        arrayList.add(onDeviceValidationType);
                    }
                }
                BarcodeValidationSuspender barcodeValidationSuspender = this.barcodeValidationSuspender;
                ArrayList arrayList2 = new ArrayList();
                for (T t : arrayList) {
                    if (!barcodeValidationSuspender.isValidationSuspended$onfido_capture_sdk_core_release((OnDeviceValidationType) t)) {
                        arrayList2.add(t);
                    }
                }
                DocumentProcessingUseCase documentProcessingUseCase = this;
                ArrayList arrayList3 = new ArrayList();
                for (T t2 : arrayList2) {
                    OnDeviceValidationType onDeviceValidationType2 = (OnDeviceValidationType) t2;
                    if (documentProcessingUseCase.onfidoRemoteConfig.getDocumentCapture().isMrzDetectionEnabled() || onDeviceValidationType2 != OnDeviceValidationType.MRZ_DETECTION) {
                        arrayList3.add(t2);
                    }
                }
                final OnDeviceValidationType[] onDeviceValidationTypeArr2 = (OnDeviceValidationType[]) arrayList3.toArray(new OnDeviceValidationType[0]);
                DocumentProcessingUseCase documentProcessingUseCase2 = this;
                ArrayList arrayList4 = new ArrayList(onDeviceValidationTypeArr2.length);
                for (OnDeviceValidationType onDeviceValidationType3 : onDeviceValidationTypeArr2) {
                    OnDeviceValidationTransformer onDeviceValidationTransformer = documentProcessingUseCase2.onDeviceValidationTransformer;
                    Intrinsics.checkNotNull(documentDetectionFrame);
                    arrayList4.add(onDeviceValidationTransformer.transformRealTimeValidations(documentDetectionFrame, onDeviceValidationType3));
                }
                final DocumentProcessingUseCase documentProcessingUseCase3 = this;
                return Single.zip(arrayList4, new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase$execute$1.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Pair<Map<OnDeviceValidationType, OnDeviceValidationResult>, DocumentDetectionFrame> apply(Object[] validationResults) {
                        Intrinsics.checkNotNullParameter(validationResults, "validationResults");
                        return TuplesKt.to(documentProcessingUseCase3.onDeviceValidationTransformer.getResults(onDeviceValidationTypeArr2, validationResults), documentDetectionFrame);
                    }
                }).toObservable();
            }
        }).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase$execute$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Pair<? extends Map<OnDeviceValidationType, ? extends OnDeviceValidationResult>, DocumentDetectionFrame> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                this.this$0.retainableValidationsResult.retainValidValidationResult$onfido_capture_sdk_core_release(pair.component1());
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase$execute$3
            @Override // io.reactivex.rxjava3.functions.Function
            public final DocumentProcessingUseCaseResult apply(Pair<? extends Map<OnDeviceValidationType, ? extends OnDeviceValidationResult>, DocumentDetectionFrame> pair) {
                Intrinsics.checkNotNullParameter(pair, "<name for destructuring parameter 0>");
                Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> mapComponent1 = pair.component1();
                DocumentDetectionFrame documentDetectionFrameComponent2 = pair.component2();
                DocumentProcessingUseCase documentProcessingUseCase = this.this$0;
                Intrinsics.checkNotNull(documentDetectionFrameComponent2);
                return documentProcessingUseCase.getResults(mapComponent1, documentDetectionFrameComponent2);
            }
        }).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase$execute$4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(DocumentProcessingUseCaseResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.barcodeValidationSuspender.onValidationResult$onfido_capture_sdk_core_release(it.getDocumentProcessingResults());
            }
        });
        final BarcodeValidationSuspender barcodeValidationSuspender = this.barcodeValidationSuspender;
        Observable<DocumentProcessingUseCaseResult> observableDoOnDispose = observableDoOnNext.doOnDispose(new Action() { // from class: com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                barcodeValidationSuspender.stopTimer$onfido_capture_sdk_core_release();
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableDoOnDispose, "doOnDispose(...)");
        return observableDoOnDispose;
    }
}
