package com.onfido.android.sdk.capture.ui.proofOfAddress.network;

import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.api.client.data.PoaDocumentUpload;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0017\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0010¢\u0006\u0002\b\u000bJ\u001b\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/PoaRepository;", "", "proofOfAddressApi", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/ProofOfAddressApi;", "onfidoApiService", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "(Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/ProofOfAddressApi;Lcom/onfido/android/sdk/capture/network/OnfidoApiService;)V", "getPoaSupportedCountries", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/PoaResponseItem;", "getPoaSupportedCountries$onfido_capture_sdk_core_release", "uploadPoaDocument", "Lcom/onfido/api/client/data/PoaDocumentUpload;", "uploadParams", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService$PoaDocumentUploadParams;", "uploadPoaDocument$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class PoaRepository {
    private final OnfidoApiService onfidoApiService;
    private final ProofOfAddressApi proofOfAddressApi;

    @Inject
    public PoaRepository(ProofOfAddressApi proofOfAddressApi, OnfidoApiService onfidoApiService) {
        Intrinsics.checkNotNullParameter(proofOfAddressApi, "proofOfAddressApi");
        Intrinsics.checkNotNullParameter(onfidoApiService, "onfidoApiService");
        this.proofOfAddressApi = proofOfAddressApi;
        this.onfidoApiService = onfidoApiService;
    }

    public Single<List<PoaResponseItem>> getPoaSupportedCountries$onfido_capture_sdk_core_release() {
        return this.proofOfAddressApi.getSupportedCountries();
    }

    public Single<PoaDocumentUpload> uploadPoaDocument$onfido_capture_sdk_core_release(OnfidoApiService.PoaDocumentUploadParams uploadParams) {
        Intrinsics.checkNotNullParameter(uploadParams, "uploadParams");
        return this.onfidoApiService.uploadPoaDocument$onfido_capture_sdk_core_release(uploadParams);
    }
}
