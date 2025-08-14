package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/Event;", "", "name", "", "inHouseType", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventType;", "publicName", "publicType", "Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventType;", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventType;Ljava/lang/String;Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventType;)V", "getInHouseType", "()Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/EventType;", "getName", "()Ljava/lang/String;", "getPublicName", "getPublicType", "()Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsEventType;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Event {
    private final EventType inHouseType;
    private final String name;
    private final String publicName;
    private final OnfidoAnalyticsEventType publicType;

    public Event(String name, EventType inHouseType, String str, OnfidoAnalyticsEventType onfidoAnalyticsEventType) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(inHouseType, "inHouseType");
        this.name = name;
        this.inHouseType = inHouseType;
        this.publicName = str;
        this.publicType = onfidoAnalyticsEventType;
    }

    public final EventType getInHouseType() {
        return this.inHouseType;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPublicName() {
        return this.publicName;
    }

    public final OnfidoAnalyticsEventType getPublicType() {
        return this.publicType;
    }

    public /* synthetic */ Event(String str, EventType eventType, String str2, OnfidoAnalyticsEventType onfidoAnalyticsEventType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, eventType, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : onfidoAnalyticsEventType);
    }
}
