package com.onfido.android.sdk.capture.network;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocument;
import com.onfido.android.sdk.capture.document.DocumentMediaType;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector;
import com.onfido.android.sdk.capture.upload.ErrorType;
import com.onfido.android.sdk.capture.utils.OnfidoExtensionsKt;
import com.onfido.android.sdk.capture.validation.Validation;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.ValidationType;
import com.onfido.api.client.data.DeviceInfo;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentBinaryMedia;
import com.onfido.api.client.data.DocumentFeatures;
import com.onfido.api.client.data.DocumentMediaIntegrity;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.DocumentValidationWarningsBundle;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.data.InternalDocSide;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import com.onfido.api.client.data.NfcProperties;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.data.SupportedDocuments;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import com.onfido.reactnative.sdk.ReactNativeBridgeUtiles;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.sentry.protocol.Message;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0017\u0018\u0000 \\2\u00020\u0001:\u0006\\]^_`aB/\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0012J+\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0010¢\u0006\u0002\b\u0017J\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0010H\u0010¢\u0006\u0002\b\u001aJ\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0010H\u0010¢\u0006\u0002\b\u001dJ\u0013\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0010H\u0010¢\u0006\u0002\b J&\u0010!\u001a\b\u0012\u0004\u0012\u0002H\"0\u0010\"\b\b\u0000\u0010\"*\u00020\u00012\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\"0\u0010H\u0012J\b\u0010$\u001a\u00020%H\u0012J\u0015\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0010¢\u0006\u0002\b*JM\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00102\u0006\u0010-\u001a\u00020\u00142\u0006\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u0002002\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u000106H\u0010¢\u0006\u0002\b7Jm\u00108\u001a\b\u0012\u0004\u0012\u00020,0\u00102\u0006\u0010-\u001a\u00020\u00142\b\u0010.\u001a\u0004\u0018\u00010\u00142\u0006\u0010/\u001a\u0002002\n\b\u0002\u00109\u001a\u0004\u0018\u00010:2\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\u0010\b\u0002\u0010;\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010\u00132\b\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u000106H\u0010¢\u0006\u0002\b=JM\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00140\u00102\u0006\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u00020\u00142\u0006\u00105\u001a\u0002062\u0006\u0010A\u001a\u00020B2\u0006\u00109\u001a\u00020C2\u0006\u00101\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0014H\u0010¢\u0006\u0002\bFJI\u0010G\u001a\u00020'2\b\u0010-\u001a\u0004\u0018\u00010\u00142\b\u0010.\u001a\u0004\u0018\u00010\u00142\b\u00103\u001a\u0004\u0018\u0001042\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0MH\u0010¢\u0006\u0002\bOJ#\u0010P\u001a\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010(\u001a\u00020S2\u0006\u0010J\u001a\u00020KH\u0010¢\u0006\u0002\bTJ\u001b\u0010U\u001a\b\u0012\u0004\u0012\u00020V0\u00102\u0006\u0010(\u001a\u00020WH\u0010¢\u0006\u0002\bXJ\u000e\u0010Y\u001a\u0004\u0018\u00010Z*\u00020[H\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "", "onfidoApi", "Lcom/onfido/api/client/OnfidoAPI;", "identityInteractor", "Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "sdkUploadMetadataHelper", "Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;", "tokenExpirationServiceConnetor", "Lcom/onfido/android/sdk/capture/token/TokenExpirationServiceConnector;", "(Lcom/onfido/api/client/OnfidoAPI;Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;Lcom/onfido/android/sdk/capture/OnfidoConfig;Lcom/onfido/android/sdk/capture/internal/metadata/SdkUploadMetadataHelper;Lcom/onfido/android/sdk/capture/token/TokenExpirationServiceConnector;)V", "deviceInfo", "Lcom/onfido/api/client/data/DeviceInfo;", "getNfcProperties", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/api/client/data/NfcProperties;", "documentIds", "", "", "mrzDocument", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocument;", "getNfcProperties$onfido_capture_sdk_core_release", "getSDKConfig", "Lcom/onfido/api/client/data/SdkConfiguration;", "getSDKConfig$onfido_capture_sdk_core_release", "getSupportedDocuments", "Lcom/onfido/api/client/data/SupportedDocuments;", "getSupportedDocuments$onfido_capture_sdk_core_release", "liveVideoChallenges", "Lcom/onfido/api/client/data/LiveVideoChallenges;", "liveVideoChallenges$onfido_capture_sdk_core_release", "retrySingleRequest", ExifInterface.GPS_DIRECTION_TRUE, "single", "tokenRefreshTask", "Lio/reactivex/rxjava3/core/Completable;", "upload", "", Message.JsonKeys.PARAMS, "Lcom/onfido/android/sdk/capture/network/OnfidoApiService$PhotoUploadParams;", "upload$onfido_capture_sdk_core_release", "uploadDocument", "Lcom/onfido/api/client/data/DocumentMediaUploadResponse;", ReactNativeBridgeUtiles.KEY_FILE_NAME, ReactNativeBridgeUtiles.KEY_FILE_TYPE, "mediaType", "Lcom/onfido/android/sdk/capture/document/DocumentMediaType;", "docType", "Lcom/onfido/android/sdk/capture/DocumentType;", "data", "", "sdkUploadMetaData", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "uploadDocument$onfido_capture_sdk_core_release", "uploadDocumentMedia", "docSide", "Lcom/onfido/api/client/data/DocSide;", "validations", "Lcom/onfido/android/sdk/capture/validation/Validation;", "uploadDocumentMedia$onfido_capture_sdk_core_release", "uploadDocumentVideo", "documentId", "videoPath", "documentMediaIntegrity", "Lcom/onfido/api/client/data/DocumentMediaIntegrity;", "Lcom/onfido/api/client/data/InternalDocSide;", "Lcom/onfido/api/client/data/DocType;", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "uploadDocumentVideo$onfido_capture_sdk_core_release", "uploadLivePhoto", "advancedValidation", "", "payloadIntegrity", "Lcom/onfido/api/client/data/PayloadIntegrity;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;", "Lcom/onfido/api/client/data/LivePhotoUpload;", "uploadLivePhoto$onfido_capture_sdk_core_release", "uploadLiveVideo", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/api/client/data/LiveVideoUpload;", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService$VideoUploadParams;", "uploadLiveVideo$onfido_capture_sdk_core_release", "uploadPoaDocument", "Lcom/onfido/api/client/data/PoaDocumentUpload;", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService$PoaDocumentUploadParams;", "uploadPoaDocument$onfido_capture_sdk_core_release", "asCertificateError", "Lcom/onfido/android/sdk/capture/upload/ErrorType$InvalidCertificate;", "", "Companion", "OnfidoApiListener", "OnfidoApiServiceListener", "PhotoUploadParams", "PoaDocumentUploadParams", "VideoUploadParams", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class OnfidoApiService {
    private static final Companion Companion = new Companion(null);
    private static final String GEOBLOCKED_ERROR = "geoblocked_request";
    private static final String TOKEN_EXPIRED = "expired_token";
    private final IdentityInteractor identityInteractor;
    private final OnfidoAPI onfidoApi;
    private final OnfidoConfig onfidoConfig;
    private final SdkUploadMetadataHelper sdkUploadMetadataHelper;
    private final TokenExpirationServiceConnector tokenExpirationServiceConnetor;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/network/OnfidoApiService$Companion;", "", "()V", "GEOBLOCKED_ERROR", "", "TOKEN_EXPIRED", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\b¢\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\"\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0015\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\tH\u0003J\b\u0010\u0016\u001a\u00020\tH&R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiListener;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/onfido/api/client/OnfidoAPI$Listener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;)V", "getListener", "()Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;", "onError", "", "errorCode", "", "message", "", "errorData", "Lcom/onfido/api/client/data/ErrorData;", "onFailure", "t", "", "onSuccess", "(Ljava/lang/Object;)V", "onTokenExpired", "onTokenRefreshed", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    abstract class OnfidoApiListener<T> implements OnfidoAPI.Listener<T> {
        private final OnfidoApiServiceListener<T> listener;
        final /* synthetic */ OnfidoApiService this$0;

        public OnfidoApiListener(OnfidoApiService onfidoApiService, OnfidoApiServiceListener<T> listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.this$0 = onfidoApiService;
            this.listener = listener;
        }

        private final void onTokenExpired() {
            this.this$0.tokenRefreshTask().subscribe(new Action() { // from class: com.onfido.android.sdk.capture.network.OnfidoApiService$OnfidoApiListener$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    this.f$0.onTokenRefreshed();
                }
            }, new Consumer(this) { // from class: com.onfido.android.sdk.capture.network.OnfidoApiService$OnfidoApiListener$onTokenExpired$2
                final /* synthetic */ OnfidoApiService.OnfidoApiListener<T> this$0;

                {
                    this.this$0 = this;
                }

                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this.this$0.getListener().onUploadError(ErrorType.TokenExpired.INSTANCE);
                }
            });
        }

        public final OnfidoApiServiceListener<T> getListener() {
            return this.listener;
        }

        @Override // com.onfido.api.client.OnfidoAPI.Listener
        public void onError(int errorCode, String message, ErrorData errorData) {
            OnfidoApiServiceListener<T> onfidoApiServiceListener;
            ErrorType errorType;
            Intrinsics.checkNotNullParameter(message, "message");
            if (errorData == null || errorData.getError() == null) {
                onfidoApiServiceListener = this.listener;
                errorType = ErrorType.Generic.INSTANCE;
            } else if (StringsKt.equals(OnfidoApiService.TOKEN_EXPIRED, errorData.getError().getType(), true)) {
                if (this.this$0.onfidoConfig.getTokenExpirationHandlerEnabled()) {
                    onTokenExpired();
                    return;
                } else {
                    onfidoApiServiceListener = this.listener;
                    errorType = ErrorType.TokenExpired.INSTANCE;
                }
            } else if (!StringsKt.equals(OnfidoApiService.GEOBLOCKED_ERROR, errorData.getError().getType(), true)) {
                this.listener.onError(errorCode, message, errorData);
                return;
            } else {
                onfidoApiServiceListener = this.listener;
                errorType = ErrorType.Geoblocked.INSTANCE;
            }
            onfidoApiServiceListener.onUploadError(errorType);
        }

        @Override // com.onfido.api.client.OnfidoAPI.Listener
        public void onFailure(Throwable t) {
            Intrinsics.checkNotNullParameter(t, "t");
            ErrorType errorTypeAsCertificateError = this.this$0.asCertificateError(t);
            if (errorTypeAsCertificateError == null) {
                errorTypeAsCertificateError = ErrorType.Network.INSTANCE;
            }
            this.listener.onUploadError(errorTypeAsCertificateError);
        }

        @Override // com.onfido.api.client.OnfidoAPI.Listener
        public void onSuccess(T t) {
            this.listener.onSuccess(t);
        }

        public abstract void onTokenRefreshed();
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b`\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00028\u0000H&¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;", ExifInterface.GPS_DIRECTION_TRUE, "", "onError", "", "errorCode", "", "message", "", "errorData", "Lcom/onfido/api/client/data/ErrorData;", "onSuccess", "uploadedDocument", "(Ljava/lang/Object;)V", "onUploadError", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface OnfidoApiServiceListener<T> {
        void onError(int errorCode, String message, ErrorData errorData);

        void onSuccess(T uploadedDocument);

        void onUploadError(ErrorType errorType);
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001Bg\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0013HÆ\u0003J}\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0013HÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00065"}, d2 = {"Lcom/onfido/android/sdk/capture/network/OnfidoApiService$PhotoUploadParams;", "", ReactNativeBridgeUtiles.KEY_FILE_NAME, "", "documentType", "Lcom/onfido/api/client/data/DocType;", ReactNativeBridgeUtiles.KEY_FILE_TYPE, "data", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;", "Lcom/onfido/api/client/data/DocumentUpload;", "validations", "", "Lcom/onfido/android/sdk/capture/validation/Validation;", ReactNativeBridgeUtiles.KEY_DOCUMENT_SIDE, "Lcom/onfido/api/client/data/InternalDocSide;", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "sdkUploadMetaData", "Lcom/onfido/api/client/data/SdkUploadMetaData;", "(Ljava/lang/String;Lcom/onfido/api/client/data/DocType;Ljava/lang/String;[BLcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;Ljava/util/List;Lcom/onfido/api/client/data/InternalDocSide;Ljava/lang/String;Lcom/onfido/api/client/data/SdkUploadMetaData;)V", "getData", "()[B", "getDocumentType", "()Lcom/onfido/api/client/data/DocType;", "getFileName", "()Ljava/lang/String;", "getFileType", "getIssuingCountry", "getListener", "()Lcom/onfido/android/sdk/capture/network/OnfidoApiService$OnfidoApiServiceListener;", "getSdkUploadMetaData", "()Lcom/onfido/api/client/data/SdkUploadMetaData;", "getSide", "()Lcom/onfido/api/client/data/InternalDocSide;", "getValidations", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class PhotoUploadParams {
        private final byte[] data;
        private final DocType documentType;
        private final String fileName;
        private final String fileType;
        private final String issuingCountry;
        private final OnfidoApiServiceListener<DocumentUpload> listener;
        private final SdkUploadMetaData sdkUploadMetaData;
        private final InternalDocSide side;
        private final List<Validation> validations;

        public PhotoUploadParams(String str, DocType docType, String str2, byte[] bArr, OnfidoApiServiceListener<DocumentUpload> listener, List<Validation> list, InternalDocSide internalDocSide, String str3, SdkUploadMetaData sdkUploadMetaData) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
            this.fileName = str;
            this.documentType = docType;
            this.fileType = str2;
            this.data = bArr;
            this.listener = listener;
            this.validations = list;
            this.side = internalDocSide;
            this.issuingCountry = str3;
            this.sdkUploadMetaData = sdkUploadMetaData;
        }

        /* renamed from: component1, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        /* renamed from: component2, reason: from getter */
        public final DocType getDocumentType() {
            return this.documentType;
        }

        /* renamed from: component3, reason: from getter */
        public final String getFileType() {
            return this.fileType;
        }

        /* renamed from: component4, reason: from getter */
        public final byte[] getData() {
            return this.data;
        }

        public final OnfidoApiServiceListener<DocumentUpload> component5() {
            return this.listener;
        }

        public final List<Validation> component6() {
            return this.validations;
        }

        /* renamed from: component7, reason: from getter */
        public final InternalDocSide getSide() {
            return this.side;
        }

        /* renamed from: component8, reason: from getter */
        public final String getIssuingCountry() {
            return this.issuingCountry;
        }

        /* renamed from: component9, reason: from getter */
        public final SdkUploadMetaData getSdkUploadMetaData() {
            return this.sdkUploadMetaData;
        }

        public final PhotoUploadParams copy(String fileName, DocType documentType, String fileType, byte[] data, OnfidoApiServiceListener<DocumentUpload> listener, List<Validation> validations, InternalDocSide side, String issuingCountry, SdkUploadMetaData sdkUploadMetaData) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
            return new PhotoUploadParams(fileName, documentType, fileType, data, listener, validations, side, issuingCountry, sdkUploadMetaData);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PhotoUploadParams)) {
                return false;
            }
            PhotoUploadParams photoUploadParams = (PhotoUploadParams) other;
            return Intrinsics.areEqual(this.fileName, photoUploadParams.fileName) && this.documentType == photoUploadParams.documentType && Intrinsics.areEqual(this.fileType, photoUploadParams.fileType) && Intrinsics.areEqual(this.data, photoUploadParams.data) && Intrinsics.areEqual(this.listener, photoUploadParams.listener) && Intrinsics.areEqual(this.validations, photoUploadParams.validations) && this.side == photoUploadParams.side && Intrinsics.areEqual(this.issuingCountry, photoUploadParams.issuingCountry) && Intrinsics.areEqual(this.sdkUploadMetaData, photoUploadParams.sdkUploadMetaData);
        }

        public final byte[] getData() {
            return this.data;
        }

        public final DocType getDocumentType() {
            return this.documentType;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final String getFileType() {
            return this.fileType;
        }

        public final String getIssuingCountry() {
            return this.issuingCountry;
        }

        public final OnfidoApiServiceListener<DocumentUpload> getListener() {
            return this.listener;
        }

        public final SdkUploadMetaData getSdkUploadMetaData() {
            return this.sdkUploadMetaData;
        }

        public final InternalDocSide getSide() {
            return this.side;
        }

        public final List<Validation> getValidations() {
            return this.validations;
        }

        public int hashCode() {
            String str = this.fileName;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            DocType docType = this.documentType;
            int iHashCode2 = (iHashCode + (docType == null ? 0 : docType.hashCode())) * 31;
            String str2 = this.fileType;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            byte[] bArr = this.data;
            int iHashCode4 = (((iHashCode3 + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31) + this.listener.hashCode()) * 31;
            List<Validation> list = this.validations;
            int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
            InternalDocSide internalDocSide = this.side;
            int iHashCode6 = (iHashCode5 + (internalDocSide == null ? 0 : internalDocSide.hashCode())) * 31;
            String str3 = this.issuingCountry;
            return ((iHashCode6 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.sdkUploadMetaData.hashCode();
        }

        public String toString() {
            return "PhotoUploadParams(fileName=" + this.fileName + ", documentType=" + this.documentType + ", fileType=" + this.fileType + ", data=" + Arrays.toString(this.data) + ", listener=" + this.listener + ", validations=" + this.validations + ", side=" + this.side + ", issuingCountry=" + this.issuingCountry + ", sdkUploadMetaData=" + this.sdkUploadMetaData + ')';
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/onfido/android/sdk/capture/network/OnfidoApiService$PoaDocumentUploadParams;", "", ReactNativeBridgeUtiles.KEY_FILE_NAME, "", "poaDocumentType", "Lcom/onfido/api/client/data/PoaDocumentType;", ReactNativeBridgeUtiles.KEY_FILE_TYPE, "data", "", ReactNativeBridgeUtiles.KEY_DOCUMENT_ISSUING_COUNTRY, "(Ljava/lang/String;Lcom/onfido/api/client/data/PoaDocumentType;Ljava/lang/String;[BLjava/lang/String;)V", "getData", "()[B", "getFileName", "()Ljava/lang/String;", "getFileType", "getIssuingCountry", "getPoaDocumentType", "()Lcom/onfido/api/client/data/PoaDocumentType;", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class PoaDocumentUploadParams {
        private final byte[] data;
        private final String fileName;
        private final String fileType;
        private final String issuingCountry;
        private final PoaDocumentType poaDocumentType;

        public PoaDocumentUploadParams(String str, PoaDocumentType poaDocumentType, String str2, byte[] bArr, String str3) {
            this.fileName = str;
            this.poaDocumentType = poaDocumentType;
            this.fileType = str2;
            this.data = bArr;
            this.issuingCountry = str3;
        }

        public static /* synthetic */ PoaDocumentUploadParams copy$default(PoaDocumentUploadParams poaDocumentUploadParams, String str, PoaDocumentType poaDocumentType, String str2, byte[] bArr, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = poaDocumentUploadParams.fileName;
            }
            if ((i & 2) != 0) {
                poaDocumentType = poaDocumentUploadParams.poaDocumentType;
            }
            PoaDocumentType poaDocumentType2 = poaDocumentType;
            if ((i & 4) != 0) {
                str2 = poaDocumentUploadParams.fileType;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                bArr = poaDocumentUploadParams.data;
            }
            byte[] bArr2 = bArr;
            if ((i & 16) != 0) {
                str3 = poaDocumentUploadParams.issuingCountry;
            }
            return poaDocumentUploadParams.copy(str, poaDocumentType2, str4, bArr2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        /* renamed from: component2, reason: from getter */
        public final PoaDocumentType getPoaDocumentType() {
            return this.poaDocumentType;
        }

        /* renamed from: component3, reason: from getter */
        public final String getFileType() {
            return this.fileType;
        }

        /* renamed from: component4, reason: from getter */
        public final byte[] getData() {
            return this.data;
        }

        /* renamed from: component5, reason: from getter */
        public final String getIssuingCountry() {
            return this.issuingCountry;
        }

        public final PoaDocumentUploadParams copy(String fileName, PoaDocumentType poaDocumentType, String fileType, byte[] data, String issuingCountry) {
            return new PoaDocumentUploadParams(fileName, poaDocumentType, fileType, data, issuingCountry);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PoaDocumentUploadParams)) {
                return false;
            }
            PoaDocumentUploadParams poaDocumentUploadParams = (PoaDocumentUploadParams) other;
            return Intrinsics.areEqual(this.fileName, poaDocumentUploadParams.fileName) && this.poaDocumentType == poaDocumentUploadParams.poaDocumentType && Intrinsics.areEqual(this.fileType, poaDocumentUploadParams.fileType) && Intrinsics.areEqual(this.data, poaDocumentUploadParams.data) && Intrinsics.areEqual(this.issuingCountry, poaDocumentUploadParams.issuingCountry);
        }

        public final byte[] getData() {
            return this.data;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final String getFileType() {
            return this.fileType;
        }

        public final String getIssuingCountry() {
            return this.issuingCountry;
        }

        public final PoaDocumentType getPoaDocumentType() {
            return this.poaDocumentType;
        }

        public int hashCode() {
            String str = this.fileName;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            PoaDocumentType poaDocumentType = this.poaDocumentType;
            int iHashCode2 = (iHashCode + (poaDocumentType == null ? 0 : poaDocumentType.hashCode())) * 31;
            String str2 = this.fileType;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            byte[] bArr = this.data;
            int iHashCode4 = (iHashCode3 + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
            String str3 = this.issuingCountry;
            return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "PoaDocumentUploadParams(fileName=" + this.fileName + ", poaDocumentType=" + this.poaDocumentType + ", fileType=" + this.fileType + ", data=" + Arrays.toString(this.data) + ", issuingCountry=" + this.issuingCountry + ')';
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001BW\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010 \u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0015J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tHÆ\u0003Jn\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010(\u001a\u00020)H\u0016J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/network/OnfidoApiService$VideoUploadParams;", "", ReactNativeBridgeUtiles.KEY_FILE_NAME, "", ReactNativeBridgeUtiles.KEY_FILE_TYPE, "data", "", "challengeId", "challengeList", "", "Lcom/onfido/api/client/data/LiveVideoChallenges$LiveVideoChallenge;", "challengeSwitchTimestamp", "", "languages", "Lcom/onfido/api/client/data/LiveVideoLanguage;", "(Ljava/lang/String;Ljava/lang/String;[BLjava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/util/List;)V", "getChallengeId", "()Ljava/lang/String;", "getChallengeList", "()Ljava/util/List;", "getChallengeSwitchTimestamp", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getData", "()[B", "getFileName", "getFileType", "getLanguages", "component1", "component2", "component3", "component4", "component5", "component6", "component7", Constants.COPY_TYPE, "(Ljava/lang/String;Ljava/lang/String;[BLjava/lang/String;Ljava/util/List;Ljava/lang/Long;Ljava/util/List;)Lcom/onfido/android/sdk/capture/network/OnfidoApiService$VideoUploadParams;", "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class VideoUploadParams {
        private final String challengeId;
        private final List<LiveVideoChallenges.LiveVideoChallenge> challengeList;
        private final Long challengeSwitchTimestamp;
        private final byte[] data;
        private final String fileName;
        private final String fileType;
        private final List<LiveVideoLanguage> languages;

        /* JADX WARN: Multi-variable type inference failed */
        public VideoUploadParams(String str, String str2, byte[] bArr, String str3, List<? extends LiveVideoChallenges.LiveVideoChallenge> list, Long l, List<LiveVideoLanguage> list2) {
            this.fileName = str;
            this.fileType = str2;
            this.data = bArr;
            this.challengeId = str3;
            this.challengeList = list;
            this.challengeSwitchTimestamp = l;
            this.languages = list2;
        }

        public static /* synthetic */ VideoUploadParams copy$default(VideoUploadParams videoUploadParams, String str, String str2, byte[] bArr, String str3, List list, Long l, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoUploadParams.fileName;
            }
            if ((i & 2) != 0) {
                str2 = videoUploadParams.fileType;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                bArr = videoUploadParams.data;
            }
            byte[] bArr2 = bArr;
            if ((i & 8) != 0) {
                str3 = videoUploadParams.challengeId;
            }
            String str5 = str3;
            if ((i & 16) != 0) {
                list = videoUploadParams.challengeList;
            }
            List list3 = list;
            if ((i & 32) != 0) {
                l = videoUploadParams.challengeSwitchTimestamp;
            }
            Long l2 = l;
            if ((i & 64) != 0) {
                list2 = videoUploadParams.languages;
            }
            return videoUploadParams.copy(str, str4, bArr2, str5, list3, l2, list2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        /* renamed from: component2, reason: from getter */
        public final String getFileType() {
            return this.fileType;
        }

        /* renamed from: component3, reason: from getter */
        public final byte[] getData() {
            return this.data;
        }

        /* renamed from: component4, reason: from getter */
        public final String getChallengeId() {
            return this.challengeId;
        }

        public final List<LiveVideoChallenges.LiveVideoChallenge> component5() {
            return this.challengeList;
        }

        /* renamed from: component6, reason: from getter */
        public final Long getChallengeSwitchTimestamp() {
            return this.challengeSwitchTimestamp;
        }

        public final List<LiveVideoLanguage> component7() {
            return this.languages;
        }

        public final VideoUploadParams copy(String fileName, String fileType, byte[] data, String challengeId, List<? extends LiveVideoChallenges.LiveVideoChallenge> challengeList, Long challengeSwitchTimestamp, List<LiveVideoLanguage> languages) {
            return new VideoUploadParams(fileName, fileType, data, challengeId, challengeList, challengeSwitchTimestamp, languages);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoUploadParams)) {
                return false;
            }
            VideoUploadParams videoUploadParams = (VideoUploadParams) other;
            if (!Intrinsics.areEqual(this.fileName, videoUploadParams.fileName) || !Intrinsics.areEqual(this.fileType, videoUploadParams.fileType)) {
                return false;
            }
            byte[] bArr = this.data;
            if (bArr != null) {
                byte[] bArr2 = videoUploadParams.data;
                if (bArr2 == null || !Arrays.equals(bArr, bArr2)) {
                    return false;
                }
            } else if (videoUploadParams.data != null) {
                return false;
            }
            return Intrinsics.areEqual(this.challengeId, videoUploadParams.challengeId) && Intrinsics.areEqual(this.challengeList, videoUploadParams.challengeList) && Intrinsics.areEqual(this.challengeSwitchTimestamp, videoUploadParams.challengeSwitchTimestamp) && Intrinsics.areEqual(this.languages, videoUploadParams.languages);
        }

        public final String getChallengeId() {
            return this.challengeId;
        }

        public final List<LiveVideoChallenges.LiveVideoChallenge> getChallengeList() {
            return this.challengeList;
        }

        public final Long getChallengeSwitchTimestamp() {
            return this.challengeSwitchTimestamp;
        }

        public final byte[] getData() {
            return this.data;
        }

        public final String getFileName() {
            return this.fileName;
        }

        public final String getFileType() {
            return this.fileType;
        }

        public final List<LiveVideoLanguage> getLanguages() {
            return this.languages;
        }

        public int hashCode() {
            String str = this.fileName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.fileType;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            byte[] bArr = this.data;
            int iHashCode3 = (iHashCode2 + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
            String str3 = this.challengeId;
            int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
            List<LiveVideoChallenges.LiveVideoChallenge> list = this.challengeList;
            int iHashCode5 = (iHashCode4 + (list != null ? list.hashCode() : 0)) * 31;
            Long l = this.challengeSwitchTimestamp;
            int iHashCode6 = (iHashCode5 + (l != null ? l.hashCode() : 0)) * 31;
            List<LiveVideoLanguage> list2 = this.languages;
            return iHashCode6 + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "VideoUploadParams(fileName=" + this.fileName + ", fileType=" + this.fileType + ", data=" + Arrays.toString(this.data) + ", challengeId=" + this.challengeId + ", challengeList=" + this.challengeList + ", challengeSwitchTimestamp=" + this.challengeSwitchTimestamp + ", languages=" + this.languages + ')';
        }
    }

    @Inject
    public OnfidoApiService(OnfidoAPI onfidoApi, IdentityInteractor identityInteractor, OnfidoConfig onfidoConfig, SdkUploadMetadataHelper sdkUploadMetadataHelper, TokenExpirationServiceConnector tokenExpirationServiceConnetor) {
        Intrinsics.checkNotNullParameter(onfidoApi, "onfidoApi");
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(sdkUploadMetadataHelper, "sdkUploadMetadataHelper");
        Intrinsics.checkNotNullParameter(tokenExpirationServiceConnetor, "tokenExpirationServiceConnetor");
        this.onfidoApi = onfidoApi;
        this.identityInteractor = identityInteractor;
        this.onfidoConfig = onfidoConfig;
        this.sdkUploadMetadataHelper = sdkUploadMetadataHelper;
        this.tokenExpirationServiceConnetor = tokenExpirationServiceConnetor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ErrorType.InvalidCertificate asCertificateError(Throwable th) {
        if (!(th instanceof SSLPeerUnverifiedException)) {
            return null;
        }
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage != null) {
            return new ErrorType.InvalidCertificate(localizedMessage);
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    private DeviceInfo deviceInfo() {
        return this.identityInteractor.getDeviceInfo$onfido_capture_sdk_core_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Single<T> retrySingleRequest(final Single<T> single) {
        Single<T> singleOnErrorResumeNext = single.onErrorResumeNext(new Function() { // from class: com.onfido.android.sdk.capture.network.OnfidoApiService.retrySingleRequest.1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SingleSource<? extends T> apply(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                return ((throwable instanceof TokenExpiredException) && OnfidoApiService.this.onfidoConfig.getTokenExpirationHandlerEnabled()) ? OnfidoApiService.this.tokenRefreshTask().andThen(OnfidoApiService.this.retrySingleRequest(single)) : Single.error(throwable);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleOnErrorResumeNext, "onErrorResumeNext(...)");
        return singleOnErrorResumeNext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable tokenRefreshTask() {
        return this.tokenExpirationServiceConnetor.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void upload$lambda$2$uploadDocument(OnfidoApiService onfidoApiService, PhotoUploadParams photoUploadParams, OnfidoApiListener<DocumentUpload> onfidoApiListener) {
        LinkedHashMap linkedHashMap;
        OnfidoAPI onfidoAPI = onfidoApiService.onfidoApi;
        String fileName = photoUploadParams.getFileName();
        DocType documentType = photoUploadParams.getDocumentType();
        String fileType = photoUploadParams.getFileType();
        byte[] data = photoUploadParams.getData();
        List<Validation> validations = photoUploadParams.getValidations();
        if (validations != null) {
            linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(validations, 10)), 16));
            for (Validation validation : validations) {
                linkedHashMap.put(ValidationType.fromId(validation.getType()), ValidationLevel.fromId(validation.getLevel()));
            }
        } else {
            linkedHashMap = null;
        }
        onfidoAPI.upload(fileName, documentType, fileType, data, onfidoApiListener, linkedHashMap, photoUploadParams.getSide(), photoUploadParams.getIssuingCountry(), photoUploadParams.getSdkUploadMetaData());
    }

    public static /* synthetic */ Single uploadDocument$onfido_capture_sdk_core_release$default(OnfidoApiService onfidoApiService, String str, String str2, DocumentMediaType documentMediaType, DocumentType documentType, byte[] bArr, SdkUploadMetaData sdkUploadMetaData, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: uploadDocument");
        }
        if ((i & 8) != 0) {
            documentType = null;
        }
        DocumentType documentType2 = documentType;
        if ((i & 32) != 0) {
            sdkUploadMetaData = onfidoApiService.sdkUploadMetadataHelper.create();
        }
        return onfidoApiService.uploadDocument$onfido_capture_sdk_core_release(str, str2, documentMediaType, documentType2, bArr, sdkUploadMetaData);
    }

    public static /* synthetic */ Single uploadDocumentMedia$onfido_capture_sdk_core_release$default(OnfidoApiService onfidoApiService, String str, String str2, DocumentMediaType documentMediaType, DocSide docSide, DocumentType documentType, List list, byte[] bArr, SdkUploadMetaData sdkUploadMetaData, int i, Object obj) {
        if (obj == null) {
            return onfidoApiService.uploadDocumentMedia$onfido_capture_sdk_core_release(str, str2, documentMediaType, (i & 8) != 0 ? null : docSide, (i & 16) != 0 ? null : documentType, (i & 32) != 0 ? null : list, bArr, (i & 128) != 0 ? onfidoApiService.sdkUploadMetadataHelper.create() : sdkUploadMetaData);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: uploadDocumentMedia");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadLivePhoto$uploadPhoto(OnfidoApiService onfidoApiService, String str, String str2, byte[] bArr, boolean z, PayloadIntegrity payloadIntegrity, OnfidoApiListener<LivePhotoUpload> onfidoApiListener) {
        onfidoApiService.onfidoApi.uploadLivePhoto(str, str2, bArr, z, onfidoApiListener, onfidoApiService.sdkUploadMetadataHelper.create(), payloadIntegrity);
    }

    public Single<NfcProperties> getNfcProperties$onfido_capture_sdk_core_release(List<String> documentIds, MRZDocument mrzDocument) {
        Intrinsics.checkNotNullParameter(documentIds, "documentIds");
        com.onfido.api.client.data.MRZDocument mRZDocument = null;
        if (mrzDocument != null) {
            DocumentType documentType = mrzDocument.getDocumentType();
            mRZDocument = new com.onfido.api.client.data.MRZDocument(documentType != null ? documentType.name() : null, mrzDocument.getCountryCode(), mrzDocument.getName(), mrzDocument.getSurname(), mrzDocument.getDocumentNumber(), mrzDocument.getNationality(), mrzDocument.getDateOfBirth(), mrzDocument.getDateOfIssue(), mrzDocument.getDateOfExpire(), mrzDocument.getSex());
        }
        Single<NfcProperties> nfcProperties = this.onfidoApi.getNfcProperties(documentIds, mRZDocument);
        Intrinsics.checkNotNullExpressionValue(nfcProperties, "getNfcProperties(...)");
        return retrySingleRequest(nfcProperties);
    }

    public Single<SdkConfiguration> getSDKConfig$onfido_capture_sdk_core_release() {
        Single<SdkConfiguration> sDKConfig = this.onfidoApi.getSDKConfig(deviceInfo());
        Intrinsics.checkNotNullExpressionValue(sDKConfig, "getSDKConfig(...)");
        return retrySingleRequest(sDKConfig);
    }

    public Single<SupportedDocuments> getSupportedDocuments$onfido_capture_sdk_core_release() {
        Single<SupportedDocuments> supportedDocuments = this.onfidoApi.getSupportedDocuments();
        Intrinsics.checkNotNullExpressionValue(supportedDocuments, "getSupportedDocuments(...)");
        return retrySingleRequest(supportedDocuments);
    }

    public Single<LiveVideoChallenges> liveVideoChallenges$onfido_capture_sdk_core_release() {
        Single<LiveVideoChallenges> liveVideoChallenges = this.onfidoApi.getLiveVideoChallenges();
        Intrinsics.checkNotNullExpressionValue(liveVideoChallenges, "getLiveVideoChallenges(...)");
        return retrySingleRequest(liveVideoChallenges);
    }

    public void upload$onfido_capture_sdk_core_release(final PhotoUploadParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        final OnfidoApiServiceListener<DocumentUpload> listener = params.getListener();
        upload$lambda$2$uploadDocument(this, params, new OnfidoApiListener<DocumentUpload>(listener) { // from class: com.onfido.android.sdk.capture.network.OnfidoApiService$upload$1$documentUploadListener$1
            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiListener
            public void onTokenRefreshed() {
                OnfidoApiService.upload$lambda$2$uploadDocument(this.this$0, params, this);
            }
        });
    }

    public Single<DocumentMediaUploadResponse> uploadDocument$onfido_capture_sdk_core_release(String fileName, String fileType, DocumentMediaType mediaType, DocumentType docType, byte[] data, SdkUploadMetaData sdkUploadMetaData) {
        DocType docType2;
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(fileType, "fileType");
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        OnfidoAPI onfidoAPI = this.onfidoApi;
        if (docType == null || (docType2 = OnfidoExtensionsKt.toDocType(docType)) == null) {
            docType2 = DocType.UNKNOWN;
        }
        Single map = onfidoAPI.uploadSingle(fileName, docType2, fileType, mediaType.name(), data, sdkUploadMetaData).map(new Function() { // from class: com.onfido.android.sdk.capture.network.OnfidoApiService$uploadDocument$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final DocumentMediaUploadResponse apply(DocumentUpload result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return new DocumentMediaUploadResponse(new DocumentBinaryMedia(result.getId()), (DocumentValidationWarningsBundle) null, (List) null, (String) null, (DocumentFeatures) null, 30, (DefaultConstructorMarker) null);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    public Single<DocumentMediaUploadResponse> uploadDocumentMedia$onfido_capture_sdk_core_release(String fileName, String fileType, DocumentMediaType mediaType, DocSide docSide, DocumentType docType, List<Validation> validations, byte[] data, SdkUploadMetaData sdkUploadMetaData) {
        DocType docType2;
        LinkedHashMap linkedHashMap;
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        if (docType == null || (docType2 = OnfidoExtensionsKt.toDocType(docType)) == null) {
            docType2 = DocType.UNKNOWN;
        }
        String id = docType2.getId();
        OnfidoAPI onfidoAPI = this.onfidoApi;
        String strName = mediaType.name();
        String id2 = docSide != null ? docSide.getId() : null;
        if (validations != null) {
            linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(validations, 10)), 16));
            for (Validation validation : validations) {
                linkedHashMap.put(ValidationType.fromId(validation.getType()), ValidationLevel.fromId(validation.getLevel()));
            }
        } else {
            linkedHashMap = null;
        }
        Single<DocumentMediaUploadResponse> singleUploadDocumentMedia = onfidoAPI.uploadDocumentMedia(fileName, fileType, strName, id2, id, linkedHashMap, data, sdkUploadMetaData);
        Intrinsics.checkNotNullExpressionValue(singleUploadDocumentMedia, "uploadDocumentMedia(...)");
        return singleUploadDocumentMedia;
    }

    public Single<String> uploadDocumentVideo$onfido_capture_sdk_core_release(String documentId, String videoPath, SdkUploadMetaData sdkUploadMetaData, DocumentMediaIntegrity documentMediaIntegrity, InternalDocSide docSide, DocType docType, String issuingCountry) {
        Intrinsics.checkNotNullParameter(documentId, "documentId");
        Intrinsics.checkNotNullParameter(videoPath, "videoPath");
        Intrinsics.checkNotNullParameter(sdkUploadMetaData, "sdkUploadMetaData");
        Intrinsics.checkNotNullParameter(documentMediaIntegrity, "documentMediaIntegrity");
        Intrinsics.checkNotNullParameter(docSide, "docSide");
        Intrinsics.checkNotNullParameter(docType, "docType");
        Single<String> singleUploadDocumentVideo = this.onfidoApi.uploadDocumentVideo(documentId, videoPath, sdkUploadMetaData, documentMediaIntegrity, docSide, docType, issuingCountry);
        Intrinsics.checkNotNullExpressionValue(singleUploadDocumentVideo, "uploadDocumentVideo(...)");
        return singleUploadDocumentVideo;
    }

    public void uploadLivePhoto$onfido_capture_sdk_core_release(final String fileName, final String fileType, final byte[] data, final boolean advancedValidation, final PayloadIntegrity payloadIntegrity, final OnfidoApiServiceListener<LivePhotoUpload> listener) {
        Intrinsics.checkNotNullParameter(payloadIntegrity, "payloadIntegrity");
        Intrinsics.checkNotNullParameter(listener, "listener");
        uploadLivePhoto$uploadPhoto(this, fileName, fileType, data, advancedValidation, payloadIntegrity, new OnfidoApiListener<LivePhotoUpload>(listener) { // from class: com.onfido.android.sdk.capture.network.OnfidoApiService$uploadLivePhoto$photoUploadListener$1
            @Override // com.onfido.android.sdk.capture.network.OnfidoApiService.OnfidoApiListener
            public void onTokenRefreshed() {
                OnfidoApiService.uploadLivePhoto$uploadPhoto(this.this$0, fileName, fileType, data, advancedValidation, payloadIntegrity, this);
            }
        });
    }

    public Observable<LiveVideoUpload> uploadLiveVideo$onfido_capture_sdk_core_release(VideoUploadParams params, PayloadIntegrity payloadIntegrity) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(payloadIntegrity, "payloadIntegrity");
        Observable<LiveVideoUpload> observable = this.onfidoApi.uploadLiveVideo(params.getFileName(), params.getFileType(), params.getData(), params.getChallengeId(), params.getChallengeList(), params.getChallengeSwitchTimestamp(), params.getLanguages(), this.sdkUploadMetadataHelper.create(), payloadIntegrity).toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "toObservable(...)");
        return observable;
    }

    public Single<PoaDocumentUpload> uploadPoaDocument$onfido_capture_sdk_core_release(PoaDocumentUploadParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        Single<PoaDocumentUpload> singlePoaUpload = this.onfidoApi.poaUpload(params.getFileName(), params.getPoaDocumentType(), params.getFileType(), params.getData(), params.getIssuingCountry(), this.sdkUploadMetadataHelper.create());
        Intrinsics.checkNotNullExpressionValue(singlePoaUpload, "with(...)");
        return singlePoaUpload;
    }
}
