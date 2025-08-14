package com.onfido.api.client;

import com.onfido.api.client.data.ErrorData;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import okhttp3.ResponseBody;

/* compiled from: ErrorParserImpl.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/api/client/ErrorParserImpl;", "Lcom/onfido/api/client/ErrorParser;", "json", "Lkotlinx/serialization/json/Json;", "(Lkotlinx/serialization/json/Json;)V", "parseErrorFrom", "Lcom/onfido/api/client/data/ErrorData;", Response.TYPE, "Lretrofit2/Response;", "Companion", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ErrorParserImpl implements ErrorParser {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Json json;

    @JvmStatic
    public static final ErrorParser newInstance(Json json) {
        return INSTANCE.newInstance(json);
    }

    public ErrorParserImpl(Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
    }

    @Override // com.onfido.api.client.ErrorParser
    public ErrorData parseErrorFrom(retrofit2.Response<?> response) {
        String strString;
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            ResponseBody responseBodyErrorBody = response.errorBody();
            if (responseBodyErrorBody != null && (strString = responseBodyErrorBody.string()) != null) {
                Json json = this.json;
                return (ErrorData) json.decodeFromString(SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(ErrorData.class)), strString);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* compiled from: ErrorParserImpl.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/onfido/api/client/ErrorParserImpl$Companion;", "", "()V", "newInstance", "Lcom/onfido/api/client/ErrorParser;", "json", "Lkotlinx/serialization/json/Json;", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ ErrorParser newInstance$default(Companion companion, Json json, int i, Object obj) {
            if ((i & 1) != 0) {
                json = JsonParserFactoryKt.getJsonParserInstance();
            }
            return companion.newInstance(json);
        }

        @JvmStatic
        public final ErrorParser newInstance(Json json) {
            Intrinsics.checkNotNullParameter(json, "json");
            return new ErrorParserImpl(json);
        }
    }
}
