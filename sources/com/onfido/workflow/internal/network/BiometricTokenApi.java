package com.onfido.workflow.internal.network;

import com.onfido.api.client.interceptor.API;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* compiled from: BiometricTokenApi.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0007"}, d2 = {"Lcom/onfido/workflow/internal/network/BiometricTokenApi;", "", "encrypt", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/workflow/internal/network/BiometricTokenEncryptionResponse;", "encryptionRequest", "Lcom/onfido/workflow/internal/network/BiometricTokenEncryptionRequest;", "onfido-workflow_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface BiometricTokenApi {
    @API(version = "v3.6")
    @POST("biometrics/media/encrypt")
    Single<BiometricTokenEncryptionResponse> encrypt(@Body BiometricTokenEncryptionRequest encryptionRequest);
}
