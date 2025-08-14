package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.BloomFilter;
import com.google.firebase.firestore.remote.TestingHooks;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class WatchChangeAggregator {
    private static final String LOG_TAG = "WatchChangeAggregator";
    private final TargetMetadataProvider targetMetadataProvider;
    private final Map<Integer, TargetState> targetStates = new HashMap();
    private Map<DocumentKey, MutableDocument> pendingDocumentUpdates = new HashMap();
    private Map<DocumentKey, Set<Integer>> pendingDocumentTargetMapping = new HashMap();
    private Map<Integer, QueryPurpose> pendingTargetResets = new HashMap();

    private enum BloomFilterApplicationStatus {
        SUCCESS,
        SKIPPED,
        FALSE_POSITIVE
    }

    public interface TargetMetadataProvider {
        DatabaseId getDatabaseId();

        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        TargetData getTargetDataForTarget(int i);
    }

    public WatchChangeAggregator(TargetMetadataProvider targetMetadataProvider) {
        this.targetMetadataProvider = targetMetadataProvider;
    }

    public void handleDocumentChange(WatchChange.DocumentChange documentChange) {
        MutableDocument newDocument = documentChange.getNewDocument();
        DocumentKey documentKey = documentChange.getDocumentKey();
        Iterator<Integer> it = documentChange.getUpdatedTargetIds().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (newDocument != null && newDocument.isFoundDocument()) {
                addDocumentToTarget(iIntValue, newDocument);
            } else {
                removeDocumentFromTarget(iIntValue, documentKey, newDocument);
            }
        }
        Iterator<Integer> it2 = documentChange.getRemovedTargetIds().iterator();
        while (it2.hasNext()) {
            removeDocumentFromTarget(it2.next().intValue(), documentKey, documentChange.getNewDocument());
        }
    }

    public void handleTargetChange(WatchChange.WatchTargetChange watchTargetChange) {
        Iterator<Integer> it = getTargetIds(watchTargetChange).iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            TargetState targetStateEnsureTargetState = ensureTargetState(iIntValue);
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[watchTargetChange.getChangeType().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    targetStateEnsureTargetState.recordTargetResponse();
                    if (!targetStateEnsureTargetState.isPending()) {
                        targetStateEnsureTargetState.clearChanges();
                    }
                    targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                } else if (i == 3) {
                    targetStateEnsureTargetState.recordTargetResponse();
                    if (!targetStateEnsureTargetState.isPending()) {
                        removeTarget(iIntValue);
                    }
                    Assert.hardAssert(watchTargetChange.getCause() == null, "WatchChangeAggregator does not handle errored targets", new Object[0]);
                } else if (i != 4) {
                    if (i == 5) {
                        if (isActiveTarget(iIntValue)) {
                            resetTarget(iIntValue);
                            targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                        }
                    } else {
                        throw Assert.fail("Unknown target watch change state: %s", watchTargetChange.getChangeType());
                    }
                } else if (isActiveTarget(iIntValue)) {
                    targetStateEnsureTargetState.markCurrent();
                    targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                }
            } else if (isActiveTarget(iIntValue)) {
                targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
            }
        }
    }

    /* renamed from: com.google.firebase.firestore.remote.WatchChangeAggregator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType;

        static {
            int[] iArr = new int[WatchChange.WatchTargetChangeType.values().length];
            $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType = iArr;
            try {
                iArr[WatchChange.WatchTargetChangeType.NoChange.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Added.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Removed.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Current.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Reset.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Collection<Integer> getTargetIds(WatchChange.WatchTargetChange watchTargetChange) {
        List<Integer> targetIds = watchTargetChange.getTargetIds();
        if (!targetIds.isEmpty()) {
            return targetIds;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.targetStates.keySet()) {
            if (isActiveTarget(num.intValue())) {
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    public void handleExistenceFilter(WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange) {
        QueryPurpose queryPurpose;
        int targetId = existenceFilterWatchChange.getTargetId();
        int count = existenceFilterWatchChange.getExistenceFilter().getCount();
        TargetData targetDataQueryDataForActiveTarget = queryDataForActiveTarget(targetId);
        if (targetDataQueryDataForActiveTarget != null) {
            Target target = targetDataQueryDataForActiveTarget.getTarget();
            if (target.isDocumentQuery()) {
                if (count == 0) {
                    DocumentKey documentKeyFromPath = DocumentKey.fromPath(target.getPath());
                    removeDocumentFromTarget(targetId, documentKeyFromPath, MutableDocument.newNoDocument(documentKeyFromPath, SnapshotVersion.NONE));
                    return;
                } else {
                    Assert.hardAssert(count == 1, "Single document existence filter with count: %d", Integer.valueOf(count));
                    return;
                }
            }
            int currentDocumentCountForTarget = getCurrentDocumentCountForTarget(targetId);
            if (currentDocumentCountForTarget != count) {
                BloomFilterApplicationStatus bloomFilterApplicationStatusApplyBloomFilter = applyBloomFilter(existenceFilterWatchChange, currentDocumentCountForTarget);
                if (bloomFilterApplicationStatusApplyBloomFilter != BloomFilterApplicationStatus.SUCCESS) {
                    resetTarget(targetId);
                    if (bloomFilterApplicationStatusApplyBloomFilter == BloomFilterApplicationStatus.FALSE_POSITIVE) {
                        queryPurpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH_BLOOM;
                    } else {
                        queryPurpose = QueryPurpose.EXISTENCE_FILTER_MISMATCH;
                    }
                    this.pendingTargetResets.put(Integer.valueOf(targetId), queryPurpose);
                }
                TestingHooks.getInstance().notifyOnExistenceFilterMismatch(TestingHooks.ExistenceFilterMismatchInfo.from(bloomFilterApplicationStatusApplyBloomFilter == BloomFilterApplicationStatus.SUCCESS, currentDocumentCountForTarget, existenceFilterWatchChange.getExistenceFilter()));
            }
        }
    }

    private BloomFilterApplicationStatus applyBloomFilter(WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange, int i) {
        int count = existenceFilterWatchChange.getExistenceFilter().getCount();
        com.google.firestore.v1.BloomFilter unchangedNames = existenceFilterWatchChange.getExistenceFilter().getUnchangedNames();
        if (unchangedNames == null || !unchangedNames.hasBits()) {
            return BloomFilterApplicationStatus.SKIPPED;
        }
        try {
            BloomFilter bloomFilterCreate = BloomFilter.create(unchangedNames.getBits().getBitmap(), unchangedNames.getBits().getPadding(), unchangedNames.getHashCount());
            if (bloomFilterCreate.getBitCount() == 0) {
                return BloomFilterApplicationStatus.SKIPPED;
            }
            if (count != i - filterRemovedDocuments(bloomFilterCreate, existenceFilterWatchChange.getTargetId())) {
                return BloomFilterApplicationStatus.FALSE_POSITIVE;
            }
            return BloomFilterApplicationStatus.SUCCESS;
        } catch (BloomFilter.BloomFilterCreateException e) {
            Logger.warn(LOG_TAG, "Applying bloom filter failed: (" + e.getMessage() + "); ignoring the bloom filter and falling back to full re-query.", new Object[0]);
            return BloomFilterApplicationStatus.SKIPPED;
        }
    }

    private int filterRemovedDocuments(BloomFilter bloomFilter, int i) {
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(i).iterator();
        int i2 = 0;
        while (it.hasNext()) {
            DocumentKey next = it.next();
            DatabaseId databaseId = this.targetMetadataProvider.getDatabaseId();
            if (!bloomFilter.mightContain("projects/" + databaseId.getProjectId() + "/databases/" + databaseId.getDatabaseId() + "/documents/" + next.getPath().canonicalString())) {
                removeDocumentFromTarget(i, next, null);
                i2++;
            }
        }
        return i2;
    }

    public RemoteEvent createRemoteEvent(SnapshotVersion snapshotVersion) {
        HashMap map = new HashMap();
        for (Map.Entry<Integer, TargetState> entry : this.targetStates.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            TargetState value = entry.getValue();
            TargetData targetDataQueryDataForActiveTarget = queryDataForActiveTarget(iIntValue);
            if (targetDataQueryDataForActiveTarget != null) {
                if (value.isCurrent() && targetDataQueryDataForActiveTarget.getTarget().isDocumentQuery()) {
                    DocumentKey documentKeyFromPath = DocumentKey.fromPath(targetDataQueryDataForActiveTarget.getTarget().getPath());
                    if (this.pendingDocumentUpdates.get(documentKeyFromPath) == null && !targetContainsDocument(iIntValue, documentKeyFromPath)) {
                        removeDocumentFromTarget(iIntValue, documentKeyFromPath, MutableDocument.newNoDocument(documentKeyFromPath, snapshotVersion));
                    }
                }
                if (value.hasChanges()) {
                    map.put(Integer.valueOf(iIntValue), value.toTargetChange());
                    value.clearChanges();
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry<DocumentKey, Set<Integer>> entry2 : this.pendingDocumentTargetMapping.entrySet()) {
            DocumentKey key = entry2.getKey();
            Iterator<Integer> it = entry2.getValue().iterator();
            while (true) {
                if (it.hasNext()) {
                    TargetData targetDataQueryDataForActiveTarget2 = queryDataForActiveTarget(it.next().intValue());
                    if (targetDataQueryDataForActiveTarget2 == null || targetDataQueryDataForActiveTarget2.getPurpose().equals(QueryPurpose.LIMBO_RESOLUTION)) {
                    }
                } else {
                    hashSet.add(key);
                    break;
                }
            }
        }
        Iterator<MutableDocument> it2 = this.pendingDocumentUpdates.values().iterator();
        while (it2.hasNext()) {
            it2.next().setReadTime(snapshotVersion);
        }
        RemoteEvent remoteEvent = new RemoteEvent(snapshotVersion, Collections.unmodifiableMap(map), Collections.unmodifiableMap(this.pendingTargetResets), Collections.unmodifiableMap(this.pendingDocumentUpdates), Collections.unmodifiableSet(hashSet));
        this.pendingDocumentUpdates = new HashMap();
        this.pendingDocumentTargetMapping = new HashMap();
        this.pendingTargetResets = new HashMap();
        return remoteEvent;
    }

    private void addDocumentToTarget(int i, MutableDocument mutableDocument) {
        DocumentViewChange.Type type;
        if (isActiveTarget(i)) {
            if (targetContainsDocument(i, mutableDocument.getKey())) {
                type = DocumentViewChange.Type.MODIFIED;
            } else {
                type = DocumentViewChange.Type.ADDED;
            }
            ensureTargetState(i).addDocumentChange(mutableDocument.getKey(), type);
            this.pendingDocumentUpdates.put(mutableDocument.getKey(), mutableDocument);
            ensureDocumentTargetMapping(mutableDocument.getKey()).add(Integer.valueOf(i));
        }
    }

    private void removeDocumentFromTarget(int i, DocumentKey documentKey, MutableDocument mutableDocument) {
        if (isActiveTarget(i)) {
            TargetState targetStateEnsureTargetState = ensureTargetState(i);
            if (targetContainsDocument(i, documentKey)) {
                targetStateEnsureTargetState.addDocumentChange(documentKey, DocumentViewChange.Type.REMOVED);
            } else {
                targetStateEnsureTargetState.removeDocumentChange(documentKey);
            }
            ensureDocumentTargetMapping(documentKey).add(Integer.valueOf(i));
            if (mutableDocument != null) {
                this.pendingDocumentUpdates.put(documentKey, mutableDocument);
            }
        }
    }

    void removeTarget(int i) {
        this.targetStates.remove(Integer.valueOf(i));
    }

    private int getCurrentDocumentCountForTarget(int i) {
        TargetChange targetChange = ensureTargetState(i).toTargetChange();
        return (this.targetMetadataProvider.getRemoteKeysForTarget(i).size() + targetChange.getAddedDocuments().size()) - targetChange.getRemovedDocuments().size();
    }

    void recordPendingTargetRequest(int i) {
        ensureTargetState(i).recordPendingTargetRequest();
    }

    private TargetState ensureTargetState(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState != null) {
            return targetState;
        }
        TargetState targetState2 = new TargetState();
        this.targetStates.put(Integer.valueOf(i), targetState2);
        return targetState2;
    }

    private Set<Integer> ensureDocumentTargetMapping(DocumentKey documentKey) {
        Set<Integer> set = this.pendingDocumentTargetMapping.get(documentKey);
        if (set != null) {
            return set;
        }
        HashSet hashSet = new HashSet();
        this.pendingDocumentTargetMapping.put(documentKey, hashSet);
        return hashSet;
    }

    private boolean isActiveTarget(int i) {
        return queryDataForActiveTarget(i) != null;
    }

    private TargetData queryDataForActiveTarget(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState == null || !targetState.isPending()) {
            return this.targetMetadataProvider.getTargetDataForTarget(i);
        }
        return null;
    }

    private void resetTarget(int i) {
        Assert.hardAssert((this.targetStates.get(Integer.valueOf(i)) == null || this.targetStates.get(Integer.valueOf(i)).isPending()) ? false : true, "Should only reset active targets", new Object[0]);
        this.targetStates.put(Integer.valueOf(i), new TargetState());
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(i).iterator();
        while (it.hasNext()) {
            removeDocumentFromTarget(i, it.next(), null);
        }
    }

    private boolean targetContainsDocument(int i, DocumentKey documentKey) {
        return this.targetMetadataProvider.getRemoteKeysForTarget(i).contains(documentKey);
    }
}
