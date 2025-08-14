package com.clevertap.android.sdk.inapp.evaluation;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.inapp.evaluation.LimitType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LimitAdapter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0003R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/LimitAdapter;", "", "limitJSON", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", Constants.KEY_FREQUENCY, "", "getFrequency", "()I", Constants.KEY_LIMIT, "getLimit", "limitType", "Lcom/clevertap/android/sdk/inapp/evaluation/LimitType;", "getLimitType", "()Lcom/clevertap/android/sdk/inapp/evaluation/LimitType;", "toJsonObject", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class LimitAdapter {
    private final int frequency;
    private final int limit;
    private final LimitType limitType;

    public final int getFrequency() {
        return this.frequency;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final LimitType getLimitType() {
        return this.limitType;
    }

    public LimitAdapter(JSONObject limitJSON) {
        Intrinsics.checkNotNullParameter(limitJSON, "limitJSON");
        LimitType.Companion companion = LimitType.INSTANCE;
        String strOptString = limitJSON.optString("type");
        Intrinsics.checkNotNullExpressionValue(strOptString, "limitJSON.optString(Constants.KEY_TYPE)");
        this.limitType = companion.fromString(strOptString);
        this.limit = limitJSON.optInt(Constants.KEY_LIMIT);
        this.frequency = limitJSON.optInt(Constants.KEY_FREQUENCY);
    }

    public final JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", this.limitType.toString());
        jSONObject.put(Constants.KEY_LIMIT, this.limit);
        jSONObject.put(Constants.KEY_FREQUENCY, this.frequency);
        return jSONObject;
    }
}
