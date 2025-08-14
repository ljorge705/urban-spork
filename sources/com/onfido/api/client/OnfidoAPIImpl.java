package com.onfido.api.client;

import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.data.DeviceInfo;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentMediaIntegrity;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.ErrorData;
import com.onfido.api.client.data.InternalDocSide;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import com.onfido.api.client.data.MRZDocument;
import com.onfido.api.client.data.NfcProperties;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.data.SupportedDocuments;
import com.onfido.api.client.exception.GeoblockedException;
import com.onfido.api.client.exception.HttpParsedException;
import com.onfido.api.client.exception.RequestEntityTooLargeException;
import com.onfido.api.client.exception.TokenExpiredException;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import java.util.Map;
import retrofit2.HttpException;

/* loaded from: classes6.dex */
class OnfidoAPIImpl implements OnfidoAPI {
    private static final String AUTHORIZATION_ERROR = "authorization_error";
    private static final String GEOBLOCKED_ERROR = "geoblocked_request";
    private static final String REQUEST_ENTITY_TOO_LARGE_ERROR = "Request Entity Too Large";
    private static final String TOKEN_EXPIRED = "expired_token";
    private final ErrorParser errorParser;
    private final UploadLivePhotoAPI livePhotoAPI;
    private final UploadLiveVideoAPI liveVideoAPI;
    private final LiveVideoChallengeAPI liveVideoChallengeAPI;
    private final NfcPropertiesAPI nfcPropertiesAPI;
    private final SdkConfigurationAPI sdkConfigAPI;
    private final String sdkSource;
    private final String sdkVersion;
    private final SupportedDocumentsAPI supportedDocumentsAPI;
    private final UploadDocumentAPI uploadDocumentAPI;

