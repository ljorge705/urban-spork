package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class LruGarbageCollector {
    private static final long INITIAL_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    private static final long REGULAR_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(5);
    private final LruDelegate delegate;
    private final Params params;

    public static class Params {
        private static final long COLLECTION_DISABLED = -1;
        private static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
        private static final int DEFAULT_COLLECTION_PERCENTILE = 10;
        private static final int DEFAULT_MAX_SEQUENCE_NUMBERS_TO_COLLECT = 1000;
        final int maximumSequenceNumbersToCollect;
        long minBytesThreshold;
        int percentileToCollect;

        public static Params Default() {
            return new Params(104857600L, 10, 1000);
        }

        public static Params Disabled() {
            return new Params(-1L, 0, 0);
        }

        public static Params WithCacheSizeBytes(long j) {
            return new Params(j, 10, 1000);
        }

        Params(long j, int i, int i2) {
            this.minBytesThreshold = j;
            this.percentileToCollect = i;
            this.maximumSequenceNumbersToCollect = i2;
        }
    }

    public static class Results {
        private final int documentsRemoved;
        private final boolean hasRun;
        private final int sequenceNumbersCollected;
        private final int targetsRemoved;

        public int getDocumentsRemoved() {
            return this.documentsRemoved;
        }

        public int getSequenceNumbersCollected() {
            return this.sequenceNumbersCollected;
        }

        public int getTargetsRemoved() {
            return this.targetsRemoved;
        }

        public boolean hasRun() {
            return this.hasRun;
        }

        static Results DidNotRun() {
            return new Results(false, 0, 0, 0);
        }

        Results(boolean z, int i, int i2, int i3) {
            this.hasRun = z;
            this.sequenceNumbersCollected = i;
            this.targetsRemoved = i2;
            this.documentsRemoved = i3;
        }
    }

    public class GCScheduler implements Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask gcTask;
        private boolean hasRun = false;
        private final LocalStore localStore;

        public GCScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
            this.asyncQueue = asyncQueue;
            this.localStore = localStore;
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void start() {
            if (LruGarbageCollector.this.params.minBytesThreshold != -1) {
                scheduleGC();
            }
        }

        @Override // com.google.firebase.firestore.local.Scheduler
        public void stop() {
            AsyncQueue.DelayedTask delayedTask = this.gcTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }

        private void scheduleGC() {
            this.gcTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.GARBAGE_COLLECTION, this.hasRun ? LruGarbageCollector.REGULAR_GC_DELAY_MS : LruGarbageCollector.INITIAL_GC_DELAY_MS, new Runnable() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$GCScheduler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m5289xb32188f8();
                }
            });
        }

        /* renamed from: lambda$scheduleGC$0$com-google-firebase-firestore-local-LruGarbageCollector$GCScheduler, reason: not valid java name */
        /* synthetic */ void m5289xb32188f8() {
            this.localStore.collectGarbage(LruGarbageCollector.this);
            this.hasRun = true;
            scheduleGC();
        }
    }

    LruGarbageCollector(LruDelegate lruDelegate, Params params) {
        this.delegate = lruDelegate;
        this.params = params;
    }

    public GCScheduler newScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
        return new GCScheduler(asyncQueue, localStore);
    }

    public LruGarbageCollector withNewThreshold(long j) {
        this.params.minBytesThreshold = j;
        this.params.percentileToCollect = 100;
        return this;
    }

    int calculateQueryCount(int i) {
        return (int) ((i / 100.0f) * this.delegate.getSequenceNumberCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class RollingSequenceNumberBuffer {
        private static final Comparator<Long> COMPARATOR = new Comparator() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$RollingSequenceNumberBuffer$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Long) obj2).compareTo((Long) obj);
            }
        };
        private final int maxElements;
        private final PriorityQueue<Long> queue;

        RollingSequenceNumberBuffer(int i) {
            this.maxElements = i;
            this.queue = new PriorityQueue<>(i, COMPARATOR);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void addElement(Long l) {
            if (this.queue.size() < this.maxElements) {
                this.queue.add(l);
                return;
            }
            if (l.longValue() < this.queue.peek().longValue()) {
                this.queue.poll();
                this.queue.add(l);
            }
        }

        long getMaxValue() {
            return this.queue.peek().longValue();
        }
    }

    long getNthSequenceNumber(int i) {
        if (i == 0) {
            return -1L;
        }
        final RollingSequenceNumberBuffer rollingSequenceNumberBuffer = new RollingSequenceNumberBuffer(i);
        this.delegate.forEachTarget(new Consumer() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                rollingSequenceNumberBuffer.addElement(Long.valueOf(((TargetData) obj).getSequenceNumber()));
            }
        });
        LruDelegate lruDelegate = this.delegate;
        Objects.requireNonNull(rollingSequenceNumberBuffer);
        lruDelegate.forEachOrphanedDocumentSequenceNumber(new Consumer() { // from class: com.google.firebase.firestore.local.LruGarbageCollector$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                rollingSequenceNumberBuffer.addElement((Long) obj);
            }
        });
        return rollingSequenceNumberBuffer.getMaxValue();
    }

    int removeTargets(long j, SparseArray<?> sparseArray) {
        return this.delegate.removeTargets(j, sparseArray);
    }

    int removeOrphanedDocuments(long j) {
        return this.delegate.removeOrphanedDocuments(j);
    }

    Results collect(SparseArray<?> sparseArray) {
        if (this.params.minBytesThreshold == -1) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; disabled", new Object[0]);
            return Results.DidNotRun();
        }
        long byteSize = getByteSize();
        if (byteSize < this.params.minBytesThreshold) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; Cache size " + byteSize + " is lower than threshold " + this.params.minBytesThreshold, new Object[0]);
            return Results.DidNotRun();
        }
        return runGarbageCollection(sparseArray);
    }

    private Results runGarbageCollection(SparseArray<?> sparseArray) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iCalculateQueryCount = calculateQueryCount(this.params.percentileToCollect);
        if (iCalculateQueryCount > this.params.maximumSequenceNumbersToCollect) {
            Logger.debug("LruGarbageCollector", "Capping sequence numbers to collect down to the maximum of " + this.params.maximumSequenceNumbersToCollect + " from " + iCalculateQueryCount, new Object[0]);
            iCalculateQueryCount = this.params.maximumSequenceNumbersToCollect;
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        long nthSequenceNumber = getNthSequenceNumber(iCalculateQueryCount);
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        int iRemoveTargets = removeTargets(nthSequenceNumber, sparseArray);
        long jCurrentTimeMillis4 = System.currentTimeMillis();
        int iRemoveOrphanedDocuments = removeOrphanedDocuments(nthSequenceNumber);
        long jCurrentTimeMillis5 = System.currentTimeMillis();
        if (Logger.isDebugEnabled()) {
            Logger.debug("LruGarbageCollector", (((("LRU Garbage Collection:\n\tCounted targets in " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + "ms\n") + String.format(Locale.ROOT, "\tDetermined least recently used %d sequence numbers in %dms\n", Integer.valueOf(iCalculateQueryCount), Long.valueOf(jCurrentTimeMillis3 - jCurrentTimeMillis2))) + String.format(Locale.ROOT, "\tRemoved %d targets in %dms\n", Integer.valueOf(iRemoveTargets), Long.valueOf(jCurrentTimeMillis4 - jCurrentTimeMillis3))) + String.format(Locale.ROOT, "\tRemoved %d documents in %dms\n", Integer.valueOf(iRemoveOrphanedDocuments), Long.valueOf(jCurrentTimeMillis5 - jCurrentTimeMillis4))) + String.format(Locale.ROOT, "Total Duration: %dms", Long.valueOf(jCurrentTimeMillis5 - jCurrentTimeMillis)), new Object[0]);
        }
        return new Results(true, iCalculateQueryCount, iRemoveTargets, iRemoveOrphanedDocuments);
    }

    long getByteSize() {
        return this.delegate.getByteSize();
    }
}
