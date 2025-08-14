package com.onfido.android.sdk.capture.internal.analytics.inhouse.network;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/OnfidoAssetsApi;", "", "getModel", "Lokhttp3/ResponseBody;", "modelName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface OnfidoAssetsApi {
    @GET("{modelName}")
    Object getModel(@Path("modelName") String str, Continuation<? super ResponseBody> continuation);
}
