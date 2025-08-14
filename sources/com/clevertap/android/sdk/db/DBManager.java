package com.clevertap.android.sdk.db;

import android.content.Context;
import android.content.SharedPreferences;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.events.EventGroup;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: DBManager.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 $2\u00020\u0001:\u0001$B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0017J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\"\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0016J*\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0016J\"\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0016J*\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0017J \u0010\u001e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\u0018H\u0003J \u0010!\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u0014H\u0017J\u0018\u0010#\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/clevertap/android/sdk/db/DBManager;", "Lcom/clevertap/android/sdk/db/BaseDatabaseManager;", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "ctLockManager", "Lcom/clevertap/android/sdk/CTLockManager;", "(Lcom/clevertap/android/sdk/CleverTapInstanceConfig;Lcom/clevertap/android/sdk/CTLockManager;)V", "dbAdapter", "Lcom/clevertap/android/sdk/db/DBAdapter;", "clearFirstRequestTimestampIfNeeded", "", "context", "Landroid/content/Context;", "clearIJ", "clearLastRequestTimestamp", "clearQueues", "clearUserContext", "getPushNotificationViewedQueuedEvents", "Lcom/clevertap/android/sdk/db/QueueData;", "batchSize", "", "previousQueue", "getQueue", "table", "Lcom/clevertap/android/sdk/db/Table;", "getQueuedDBEvents", "getQueuedEvents", "eventGroup", "Lcom/clevertap/android/sdk/events/EventGroup;", "loadDBAdapter", "queueEventForTable", "event", "Lorg/json/JSONObject;", "queueEventToDB", "type", "queuePushNotificationViewedEventToDB", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class DBManager implements BaseDatabaseManager {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final int USER_EVENT_LOG_ROWS_PER_USER = 2304;

    @Deprecated
    private static final int USER_EVENT_LOG_ROWS_THRESHOLD = 11520;
    private final CleverTapInstanceConfig config;
    private final CTLockManager ctLockManager;
    private DBAdapter dbAdapter;

    public DBManager(CleverTapInstanceConfig config, CTLockManager ctLockManager) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(ctLockManager, "ctLockManager");
        this.config = config;
        this.ctLockManager = ctLockManager;
    }

    /* compiled from: DBManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/clevertap/android/sdk/db/DBManager$Companion;", "", "()V", "USER_EVENT_LOG_ROWS_PER_USER", "", "USER_EVENT_LOG_ROWS_THRESHOLD", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public synchronized DBAdapter loadDBAdapter(Context context) {
        DBAdapter dBAdapter;
        Intrinsics.checkNotNullParameter(context, "context");
        dBAdapter = this.dbAdapter;
        if (dBAdapter == null) {
            dBAdapter = new DBAdapter(context, this.config);
            this.dbAdapter = dBAdapter;
            dBAdapter.cleanupStaleEvents(Table.EVENTS);
            dBAdapter.cleanupStaleEvents(Table.PROFILE_EVENTS);
            dBAdapter.cleanupStaleEvents(Table.PUSH_NOTIFICATION_VIEWED);
            dBAdapter.cleanUpPushNotifications();
            dBAdapter.userEventLogDAO().cleanUpExtraEvents(USER_EVENT_LOG_ROWS_THRESHOLD, 2304);
        }
        return dBAdapter;
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public void clearQueues(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object eventLock = this.ctLockManager.getEventLock();
        Intrinsics.checkNotNullExpressionValue(eventLock, "ctLockManager.eventLock");
        synchronized (eventLock) {
            DBAdapter dBAdapterLoadDBAdapter = loadDBAdapter(context);
            dBAdapterLoadDBAdapter.removeEvents(Table.EVENTS);
            dBAdapterLoadDBAdapter.removeEvents(Table.PROFILE_EVENTS);
            clearUserContext(context);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public QueueData getQueuedEvents(Context context, int batchSize, QueueData previousQueue, EventGroup eventGroup) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eventGroup, "eventGroup");
        if (eventGroup == EventGroup.PUSH_NOTIFICATION_VIEWED) {
            this.config.getLogger().verbose(this.config.getAccountId(), "Returning Queued Notification Viewed events");
            return getPushNotificationViewedQueuedEvents(context, batchSize, previousQueue);
        }
        this.config.getLogger().verbose(this.config.getAccountId(), "Returning Queued events");
        return getQueuedDBEvents(context, batchSize, previousQueue);
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public QueueData getQueuedDBEvents(Context context, int batchSize, QueueData previousQueue) {
        QueueData queue;
        Intrinsics.checkNotNullParameter(context, "context");
        Object eventLock = this.ctLockManager.getEventLock();
        Intrinsics.checkNotNullExpressionValue(eventLock, "ctLockManager.eventLock");
        synchronized (eventLock) {
            queue = getQueue(context, Table.EVENTS, batchSize, previousQueue);
            if (queue.isEmpty() && queue.getTable() == Table.EVENTS) {
                queue = getQueue(context, Table.PROFILE_EVENTS, batchSize, null);
            }
        }
        return queue;
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public QueueData getQueue(Context context, Table table, int batchSize, QueueData previousQueue) {
        QueueData queueData;
        String lastId;
        Table table2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(table, "table");
        Object eventLock = this.ctLockManager.getEventLock();
        Intrinsics.checkNotNullExpressionValue(eventLock, "ctLockManager.eventLock");
        synchronized (eventLock) {
            DBAdapter dBAdapterLoadDBAdapter = loadDBAdapter(context);
            if (previousQueue != null && (table2 = previousQueue.getTable()) != null) {
                table = table2;
            }
            if (previousQueue != null && (lastId = previousQueue.getLastId()) != null) {
                dBAdapterLoadDBAdapter.cleanupEventsFromLastId(lastId, previousQueue.getTable());
            }
            JSONObject jSONObjectFetchEvents = dBAdapterLoadDBAdapter.fetchEvents(table, batchSize);
            queueData = new QueueData(table);
            queueData.setDataFromDbObject(jSONObjectFetchEvents);
        }
        return queueData;
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public void queueEventToDB(Context context, JSONObject event, int type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(event, "event");
        queueEventForTable(context, event, type == 3 ? Table.PROFILE_EVENTS : Table.EVENTS);
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public void queuePushNotificationViewedEventToDB(Context context, JSONObject event) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(event, "event");
        queueEventForTable(context, event, Table.PUSH_NOTIFICATION_VIEWED);
    }

    @Override // com.clevertap.android.sdk.db.BaseDatabaseManager
    public QueueData getPushNotificationViewedQueuedEvents(Context context, int batchSize, QueueData previousQueue) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getQueue(context, Table.PUSH_NOTIFICATION_VIEWED, batchSize, previousQueue);
    }

    private final void clearIJ(Context context) {
        SharedPreferences.Editor editorEdit = StorageHelper.getPreferences(context, Constants.NAMESPACE_IJ).edit();
        editorEdit.clear();
        StorageHelper.persist(editorEdit);
    }

    private final void clearLastRequestTimestamp(Context context) {
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_LAST_TS), 0);
    }

    private final void clearUserContext(Context context) {
        clearIJ(context);
        clearFirstRequestTimestampIfNeeded(context);
        clearLastRequestTimestamp(context);
    }

    private final void clearFirstRequestTimestampIfNeeded(Context context) {
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(this.config, Constants.KEY_FIRST_TS), 0);
    }

    private final void queueEventForTable(Context context, JSONObject event, Table table) {
        Object eventLock = this.ctLockManager.getEventLock();
        Intrinsics.checkNotNullExpressionValue(eventLock, "ctLockManager.eventLock");
        synchronized (eventLock) {
            if (loadDBAdapter(context).storeObject(event, table) > 0) {
                this.config.getLogger().debug(this.config.getAccountId(), "Queued event: " + event);
                this.config.getLogger().verbose(this.config.getAccountId(), "Queued event to DB table " + table + ": " + event);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
