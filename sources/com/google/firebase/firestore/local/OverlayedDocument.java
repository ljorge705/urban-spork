package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.mutation.FieldMask;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class OverlayedDocument {

    @Nullable
    private FieldMask mutatedFields;
    private Document overlayedDocument;

    public Document getDocument() {
        return this.overlayedDocument;
    }

    @Nullable
    public FieldMask getMutatedFields() {
        return this.mutatedFields;
    }

    OverlayedDocument(Document document, FieldMask fieldMask) {
        this.overlayedDocument = document;
        this.mutatedFields = fieldMask;
    }
}
