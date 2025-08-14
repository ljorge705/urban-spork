package com.onfido.api.client;

import com.onfido.api.client.data.LivePhotoUpload;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.SdkUploadMetaData;
import retrofit2.Call;

/* loaded from: classes6.dex */
class UploadLivePhotoAPI {
    private final OnfidoService onfidoService;

    UploadLivePhotoAPI(OnfidoService onfidoService) {
        this.onfidoService = onfidoService;
    }

    Call<LivePhotoUpload> upload(String str, String str2, boolean z, byte[] bArr, SdkUploadMetaData sdkUploadMetaData, String str3, String str4, PayloadIntegrity payloadIntegrity) {
        return this.onfidoService.uploadLivePhoto(new MultipartLivePhotoRequestBuilder(str3, str4).setMultipartRequestBody(str, str2, z, bArr, sdkUploadMetaData, payloadIntegrity).build());
    }
}
