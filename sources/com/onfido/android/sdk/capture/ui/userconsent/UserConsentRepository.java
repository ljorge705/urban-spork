package com.onfido.android.sdk.capture.ui.userconsent;

import com.onfido.android.sdk.capture.BuildConfig;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.userconsent.network.ConsentBody;
import com.onfido.android.sdk.capture.ui.userconsent.network.UserConsentApi;
import com.onfido.android.sdk.capture.ui.userconsent.network.UserConsentsResponseItem;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u001f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016H\u0010¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001aJ\r\u0010\u001b\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001cJ\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0016H\u0016R\u0014\u0010\t\u001a\u00020\n8RX\u0092\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00020\u000e8\u0010@\u0010X\u0091\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentRepository;", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "userConsentApi", "Lcom/onfido/android/sdk/capture/ui/userconsent/network/UserConsentApi;", "tokenProvider", "Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "(Lokhttp3/OkHttpClient;Lcom/onfido/android/sdk/capture/ui/userconsent/network/UserConsentApi;Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;)V", "applicantId", "Lcom/onfido/api/client/token/sdk/ApplicantId;", "getApplicantId", "()Lcom/onfido/api/client/token/sdk/ApplicantId;", "consentPageUrl", "", "getConsentPageUrl$onfido_capture_sdk_core_release$annotations", "()V", "getConsentPageUrl$onfido_capture_sdk_core_release", "()Ljava/lang/String;", "setConsentPageUrl$onfido_capture_sdk_core_release", "(Ljava/lang/String;)V", "getUserConsentPage", "Lio/reactivex/rxjava3/core/Single;", "getUserConsentPage$onfido_capture_sdk_core_release", "grantUserConsent", "Lio/reactivex/rxjava3/core/Completable;", "grantUserConsent$onfido_capture_sdk_core_release", "revokeConsent", "revokeConsent$onfido_capture_sdk_core_release", "shouldShowUserConsent", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class UserConsentRepository {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long LOCATION_CALL_RETRY_COUNT = 3;
    private String consentPageUrl;
    private final OkHttpClient okHttpClient;
    private final OnfidoTokenProvider tokenProvider;
    private final UserConsentApi userConsentApi;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/userconsent/UserConsentRepository$Companion;", "", "()V", "LOCATION_CALL_RETRY_COUNT", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public UserConsentRepository(OkHttpClient okHttpClient, UserConsentApi userConsentApi, OnfidoTokenProvider tokenProvider) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(userConsentApi, "userConsentApi");
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        this.okHttpClient = okHttpClient;
        this.userConsentApi = userConsentApi;
        this.tokenProvider = tokenProvider;
        this.consentPageUrl = BuildConfig.URL_USER_CONSENT_PAGE;
    }

    private ApplicantId getApplicantId() {
        return this.tokenProvider.provideToken().getApplicantId();
    }

    public static /* synthetic */ void getConsentPageUrl$onfido_capture_sdk_core_release$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getUserConsentPage$lambda$0(UserConsentRepository this$0, final SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        final Call callNewCall = this$0.okHttpClient.newCall(new Request.Builder().get().url(this$0.getConsentPageUrl()).build());
        callNewCall.enqueue(new Callback() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository$getUserConsentPage$1$1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onError(e);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (emitter.isDisposed()) {
                    return;
                }
                try {
                    SingleEmitter<String> singleEmitter = emitter;
                    ResponseBody responseBodyBody = response.body();
                    String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                    if (strString == null) {
                        strString = "";
                    }
                    singleEmitter.onSuccess(strString);
                } catch (IOException e) {
                    if (emitter.isDisposed()) {
                        return;
                    }
                    emitter.onError(e);
                }
            }
        });
        emitter.setCancellable(new Cancellable() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Cancellable
            public final void cancel() {
                callNewCall.cancel();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean shouldShowUserConsent$lambda$1(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.TRUE;
    }

    /* renamed from: getConsentPageUrl$onfido_capture_sdk_core_release, reason: from getter */
    public String getConsentPageUrl() {
        return this.consentPageUrl;
    }

    public Single<String> getUserConsentPage$onfido_capture_sdk_core_release() {
        Single<String> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                UserConsentRepository.getUserConsentPage$lambda$0(this.f$0, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create(...)");
        return singleCreate;
    }

    public Completable grantUserConsent$onfido_capture_sdk_core_release() {
        return this.userConsentApi.changeApplicantConsents(getApplicantId().getUuid(), CollectionsKt.listOf(ConsentBody.INSTANCE.createConsent(true)));
    }

    public Completable revokeConsent$onfido_capture_sdk_core_release() {
        return this.userConsentApi.changeApplicantConsents(getApplicantId().getUuid(), CollectionsKt.listOf(ConsentBody.INSTANCE.createConsent(false)));
    }

    public void setConsentPageUrl$onfido_capture_sdk_core_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.consentPageUrl = str;
    }

    public Single<Boolean> shouldShowUserConsent() {
        Single<Boolean> singleOnErrorReturn = this.userConsentApi.patchApplicantLocation(getApplicantId().getUuid()).retry(3L, new Predicate() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository.shouldShowUserConsent.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Throwable it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Timber.INSTANCE.e(it);
                return true;
            }
        }).andThen(this.userConsentApi.getConsents(getApplicantId().getUuid())).map(new Function() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository.shouldShowUserConsent.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Boolean apply(List<UserConsentsResponseItem> consentsList) {
                boolean z;
                Intrinsics.checkNotNullParameter(consentsList, "consentsList");
                if ((consentsList instanceof Collection) && consentsList.isEmpty()) {
                    z = false;
                } else {
                    for (UserConsentsResponseItem userConsentsResponseItem : consentsList) {
                        if (userConsentsResponseItem.getIsRequired() && !userConsentsResponseItem.getIsGranted()) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }).onErrorReturn(new Function() { // from class: com.onfido.android.sdk.capture.ui.userconsent.UserConsentRepository$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return UserConsentRepository.shouldShowUserConsent$lambda$1((Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleOnErrorReturn, "onErrorReturn(...)");
        return singleOnErrorReturn;
    }
}
