package com.google.firebase.firestore;

import com.facebook.common.statfs.StatFsHelper;

/* loaded from: classes2.dex */
public final class MemoryLruGcSettings implements MemoryGarbageCollectorSettings {
    private long sizeBytes;

    public long getSizeBytes() {
        return this.sizeBytes;
    }

    public int hashCode() {
        long j = this.sizeBytes;
        return (int) (j ^ (j >>> 32));
    }

    public static class Builder {
        private long sizeBytes;

        public void setSizeBytes(long j) {
            this.sizeBytes = j;
        }

        private Builder() {
            this.sizeBytes = StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES;
        }

        public MemoryLruGcSettings build() {
            return new MemoryLruGcSettings(this.sizeBytes);
        }
    }

    private MemoryLruGcSettings(long j) {
        this.sizeBytes = j;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.sizeBytes == ((MemoryLruGcSettings) obj).sizeBytes;
    }

    public String toString() {
        return "MemoryLruGcSettings{cacheSize=" + getSizeBytes() + "}";
    }
}
