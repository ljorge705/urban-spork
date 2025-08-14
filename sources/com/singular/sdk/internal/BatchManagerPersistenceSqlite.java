package com.singular.sdk.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class BatchManagerPersistenceSqlite implements BatchManagerPersistence {
    private static final SingularLog logger = SingularLog.getLogger("BatchManagerPersistenceSqlite");
    private Context context;
    private BatchManagerSQLiteHelper helper;

    public BatchManagerPersistenceSqlite(Context context) {
        this.helper = new BatchManagerSQLiteHelper(context);
        this.context = context;
    }

    @Override // com.singular.sdk.internal.BatchManagerPersistence
    public synchronized boolean addEvent(String str, String str2) throws IOException {
        logger.debug("addEvent: key: " + str + " value: " + str2);
        return this.helper.insert(str, str2);
    }

    @Override // com.singular.sdk.internal.BatchManagerPersistence
    public synchronized boolean updateEvent(String str, String str2) throws IOException {
        logger.debug("updateEvent: key: " + str + " value: " + str2);
        return this.helper.update(str, str2);
    }

    @Override // com.singular.sdk.internal.BatchManagerPersistence
    public synchronized Set<Map.Entry<String, String>> getAllEvents() {
        Set<Map.Entry<String, String>> all;
        SingularLog singularLog = logger;
        singularLog.debug("getAllEvents");
        all = this.helper.getAll();
        singularLog.debug("getAllEvents: returning " + all.size() + " events");
        return all;
    }

    @Override // com.singular.sdk.internal.BatchManagerPersistence
    public synchronized boolean deleteEvent(String str) throws IOException {
        logger.debug("deleteEvent: key: " + str);
        return this.helper.delete(str);
    }

    private static class BatchManagerSQLiteHelper extends SQLiteOpenHelper {
        private static final String COLUMN_NAME_KEY = "eventKey";
        private static final String COLUMN_NAME_VALUE = "value";
        private static final String COMMA_SEP = ",";
        private static final String DATABASE_NAME = "singular-batch-managerx-1.db";
        private static final int DATABASE_VERSION = 1;
        private static final String SQL_CREATE_ENTRIES = "CREATE TABLE events (eventKey  TEXT PRIMARY KEY NOT NULL,value TEXT )";
        private static final String TABLE_NAME = "events";
        private static final String TEXT_TYPE = " TEXT";
        private SQLiteDatabase db;

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        public BatchManagerSQLiteHelper(Context context) {
            super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        }

        public synchronized boolean keyExists(String str) throws IOException {
            BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.keyExists: key: " + str);
            Cursor cursor = null;
            try {
                try {
                    SQLiteDatabase writableDatabase = getWritableDatabase();
                    this.db = writableDatabase;
                    Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT * FROM events WHERE eventKey= ?", new String[]{str});
                    if (cursorRawQuery != null && cursorRawQuery.getCount() != 0) {
                        BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.keyExists: returning true ");
                        cursorRawQuery.close();
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                        SQLiteDatabase sQLiteDatabase = this.db;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return true;
                    }
                    BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.keyExists: returning false ");
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    SQLiteDatabase sQLiteDatabase2 = this.db;
                    if (sQLiteDatabase2 != null) {
                        sQLiteDatabase2.close();
                    }
                    return false;
                } catch (SQLException unused) {
                    throw new IOException();
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    cursor.close();
                }
                SQLiteDatabase sQLiteDatabase3 = this.db;
                if (sQLiteDatabase3 != null) {
                    sQLiteDatabase3.close();
                }
                throw th;
            }
        }

        public synchronized boolean insert(String str, String str2) throws IOException {
            SQLiteDatabase writableDatabase;
            BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.insert key: " + str + " value: " + str2);
            if (keyExists(str)) {
                BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.insert key already exists - returning false ");
                return false;
            }
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    writableDatabase = getWritableDatabase();
                } catch (SQLException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_NAME_KEY, str);
                contentValues.put("value", str2);
                if (writableDatabase.insert(TABLE_NAME, null, contentValues) != -1) {
                    BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.insert success ");
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return true;
                }
                BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.insert false ");
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
                return false;
            } catch (SQLException e2) {
                e = e2;
                sQLiteDatabase = writableDatabase;
                throw new IOException(e);
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabase = writableDatabase;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }

        public synchronized boolean update(String str, String str2) throws IOException {
            BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.update key: " + str + " value: " + str2);
            try {
                if (!keyExists(str)) {
                    BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.update: key does not exist - returning false ");
                    return false;
                }
                try {
                    this.db = getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(COLUMN_NAME_KEY, str);
                    contentValues.put("value", str2);
                    if (this.db.replace(TABLE_NAME, null, contentValues) != -1) {
                        BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.update - success");
                        SQLiteDatabase sQLiteDatabase = this.db;
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return true;
                    }
                    BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.update - failed");
                    return false;
                } catch (SQLException unused) {
                    throw new IOException();
                }
            } finally {
                SQLiteDatabase sQLiteDatabase2 = this.db;
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
            }
        }

        public synchronized boolean delete(String str) throws IOException {
            BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.delete key: " + str);
            try {
                if (!keyExists(str)) {
                    BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.delete key does not exist - returning false ");
                    return false;
                }
                try {
                    SQLiteDatabase writableDatabase = getWritableDatabase();
                    this.db = writableDatabase;
                    if (writableDatabase.delete(TABLE_NAME, "eventKey =?", new String[]{str}) != 1) {
                        BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.delete failed ");
                        return false;
                    }
                    BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.delete - success ");
                    SQLiteDatabase sQLiteDatabase = this.db;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    return true;
                } catch (SQLException unused) {
                    throw new IOException();
                }
            } finally {
                SQLiteDatabase sQLiteDatabase2 = this.db;
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
            }
        }

        public synchronized Set<Map.Entry<String, String>> getAll() {
            HashSet hashSet;
            SQLiteDatabase sQLiteDatabase;
            BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.getAll");
            hashSet = new HashSet();
            Cursor cursorRawQuery = null;
            try {
                SQLiteDatabase readableDatabase = getReadableDatabase();
                this.db = readableDatabase;
                cursorRawQuery = readableDatabase.rawQuery("SELECT * FROM events", null);
                if (cursorRawQuery != null && cursorRawQuery.getCount() != 0) {
                    cursorRawQuery.moveToFirst();
                    do {
                        hashSet.add(new AbstractMap.SimpleEntry(cursorRawQuery.getString(cursorRawQuery.getColumnIndex(COLUMN_NAME_KEY)), cursorRawQuery.getString(cursorRawQuery.getColumnIndex("value"))));
                    } while (cursorRawQuery.moveToNext());
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                sQLiteDatabase = this.db;
            } catch (Throwable th) {
                try {
                    BatchManagerPersistenceSqlite.logger.error("SQLiteHelper.getAll exception: " + th.getMessage());
                    BatchManagerPersistenceSqlite.logger.error(Utils.formatException(th));
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    sQLiteDatabase = this.db;
                    if (sQLiteDatabase != null) {
                    }
                } catch (Throwable th2) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    SQLiteDatabase sQLiteDatabase2 = this.db;
                    if (sQLiteDatabase2 != null) {
                        sQLiteDatabase2.close();
                    }
                    throw th2;
                }
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            BatchManagerPersistenceSqlite.logger.debug("SQLiteHelper.getAll returning: " + hashSet.size() + " entries");
            return hashSet;
        }
    }

    @Override // com.singular.sdk.internal.BatchManagerPersistence
    public synchronized long getSendId() {
        return Utils.getBatchSendId(this.context);
    }

    @Override // com.singular.sdk.internal.BatchManagerPersistence
    public synchronized long incSendId() {
        return Utils.incBatchSendId(this.context);
    }
}
