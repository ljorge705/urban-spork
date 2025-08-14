package com.google.firebase.firestore.remote;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public final class ExistenceFilter {
    private final int count;
    private com.google.firestore.v1.BloomFilter unchangedNames;

    public int getCount() {
        return this.count;
    }

    public com.google.firestore.v1.BloomFilter getUnchangedNames() {
        return this.unchangedNames;
    }

    public ExistenceFilter(int i) {
        this.count = i;
    }

    public ExistenceFilter(int i, com.google.firestore.v1.BloomFilter bloomFilter) {
        this.count = i;
        this.unchangedNames = bloomFilter;
    }

    public String toString() {
        return "ExistenceFilter{count=" + this.count + ", unchangedNames=" + this.unchangedNames + AbstractJsonLexerKt.END_OBJ;
    }
}
