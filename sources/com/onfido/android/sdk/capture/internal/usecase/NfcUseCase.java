package com.onfido.android.sdk.capture.internal.usecase;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.flow.CaptureStepDataBundle;
import com.onfido.android.sdk.capture.flow.NfcArguments;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.model.NFCOptions;
import com.onfido.android.sdk.capture.model.NFCOptionsKt;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.DocumentUploadResult;
import com.onfido.android.sdk.capture.ui.camera.DocumentValidationWarningsBundleUtilsKt;
import com.onfido.android.sdk.capture.ui.camera.ErrorTypeException;
import com.onfido.android.sdk.capture.ui.camera.IQSUploadErrorParser;
import com.onfido.android.sdk.capture.ui.camera.NfcPropertiesService;
import com.onfido.android.sdk.capture.ui.camera.UploadBinaryResult;
import com.onfido.android.sdk.capture.ui.nfc.model.NfcProperties;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.DocumentUtilsKt;
import com.onfido.android.sdk.capture.validation.MRZData;
import com.onfido.android.sdk.capture.validation.MRZDataType;
import com.onfido.android.sdk.capture.validation.device.MRZValidationResult;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocumentFeatures;
import com.onfido.api.client.data.DocumentValidationWarningsBundle;
import com.onfido.api.client.exception.GeoblockedException;
import com.onfido.api.client.exception.HttpParsedException;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.rx3.RxAwaitKt;
import retrofit2.HttpException;

