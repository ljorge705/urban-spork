package com.clevertap.android.sdk.network.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SendQueueRequestBody.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/clevertap/android/sdk/network/api/SendQueueRequestBody;", "", "queueHeader", "Lorg/json/JSONObject;", "queue", "Lorg/json/JSONArray;", "(Lorg/json/JSONObject;Lorg/json/JSONArray;)V", "getQueue", "()Lorg/json/JSONArray;", "getQueueHeader", "()Lorg/json/JSONObject;", "toString", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class SendQueueRequestBody {
    private final JSONArray queue;
    private final JSONObject queueHeader;

    public final JSONArray getQueue() {
        return this.queue;
    }

    public final JSONObject getQueueHeader() {
        return this.queueHeader;
    }

    public SendQueueRequestBody(JSONObject jSONObject, JSONArray queue) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        this.queueHeader = jSONObject;
        this.queue = queue;
    }

    public String toString() {
        if (this.queueHeader == null) {
            String string = this.queue.toString();
            Intrinsics.checkNotNullExpressionValue(string, "{\n        queue.toString()\n    }");
            return string;
        }
        StringBuilder sbAppend = new StringBuilder("[").append(this.queueHeader).append(AbstractJsonLexerKt.COMMA);
        String string2 = this.queue.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "queue.toString()");
        String strSubstring = string2.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return sbAppend.append(strSubstring).toString();
    }
}
