package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Supplier;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class MemoryPersistence extends Persistence {
    private ReferenceDelegate referenceDelegate;
    private boolean started;
    private final Map<User, MemoryMutationQueue> mutationQueues = new HashMap();
    private final MemoryIndexManager indexManager = new MemoryIndexManager();
    private final MemoryTargetCache targetCache = new MemoryTargetCache(this);
    private final MemoryBundleCache bundleCache = new MemoryBundleCache();
    private final MemoryRemoteDocumentCache remoteDocumentCache = new MemoryRemoteDocumentCache();
    private final Map<User, MemoryDocumentOverlayCache> overlays = new HashMap();

    private void setReferenceDelegate(ReferenceDelegate referenceDelegate) {
        this.referenceDelegate = referenceDelegate;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    BundleCache getBundleCache() {
        return this.bundleCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryIndexManager getIndexManager(User user) {
        return this.indexManager;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public ReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryRemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.firestore.local.Persistence
    public MemoryTargetCache getTargetCache() {
        return this.targetCache;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public boolean isStarted() {
        return this.started;
    }

    public static MemoryPersistence createEagerGcMemoryPersistence() {
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        memoryPersistence.setReferenceDelegate(new MemoryEagerReferenceDelegate(memoryPersistence));
        return memoryPersistence;
    }

    public static MemoryPersistence createLruGcMemoryPersistence(LruGarbageCollector.Params params, LocalSerializer localSerializer) {
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        memoryPersistence.setReferenceDelegate(new MemoryLruReferenceDelegate(memoryPersistence, params, localSerializer));
        return memoryPersistence;
    }

    private MemoryPersistence() {
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void start() {
        Assert.hardAssert(!this.started, "MemoryPersistence double-started!", new Object[0]);
        this.started = true;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    public void shutdown() {
        Assert.hardAssert(this.started, "MemoryPersistence shutdown without start", new Object[0]);
        this.started = false;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    MutationQueue getMutationQueue(User user, IndexManager indexManager) {
        MemoryMutationQueue memoryMutationQueue = this.mutationQueues.get(user);
        if (memoryMutationQueue != null) {
            return memoryMutationQueue;
        }
        MemoryMutationQueue memoryMutationQueue2 = new MemoryMutationQueue(this, user);
        this.mutationQueues.put(user, memoryMutationQueue2);
        return memoryMutationQueue2;
    }

    Iterable<MemoryMutationQueue> getMutationQueues() {
        return this.mutationQueues.values();
    }

    @Override // com.google.firebase.firestore.local.Persistence
    DocumentOverlayCache getDocumentOverlayCache(User user) {
        MemoryDocumentOverlayCache memoryDocumentOverlayCache = this.overlays.get(user);
        if (memoryDocumentOverlayCache != null) {
            return memoryDocumentOverlayCache;
        }
        MemoryDocumentOverlayCache memoryDocumentOverlayCache2 = new MemoryDocumentOverlayCache();
        this.overlays.put(user, memoryDocumentOverlayCache2);
        return memoryDocumentOverlayCache2;
    }

    @Override // com.google.firebase.firestore.local.Persistence
    OverlayMigrationManager getOverlayMigrationManager() {
        return new MemoryOverlayMigrationManager();
    }

    @Override // com.google.firebase.firestore.local.Persistence
    void runTransaction(String str, Runnable runnable) {
        this.referenceDelegate.onTransactionStarted();
        try {
            runnable.run();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }

    @Override // com.google.firebase.firestore.local.Persistence
    <T> T runTransaction(String str, Supplier<T> supplier) {
        this.referenceDelegate.onTransactionStarted();
        try {
            return supplier.get();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }
}
