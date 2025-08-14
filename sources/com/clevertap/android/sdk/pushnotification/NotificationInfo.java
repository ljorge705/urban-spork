package com.clevertap.android.sdk.pushnotification;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes5.dex */
public final class NotificationInfo {
    public final boolean fromCleverTap;
    public final boolean shouldRender;

    public NotificationInfo(boolean z, boolean z2) {
        this.fromCleverTap = z;
        this.shouldRender = z2;
    }

    public String toString() {
        return "NotificationInfo{fromCleverTap=" + this.fromCleverTap + ", shouldRender=" + this.shouldRender + AbstractJsonLexerKt.END_OBJ;
    }
}
