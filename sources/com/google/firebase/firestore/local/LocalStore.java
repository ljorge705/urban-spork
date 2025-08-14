package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleCallback;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.core.TargetIdGenerator;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class LocalStore implements BundleCallback {
    private static final long RESUME_TOKEN_MAX_AGE_SECONDS = TimeUnit.MINUTES.toSeconds(5);
    private final BundleCache bundleCache;
    private DocumentOverlayCache documentOverlayCache;
    private IndexManager indexManager;
    private LocalDocumentsView localDocuments;
    private final ReferenceSet localViewReferences;
    private MutationQueue mutationQueue;
    private final Persistence persistence;
    private final SparseArray<TargetData> queryDataByTarget;
    private final QueryEngine queryEngine;
    private final RemoteDocumentCache remoteDocuments;
    private final TargetCache targetCache;
    private final Map<Target, Integer> targetIdByTarget;
    private final TargetIdGenerator targetIdGenerator;

    public IndexManager getIndexManagerForCurrentUser() {
        return this.indexManager;
    }

    public LocalDocumentsView getLocalDocumentsForCurrentUser() {
        return this.localDocuments;
    }

    public LocalStore(Persistence persistence, QueryEngine queryEngine, User user) {
        Assert.hardAssert(persistence.isStarted(), "LocalStore was passed an unstarted persistence implementation", new Object[0]);
        this.persistence = persistence;
        this.queryEngine = queryEngine;
        TargetCache targetCache = persistence.getTargetCache();
        this.targetCache = targetCache;
        this.bundleCache = persistence.getBundleCache();
        this.targetIdGenerator = TargetIdGenerator.forTargetCache(targetCache.getHighestTargetId());
        this.remoteDocuments = persistence.getRemoteDocumentCache();
        ReferenceSet referenceSet = new ReferenceSet();
        this.localViewReferences = referenceSet;
        this.queryDataByTarget = new SparseArray<>();
        this.targetIdByTarget = new HashMap();
        persistence.getReferenceDelegate().setInMemoryPins(referenceSet);
        initializeUserComponents(user);
    }

    private void initializeUserComponents(User user) {
        IndexManager indexManager = this.persistence.getIndexManager(user);
        this.indexManager = indexManager;
        this.mutationQueue = this.persistence.getMutationQueue(user, indexManager);
        this.documentOverlayCache = this.persistence.getDocumentOverlayCache(user);
        this.localDocuments = new LocalDocumentsView(this.remoteDocuments, this.mutationQueue, this.documentOverlayCache, this.indexManager);
        this.remoteDocuments.setIndexManager(this.indexManager);
        this.queryEngine.initialize(this.localDocuments, this.indexManager);
    }

    public void start() {
        this.persistence.getOverlayMigrationManager().run();
        startIndexManager();
        startMutationQueue();
    }

    private void startIndexManager() {
        this.persistence.runTransaction("Start IndexManager", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5286xf9470190();
            }
        });
    }

    /* renamed from: lambda$startIndexManager$0$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5286xf9470190() {
        this.indexManager.start();
    }

    private void startMutationQueue() {
        this.persistence.runTransaction("Start MutationQueue", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5287xa2ea7b8c();
            }
        });
    }

    /* renamed from: lambda$startMutationQueue$1$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5287xa2ea7b8c() {
        this.mutationQueue.start();
    }

    public ImmutableSortedMap<DocumentKey, Document> handleUserChange(User user) {
        List<MutationBatch> allMutationBatches = this.mutationQueue.getAllMutationBatches();
        initializeUserComponents(user);
        startIndexManager();
        startMutationQueue();
        List<MutationBatch> allMutationBatches2 = this.mutationQueue.getAllMutationBatches();
        ImmutableSortedSet<DocumentKey> immutableSortedSetEmptyKeySet = DocumentKey.emptyKeySet();
        Iterator it = Arrays.asList(allMutationBatches, allMutationBatches2).iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                Iterator<Mutation> it3 = ((MutationBatch) it2.next()).getMutations().iterator();
                while (it3.hasNext()) {
                    immutableSortedSetEmptyKeySet = immutableSortedSetEmptyKeySet.insert(it3.next().getKey());
                }
            }
        }
        return this.localDocuments.getDocuments(immutableSortedSetEmptyKeySet);
    }

    public LocalDocumentsResult writeLocally(final List<Mutation> list) {
        final Timestamp timestampNow = Timestamp.now();
        final HashSet hashSet = new HashSet();
        Iterator<Mutation> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getKey());
        }
        return (LocalDocumentsResult) this.persistence.runTransaction("Locally write mutations", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5288x4f0ff958(hashSet, list, timestampNow);
            }
        });
    }

    /* renamed from: lambda$writeLocally$2$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ LocalDocumentsResult m5288x4f0ff958(Set set, List list, Timestamp timestamp) {
        Map<DocumentKey, MutableDocument> all = this.remoteDocuments.getAll(set);
        HashSet hashSet = new HashSet();
        for (Map.Entry<DocumentKey, MutableDocument> entry : all.entrySet()) {
            if (!entry.getValue().isValidDocument()) {
                hashSet.add(entry.getKey());
            }
        }
        Map<DocumentKey, OverlayedDocument> overlayedDocuments = this.localDocuments.getOverlayedDocuments(all);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Mutation mutation = (Mutation) it.next();
            ObjectValue objectValueExtractTransformBaseValue = mutation.extractTransformBaseValue(overlayedDocuments.get(mutation.getKey()).getDocument());
            if (objectValueExtractTransformBaseValue != null) {
                arrayList.add(new PatchMutation(mutation.getKey(), objectValueExtractTransformBaseValue, objectValueExtractTransformBaseValue.getFieldMask(), Precondition.exists(true)));
            }
        }
        MutationBatch mutationBatchAddMutationBatch = this.mutationQueue.addMutationBatch(timestamp, arrayList, list);
        this.documentOverlayCache.saveOverlays(mutationBatchAddMutationBatch.getBatchId(), mutationBatchAddMutationBatch.applyToLocalDocumentSet(overlayedDocuments, hashSet));
        return LocalDocumentsResult.fromOverlayedDocuments(mutationBatchAddMutationBatch.getBatchId(), overlayedDocuments);
    }

    public ImmutableSortedMap<DocumentKey, Document> acknowledgeBatch(final MutationBatchResult mutationBatchResult) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Acknowledge batch", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5271xd0c8f3b2(mutationBatchResult);
            }
        });
    }

    /* renamed from: lambda$acknowledgeBatch$3$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m5271xd0c8f3b2(MutationBatchResult mutationBatchResult) {
        MutationBatch batch = mutationBatchResult.getBatch();
        this.mutationQueue.acknowledgeBatch(batch, mutationBatchResult.getStreamToken());
        applyWriteToRemoteDocuments(mutationBatchResult);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(mutationBatchResult.getBatch().getBatchId());
        this.localDocuments.recalculateAndSaveOverlays(getKeysWithTransformResults(mutationBatchResult));
        return this.localDocuments.getDocuments(batch.getKeys());
    }

    private Set<DocumentKey> getKeysWithTransformResults(MutationBatchResult mutationBatchResult) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < mutationBatchResult.getMutationResults().size(); i++) {
            if (!mutationBatchResult.getMutationResults().get(i).getTransformResults().isEmpty()) {
                hashSet.add(mutationBatchResult.getBatch().getMutations().get(i).getKey());
            }
        }
        return hashSet;
    }

    public ImmutableSortedMap<DocumentKey, Document> rejectBatch(final int i) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Reject batch", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda11
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5281x1ab75fae(i);
            }
        });
    }

    /* renamed from: lambda$rejectBatch$4$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m5281x1ab75fae(int i) {
        MutationBatch mutationBatchLookupMutationBatch = this.mutationQueue.lookupMutationBatch(i);
        Assert.hardAssert(mutationBatchLookupMutationBatch != null, "Attempt to reject nonexistent batch!", new Object[0]);
        this.mutationQueue.removeMutationBatch(mutationBatchLookupMutationBatch);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(i);
        this.localDocuments.recalculateAndSaveOverlays(mutationBatchLookupMutationBatch.getKeys());
        return this.localDocuments.getDocuments(mutationBatchLookupMutationBatch.getKeys());
    }

    public int getHighestUnacknowledgedBatchId() {
        return this.mutationQueue.getHighestUnacknowledgedBatchId();
    }

    public ByteString getLastStreamToken() {
        return this.mutationQueue.getLastStreamToken();
    }

    public void setLastStreamToken(final ByteString byteString) {
        this.persistence.runTransaction("Set stream token", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5285x6c1835ed(byteString);
            }
        });
    }

    /* renamed from: lambda$setLastStreamToken$5$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5285x6c1835ed(ByteString byteString) {
        this.mutationQueue.setLastStreamToken(byteString);
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.targetCache.getLastRemoteSnapshotVersion();
    }

    public ImmutableSortedMap<DocumentKey, Document> applyRemoteEvent(final RemoteEvent remoteEvent) {
        final SnapshotVersion snapshotVersion = remoteEvent.getSnapshotVersion();
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply remote event", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda10
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5274xfb616aa7(remoteEvent, snapshotVersion);
            }
        });
    }

    /* renamed from: lambda$applyRemoteEvent$6$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m5274xfb616aa7(RemoteEvent remoteEvent, SnapshotVersion snapshotVersion) {
        Map<Integer, TargetChange> targetChanges = remoteEvent.getTargetChanges();
        long currentSequenceNumber = this.persistence.getReferenceDelegate().getCurrentSequenceNumber();
        for (Map.Entry<Integer, TargetChange> entry : targetChanges.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            TargetChange value = entry.getValue();
            TargetData targetData = this.queryDataByTarget.get(iIntValue);
            if (targetData != null) {
                this.targetCache.removeMatchingKeys(value.getRemovedDocuments(), iIntValue);
                this.targetCache.addMatchingKeys(value.getAddedDocuments(), iIntValue);
                TargetData targetDataWithSequenceNumber = targetData.withSequenceNumber(currentSequenceNumber);
                if (remoteEvent.getTargetMismatches().containsKey(Integer.valueOf(iIntValue))) {
                    targetDataWithSequenceNumber = targetDataWithSequenceNumber.withResumeToken(ByteString.EMPTY, SnapshotVersion.NONE).withLastLimboFreeSnapshotVersion(SnapshotVersion.NONE);
                } else if (!value.getResumeToken().isEmpty()) {
                    targetDataWithSequenceNumber = targetDataWithSequenceNumber.withResumeToken(value.getResumeToken(), remoteEvent.getSnapshotVersion());
                }
                this.queryDataByTarget.put(iIntValue, targetDataWithSequenceNumber);
                if (shouldPersistTargetData(targetData, targetDataWithSequenceNumber, value)) {
                    this.targetCache.updateTargetData(targetDataWithSequenceNumber);
                }
            }
        }
        Map<DocumentKey, MutableDocument> documentUpdates = remoteEvent.getDocumentUpdates();
        Set<DocumentKey> resolvedLimboDocuments = remoteEvent.getResolvedLimboDocuments();
        for (DocumentKey documentKey : documentUpdates.keySet()) {
            if (resolvedLimboDocuments.contains(documentKey)) {
                this.persistence.getReferenceDelegate().updateLimboDocument(documentKey);
            }
        }
        DocumentChangeResult documentChangeResultPopulateDocumentChanges = populateDocumentChanges(documentUpdates);
        Map<DocumentKey, MutableDocument> map = documentChangeResultPopulateDocumentChanges.changedDocuments;
        SnapshotVersion lastRemoteSnapshotVersion = this.targetCache.getLastRemoteSnapshotVersion();
        if (!snapshotVersion.equals(SnapshotVersion.NONE)) {
            Assert.hardAssert(snapshotVersion.compareTo(lastRemoteSnapshotVersion) >= 0, "Watch stream reverted to previous snapshot?? (%s < %s)", snapshotVersion, lastRemoteSnapshotVersion);
            this.targetCache.setLastRemoteSnapshotVersion(snapshotVersion);
        }
        return this.localDocuments.getLocalViewOfDocuments(map, documentChangeResultPopulateDocumentChanges.existenceChangedKeys);
    }

    private static class DocumentChangeResult {
        private final Map<DocumentKey, MutableDocument> changedDocuments;
        private final Set<DocumentKey> existenceChangedKeys;

        private DocumentChangeResult(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
            this.changedDocuments = map;
            this.existenceChangedKeys = set;
        }
    }

    private DocumentChangeResult populateDocumentChanges(Map<DocumentKey, MutableDocument> map) {
        HashMap map2 = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Map<DocumentKey, MutableDocument> all = this.remoteDocuments.getAll(map.keySet());
        for (Map.Entry<DocumentKey, MutableDocument> entry : map.entrySet()) {
            DocumentKey key = entry.getKey();
            MutableDocument value = entry.getValue();
            MutableDocument mutableDocument = all.get(key);
            if (value.isFoundDocument() != mutableDocument.isFoundDocument()) {
                hashSet.add(key);
            }
            if (value.isNoDocument() && value.getVersion().equals(SnapshotVersion.NONE)) {
                arrayList.add(value.getKey());
                map2.put(key, value);
            } else if (!mutableDocument.isValidDocument() || value.getVersion().compareTo(mutableDocument.getVersion()) > 0 || (value.getVersion().compareTo(mutableDocument.getVersion()) == 0 && mutableDocument.hasPendingWrites())) {
                Assert.hardAssert(!SnapshotVersion.NONE.equals(value.getReadTime()), "Cannot add a document when the remote version is zero", new Object[0]);
                this.remoteDocuments.add(value, value.getReadTime());
                map2.put(key, value);
            } else {
                Logger.debug("LocalStore", "Ignoring outdated watch update for %s.Current version: %s  Watch version: %s", key, mutableDocument.getVersion(), value.getVersion());
            }
        }
        this.remoteDocuments.removeAll(arrayList);
        return new DocumentChangeResult(map2, hashSet);
    }

    private static boolean shouldPersistTargetData(TargetData targetData, TargetData targetData2, TargetChange targetChange) {
        if (targetData.getResumeToken().isEmpty()) {
            return true;
        }
        long seconds = targetData2.getSnapshotVersion().getTimestamp().getSeconds() - targetData.getSnapshotVersion().getTimestamp().getSeconds();
        long j = RESUME_TOKEN_MAX_AGE_SECONDS;
        if (seconds < j && targetData2.getLastLimboFreeSnapshotVersion().getTimestamp().getSeconds() - targetData.getLastLimboFreeSnapshotVersion().getTimestamp().getSeconds() < j) {
            return targetChange != null && (targetChange.getAddedDocuments().size() + targetChange.getModifiedDocuments().size()) + targetChange.getRemovedDocuments().size() > 0;
        }
        return true;
    }

    public void notifyLocalViewChanges(final List<LocalViewChanges> list) {
        this.persistence.runTransaction("notifyLocalViewChanges", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5280x6c8bed50(list);
            }
        });
    }

    /* renamed from: lambda$notifyLocalViewChanges$7$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5280x6c8bed50(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            LocalViewChanges localViewChanges = (LocalViewChanges) it.next();
            int targetId = localViewChanges.getTargetId();
            this.localViewReferences.addReferences(localViewChanges.getAdded(), targetId);
            ImmutableSortedSet<DocumentKey> removed = localViewChanges.getRemoved();
            Iterator<DocumentKey> it2 = removed.iterator();
            while (it2.hasNext()) {
                this.persistence.getReferenceDelegate().removeReference(it2.next());
            }
            this.localViewReferences.removeReferences(removed, targetId);
            if (!localViewChanges.isFromCache()) {
                TargetData targetData = this.queryDataByTarget.get(targetId);
                Assert.hardAssert(targetData != null, "Can't set limbo-free snapshot version for unknown target: %s", Integer.valueOf(targetId));
                TargetData targetDataWithLastLimboFreeSnapshotVersion = targetData.withLastLimboFreeSnapshotVersion(targetData.getSnapshotVersion());
                this.queryDataByTarget.put(targetId, targetDataWithLastLimboFreeSnapshotVersion);
                if (shouldPersistTargetData(targetData, targetDataWithLastLimboFreeSnapshotVersion, null)) {
                    this.targetCache.updateTargetData(targetDataWithLastLimboFreeSnapshotVersion);
                }
            }
        }
    }

    public MutationBatch getNextMutationBatch(int i) {
        return this.mutationQueue.getNextMutationBatchAfterBatchId(i);
    }

    public Document readDocument(DocumentKey documentKey) {
        return this.localDocuments.getDocument(documentKey);
    }

    public TargetData allocateTarget(final Target target) {
        int targetId;
        TargetData targetData = this.targetCache.getTargetData(target);
        if (targetData != null) {
            targetId = targetData.getTargetId();
        } else {
            final AllocateQueryHolder allocateQueryHolder = new AllocateQueryHolder();
            this.persistence.runTransaction("Allocate target", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5272x5de91a9d(allocateQueryHolder, target);
                }
            });
            targetId = allocateQueryHolder.targetId;
            targetData = allocateQueryHolder.cached;
        }
        if (this.queryDataByTarget.get(targetId) == null) {
            this.queryDataByTarget.put(targetId, targetData);
            this.targetIdByTarget.put(target, Integer.valueOf(targetId));
        }
        return targetData;
    }

    /* renamed from: lambda$allocateTarget$8$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5272x5de91a9d(AllocateQueryHolder allocateQueryHolder, Target target) {
        allocateQueryHolder.targetId = this.targetIdGenerator.nextId();
        allocateQueryHolder.cached = new TargetData(target, allocateQueryHolder.targetId, this.persistence.getReferenceDelegate().getCurrentSequenceNumber(), QueryPurpose.LISTEN);
        this.targetCache.addTargetData(allocateQueryHolder.cached);
    }

    TargetData getTargetData(Target target) {
        Integer num = this.targetIdByTarget.get(target);
        if (num != null) {
            return this.queryDataByTarget.get(num.intValue());
        }
        return this.targetCache.getTargetData(target);
    }

    public boolean hasNewerBundle(final BundleMetadata bundleMetadata) {
        return ((Boolean) this.persistence.runTransaction("Has newer bundle", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda8
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5279x622d6455(bundleMetadata);
            }
        })).booleanValue();
    }

    /* renamed from: lambda$hasNewerBundle$9$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ Boolean m5279x622d6455(BundleMetadata bundleMetadata) {
        BundleMetadata bundleMetadata2 = this.bundleCache.getBundleMetadata(bundleMetadata.getBundleId());
        return Boolean.valueOf(bundleMetadata2 != null && bundleMetadata2.getCreateTime().compareTo(bundleMetadata.getCreateTime()) >= 0);
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public void saveBundle(final BundleMetadata bundleMetadata) {
        this.persistence.runTransaction("Save bundle", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5283x98d21eb5(bundleMetadata);
            }
        });
    }

    /* renamed from: lambda$saveBundle$10$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5283x98d21eb5(BundleMetadata bundleMetadata) {
        this.bundleCache.saveBundleMetadata(bundleMetadata);
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public ImmutableSortedMap<DocumentKey, Document> applyBundledDocuments(final ImmutableSortedMap<DocumentKey, MutableDocument> immutableSortedMap, String str) {
        final TargetData targetDataAllocateTarget = allocateTarget(newUmbrellaTarget(str));
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply bundle documents", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda12
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5273xda7cc59f(immutableSortedMap, targetDataAllocateTarget);
            }
        });
    }

    /* renamed from: lambda$applyBundledDocuments$11$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ ImmutableSortedMap m5273xda7cc59f(ImmutableSortedMap immutableSortedMap, TargetData targetData) {
        ImmutableSortedSet<DocumentKey> immutableSortedSetEmptyKeySet = DocumentKey.emptyKeySet();
        HashMap map = new HashMap();
        Iterator it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            DocumentKey documentKey = (DocumentKey) entry.getKey();
            MutableDocument mutableDocument = (MutableDocument) entry.getValue();
            if (mutableDocument.isFoundDocument()) {
                immutableSortedSetEmptyKeySet = immutableSortedSetEmptyKeySet.insert(documentKey);
            }
            map.put(documentKey, mutableDocument);
        }
        this.targetCache.removeMatchingKeysForTargetId(targetData.getTargetId());
        this.targetCache.addMatchingKeys(immutableSortedSetEmptyKeySet, targetData.getTargetId());
        DocumentChangeResult documentChangeResultPopulateDocumentChanges = populateDocumentChanges(map);
        return this.localDocuments.getLocalViewOfDocuments(documentChangeResultPopulateDocumentChanges.changedDocuments, documentChangeResultPopulateDocumentChanges.existenceChangedKeys);
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public void saveNamedQuery(final NamedQuery namedQuery, final ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        final TargetData targetDataAllocateTarget = allocateTarget(namedQuery.getBundledQuery().getTarget());
        final int targetId = targetDataAllocateTarget.getTargetId();
        this.persistence.runTransaction("Saved named query", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5284x3730c4c0(namedQuery, targetDataAllocateTarget, targetId, immutableSortedSet);
            }
        });
    }

    /* renamed from: lambda$saveNamedQuery$12$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5284x3730c4c0(NamedQuery namedQuery, TargetData targetData, int i, ImmutableSortedSet immutableSortedSet) {
        if (namedQuery.getReadTime().compareTo(targetData.getSnapshotVersion()) > 0) {
            TargetData targetDataWithResumeToken = targetData.withResumeToken(ByteString.EMPTY, namedQuery.getReadTime());
            this.queryDataByTarget.append(i, targetDataWithResumeToken);
            this.targetCache.updateTargetData(targetDataWithResumeToken);
            this.targetCache.removeMatchingKeysForTargetId(i);
            this.targetCache.addMatchingKeys(immutableSortedSet, i);
        }
        this.bundleCache.saveNamedQuery(namedQuery);
    }

    public NamedQuery getNamedQuery(final String str) {
        return (NamedQuery) this.persistence.runTransaction("Get named query", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda9
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5278x4cee2ade(str);
            }
        });
    }

    /* renamed from: lambda$getNamedQuery$13$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ NamedQuery m5278x4cee2ade(String str) {
        return this.bundleCache.getNamedQuery(str);
    }

    Collection<FieldIndex> getFieldIndexes() {
        return (Collection) this.persistence.runTransaction("Get indexes", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5277x64aa3c54();
            }
        });
    }

    /* renamed from: lambda$getFieldIndexes$14$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ Collection m5277x64aa3c54() {
        return this.indexManager.getFieldIndexes();
    }

    public void configureFieldIndexes(final List<FieldIndex> list) {
        this.persistence.runTransaction("Configure indexes", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5276x4fad3f83(list);
            }
        });
    }

    /* renamed from: lambda$configureFieldIndexes$15$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5276x4fad3f83(List list) {
        Collection<FieldIndex> fieldIndexes = this.indexManager.getFieldIndexes();
        Comparator<FieldIndex> comparator = FieldIndex.SEMANTIC_COMPARATOR;
        final IndexManager indexManager = this.indexManager;
        Objects.requireNonNull(indexManager);
        Consumer consumer = new Consumer() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda6
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                indexManager.addFieldIndex((FieldIndex) obj);
            }
        };
        final IndexManager indexManager2 = this.indexManager;
        Objects.requireNonNull(indexManager2);
        Util.diffCollections(fieldIndexes, list, comparator, consumer, new Consumer() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda7
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                indexManager2.deleteFieldIndex((FieldIndex) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class AllocateQueryHolder {
        TargetData cached;
        int targetId;

        private AllocateQueryHolder() {
        }
    }

    public void releaseTarget(final int i) {
        this.persistence.runTransaction("Release target", new Runnable() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5282x1dfd2d2e(i);
            }
        });
    }

    /* renamed from: lambda$releaseTarget$16$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ void m5282x1dfd2d2e(int i) {
        TargetData targetData = this.queryDataByTarget.get(i);
        Assert.hardAssert(targetData != null, "Tried to release nonexistent target: %s", Integer.valueOf(i));
        Iterator<DocumentKey> it = this.localViewReferences.removeReferencesForId(i).iterator();
        while (it.hasNext()) {
            this.persistence.getReferenceDelegate().removeReference(it.next());
        }
        this.persistence.getReferenceDelegate().removeTarget(targetData);
        this.queryDataByTarget.remove(i);
        this.targetIdByTarget.remove(targetData.getTarget());
    }

    public QueryResult executeQuery(Query query, boolean z) {
        TargetData targetData = getTargetData(query.toTarget());
        SnapshotVersion lastLimboFreeSnapshotVersion = SnapshotVersion.NONE;
        ImmutableSortedSet<DocumentKey> immutableSortedSetEmptyKeySet = DocumentKey.emptyKeySet();
        if (targetData != null) {
            lastLimboFreeSnapshotVersion = targetData.getLastLimboFreeSnapshotVersion();
            immutableSortedSetEmptyKeySet = this.targetCache.getMatchingKeysForTargetId(targetData.getTargetId());
        }
        QueryEngine queryEngine = this.queryEngine;
        if (!z) {
            lastLimboFreeSnapshotVersion = SnapshotVersion.NONE;
        }
        return new QueryResult(queryEngine.getDocumentsMatchingQuery(query, lastLimboFreeSnapshotVersion, immutableSortedSetEmptyKeySet), immutableSortedSetEmptyKeySet);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteDocumentKeys(int i) {
        return this.targetCache.getMatchingKeysForTargetId(i);
    }

    private void applyWriteToRemoteDocuments(MutationBatchResult mutationBatchResult) {
        MutationBatch batch = mutationBatchResult.getBatch();
        for (DocumentKey documentKey : batch.getKeys()) {
            MutableDocument mutableDocument = this.remoteDocuments.get(documentKey);
            SnapshotVersion snapshotVersion = mutationBatchResult.getDocVersions().get(documentKey);
            Assert.hardAssert(snapshotVersion != null, "docVersions should contain every doc in the write.", new Object[0]);
            if (mutableDocument.getVersion().compareTo(snapshotVersion) < 0) {
                batch.applyToRemoteDocument(mutableDocument, mutationBatchResult);
                if (mutableDocument.isValidDocument()) {
                    this.remoteDocuments.add(mutableDocument, mutationBatchResult.getCommitVersion());
                }
            }
        }
        this.mutationQueue.removeMutationBatch(batch);
    }

    public LruGarbageCollector.Results collectGarbage(final LruGarbageCollector lruGarbageCollector) {
        return (LruGarbageCollector.Results) this.persistence.runTransaction("Collect garbage", new Supplier() { // from class: com.google.firebase.firestore.local.LocalStore$$ExternalSyntheticLambda16
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5275x1db5499a(lruGarbageCollector);
            }
        });
    }

    /* renamed from: lambda$collectGarbage$17$com-google-firebase-firestore-local-LocalStore, reason: not valid java name */
    /* synthetic */ LruGarbageCollector.Results m5275x1db5499a(LruGarbageCollector lruGarbageCollector) {
        return lruGarbageCollector.collect(this.queryDataByTarget);
    }

    private static Target newUmbrellaTarget(String str) {
        return Query.atPath(ResourcePath.fromString("__bundle__/docs/" + str)).toTarget();
    }
}
