package com.onfido.android.sdk.capture.component.active.video.capture.analytics;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsFaceAlignment;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NO_FACE_DETECTED", "FACE_NOT_CENTERED", "FACE_TOO_CLOSE", "FACE_TOO_FAR", "FACE_ALIGNED", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AvcAnalyticsFaceAlignment {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AvcAnalyticsFaceAlignment[] $VALUES;
    private final String value;
    public static final AvcAnalyticsFaceAlignment NO_FACE_DETECTED = new AvcAnalyticsFaceAlignment("NO_FACE_DETECTED", 0, "no face detected");
    public static final AvcAnalyticsFaceAlignment FACE_NOT_CENTERED = new AvcAnalyticsFaceAlignment("FACE_NOT_CENTERED", 1, "face not centered");
    public static final AvcAnalyticsFaceAlignment FACE_TOO_CLOSE = new AvcAnalyticsFaceAlignment("FACE_TOO_CLOSE", 2, "face too close");
    public static final AvcAnalyticsFaceAlignment FACE_TOO_FAR = new AvcAnalyticsFaceAlignment("FACE_TOO_FAR", 3, "face too far");
    public static final AvcAnalyticsFaceAlignment FACE_ALIGNED = new AvcAnalyticsFaceAlignment("FACE_ALIGNED", 4, "face aligned");

    private static final /* synthetic */ AvcAnalyticsFaceAlignment[] $values() {
        return new AvcAnalyticsFaceAlignment[]{NO_FACE_DETECTED, FACE_NOT_CENTERED, FACE_TOO_CLOSE, FACE_TOO_FAR, FACE_ALIGNED};
    }

    static {
        AvcAnalyticsFaceAlignment[] avcAnalyticsFaceAlignmentArr$values = $values();
        $VALUES = avcAnalyticsFaceAlignmentArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(avcAnalyticsFaceAlignmentArr$values);
    }

    private AvcAnalyticsFaceAlignment(String str, int i, String str2) {
        this.value = str2;
    }

    public static EnumEntries<AvcAnalyticsFaceAlignment> getEntries() {
        return $ENTRIES;
    }

    public static AvcAnalyticsFaceAlignment valueOf(String str) {
        return (AvcAnalyticsFaceAlignment) Enum.valueOf(AvcAnalyticsFaceAlignment.class, str);
    }

    public static AvcAnalyticsFaceAlignment[] values() {
        return (AvcAnalyticsFaceAlignment[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }
}
