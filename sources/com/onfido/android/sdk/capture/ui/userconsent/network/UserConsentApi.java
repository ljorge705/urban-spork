package com.onfido.android.sdk.capture.ui.userconsent.network;

import com.onfido.api.client.interceptor.API;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.sentry.protocol.DebugImage;
import java.util.List;
import kotlin.Metadata;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H'J\u001e\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\n2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\f\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H'¨\u0006\r"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/network/UserConsentApi;", "", "changeApplicantConsents", "Lio/reactivex/rxjava3/core/Completable;", "applicantId", "", "consents", "", "Lcom/onfido/android/sdk/capture/ui/userconsent/network/ConsentBody;", "getConsents", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/ui/userconsent/network/UserConsentsResponseItem;", "patchApplicantLocation", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface UserConsentApi {
    @API(version = "v3.3")
    @PATCH("/applicants/{uuid}/consents")
    Completable changeApplicantConsents(@Path(DebugImage.JsonKeys.UUID) String applicantId, @Body List<ConsentBody> consents);

    @API(version = "v3.3")
    @GET("/applicants/{uuid}/consents")
    Single<List<UserConsentsResponseItem>> getConsents(@Path(DebugImage.JsonKeys.UUID) String applicantId);

    @API(version = "v3.3")
    @PATCH("/applicants/{uuid}/location")
    Completable patchApplicantLocation(@Path(DebugImage.JsonKeys.UUID) String applicantId);
}
