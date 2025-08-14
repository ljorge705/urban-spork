package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.TestingHooks;

/* loaded from: classes2.dex */
final class AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo extends TestingHooks.ExistenceFilterBloomFilterInfo {
    private final boolean applied;
    private final int bitmapLength;
    private final int hashCount;
    private final int padding;

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    boolean applied() {
        return this.applied;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    int bitmapLength() {
        return this.bitmapLength;
    }

    public int hashCode() {
        return (((((((this.applied ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.hashCount) * 1000003) ^ this.bitmapLength) * 1000003) ^ this.padding;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    int hashCount() {
        return this.hashCount;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterBloomFilterInfo
    int padding() {
        return this.padding;
    }

    AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(boolean z, int i, int i2, int i3) {
        this.applied = z;
        this.hashCount = i;
        this.bitmapLength = i2;
        this.padding = i3;
    }

    public String toString() {
        return "ExistenceFilterBloomFilterInfo{applied=" + this.applied + ", hashCount=" + this.hashCount + ", bitmapLength=" + this.bitmapLength + ", padding=" + this.padding + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingHooks.ExistenceFilterBloomFilterInfo)) {
            return false;
        }
        TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = (TestingHooks.ExistenceFilterBloomFilterInfo) obj;
        return this.applied == existenceFilterBloomFilterInfo.applied() && this.hashCount == existenceFilterBloomFilterInfo.hashCount() && this.bitmapLength == existenceFilterBloomFilterInfo.bitmapLength() && this.padding == existenceFilterBloomFilterInfo.padding();
    }
}
