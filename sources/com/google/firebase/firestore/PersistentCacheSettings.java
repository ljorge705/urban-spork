package com.google.firebase.firestore;

import com.facebook.common.statfs.StatFsHelper;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public final class PersistentCacheSettings implements LocalCacheSettings {
    private final long sizeBytes;

    public long getSizeBytes() {
        return this.sizeBytes;
    }

    public int hashCode() {
        long j = this.sizeBytes;
        return (int) (j ^ (j >>> 32));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private PersistentCacheSettings(long j) {
        this.sizeBytes = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.sizeBytes == ((PersistentCacheSettings) obj).sizeBytes;
    }

    public String toString() {
        return "PersistentCacheSettings{sizeBytes=" + this.sizeBytes + AbstractJsonLexerKt.END_OBJ;
    }

    public static class Builder {
        private long sizeBytes;

        public Builder setSizeBytes(long j) {
            this.sizeBytes = j;
            return this;
        }

        private Builder() {
            this.sizeBytes = StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES;
        }

        public PersistentCacheSettings build() {
            return new PersistentCacheSettings(this.sizeBytes);
        }
    }
}
