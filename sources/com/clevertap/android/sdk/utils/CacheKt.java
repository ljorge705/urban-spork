package com.clevertap.android.sdk.utils;

import android.graphics.Bitmap;
import kotlin.Metadata;

/* compiled from: Cache.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0000\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0003"}, d2 = {"sizeInKb", "", "", "clevertap-core_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class CacheKt {
    public static final int sizeInKb(Object obj) {
        if (obj instanceof Bitmap) {
            return ((Bitmap) obj).getByteCount() / 1024;
        }
        if (obj instanceof byte[]) {
            return ((byte[]) obj).length / 1024;
        }
        return 1;
    }
}
