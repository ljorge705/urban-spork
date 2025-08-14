package com.clevertap.android.sdk.utils;

import android.util.LruCache;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Cache.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0000\u0010\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/clevertap/android/sdk/utils/LruCacheProvider;", "", "()V", "provide", "Landroid/util/LruCache;", "", ExifInterface.GPS_DIRECTION_TRUE, "maxSize", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class LruCacheProvider {
    public static final LruCacheProvider INSTANCE = new LruCacheProvider();

    private LruCacheProvider() {
    }

    public final <T> LruCache<String, T> provide(final int maxSize) {
        return new LruCache<String, T>(maxSize) { // from class: com.clevertap.android.sdk.utils.LruCacheProvider$provide$$inlined$lruCache$default$1
            @Override // android.util.LruCache
            protected T create(String key) {
                Intrinsics.checkNotNullParameter(key, "key");
                return null;
            }

            @Override // android.util.LruCache
            protected void entryRemoved(boolean evicted, String key, T oldValue, T newValue) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(oldValue, "oldValue");
            }

            @Override // android.util.LruCache
            protected int sizeOf(String key, T value) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                return CacheKt.sizeInKb(value);
            }
        };
    }
}
