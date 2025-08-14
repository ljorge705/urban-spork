package com.onfido.android.sdk.capture.utils;

import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/Rotation;", "", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "(Ljava/lang/String;II)V", "getIdentifier", "()I", "ANGLE_0", "ANGLE_90", "ANGLE_180", "ANGLE_270", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
final class Rotation {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ Rotation[] $VALUES;
    private final int identifier;
    public static final Rotation ANGLE_0 = new Rotation("ANGLE_0", 0, 0);
    public static final Rotation ANGLE_90 = new Rotation("ANGLE_90", 1, 1);
    public static final Rotation ANGLE_180 = new Rotation("ANGLE_180", 2, 2);
    public static final Rotation ANGLE_270 = new Rotation("ANGLE_270", 3, 3);

    private static final /* synthetic */ Rotation[] $values() {
        return new Rotation[]{ANGLE_0, ANGLE_90, ANGLE_180, ANGLE_270};
    }

    static {
        Rotation[] rotationArr$values = $values();
        $VALUES = rotationArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(rotationArr$values);
    }

    private Rotation(String str, int i, int i2) {
        this.identifier = i2;
    }

    public static EnumEntries<Rotation> getEntries() {
        return $ENTRIES;
    }

    public static Rotation valueOf(String str) {
        return (Rotation) Enum.valueOf(Rotation.class, str);
    }

    public static Rotation[] values() {
        return (Rotation[]) $VALUES.clone();
    }

    public final int getIdentifier() {
        return this.identifier;
    }
}
