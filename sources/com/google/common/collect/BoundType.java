package com.google.common.collect;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public enum BoundType {
    OPEN(false),
    CLOSED(true);

    final boolean inclusive;

    static BoundType forBoolean(boolean z) {
        return z ? CLOSED : OPEN;
    }

    BoundType(boolean z) {
        this.inclusive = z;
    }
}
