package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: classes2.dex */
class LocalDocumentsView {
    private final DocumentOverlayCache documentOverlayCache;
    private final IndexManager indexManager;
    private final MutationQueue mutationQueue;
    private final RemoteDocumentCache remoteDocumentCache;

    DocumentOverlayCache getDocumentOverlayCache() {
        return this.documentOverlayCache;
    }

    MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    LocalDocumentsView(RemoteDocumentCache remoteDocumentCache, MutationQueue mutationQueue, DocumentOverlayCache documentOverlayCache, IndexManager indexManager) {
        this.remoteDocumentCache = remoteDocumentCache;
        this.mutationQueue = mutationQueue;
        this.documentOverlayCache = documentOverlayCache;
        this.indexManager = indexManager;
    }

    Document getDocument(DocumentKey documentKey) {
        Overlay overlay = this.documentOverlayCache.getOverlay(documentKey);
        MutableDocument baseDocument = getBaseDocument(documentKey, overlay);
        if (overlay != null) {
            overlay.getMutation().applyToLocalView(baseDocument, FieldMask.EMPTY, Timestamp.now());
        }
        return baseDocument;
    }

    ImmutableSortedMap<DocumentKey, Document> getDocuments(Iterable<DocumentKey> iterable) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(iterable), new HashSet());
    }

    ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
        HashMap map2 = new HashMap();
        populateOverlays(map2, map.keySet());
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, OverlayedDocument> entry : computeViews(map, map2, set).entrySet()) {
            immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(entry.getKey(), entry.getValue().getDocument());
        }
        return immutableSortedMapEmptyDocumentMap;
    }

    Map<DocumentKey, OverlayedDocument> getOverlayedDocuments(Map<DocumentKey, MutableDocument> map) {
        HashMap map2 = new HashMap();
        populateOverlays(map2, map.keySet());
        return computeViews(map, map2, new HashSet());
    }

    private Map<DocumentKey, OverlayedDocument> computeViews(Map<DocumentKey, MutableDocument> map, Map<DocumentKey, Overlay> map2, Set<DocumentKey> set) {
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        for (MutableDocument mutableDocument : map.values()) {
            Overlay overlay = map2.get(mutableDocument.getKey());
            if (set.contains(mutableDocument.getKey()) && (overlay == null || (overlay.getMutation() instanceof PatchMutation))) {
                map3.put(mutableDocument.getKey(), mutableDocument);
            } else if (overlay != null) {
                map4.put(mutableDocument.getKey(), overlay.getMutation().getFieldMask());
                overlay.getMutation().applyToLocalView(mutableDocument, overlay.getMutation().getFieldMask(), Timestamp.now());
            } else {
                map4.put(mutableDocument.getKey(), FieldMask.EMPTY);
            }
        }
        map4.putAll(recalculateAndSaveOverlays(map3));
        HashMap map5 = new HashMap();
        for (Map.Entry<DocumentKey, MutableDocument> entry : map.entrySet()) {
            map5.put(entry.getKey(), new OverlayedDocument(entry.getValue(), (FieldMask) map4.get(entry.getKey())));
        }
        return map5;
    }

    private Map<DocumentKey, FieldMask> recalculateAndSaveOverlays(Map<DocumentKey, MutableDocument> map) {
        List<MutationBatch> allMutationBatchesAffectingDocumentKeys = this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(map.keySet());
        HashMap map2 = new HashMap();
        TreeMap treeMap = new TreeMap();
        for (MutationBatch mutationBatch : allMutationBatchesAffectingDocumentKeys) {
            for (DocumentKey documentKey : mutationBatch.getKeys()) {
                MutableDocument mutableDocument = map.get(documentKey);
                if (mutableDocument != null) {
                    map2.put(documentKey, mutationBatch.applyToLocalView(mutableDocument, map2.containsKey(documentKey) ? (FieldMask) map2.get(documentKey) : FieldMask.EMPTY));
                    int batchId = mutationBatch.getBatchId();
                    if (!treeMap.containsKey(Integer.valueOf(batchId))) {
                        treeMap.put(Integer.valueOf(batchId), new HashSet());
                    }
                    ((Set) treeMap.get(Integer.valueOf(batchId))).add(documentKey);
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : treeMap.descendingMap().entrySet()) {
            HashMap map3 = new HashMap();
            for (DocumentKey documentKey2 : (Set) entry.getValue()) {
                if (!hashSet.contains(documentKey2)) {
                    Mutation mutationCalculateOverlayMutation = Mutation.calculateOverlayMutation(map.get(documentKey2), (FieldMask) map2.get(documentKey2));
                    if (mutationCalculateOverlayMutation != null) {
                        map3.put(documentKey2, mutationCalculateOverlayMutation);
                    }
                    hashSet.add(documentKey2);
                }
            }
            this.documentOverlayCache.saveOverlays(((Integer) entry.getKey()).intValue(), map3);
        }
        return map2;
    }

    void recalculateAndSaveOverlays(Set<DocumentKey> set) {
        recalculateAndSaveOverlays(this.remoteDocumentCache.getAll(set));
    }

    ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        ResourcePath path = query.getPath();
        if (query.isDocumentQuery()) {
            return getDocumentsMatchingDocumentQuery(path);
        }
        if (query.isCollectionGroupQuery()) {
            return getDocumentsMatchingCollectionGroupQuery(query, indexOffset);
        }
        return getDocumentsMatchingCollectionQuery(query, indexOffset);
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath resourcePath) {
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        Document document = getDocument(DocumentKey.fromPath(resourcePath));
        return document.isFoundDocument() ? immutableSortedMapEmptyDocumentMap.insert(document.getKey(), document) : immutableSortedMapEmptyDocumentMap;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionGroup = query.getCollectionGroup();
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        Iterator<ResourcePath> it = this.indexManager.getCollectionParents(collectionGroup).iterator();
        while (it.hasNext()) {
            Iterator<Map.Entry<DocumentKey, Document>> it2 = getDocumentsMatchingCollectionQuery(query.asCollectionQueryAtPath(it.next().append(collectionGroup)), indexOffset).iterator();
            while (it2.hasNext()) {
                Map.Entry<DocumentKey, Document> next = it2.next();
                immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(next.getKey(), next.getValue());
            }
        }
        return immutableSortedMapEmptyDocumentMap;
    }

    LocalDocumentsResult getNextDocuments(String str, FieldIndex.IndexOffset indexOffset, int i) {
        Map<DocumentKey, Overlay> mapEmptyMap;
        Map<DocumentKey, MutableDocument> all = this.remoteDocumentCache.getAll(str, indexOffset, i);
        if (i - all.size() > 0) {
            mapEmptyMap = this.documentOverlayCache.getOverlays(str, indexOffset.getLargestBatchId(), i - all.size());
        } else {
            mapEmptyMap = Collections.emptyMap();
        }
        int iMax = -1;
        for (Overlay overlay : mapEmptyMap.values()) {
            if (!all.containsKey(overlay.getKey())) {
                all.put(overlay.getKey(), getBaseDocument(overlay.getKey(), overlay));
            }
            iMax = Math.max(iMax, overlay.getLargestBatchId());
        }
        populateOverlays(mapEmptyMap, all.keySet());
        return LocalDocumentsResult.fromOverlayedDocuments(iMax, computeViews(all, mapEmptyMap, Collections.emptySet()));
    }

    private void populateOverlays(Map<DocumentKey, Overlay> map, Set<DocumentKey> set) {
        TreeSet treeSet = new TreeSet();
        for (DocumentKey documentKey : set) {
            if (!map.containsKey(documentKey)) {
                treeSet.add(documentKey);
            }
        }
        map.putAll(this.documentOverlayCache.getOverlays(treeSet));
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(Query query, FieldIndex.IndexOffset indexOffset) {
        Map<DocumentKey, Overlay> overlays = this.documentOverlayCache.getOverlays(query.getPath(), indexOffset.getLargestBatchId());
        Map<DocumentKey, MutableDocument> documentsMatchingQuery = this.remoteDocumentCache.getDocumentsMatchingQuery(query, indexOffset, overlays.keySet());
        for (Map.Entry<DocumentKey, Overlay> entry : overlays.entrySet()) {
            if (!documentsMatchingQuery.containsKey(entry.getKey())) {
                documentsMatchingQuery.put(entry.getKey(), MutableDocument.newInvalidDocument(entry.getKey()));
            }
        }
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, MutableDocument> entry2 : documentsMatchingQuery.entrySet()) {
            Overlay overlay = overlays.get(entry2.getKey());
            if (overlay != null) {
                overlay.getMutation().applyToLocalView(entry2.getValue(), FieldMask.EMPTY, Timestamp.now());
            }
            if (query.matches(entry2.getValue())) {
                immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(entry2.getKey(), entry2.getValue());
            }
        }
        return immutableSortedMapEmptyDocumentMap;
    }

    private MutableDocument getBaseDocument(DocumentKey documentKey, Overlay overlay) {
        if (overlay == null || (overlay.getMutation() instanceof PatchMutation)) {
            return this.remoteDocumentCache.get(documentKey);
        }
        return MutableDocument.newInvalidDocument(documentKey);
    }
}
