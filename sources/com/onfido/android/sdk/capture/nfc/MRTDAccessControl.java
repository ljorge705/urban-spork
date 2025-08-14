package com.onfido.android.sdk.capture.nfc;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: MRTDModels.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/nfc/MRTDAccessControl;", "", "(Ljava/lang/String;I)V", "BAC", "PACE", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRTDAccessControl {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ MRTDAccessControl[] $VALUES;
    public static final MRTDAccessControl BAC = new MRTDAccessControl("BAC", 0);
    public static final MRTDAccessControl PACE = new MRTDAccessControl("PACE", 1);

    private static final /* synthetic */ MRTDAccessControl[] $values() {
        return new MRTDAccessControl[]{BAC, PACE};
    }

    public static EnumEntries<MRTDAccessControl> getEntries() {
        return $ENTRIES;
    }

    public static MRTDAccessControl valueOf(String str) {
        return (MRTDAccessControl) Enum.valueOf(MRTDAccessControl.class, str);
    }

    public static MRTDAccessControl[] values() {
        return (MRTDAccessControl[]) $VALUES.clone();
    }

    private MRTDAccessControl(String str, int i) {
    }

    static {
        MRTDAccessControl[] mRTDAccessControlArr$values = $values();
        $VALUES = mRTDAccessControlArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(mRTDAccessControlArr$values);
    }
}
