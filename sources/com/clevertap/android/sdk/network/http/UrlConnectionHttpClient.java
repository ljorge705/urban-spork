package com.clevertap.android.sdk.network.http;

import com.clevertap.android.sdk.Logger;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;

/* compiled from: UrlConnectionHttpClient.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\n\u0010\u0017\u001a\u0004\u0018\u00010\u0013H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u0004\u0018\u00010\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, d2 = {"Lcom/clevertap/android/sdk/network/http/UrlConnectionHttpClient;", "Lcom/clevertap/android/sdk/network/http/CtHttpClient;", "isSslPinningEnabled", "", "logger", "Lcom/clevertap/android/sdk/Logger;", "logTag", "", "(ZLcom/clevertap/android/sdk/Logger;Ljava/lang/String;)V", "()Z", "setSslPinningEnabled", "(Z)V", "socketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "getSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "socketFactory$delegate", "Lkotlin/Lazy;", "sslContext", "Ljavax/net/ssl/SSLContext;", "getSslContext", "()Ljavax/net/ssl/SSLContext;", "sslContext$delegate", "createSslContext", "execute", "Lcom/clevertap/android/sdk/network/http/Response;", "request", "Lcom/clevertap/android/sdk/network/http/Request;", "openHttpsURLConnection", "Ljavax/net/ssl/HttpsURLConnection;", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class UrlConnectionHttpClient implements CtHttpClient {
    public static final int CONNECT_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 10000;
    private boolean isSslPinningEnabled;
    private final String logTag;
    private final Logger logger;

    /* renamed from: socketFactory$delegate, reason: from kotlin metadata */
    private final Lazy socketFactory;

    /* renamed from: sslContext$delegate, reason: from kotlin metadata */
    private final Lazy sslContext;

    /* renamed from: isSslPinningEnabled, reason: from getter */
    public final boolean getIsSslPinningEnabled() {
        return this.isSslPinningEnabled;
    }

    public final void setSslPinningEnabled(boolean z) {
        this.isSslPinningEnabled = z;
    }

    public UrlConnectionHttpClient(boolean z, Logger logger, String logTag) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        this.isSslPinningEnabled = z;
        this.logger = logger;
        this.logTag = logTag;
        this.socketFactory = LazyKt.lazy(new Function0<SSLSocketFactory>() { // from class: com.clevertap.android.sdk.network.http.UrlConnectionHttpClient$socketFactory$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SSLSocketFactory invoke() {
                try {
                    Logger.d("Pinning SSL session to DigiCertGlobalRoot CA certificate");
                    SSLContext sslContext = this.this$0.getSslContext();
                    if (sslContext != null) {
                        return sslContext.getSocketFactory();
                    }
                    return null;
                } catch (Exception e) {
                    Logger.d("Issue in pinning SSL,", e);
                    return null;
                }
            }
        });
        this.sslContext = LazyKt.lazy(new Function0<SSLContext>() { // from class: com.clevertap.android.sdk.network.http.UrlConnectionHttpClient$sslContext$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final SSLContext invoke() {
                return this.this$0.createSslContext();
            }
        });
    }

    private final SSLSocketFactory getSocketFactory() {
        return (SSLSocketFactory) this.socketFactory.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SSLContext getSslContext() {
        return (SSLContext) this.sslContext.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, javax.net.ssl.HttpsURLConnection] */
    @Override // com.clevertap.android.sdk.network.http.CtHttpClient
    public Response execute(Request request) throws Exception {
        Intrinsics.checkNotNullParameter(request, "request");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            objectRef.element = openHttpsURLConnection(request);
            this.logger.debug(this.logTag, "Sending request to: " + request.getUrl());
            int responseCode = ((HttpsURLConnection) objectRef.element).getResponseCode();
            Map headers = ((HttpsURLConnection) objectRef.element).getHeaderFields();
            Function0<Unit> function0 = new Function0<Unit>() { // from class: com.clevertap.android.sdk.network.http.UrlConnectionHttpClient$execute$disconnectConnection$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    objectRef.element.disconnect();
                }
            };
            if (responseCode == 200) {
                Intrinsics.checkNotNullExpressionValue(headers, "headers");
                return new Response(request, responseCode, headers, ((HttpsURLConnection) objectRef.element).getInputStream(), function0);
            }
            Intrinsics.checkNotNullExpressionValue(headers, "headers");
            return new Response(request, responseCode, headers, ((HttpsURLConnection) objectRef.element).getErrorStream(), function0);
        } catch (Exception e) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) objectRef.element;
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
            throw e;
        }
    }

    private final HttpsURLConnection openHttpsURLConnection(Request request) throws IOException {
        URLConnection uRLConnectionOpenConnection = new URL(request.getUrl().toString()).openConnection();
        Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnectionOpenConnection;
        httpsURLConnection.setConnectTimeout(10000);
        httpsURLConnection.setReadTimeout(10000);
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            httpsURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        httpsURLConnection.setInstanceFollowRedirects(false);
        if (this.isSslPinningEnabled && getSslContext() != null) {
            httpsURLConnection.setSSLSocketFactory(getSocketFactory());
        }
        if (request.getBody() != null) {
            httpsURLConnection.setDoOutput(true);
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            try {
                byte[] bytes = request.getBody().getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                outputStream.write(bytes);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, null);
            } finally {
            }
        }
        return httpsURLConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SSLContext createSslContext() throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException, KeyManagementException {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            ClassLoader classLoader = keyStore.getClass().getClassLoader();
            Certificate certificateGenerateCertificate = certificateFactory.generateCertificate(new BufferedInputStream(classLoader != null ? classLoader.getResourceAsStream("com/clevertap/android/sdk/certificates/AmazonRootCA1.cer") : null));
            Intrinsics.checkNotNull(certificateGenerateCertificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            keyStore.setCertificateEntry("AmazonRootCA1", (X509Certificate) certificateGenerateCertificate);
            trustManagerFactory.init(keyStore);
            sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
            Logger.d("SSL Context built");
            return sSLContext;
        } catch (Exception e) {
            Logger.i("Error building SSL Context", e);
            return null;
        }
    }
}
