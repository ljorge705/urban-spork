package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeType;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "RECITE", "MOVEMENT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessChallengeType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LivenessChallengeType[] $VALUES;
    private final String id;
    public static final LivenessChallengeType RECITE = new LivenessChallengeType("RECITE", 0, "recite");
    public static final LivenessChallengeType MOVEMENT = new LivenessChallengeType("MOVEMENT", 1, "movement");

    private static final /* synthetic */ LivenessChallengeType[] $values() {
        return new LivenessChallengeType[]{RECITE, MOVEMENT};
    }

    static {
        LivenessChallengeType[] livenessChallengeTypeArr$values = $values();
        $VALUES = livenessChallengeTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(livenessChallengeTypeArr$values);
    }

    private LivenessChallengeType(String str, int i, String str2) {
        this.id = str2;
    }

    public static EnumEntries<LivenessChallengeType> getEntries() {
        return $ENTRIES;
    }

    public static LivenessChallengeType valueOf(String str) {
        return (LivenessChallengeType) Enum.valueOf(LivenessChallengeType.class, str);
    }

    public static LivenessChallengeType[] values() {
        return (LivenessChallengeType[]) $VALUES.clone();
    }

    public final String getId() {
        return this.id;
    }
}
