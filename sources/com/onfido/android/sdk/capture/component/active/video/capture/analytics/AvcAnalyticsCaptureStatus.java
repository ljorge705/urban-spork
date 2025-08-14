package com.onfido.android.sdk.capture.component.active.video.capture.analytics;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsCaptureStatus;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "FACE_LOST", "ANIMATION_DISPLAYED", "HEADTURN_COMPLETED", "CAPTURE_STARTED", "CAPTURE_COMPLETED", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AvcAnalyticsCaptureStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AvcAnalyticsCaptureStatus[] $VALUES;
    private final String value;
    public static final AvcAnalyticsCaptureStatus FACE_LOST = new AvcAnalyticsCaptureStatus("FACE_LOST", 0, "face lost");
    public static final AvcAnalyticsCaptureStatus ANIMATION_DISPLAYED = new AvcAnalyticsCaptureStatus("ANIMATION_DISPLAYED", 1, "animation displayed");
    public static final AvcAnalyticsCaptureStatus HEADTURN_COMPLETED = new AvcAnalyticsCaptureStatus("HEADTURN_COMPLETED", 2, "headturn completed");
    public static final AvcAnalyticsCaptureStatus CAPTURE_STARTED = new AvcAnalyticsCaptureStatus("CAPTURE_STARTED", 3, "capture started");
    public static final AvcAnalyticsCaptureStatus CAPTURE_COMPLETED = new AvcAnalyticsCaptureStatus("CAPTURE_COMPLETED", 4, "capture completed");

    private static final /* synthetic */ AvcAnalyticsCaptureStatus[] $values() {
        return new AvcAnalyticsCaptureStatus[]{FACE_LOST, ANIMATION_DISPLAYED, HEADTURN_COMPLETED, CAPTURE_STARTED, CAPTURE_COMPLETED};
    }

    static {
        AvcAnalyticsCaptureStatus[] avcAnalyticsCaptureStatusArr$values = $values();
        $VALUES = avcAnalyticsCaptureStatusArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(avcAnalyticsCaptureStatusArr$values);
    }

    private AvcAnalyticsCaptureStatus(String str, int i, String str2) {
        this.value = str2;
    }

    public static EnumEntries<AvcAnalyticsCaptureStatus> getEntries() {
        return $ENTRIES;
    }

    public static AvcAnalyticsCaptureStatus valueOf(String str) {
        return (AvcAnalyticsCaptureStatus) Enum.valueOf(AvcAnalyticsCaptureStatus.class, str);
    }

    public static AvcAnalyticsCaptureStatus[] values() {
        return (AvcAnalyticsCaptureStatus[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }
}
