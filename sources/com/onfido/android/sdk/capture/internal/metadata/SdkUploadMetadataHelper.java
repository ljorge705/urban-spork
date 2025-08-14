package com.onfido.android.sdk.capture.internal.metadata;

import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.validation.device.BarcodeValidationResult;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.ExtractedInfo;
import com.onfido.api.client.data.PhotoDetection;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0010\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0012J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0012J\b\u0010\u0015\u001a\u00020\u0016H\u0016J0\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0012J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fH\u0012J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fH\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;", "", "identityInteractor", "Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "environmentIntegrityChecker", "Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;", "(Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/util/environment/EnvironmentIntegrityChecker;)V", "barcodeProcessingResult", "Lcom/onfido/api/client/data/PhotoDetection$BarcodeProcessingResult;", "captureStepDataBundle", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "barcodeResults", "Lcom/onfido/android/sdk/capture/validation/device/BarcodeValidationResult;", "blurProcessingResult", "Lcom/onfido/api/client/data/PhotoDetection$ProcessingResult;", "documentProcessingResults", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "blurRejectionCount", "", "create", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "isMRZExtracted", "", "numberOfTakenPhoto", "glareProcessingResult", "isRelevantDocumentForMRZ", "mrzExtractionResult", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class SdkUploadMetadataHelper {
    private final EnvironmentIntegrityChecker environmentIntegrityChecker;
    private final IdentityInteractor identityInteractor;
    private final OnfidoRemoteConfig onfidoRemoteConfig;

    @Inject
    public SdkUploadMetadataHelper(IdentityInteractor identityInteractor, OnfidoRemoteConfig onfidoRemoteConfig, EnvironmentIntegrityChecker environmentIntegrityChecker) {
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(environmentIntegrityChecker, "environmentIntegrityChecker");
        this.identityInteractor = identityInteractor;
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.environmentIntegrityChecker = environmentIntegrityChecker;
    }

    private PhotoDetection.BarcodeProcessingResult barcodeProcessingResult(CaptureStepDataBundle captureStepDataBundle, BarcodeValidationResult barcodeResults) {
        if (DocumentType.DRIVING_LICENCE != captureStepDataBundle.getDocumentType() || DocSide.BACK != captureStepDataBundle.getDocSide() || CountryCode.US != captureStepDataBundle.getCountryCode()) {
            return null;
        }
        String barcodeValue = barcodeResults.getBarcodeValue();
        return new PhotoDetection.BarcodeProcessingResult(barcodeValue != null ? new ExtractedInfo(barcodeValue) : null, barcodeResults.isValid());
    }

    private PhotoDetection.ProcessingResult blurProcessingResult(DocumentProcessingResults documentProcessingResults, int blurRejectionCount) {
        if (documentProcessingResults.getBlurResults().getWasExecuted()) {
            return new PhotoDetection.ProcessingResult(documentProcessingResults.getBlurResults().isValid(), Integer.valueOf(blurRejectionCount));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PhotoDetection.ProcessingResult glareProcessingResult(DocumentProcessingResults documentProcessingResults) {
        Integer num = null;
        Object[] objArr = 0;
        if (documentProcessingResults.getGlareResults().getWasExecuted()) {
            return new PhotoDetection.ProcessingResult(documentProcessingResults.getGlareResults().isValid(), num, 2, (DefaultConstructorMarker) (objArr == true ? 1 : 0));
        }
        return null;
    }

    private boolean isRelevantDocumentForMRZ(CaptureStepDataBundle captureStepDataBundle) {
        return captureStepDataBundle.getDocumentType() == DocumentType.PASSPORT || (captureStepDataBundle.getDocumentType() == DocumentType.NATIONAL_IDENTITY_CARD && captureStepDataBundle.getCountryCode() == CountryCode.NL && captureStepDataBundle.getDocSide() == DocSide.BACK);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PhotoDetection.ProcessingResult mrzExtractionResult(boolean isMRZExtracted, CaptureStepDataBundle captureStepDataBundle) {
        Integer num = null;
        Object[] objArr = 0;
        if (isRelevantDocumentForMRZ(captureStepDataBundle)) {
            return new PhotoDetection.ProcessingResult(isMRZExtracted, num, 2, (DefaultConstructorMarker) (objArr == true ? 1 : 0));
        }
        return null;
    }

    public SdkUploadMetaData create() {
        return new SdkUploadMetaData(null, null, null, null, this.identityInteractor.getDeviceSystem$onfido_capture_sdk_core_release(), this.onfidoRemoteConfig.getSdkConfiguration(), null, this.environmentIntegrityChecker.hasEnvironmentIntegrity(), BuildConfig.SDK_SOURCE, "22.7.0");
    }

    public SdkUploadMetaData create(DocumentProcessingResults documentProcessingResults, int blurRejectionCount, boolean isMRZExtracted, int numberOfTakenPhoto, CaptureStepDataBundle captureStepDataBundle) {
        Intrinsics.checkNotNullParameter(documentProcessingResults, "documentProcessingResults");
        Intrinsics.checkNotNullParameter(captureStepDataBundle, "captureStepDataBundle");
        return new SdkUploadMetaData(glareProcessingResult(documentProcessingResults), blurProcessingResult(documentProcessingResults, blurRejectionCount), barcodeProcessingResult(captureStepDataBundle, documentProcessingResults.getBarcodeResults()), mrzExtractionResult(isMRZExtracted, captureStepDataBundle), this.identityInteractor.getDeviceSystem$onfido_capture_sdk_core_release(), this.onfidoRemoteConfig.getSdkConfiguration(), Integer.valueOf(numberOfTakenPhoto), this.environmentIntegrityChecker.hasEnvironmentIntegrity(), BuildConfig.SDK_SOURCE, "22.7.0");
    }
}
