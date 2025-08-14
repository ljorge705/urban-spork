package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Util;
import java.util.Comparator;

/* loaded from: classes2.dex */
class DocumentReference {
    static final Comparator<DocumentReference> BY_KEY = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$0((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    static final Comparator<DocumentReference> BY_TARGET = new Comparator() { // from class: com.google.firebase.firestore.local.DocumentReference$$ExternalSyntheticLambda1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return DocumentReference.lambda$static$1((DocumentReference) obj, (DocumentReference) obj2);
        }
    };
    private final DocumentKey key;
    private final int targetOrBatchId;

    int getId() {
        return this.targetOrBatchId;
    }

    DocumentKey getKey() {
        return this.key;
    }

    public DocumentReference(DocumentKey documentKey, int i) {
        this.key = documentKey;
        this.targetOrBatchId = i;
    }

    static /* synthetic */ int lambda$static$0(DocumentReference documentReference, DocumentReference documentReference2) {
        int iCompareTo = documentReference.key.compareTo(documentReference2.key);
        return iCompareTo != 0 ? iCompareTo : Util.compareIntegers(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
    }

    static /* synthetic */ int lambda$static$1(DocumentReference documentReference, DocumentReference documentReference2) {
        int iCompareIntegers = Util.compareIntegers(documentReference.targetOrBatchId, documentReference2.targetOrBatchId);
        return iCompareIntegers != 0 ? iCompareIntegers : documentReference.key.compareTo(documentReference2.key);
    }
}
