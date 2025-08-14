package com.clevertap.android.sdk.db;

import kotlin.Metadata;

/* compiled from: CtDatabase.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/clevertap/android/sdk/db/Table;", "", "tableName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTableName", "()Ljava/lang/String;", "EVENTS", "PROFILE_EVENTS", "USER_PROFILES", "INBOX_MESSAGES", "PUSH_NOTIFICATIONS", "UNINSTALL_TS", "PUSH_NOTIFICATION_VIEWED", "USER_EVENT_LOGS_TABLE", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public enum Table {
    EVENTS("events"),
    PROFILE_EVENTS("profileEvents"),
    USER_PROFILES("userProfiles"),
    INBOX_MESSAGES("inboxMessages"),
    PUSH_NOTIFICATIONS("pushNotifications"),
    UNINSTALL_TS("uninstallTimestamp"),
    PUSH_NOTIFICATION_VIEWED("notificationViewed"),
    USER_EVENT_LOGS_TABLE("userEventLogs");

    private final String tableName;

    public final String getTableName() {
        return this.tableName;
    }

    Table(String str) {
        this.tableName = str;
    }
}
