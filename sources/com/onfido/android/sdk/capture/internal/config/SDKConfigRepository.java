package com.onfido.android.sdk.capture.internal.config;

import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.api.client.exception.TokenExpiredException;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B'\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0012R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\fX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/config/SDKConfigRepository;", "", "onfidoAPI", "Lcom/onfido/android/sdk/capture/network/OnfidoApiService;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "onfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "mutableOnfidoRemoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/MutableOnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/network/OnfidoApiService;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;Lcom/onfido/android/sdk/capture/internal/config/MutableOnfidoRemoteConfig;)V", "fetchSdkConfigObservable", "Lio/reactivex/rxjava3/core/Observable;", "fetchConfig", "mapToNewRemoteSdkConfiguration", "Lcom/onfido/api/client/data/SdkConfiguration;", OnfidoLauncher.KEY_CONFIG, "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class SDKConfigRepository {
    private final Observable<OnfidoRemoteConfig> fetchSdkConfigObservable;
    private final MutableOnfidoRemoteConfig mutableOnfidoRemoteConfig;
    private final OnfidoRemoteConfig onfidoRemoteConfig;

    @Inject
    public SDKConfigRepository(OnfidoApiService onfidoAPI, SchedulersProvider schedulersProvider, OnfidoRemoteConfig onfidoRemoteConfig, final MutableOnfidoRemoteConfig mutableOnfidoRemoteConfig) {
        Intrinsics.checkNotNullParameter(onfidoAPI, "onfidoAPI");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(onfidoRemoteConfig, "onfidoRemoteConfig");
        Intrinsics.checkNotNullParameter(mutableOnfidoRemoteConfig, "mutableOnfidoRemoteConfig");
        this.onfidoRemoteConfig = onfidoRemoteConfig;
        this.mutableOnfidoRemoteConfig = mutableOnfidoRemoteConfig;
        Observable<OnfidoRemoteConfig> observableOnErrorResumeNext = onfidoAPI.getSDKConfig$onfido_capture_sdk_core_release().toObservable().observeOn(schedulersProvider.getUi()).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.config.SDKConfigRepository$fetchSdkConfigObservable$1
            @Override // io.reactivex.rxjava3.functions.Function
            public final SdkConfiguration apply(SdkConfiguration p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return this.$tmp0.mapToNewRemoteSdkConfiguration(p0);
            }
        }).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.internal.config.SDKConfigRepository$fetchSdkConfigObservable$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(SdkConfiguration p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                mutableOnfidoRemoteConfig.updateFromSdkConfiguration(p0);
            }
        }).observeOn(schedulersProvider.getIo()).map(new Function() { // from class: com.onfido.android.sdk.capture.internal.config.SDKConfigRepository$fetchSdkConfigObservable$3
            @Override // io.reactivex.rxjava3.functions.Function
            public final OnfidoRemoteConfig apply(SdkConfiguration it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return this.this$0.onfidoRemoteConfig;
            }
        }).onErrorResumeNext(new Function() { // from class: com.onfido.android.sdk.capture.internal.config.SDKConfigRepository$fetchSdkConfigObservable$4
            @Override // io.reactivex.rxjava3.functions.Function
            public final ObservableSource<? extends OnfidoRemoteConfig> apply(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                return ((throwable instanceof TokenExpiredException) || (throwable instanceof SSLPeerUnverifiedException)) ? Observable.error(throwable) : Observable.just(this.this$0.onfidoRemoteConfig);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableOnErrorResumeNext, "onErrorResumeNext(...)");
        this.fetchSdkConfigObservable = observableOnErrorResumeNext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SdkConfiguration mapToNewRemoteSdkConfiguration(SdkConfiguration configuration) {
        return new SdkConfiguration(configuration.getValidations(), configuration.getDocumentCapture(), configuration.getExperimentalFeatures(), configuration.getLivenessCapture(), configuration.getSelfieCapture(), configuration.getMotionCapture(), configuration.getSdkFeatures(), "remote");
    }

    public Observable<OnfidoRemoteConfig> fetchConfig() {
        return this.fetchSdkConfigObservable;
    }
}
