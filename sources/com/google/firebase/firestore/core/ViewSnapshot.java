package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ViewSnapshot {
    private final List<DocumentViewChange> changes;
    private final boolean didSyncStateChange;
    private final DocumentSet documents;
    private boolean excludesMetadataChanges;
    private boolean hasCachedResults;
    private final boolean isFromCache;
    private final ImmutableSortedSet<DocumentKey> mutatedKeys;
    private final DocumentSet oldDocuments;
    private final Query query;

    public enum SyncState {
        NONE,
        LOCAL,
        SYNCED
    }

    public boolean didSyncStateChange() {
        return this.didSyncStateChange;
    }

    public boolean excludesMetadataChanges() {
        return this.excludesMetadataChanges;
    }

    public List<DocumentViewChange> getChanges() {
        return this.changes;
    }

    public DocumentSet getDocuments() {
        return this.documents;
    }

    public ImmutableSortedSet<DocumentKey> getMutatedKeys() {
        return this.mutatedKeys;
    }

    public DocumentSet getOldDocuments() {
        return this.oldDocuments;
    }

    public Query getQuery() {
        return this.query;
    }

    public boolean hasCachedResults() {
        return this.hasCachedResults;
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    public ViewSnapshot(Query query, DocumentSet documentSet, DocumentSet documentSet2, List<DocumentViewChange> list, boolean z, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z2, boolean z3, boolean z4) {
        this.query = query;
        this.documents = documentSet;
        this.oldDocuments = documentSet2;
        this.changes = list;
        this.isFromCache = z;
        this.mutatedKeys = immutableSortedSet;
        this.didSyncStateChange = z2;
        this.excludesMetadataChanges = z3;
        this.hasCachedResults = z4;
    }

    public static ViewSnapshot fromInitialDocuments(Query query, DocumentSet documentSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z, boolean z2, boolean z3) {
        ArrayList arrayList = new ArrayList();
        Iterator<Document> it = documentSet.iterator();
        while (it.hasNext()) {
            arrayList.add(DocumentViewChange.create(DocumentViewChange.Type.ADDED, it.next()));
        }
        return new ViewSnapshot(query, documentSet, DocumentSet.emptySet(query.comparator()), arrayList, z, immutableSortedSet, true, z2, z3);
    }

    public boolean hasPendingWrites() {
        return !this.mutatedKeys.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewSnapshot)) {
            return false;
        }
        ViewSnapshot viewSnapshot = (ViewSnapshot) obj;
        if (this.isFromCache == viewSnapshot.isFromCache && this.didSyncStateChange == viewSnapshot.didSyncStateChange && this.excludesMetadataChanges == viewSnapshot.excludesMetadataChanges && this.query.equals(viewSnapshot.query) && this.mutatedKeys.equals(viewSnapshot.mutatedKeys) && this.documents.equals(viewSnapshot.documents) && this.oldDocuments.equals(viewSnapshot.oldDocuments) && this.hasCachedResults == viewSnapshot.hasCachedResults) {
            return this.changes.equals(viewSnapshot.changes);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((this.query.hashCode() * 31) + this.documents.hashCode()) * 31) + this.oldDocuments.hashCode()) * 31) + this.changes.hashCode()) * 31) + this.mutatedKeys.hashCode()) * 31) + (this.isFromCache ? 1 : 0)) * 31) + (this.didSyncStateChange ? 1 : 0)) * 31) + (this.excludesMetadataChanges ? 1 : 0)) * 31) + (this.hasCachedResults ? 1 : 0);
    }

    public String toString() {
        return "ViewSnapshot(" + this.query + ", " + this.documents + ", " + this.oldDocuments + ", " + this.changes + ", isFromCache=" + this.isFromCache + ", mutatedKeys=" + this.mutatedKeys.size() + ", didSyncStateChange=" + this.didSyncStateChange + ", excludesMetadataChanges=" + this.excludesMetadataChanges + ", hasCachedResults=" + this.hasCachedResults + ")";
    }
}
