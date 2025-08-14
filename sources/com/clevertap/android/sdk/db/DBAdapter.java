package com.clevertap.android.sdk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.inbox.CTMessageDAO;
import com.clevertap.android.sdk.usereventlogs.UserEventLogDAO;
import com.clevertap.android.sdk.usereventlogs.UserEventLogDAOImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DBAdapter.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0000\u0018\u0000 K2\u00020\u0001:\u0001KB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\rH\u0003J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u0012J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\r\u0010\u001c\u001a\u00020\u0012H\u0001¢\u0006\u0002\b\u001dJ\u001c\u0010\u001e\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u001aH\u0007J$\u0010!\u001a\u00020\r2\u0010\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010#2\b\u0010 \u001a\u0004\u0018\u00010\u001aH\u0007J\u000e\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u001aJ\u0018\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001aH\u0002J\u0013\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0,¢\u0006\u0002\u0010-J\u001c\u0010.\u001a\u0004\u0018\u00010'2\b\u0010/\u001a\u0004\u0018\u00010\u001a2\b\u00100\u001a\u0004\u0018\u00010\u001aJ\u001c\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020'022\b\u0010/\u001a\u0004\u0018\u00010\u001aJ\u0010\u00103\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0006\u00104\u001a\u00020\u0016J \u00105\u001a\u0012\u0012\u0004\u0012\u00020706j\b\u0012\u0004\u0012\u000207`82\u0006\u0010 \u001a\u00020\u001aH\u0007J\u0010\u00109\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020)H\u0002J\u001c\u0010;\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u001aH\u0007J$\u0010<\u001a\u00020\r2\u0010\u0010\"\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010#2\b\u0010 \u001a\u0004\u0018\u00010\u001aH\u0007J\u000e\u0010=\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010>\u001a\u00020\u00122\b\u0010%\u001a\u0004\u0018\u00010\u001aJ\u0018\u0010?\u001a\u00020\u00162\u0006\u0010@\u001a\u00020'2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0018\u0010A\u001a\u00020\u00122\b\u0010%\u001a\u0004\u0018\u00010\u001a2\u0006\u0010B\u001a\u00020\u0016J\u0006\u0010C\u001a\u00020\u0012J$\u0010D\u001a\u00020\u00162\b\u0010%\u001a\u0004\u0018\u00010\u001a2\b\u00100\u001a\u0004\u0018\u00010\u001a2\u0006\u0010@\u001a\u00020'H\u0007J\u001d\u0010E\u001a\u00020\u00122\u000e\u0010F\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0,H\u0007¢\u0006\u0002\u0010GJ\u0016\u0010H\u001a\u00020\u00122\f\u0010I\u001a\b\u0012\u0004\u0012\u0002070#H\u0007J\b\u0010J\u001a\u00020\u000fH\u0007R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/clevertap/android/sdk/db/DBAdapter;", "", "context", "Landroid/content/Context;", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "(Landroid/content/Context;Lcom/clevertap/android/sdk/CleverTapInstanceConfig;)V", "dbHelper", "Lcom/clevertap/android/sdk/db/DatabaseHelper;", "logger", "Lcom/clevertap/android/sdk/Logger;", "kotlin.jvm.PlatformType", "rtlDirtyFlag", "", "userEventLogDao", "Lcom/clevertap/android/sdk/usereventlogs/UserEventLogDAO;", "belowMemThreshold", "cleanInternal", "", "table", "Lcom/clevertap/android/sdk/db/Table;", "expiration", "", "cleanUpPushNotifications", "cleanupEventsFromLastId", "lastId", "", "cleanupStaleEvents", "deleteDB", "deleteDB$clevertap_core_release", "deleteMessageForId", "messageId", "userId", "deleteMessagesForIDs", "messageIDs", "", "doesPushNotificationIdExist", "id", "fetchEvents", "Lorg/json/JSONObject;", Constants.KEY_LIMIT, "", "fetchPushNotificationId", "fetchPushNotificationIds", "", "()[Ljava/lang/String;", "fetchUserProfileByAccountIdAndDeviceID", Constants.KEY_ACCOUNT_ID, Constants.DEVICE_ID_TAG, "fetchUserProfilesByAccountId", "", "getDatabaseName", "getLastUninstallTimestamp", "getMessages", "Ljava/util/ArrayList;", "Lcom/clevertap/android/sdk/inbox/CTMessageDAO;", "Lkotlin/collections/ArrayList;", "getTemplateMarkersList", "count", "markReadMessageForId", "markReadMessagesForIds", "removeEvents", "removeUserProfilesForAccountId", "storeObject", "obj", "storePushNotificationId", "ttl", "storeUninstallTimestamp", "storeUserProfile", "updatePushNotificationIds", "ids", "([Ljava/lang/String;)V", "upsertMessages", "inboxMessages", "userEventLogDAO", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class DBAdapter {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DATABASE_NAME = "clevertap";
    private static final long DATA_EXPIRATION = 432000000;
    public static final long DB_OUT_OF_MEMORY_ERROR = -2;
    private static final long DB_UNDEFINED_CODE = -3;
    public static final long DB_UPDATE_ERROR = -1;
    public static final String NOT_ENOUGH_SPACE_LOG = "There is not enough space left on the device to store data, data discarded";
    private final DatabaseHelper dbHelper;
    private final Logger logger;
    private boolean rtlDirtyFlag;
    private volatile UserEventLogDAO userEventLogDao;

    public DBAdapter(Context context, CleverTapInstanceConfig config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Logger logger = config.getLogger();
        this.logger = logger;
        String databaseName = getDatabaseName(config);
        Intrinsics.checkNotNullExpressionValue(logger, "logger");
        this.dbHelper = new DatabaseHelper(context, config, databaseName, logger);
        this.rtlDirtyFlag = true;
    }

    /* compiled from: DBAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u000e\u0010\n\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/clevertap/android/sdk/db/DBAdapter$Companion;", "", "()V", "DATABASE_NAME", "", "DATA_EXPIRATION", "", "DB_OUT_OF_MEMORY_ERROR", "DB_UNDEFINED_CODE", "getDB_UNDEFINED_CODE$annotations", "DB_UPDATE_ERROR", "NOT_ENOUGH_SPACE_LOG", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getDB_UNDEFINED_CODE$annotations() {
        }

        private Companion() {
        }
    }

    public final synchronized boolean deleteMessageForId(String messageId, String userId) {
        boolean z = false;
        if (messageId == null || userId == null) {
            return false;
        }
        String tableName = Table.INBOX_MESSAGES.getTableName();
        try {
            this.dbHelper.getWritableDatabase().delete(tableName, "_id = ? AND messageUser = ?", new String[]{messageId, userId});
            z = true;
        } catch (SQLiteException e) {
            this.logger.verbose("Error removing stale records from " + tableName, e);
        }
        return z;
    }

    public final synchronized boolean deleteMessagesForIDs(List<String> messageIDs, String userId) {
        boolean z = false;
        if (messageIDs == null || userId == null) {
            return false;
        }
        String tableName = Table.INBOX_MESSAGES.getTableName();
        String templateMarkersList = getTemplateMarkersList(messageIDs.size());
        List mutableList = CollectionsKt.toMutableList((Collection) messageIDs);
        mutableList.add(userId);
        try {
            Object[] array = mutableList.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            this.dbHelper.getWritableDatabase().delete(tableName, "_id IN (" + templateMarkersList + ") AND messageUser = ?", (String[]) array);
            z = true;
        } catch (SQLiteException e) {
            this.logger.verbose("Error removing stale records from " + tableName, e);
        }
        return z;
    }

    public final synchronized boolean doesPushNotificationIdExist(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return Intrinsics.areEqual(id, fetchPushNotificationId(id));
    }

    public final synchronized Map<String, JSONObject> fetchUserProfilesByAccountId(String accountId) {
        if (accountId == null) {
            return MapsKt.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String tableName = Table.USER_PROFILES.getTableName();
        try {
            Cursor cursorQuery = this.dbHelper.getReadableDatabase().query(tableName, null, "_id = ?", new String[]{accountId}, null, null, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    int columnIndex = cursor2.getColumnIndex("data");
                    int columnIndex2 = cursor2.getColumnIndex(Column.DEVICE_ID);
                    if (columnIndex >= 0) {
                        while (cursor2.moveToNext()) {
                            String profileString = cursor2.getString(columnIndex);
                            String deviceIdString = cursor2.getString(columnIndex2);
                            if (profileString != null) {
                                Intrinsics.checkNotNullExpressionValue(profileString, "profileString");
                                try {
                                    JSONObject jSONObject = new JSONObject(profileString);
                                    Intrinsics.checkNotNullExpressionValue(deviceIdString, "deviceIdString");
                                    linkedHashMap.put(deviceIdString, jSONObject);
                                } catch (JSONException e) {
                                    this.logger.verbose("Error parsing JSON for profile", e);
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                }
            }
        } catch (SQLiteException e2) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e2);
        }
        return linkedHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized org.json.JSONObject fetchUserProfileByAccountIdAndDeviceID(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            java.lang.String r0 = "Could not fetch records out of database "
            monitor-enter(r12)
            r1 = 0
            if (r13 == 0) goto L87
            if (r14 != 0) goto La
            goto L87
        La:
            com.clevertap.android.sdk.db.Table r2 = com.clevertap.android.sdk.db.Table.USER_PROFILES     // Catch: java.lang.Throwable -> L84
            java.lang.String r2 = r2.getTableName()     // Catch: java.lang.Throwable -> L84
            com.clevertap.android.sdk.db.DatabaseHelper r3 = r12.dbHelper     // Catch: android.database.sqlite.SQLiteException -> L5a java.lang.Throwable -> L84
            android.database.sqlite.SQLiteDatabase r3 = r3.getReadableDatabase()     // Catch: android.database.sqlite.SQLiteException -> L5a java.lang.Throwable -> L84
            r5 = 0
            java.lang.String r6 = "_id = ? AND deviceID = ?"
            java.lang.String[] r7 = new java.lang.String[]{r13, r14}     // Catch: android.database.sqlite.SQLiteException -> L5a java.lang.Throwable -> L84
            r8 = 0
            r9 = 0
            r10 = 0
            r4 = r2
            android.database.Cursor r13 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: android.database.sqlite.SQLiteException -> L5a java.lang.Throwable -> L84
            if (r13 == 0) goto L58
            java.io.Closeable r13 = (java.io.Closeable) r13     // Catch: android.database.sqlite.SQLiteException -> L5a java.lang.Throwable -> L84
            r14 = r13
            android.database.Cursor r14 = (android.database.Cursor) r14     // Catch: java.lang.Throwable -> L4d
            boolean r3 = r14.moveToFirst()     // Catch: java.lang.Throwable -> L4d
            if (r3 == 0) goto L3f
            java.lang.String r3 = "data"
            int r3 = r14.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L4d
            if (r3 < 0) goto L3f
            java.lang.String r14 = r14.getString(r3)     // Catch: java.lang.Throwable -> L4d
            goto L40
        L3f:
            r14 = r1
        L40:
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L48
            kotlin.io.CloseableKt.closeFinally(r13, r1)     // Catch: android.database.sqlite.SQLiteException -> L46 java.lang.Throwable -> L84
            goto L76
        L46:
            r13 = move-exception
            goto L5c
        L48:
            r3 = move-exception
            r11 = r3
            r3 = r14
            r14 = r11
            goto L4f
        L4d:
            r14 = move-exception
            r3 = r1
        L4f:
            throw r14     // Catch: java.lang.Throwable -> L50
        L50:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r13, r14)     // Catch: android.database.sqlite.SQLiteException -> L55 java.lang.Throwable -> L84
            throw r4     // Catch: android.database.sqlite.SQLiteException -> L55 java.lang.Throwable -> L84
        L55:
            r13 = move-exception
            r14 = r3
            goto L5c
        L58:
            r14 = r1
            goto L76
        L5a:
            r13 = move-exception
            r14 = r1
        L5c:
            com.clevertap.android.sdk.Logger r3 = r12.logger     // Catch: java.lang.Throwable -> L84
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L84
            java.lang.StringBuilder r0 = r4.append(r2)     // Catch: java.lang.Throwable -> L84
            r2 = 46
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch: java.lang.Throwable -> L84
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L84
            java.lang.Throwable r13 = (java.lang.Throwable) r13     // Catch: java.lang.Throwable -> L84
            r3.verbose(r0, r13)     // Catch: java.lang.Throwable -> L84
        L76:
            if (r14 == 0) goto L82
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch: org.json.JSONException -> L7f java.lang.Throwable -> L84
            r13.<init>(r14)     // Catch: org.json.JSONException -> L7f java.lang.Throwable -> L84
            r1 = r13
            goto L82
        L7f:
            r13 = r1
            org.json.JSONObject r13 = (org.json.JSONObject) r13     // Catch: java.lang.Throwable -> L84
        L82:
            monitor-exit(r12)
            return r1
        L84:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        L87:
            monitor-exit(r12)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.db.DBAdapter.fetchUserProfileByAccountIdAndDeviceID(java.lang.String, java.lang.String):org.json.JSONObject");
    }

    public final synchronized long getLastUninstallTimestamp() {
        long j;
        String tableName = Table.UNINSTALL_TS.getTableName();
        try {
            Cursor cursorQuery = this.dbHelper.getReadableDatabase().query(tableName, null, null, null, null, null, "created_at DESC", "1");
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    j = cursor2.moveToFirst() ? cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.CREATED_AT)) : 0L;
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                }
            }
        } catch (Exception e) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
        }
        return j;
    }

    public final synchronized ArrayList<CTMessageDAO> getMessages(String userId) {
        ArrayList<CTMessageDAO> arrayList;
        Intrinsics.checkNotNullParameter(userId, "userId");
        String tableName = Table.INBOX_MESSAGES.getTableName();
        arrayList = new ArrayList<>();
        try {
            Cursor cursorQuery = this.dbHelper.getReadableDatabase().query(tableName, null, "messageUser = ?", new String[]{userId}, null, null, "created_at DESC");
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    while (cursor2.moveToNext()) {
                        CTMessageDAO cTMessageDAO = new CTMessageDAO();
                        cTMessageDAO.setId(cursor2.getString(cursor2.getColumnIndexOrThrow(Column.ID)));
                        cTMessageDAO.setJsonData(new JSONObject(cursor2.getString(cursor2.getColumnIndexOrThrow("data"))));
                        cTMessageDAO.setWzrkParams(new JSONObject(cursor2.getString(cursor2.getColumnIndexOrThrow("wzrkParams"))));
                        cTMessageDAO.setDate(cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.CREATED_AT)));
                        cTMessageDAO.setExpires(cursor2.getLong(cursor2.getColumnIndexOrThrow(Column.EXPIRES)));
                        cTMessageDAO.setRead(cursor2.getInt(cursor2.getColumnIndexOrThrow("isRead")));
                        cTMessageDAO.setUserId(cursor2.getString(cursor2.getColumnIndexOrThrow(Column.USER_ID)));
                        cTMessageDAO.setTags(cursor2.getString(cursor2.getColumnIndexOrThrow("tags")));
                        cTMessageDAO.setCampaignId(cursor2.getString(cursor2.getColumnIndexOrThrow(Column.CAMPAIGN)));
                        arrayList.add(cTMessageDAO);
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                }
            }
        } catch (Exception e) {
            this.logger.verbose("Error retrieving records from " + tableName, e);
        }
        return arrayList;
    }

    public final synchronized boolean markReadMessageForId(String messageId, String userId) {
        boolean z = false;
        if (messageId == null || userId == null) {
            return false;
        }
        String tableName = Table.INBOX_MESSAGES.getTableName();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isRead", (Integer) 1);
        try {
            this.dbHelper.getWritableDatabase().update(Table.INBOX_MESSAGES.getTableName(), contentValues, "_id = ? AND messageUser = ?", new String[]{messageId, userId});
            z = true;
        } catch (SQLiteException e) {
            this.logger.verbose("Error removing stale records from " + tableName, e);
        }
        return z;
    }

    public final synchronized boolean markReadMessagesForIds(List<String> messageIDs, String userId) {
        boolean z = false;
        if (messageIDs == null || userId == null) {
            return false;
        }
        String tableName = Table.INBOX_MESSAGES.getTableName();
        String templateMarkersList = getTemplateMarkersList(messageIDs.size());
        List mutableList = CollectionsKt.toMutableList((Collection) messageIDs);
        mutableList.add(userId);
        ContentValues contentValues = new ContentValues();
        contentValues.put("isRead", (Integer) 1);
        try {
            Object[] array = mutableList.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            this.dbHelper.getWritableDatabase().update(Table.INBOX_MESSAGES.getTableName(), contentValues, "_id IN (" + templateMarkersList + ") AND messageUser = ?", (String[]) array);
            z = true;
        } catch (SQLiteException e) {
            this.logger.verbose("Error removing stale records from " + tableName, e);
        }
        return z;
    }

    public final synchronized void removeUserProfilesForAccountId(String id) {
        if (id == null) {
            return;
        }
        String tableName = Table.USER_PROFILES.getTableName();
        try {
            this.dbHelper.getWritableDatabase().delete(tableName, "_id = ?", new String[]{id});
        } catch (SQLiteException unused) {
            this.logger.verbose("Error removing user profile from " + tableName + " Recreating DB");
            deleteDB$clevertap_core_release();
        }
    }

    public final synchronized void storeUninstallTimestamp() {
        if (!belowMemThreshold()) {
            this.logger.verbose(NOT_ENOUGH_SPACE_LOG);
            return;
        }
        String tableName = Table.UNINSTALL_TS.getTableName();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column.CREATED_AT, Long.valueOf(System.currentTimeMillis()));
        try {
            this.dbHelper.getWritableDatabase().insert(tableName, null, contentValues);
        } catch (SQLiteException unused) {
            this.logger.verbose("Error adding data to table " + tableName + " Recreating DB");
            deleteDB$clevertap_core_release();
        }
    }

    public final synchronized long storeUserProfile(String id, String deviceId, JSONObject obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        long jInsertWithOnConflict = -1;
        if (id != null && deviceId != null) {
            if (!belowMemThreshold()) {
                this.logger.verbose(NOT_ENOUGH_SPACE_LOG);
                return -2L;
            }
            String tableName = Table.USER_PROFILES.getTableName();
            this.logger.verbose("Inserting or updating userProfile for accountID = " + id + " + deviceID = " + deviceId);
            ContentValues contentValues = new ContentValues();
            contentValues.put("data", obj.toString());
            contentValues.put(Column.ID, id);
            contentValues.put(Column.DEVICE_ID, deviceId);
            try {
                jInsertWithOnConflict = this.dbHelper.getWritableDatabase().insertWithOnConflict(tableName, null, contentValues, 5);
            } catch (SQLiteException unused) {
                this.logger.verbose("Error adding data to table " + tableName + " Recreating DB");
                deleteDB$clevertap_core_release();
            }
            return jInsertWithOnConflict;
        }
        return -1L;
    }

    public final synchronized void upsertMessages(List<? extends CTMessageDAO> inboxMessages) {
        Intrinsics.checkNotNullParameter(inboxMessages, "inboxMessages");
        if (!belowMemThreshold()) {
            this.logger.verbose(NOT_ENOUGH_SPACE_LOG);
            return;
        }
        for (CTMessageDAO cTMessageDAO : inboxMessages) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Column.ID, cTMessageDAO.getId());
            contentValues.put("data", cTMessageDAO.getJsonData().toString());
            contentValues.put("wzrkParams", cTMessageDAO.getWzrkParams().toString());
            contentValues.put(Column.CAMPAIGN, cTMessageDAO.getCampaignId());
            contentValues.put("tags", cTMessageDAO.getTags());
            contentValues.put("isRead", Integer.valueOf(cTMessageDAO.isRead()));
            contentValues.put(Column.EXPIRES, Long.valueOf(cTMessageDAO.getExpires()));
            contentValues.put(Column.CREATED_AT, Long.valueOf(cTMessageDAO.getDate()));
            contentValues.put(Column.USER_ID, cTMessageDAO.getUserId());
            try {
                this.dbHelper.getWritableDatabase().insertWithOnConflict(Table.INBOX_MESSAGES.getTableName(), null, contentValues, 5);
            } catch (SQLiteException unused) {
                this.logger.verbose("Error adding data to table " + Table.INBOX_MESSAGES.getTableName());
            }
        }
    }

    public final synchronized void cleanUpPushNotifications() {
        cleanInternal(Table.PUSH_NOTIFICATIONS, 0L);
    }

    public final synchronized void cleanupEventsFromLastId(String lastId, Table table) {
        Intrinsics.checkNotNullParameter(lastId, "lastId");
        Intrinsics.checkNotNullParameter(table, "table");
        String tableName = table.getTableName();
        try {
            this.dbHelper.getWritableDatabase().delete(tableName, "_id <= ?", new String[]{lastId});
        } catch (SQLiteException unused) {
            this.logger.verbose("Error removing sent data from table " + tableName + " Recreating DB");
            deleteDB$clevertap_core_release();
        }
    }

    public final synchronized void storePushNotificationId(String id, long ttl) {
        if (id == null) {
            return;
        }
        if (!belowMemThreshold()) {
            this.logger.verbose(NOT_ENOUGH_SPACE_LOG);
            return;
        }
        String tableName = Table.PUSH_NOTIFICATIONS.getTableName();
        if (ttl <= 0) {
            ttl = System.currentTimeMillis() + Constants.DEFAULT_PUSH_TTL;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", id);
        contentValues.put(Column.CREATED_AT, Long.valueOf(ttl));
        contentValues.put("isRead", (Integer) 0);
        try {
            this.dbHelper.getWritableDatabase().insert(tableName, null, contentValues);
            this.rtlDirtyFlag = true;
            this.logger.verbose("Stored PN - " + id + " with TTL - " + ttl);
        } catch (SQLiteException unused) {
            this.logger.verbose("Error adding data to table " + tableName + " Recreating DB");
            deleteDB$clevertap_core_release();
        }
    }

    public final synchronized void cleanupStaleEvents(Table table) {
        Intrinsics.checkNotNullParameter(table, "table");
        cleanInternal(table, DATA_EXPIRATION);
    }

    public final synchronized JSONObject fetchEvents(Table table, int limit) {
        JSONObject jSONObject;
        String string;
        Cursor cursorQuery;
        Intrinsics.checkNotNullParameter(table, "table");
        String tableName = table.getTableName();
        JSONArray jSONArray = new JSONArray();
        jSONObject = null;
        try {
            cursorQuery = this.dbHelper.getReadableDatabase().query(tableName, null, null, null, null, null, "created_at ASC", String.valueOf(limit));
        } catch (Exception e) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
        }
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                string = null;
                while (cursor2.moveToNext()) {
                    if (cursor2.isLast()) {
                        string = cursor2.getString(cursor2.getColumnIndexOrThrow(Column.ID));
                    }
                    try {
                        jSONArray.put(new JSONObject(cursor2.getString(cursor2.getColumnIndexOrThrow("data"))));
                    } catch (JSONException unused) {
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } finally {
            }
        } else {
            string = null;
        }
        if (string != null) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(string, jSONArray);
                jSONObject = jSONObject2;
            } catch (JSONException unused2) {
            }
        }
        return jSONObject;
    }

    public final synchronized void updatePushNotificationIds(String[] ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        if (ids.length == 0) {
            return;
        }
        if (!belowMemThreshold()) {
            this.logger.verbose(NOT_ENOUGH_SPACE_LOG);
            return;
        }
        String tableName = Table.PUSH_NOTIFICATIONS.getTableName();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isRead", (Integer) 1);
        try {
            this.dbHelper.getWritableDatabase().update(tableName, contentValues, "data IN (" + getTemplateMarkersList(ids.length) + ')', ids);
            this.rtlDirtyFlag = false;
        } catch (SQLiteException unused) {
            this.logger.verbose("Error adding data to table " + tableName + " Recreating DB");
            deleteDB$clevertap_core_release();
        }
    }

    public final synchronized long storeObject(JSONObject obj, Table table) {
        long jSimpleQueryForLong;
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(table, "table");
        if (!belowMemThreshold()) {
            this.logger.verbose(NOT_ENOUGH_SPACE_LOG);
            return -2L;
        }
        String tableName = table.getTableName();
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", obj.toString());
        contentValues.put(Column.CREATED_AT, Long.valueOf(System.currentTimeMillis()));
        try {
            this.dbHelper.getWritableDatabase().insert(tableName, null, contentValues);
            jSimpleQueryForLong = this.dbHelper.getWritableDatabase().compileStatement("SELECT COUNT(*) FROM " + tableName).simpleQueryForLong();
        } catch (SQLiteException unused) {
            this.logger.verbose("Error adding data to table " + tableName + " Recreating DB");
            deleteDB$clevertap_core_release();
            jSimpleQueryForLong = -1;
        }
        return jSimpleQueryForLong;
    }

    public final synchronized void removeEvents(Table table) {
        Intrinsics.checkNotNullParameter(table, "table");
        String tableName = table.getTableName();
        try {
            this.dbHelper.getWritableDatabase().delete(tableName, null, null);
        } catch (SQLiteException unused) {
            this.logger.verbose("Error removing all events from table " + tableName + " Recreating DB");
            deleteDB$clevertap_core_release();
        }
    }

    public final UserEventLogDAO userEventLogDAO() {
        UserEventLogDAOImpl userEventLogDAOImpl = this.userEventLogDao;
        if (userEventLogDAOImpl == null) {
            synchronized (this) {
                userEventLogDAOImpl = this.userEventLogDao;
                if (userEventLogDAOImpl == null) {
                    DatabaseHelper databaseHelper = this.dbHelper;
                    Logger logger = this.logger;
                    Intrinsics.checkNotNullExpressionValue(logger, "logger");
                    UserEventLogDAOImpl userEventLogDAOImpl2 = new UserEventLogDAOImpl(databaseHelper, logger, Table.USER_EVENT_LOGS_TABLE);
                    this.userEventLogDao = userEventLogDAOImpl2;
                    userEventLogDAOImpl = userEventLogDAOImpl2;
                }
            }
        }
        return userEventLogDAOImpl;
    }

    private final boolean belowMemThreshold() {
        return this.dbHelper.belowMemThreshold();
    }

    private final void cleanInternal(Table table, long expiration) {
        long jCurrentTimeMillis = (System.currentTimeMillis() - expiration) / 1000;
        String tableName = table.getTableName();
        try {
            this.dbHelper.getWritableDatabase().delete(tableName, "created_at <= " + jCurrentTimeMillis, null);
        } catch (SQLiteException e) {
            this.logger.verbose("Error removing stale event records from " + tableName + ". Recreating DB.", e);
            deleteDB$clevertap_core_release();
        }
    }

    public final void deleteDB$clevertap_core_release() {
        this.dbHelper.deleteDatabase();
    }

    private final String fetchPushNotificationId(String id) {
        String tableName = Table.PUSH_NOTIFICATIONS.getTableName();
        String str = "";
        try {
            Cursor cursorQuery = this.dbHelper.getReadableDatabase().query(tableName, null, "data =?", new String[]{id}, null, null, null);
            if (cursorQuery != null) {
                Cursor cursor = cursorQuery;
                try {
                    Cursor cursor2 = cursor;
                    if (cursor2.moveToFirst()) {
                        String string = cursor2.getString(cursor2.getColumnIndexOrThrow("data"));
                        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…ndexOrThrow(Column.DATA))");
                        str = string;
                    }
                    this.logger.verbose("Fetching PID for check - " + str);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(cursor, null);
                } finally {
                }
            }
        } catch (Exception e) {
            this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
        }
        return str;
    }

    private final String getDatabaseName(CleverTapInstanceConfig config) {
        return config.isDefaultInstance() ? DATABASE_NAME : "clevertap_" + config.getAccountId();
    }

    private final String getTemplateMarkersList(int count) {
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append("?");
            int i = count - 1;
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(", ?");
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final synchronized String[] fetchPushNotificationIds() {
        if (this.rtlDirtyFlag) {
            String tableName = Table.PUSH_NOTIFICATIONS.getTableName();
            ArrayList arrayList = new ArrayList();
            try {
                Cursor cursorQuery = this.dbHelper.getReadableDatabase().query(tableName, null, "isRead = 0", null, null, null, null);
                if (cursorQuery != null) {
                    Cursor cursor = cursorQuery;
                    try {
                        Cursor cursor2 = cursor;
                        while (cursor2.moveToNext()) {
                            int columnIndex = cursor2.getColumnIndex("data");
                            if (columnIndex >= 0) {
                                String string = cursor2.getString(columnIndex);
                                this.logger.verbose("Fetching PID - " + string);
                                arrayList.add(string);
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, null);
                    } finally {
                    }
                }
            } catch (SQLiteException e) {
                this.logger.verbose("Could not fetch records out of database " + tableName + ClassUtils.PACKAGE_SEPARATOR_CHAR, e);
            }
            Object[] array = arrayList.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            return (String[]) array;
        }
        return new String[0];
    }
}
