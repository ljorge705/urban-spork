package com.clevertap.android.sdk.utils;

import android.util.LruCache;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Cache.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u0014*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\u0014B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0015\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\tJ\u0015\u0010\u0013\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/clevertap/android/sdk/utils/InMemoryLruCache;", ExifInterface.GPS_DIRECTION_TRUE, "", "maxSize", "", "memoryCache", "Lcom/clevertap/android/sdk/utils/CacheMethods;", "(ILcom/clevertap/android/sdk/utils/CacheMethods;)V", "add", "", Constants.KEY_KEY, "", "value", "(Ljava/lang/String;Ljava/lang/Object;)Z", "empty", "", "get", "(Ljava/lang/String;)Ljava/lang/Object;", "isEmpty", "remove", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class InMemoryLruCache<T> {
    public static final String TYPE_LRU = "TYPE_LRU";
    private final int maxSize;
    private final CacheMethods<T> memoryCache;

    public InMemoryLruCache(int i, CacheMethods<T> memoryCache) {
        Intrinsics.checkNotNullParameter(memoryCache, "memoryCache");
        this.maxSize = i;
        this.memoryCache = memoryCache;
    }

    public /* synthetic */ InMemoryLruCache(int i, CacheMethods cacheMethods, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? new CacheMethods<T>(i) { // from class: com.clevertap.android.sdk.utils.InMemoryLruCache.1
            private final LruCache<String, T> lru;

            public final LruCache<String, T> getLru() {
                return this.lru;
            }

            {
                this.lru = LruCacheProvider.INSTANCE.provide(i);
            }

            @Override // com.clevertap.android.sdk.utils.CacheMethods
            public boolean add(String key, T value) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                this.lru.put(key, value);
                return true;
            }

            @Override // com.clevertap.android.sdk.utils.CacheMethods
            public T get(String key) {
                Intrinsics.checkNotNullParameter(key, "key");
                return this.lru.get(key);
            }

            @Override // com.clevertap.android.sdk.utils.CacheMethods
            public T remove(String key) {
                Intrinsics.checkNotNullParameter(key, "key");
                return this.lru.remove(key);
            }

            @Override // com.clevertap.android.sdk.utils.CacheMethods
            public void empty() {
                this.lru.evictAll();
            }

            @Override // com.clevertap.android.sdk.utils.CacheMethods
            public boolean isEmpty() {
                return this.lru.size() == 0;
            }
        } : cacheMethods);
    }

    public final boolean add(String key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        if (CacheKt.sizeInKb(value) > this.maxSize) {
            remove(key);
            return false;
        }
        this.memoryCache.add(key, value);
        return true;
    }

    public final T get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.memoryCache.get(key);
    }

    public final T remove(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.memoryCache.remove(key);
    }

    public final void empty() {
        this.memoryCache.empty();
    }

    public final boolean isEmpty() {
        return this.memoryCache.isEmpty();
    }
}
