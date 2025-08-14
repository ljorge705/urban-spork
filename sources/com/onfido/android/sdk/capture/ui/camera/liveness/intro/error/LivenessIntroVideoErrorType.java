package com.onfido.android.sdk.capture.ui.camera.liveness.intro.error;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/intro/error/LivenessIntroVideoErrorType;", "", "(Ljava/lang/String;I)V", "TIMEOUT", "NETWORK", "NO_VIDEOS_FOUND", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessIntroVideoErrorType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LivenessIntroVideoErrorType[] $VALUES;
    public static final LivenessIntroVideoErrorType TIMEOUT = new LivenessIntroVideoErrorType("TIMEOUT", 0);
    public static final LivenessIntroVideoErrorType NETWORK = new LivenessIntroVideoErrorType("NETWORK", 1);
    public static final LivenessIntroVideoErrorType NO_VIDEOS_FOUND = new LivenessIntroVideoErrorType("NO_VIDEOS_FOUND", 2);

    private static final /* synthetic */ LivenessIntroVideoErrorType[] $values() {
        return new LivenessIntroVideoErrorType[]{TIMEOUT, NETWORK, NO_VIDEOS_FOUND};
    }

    static {
        LivenessIntroVideoErrorType[] livenessIntroVideoErrorTypeArr$values = $values();
        $VALUES = livenessIntroVideoErrorTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(livenessIntroVideoErrorTypeArr$values);
    }

    private LivenessIntroVideoErrorType(String str, int i) {
    }

    public static EnumEntries<LivenessIntroVideoErrorType> getEntries() {
        return $ENTRIES;
    }

    public static LivenessIntroVideoErrorType valueOf(String str) {
        return (LivenessIntroVideoErrorType) Enum.valueOf(LivenessIntroVideoErrorType.class, str);
    }

    public static LivenessIntroVideoErrorType[] values() {
        return (LivenessIntroVideoErrorType[]) $VALUES.clone();
    }
}
