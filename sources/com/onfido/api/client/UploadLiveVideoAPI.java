package com.onfido.api.client;

import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.SdkUploadMetaData;
import io.reactivex.rxjava3.core.Single;
import java.util.List;

/* loaded from: classes6.dex */
class UploadLiveVideoAPI {
    private final OnfidoService onfidoService;

    UploadLiveVideoAPI(OnfidoService onfidoService) {
        this.onfidoService = onfidoService;
    }

    Single<LiveVideoUpload> upload(String str, String str2, byte[] bArr, String str3, List<LiveVideoChallenges.LiveVideoChallenge> list, long j, List<LiveVideoLanguage> list2, SdkUploadMetaData sdkUploadMetaData, String str4, String str5, PayloadIntegrity payloadIntegrity) {
        return this.onfidoService.uploadLiveVideo(new MultipartLiveVideoRequestBuilder(str4, str5).setMultipartRequestBody(str, str2, bArr, str3, list, j, list2, sdkUploadMetaData, payloadIntegrity).build());
    }
}
