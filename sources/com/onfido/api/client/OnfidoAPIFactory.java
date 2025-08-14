package com.onfido.api.client;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.onfido.api.client.ErrorParserImpl;
import com.onfido.api.client.demo.OnfidoDemoAPIImpl;
import com.onfido.api.client.token.TokenProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;

/* compiled from: OnfidoAPIFactory.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rH\u0007¨\u0006\u000e"}, d2 = {"Lcom/onfido/api/client/OnfidoAPIFactory;", "", "()V", "create", "Lcom/onfido/api/client/OnfidoAPI;", "tokenProvider", "Lcom/onfido/api/client/token/TokenProvider;", "onfidoFetcher", "Lcom/onfido/api/client/OnfidoFetcher;", "sdkSource", "", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "json", "Lkotlinx/serialization/json/Json;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class OnfidoAPIFactory {
    public static final OnfidoAPIFactory INSTANCE = new OnfidoAPIFactory();

    private OnfidoAPIFactory() {
    }

    @JvmStatic
    public static final OnfidoAPI create(TokenProvider tokenProvider, OnfidoFetcher onfidoFetcher, String sdkSource, String sdkVersion, Json json) {
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        Intrinsics.checkNotNullParameter(json, "json");
        if (tokenProvider.provideToken().getIsDemoToken()) {
            return new OnfidoDemoAPIImpl(json);
        }
        OnfidoService onfidoServiceApplicants$onfido_api_client = onfidoFetcher.applicants$onfido_api_client();
        return new OnfidoAPIImpl(new UploadDocumentAPI(tokenProvider, onfidoServiceApplicants$onfido_api_client), new UploadLivePhotoAPI(onfidoServiceApplicants$onfido_api_client), new UploadLiveVideoAPI(onfidoServiceApplicants$onfido_api_client), new LiveVideoChallengeAPI(onfidoServiceApplicants$onfido_api_client), new SdkConfigurationAPI(onfidoServiceApplicants$onfido_api_client), new NfcPropertiesAPI(onfidoServiceApplicants$onfido_api_client), ErrorParserImpl.Companion.newInstance$default(ErrorParserImpl.INSTANCE, null, 1, null), new SupportedDocumentsAPI(onfidoServiceApplicants$onfido_api_client), sdkSource, sdkVersion);
    }
}
