package com.google.firebase.firestore.core;

/* loaded from: classes2.dex */
public class ListenSequence {
    public static final long INVALID = -1;
    private long previousSequenceNumber;

    public long next() {
        long j = this.previousSequenceNumber + 1;
        this.previousSequenceNumber = j;
        return j;
    }

    public ListenSequence(long j) {
        this.previousSequenceNumber = j;
    }
}
