package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ReferenceSet {
    private ImmutableSortedSet<DocumentReference> referencesByKey = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_KEY);
    private ImmutableSortedSet<DocumentReference> referencesByTarget = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_TARGET);

    public boolean isEmpty() {
        return this.referencesByKey.isEmpty();
    }

    public void addReference(DocumentKey documentKey, int i) {
        DocumentReference documentReference = new DocumentReference(documentKey, i);
        this.referencesByKey = this.referencesByKey.insert(documentReference);
        this.referencesByTarget = this.referencesByTarget.insert(documentReference);
    }

    public void addReferences(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            addReference(it.next(), i);
        }
    }

    public void removeReference(DocumentKey documentKey, int i) {
        removeReference(new DocumentReference(documentKey, i));
    }

    public void removeReferences(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            removeReference(it.next(), i);
        }
    }

    public ImmutableSortedSet<DocumentKey> removeReferencesForId(int i) {
        Iterator<DocumentReference> itIteratorFrom = this.referencesByTarget.iteratorFrom(new DocumentReference(DocumentKey.empty(), i));
        ImmutableSortedSet<DocumentKey> immutableSortedSetEmptyKeySet = DocumentKey.emptyKeySet();
        while (itIteratorFrom.hasNext()) {
            DocumentReference next = itIteratorFrom.next();
            if (next.getId() != i) {
                break;
            }
            immutableSortedSetEmptyKeySet = immutableSortedSetEmptyKeySet.insert(next.getKey());
            removeReference(next);
        }
        return immutableSortedSetEmptyKeySet;
    }

    public void removeAllReferences() {
        Iterator<DocumentReference> it = this.referencesByKey.iterator();
        while (it.hasNext()) {
            removeReference(it.next());
        }
    }

    private void removeReference(DocumentReference documentReference) {
        this.referencesByKey = this.referencesByKey.remove(documentReference);
        this.referencesByTarget = this.referencesByTarget.remove(documentReference);
    }

    public ImmutableSortedSet<DocumentKey> referencesForId(int i) {
        Iterator<DocumentReference> itIteratorFrom = this.referencesByTarget.iteratorFrom(new DocumentReference(DocumentKey.empty(), i));
        ImmutableSortedSet<DocumentKey> immutableSortedSetEmptyKeySet = DocumentKey.emptyKeySet();
        while (itIteratorFrom.hasNext()) {
            DocumentReference next = itIteratorFrom.next();
            if (next.getId() != i) {
                break;
            }
            immutableSortedSetEmptyKeySet = immutableSortedSetEmptyKeySet.insert(next.getKey());
        }
        return immutableSortedSetEmptyKeySet;
    }

    public boolean containsKey(DocumentKey documentKey) {
        Iterator<DocumentReference> itIteratorFrom = this.referencesByKey.iteratorFrom(new DocumentReference(documentKey, 0));
        if (itIteratorFrom.hasNext()) {
            return itIteratorFrom.next().getKey().equals(documentKey);
        }
        return false;
    }
}
