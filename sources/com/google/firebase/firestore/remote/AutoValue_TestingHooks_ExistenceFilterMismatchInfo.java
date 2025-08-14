package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.remote.TestingHooks;

/* loaded from: classes2.dex */
final class AutoValue_TestingHooks_ExistenceFilterMismatchInfo extends TestingHooks.ExistenceFilterMismatchInfo {
    private final TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter;
    private final int existenceFilterCount;
    private final int localCacheCount;

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    TestingHooks.ExistenceFilterBloomFilterInfo bloomFilter() {
        return this.bloomFilter;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    int existenceFilterCount() {
        return this.existenceFilterCount;
    }

    @Override // com.google.firebase.firestore.remote.TestingHooks.ExistenceFilterMismatchInfo
    int localCacheCount() {
        return this.localCacheCount;
    }

    AutoValue_TestingHooks_ExistenceFilterMismatchInfo(int i, int i2, TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo) {
        this.localCacheCount = i;
        this.existenceFilterCount = i2;
        this.bloomFilter = existenceFilterBloomFilterInfo;
    }

    public String toString() {
        return "ExistenceFilterMismatchInfo{localCacheCount=" + this.localCacheCount + ", existenceFilterCount=" + this.existenceFilterCount + ", bloomFilter=" + this.bloomFilter + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingHooks.ExistenceFilterMismatchInfo)) {
            return false;
        }
        TestingHooks.ExistenceFilterMismatchInfo existenceFilterMismatchInfo = (TestingHooks.ExistenceFilterMismatchInfo) obj;
        if (this.localCacheCount == existenceFilterMismatchInfo.localCacheCount() && this.existenceFilterCount == existenceFilterMismatchInfo.existenceFilterCount()) {
            TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = this.bloomFilter;
            if (existenceFilterBloomFilterInfo == null) {
                if (existenceFilterMismatchInfo.bloomFilter() == null) {
                    return true;
                }
            } else if (existenceFilterBloomFilterInfo.equals(existenceFilterMismatchInfo.bloomFilter())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = (((this.localCacheCount ^ 1000003) * 1000003) ^ this.existenceFilterCount) * 1000003;
        TestingHooks.ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo = this.bloomFilter;
        return i ^ (existenceFilterBloomFilterInfo == null ? 0 : existenceFilterBloomFilterInfo.hashCode());
    }
}
