package com.google.firebase.firestore;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public class SnapshotMetadata {
    private final boolean hasPendingWrites;
    private final boolean isFromCache;

    public boolean hasPendingWrites() {
        return this.hasPendingWrites;
    }

    public int hashCode() {
        return ((this.hasPendingWrites ? 1 : 0) * 31) + (this.isFromCache ? 1 : 0);
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    SnapshotMetadata(boolean z, boolean z2) {
        this.hasPendingWrites = z;
        this.isFromCache = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        SnapshotMetadata snapshotMetadata = (SnapshotMetadata) obj;
        return this.hasPendingWrites == snapshotMetadata.hasPendingWrites && this.isFromCache == snapshotMetadata.isFromCache;
    }

    public String toString() {
        return "SnapshotMetadata{hasPendingWrites=" + this.hasPendingWrites + ", isFromCache=" + this.isFromCache + AbstractJsonLexerKt.END_OBJ;
    }
}
