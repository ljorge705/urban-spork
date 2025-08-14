package com.google.firebase.firestore.core;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.firestore.model.Document;

/* loaded from: classes2.dex */
public class DocumentViewChange {
    private final Document document;
    private final Type type;

    public enum Type {
        REMOVED,
        ADDED,
        MODIFIED,
        METADATA
    }

    public Document getDocument() {
        return this.document;
    }

    public Type getType() {
        return this.type;
    }

    public static DocumentViewChange create(Type type, Document document) {
        return new DocumentViewChange(type, document);
    }

    private DocumentViewChange(Type type, Document document) {
        this.type = type;
        this.document = document;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DocumentViewChange)) {
            return false;
        }
        DocumentViewChange documentViewChange = (DocumentViewChange) obj;
        return this.type.equals(documentViewChange.type) && this.document.equals(documentViewChange.document);
    }

    public int hashCode() {
        return ((((1891 + this.type.hashCode()) * 31) + this.document.getKey().hashCode()) * 31) + this.document.getData().hashCode();
    }

    public String toString() {
        return "DocumentViewChange(" + this.document + Constants.SEPARATOR_COMMA + this.type + ")";
    }
}
