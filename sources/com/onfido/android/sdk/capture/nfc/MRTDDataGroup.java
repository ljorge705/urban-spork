package com.onfido.android.sdk.capture.nfc;

import com.facebook.hermes.intl.Constants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: MRTDModels.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "", "(Ljava/lang/String;I)V", "COM", "SOD", "DG1", "DG2", "DG3", "DG4", "DG5", "DG6", "DG7", "DG8", "DG9", "DG10", "DG11", "DG12", "DG13", "DG14", "DG15", "DG16", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRTDDataGroup {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ MRTDDataGroup[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final MRTDDataGroup COM = new MRTDDataGroup("COM", 0);
    public static final MRTDDataGroup SOD = new MRTDDataGroup("SOD", 1);
    public static final MRTDDataGroup DG1 = new MRTDDataGroup("DG1", 2);
    public static final MRTDDataGroup DG2 = new MRTDDataGroup("DG2", 3);
    public static final MRTDDataGroup DG3 = new MRTDDataGroup("DG3", 4);
    public static final MRTDDataGroup DG4 = new MRTDDataGroup("DG4", 5);
    public static final MRTDDataGroup DG5 = new MRTDDataGroup("DG5", 6);
    public static final MRTDDataGroup DG6 = new MRTDDataGroup("DG6", 7);
    public static final MRTDDataGroup DG7 = new MRTDDataGroup("DG7", 8);
    public static final MRTDDataGroup DG8 = new MRTDDataGroup("DG8", 9);
    public static final MRTDDataGroup DG9 = new MRTDDataGroup("DG9", 10);
    public static final MRTDDataGroup DG10 = new MRTDDataGroup("DG10", 11);
    public static final MRTDDataGroup DG11 = new MRTDDataGroup("DG11", 12);
    public static final MRTDDataGroup DG12 = new MRTDDataGroup("DG12", 13);
    public static final MRTDDataGroup DG13 = new MRTDDataGroup("DG13", 14);
    public static final MRTDDataGroup DG14 = new MRTDDataGroup("DG14", 15);
    public static final MRTDDataGroup DG15 = new MRTDDataGroup("DG15", 16);
    public static final MRTDDataGroup DG16 = new MRTDDataGroup("DG16", 17);

    private static final /* synthetic */ MRTDDataGroup[] $values() {
        return new MRTDDataGroup[]{COM, SOD, DG1, DG2, DG3, DG4, DG5, DG6, DG7, DG8, DG9, DG10, DG11, DG12, DG13, DG14, DG15, DG16};
    }

    public static EnumEntries<MRTDDataGroup> getEntries() {
        return $ENTRIES;
    }

    public static MRTDDataGroup valueOf(String str) {
        return (MRTDDataGroup) Enum.valueOf(MRTDDataGroup.class, str);
    }

    public static MRTDDataGroup[] values() {
        return (MRTDDataGroup[]) $VALUES.clone();
    }

    private MRTDDataGroup(String str, int i) {
    }

    static {
        MRTDDataGroup[] mRTDDataGroupArr$values = $values();
        $VALUES = mRTDDataGroupArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(mRTDDataGroupArr$values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: MRTDModels.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup$Companion;", "", "()V", "all", "", "Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", "()[Lcom/onfido/android/sdk/capture/nfc/MRTDDataGroup;", Constants.COLLATION_DEFAULT, "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MRTDDataGroup[] all() {
            return MRTDDataGroup.values();
        }

        /* renamed from: default, reason: not valid java name */
        public final MRTDDataGroup[] m5616default() {
            return new MRTDDataGroup[]{MRTDDataGroup.DG1, MRTDDataGroup.DG2, MRTDDataGroup.DG11, MRTDDataGroup.DG12, MRTDDataGroup.DG13, MRTDDataGroup.DG14, MRTDDataGroup.DG15, MRTDDataGroup.SOD};
        }
    }
}
