package com.clevertap.android.sdk.network;

import com.clevertap.android.sdk.inapp.evaluation.EventType;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: NetworkHeadersListener.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/clevertap/android/sdk/network/NetworkHeadersListener;", "", "onAttachHeaders", "Lorg/json/JSONObject;", "endpointId", "Lcom/clevertap/android/sdk/network/EndpointId;", "eventType", "Lcom/clevertap/android/sdk/inapp/evaluation/EventType;", "onSentHeaders", "", "allHeaders", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface NetworkHeadersListener {
    JSONObject onAttachHeaders(EndpointId endpointId, EventType eventType);

    void onSentHeaders(JSONObject allHeaders, EndpointId endpointId, EventType eventType);
}
