package com.clevertap.android.sdk.db;

import android.content.Context;
import com.clevertap.android.sdk.events.EventGroup;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: BaseDatabaseManager.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007H&J*\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007H&J\"\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007H&J*\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH&J\u0018\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H&¨\u0006\u0019"}, d2 = {"Lcom/clevertap/android/sdk/db/BaseDatabaseManager;", "", "clearQueues", "", "context", "Landroid/content/Context;", "getPushNotificationViewedQueuedEvents", "Lcom/clevertap/android/sdk/db/QueueData;", "batchSize", "", "previousQueue", "getQueue", "table", "Lcom/clevertap/android/sdk/db/Table;", "getQueuedDBEvents", "getQueuedEvents", "eventGroup", "Lcom/clevertap/android/sdk/events/EventGroup;", "loadDBAdapter", "Lcom/clevertap/android/sdk/db/DBAdapter;", "queueEventToDB", "event", "Lorg/json/JSONObject;", "type", "queuePushNotificationViewedEventToDB", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface BaseDatabaseManager {
    void clearQueues(Context context);

    QueueData getPushNotificationViewedQueuedEvents(Context context, int batchSize, QueueData previousQueue);

    QueueData getQueue(Context context, Table table, int batchSize, QueueData previousQueue);

    QueueData getQueuedDBEvents(Context context, int batchSize, QueueData previousQueue);

    QueueData getQueuedEvents(Context context, int batchSize, QueueData previousQueue, EventGroup eventGroup);

    DBAdapter loadDBAdapter(Context context);

    void queueEventToDB(Context context, JSONObject event, int type);

    void queuePushNotificationViewedEventToDB(Context context, JSONObject event);
}
