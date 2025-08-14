package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.OnfidoExtensionsKt;
import com.onfido.android.sdk.capture.validation.Validation;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.javax.inject.Inject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bH\u0012JP\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\f\u0010\u001b\u001a\u00020\f*\u00020\u001cH\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/DocumentService;", "", "apiService", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;)V", "iqsUploadParser", "Lcom/onfido/android/sdk/capture/ui/camera/IQSUploadErrorParser;", "getPictureFileName", "", "extraInfo", "uploadDocumentMedia", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentUploadResult;", "data", "", ReactNativeBridgeUtiles.KEY_FILE_NAME, "docSide", "Lcom/onfido/api/client/data/DocSide;", "docType", "Lcom/onfido/android/sdk/capture/DocumentType;", "validations", "", "Lcom/onfido/android/sdk/capture/validation/Validation;", "sdkUploadMetaData", "Lcom/onfido/api/client/data/SdkUploadMetaData;", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "toDocumentUploadResult", "Lcom/onfido/api/client/data/DocumentUpload;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class DocumentService {
    public static final String FILE_TYPE_JPG = "image/jpeg";
    public static final String PICTURE_FILENAME = "onfido_captured_image";
    public static final String PICTURE_FILE_EXTENSION = ".jpg";
    private final OnfidoApiService apiService;
    private final IQSUploadErrorParser iqsUploadParser;

    @Inject
    public DocumentService(OnfidoApiService apiService) {
        Intrinsics.checkNotNullParameter(apiService, "apiService");
        this.apiService = apiService;
        this.iqsUploadParser = new IQSUploadErrorParser();
    }

    private String getPictureFileName(String extraInfo) {
        return extraInfo != null ? "onfido_captured_image_" + extraInfo + ".jpg" : "onfido_captured_image.jpg";
    }

    static /* synthetic */ String getPictureFileName$default(DocumentService documentService, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPictureFileName");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        return documentService.getPictureFileName(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DocumentUploadResult toDocumentUploadResult(DocumentUpload documentUpload) {
        return new DocumentUploadResult(documentUpload.getId(), documentUpload.getWarningsBundle(), documentUpload.getDocumentFeatures());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadDocumentMedia$lambda$0(final DocumentService this$0, String str, DocumentType docType, byte[] data, List validations, DocSide docSide, CountryCode countryCode, SdkUploadMetaData sdkUploadMetaData, final SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(docType, "$docType");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(validations, "$validations");
        Intrinsics.checkNotNullParameter(docSide, "$docSide");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "$sdkUploadMetaData");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this$0.apiService.upload$onfido_capture_sdk_core_release(new OnfidoApiService.PhotoUploadParams(this$0.getPictureFileName(str), OnfidoExtensionsKt.toDocType(docType), "image/jpeg", data, new OnfidoApiService.OnfidoApiServiceListener<DocumentUpload>() { // from class: com.onfido.android.sdk.capture.ui.camera.DocumentService$uploadDocumentMedia$1$resultListener$1
            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiServiceListener
            public void onError(int errorCode, String message, ErrorData errorData) {
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(errorData, "errorData");
                ErrorType uploadError = this$0.iqsUploadParser.parseUploadError(errorData, CaptureType.DOCUMENT);
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onError(new ErrorTypeException(uploadError));
            }

            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiServiceListener
            public void onSuccess(DocumentUpload uploadedDocument) {
                Intrinsics.checkNotNullParameter(uploadedDocument, "uploadedDocument");
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onSuccess(this$0.toDocumentUploadResult(uploadedDocument));
            }

            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiServiceListener
            public void onUploadError(ErrorType errorType) {
                Intrinsics.checkNotNullParameter(errorType, "errorType");
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onError(new ErrorTypeException(errorType));
            }
        }, validations, OnfidoExtensionsKt.toInternalDocSide(docSide), countryCode != null ? countryCode.getAlpha3() : null, sdkUploadMetaData));
    }

    public Single<DocumentUploadResult> uploadDocumentMedia(final byte[] data, final String fileName, final DocSide docSide, final DocumentType docType, final List<Validation> validations, final SdkUploadMetaData sdkUploadMetaData, final CountryCode issuingCountry) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        Intrinsics.checkNotNullParameter(docType, "docType");
        Intrinsics.checkNotNullParameter(validations, "validations");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Single<DocumentUploadResult> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.ui.camera.DocumentService$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                DocumentService.uploadDocumentMedia$lambda$0(this.f$0, fileName, docType, data, validations, docSide, issuingCountry, sdkUploadMetaData, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }
}
