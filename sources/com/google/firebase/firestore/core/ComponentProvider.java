package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;

/* loaded from: classes2.dex */
public abstract class ComponentProvider {
    private ConnectivityMonitor connectivityMonitor;
    private EventManager eventManager;
    private Scheduler garbageCollectionScheduler;
    private IndexBackfiller indexBackfiller;
    private LocalStore localStore;
    private Persistence persistence;
    private RemoteStore remoteStore;
    private SyncEngine syncEngine;

    protected abstract ConnectivityMonitor createConnectivityMonitor(Configuration configuration);

    protected abstract EventManager createEventManager(Configuration configuration);

    protected abstract Scheduler createGarbageCollectionScheduler(Configuration configuration);

    protected abstract IndexBackfiller createIndexBackfiller(Configuration configuration);

    protected abstract LocalStore createLocalStore(Configuration configuration);

    protected abstract Persistence createPersistence(Configuration configuration);

    protected abstract RemoteStore createRemoteStore(Configuration configuration);

    protected abstract SyncEngine createSyncEngine(Configuration configuration);

    public Scheduler getGarbageCollectionScheduler() {
        return this.garbageCollectionScheduler;
    }

    public IndexBackfiller getIndexBackfiller() {
        return this.indexBackfiller;
    }

    public static class Configuration {
        private final AsyncQueue asyncQueue;
        private final Context context;
        private final DatabaseInfo databaseInfo;
        private final Datastore datastore;
        private final User initialUser;
        private final int maxConcurrentLimboResolutions;
        private final FirebaseFirestoreSettings settings;

        AsyncQueue getAsyncQueue() {
            return this.asyncQueue;
        }

        Context getContext() {
            return this.context;
        }

        DatabaseInfo getDatabaseInfo() {
            return this.databaseInfo;
        }

        Datastore getDatastore() {
            return this.datastore;
        }

        User getInitialUser() {
            return this.initialUser;
        }

        int getMaxConcurrentLimboResolutions() {
            return this.maxConcurrentLimboResolutions;
        }

        FirebaseFirestoreSettings getSettings() {
            return this.settings;
        }

        public Configuration(Context context, AsyncQueue asyncQueue, DatabaseInfo databaseInfo, Datastore datastore, User user, int i, FirebaseFirestoreSettings firebaseFirestoreSettings) {
            this.context = context;
            this.asyncQueue = asyncQueue;
            this.databaseInfo = databaseInfo;
            this.datastore = datastore;
            this.initialUser = user;
            this.maxConcurrentLimboResolutions = i;
            this.settings = firebaseFirestoreSettings;
        }
    }

    public Persistence getPersistence() {
        return (Persistence) Assert.hardAssertNonNull(this.persistence, "persistence not initialized yet", new Object[0]);
    }

    public LocalStore getLocalStore() {
        return (LocalStore) Assert.hardAssertNonNull(this.localStore, "localStore not initialized yet", new Object[0]);
    }

    public SyncEngine getSyncEngine() {
        return (SyncEngine) Assert.hardAssertNonNull(this.syncEngine, "syncEngine not initialized yet", new Object[0]);
    }

    public RemoteStore getRemoteStore() {
        return (RemoteStore) Assert.hardAssertNonNull(this.remoteStore, "remoteStore not initialized yet", new Object[0]);
    }

    public EventManager getEventManager() {
        return (EventManager) Assert.hardAssertNonNull(this.eventManager, "eventManager not initialized yet", new Object[0]);
    }

    protected ConnectivityMonitor getConnectivityMonitor() {
        return (ConnectivityMonitor) Assert.hardAssertNonNull(this.connectivityMonitor, "connectivityMonitor not initialized yet", new Object[0]);
    }

    public void initialize(Configuration configuration) {
        Persistence persistenceCreatePersistence = createPersistence(configuration);
        this.persistence = persistenceCreatePersistence;
        persistenceCreatePersistence.start();
        this.localStore = createLocalStore(configuration);
        this.connectivityMonitor = createConnectivityMonitor(configuration);
        this.remoteStore = createRemoteStore(configuration);
        this.syncEngine = createSyncEngine(configuration);
        this.eventManager = createEventManager(configuration);
        this.localStore.start();
        this.remoteStore.start();
        this.garbageCollectionScheduler = createGarbageCollectionScheduler(configuration);
        this.indexBackfiller = createIndexBackfiller(configuration);
    }
}
