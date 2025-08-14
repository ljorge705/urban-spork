package com.onfido.android.sdk.capture.analytics;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: OnfidoAnalyticsPropertyKey.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/analytics/OnfidoAnalyticsPropertyKey;", "", "(Ljava/lang/String;I)V", "SCREEN_NAME", "SCREEN_MODE", "DOCUMENT_TYPE", "COUNTRY_CODE", "VIDEO_CHALLENGE_TYPE", "IS_AUTOCAPTURE", "STEP", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoAnalyticsPropertyKey {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ OnfidoAnalyticsPropertyKey[] $VALUES;
    public static final OnfidoAnalyticsPropertyKey SCREEN_NAME = new OnfidoAnalyticsPropertyKey("SCREEN_NAME", 0);
    public static final OnfidoAnalyticsPropertyKey SCREEN_MODE = new OnfidoAnalyticsPropertyKey("SCREEN_MODE", 1);
    public static final OnfidoAnalyticsPropertyKey DOCUMENT_TYPE = new OnfidoAnalyticsPropertyKey("DOCUMENT_TYPE", 2);
    public static final OnfidoAnalyticsPropertyKey COUNTRY_CODE = new OnfidoAnalyticsPropertyKey("COUNTRY_CODE", 3);
    public static final OnfidoAnalyticsPropertyKey VIDEO_CHALLENGE_TYPE = new OnfidoAnalyticsPropertyKey("VIDEO_CHALLENGE_TYPE", 4);
    public static final OnfidoAnalyticsPropertyKey IS_AUTOCAPTURE = new OnfidoAnalyticsPropertyKey("IS_AUTOCAPTURE", 5);
    public static final OnfidoAnalyticsPropertyKey STEP = new OnfidoAnalyticsPropertyKey("STEP", 6);

    private static final /* synthetic */ OnfidoAnalyticsPropertyKey[] $values() {
        return new OnfidoAnalyticsPropertyKey[]{SCREEN_NAME, SCREEN_MODE, DOCUMENT_TYPE, COUNTRY_CODE, VIDEO_CHALLENGE_TYPE, IS_AUTOCAPTURE, STEP};
    }

    public static EnumEntries<OnfidoAnalyticsPropertyKey> getEntries() {
        return $ENTRIES;
    }

    public static OnfidoAnalyticsPropertyKey valueOf(String str) {
        return (OnfidoAnalyticsPropertyKey) Enum.valueOf(OnfidoAnalyticsPropertyKey.class, str);
    }

    public static OnfidoAnalyticsPropertyKey[] values() {
        return (OnfidoAnalyticsPropertyKey[]) $VALUES.clone();
    }

    private OnfidoAnalyticsPropertyKey(String str, int i) {
    }

    static {
        OnfidoAnalyticsPropertyKey[] onfidoAnalyticsPropertyKeyArr$values = $values();
        $VALUES = onfidoAnalyticsPropertyKeyArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(onfidoAnalyticsPropertyKeyArr$values);
    }
}
