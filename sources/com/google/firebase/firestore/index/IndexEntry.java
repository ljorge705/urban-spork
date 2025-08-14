package com.google.firebase.firestore.index;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;

/* loaded from: classes2.dex */
public abstract class IndexEntry implements Comparable<IndexEntry> {
    public abstract byte[] getArrayValue();

    public abstract byte[] getDirectionalValue();

    public abstract DocumentKey getDocumentKey();

    public abstract int getIndexId();

    public static IndexEntry create(int i, DocumentKey documentKey, byte[] bArr, byte[] bArr2) {
        return new AutoValue_IndexEntry(i, documentKey, bArr, bArr2);
    }

    @Override // java.lang.Comparable
    public int compareTo(IndexEntry indexEntry) {
        int iCompare = Integer.compare(getIndexId(), indexEntry.getIndexId());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompareTo = getDocumentKey().compareTo(indexEntry.getDocumentKey());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareByteArrays = Util.compareByteArrays(getArrayValue(), indexEntry.getArrayValue());
        return iCompareByteArrays != 0 ? iCompareByteArrays : Util.compareByteArrays(getDirectionalValue(), indexEntry.getDirectionalValue());
    }
}
