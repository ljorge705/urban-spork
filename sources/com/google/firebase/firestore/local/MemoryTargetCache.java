package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes2.dex */
final class MemoryTargetCache implements TargetCache {
    private int highestTargetId;
    private final MemoryPersistence persistence;
    private final Map<Target, TargetData> targets = new HashMap();
    private final ReferenceSet references = new ReferenceSet();
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private long highestSequenceNumber = 0;

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getHighestListenSequenceNumber() {
        return this.highestSequenceNumber;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
    }

    MemoryTargetCache(MemoryPersistence memoryPersistence) {
        this.persistence = memoryPersistence;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public long getTargetCount() {
        return this.targets.size();
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void forEachTarget(Consumer<TargetData> consumer) {
        Iterator<TargetData> it = this.targets.values().iterator();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addTargetData(TargetData targetData) {
        this.targets.put(targetData.getTarget(), targetData);
        int targetId = targetData.getTargetId();
        if (targetId > this.highestTargetId) {
            this.highestTargetId = targetId;
        }
        if (targetData.getSequenceNumber() > this.highestSequenceNumber) {
            this.highestSequenceNumber = targetData.getSequenceNumber();
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void updateTargetData(TargetData targetData) {
        addTargetData(targetData);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeTargetData(TargetData targetData) {
        this.targets.remove(targetData.getTarget());
        this.references.removeReferencesForId(targetData.getTargetId());
    }

    int removeQueries(long j, SparseArray<?> sparseArray) {
        Iterator<Map.Entry<Target, TargetData>> it = this.targets.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry<Target, TargetData> next = it.next();
            int targetId = next.getValue().getTargetId();
            if (next.getValue().getSequenceNumber() <= j && sparseArray.get(targetId) == null) {
                it.remove();
                removeMatchingKeysForTargetId(targetId);
                i++;
            }
        }
        return i;
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public TargetData getTargetData(Target target) {
        return this.targets.get(target);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        this.references.addReferences(immutableSortedSet, i);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            referenceDelegate.addReference(it.next());
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i) {
        this.references.removeReferences(immutableSortedSet, i);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        Iterator<DocumentKey> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            referenceDelegate.removeReference(it.next());
        }
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public void removeMatchingKeysForTargetId(int i) {
        this.references.removeReferencesForId(i);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int i) {
        return this.references.referencesForId(i);
    }

    @Override // com.google.firebase.firestore.local.TargetCache
    public boolean containsKey(DocumentKey documentKey) {
        return this.references.containsKey(documentKey);
    }

    long getByteSize(LocalSerializer localSerializer) {
        long serializedSize = 0;
        while (this.targets.entrySet().iterator().hasNext()) {
            serializedSize += localSerializer.encodeTargetData(r0.next().getValue()).getSerializedSize();
        }
        return serializedSize;
    }
}
