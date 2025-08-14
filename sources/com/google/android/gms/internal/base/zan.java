package com.google.android.gms.internal.base;

import android.os.Build;

/* compiled from: com.google.android.gms:play-services-base@@18.2.0 */
/* loaded from: classes3.dex */
final class zan {
    static boolean zaa() {
        return Build.VERSION.SDK_INT >= 33 || Build.VERSION.CODENAME.charAt(0) == 'T';
    }
}
