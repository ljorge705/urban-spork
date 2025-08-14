package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.DocumentKey;

/* loaded from: classes2.dex */
public class LimboDocumentChange {
    private final DocumentKey key;
    private final Type type;

    public enum Type {
        ADDED,
        REMOVED
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public Type getType() {
        return this.type;
    }

    public LimboDocumentChange(Type type, DocumentKey documentKey) {
        this.type = type;
        this.key = documentKey;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LimboDocumentChange)) {
            return false;
        }
        LimboDocumentChange limboDocumentChange = (LimboDocumentChange) obj;
        return this.type.equals(limboDocumentChange.getType()) && this.key.equals(limboDocumentChange.getKey());
    }

    public int hashCode() {
        return ((2077 + this.type.hashCode()) * 31) + this.key.hashCode();
    }
}
