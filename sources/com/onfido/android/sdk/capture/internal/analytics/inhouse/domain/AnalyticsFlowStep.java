package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.EventNames;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/AnalyticsFlowStep;", "", "(Ljava/lang/String;I)V", EventNames.PublicNames.Other.WELCOME, "CONSENT", "DOCUMENT", "FACE", "COMPLETE", "NFC", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AnalyticsFlowStep {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AnalyticsFlowStep[] $VALUES;
    public static final AnalyticsFlowStep WELCOME = new AnalyticsFlowStep(EventNames.PublicNames.Other.WELCOME, 0);
    public static final AnalyticsFlowStep CONSENT = new AnalyticsFlowStep("CONSENT", 1);
    public static final AnalyticsFlowStep DOCUMENT = new AnalyticsFlowStep("DOCUMENT", 2);
    public static final AnalyticsFlowStep FACE = new AnalyticsFlowStep("FACE", 3);
    public static final AnalyticsFlowStep COMPLETE = new AnalyticsFlowStep("COMPLETE", 4);
    public static final AnalyticsFlowStep NFC = new AnalyticsFlowStep("NFC", 5);

    private static final /* synthetic */ AnalyticsFlowStep[] $values() {
        return new AnalyticsFlowStep[]{WELCOME, CONSENT, DOCUMENT, FACE, COMPLETE, NFC};
    }

    static {
        AnalyticsFlowStep[] analyticsFlowStepArr$values = $values();
        $VALUES = analyticsFlowStepArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(analyticsFlowStepArr$values);
    }

    private AnalyticsFlowStep(String str, int i) {
    }

    public static EnumEntries<AnalyticsFlowStep> getEntries() {
        return $ENTRIES;
    }

    public static AnalyticsFlowStep valueOf(String str) {
        return (AnalyticsFlowStep) Enum.valueOf(AnalyticsFlowStep.class, str);
    }

    public static AnalyticsFlowStep[] values() {
        return (AnalyticsFlowStep[]) $VALUES.clone();
    }
}
