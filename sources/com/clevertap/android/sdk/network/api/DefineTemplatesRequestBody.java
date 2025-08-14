package com.clevertap.android.sdk.network.api;

import com.clevertap.android.sdk.inapp.customtemplates.CustomTemplate;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: DefineTemplatesRequestBody.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/clevertap/android/sdk/network/api/DefineTemplatesRequestBody;", "", "header", "Lorg/json/JSONObject;", "templates", "", "Lcom/clevertap/android/sdk/inapp/customtemplates/CustomTemplate;", "(Lorg/json/JSONObject;Ljava/util/Collection;)V", "jsonArray", "Lorg/json/JSONArray;", "getJsonArray", "()Lorg/json/JSONArray;", "toString", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class DefineTemplatesRequestBody {
    private final JSONArray jsonArray;

    public final JSONArray getJsonArray() {
        return this.jsonArray;
    }

    public DefineTemplatesRequestBody(JSONObject header, Collection<CustomTemplate> templates) {
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(templates, "templates");
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(header);
        jSONArray.put(DefineTemplatesRequestBodyKt.toJSON(templates));
        this.jsonArray = jSONArray;
    }

    public String toString() {
        String string = this.jsonArray.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.toString()");
        return string;
    }
}