@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 H2\u00020\u0001:\u0001HB'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0002J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0000¢\u0006\u0002\b\u001bJ\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0000¢\u0006\u0002\b\u001fJ\u0012\u0010 \u001a\u00020\u00102\b\u0010!\u001a\u0004\u0018\u00010\fH\u0002J*\u0010\"\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u001d\u001a\u00020%H\u0082@¢\u0006\u0002\u0010&J!\u0010'\u001a\u00020\u00102\b\u0010!\u001a\u0004\u0018\u00010\f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0000¢\u0006\u0002\b*JC\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\"\u00100\u001a\u001e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020301j\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u000203`4H\u0000¢\u0006\u0002\b5J<\u00106\u001a\u0002072\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u001d\u001a\u00020%2\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0018H\u0082@¢\u0006\u0002\u0010;J<\u0010<\u001a\u0002072\u0006\u0010\u001d\u001a\u00020%2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u00108\u001a\u0002092\u0006\u0010,\u001a\u00020-H\u0080@¢\u0006\u0004\b=\u0010>J\u001a\u0010?\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\b\u0010@\u001a\u0004\u0018\u00010AH\u0002J3\u0010B\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-2\b\u0010@\u001a\u0004\u0018\u00010A2\b\u0010!\u001a\u0004\u0018\u00010\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0002\bCJ!\u0010D\u001a\u00020\u00102\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010,\u001a\u0004\u0018\u00010-H\u0000¢\u0006\u0002\bGR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/NfcUseCase;", "", "nfcInteractor", "Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;", "documentConfigurationManager", "Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;", "nfcPropertiesService", "Lcom/onfido/android/sdk/capture/ui/camera/NfcPropertiesService;", "nfcTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;", "(Lcom/onfido/android/sdk/capture/internal/nfc/NfcInteractor;Lcom/onfido/android/sdk/capture/document/DocumentConfigurationManager;Lcom/onfido/android/sdk/capture/ui/camera/NfcPropertiesService;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/NfcTracker;)V", "getDocumentFeatures", "Lcom/onfido/api/client/data/DocumentFeatures;", "nfcProperties", "Lcom/onfido/android/sdk/capture/ui/nfc/model/NfcProperties;", "hasNfc", "", "getDocumentIdsForDocumentResponse", "", "", "nfcArguments", "Lcom/onfido/android/sdk/capture/flow/NfcArguments;", "uploadedMediaId", "getErrorTypeFromResult", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "uploadBinaryResult", "Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult$Error;", "getErrorTypeFromResult$onfido_capture_sdk_core_release", "getMRZResult", OnfidoLauncher.KEY_RESULT, "Lcom/onfido/android/sdk/capture/validation/device/MRZValidationResult;", "getMRZResult$onfido_capture_sdk_core_release", "getMediaNfcClassified", "documentFeatures", "getNfcProperties", "mrzDocument", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentUploadResult;", "(Lcom/onfido/android/sdk/capture/flow/NfcArguments;Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;Lcom/onfido/android/sdk/capture/ui/camera/DocumentUploadResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isDocumentNfcClassified", "capturedData", "Lcom/onfido/android/sdk/capture/flow/NfcArguments$CapturedNfcData;", "isDocumentNfcClassified$onfido_capture_sdk_core_release", "isMRZExtracted", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "mrzExtractionResultMap", "Ljava/util/HashMap;", "Lcom/onfido/android/sdk/capture/validation/MRZDataType;", "Lcom/onfido/android/sdk/capture/validation/MRZData;", "Lkotlin/collections/HashMap;", "isMRZExtracted$onfido_capture_sdk_core_release", "nfcPropertiesToBinaryResult", "Lcom/onfido/android/sdk/capture/ui/camera/UploadBinaryResult;", "documentData", "Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;", "iqsWarning", "(Lcom/onfido/android/sdk/capture/flow/NfcArguments;Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;Lcom/onfido/android/sdk/capture/ui/camera/DocumentUploadResult;Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;Lcom/onfido/android/sdk/capture/upload/ErrorType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processDocumentUploadResult", "processDocumentUploadResult$onfido_capture_sdk_core_release", "(Lcom/onfido/android/sdk/capture/ui/camera/DocumentUploadResult;Lcom/onfido/android/sdk/capture/flow/NfcArguments;Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;Lcom/onfido/android/sdk/capture/flow/CaptureStepDataBundle;Lcom/onfido/android/sdk/capture/DocumentType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldGetNfcProperties", "docSide", "Lcom/onfido/api/client/data/DocSide;", "shouldProceedWithNfc", "shouldProceedWithNfc$onfido_capture_sdk_core_release", "shouldScanNfc", "nfcOptions", "Lcom/onfido/android/sdk/capture/model/NFCOptions;", "shouldScanNfc$onfido_capture_sdk_core_release", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NfcUseCase {
    private static final List<MRZDataType> DUTCH_ID_MRZ_REQUIRED_FIELDS;
    private static final String MRZ_IS_NOT_READABLE = "0";
    private static final String MRZ_IS_READABLE = "1";
    private static final String NFC_LOGGER = "NFC Logger";
    private static final List<MRZDataType> PASSPORT_MRZ_REQUIRED_FIELDS;
    private final DocumentConfigurationManager documentConfigurationManager;
    private final NfcInteractor nfcInteractor;
    private final NfcPropertiesService nfcPropertiesService;
    private final NfcTracker nfcTracker;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.internal.usecase.NfcUseCase", f = "NfcUseCase.kt", i = {0, 0, 0, 0}, l = {87}, m = "nfcPropertiesToBinaryResult", n = {"this", OnfidoLauncher.KEY_RESULT, "documentData", "iqsWarning"}, s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: com.onfido.android.sdk.capture.internal.usecase.NfcUseCase$nfcPropertiesToBinaryResult$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return NfcUseCase.this.nfcPropertiesToBinaryResult(null, null, null, null, null, this);
        }
    }

    static {
        MRZDataType mRZDataType = MRZDataType.DATE_OF_BIRTH;
        MRZDataType mRZDataType2 = MRZDataType.EXPIRY_DATE;
        PASSPORT_MRZ_REQUIRED_FIELDS = CollectionsKt.listOf((Object[]) new MRZDataType[]{MRZDataType.PASSPORT_NUMBER, mRZDataType, mRZDataType2});
        DUTCH_ID_MRZ_REQUIRED_FIELDS = CollectionsKt.listOf((Object[]) new MRZDataType[]{MRZDataType.DOCUMENT_NUMBER, MRZDataType.PERSONAL_NUMBER, mRZDataType, mRZDataType2});
    }

    @Inject
    public NfcUseCase(NfcInteractor nfcInteractor, DocumentConfigurationManager documentConfigurationManager, NfcPropertiesService nfcPropertiesService, NfcTracker nfcTracker) {
        Intrinsics.checkNotNullParameter(nfcInteractor, "nfcInteractor");
        Intrinsics.checkNotNullParameter(documentConfigurationManager, "documentConfigurationManager");
        Intrinsics.checkNotNullParameter(nfcPropertiesService, "nfcPropertiesService");
        Intrinsics.checkNotNullParameter(nfcTracker, "nfcTracker");
        this.nfcInteractor = nfcInteractor;
        this.documentConfigurationManager = documentConfigurationManager;
        this.nfcPropertiesService = nfcPropertiesService;
        this.nfcTracker = nfcTracker;
    }

    private final DocumentFeatures getDocumentFeatures(NfcProperties nfcProperties, boolean hasNfc) {
        return new DocumentFeatures(hasNfc, nfcProperties.getHasCan(), nfcProperties.getHasPin(), nfcProperties.getCanLength(), nfcProperties.getPinLength());
    }

    static /* synthetic */ DocumentFeatures getDocumentFeatures$default(NfcUseCase nfcUseCase, NfcProperties nfcProperties, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return nfcUseCase.getDocumentFeatures(nfcProperties, z);
    }

    private final List<String> getDocumentIdsForDocumentResponse(NfcArguments nfcArguments, String uploadedMediaId) {
        NfcArguments.CapturedNfcData capturedData;
        return CollectionsKt.listOfNotNull((Object[]) new String[]{(nfcArguments == null || (capturedData = nfcArguments.getCapturedData()) == null) ? null : capturedData.getFrontId(), uploadedMediaId});
    }

    private final boolean getMediaNfcClassified(DocumentFeatures documentFeatures) {
        if (documentFeatures != null) {
            return documentFeatures.getHasNFC();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getNfcProperties(NfcArguments nfcArguments, MRZDocument mRZDocument, DocumentUploadResult documentUploadResult, Continuation<? super NfcProperties> continuation) {
        return RxAwaitKt.await(this.nfcPropertiesService.get(getDocumentIdsForDocumentResponse(nfcArguments, documentUploadResult.getMediaId()), mRZDocument, documentUploadResult.getDocumentFeatures()), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object nfcPropertiesToBinaryResult(com.onfido.android.sdk.capture.flow.NfcArguments r16, com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument r17, com.onfido.android.sdk.capture.ui.camera.DocumentUploadResult r18, com.onfido.android.sdk.capture.flow.CaptureStepDataBundle r19, com.onfido.android.sdk.capture.upload.ErrorType r20, kotlin.coroutines.Continuation<? super com.onfido.android.sdk.capture.ui.camera.UploadBinaryResult> r21) {
        /*
            Method dump skipped, instructions count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.usecase.NfcUseCase.nfcPropertiesToBinaryResult(com.onfido.android.sdk.capture.flow.NfcArguments, com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument, com.onfido.android.sdk.capture.ui.camera.DocumentUploadResult, com.onfido.android.sdk.capture.flow.CaptureStepDataBundle, com.onfido.android.sdk.capture.upload.ErrorType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean shouldGetNfcProperties(DocumentType documentType, DocSide docSide) {
        return docSide == this.documentConfigurationManager.getDocSideForNfcProperties(documentType);
    }

    public final ErrorType getErrorTypeFromResult$onfido_capture_sdk_core_release(UploadBinaryResult.Error uploadBinaryResult) {
        Intrinsics.checkNotNullParameter(uploadBinaryResult, "uploadBinaryResult");
        Timber.INSTANCE.e("NFC Logger - UploadBinaryResult Error: " + uploadBinaryResult.getThrowable().getMessage(), new Object[0]);
        IQSUploadErrorParser iQSUploadErrorParser = new IQSUploadErrorParser();
        Throwable throwable = uploadBinaryResult.getThrowable();
        if (throwable instanceof ErrorTypeException) {
            return ((ErrorTypeException) throwable).getErrorType();
        }
        if (throwable instanceof HttpParsedException) {
            return iQSUploadErrorParser.parseUploadError(((HttpParsedException) throwable).getErrorData(), CaptureType.DOCUMENT);
        }
        if (throwable instanceof TokenExpiredException) {
            return ErrorType.TokenExpired.INSTANCE;
        }
        if (!(throwable instanceof SSLPeerUnverifiedException)) {
            return throwable instanceof HttpException ? ErrorType.Network.INSTANCE : throwable instanceof GeoblockedException ? ErrorType.Geoblocked.INSTANCE : ErrorType.Generic.INSTANCE;
        }
        String localizedMessage = ((SSLPeerUnverifiedException) throwable).getLocalizedMessage();
        if (localizedMessage == null) {
            localizedMessage = "";
        }
        return new ErrorType.InvalidCertificate(localizedMessage);
    }

    public final String getMRZResult$onfido_capture_sdk_core_release(MRZValidationResult result) {
        if (result == null || !result.getWasExecuted()) {
            return null;
        }
        if (result.isMrzReadable()) {
            Timber.INSTANCE.i("NFC Logger - MRZ is readable", new Object[0]);
            return MRZ_IS_READABLE;
        }
        Timber.INSTANCE.i("NFC Logger - MRZ is not readable", new Object[0]);
        return MRZ_IS_NOT_READABLE;
    }

    public final boolean isDocumentNfcClassified$onfido_capture_sdk_core_release(DocumentFeatures documentFeatures, NfcArguments.CapturedNfcData capturedData) {
        return (capturedData != null && capturedData.getNfcSupported()) || getMediaNfcClassified(documentFeatures);
    }

    public final boolean isMRZExtracted$onfido_capture_sdk_core_release(DocumentType documentType, CountryCode countryCode, HashMap<MRZDataType, MRZData> mrzExtractionResultMap) {
        boolean zHasRequiredFields;
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(mrzExtractionResultMap, "mrzExtractionResultMap");
        if (documentType == DocumentType.PASSPORT) {
            zHasRequiredFields = DocumentUtilsKt.hasRequiredFields(mrzExtractionResultMap, PASSPORT_MRZ_REQUIRED_FIELDS);
            Timber.INSTANCE.i("NFC Logger - MRZ detected [Passport] : " + zHasRequiredFields, new Object[0]);
        } else {
            if (documentType != DocumentType.NATIONAL_IDENTITY_CARD || countryCode != CountryCode.NL) {
                Timber.INSTANCE.i("NFC Logger - MRZ not detected", new Object[0]);
                return false;
            }
            zHasRequiredFields = DocumentUtilsKt.hasRequiredFields(mrzExtractionResultMap, DUTCH_ID_MRZ_REQUIRED_FIELDS);
            Timber.INSTANCE.i("NFC Logger - MRZ detected [Dutch ID] : " + zHasRequiredFields, new Object[0]);
        }
        return zHasRequiredFields;
    }

    public final Object processDocumentUploadResult$onfido_capture_sdk_core_release(DocumentUploadResult documentUploadResult, NfcArguments nfcArguments, MRZDocument mRZDocument, CaptureStepDataBundle captureStepDataBundle, DocumentType documentType, Continuation<? super UploadBinaryResult> continuation) {
        DocumentValidationWarningsBundle warningsBundle = documentUploadResult.getWarningsBundle();
        ErrorType errorTypeFirstRemoteWarningOrNull = warningsBundle != null ? DocumentValidationWarningsBundleUtilsKt.firstRemoteWarningOrNull(warningsBundle) : null;
        return shouldProceedWithNfc$onfido_capture_sdk_core_release(documentType, captureStepDataBundle.getDocSide(), documentUploadResult.getDocumentFeatures(), nfcArguments) ? nfcPropertiesToBinaryResult(nfcArguments, mRZDocument, documentUploadResult, captureStepDataBundle, errorTypeFirstRemoteWarningOrNull, continuation) : new UploadBinaryResult.BinaryUploaded(documentUploadResult.getMediaId(), errorTypeFirstRemoteWarningOrNull, getMediaNfcClassified(documentUploadResult.getDocumentFeatures()), documentUploadResult.getWarningsBundle());
    }

    public final boolean shouldProceedWithNfc$onfido_capture_sdk_core_release(DocumentType documentType, DocSide docSide, DocumentFeatures documentFeatures, NfcArguments nfcArguments) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        if (shouldScanNfc$onfido_capture_sdk_core_release(nfcArguments != null ? nfcArguments.getNfcOptions() : null, documentType)) {
            if (isDocumentNfcClassified$onfido_capture_sdk_core_release(documentFeatures, nfcArguments != null ? nfcArguments.getCapturedData() : null) && shouldGetNfcProperties(documentType, docSide)) {
                return true;
            }
        }
        return false;
    }

    public final boolean shouldScanNfc$onfido_capture_sdk_core_release(NFCOptions nfcOptions, DocumentType documentType) {
        if (Intrinsics.areEqual(nfcOptions, NFCOptions.Enabled.Required.INSTANCE)) {
            return true;
        }
        return nfcOptions != null && NFCOptionsKt.isEnabled(nfcOptions) && Intrinsics.areEqual(documentType != null ? Boolean.valueOf(this.documentConfigurationManager.shouldScanNfc(documentType)) : null, Boolean.TRUE) && this.nfcInteractor.isNfcSupported();
    }
}
