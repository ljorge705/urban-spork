package com.nimbusds.jose.util.cache;

import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class CachedObject<V> {
    private final long expirationTime;
    private final V object;
    private final long timestamp;

    public static long computeExpirationTime(long j, long j2) {
        long j3 = j + j2;
        if (j3 < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    public V get() {
        return this.object;
    }

    public long getExpirationTime() {
        return this.expirationTime;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public boolean isValid(long j) {
        return j < this.expirationTime;
    }

    public CachedObject(V v, long j, long j2) {
        if (v == null) {
            throw new IllegalArgumentException("The object must not be null");
        }
        this.object = v;
        this.timestamp = j;
        this.expirationTime = j2;
    }

    public boolean isExpired(long j) {
        return !isValid(j);
    }
}
