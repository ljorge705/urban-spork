package com.google.android.play.core.splitinstall.internal;

import android.os.Build;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzao {
    public static zzan zza() {
        switch (Build.VERSION.SDK_INT) {
            case 24:
                return new zzaz();
            case 25:
                return new zzba();
            case 26:
                return new zzbd();
            case 27:
                if (Build.VERSION.PREVIEW_SDK_INT == 0) {
                    return new zzbe();
                }
                break;
        }
        return new zzbg();
    }
}
