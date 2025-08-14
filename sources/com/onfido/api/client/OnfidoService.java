package com.onfido.api.client;

import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.api.client.data.LiveVideoUpload;
import com.onfido.api.client.data.NfcProperties;
import com.onfido.api.client.data.NfcPropertiesRequest;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.data.SdkConfigurationRequestBody;
import com.onfido.api.client.data.SupportedDocuments;
import com.onfido.api.client.interceptor.API;
import io.reactivex.rxjava3.core.Single;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/* loaded from: classes6.dex */
interface OnfidoService {
    @API(version = "v3.6")
    @POST("live_video_challenge")
    Single<LiveVideoChallenges> getLiveVideoChallenges();

    @API(version = "v4")
    @POST("nfc_properties")
    Single<NfcProperties> getNfcProperties(@Body NfcPropertiesRequest nfcPropertiesRequest);

    @API(version = "v3.5")
    @POST("sdk/configurations")
    Single<SdkConfiguration> getSdkConfiguration(@Body SdkConfigurationRequestBody sdkConfigurationRequestBody);

    @API(version = "v3.6")
    @GET("sdk/documents")
    Single<SupportedDocuments> getSupportedDocuments();

    @API(version = "v3.6")
    @POST("documents")
    Call<DocumentUpload> upload(@Body RequestBody requestBody);

    @API(version = "v3.6")
    @POST("documents")
    Single<DocumentUpload> uploadDocumentVideo(@Header("x-onfido-sdk-media-signature") String str, @Header("x-onfido-sdk-client-nonce") String str2, @Body RequestBody requestBody);

    @API(version = "v3.6")
    @POST("live_photos")
    Call<LivePhotoUpload> uploadLivePhoto(@Body RequestBody requestBody);

    @API(version = "v3.6")
    @POST("live_videos")
    Single<LiveVideoUpload> uploadLiveVideo(@Body RequestBody requestBody);

    @API(version = "v4")
    @POST("documents/media")
    Single<DocumentMediaUploadResponse> uploadMediaFile(@Header("X-Video-Auth") String str, @Body RequestBody requestBody);

    @API(version = "v3.6")
    @POST("documents")
    Single<PoaDocumentUpload> uploadPoa(@Body RequestBody requestBody);

    @API(version = "v3.6")
    @POST("documents")
    Single<DocumentUpload> uploadSingle(@Body RequestBody requestBody);
}
