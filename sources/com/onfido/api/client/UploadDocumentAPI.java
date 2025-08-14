package com.onfido.api.client;

import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentMediaIntegrity;
import com.onfido.api.client.data.DocumentMediaUploadResponse;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.InternalDocSide;
import com.onfido.api.client.data.PoaDocumentType;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.api.client.token.InternalToken;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.api.client.token.sdk.InternalSDKToken;
import com.onfido.api.client.util.HashUtil;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.util.Map;
import okhttp3.MultipartBody;
import retrofit2.Call;

/* loaded from: classes6.dex */
class UploadDocumentAPI {
    private final OnfidoService onfidoService;
    private final TokenProvider tokenProvider;

    UploadDocumentAPI(TokenProvider tokenProvider, OnfidoService onfidoService) {
        this.tokenProvider = tokenProvider;
        this.onfidoService = onfidoService;
    }

    Call<DocumentUpload> upload(String str, DocType docType, String str2, byte[] bArr, Map<ValidationType, ValidationLevel> map, InternalDocSide internalDocSide, String str3, SdkUploadMetaData sdkUploadMetaData, String str4, String str5) {
        return this.onfidoService.upload(new MultipartDocumentRequestBuilder(str4, str5).setMultipartRequestBody(str, docType, str2, bArr, map, internalDocSide, str3, sdkUploadMetaData, null).build());
    }

    Single<DocumentUpload> uploadSingle(String str, DocType docType, String str2, byte[] bArr, String str3, SdkUploadMetaData sdkUploadMetaData, String str4, String str5) {
        return this.onfidoService.uploadSingle(new MultipartDocumentRequestBuilder(str4, str5).setMultipartRequestBody(str, docType, str2, bArr, null, null, null, sdkUploadMetaData, str3).build());
    }

    Single<PoaDocumentUpload> poaUpload(String str, PoaDocumentType poaDocumentType, String str2, byte[] bArr, String str3, SdkUploadMetaData sdkUploadMetaData, String str4, String str5) {
        return this.onfidoService.uploadPoa(new MultipartDocumentRequestBuilder(str4, str5).setPoaMultipartRequestBody(str, poaDocumentType, str2, bArr, str3, sdkUploadMetaData).build());
    }

    Single<String> uploadVideo(String str, String str2, SdkUploadMetaData sdkUploadMetaData, String str3, String str4, DocumentMediaIntegrity documentMediaIntegrity, InternalDocSide internalDocSide, DocType docType, String str5) {
        String signatureBase64;
        UploadDocumentAPI uploadDocumentAPI;
        String clientNonce;
        MultipartBody multipartBodyBuild = new MultipartDocumentRequestBuilder(str3, str4).setMultipartRequestBody(str, str2, sdkUploadMetaData, internalDocSide, docType, str5).build();
        if (documentMediaIntegrity != null) {
            signatureBase64 = documentMediaIntegrity.getSignatureBase64();
            clientNonce = documentMediaIntegrity.getClientNonce();
            uploadDocumentAPI = this;
        } else {
            signatureBase64 = null;
            uploadDocumentAPI = this;
            clientNonce = null;
        }
        return uploadDocumentAPI.onfidoService.uploadDocumentVideo(signatureBase64, clientNonce, multipartBodyBuild).map(new Function() { // from class: com.onfido.api.client.UploadDocumentAPI$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return ((DocumentUpload) obj).getId();
            }
        });
    }

    public Single<DocumentMediaUploadResponse> uploadDocumentMedia(String str, String str2, String str3, String str4, String str5, Map<ValidationType, ValidationLevel> map, byte[] bArr, SdkUploadMetaData sdkUploadMetaData, String str6, String str7) {
        return this.onfidoService.uploadMediaFile(HashUtil.sha256(bArr, getUuid()), new DocumentMediaRequestBuilder(str6, str7).setMultipartRequestBody(str, str2, bArr, str3, str4, str5, map, sdkUploadMetaData).build());
    }

    private String getUuid() {
        InternalToken internalTokenProvideToken = this.tokenProvider.provideToken();
        return internalTokenProvideToken instanceof InternalSDKToken ? ((InternalSDKToken) internalTokenProvideToken).getUuid() : "";
    }
}
