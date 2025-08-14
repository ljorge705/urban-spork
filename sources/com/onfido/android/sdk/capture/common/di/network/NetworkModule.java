package com.onfido.android.sdk.capture.common.di.network;

import com.jakewharton.retrofit2.converter.kotlinx.serialization.KotlinSerializationConverterFactory;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.document.supported.data.remote.datasource.SupportedDocumentsApi;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAnalyticsApi;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.network.OnfidoAssetsApi;
import com.onfido.android.sdk.capture.internal.network.InternalOnfidoNetworkInterceptorsProvider;
import com.onfido.android.sdk.capture.internal.performance.repository.PerformanceAnalyticsApi;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.internal.util.logging.network.OnfidoLoggerApi;
import com.onfido.android.sdk.capture.ui.camera.liveness.intro.LivenessIntroVideoApi;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.android.sdk.capture.ui.proofOfAddress.network.ProofOfAddressApi;
import com.onfido.android.sdk.capture.ui.userconsent.network.UserConsentApi;
import com.onfido.api.client.JsonParserFactoryKt;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.OnfidoAPIFactory;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.Module;
import com.onfido.dagger.Provides;
import com.onfido.javax.inject.Singleton;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

@Module
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u0005J\r\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0002\b\rJ\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u0011J-\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b!J5\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007H\u0001¢\u0006\u0002\b&J\u0015\u0010'\u001a\u00020(2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b)J\u0015\u0010*\u001a\u00020+2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b,J\u0015\u0010-\u001a\u00020.2\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b/J\u001d\u00100\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0007H\u0001¢\u0006\u0002\b1J\u0015\u00102\u001a\u0002032\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b4J\u0015\u00105\u001a\u0002062\u0006\u0010\u0016\u001a\u00020\u0017H\u0001¢\u0006\u0002\b7¨\u00068"}, d2 = {"Lcom/onfido/android/sdk/capture/common/di/network/NetworkModule;", "", "()V", "provideInternalOnfidoNetworkInterceptorsProvider", "Lcom/onfido/android/sdk/capture/internal/network/InternalOnfidoNetworkInterceptorsProvider;", "provideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_release", "provideJson", "Lkotlinx/serialization/json/Json;", "provideJson$onfido_capture_sdk_core_release", "provideLivenessIntroVideoAPI", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/LivenessIntroVideoApi;", "retrofit", "Lretrofit2/Retrofit;", "provideLivenessIntroVideoAPI$onfido_capture_sdk_core_release", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "provider", "provideOkHttpClient$onfido_capture_sdk_core_release", "provideOnfidoAPI", "Lcom/onfido/api/client/OnfidoAPI;", "tokenProvider", "Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "onfidoFetcher", "Lcom/onfido/api/client/OnfidoFetcher;", "identityInteractor", "Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "json", "provideOnfidoAPI$onfido_capture_sdk_core_release", "provideOnfidoAnalyticsApi", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/OnfidoAnalyticsApi;", "provideOnfidoAnalyticsApi$onfido_capture_sdk_core_release", "provideOnfidoAssetsApi", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/network/OnfidoAssetsApi;", "provideOnfidoAssetsApi$onfido_capture_sdk_core_release", "provideOnfidoFetcher", "okHttpClient", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "provideOnfidoFetcher$onfido_capture_sdk_core_release", "provideOnfidoLoggerApi", "Lcom/onfido/android/sdk/capture/internal/util/logging/network/OnfidoLoggerApi;", "provideOnfidoLoggerApi$onfido_capture_sdk_core_release", "providePerformanceAnalyticsApi", "Lcom/onfido/android/sdk/capture/internal/performance/repository/PerformanceAnalyticsApi;", "providePerformanceAnalyticsApi$onfido_capture_sdk_core_release", "provideProofOfAddressApi", "Lcom/onfido/android/sdk/capture/ui/proofOfAddress/network/ProofOfAddressApi;", "provideProofOfAddressApi$onfido_capture_sdk_core_release", "provideRetrofit", "provideRetrofit$onfido_capture_sdk_core_release", "provideSupportedDocumentsApi", "Lcom/onfido/android/sdk/capture/document/supported/data/remote/datasource/SupportedDocumentsApi;", "provideSupportedDocumentsApi$onfido_capture_sdk_core_release", "provideUserConsentApi", "Lcom/onfido/android/sdk/capture/ui/userconsent/network/UserConsentApi;", "provideUserConsentApi$onfido_capture_sdk_core_release", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class NetworkModule {
    public static final NetworkModule INSTANCE = new NetworkModule();

    private NetworkModule() {
    }

    @Provides
    public final InternalOnfidoNetworkInterceptorsProvider provideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_release() {
        return InternalOnfidoNetworkInterceptorsProvider.INSTANCE;
    }

    @Provides
    public final Json provideJson$onfido_capture_sdk_core_release() {
        return JsonParserFactoryKt.getJsonParserInstance();
    }

    @Provides
    @Singleton
    public final LivenessIntroVideoApi provideLivenessIntroVideoAPI$onfido_capture_sdk_core_release(Retrofit retrofit) throws SecurityException {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(LivenessIntroVideoApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (LivenessIntroVideoApi) objCreate;
    }

    @Provides
    @Singleton
    public final OkHttpClient provideOkHttpClient$onfido_capture_sdk_core_release(InternalOnfidoNetworkInterceptorsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Iterator<T> it = provider.getInterceptors().iterator();
        while (it.hasNext()) {
            builder.addInterceptor((Interceptor) it.next());
        }
        return builder.build();
    }

    @Provides
    public final OnfidoAPI provideOnfidoAPI$onfido_capture_sdk_core_release(OnfidoTokenProvider tokenProvider, OnfidoFetcher onfidoFetcher, IdentityInteractor identityInteractor, Json json) {
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        Intrinsics.checkNotNullParameter(json, "json");
        return OnfidoAPIFactory.create(tokenProvider, onfidoFetcher, identityInteractor.getSdkSource$onfido_capture_sdk_core_release(), identityInteractor.getSdkVersion$onfido_capture_sdk_core_release(), json);
    }

    @Provides
    public final OnfidoAnalyticsApi provideOnfidoAnalyticsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        return (OnfidoAnalyticsApi) onfidoFetcher.api(OnfidoAnalyticsApi.class);
    }

    @Provides
    public final OnfidoAssetsApi provideOnfidoAssetsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        return (OnfidoAssetsApi) onfidoFetcher.api(OnfidoAssetsApi.class);
    }

    @Provides
    @Singleton
    public final OnfidoFetcher provideOnfidoFetcher$onfido_capture_sdk_core_release(OkHttpClient okHttpClient, OnfidoTokenProvider tokenProvider, OnfidoConfig onfidoConfig, IdentityInteractor identityInteractor, Json json) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        Intrinsics.checkNotNullParameter(json, "json");
        return OnfidoFetcher.INSTANCE.create(okHttpClient, tokenProvider, tokenProvider.provideToken().buildUrl(), onfidoConfig.getApiCertificatePinningPKHashes(), identityInteractor.getSdkVersion$onfido_capture_sdk_core_release(), identityInteractor.getSdkVariant$onfido_capture_sdk_core_release(), identityInteractor.getWrapperSdkPlatform$onfido_capture_sdk_core_release(), identityInteractor.getWrapperSdkVersion$onfido_capture_sdk_core_release(), json);
    }

    @Provides
    public final OnfidoLoggerApi provideOnfidoLoggerApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        return (OnfidoLoggerApi) onfidoFetcher.api(OnfidoLoggerApi.class);
    }

    @Provides
    public final PerformanceAnalyticsApi providePerformanceAnalyticsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        return (PerformanceAnalyticsApi) onfidoFetcher.api(PerformanceAnalyticsApi.class);
    }

    @Provides
    public final ProofOfAddressApi provideProofOfAddressApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        return (ProofOfAddressApi) onfidoFetcher.api(ProofOfAddressApi.class);
    }

    @Provides
    @Singleton
    public final Retrofit provideRetrofit$onfido_capture_sdk_core_release(OkHttpClient okHttpClient, Json json) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(json, "json");
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, null);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder builderAddInterceptor = okHttpClient.newBuilder().addInterceptor(httpLoggingInterceptor);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        Retrofit retrofitBuild = new Retrofit.Builder().addCallAdapterFactory(RxJava3CallAdapterFactory.create()).addConverterFactory(KotlinSerializationConverterFactory.create(json, MediaType.INSTANCE.get(NfcDataRepository.FILE_TYPE_JSON))).client(builderAddInterceptor.connectTimeout(5000L, timeUnit).readTimeout(5000L, timeUnit).writeTimeout(5000L, timeUnit).retryOnConnectionFailure(false).build()).baseUrl(NetworkConstants.ONFIDO_API_BASE_URL).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
        return retrofitBuild;
    }

    @Provides
    public final SupportedDocumentsApi provideSupportedDocumentsApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        return (SupportedDocumentsApi) onfidoFetcher.api(SupportedDocumentsApi.class);
    }

    @Provides
    public final UserConsentApi provideUserConsentApi$onfido_capture_sdk_core_release(OnfidoFetcher onfidoFetcher) {
        Intrinsics.checkNotNullParameter(onfidoFetcher, "onfidoFetcher");
        return (UserConsentApi) onfidoFetcher.api(UserConsentApi.class);
    }
}
