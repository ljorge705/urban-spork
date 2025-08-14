package com.onfido.api.client;

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
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.Map;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;

/* loaded from: classes6.dex */
public interface OnfidoAPI {

    public interface Listener<T> {
        void onError(int i, String str, ErrorData errorData);

        void onFailure(Throwable th);

        void onSuccess(T t);
    }

    Single<LiveVideoChallenges> getLiveVideoChallenges();

    Single<NfcProperties> getNfcProperties(List<String> list, MRZDocument mRZDocument);

    Single<SdkConfiguration> getSDKConfig(DeviceInfo deviceInfo);

    Single<SupportedDocuments> getSupportedDocuments();

    Single<PoaDocumentUpload> poaUpload(String str, PoaDocumentType poaDocumentType, String str2, byte[] bArr, String str3, SdkUploadMetaData sdkUploadMetaData);

    void upload(String str, DocType docType, String str2, byte[] bArr, Listener<DocumentUpload> listener, Map<ValidationType, ValidationLevel> map, InternalDocSide internalDocSide, String str3, SdkUploadMetaData sdkUploadMetaData);

    Single<DocumentMediaUploadResponse> uploadDocumentMedia(String str, String str2, String str3, String str4, String str5, Map<ValidationType, ValidationLevel> map, byte[] bArr, SdkUploadMetaData sdkUploadMetaData);

    Single<String> uploadDocumentVideo(String str, String str2, SdkUploadMetaData sdkUploadMetaData, DocumentMediaIntegrity documentMediaIntegrity, InternalDocSide internalDocSide, DocType docType, String str3);

    void uploadLivePhoto(String str, String str2, byte[] bArr, boolean z, Listener<LivePhotoUpload> listener, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity);

    Single<LiveVideoUpload> uploadLiveVideo(String str, String str2, byte[] bArr, String str3, List<LiveVideoChallenges.LiveVideoChallenge> list, Long l, List<LiveVideoLanguage> list2, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity);

    Single<DocumentUpload> uploadSingle(String str, DocType docType, String str2, String str3, byte[] bArr, SdkUploadMetaData sdkUploadMetaData);

    public static class Callback<T> implements retrofit2.Callback<T> {
        static final String TAG = "OnfidoAPI." + Callback.class.getSimpleName();
        private final ErrorParser errorParser;
        private final Listener<T> listener;

        public Callback(Listener<T> listener, ErrorParser errorParser) {
            this.listener = listener;
            this.errorParser = errorParser;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<T> call, Throwable th) {
            this.listener.onFailure(th);
            HttpLoggingInterceptor.Logger.DEFAULT.log(TAG + "/onFailure:" + th);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<T> call, Response<T> response) {
            if (!response.isSuccessful()) {
                this.listener.onError(response.code(), response.message(), this.errorParser.parseErrorFrom(response));
            } else {
                this.listener.onSuccess(response.body());
            }
        }
    }
}
