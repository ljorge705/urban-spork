package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.Supplier;

/* loaded from: classes2.dex */
public abstract class Persistence {
    public static String DATA_MIGRATION_BUILD_OVERLAYS = "BUILD_OVERLAYS";
    static final String TAG = "Persistence";

    abstract BundleCache getBundleCache();

    abstract DocumentOverlayCache getDocumentOverlayCache(User user);

    abstract IndexManager getIndexManager(User user);

    abstract MutationQueue getMutationQueue(User user, IndexManager indexManager);

    abstract OverlayMigrationManager getOverlayMigrationManager();

    public abstract ReferenceDelegate getReferenceDelegate();

    abstract RemoteDocumentCache getRemoteDocumentCache();

    abstract TargetCache getTargetCache();

    public abstract boolean isStarted();

    abstract <T> T runTransaction(String str, Supplier<T> supplier);

    abstract void runTransaction(String str, Runnable runnable);

    public abstract void shutdown();

    public abstract void start();

    Persistence() {
    }
}
