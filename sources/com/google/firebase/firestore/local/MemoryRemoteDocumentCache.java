package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

/* loaded from: classes2.dex */
final class MemoryRemoteDocumentCache implements RemoteDocumentCache {
    private ImmutableSortedMap<DocumentKey, Document> docs = DocumentCollections.emptyDocumentMap();
    private IndexManager indexManager;

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    MemoryRemoteDocumentCache() {
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        this.docs = this.docs.insert(mutableDocument.getKey(), mutableDocument.mutableCopy().setReadTime(snapshotVersion));
        this.indexManager.addToCollectionParentIndex(mutableDocument.getKey().getCollectionPath());
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void removeAll(Collection<DocumentKey> collection) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapEmptyDocumentMap = DocumentCollections.emptyDocumentMap();
        for (DocumentKey documentKey : collection) {
            this.docs = this.docs.remove(documentKey);
            immutableSortedMapEmptyDocumentMap = immutableSortedMapEmptyDocumentMap.insert(documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE));
        }
        this.indexManager.updateIndexEntries(immutableSortedMapEmptyDocumentMap);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public MutableDocument get(DocumentKey documentKey) {
        Document document = this.docs.get(documentKey);
        return document != null ? document.mutableCopy() : MutableDocument.newInvalidDocument(documentKey);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable) {
        HashMap map = new HashMap();
        for (DocumentKey documentKey : iterable) {
            map.put(documentKey, get(documentKey));
        }
        return map;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i) {
        throw new UnsupportedOperationException("getAll(String, IndexOffset, int) is not supported.");
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset indexOffset, @Nonnull Set<DocumentKey> set) {
        HashMap map = new HashMap();
        Iterator<Map.Entry<DocumentKey, Document>> itIteratorFrom = this.docs.iteratorFrom(DocumentKey.fromPath(query.getPath().append("")));
        while (itIteratorFrom.hasNext()) {
            Map.Entry<DocumentKey, Document> next = itIteratorFrom.next();
            Document value = next.getValue();
            DocumentKey key = next.getKey();
            if (!query.getPath().isPrefixOf(key.getPath())) {
                break;
            }
            if (key.getPath().length() <= query.getPath().length() + 1 && FieldIndex.IndexOffset.fromDocument(value).compareTo(indexOffset) > 0 && (set.contains(value.getKey()) || query.matches(value))) {
                map.put(value.getKey(), value.mutableCopy());
            }
        }
        return map;
    }

    Iterable<Document> getDocuments() {
        return new DocumentIterable();
    }

    long getByteSize(LocalSerializer localSerializer) {
        long serializedSize = 0;
        while (new DocumentIterable().iterator().hasNext()) {
            serializedSize += localSerializer.encodeMaybeDocument(r0.next()).getSerializedSize();
        }
        return serializedSize;
    }

    private class DocumentIterable implements Iterable<Document> {
        private DocumentIterable() {
        }

        @Override // java.lang.Iterable
        public Iterator<Document> iterator() {
            final Iterator it = MemoryRemoteDocumentCache.this.docs.iterator();
            return new Iterator<Document>() { // from class: com.google.firebase.firestore.local.MemoryRemoteDocumentCache.DocumentIterable.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public Document next() {
                    return (Document) ((Map.Entry) it.next()).getValue();
                }
            };
        }
    }
}
