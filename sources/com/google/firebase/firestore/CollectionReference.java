package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;

/* loaded from: classes2.dex */
public class CollectionReference extends Query {
    CollectionReference(ResourcePath resourcePath, FirebaseFirestore firebaseFirestore) {
        super(com.google.firebase.firestore.core.Query.atPath(resourcePath), firebaseFirestore);
        if (resourcePath.length() % 2 != 1) {
            throw new IllegalArgumentException("Invalid collection reference. Collection references must have an odd number of segments, but " + resourcePath.canonicalString() + " has " + resourcePath.length());
        }
    }

    public String getId() {
        return this.query.getPath().getLastSegment();
    }

    public DocumentReference getParent() {
        ResourcePath resourcePathPopLast = this.query.getPath().popLast();
        if (resourcePathPopLast.isEmpty()) {
            return null;
        }
        return new DocumentReference(DocumentKey.fromPath(resourcePathPopLast), this.firestore);
    }

    public String getPath() {
        return this.query.getPath().canonicalString();
    }

    public DocumentReference document() {
        return document(Util.autoId());
    }

    public DocumentReference document(String str) {
        Preconditions.checkNotNull(str, "Provided document path must not be null.");
        return DocumentReference.forPath(this.query.getPath().append(ResourcePath.fromString(str)), this.firestore);
    }

    public Task<DocumentReference> add(Object obj) {
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        final DocumentReference documentReferenceDocument = document();
        return documentReferenceDocument.set(obj).continueWith(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.CollectionReference$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return CollectionReference.lambda$add$0(documentReferenceDocument, task);
            }
        });
    }

    static /* synthetic */ DocumentReference lambda$add$0(DocumentReference documentReference, Task task) throws Exception {
        task.getResult();
        return documentReference;
    }
}
