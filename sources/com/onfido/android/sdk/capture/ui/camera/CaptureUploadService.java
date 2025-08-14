package com.onfido.android.sdk.capture.ui.camera;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.common.cryptography.Cryptography;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.ByteArrayExtensionsKt;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.OnfidoExtensionsKt;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentMediaIntegrity;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.data.InternalDocSide;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
import java.util.concurrent.Callable;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import retrofit2.HttpException;

@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0010\u0018\u0000 72\u00020\u0001:\u000278B;\b\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0012J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH\u0012J\r\u0010\u001b\u001a\u00020\u001cH\u0010¢\u0006\u0002\b\u001dJG\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0010¢\u0006\u0002\b(Je\u0010)\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001a2!\u0010*\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u001c0+2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0012J-\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020!2\u0006\u00105\u001a\u000201H\u0010¢\u0006\u0002\b6R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService;", "", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", "onfidoApiService", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadServiceListener;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "cryptography", "Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;", "payloadHelper", "Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;", "(Lcom/onfido/android/sdk/capture/ui/CaptureType;Lcom/onfido/android/sdk/capture/network/OnfidoApiService;Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadServiceListener;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/common/cryptography/Cryptography;Lcom/onfido/android/sdk/capture/common/cryptography/PayloadHelper;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "iqsUploadParser", "Lcom/onfido/android/sdk/capture/ui/camera/IQSUploadErrorParser;", "getPictureFileName", "", "extraInfo", "signDocumentVideo", "Lcom/onfido/api/client/data/DocumentMediaIntegrity;", "videoPath", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "stop", "", "stop$onfido_capture_sdk_core_release", "uploadDocumentVideo", "documentId", "sdkUploadMetaData", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "docSide", "Lcom/onfido/api/client/data/DocSide;", "docType", "Lcom/onfido/android/sdk/capture/DocumentType;", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "Lcom/onfido/android/sdk/capture/utils/CountryCode;", "uploadDocumentVideo$onfido_capture_sdk_core_release", "uploadDocumentVideoForDocumentId", "onDocumentUploaded", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "documentVideoId", "uploadSelfie", "shouldAttemptValidation", "", "data", "", "metadata", "isPayloadSignatureEnabled", "uploadSelfie$onfido_capture_sdk_core_release", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class CaptureUploadService {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String FILE_TYPE_JPG = "image/jpeg";

    @Deprecated
    public static final String PICTURE_FILENAME = "onfido_captured_image";

    @Deprecated
    public static final String PICTURE_FILE_EXTENSION = ".jpg";
    private final CaptureType captureType;
    private final CompositeDisposable compositeDisposable;
    private final Cryptography cryptography;
    private final IQSUploadErrorParser iqsUploadParser;
    private final CaptureUploadServiceListener listener;
    private final OnfidoApiService onfidoApiService;
    private final PayloadHelper payloadHelper;
    private final SchedulersProvider schedulersProvider;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService$Companion;", "", "()V", "FILE_TYPE_JPG", "", "PICTURE_FILENAME", "PICTURE_FILE_EXTENSION", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadService;", ReactNativeBridgeUtiles.KEY_CAPTURE_TYPE, "Lcom/onfido/android/sdk/capture/ui/CaptureType;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/ui/camera/CaptureUploadServiceListener;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        CaptureUploadService create(CaptureType captureType, CaptureUploadServiceListener listener);
    }

    @AssistedInject
    public CaptureUploadService(@Assisted CaptureType captureType, OnfidoApiService onfidoApiService, @Assisted CaptureUploadServiceListener listener, SchedulersProvider schedulersProvider, Cryptography cryptography, PayloadHelper payloadHelper) {
        Intrinsics.checkNotNullParameter(captureType, "captureType");
        Intrinsics.checkNotNullParameter(onfidoApiService, "onfidoApiService");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(cryptography, "cryptography");
        Intrinsics.checkNotNullParameter(payloadHelper, "payloadHelper");
        this.captureType = captureType;
        this.onfidoApiService = onfidoApiService;
        this.listener = listener;
        this.schedulersProvider = schedulersProvider;
        this.cryptography = cryptography;
        this.payloadHelper = payloadHelper;
        this.iqsUploadParser = new IQSUploadErrorParser();
        this.compositeDisposable = new CompositeDisposable();
    }

    private String getPictureFileName(String extraInfo) {
        return extraInfo != null ? "onfido_captured_image_" + extraInfo + ".jpg" : "onfido_captured_image.jpg";
    }

    static /* synthetic */ String getPictureFileName$default(CaptureUploadService captureUploadService, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPictureFileName");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        return captureUploadService.getPictureFileName(str);
    }

    private DocumentMediaIntegrity signDocumentVideo(Cryptography cryptography, String videoPath, ApplicantId applicantId) {
        Cryptography.Result resultSign = cryptography.sign(FilesKt.readBytes(new File(videoPath)), applicantId);
        return new DocumentMediaIntegrity(ByteArrayExtensionsKt.toBase64String(resultSign.getSignature()), StringsKt.decodeToString(resultSign.getClientNonce()));
    }

    private void uploadDocumentVideoForDocumentId(final String documentId, final String videoPath, final SdkUploadMetaData sdkUploadMetaData, final ApplicantId applicantId, final Function1<? super String, Unit> onDocumentUploaded, final DocSide docSide, final DocumentType docType, final CountryCode issuingCountry) {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = Single.fromCallable(new Callable() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureUploadService$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return CaptureUploadService.uploadDocumentVideoForDocumentId$lambda$0(this.f$0, videoPath, applicantId);
            }
        }).flatMap(new Function() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureUploadService.uploadDocumentVideoForDocumentId.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends String> apply(DocumentMediaIntegrity documentMediaIntegrity) {
                OnfidoApiService onfidoApiService = CaptureUploadService.this.onfidoApiService;
                String str = documentId;
                String str2 = videoPath;
                SdkUploadMetaData sdkUploadMetaData2 = sdkUploadMetaData;
                Intrinsics.checkNotNull(documentMediaIntegrity);
                InternalDocSide internalDocSide = OnfidoExtensionsKt.toInternalDocSide(docSide);
                DocType docType2 = OnfidoExtensionsKt.toDocType(docType);
                CountryCode countryCode = issuingCountry;
                return onfidoApiService.uploadDocumentVideo$onfido_capture_sdk_core_release(str, str2, sdkUploadMetaData2, documentMediaIntegrity, internalDocSide, docType2, countryCode != null ? countryCode.getAlpha3() : null);
            }
        }).subscribeOn(this.schedulersProvider.getIo()).observeOn(this.schedulersProvider.getUi()).doFinally(new Action() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureUploadService$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CaptureUploadService.uploadDocumentVideoForDocumentId$lambda$1(videoPath);
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureUploadService.uploadDocumentVideoForDocumentId.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                onDocumentUploaded.invoke(it);
            }
        }, new Consumer() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureUploadService.uploadDocumentVideoForDocumentId.5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable throwable) {
                ErrorType invalidCertificate;
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                if (throwable instanceof TokenExpiredException) {
                    invalidCertificate = ErrorType.TokenExpired.INSTANCE;
                } else if (throwable instanceof SSLPeerUnverifiedException) {
                    String localizedMessage = ((SSLPeerUnverifiedException) throwable).getLocalizedMessage();
                    if (localizedMessage == null) {
                        localizedMessage = "";
                    }
                    invalidCertificate = new ErrorType.InvalidCertificate(localizedMessage);
                } else {
                    invalidCertificate = throwable instanceof HttpException ? ErrorType.Network.INSTANCE : ErrorType.Generic.INSTANCE;
                }
                CaptureUploadService.this.listener.onUploadError(invalidCertificate);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DocumentMediaIntegrity uploadDocumentVideoForDocumentId$lambda$0(CaptureUploadService this$0, String videoPath, ApplicantId applicantId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(videoPath, "$videoPath");
        Intrinsics.checkNotNullParameter(applicantId, "$applicantId");
        return this$0.signDocumentVideo(this$0.cryptography, videoPath, applicantId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadDocumentVideoForDocumentId$lambda$1(String videoPath) {
        Intrinsics.checkNotNullParameter(videoPath, "$videoPath");
        new File(videoPath).delete();
    }

    public void stop$onfido_capture_sdk_core_release() {
        this.compositeDisposable.clear();
    }

    public void uploadDocumentVideo$onfido_capture_sdk_core_release(String documentId, String videoPath, SdkUploadMetaData sdkUploadMetaData, ApplicantId applicantId, DocSide docSide, DocumentType docType, CountryCode issuingCountry) {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNullParameter(videoPath, "videoPath");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        Intrinsics.checkNotNullParameter(docType, "docType");
        uploadDocumentVideoForDocumentId(documentId, videoPath, sdkUploadMetaData, applicantId, new CaptureUploadService$uploadDocumentVideo$1(this.listener), docSide, docType, issuingCountry);
    }

    public void uploadSelfie$onfido_capture_sdk_core_release(boolean shouldAttemptValidation, byte[] data, SdkUploadMetaData metadata, boolean isPayloadSignatureEnabled) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.onfidoApiService.uploadLivePhoto$onfido_capture_sdk_core_release(getPictureFileName$default(this, null, 1, null), "image/jpeg", data, shouldAttemptValidation, this.payloadHelper.getSignedPayload(data, metadata, isPayloadSignatureEnabled), new OnfidoApiService.OnfidoApiServiceListener<LivePhotoUpload>() { // from class: com.onfido.android.sdk.capture.ui.camera.CaptureUploadService$uploadSelfie$livePhotoSuccessListener$1
            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiServiceListener
            public void onError(int errorCode, String message, ErrorData errorData) {
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(errorData, "errorData");
                this.this$0.listener.onUploadError(this.this$0.iqsUploadParser.parseUploadError(errorData, this.this$0.captureType));
            }

            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiServiceListener
            public void onSuccess(LivePhotoUpload uploadedDocument) {
                Intrinsics.checkNotNullParameter(uploadedDocument, "uploadedDocument");
                this.this$0.listener.onLivePhotoUploaded(uploadedDocument);
            }

            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiServiceListener
            public void onUploadError(ErrorType errorType) {
                Intrinsics.checkNotNullParameter(errorType, "errorType");
                this.this$0.listener.onUploadError(errorType);
            }
        });
    }
}
