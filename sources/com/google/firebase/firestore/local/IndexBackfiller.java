package com.google.firebase.firestore.local;

import com.google.common.base.Supplier;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class IndexBackfiller {
    private static final String LOG_TAG = "IndexBackfiller";
    private static final int MAX_DOCUMENTS_TO_PROCESS = 50;
    private final Supplier<IndexManager> indexManagerOfCurrentUser;
    private final Supplier<LocalDocumentsView> localDocumentsViewOfCurrentUser;
    private int maxDocumentsToProcess;
    private final Persistence persistence;
    private final Scheduler scheduler;
    private static final long INITIAL_BACKFILL_DELAY_MS = TimeUnit.SECONDS.toMillis(15);
    private static final long REGULAR_BACKFILL_DELAY_MS = TimeUnit.MINUTES.toMillis(1);

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    void setMaxDocumentsToProcess(int i) {
        this.maxDocumentsToProcess = i;
    }

    public IndexBackfiller(Persistence persistence, AsyncQueue asyncQueue, final LocalStore localStore) {
        Objects.requireNonNull(localStore);
        Supplier supplier = new Supplier() { // from class: com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return localStore.getIndexManagerForCurrentUser();
            }
        };
        Objects.requireNonNull(localStore);
        this(persistence, asyncQueue, supplier, new Supplier() { // from class: com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return localStore.getLocalDocumentsForCurrentUser();
            }
        });
    }

    public IndexBackfiller(Persistence persistence, AsyncQueue asyncQueue, Supplier<IndexManager> supplier, Supplier<LocalDocumentsView> supplier2) {
        this.maxDocumentsToProcess = 50;
        this.persistence = persistence;
        this.scheduler = new Scheduler(asyncQueue);
        this.indexManagerOfCurrentUser = supplier;
        this.localDocumentsViewOfCurrentUser = supplier2;
    }

    public class Scheduler implements com.google.firebase.firestore.local.Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask backfillTask;

        public Scheduler(AsyncQueue asyncQueue) {
            this.asyncQueue = asyncQueue;
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void start() {
            scheduleBackfill(IndexBackfiller.INITIAL_BACKFILL_DELAY_MS);
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void stop() {
            AsyncQueue.DelayedTask delayedTask = this.backfillTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }

        private void scheduleBackfill(long j) {
            this.backfillTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.INDEX_BACKFILL, j, new Runnable() { // from class: com.google.firebase.firestore.local.IndexBackfiller$Scheduler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5270x4c716e24();
                }
            });
        }

        /* renamed from: lambda$scheduleBackfill$0$com-google-firebase-firestore-local-IndexBackfiller$Scheduler, reason: not valid java name */
        /* synthetic */ void m5270x4c716e24() {
            Logger.debug(IndexBackfiller.LOG_TAG, "Documents written: %s", Integer.valueOf(IndexBackfiller.this.backfill()));
            scheduleBackfill(IndexBackfiller.REGULAR_BACKFILL_DELAY_MS);
        }
    }

    public int backfill() {
        return ((Integer) this.persistence.runTransaction("Backfill Indexes", new com.google.firebase.firestore.util.Supplier() { // from class: com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f$0.m5269x14d875f6();
            }
        })).intValue();
    }

    /* renamed from: lambda$backfill$0$com-google-firebase-firestore-local-IndexBackfiller, reason: not valid java name */
    /* synthetic */ Integer m5269x14d875f6() {
        return Integer.valueOf(writeIndexEntries());
    }

    private int writeIndexEntries() {
        IndexManager indexManager = this.indexManagerOfCurrentUser.get();
        HashSet hashSet = new HashSet();
        int iWriteEntriesForCollectionGroup = this.maxDocumentsToProcess;
        while (iWriteEntriesForCollectionGroup > 0) {
            String nextCollectionGroupToUpdate = indexManager.getNextCollectionGroupToUpdate();
            if (nextCollectionGroupToUpdate == null || hashSet.contains(nextCollectionGroupToUpdate)) {
                break;
            }
            Logger.debug(LOG_TAG, "Processing collection: %s", nextCollectionGroupToUpdate);
            iWriteEntriesForCollectionGroup -= writeEntriesForCollectionGroup(nextCollectionGroupToUpdate, iWriteEntriesForCollectionGroup);
            hashSet.add(nextCollectionGroupToUpdate);
        }
        return this.maxDocumentsToProcess - iWriteEntriesForCollectionGroup;
    }

    private int writeEntriesForCollectionGroup(String str, int i) {
        IndexManager indexManager = this.indexManagerOfCurrentUser.get();
        LocalDocumentsView localDocumentsView = this.localDocumentsViewOfCurrentUser.get();
        FieldIndex.IndexOffset minOffset = indexManager.getMinOffset(str);
        LocalDocumentsResult nextDocuments = localDocumentsView.getNextDocuments(str, minOffset, i);
        indexManager.updateIndexEntries(nextDocuments.getDocuments());
        FieldIndex.IndexOffset newOffset = getNewOffset(minOffset, nextDocuments);
        Logger.debug(LOG_TAG, "Updating offset: %s", newOffset);
        indexManager.updateCollectionGroup(str, newOffset);
        return nextDocuments.getDocuments().size();
    }

    private FieldIndex.IndexOffset getNewOffset(FieldIndex.IndexOffset indexOffset, LocalDocumentsResult localDocumentsResult) {
        Iterator<Map.Entry<DocumentKey, Document>> it = localDocumentsResult.getDocuments().iterator();
        FieldIndex.IndexOffset indexOffset2 = indexOffset;
        while (it.hasNext()) {
            FieldIndex.IndexOffset indexOffsetFromDocument = FieldIndex.IndexOffset.fromDocument(it.next().getValue());
            if (indexOffsetFromDocument.compareTo(indexOffset2) > 0) {
                indexOffset2 = indexOffsetFromDocument;
            }
        }
        return FieldIndex.IndexOffset.create(indexOffset2.getReadTime(), indexOffset2.getDocumentKey(), Math.max(localDocumentsResult.getBatchId(), indexOffset.getLargestBatchId()));
    }
}
