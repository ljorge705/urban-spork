package com.onfido.api.client;

import com.onfido.api.client.data.PayloadIntegrity;
import com.onfido.api.client.data.SdkUploadMetaData;
import okhttp3.MultipartBody;

/* loaded from: classes6.dex */
class MultipartLivePhotoRequestBuilder extends MultiPartRequestBuilder {
    private static final String VALIDATE = "advanced_validation";

    MultipartLivePhotoRequestBuilder(String str, String str2) {
        super(str, str2);
    }

    public MultipartBody.Builder setMultipartRequestBody(String str, String str2, boolean z, byte[] bArr, SdkUploadMetaData sdkUploadMetaData, PayloadIntegrity payloadIntegrity) {
        setFile(str, str2, bArr);
        setValidate(z);
        setSdkMetadata(sdkUploadMetaData);
        if (payloadIntegrity != null && !payloadIntegrity.isEmpty()) {
            setSignature(payloadIntegrity.getSignatureBase64());
            setClientNonce(payloadIntegrity.getClientNonce());
        }
        return super.getBuilder();
    }

    private void setValidate(boolean z) {
        setFormData(VALIDATE, String.valueOf(z));
    }
}
