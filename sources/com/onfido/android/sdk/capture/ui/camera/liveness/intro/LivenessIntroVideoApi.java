package com.onfido.android.sdk.capture.ui.camera.liveness.intro;

import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\u0006H'¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoApi;", "", "getLivenessIntroVideoPaths", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoIndexResponse;", "indexUrl", "", "getLivenessVideo", "Lokhttp3/ResponseBody;", "fileUrl", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface LivenessIntroVideoApi {
    @GET
    Single<LivenessIntroVideoIndexResponse> getLivenessIntroVideoPaths(@Url String indexUrl);

    @GET
    Single<ResponseBody> getLivenessVideo(@Url String fileUrl);
}
