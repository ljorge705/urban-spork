package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
final class MemoryMutationQueue implements MutationQueue {
    private final MemoryIndexManager indexManager;
    private final MemoryPersistence persistence;
    private final List<MutationBatch> queue = new ArrayList();
    private ImmutableSortedSet<DocumentReference> batchesByDocumentKey = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_KEY);
    private int nextBatchId = 1;
    private ByteString lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;

    @Override // com.google.firebase.firestore.local.MutationQueue
    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    MemoryMutationQueue(MemoryPersistence memoryPersistence, User user) {
        this.persistence = memoryPersistence;
        this.indexManager = memoryPersistence.getIndexManager(user);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void start() {
        if (isEmpty()) {
            this.nextBatchId = 1;
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void acknowledgeBatch(MutationBatch mutationBatch, ByteString byteString) {
        int batchId = mutationBatch.getBatchId();
        int iIndexOfExistingBatchId = indexOfExistingBatchId(batchId, "acknowledged");
        Assert.hardAssert(iIndexOfExistingBatchId == 0, "Can only acknowledge the first batch in the mutation queue", new Object[0]);
        MutationBatch mutationBatch2 = this.queue.get(iIndexOfExistingBatchId);
        Assert.hardAssert(batchId == mutationBatch2.getBatchId(), "Queue ordering failure: expected batch %d, got batch %d", Integer.valueOf(batchId), Integer.valueOf(mutationBatch2.getBatchId()));
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(byteString);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void setLastStreamToken(ByteString byteString) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(byteString);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch addMutationBatch(Timestamp timestamp, List<Mutation> list, List<Mutation> list2) {
        Assert.hardAssert(!list2.isEmpty(), "Mutation batches should not be empty", new Object[0]);
        int i = this.nextBatchId;
        this.nextBatchId = i + 1;
        int size = this.queue.size();
        if (size > 0) {
            Assert.hardAssert(this.queue.get(size - 1).getBatchId() < i, "Mutation batchIds must be monotonically increasing order", new Object[0]);
        }
        MutationBatch mutationBatch = new MutationBatch(i, timestamp, list, list2);
        this.queue.add(mutationBatch);
        for (Mutation mutation : list2) {
            this.batchesByDocumentKey = this.batchesByDocumentKey.insert(new DocumentReference(mutation.getKey(), i));
            this.indexManager.addToCollectionParentIndex(mutation.getKey().getCollectionPath());
        }
        return mutationBatch;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch lookupMutationBatch(int i) {
        int iIndexOfBatchId = indexOfBatchId(i);
        if (iIndexOfBatchId < 0 || iIndexOfBatchId >= this.queue.size()) {
            return null;
        }
        MutationBatch mutationBatch = this.queue.get(iIndexOfBatchId);
        Assert.hardAssert(mutationBatch.getBatchId() == i, "If found batch must match", new Object[0]);
        return mutationBatch;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch getNextMutationBatchAfterBatchId(int i) {
        int iIndexOfBatchId = indexOfBatchId(i + 1);
        if (iIndexOfBatchId < 0) {
            iIndexOfBatchId = 0;
        }
        if (this.queue.size() > iIndexOfBatchId) {
            return this.queue.get(iIndexOfBatchId);
        }
        return null;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public int getHighestUnacknowledgedBatchId() {
        if (this.queue.isEmpty()) {
            return -1;
        }
        return this.nextBatchId - 1;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatches() {
        return Collections.unmodifiableList(this.queue);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        DocumentReference documentReference = new DocumentReference(documentKey, 0);
        ArrayList arrayList = new ArrayList();
        Iterator<DocumentReference> itIteratorFrom = this.batchesByDocumentKey.iteratorFrom(documentReference);
        while (itIteratorFrom.hasNext()) {
            DocumentReference next = itIteratorFrom.next();
            if (!documentKey.equals(next.getKey())) {
                break;
            }
            MutationBatch mutationBatchLookupMutationBatch = lookupMutationBatch(next.getId());
            Assert.hardAssert(mutationBatchLookupMutationBatch != null, "Batches in the index must exist in the main table", new Object[0]);
            arrayList.add(mutationBatchLookupMutationBatch);
        }
        return arrayList;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> iterable) {
        ImmutableSortedSet<Integer> immutableSortedSet = new ImmutableSortedSet<>(Collections.emptyList(), Util.comparator());
        for (DocumentKey documentKey : iterable) {
            Iterator<DocumentReference> itIteratorFrom = this.batchesByDocumentKey.iteratorFrom(new DocumentReference(documentKey, 0));
            while (itIteratorFrom.hasNext()) {
                DocumentReference next = itIteratorFrom.next();
                if (!documentKey.equals(next.getKey())) {
                    break;
                }
                immutableSortedSet = immutableSortedSet.insert(Integer.valueOf(next.getId()));
            }
        }
        return lookupMutationBatches(immutableSortedSet);
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath path = query.getPath();
        int length = path.length() + 1;
        DocumentReference documentReference = new DocumentReference(DocumentKey.fromPath(!DocumentKey.isDocumentKey(path) ? path.append("") : path), 0);
        ImmutableSortedSet<Integer> immutableSortedSet = new ImmutableSortedSet<>(Collections.emptyList(), Util.comparator());
        Iterator<DocumentReference> itIteratorFrom = this.batchesByDocumentKey.iteratorFrom(documentReference);
        while (itIteratorFrom.hasNext()) {
            DocumentReference next = itIteratorFrom.next();
            ResourcePath path2 = next.getKey().getPath();
            if (!path.isPrefixOf(path2)) {
                break;
            }
            if (path2.length() == length) {
                immutableSortedSet = immutableSortedSet.insert(Integer.valueOf(next.getId()));
            }
        }
        return lookupMutationBatches(immutableSortedSet);
    }

    private List<MutationBatch> lookupMutationBatches(ImmutableSortedSet<Integer> immutableSortedSet) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            MutationBatch mutationBatchLookupMutationBatch = lookupMutationBatch(it.next().intValue());
            if (mutationBatchLookupMutationBatch != null) {
                arrayList.add(mutationBatchLookupMutationBatch);
            }
        }
        return arrayList;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void removeMutationBatch(MutationBatch mutationBatch) {
        Assert.hardAssert(indexOfExistingBatchId(mutationBatch.getBatchId(), "removed") == 0, "Can only remove the first entry of the mutation queue", new Object[0]);
        this.queue.remove(0);
        ImmutableSortedSet<DocumentReference> immutableSortedSetRemove = this.batchesByDocumentKey;
        Iterator<Mutation> it = mutationBatch.getMutations().iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next().getKey();
            this.persistence.getReferenceDelegate().removeMutationReference(key);
            immutableSortedSetRemove = immutableSortedSetRemove.remove(new DocumentReference(key, mutationBatch.getBatchId()));
        }
        this.batchesByDocumentKey = immutableSortedSetRemove;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void performConsistencyCheck() {
        if (this.queue.isEmpty()) {
            Assert.hardAssert(this.batchesByDocumentKey.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty.", new Object[0]);
        }
    }

    boolean containsKey(DocumentKey documentKey) {
        Iterator<DocumentReference> itIteratorFrom = this.batchesByDocumentKey.iteratorFrom(new DocumentReference(documentKey, 0));
        if (itIteratorFrom.hasNext()) {
            return itIteratorFrom.next().getKey().equals(documentKey);
        }
        return false;
    }

    private int indexOfBatchId(int i) {
        if (this.queue.isEmpty()) {
            return 0;
        }
        return i - this.queue.get(0).getBatchId();
    }

    private int indexOfExistingBatchId(int i, String str) {
        int iIndexOfBatchId = indexOfBatchId(i);
        Assert.hardAssert(iIndexOfBatchId >= 0 && iIndexOfBatchId < this.queue.size(), "Batches must exist to be %s", str);
        return iIndexOfBatchId;
    }

    long getByteSize(LocalSerializer localSerializer) {
        long serializedSize = 0;
        while (this.queue.iterator().hasNext()) {
            serializedSize += localSerializer.encodeMutationBatch(r0.next()).getSerializedSize();
        }
        return serializedSize;
    }
}
