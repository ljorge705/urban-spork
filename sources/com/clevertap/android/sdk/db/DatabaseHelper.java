package com.clevertap.android.sdk.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import java.io.File;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CtDatabase.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 #2\u00020\u0001:\u0001#B)\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0007J\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0002J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J \u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/clevertap/android/sdk/db/DatabaseHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "config", "Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "dbName", "", "logger", "Lcom/clevertap/android/sdk/Logger;", "(Landroid/content/Context;Lcom/clevertap/android/sdk/CleverTapInstanceConfig;Ljava/lang/String;Lcom/clevertap/android/sdk/Logger;)V", "getConfig", "()Lcom/clevertap/android/sdk/CleverTapInstanceConfig;", "getContext", "()Landroid/content/Context;", "databaseFile", "Ljava/io/File;", "belowMemThreshold", "", "deleteDatabase", "", "executeStatement", "db", "Landroid/database/sqlite/SQLiteDatabase;", "statement", "getDeviceIdForAccountIdFromPrefs", Constants.KEY_ACCOUNT_ID, "migrateDataString", "dataString", "migrateUserProfilesTable", "onCreate", "onUpgrade", "oldVersion", "", "newVersion", "Companion", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final int DB_LIMIT = 20971520;
    private final CleverTapInstanceConfig config;
    private final Context context;
    private final File databaseFile;
    private final Logger logger;

    public final CleverTapInstanceConfig getConfig() {
        return this.config;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatabaseHelper(Context context, CleverTapInstanceConfig config, String str, Logger logger) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 5);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.context = context;
        this.config = config;
        this.logger = logger;
        File databasePath = context.getDatabasePath(str);
        Intrinsics.checkNotNullExpressionValue(databasePath, "context.getDatabasePath(dbName)");
        this.databaseFile = databasePath;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) throws SQLException {
        Intrinsics.checkNotNullParameter(db, "db");
        this.logger.verbose("Creating CleverTap DB");
        executeStatement(db, CtDatabaseKt.CREATE_EVENTS_TABLE);
        executeStatement(db, CtDatabaseKt.CREATE_USER_EVENT_LOGS_TABLE);
        executeStatement(db, CtDatabaseKt.CREATE_PROFILE_EVENTS_TABLE);
        executeStatement(db, CtDatabaseKt.CREATE_USER_PROFILES_TABLE);
        executeStatement(db, CtDatabaseKt.CREATE_INBOX_MESSAGES_TABLE);
        executeStatement(db, CtDatabaseKt.CREATE_PUSH_NOTIFICATIONS_TABLE);
        executeStatement(db, CtDatabaseKt.CREATE_UNINSTALL_TS_TABLE);
        executeStatement(db, CtDatabaseKt.CREATE_NOTIFICATION_VIEWED_TABLE);
        executeStatement(db, CtDatabaseKt.EVENTS_TIME_INDEX);
        executeStatement(db, CtDatabaseKt.PROFILE_EVENTS_TIME_INDEX);
        executeStatement(db, CtDatabaseKt.UNINSTALL_TS_INDEX);
        executeStatement(db, CtDatabaseKt.PUSH_NOTIFICATIONS_TIME_INDEX);
        executeStatement(db, CtDatabaseKt.INBOX_MESSAGES_COMP_ID_USERID_INDEX);
        executeStatement(db, CtDatabaseKt.NOTIFICATION_VIEWED_INDEX);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
        Intrinsics.checkNotNullParameter(db, "db");
        this.logger.verbose("Upgrading CleverTap DB to version " + newVersion);
        if (oldVersion == 1) {
            executeStatement(db, CtDatabaseKt.DROP_TABLE_UNINSTALL_TS);
            executeStatement(db, CtDatabaseKt.DROP_TABLE_INBOX_MESSAGES);
            executeStatement(db, CtDatabaseKt.DROP_TABLE_PUSH_NOTIFICATION_VIEWED);
            executeStatement(db, CtDatabaseKt.CREATE_INBOX_MESSAGES_TABLE);
            executeStatement(db, CtDatabaseKt.CREATE_PUSH_NOTIFICATIONS_TABLE);
            executeStatement(db, CtDatabaseKt.CREATE_UNINSTALL_TS_TABLE);
            executeStatement(db, CtDatabaseKt.CREATE_NOTIFICATION_VIEWED_TABLE);
            executeStatement(db, CtDatabaseKt.UNINSTALL_TS_INDEX);
            executeStatement(db, CtDatabaseKt.PUSH_NOTIFICATIONS_TIME_INDEX);
            executeStatement(db, CtDatabaseKt.INBOX_MESSAGES_COMP_ID_USERID_INDEX);
            executeStatement(db, CtDatabaseKt.NOTIFICATION_VIEWED_INDEX);
            migrateUserProfilesTable(db);
        } else if (oldVersion == 2) {
            executeStatement(db, CtDatabaseKt.DROP_TABLE_PUSH_NOTIFICATION_VIEWED);
            executeStatement(db, CtDatabaseKt.CREATE_NOTIFICATION_VIEWED_TABLE);
            executeStatement(db, CtDatabaseKt.NOTIFICATION_VIEWED_INDEX);
            migrateUserProfilesTable(db);
        } else if (oldVersion == 3) {
            migrateUserProfilesTable(db);
        }
        if (oldVersion < 5) {
            executeStatement(db, CtDatabaseKt.CREATE_USER_EVENT_LOGS_TABLE);
        }
    }

    private final String getDeviceIdForAccountIdFromPrefs(String accountId) {
        String str = "deviceId:" + accountId;
        String str2 = "fallbackId:" + accountId;
        String string = StorageHelper.getString(this.context, str, null);
        if (string != null) {
            return string;
        }
        String string2 = this.config.isDefaultInstance() ? StorageHelper.getString(this.context, str, null) : StorageHelper.getString(this.context, str2, "");
        Intrinsics.checkNotNullExpressionValue(string2, "if (config.isDefaultInst…context, fallbackKey, \"\")");
        return string2;
    }

    private final void migrateUserProfilesTable(SQLiteDatabase db) throws SQLException {
        executeStatement(db, CtDatabaseKt.CREATE_TEMP_USER_PROFILES_TABLE);
        String accountId = this.config.getAccountId();
        Intrinsics.checkNotNullExpressionValue(accountId, "config.accountId");
        String deviceIdForAccountIdFromPrefs = getDeviceIdForAccountIdFromPrefs(accountId);
        Cursor cursorRawQuery = db.rawQuery("SELECT _id, data FROM " + Table.USER_PROFILES.getTableName() + ';', null);
        Cursor cursor = cursorRawQuery;
        try {
            if (cursorRawQuery.moveToFirst()) {
                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(Column.ID));
                String dataString = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("data"));
                Intrinsics.checkNotNullExpressionValue(dataString, "dataString");
                executeStatement(db, "INSERT INTO temp_" + Table.USER_PROFILES.getTableName() + " (_id, deviceID, data)\n                                 VALUES ('" + string + "', '" + deviceIdForAccountIdFromPrefs + "', '" + migrateDataString(dataString) + "');");
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor, null);
            executeStatement(db, CtDatabaseKt.DROP_USER_PROFILES_TABLE);
            executeStatement(db, CtDatabaseKt.RENAME_USER_PROFILES_TABLE);
        } finally {
        }
    }

    private final String migrateDataString(String dataString) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject(dataString);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                Object objValueOf = jSONObject.get(next);
                if ((objValueOf instanceof String) && StringsKt.startsWith$default((String) objValueOf, Constants.DATE_PREFIX, false, 2, (Object) null)) {
                    objValueOf = Long.valueOf(Long.parseLong(StringsKt.removePrefix((String) objValueOf, (CharSequence) Constants.DATE_PREFIX)));
                    jSONObject.put(next, ((Number) objValueOf).longValue());
                }
                if (objValueOf instanceof JSONObject) {
                    if (((JSONObject) objValueOf).has(Constants.COMMAND_SET)) {
                        jSONObject.put(next, ((JSONObject) objValueOf).getJSONArray(Constants.COMMAND_SET));
                    } else if (((JSONObject) objValueOf).has(Constants.COMMAND_ADD)) {
                        jSONObject.put(next, ((JSONObject) objValueOf).getJSONArray(Constants.COMMAND_ADD));
                    }
                }
            }
            String string = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(string, "{\n            val jsonOb…ject.toString()\n        }");
            return string;
        } catch (JSONException e) {
            this.logger.verbose("Error while migrating data column for userProfiles table for data = " + dataString, e);
            return dataString;
        }
    }

    public final boolean belowMemThreshold() {
        return !this.databaseFile.exists() || Math.max(this.databaseFile.getUsableSpace(), 20971520L) >= this.databaseFile.length();
    }

    public final void deleteDatabase() {
        close();
        if (this.databaseFile.delete()) {
            return;
        }
        this.logger.debug("Could not delete database");
    }

    private final void executeStatement(SQLiteDatabase db, String statement) throws SQLException {
        SQLiteStatement sQLiteStatementCompileStatement = db.compileStatement(statement);
        this.logger.verbose("Executing - " + statement);
        sQLiteStatementCompileStatement.execute();
    }
}
