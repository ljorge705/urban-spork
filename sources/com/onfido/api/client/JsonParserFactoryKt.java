package com.onfido.api.client;

import com.onfido.api.client.serializers.LocaleAsStringSerializer;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.modules.SerializersModuleBuilder;

/* compiled from: JsonParserFactory.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\u0002\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0003"}, d2 = {"jsonParser", "Lkotlinx/serialization/json/Json;", "getJsonParserInstance", "onfido-api-client"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class JsonParserFactoryKt {
    private static final Json jsonParser = JsonKt.Json$default(null, new Function1<JsonBuilder, Unit>() { // from class: com.onfido.api.client.JsonParserFactoryKt$jsonParser$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(JsonBuilder jsonBuilder) {
            invoke2(jsonBuilder);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(JsonBuilder Json) {
            Intrinsics.checkNotNullParameter(Json, "$this$Json");
            Json.setIgnoreUnknownKeys(true);
            Json.setEncodeDefaults(true);
            Json.setLenient(true);
            Json.setExplicitNulls(false);
            SerializersModuleBuilder serializersModuleBuilder = new SerializersModuleBuilder();
            serializersModuleBuilder.contextual(Reflection.getOrCreateKotlinClass(Locale.class), LocaleAsStringSerializer.INSTANCE);
            Json.setSerializersModule(serializersModuleBuilder.build());
            Json.setCoerceInputValues(true);
        }
    }, 1, null);

    public static final Json getJsonParserInstance() {
        return jsonParser;
    }
}
