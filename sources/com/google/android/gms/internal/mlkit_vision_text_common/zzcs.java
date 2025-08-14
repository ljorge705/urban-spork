package com.google.android.gms.internal.mlkit_vision_text_common;

import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzcs extends zzct {
    public static int zza(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }
}
