package com.clevertap.android.sdk.usereventlogs;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.db.Column;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.db.DatabaseHelper;
import com.clevertap.android.sdk.db.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: UserEventLogDAOImpl.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0017J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\r\u001a\u00020\u000eH\u0017J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0017J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0017J \u0010\u0016\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0012H\u0017J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0017J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0017J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0017J\u0018\u0010\u001d\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0017J*\u0010\u001e\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0!0 H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/clevertap/android/sdk/usereventlogs/UserEventLogDAOImpl;", "Lcom/clevertap/android/sdk/usereventlogs/UserEventLogDAO;", "db", "Lcom/clevertap/android/sdk/db/DatabaseHelper;", "logger", "Lcom/clevertap/android/sdk/Logger;", "table", "Lcom/clevertap/android/sdk/db/Table;", "(Lcom/clevertap/android/sdk/db/DatabaseHelper;Lcom/clevertap/android/sdk/Logger;Lcom/clevertap/android/sdk/db/Table;)V", "allEvents", "", "Lcom/clevertap/android/sdk/usereventlogs/UserEventLog;", "allEventsByDeviceID", Column.DEVICE_ID, "", "cleanUpExtraEvents", "", "threshold", "", "numberOfRowsToCleanup", "eventExistsByDeviceIdAndNormalizedEventName", Column.NORMALIZED_EVENT_NAME, "eventExistsByDeviceIdAndNormalizedEventNameAndCount", "count", "insertEvent", "", "eventName", "readEventByDeviceIdAndNormalizedEventName", "readEventCountByDeviceIdAndNormalizedEventName", "updateEventByDeviceIdAndNormalizedEventName", "upsertEventsByDeviceIdAndNormalizedEventName", "setOfActualAndNormalizedEventNamePair", "", "Lkotlin/Pair;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class UserEventLogDAOImpl implements UserEventLogDAO {
    private final DatabaseHelper db;
    private final Logger logger;
    private final Table table;

    public UserEventLogDAOImpl(DatabaseHelper db, Logger logger, Table table) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(table, "table");
        this.db = db;
        this.logger = logger;
        this.table = table;
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public long insertEvent(String deviceID, String eventName, String normalizedEventName) {
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(normalizedEventName, "normalizedEventName");
        if (!this.db.belowMemThreshold()) {
            this.logger.verbose(DBAdapter.NOT_ENOUGH_SPACE_LOG);
            return -2L;
        }
        String tableName = this.table.getTableName();
        this.logger.verbose("Inserting event " + eventName + " with deviceID = " + deviceID + " in " + tableName);
        long nowInMillis = Utils.getNowInMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventName", eventName);
        contentValues.put(Column.NORMALIZED_EVENT_NAME, normalizedEventName);
        contentValues.put(Column.FIRST_TS, Long.valueOf(nowInMillis));
        contentValues.put(Column.LAST_TS, Long.valueOf(nowInMillis));
        contentValues.put("count", (Integer) 1);
        contentValues.put(Column.DEVICE_ID, deviceID);
        try {
            return this.db.getWritableDatabase().insertWithOnConflict(tableName, null, contentValues, 5);
        } catch (Exception e) {
            this.logger.verbose("Error adding row to table " + tableName + " Recreating DB. Exception: " + e);
            this.db.deleteDatabase();
            return -1L;
        }
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public boolean updateEventByDeviceIdAndNormalizedEventName(String deviceID, String normalizedEventName) throws SQLException {
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        Intrinsics.checkNotNullParameter(normalizedEventName, "normalizedEventName");
        String tableName = this.table.getTableName();
        long nowInMillis = Utils.getNowInMillis();
        try {
            String strTrimIndent = StringsKt.trimIndent("\n            UPDATE " + tableName + " \n            SET \n                count = count + 1,\n                lastTs = ?\n            WHERE deviceID = ? \n            AND normalizedEventName = ?;\n        ");
            this.logger.verbose("Updating event " + normalizedEventName + " with deviceID = " + deviceID + " in " + tableName);
            this.db.getWritableDatabase().execSQL(strTrimIndent, new Object[]{Long.valueOf(nowInMillis), deviceID, normalizedEventName});
            return true;
        } catch (Exception e) {
            this.logger.verbose("Could not update event in database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
            return false;
        }
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public boolean upsertEventsByDeviceIdAndNormalizedEventName(String deviceID, Set<Pair<String, String>> setOfActualAndNormalizedEventNamePair) {
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        Intrinsics.checkNotNullParameter(setOfActualAndNormalizedEventNamePair, "setOfActualAndNormalizedEventNamePair");
        String tableName = this.table.getTableName();
        this.logger.verbose("UserEventLog: upsert EventLog for bulk events");
        try {
            this.db.getWritableDatabase().beginTransaction();
            Iterator<T> it = setOfActualAndNormalizedEventNamePair.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (eventExistsByDeviceIdAndNormalizedEventName(deviceID, (String) pair.getSecond())) {
                    this.logger.verbose("UserEventLog: Updating EventLog for event " + pair);
                    updateEventByDeviceIdAndNormalizedEventName(deviceID, (String) pair.getSecond());
                } else {
                    this.logger.verbose("UserEventLog: Inserting EventLog for event " + pair);
                    insertEvent(deviceID, (String) pair.getFirst(), (String) pair.getSecond());
                }
            }
            this.db.getWritableDatabase().setTransactionSuccessful();
            this.db.getWritableDatabase().endTransaction();
            return true;
        } catch (Exception e) {
            this.logger.verbose("Failed to perform bulk upsert on table " + tableName, e);
            try {
                this.db.getWritableDatabase().endTransaction();
            } catch (Exception e2) {
                this.logger.verbose("Failed to end transaction on table " + tableName, e2);
            }
            return false;
        }
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public UserEventLog readEventByDeviceIdAndNormalizedEventName(String deviceID, String normalizedEventName) {
        UserEventLog userEventLog;
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        Intrinsics.checkNotNullParameter(normalizedEventName, "normalizedEventName");
        String tableName = this.table.getTableName();
        try {
            Cursor cursorQuery = this.db.getReadableDatabase().query(tableName, null, "deviceID = ? AND normalizedEventName = ?", new String[]{deviceID, normalizedEventName}, null, null, null, null);
            if (cursorQuery == null) {
                return null;
            }
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                if (cursor2.moveToFirst()) {
                    String string = cursor2.getString(cursor2.getColumnIndexOrThrow("eventName"));
                    Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…Throw(Column.EVENT_NAME))");
                    String string2 = cursor2.getString(cursor2.getColumnIndexOrThrow(Column.NORMALIZED_EVENT_NAME));
                    Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(cursor.…n.NORMALIZED_EVENT_NAME))");
                    long j = cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.FIRST_TS));
                    long j2 = cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.LAST_TS));
                    int i = cursor2.getInt(cursor2.getColumnIndexOrThrow("count"));
                    String string3 = cursor2.getString(cursor2.getColumnIndexOrThrow(Column.DEVICE_ID));
                    Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(cursor.…rThrow(Column.DEVICE_ID))");
                    userEventLog = new UserEventLog(string, string2, j, j2, i, string3);
                } else {
                    userEventLog = null;
                }
                CloseableKt.closeFinally(cursor, null);
                return userEventLog;
            } finally {
            }
        } catch (Exception e) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
            return null;
        }
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public int readEventCountByDeviceIdAndNormalizedEventName(String deviceID, String normalizedEventName) {
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        Intrinsics.checkNotNullParameter(normalizedEventName, "normalizedEventName");
        String tableName = this.table.getTableName();
        try {
            Cursor cursorQuery = this.db.getReadableDatabase().query(tableName, new String[]{"count"}, "deviceID = ? AND normalizedEventName = ?", new String[]{deviceID, normalizedEventName}, null, null, null, null);
            if (cursorQuery == null) {
                return -1;
            }
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                int i = cursor2.moveToFirst() ? cursor2.getInt(cursor2.getColumnIndexOrThrow("count")) : 0;
                CloseableKt.closeFinally(cursor, null);
                return i;
            } finally {
            }
        } catch (Exception e) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0054  */
    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean eventExistsByDeviceIdAndNormalizedEventName(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r0 = "deviceID"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "normalizedEventName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.clevertap.android.sdk.db.Table r0 = r4.table
            java.lang.String r0 = r0.getTableName()
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}
            java.lang.String r6 = "eventExists"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\n            SELECT EXISTS(\n                SELECT 1 \n                FROM "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r2 = " \n                WHERE deviceID = ? AND normalizedEventName = ?\n            ) AS eventExists;\n        "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = kotlin.text.StringsKt.trimIndent(r1)
            r2 = 0
            com.clevertap.android.sdk.db.DatabaseHelper r3 = r4.db     // Catch: java.lang.Exception -> L62
            android.database.sqlite.SQLiteDatabase r3 = r3.getReadableDatabase()     // Catch: java.lang.Exception -> L62
            android.database.Cursor r5 = r3.rawQuery(r1, r5)     // Catch: java.lang.Exception -> L62
            if (r5 == 0) goto L7f
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch: java.lang.Exception -> L62
            r1 = r5
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch: java.lang.Throwable -> L5b
            boolean r3 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L5b
            if (r3 == 0) goto L54
            int r6 = r1.getColumnIndexOrThrow(r6)     // Catch: java.lang.Throwable -> L5b
            int r6 = r1.getInt(r6)     // Catch: java.lang.Throwable -> L5b
            r1 = 1
            if (r6 != r1) goto L54
            goto L55
        L54:
            r1 = r2
        L55:
            r6 = 0
            kotlin.io.CloseableKt.closeFinally(r5, r6)     // Catch: java.lang.Exception -> L62
            r2 = r1
            goto L7f
        L5b:
            r6 = move-exception
            throw r6     // Catch: java.lang.Throwable -> L5d
        L5d:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r6)     // Catch: java.lang.Exception -> L62
            throw r1     // Catch: java.lang.Exception -> L62
        L62:
            r5 = move-exception
            com.clevertap.android.sdk.Logger r6 = r4.logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Could not fetch records out of database "
            r1.<init>(r3)
            java.lang.StringBuilder r0 = r1.append(r0)
            r1 = 46
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r6.verbose(r0, r5)
        L7f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.usereventlogs.UserEventLogDAOImpl.eventExistsByDeviceIdAndNormalizedEventName(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0058  */
    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean eventExistsByDeviceIdAndNormalizedEventNameAndCount(java.lang.String r4, java.lang.String r5, int r6) {
        /*
            r3 = this;
            java.lang.String r0 = "deviceID"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "normalizedEventName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.clevertap.android.sdk.db.Table r0 = r3.table
            java.lang.String r0 = r0.getTableName()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6}
            java.lang.String r5 = "eventExists"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r1 = "\n            SELECT EXISTS(\n                SELECT 1 \n                FROM "
            r6.<init>(r1)
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r1 = " \n                WHERE deviceID = ? AND normalizedEventName = ? AND count = ?\n                ) AS eventExists;\n        "
            java.lang.StringBuilder r6 = r6.append(r1)
            java.lang.String r6 = r6.toString()
            java.lang.String r6 = kotlin.text.StringsKt.trimIndent(r6)
            r1 = 0
            com.clevertap.android.sdk.db.DatabaseHelper r2 = r3.db     // Catch: java.lang.Exception -> L66
            android.database.sqlite.SQLiteDatabase r2 = r2.getReadableDatabase()     // Catch: java.lang.Exception -> L66
            android.database.Cursor r4 = r2.rawQuery(r6, r4)     // Catch: java.lang.Exception -> L66
            if (r4 == 0) goto L83
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch: java.lang.Exception -> L66
            r6 = r4
            android.database.Cursor r6 = (android.database.Cursor) r6     // Catch: java.lang.Throwable -> L5f
            boolean r2 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L58
            int r5 = r6.getColumnIndexOrThrow(r5)     // Catch: java.lang.Throwable -> L5f
            int r5 = r6.getInt(r5)     // Catch: java.lang.Throwable -> L5f
            r6 = 1
            if (r5 != r6) goto L58
            goto L59
        L58:
            r6 = r1
        L59:
            r5 = 0
            kotlin.io.CloseableKt.closeFinally(r4, r5)     // Catch: java.lang.Exception -> L66
            r1 = r6
            goto L83
        L5f:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L61
        L61:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r5)     // Catch: java.lang.Exception -> L66
            throw r6     // Catch: java.lang.Exception -> L66
        L66:
            r4 = move-exception
            com.clevertap.android.sdk.Logger r5 = r3.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r2 = "Could not fetch records out of database "
            r6.<init>(r2)
            java.lang.StringBuilder r6 = r6.append(r0)
            r0 = 46
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            r5.verbose(r6, r4)
        L83:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.usereventlogs.UserEventLogDAOImpl.eventExistsByDeviceIdAndNormalizedEventNameAndCount(java.lang.String, java.lang.String, int):boolean");
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public List<UserEventLog> allEventsByDeviceID(String deviceID) {
        Intrinsics.checkNotNullParameter(deviceID, "deviceID");
        String tableName = this.table.getTableName();
        ArrayList arrayList = new ArrayList();
        try {
            Cursor cursorQuery = this.db.getReadableDatabase().query(tableName, null, "deviceID = ?", new String[]{deviceID}, null, null, "lastTs ASC", null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    while (cursor2.moveToNext()) {
                        String string = cursor2.getString(cursor2.getColumnIndexOrThrow("eventName"));
                        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…Throw(Column.EVENT_NAME))");
                        String string2 = cursor2.getString(cursor2.getColumnIndexOrThrow(Column.NORMALIZED_EVENT_NAME));
                        Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(cursor.…n.NORMALIZED_EVENT_NAME))");
                        long j = cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.FIRST_TS));
                        long j2 = cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.LAST_TS));
                        int i = cursor2.getInt(cursor2.getColumnIndexOrThrow("count"));
                        String string3 = cursor2.getString(cursor2.getColumnIndexOrThrow(Column.DEVICE_ID));
                        Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(cursor.…rThrow(Column.DEVICE_ID))");
                        arrayList.add(new UserEventLog(string, string2, j, j2, i, string3));
                    }
                    CloseableKt.closeFinally(cursor, null);
                    return arrayList;
                } finally {
                }
            } else {
                return CollectionsKt.emptyList();
            }
        } catch (Exception e) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
            return CollectionsKt.emptyList();
        }
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public List<UserEventLog> allEvents() {
        String tableName = this.table.getTableName();
        ArrayList arrayList = new ArrayList();
        try {
            Cursor cursorQuery = this.db.getReadableDatabase().query(tableName, null, null, null, null, null, "lastTs ASC");
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    while (cursor2.moveToNext()) {
                        String string = cursor2.getString(cursor2.getColumnIndexOrThrow("eventName"));
                        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…Throw(Column.EVENT_NAME))");
                        String string2 = cursor2.getString(cursor2.getColumnIndexOrThrow(Column.NORMALIZED_EVENT_NAME));
                        Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(cursor.…n.NORMALIZED_EVENT_NAME))");
                        long j = cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.FIRST_TS));
                        long j2 = cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.LAST_TS));
                        int i = cursor2.getInt(cursor2.getColumnIndexOrThrow("count"));
                        String string3 = cursor2.getString(cursor2.getColumnIndexOrThrow(Column.DEVICE_ID));
                        Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(cursor.…rThrow(Column.DEVICE_ID))");
                        arrayList.add(new UserEventLog(string, string2, j, j2, i, string3));
                    }
                    CloseableKt.closeFinally(cursor, null);
                    return arrayList;
                } finally {
                }
            } else {
                return CollectionsKt.emptyList();
            }
        } catch (Exception e) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
            return CollectionsKt.emptyList();
        }
    }

    @Override // com.clevertap.android.sdk.usereventlogs.UserEventLogDAO
    public boolean cleanUpExtraEvents(int threshold, int numberOfRowsToCleanup) throws SQLException {
        if (threshold <= 0) {
            this.logger.verbose("Invalid threshold value: " + threshold + ". Threshold should be greater than 0");
            return false;
        }
        if (numberOfRowsToCleanup < 0) {
            this.logger.verbose("Invalid numberOfRowsToCleanup value: " + numberOfRowsToCleanup + ". Should be greater than or equal to 0");
            return false;
        }
        if (numberOfRowsToCleanup >= threshold) {
            this.logger.verbose("Invalid numberOfRowsToCleanup value: " + numberOfRowsToCleanup + ". Should be less than threshold: " + threshold);
            return false;
        }
        String tableName = this.table.getTableName();
        int i = threshold - numberOfRowsToCleanup;
        try {
            this.db.getWritableDatabase().execSQL(StringsKt.trimIndent("\n            DELETE FROM " + tableName + "\n            WHERE (normalizedEventName, deviceID) IN (\n                SELECT normalizedEventName, deviceID\n                FROM " + tableName + "\n                ORDER BY lastTs ASC \n                LIMIT (\n                SELECT CASE \n                    WHEN COUNT(*) > ? THEN COUNT(*) - ?\n                    ELSE 0\n                END \n                FROM " + tableName + "\n                )\n            );\n        "), new Integer[]{Integer.valueOf(threshold), Integer.valueOf(i)});
            this.logger.verbose("If row count is above " + threshold + " then only keep " + i + " rows in " + tableName);
            return true;
        } catch (Exception e) {
            this.logger.verbose("Error cleaning up extra events in " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
            return false;
        }
    }
}
