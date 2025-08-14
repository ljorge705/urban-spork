package com.clevertap.android.sdk.network.api;

import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate;
import com.clevertap.android.sdk.inapp.customtemplates.TemplateArgument;
import com.clevertap.android.sdk.utils.JsonUtilsKt;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DefineTemplatesRequestBody.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002¨\u0006\u0004"}, d2 = {"toJSON", "Lorg/json/JSONObject;", "", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "clevertap-core_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class DefineTemplatesRequestBodyKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final JSONObject toJSON(final Collection<CustomTemplate> collection) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "templatePayload");
        JsonUtilsKt.putObject(jSONObject, "definitions", new Function1<JSONObject, Unit>() { // from class: com.clevertap.android.sdk.network.api.DefineTemplatesRequestBodyKt$toJSON$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JSONObject jSONObject2) throws JSONException {
                invoke2(jSONObject2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JSONObject putObject) throws JSONException {
                Intrinsics.checkNotNullParameter(putObject, "$this$putObject");
                for (final CustomTemplate customTemplate : collection) {
                    JsonUtilsKt.putObject(putObject, customTemplate.getName(), new Function1<JSONObject, Unit>() { // from class: com.clevertap.android.sdk.network.api.DefineTemplatesRequestBodyKt$toJSON$1$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JSONObject jSONObject2) throws JSONException {
                            invoke2(jSONObject2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JSONObject putObject2) throws JSONException {
                            Intrinsics.checkNotNullParameter(putObject2, "$this$putObject");
                            putObject2.put("type", customTemplate.getType().getStringName());
                            if (!customTemplate.getArgs$clevertap_core_release().isEmpty()) {
                                final CustomTemplate customTemplate2 = customTemplate;
                                JsonUtilsKt.putObject(putObject2, "vars", new Function1<JSONObject, Unit>() { // from class: com.clevertap.android.sdk.network.api.DefineTemplatesRequestBodyKt.toJSON.1.1.1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(JSONObject jSONObject2) throws JSONException {
                                        invoke2(jSONObject2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(JSONObject putObject3) throws JSONException {
                                        Intrinsics.checkNotNullParameter(putObject3, "$this$putObject");
                                        final int i = 0;
                                        for (Object obj : customTemplate2.getArgs$clevertap_core_release()) {
                                            int i2 = i + 1;
                                            if (i < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            final TemplateArgument templateArgument = (TemplateArgument) obj;
                                            JsonUtilsKt.putObject(putObject3, templateArgument.getName(), new Function1<JSONObject, Unit>() { // from class: com.clevertap.android.sdk.network.api.DefineTemplatesRequestBodyKt$toJSON$1$1$1$1$1$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(JSONObject jSONObject2) throws JSONException {
                                                    invoke2(jSONObject2);
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(JSONObject putObject4) throws JSONException {
                                                    Intrinsics.checkNotNullParameter(putObject4, "$this$putObject");
                                                    Object defaultValue = templateArgument.getDefaultValue();
                                                    if (defaultValue != null) {
                                                        putObject4.put("defaultValue", defaultValue);
                                                    }
                                                    putObject4.put("type", templateArgument.getType().getStringName());
                                                    putObject4.put("order", i);
                                                }
                                            });
                                            i = i2;
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        return jSONObject;
    }
}
