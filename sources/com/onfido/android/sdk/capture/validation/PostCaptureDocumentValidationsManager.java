package com.onfido.android.sdk.capture.validation;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.detector.mrz.DutchIDMRZValidator;
import com.onfido.android.sdk.capture.detector.mrz.MRZValidator;
import com.onfido.android.sdk.capture.detector.mrz.PassportMRZValidator;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult;
import com.onfido.api.client.data.DocSide;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B/\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ-\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0012¢\u0006\u0002\u0010\u0016J,\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0019\u001a\u00020\u000fH\u0012J<\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001c0\u001bH\u0012J5\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010$\u001a\u00020\u0015H\u0010¢\u0006\u0002\b%R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/validation/PostCaptureDocumentValidationsManager;", "", "identityInteractor", "Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "onDeviceValidationTransformer", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationTransformer;", "retainableValidationsResult", "Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;", "barcodeValidationSuspender", "Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationTransformer;Lcom/onfido/android/sdk/capture/internal/validation/RetainableValidationsResult;Lcom/onfido/android/sdk/capture/validation/BarcodeValidationSuspender;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "getRequiredValidations", "", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/android/sdk/capture/utils/CountryCode;Lcom/onfido/api/client/data/DocSide;)[Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "getValidator", "Lcom/onfido/android/sdk/capture/validation/DocumentCodeValidator;", "validationType", "replaceValidationResultsByRetainedResults", "", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "actualValidations", "retainedValidationResults", "validate", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "frame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "docSide", "validate$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class PostCaptureDocumentValidationsManager {
    private final BarcodeValidationSuspender barcodeValidationSuspender;
    private final IdentityInteractor identityInteractor;
    private final OnDeviceValidationTransformer onDeviceValidationTransformer;
    private final OnfidoRemoteConfig onfidoRemoteConfig;
    private final RetainableValidationsResult retainableValidationsResult;

    @Inject
    public PostCaptureDocumentValidationsManager(IdentityInteractor identityInteractor, OnDeviceValidationTransformer onDeviceValidationTransformer, RetainableValidationsResult retainableValidationsResult, BarcodeValidationSuspender barcodeValidationSuspender, OnfidoRemoteConfig onfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        Intrinsics.checkNotNullParameter(onDeviceValidationTransformer, "onDeviceValidationTransformer");
        Intrinsics.checkNotNullParameter(retainableValidationsResult, "retainableValidationsResult");
        Intrinsics.checkNotNullParameter(barcodeValidationSuspender, "barcodeValidationSuspender");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        this.identityInteractor = identityInteractor;
        this.onDeviceValidationTransformer = onDeviceValidationTransformer;
        this.retainableValidationsResult = retainableValidationsResult;
        this.barcodeValidationSuspender = barcodeValidationSuspender;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    private OnDeviceValidationType[] getRequiredValidations(DocumentType documentType, CountryCode countryCode, DocSide documentSide) {
        DocumentType documentType2 = DocumentType.PASSPORT;
        return documentType == documentType2 && this.identityInteractor.isDeviceHighEnd() ? new OnDeviceValidationType[]{OnDeviceValidationType.BLUR_DETECTION, OnDeviceValidationType.MRZ_DETECTION, OnDeviceValidationType.FACE_ON_DOCUMENT_DETECTION} : documentType == documentType2 ? new OnDeviceValidationType[]{OnDeviceValidationType.BLUR_DETECTION, OnDeviceValidationType.FACE_ON_DOCUMENT_DETECTION} : documentType == DocumentType.GENERIC ? new OnDeviceValidationType[]{OnDeviceValidationType.BLUR_DETECTION} : documentSide == DocSide.FRONT ? new OnDeviceValidationType[]{OnDeviceValidationType.BLUR_DETECTION, OnDeviceValidationType.FACE_ON_DOCUMENT_DETECTION} : documentType == DocumentType.DRIVING_LICENCE && countryCode == CountryCode.US && documentSide == DocSide.BACK ? new OnDeviceValidationType[]{OnDeviceValidationType.PDF_417_BARCODE_DETECTION} : documentType == DocumentType.NATIONAL_IDENTITY_CARD && countryCode == CountryCode.NL && documentSide == DocSide.BACK && this.identityInteractor.isDeviceHighEnd() ? new OnDeviceValidationType[]{OnDeviceValidationType.BLUR_DETECTION, OnDeviceValidationType.MRZ_DETECTION} : new OnDeviceValidationType[]{OnDeviceValidationType.BLUR_DETECTION};
    }

    private DocumentCodeValidator getValidator(DocumentType documentType, CountryCode countryCode, DocSide documentSide, OnDeviceValidationType validationType) {
        if (validationType == OnDeviceValidationType.MRZ_DETECTION) {
            boolean z = documentType == DocumentType.PASSPORT;
            boolean z2 = documentType == DocumentType.NATIONAL_IDENTITY_CARD && countryCode == CountryCode.NL && documentSide == DocSide.BACK;
            if ((z || z2) && this.onfidoRemoteConfig.isGenericMrzValidatorEnabled()) {
                return new MRZValidator(documentType);
            }
            if (z) {
                return new PassportMRZValidator();
            }
            if (z2) {
                return new DutchIDMRZValidator();
            }
        }
        return DocumentCodeValidator.INSTANCE.getNone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<OnDeviceValidationType, OnDeviceValidationResult> replaceValidationResultsByRetainedResults(Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> actualValidations, Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> retainedValidationResults) {
        Map<OnDeviceValidationType, OnDeviceValidationResult> mutableMap = MapsKt.toMutableMap(actualValidations);
        for (Map.Entry<OnDeviceValidationType, ? extends OnDeviceValidationResult> entry : retainedValidationResults.entrySet()) {
            mutableMap.put(entry.getKey(), entry.getValue());
        }
        return mutableMap;
    }

    public Single<DocumentProcessingResults> validate$onfido_capture_sdk_core_release(DocumentDetectionFrame frame, DocumentType documentType, CountryCode countryCode, DocSide docSide) {
        Single<DocumentProcessingResults> map;
        String str;
        Intrinsics.checkNotNullParameter(frame, "frame");
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        OnDeviceValidationType[] requiredValidations = getRequiredValidations(documentType, countryCode, docSide);
        BarcodeValidationSuspender barcodeValidationSuspender = this.barcodeValidationSuspender;
        ArrayList arrayList = new ArrayList();
        for (OnDeviceValidationType onDeviceValidationType : requiredValidations) {
            if (!barcodeValidationSuspender.isValidationSuspended$onfido_capture_sdk_core_release(onDeviceValidationType)) {
                arrayList.add(onDeviceValidationType);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            OnDeviceValidationType onDeviceValidationType2 = (OnDeviceValidationType) obj;
            if (this.onfidoRemoteConfig.getDocumentCapture().isMrzDetectionEnabled() || onDeviceValidationType2 != OnDeviceValidationType.MRZ_DETECTION) {
                arrayList2.add(obj);
            }
        }
        final OnDeviceValidationType[] onDeviceValidationTypeArr = (OnDeviceValidationType[]) arrayList2.toArray(new OnDeviceValidationType[0]);
        final Map<OnDeviceValidationType, OnDeviceValidationResult> retainedValidationResults$onfido_capture_sdk_core_release = this.retainableValidationsResult.getRetainedValidationResults$onfido_capture_sdk_core_release();
        if (retainedValidationResults$onfido_capture_sdk_core_release.keySet().containsAll(ArraysKt.toList(onDeviceValidationTypeArr))) {
            map = Single.just(DocumentProcessingResults.INSTANCE.mapFromValidationTypeToResult(retainedValidationResults$onfido_capture_sdk_core_release));
            str = "just(...)";
        } else {
            ArrayList<OnDeviceValidationType> arrayList3 = new ArrayList();
            for (OnDeviceValidationType onDeviceValidationType3 : onDeviceValidationTypeArr) {
                if (!retainedValidationResults$onfido_capture_sdk_core_release.containsKey(onDeviceValidationType3)) {
                    arrayList3.add(onDeviceValidationType3);
                }
            }
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            for (OnDeviceValidationType onDeviceValidationType4 : arrayList3) {
                arrayList4.add(this.onDeviceValidationTransformer.transformPostCaptureValidations$onfido_capture_sdk_core_release(frame, onDeviceValidationType4, getValidator(documentType, countryCode, docSide, onDeviceValidationType4)));
            }
            Single map2 = Single.zip(arrayList4, new Function() { // from class: com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager$validate$3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Map<OnDeviceValidationType, OnDeviceValidationResult> apply(Object[] results) {
                    Intrinsics.checkNotNullParameter(results, "results");
                    return this.this$0.onDeviceValidationTransformer.getResults(onDeviceValidationTypeArr, results);
                }
            }).map(new Function() { // from class: com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager$validate$4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Map<OnDeviceValidationType, OnDeviceValidationResult> apply(Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return this.this$0.replaceValidationResultsByRetainedResults(it, retainedValidationResults$onfido_capture_sdk_core_release);
                }
            });
            final DocumentProcessingResults.Companion companion = DocumentProcessingResults.INSTANCE;
            map = map2.map(new Function() { // from class: com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidationsManager$validate$5
                @Override // io.reactivex.rxjava3.functions.Function
                public final DocumentProcessingResults apply(Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    return companion.mapFromValidationTypeToResult(p0);
                }
            });
            str = "map(...)";
        }
        Intrinsics.checkNotNullExpressionValue(map, str);
        return map;
    }
}
