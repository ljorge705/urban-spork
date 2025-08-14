package com.onfido.api.client;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.net.HttpHeaders;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.jakewharton.retrofit2.converter.kotlinx.serialization.KotlinSerializationConverterFactory;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.api.client.Utils;
import com.onfido.api.client.interceptor.ApiVersioningInterceptor;
import com.onfido.api.client.interceptor.SdkHeadersInterceptor;
import com.onfido.api.client.token.InternalToken;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.api.client.token.sdk.InternalSDKToken;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import okhttp3.CertificatePinner;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/* compiled from: OnfidoFetcher.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 '2\u00020\u0001:\u0002&'BW\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0016¢\u0006\u0002\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\u001d\u0010\u001b\u001a\u00020\u001c2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u001dJ-\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\tH\u0002¢\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0002R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/onfido/api/client/OnfidoFetcher;", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "tokenProvider", "Lcom/onfido/api/client/token/TokenProvider;", "baseUrl", "", "certificatePinningPKs", "", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "sdkVariant", "sdkWrapperPlatform", "sdkWrapperVersion", "json", "Lkotlinx/serialization/json/Json;", "(Lokhttp3/OkHttpClient;Lcom/onfido/api/client/token/TokenProvider;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/Json;)V", "retrofit", "Lretrofit2/Retrofit;", "api", ExifInterface.GPS_DIRECTION_TRUE, "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "applicants", "Lcom/onfido/api/client/OnfidoService;", "applicants$onfido_api_client", "certificatePinningActive", "", "([Ljava/lang/String;)Z", "configureCertificatePinning", "", "builder", "Lokhttp3/OkHttpClient$Builder;", "hostname", "(Lokhttp3/OkHttpClient$Builder;Ljava/lang/String;[Ljava/lang/String;)V", "makeUrlCompatibleWithRetrofit", "url", "AuthTokenInterceptor", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class OnfidoFetcher {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "OnfidoFetcher";
    private static final int TIMEOUT_SECONDS = 30;
    private static final int WRITE_TIMEOUT_SECONDS = 60;
    private final Retrofit retrofit;

    public /* synthetic */ OnfidoFetcher(OkHttpClient okHttpClient, TokenProvider tokenProvider, String str, String[] strArr, String str2, String str3, String str4, String str5, Json json, DefaultConstructorMarker defaultConstructorMarker) {
        this(okHttpClient, tokenProvider, str, strArr, str2, str3, str4, str5, json);
    }

    @JvmStatic
    public static final OnfidoFetcher create(OkHttpClient okHttpClient, TokenProvider tokenProvider, String str, String[] strArr, String str2, String str3, String str4, String str5, Json json) {
        return INSTANCE.create(okHttpClient, tokenProvider, str, strArr, str2, str3, str4, str5, json);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private OnfidoFetcher(OkHttpClient okHttpClient, TokenProvider tokenProvider, String str, String[] strArr, String str2, String str3, String str4, String str5, Json json) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManager[] trustManagers;
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, 0 == true ? 1 : 0);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder builderNewBuilder = okHttpClient.newBuilder();
        ArrayList arrayList = new ArrayList(builderNewBuilder.interceptors());
        builderNewBuilder.addInterceptor(new AuthTokenInterceptor(tokenProvider)).addInterceptor(new SdkHeadersInterceptor(str2, str3, str4, str5, json)).addInterceptor(httpLoggingInterceptor).connectTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).protocols(CollectionsKt.listOf(Protocol.HTTP_1_1)).retryOnConnectionFailure(true).addInterceptor(new ApiVersioningInterceptor());
        UtilsExtKt.moveInterceptorsToTop(builderNewBuilder, arrayList);
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            trustManagers = trustManagerFactory.getTrustManagers();
        } catch (KeyManagementException unused) {
            Utils.Log.e(TAG, "Exception thrown while setting SSL socket factory");
        } catch (KeyStoreException unused2) {
            Utils.Log.e(TAG, "Exception thrown while setting SSL socket factory");
        } catch (NoSuchAlgorithmException unused3) {
            Utils.Log.e(TAG, "Exception thrown while setting SSL socket factory");
        }
        if (trustManagers.length == 1) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                Intrinsics.checkNotNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                builderNewBuilder.sslSocketFactory(new OnfidoTLSSocketFactory(), (X509TrustManager) trustManager);
                if (certificatePinningActive(strArr)) {
                    configureCertificatePinning(builderNewBuilder, HttpUrl.INSTANCE.get(str).host(), strArr);
                }
                Retrofit retrofitBuild = new Retrofit.Builder().client(builderNewBuilder.build()).addConverterFactory(KotlinSerializationConverterFactory.create(json, MediaType.INSTANCE.get(NfcDataRepository.FILE_TYPE_JSON))).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(makeUrlCompatibleWithRetrofit(str)).build();
                Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
                this.retrofit = retrofitBuild;
                return;
            }
        }
        throw new IllegalStateException(("Unexpected default trust managers:" + Arrays.toString(trustManagers)).toString());
    }

    private final String makeUrlCompatibleWithRetrofit(String url) {
        return StringsKt.endsWith$default(url, "/", false, 2, (Object) null) ? url : url + "/";
    }

    private final boolean certificatePinningActive(String[] certificatePinningPKs) {
        return !(certificatePinningPKs == null || certificatePinningPKs.length == 0);
    }

    private final void configureCertificatePinning(OkHttpClient.Builder builder, String hostname, String[] certificatePinningPKs) {
        CertificatePinner.Builder builder2 = new CertificatePinner.Builder();
        if (certificatePinningPKs == null) {
            certificatePinningPKs = new String[0];
        }
        for (String str : certificatePinningPKs) {
            builder2.add(hostname, str);
        }
        builder.certificatePinner(builder2.build());
    }

    public final <T> T api(Class<T> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return (T) this.retrofit.create(clazz);
    }

    public final OnfidoService applicants$onfido_api_client() {
        return (OnfidoService) api(OnfidoService.class);
    }

    /* compiled from: OnfidoFetcher.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/onfido/api/client/OnfidoFetcher$AuthTokenInterceptor;", "Lokhttp3/Interceptor;", "tokenProvider", "Lcom/onfido/api/client/token/TokenProvider;", "(Lcom/onfido/api/client/token/TokenProvider;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class AuthTokenInterceptor implements Interceptor {
        private final TokenProvider tokenProvider;

        public AuthTokenInterceptor(TokenProvider tokenProvider) {
            Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
            this.tokenProvider = tokenProvider;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Intrinsics.checkNotNullParameter(chain, "chain");
            Request.Builder builderNewBuilder = chain.request().newBuilder();
            InternalToken internalTokenProvideToken = this.tokenProvider.provideToken();
            if (internalTokenProvideToken instanceof InternalSDKToken) {
                String appId = internalTokenProvideToken.getAppId();
                if (appId == null) {
                    appId = "";
                }
                builderNewBuilder.addHeader("Application-Id", appId);
                builderNewBuilder.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + internalTokenProvideToken.getTokenValue());
            } else {
                builderNewBuilder.addHeader(HttpHeaders.AUTHORIZATION, "Token token=" + internalTokenProvideToken.getTokenValue());
            }
            return chain.proceed(builderNewBuilder.build());
        }
    }

    /* compiled from: OnfidoFetcher.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J]\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00042\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\u0010\u0018R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/onfido/api/client/OnfidoFetcher$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TIMEOUT_SECONDS", "", "WRITE_TIMEOUT_SECONDS", "create", "Lcom/onfido/api/client/OnfidoFetcher;", "okHttpClient", "Lokhttp3/OkHttpClient;", "tokenProvider", "Lcom/onfido/api/client/token/TokenProvider;", "baseUrl", "certificatePinningPKs", "", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "sdkVariant", "sdkWrapperPlatform", "sdkWrapperVersion", "json", "Lkotlinx/serialization/json/Json;", "(Lokhttp3/OkHttpClient;Lcom/onfido/api/client/token/TokenProvider;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/Json;)Lcom/onfido/api/client/OnfidoFetcher;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final OnfidoFetcher create(OkHttpClient okHttpClient, TokenProvider tokenProvider, String baseUrl, String[] certificatePinningPKs, String sdkVersion, String sdkVariant, String sdkWrapperPlatform, String sdkWrapperVersion, Json json) {
            Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
            Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
            Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
            Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
            Intrinsics.checkNotNullParameter(sdkVariant, "sdkVariant");
            Intrinsics.checkNotNullParameter(sdkWrapperPlatform, "sdkWrapperPlatform");
            Intrinsics.checkNotNullParameter(sdkWrapperVersion, "sdkWrapperVersion");
            Intrinsics.checkNotNullParameter(json, "json");
            return new OnfidoFetcher(okHttpClient, tokenProvider, baseUrl, certificatePinningPKs, sdkVersion, sdkVariant, sdkWrapperPlatform, sdkWrapperVersion, json, null);
        }
    }
}
