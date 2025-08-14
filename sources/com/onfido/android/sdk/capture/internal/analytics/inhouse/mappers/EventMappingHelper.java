package com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.AnalyticsEvent;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.PublicPropertyKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\u0004*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/mappers/EventMappingHelper;", "", "()V", "ORIENTATION_LANDSCAPE", "", "ORIENTATION_PORTRAIT", "SCREEN_ORIENTATION", "screenMode", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;", "getScreenMode", "(Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsEvent;)Ljava/lang/String;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class EventMappingHelper {
    public static final EventMappingHelper INSTANCE = new EventMappingHelper();
    private static final String ORIENTATION_LANDSCAPE = "LANDSCAPE";
    private static final String ORIENTATION_PORTRAIT = "PORTRAIT";
    private static final String SCREEN_ORIENTATION = "orientation";

    private EventMappingHelper() {
    }

    public final String getScreenMode(AnalyticsEvent analyticsEvent) throws JSONException {
        Intrinsics.checkNotNullParameter(analyticsEvent, "<this>");
        Object obj = analyticsEvent.getExtraPublicProperties().get(PublicPropertyKey.IS_PORTRAIT);
        Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : true;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("orientation", zBooleanValue ? ORIENTATION_PORTRAIT : ORIENTATION_LANDSCAPE);
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