    OnfidoAPIImpl(UploadDocumentAPI uploadDocumentAPI, UploadLivePhotoAPI uploadLivePhotoAPI, UploadLiveVideoAPI uploadLiveVideoAPI, LiveVideoChallengeAPI liveVideoChallengeAPI, SdkConfigurationAPI sdkConfigurationAPI, NfcPropertiesAPI nfcPropertiesAPI, ErrorParser errorParser, SupportedDocumentsAPI supportedDocumentsAPI, String str, String str2) {
        this.uploadDocumentAPI = uploadDocumentAPI;
        this.livePhotoAPI = uploadLivePhotoAPI;
        this.liveVideoAPI = uploadLiveVideoAPI;
        this.liveVideoChallengeAPI = liveVideoChallengeAPI;
        this.errorParser = errorParser;
        this.sdkConfigAPI = sdkConfigurationAPI;
        this.nfcPropertiesAPI = nfcPropertiesAPI;
        this.supportedDocumentsAPI = supportedDocumentsAPI;
        this.sdkSource = str;
        this.sdkVersion = str2;
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public void upload(String str, DocType docType, String str2, byte[] bArr, OnfidoAPI.Listener<DocumentUpload> listener, Map<ValidationType, ValidationLevel> map, InternalDocSide internalDocSide, String str3, SdkUploadMetaData sdkUploadMetaData) {
        this.uploadDocumentAPI.upload(str, docType, str2, bArr, map, internalDocSide, str3, sdkUploadMetaData, this.sdkSource, this.sdkVersion).enqueue(new OnfidoAPI.Callback(listener, this.errorParser));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<DocumentUpload> uploadSingle(String str, DocType docType, String str2, String str3, byte[] bArr, SdkUploadMetaData sdkUploadMetaData) {
        return requestWrapper(this.uploadDocumentAPI.uploadSingle(str, docType, str2, bArr, str3, sdkUploadMetaData, this.sdkSource, this.sdkVersion));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<PoaDocumentUpload> poaUpload(String str, PoaDocumentType poaDocumentType, String str2, byte[] bArr, String str3, SdkUploadMetaData sdkUploadMetaData) {
        return requestWrapper(this.uploadDocumentAPI.poaUpload(str, poaDocumentType, str2, bArr, str3, sdkUploadMetaData, this.sdkSource, this.sdkVersion));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<String> uploadDocumentVideo(String str, String str2, SdkUploadMetaData sdkUploadMetaData, DocumentMediaIntegrity documentMediaIntegrity, InternalDocSide internalDocSide, DocType docType, String str3) {
        return requestWrapper(this.uploadDocumentAPI.uploadVideo(str, str2, sdkUploadMetaData, this.sdkSource, this.sdkVersion, documentMediaIntegrity, internalDocSide, docType, str3));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<DocumentMediaUploadResponse> uploadDocumentMedia(String str, String str2, String str3, String str4, String str5, Map<ValidationType, ValidationLevel> map, byte[] bArr, SdkUploadMetaData sdkUploadMetaData) {
        return requestWrapper(this.uploadDocumentAPI.uploadDocumentMedia(str, str2, str3, str4, str5, map, bArr, sdkUploadMetaData, this.sdkSource, this.sdkVersion));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public void uploadLivePhoto(String str, String str2, byte[] bArr, boolean z, OnfidoAPI.Listener<LivePhotoUpload> listener, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity) {
        this.livePhotoAPI.upload(str, str2, z, bArr, sdkUploadMetaData, this.sdkSource, this.sdkVersion, payloadIntegrity).enqueue(new OnfidoAPI.Callback(listener, this.errorParser));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<LiveVideoUpload> uploadLiveVideo(String str, String str2, byte[] bArr, String str3, List<LiveVideoChallenges.LiveVideoChallenge> list, Long l, List<LiveVideoLanguage> list2, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity) {
        return requestWrapper(this.liveVideoAPI.upload(str, str2, bArr, str3, list, l.longValue(), list2, sdkUploadMetaData, this.sdkSource, this.sdkVersion, payloadIntegrity));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<LiveVideoChallenges> getLiveVideoChallenges() {
        return requestWrapper(this.liveVideoChallengeAPI.get());
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<SdkConfiguration> getSDKConfig(DeviceInfo deviceInfo) {
        return requestWrapper(this.sdkConfigAPI.getConfiguration(this.sdkSource, this.sdkVersion, deviceInfo));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<NfcProperties> getNfcProperties(List<String> list, MRZDocument mRZDocument) {
        return requestWrapper(this.nfcPropertiesAPI.get(list, mRZDocument));
    }

    @Override // com.onfido.api.client.OnfidoAPI
    public Single<SupportedDocuments> getSupportedDocuments() {
        return requestWrapper(this.supportedDocumentsAPI.getSupportedDocuments());
    }

    private Completable requestWrapper(Completable completable) {
        return requestWrapper(completable.toSingleDefault("")).ignoreElement();
    }

    private <T> Single<T> requestWrapper(Single<T> single) {
        return (Single<T>) single.compose(handleIdentifyingPossibleExceptions());
    }

    private boolean isTokenExpirationError(ErrorData errorData) {
        return (errorData == null || errorData.getError() == null || (!TOKEN_EXPIRED.equals(errorData.getError().getType()) && !AUTHORIZATION_ERROR.equals(errorData.getError().getType()))) ? false : true;
    }

    private boolean isGeolocationError(HttpException httpException, ErrorData errorData) {
        return errorData != null && errorData.getError() != null && httpException.code() == 403 && errorData.getError().getType().equals(GEOBLOCKED_ERROR);
    }

    private boolean isRequestEntityTooLargeError(HttpException httpException) {
        return httpException.code() == 413 && httpException.message().equals(REQUEST_ENTITY_TOO_LARGE_ERROR);
    }

    <T> SingleTransformer<T, T> handleIdentifyingPossibleExceptions() {
        return new SingleTransformer() { // from class: com.onfido.api.client.OnfidoAPIImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.SingleTransformer
            public final SingleSource apply(Single single) {
                return this.f$0.m5747x9203ff01(single);
            }
        };
    }

    /* renamed from: lambda$handleIdentifyingPossibleExceptions$0$com-onfido-api-client-OnfidoAPIImpl, reason: not valid java name */
    /* synthetic */ SingleSource m5746x7e5c2b80(Throwable th) throws Throwable {
        return Single.error(identifyError(th));
    }

    /* renamed from: lambda$handleIdentifyingPossibleExceptions$1$com-onfido-api-client-OnfidoAPIImpl, reason: not valid java name */
    /* synthetic */ SingleSource m5747x9203ff01(Single single) {
        return single.onErrorResumeNext(new Function() { // from class: com.onfido.api.client.OnfidoAPIImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.m5746x7e5c2b80((Throwable) obj);
            }
        });
    }

    private Throwable identifyError(Throwable th) {
        if (!(th instanceof HttpException)) {
            return th;
        }
        HttpException httpException = (HttpException) th;
        ErrorData errorFrom = this.errorParser.parseErrorFrom(httpException.response());
        if (isTokenExpirationError(errorFrom)) {
            return new TokenExpiredException();
        }
        if (isGeolocationError(httpException, errorFrom)) {
            return new GeoblockedException();
        }
        if (isRequestEntityTooLargeError(httpException)) {
            return new RequestEntityTooLargeException();
        }
        return new HttpParsedException(errorFrom);
    }
}
