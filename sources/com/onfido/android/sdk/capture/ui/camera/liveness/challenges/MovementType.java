package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/MovementType;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "TURN_LEFT", "TURN_RIGHT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MovementType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ MovementType[] $VALUES;
    public static final MovementType TURN_LEFT = new MovementType("TURN_LEFT", 0, "turnLeft");
    public static final MovementType TURN_RIGHT = new MovementType("TURN_RIGHT", 1, "turnRight");
    private final String id;

    private static final /* synthetic */ MovementType[] $values() {
        return new MovementType[]{TURN_LEFT, TURN_RIGHT};
    }

    static {
        MovementType[] movementTypeArr$values = $values();
        $VALUES = movementTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(movementTypeArr$values);
    }

    private MovementType(String str, int i, String str2) {
        this.id = str2;
    }

    public static EnumEntries<MovementType> getEntries() {
        return $ENTRIES;
    }

    public static MovementType valueOf(String str) {
        return (MovementType) Enum.valueOf(MovementType.class, str);
    }

    public static MovementType[] values() {
        return (MovementType[]) $VALUES.clone();
    }

    public final String getId() {
        return this.id;
    }
}
