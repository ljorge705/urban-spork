package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class QueryEngine {
    private static final String LOG_TAG = "QueryEngine";
    private IndexManager indexManager;
    private boolean initialized;
    private LocalDocumentsView localDocumentsView;

    public void initialize(LocalDocumentsView localDocumentsView, IndexManager indexManager) {
        this.localDocumentsView = localDocumentsView;
        this.indexManager = indexManager;
        this.initialized = true;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, SnapshotVersion snapshotVersion, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        Assert.hardAssert(this.initialized, "initialize() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapPerformQueryUsingIndex = performQueryUsingIndex(query);
        if (immutableSortedMapPerformQueryUsingIndex != null) {
            return immutableSortedMapPerformQueryUsingIndex;
        }
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapPerformQueryUsingRemoteKeys = performQueryUsingRemoteKeys(query, immutableSortedSet, snapshotVersion);
        return immutableSortedMapPerformQueryUsingRemoteKeys != null ? immutableSortedMapPerformQueryUsingRemoteKeys : executeFullCollectionScan(query);
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingIndex(Query query) {
        if (query.matchesAllDocuments()) {
            return null;
        }
        Target target = query.toTarget();
        IndexManager.IndexType indexType = this.indexManager.getIndexType(target);
        if (indexType.equals(IndexManager.IndexType.NONE)) {
            return null;
        }
        if (query.hasLimit() && indexType.equals(IndexManager.IndexType.PARTIAL)) {
            return performQueryUsingIndex(query.limitToFirst(-1L));
        }
        List<DocumentKey> documentsMatchingTarget = this.indexManager.getDocumentsMatchingTarget(target);
        Assert.hardAssert(documentsMatchingTarget != null, "index manager must return results for partial and full indexes.", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> documents = this.localDocumentsView.getDocuments(documentsMatchingTarget);
        FieldIndex.IndexOffset minOffset = this.indexManager.getMinOffset(target);
        ImmutableSortedSet<Document> immutableSortedSetApplyQuery = applyQuery(query, documents);
        if (needsRefill(query, documentsMatchingTarget.size(), immutableSortedSetApplyQuery, minOffset.getReadTime())) {
            return performQueryUsingIndex(query.limitToFirst(-1L));
        }
        return appendRemainingResults(immutableSortedSetApplyQuery, query, minOffset);
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingRemoteKeys(Query query, ImmutableSortedSet<DocumentKey> immutableSortedSet, SnapshotVersion snapshotVersion) {
        if (query.matchesAllDocuments() || snapshotVersion.equals(SnapshotVersion.NONE)) {
            return null;
        }
        ImmutableSortedSet<Document> immutableSortedSetApplyQuery = applyQuery(query, this.localDocumentsView.getDocuments(immutableSortedSet));
        if (needsRefill(query, immutableSortedSet.size(), immutableSortedSetApplyQuery, snapshotVersion)) {
            return null;
        }
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Re-using previous result from %s to execute query: %s", snapshotVersion.toString(), query.toString());
        }
        return appendRemainingResults(immutableSortedSetApplyQuery, query, FieldIndex.IndexOffset.createSuccessor(snapshotVersion, -1));
    }

    private ImmutableSortedSet<Document> applyQuery(Query query, ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        ImmutableSortedSet<Document> immutableSortedSet = new ImmutableSortedSet<>(Collections.emptyList(), query.comparator());
        Iterator<Map.Entry<DocumentKey, Document>> it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Document value = it.next().getValue();
            if (query.matches(value)) {
                immutableSortedSet = immutableSortedSet.insert(value);
            }
        }
        return immutableSortedSet;
    }

    private boolean needsRefill(Query query, int i, ImmutableSortedSet<Document> immutableSortedSet, SnapshotVersion snapshotVersion) {
        Document minEntry;
        if (!query.hasLimit()) {
            return false;
        }
        if (i != immutableSortedSet.size()) {
            return true;
        }
        if (query.getLimitType() == Query.LimitType.LIMIT_TO_FIRST) {
            minEntry = immutableSortedSet.getMaxEntry();
        } else {
            minEntry = immutableSortedSet.getMinEntry();
        }
        if (minEntry == null) {
            return false;
        }
        return minEntry.hasPendingWrites() || minEntry.getVersion().compareTo(snapshotVersion) > 0;
    }

    private ImmutableSortedMap<DocumentKey, Document> executeFullCollectionScan(Query query) {
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Using full collection scan to execute query: %s", query.toString());
        }
        return this.localDocumentsView.getDocumentsMatchingQuery(query, FieldIndex.IndexOffset.NONE);
    }

    private ImmutableSortedMap<DocumentKey, Document> appendRemainingResults(Iterable<Document> iterable, Query query, FieldIndex.IndexOffset indexOffset) {
        ImmutableSortedMap<DocumentKey, Document> documentsMatchingQuery = this.localDocumentsView.getDocumentsMatchingQuery(query, indexOffset);
        for (Document document : iterable) {
            documentsMatchingQuery = documentsMatchingQuery.insert(document.getKey(), document);
        }
        return documentsMatchingQuery;
    }
}
