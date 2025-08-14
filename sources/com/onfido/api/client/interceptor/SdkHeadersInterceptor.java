package com.onfido.api.client.interceptor;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: SdkHeadersInterceptor.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/api/client/interceptor/SdkHeadersInterceptor;", "Lokhttp3/Interceptor;", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "", "sdkVariant", "sdkWrapperPlatform", "sdkWrapperVersion", "jsonParser", "Lkotlinx/serialization/json/Json;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/Json;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SdkHeadersInterceptor implements Interceptor {
    private static final String INTEGRATION_HEADER_NAME = "integration";
    private static final String INTEGRATION_VARIANT_HEADER_NAME = "variant";
    private static final String INTEGRATION_VERSION_HEADER_NAME = "integration_version";
    private static final String METADATA_HEADER_NAME = "x-onfido-sdk-metadata";
    private static final String PLATFORM_HEADER_NAME = "x-onfido-sdk-platform";
    private static final String SDK_PLATFORM = "android";
    private static final String VERSION_HEADER_NAME = "x-onfido-sdk-version";
    private final Json jsonParser;
    private final String sdkVariant;
    private final String sdkVersion;
    private final String sdkWrapperPlatform;
    private final String sdkWrapperVersion;

    public SdkHeadersInterceptor(String sdkVersion, String sdkVariant, String sdkWrapperPlatform, String sdkWrapperVersion, Json jsonParser) {
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
        Intrinsics.checkNotNullParameter(sdkVariant, "sdkVariant");
        Intrinsics.checkNotNullParameter(sdkWrapperPlatform, "sdkWrapperPlatform");
        Intrinsics.checkNotNullParameter(sdkWrapperVersion, "sdkWrapperVersion");
        Intrinsics.checkNotNullParameter(jsonParser, "jsonParser");
        this.sdkVersion = sdkVersion;
        this.sdkVariant = sdkVariant;
        this.sdkWrapperPlatform = sdkWrapperPlatform;
        this.sdkWrapperVersion = sdkWrapperVersion;
        this.jsonParser = jsonParser;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(INTEGRATION_HEADER_NAME, this.sdkWrapperPlatform), TuplesKt.to(INTEGRATION_VERSION_HEADER_NAME, this.sdkWrapperVersion), TuplesKt.to("variant", this.sdkVariant));
        Request.Builder builderNewBuilder = chain.request().newBuilder();
        builderNewBuilder.addHeader(PLATFORM_HEADER_NAME, "android");
        builderNewBuilder.addHeader(VERSION_HEADER_NAME, this.sdkVersion);
        Json json = this.jsonParser;
        builderNewBuilder.addHeader(METADATA_HEADER_NAME, json.encodeToString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Map.class, KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)), KTypeProjection.INSTANCE.invariant(Reflection.typeOf(String.class)))), mapMapOf));
        return chain.proceed(builderNewBuilder.build());
    }
}
