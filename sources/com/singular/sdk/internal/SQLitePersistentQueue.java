package com.singular.sdk.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import java.io.IOException;

/* loaded from: classes6.dex */
public class SQLitePersistentQueue implements Queue {
    private static final SingularLog logger = SingularLog.getLogger("SQLitePersistentQueue");
    private SQLiteManager sqlite;

    public SQLitePersistentQueue(Context context) {
        this.sqlite = new SQLiteManager(new SQLiteHelper(context.getApplicationContext()));
    }

    @Override // com.singular.sdk.internal.Queue
    public synchronized void add(String str) throws IOException {
        if (this.sqlite.insert(str) == -1) {
            throw new IOException("Failed to add element = " + str);
        }
    }

    @Override // com.singular.sdk.internal.Queue
    public synchronized String peek() throws IOException {
        return this.sqlite.getHead();
    }

    @Override // com.singular.sdk.internal.Queue
    public synchronized void remove() throws IOException {
        this.sqlite.removeHead();
    }

    private static class SQLiteManager {
        private final SQLiteHelper helper;

        SQLiteManager(SQLiteHelper sQLiteHelper) {
            this.helper = sQLiteHelper;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x006e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        long insert(java.lang.String r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.String r0 = "insert() row = "
                android.content.ContentValues r1 = new android.content.ContentValues
                r1.<init>()
                java.lang.String r2 = "value"
                r1.put(r2, r8)
                r8 = 0
                com.singular.sdk.internal.SQLitePersistentQueue$SQLiteHelper r2 = r7.helper     // Catch: java.lang.Throwable -> L5e android.database.SQLException -> L62
                android.database.sqlite.SQLiteDatabase r2 = r2.getWritableDatabase()     // Catch: java.lang.Throwable -> L5e android.database.SQLException -> L62
                java.lang.String r3 = "events"
                long r3 = r2.insert(r3, r8, r1)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                com.singular.sdk.internal.SingularLog r8 = com.singular.sdk.internal.SQLitePersistentQueue.access$000()     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r1.<init>(r0)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                java.lang.StringBuilder r0 = r1.append(r3)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                java.lang.String r0 = r0.toString()     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r8.debug(r0)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                long r0 = r7.getCount(r2)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r5 = 10000(0x2710, double:4.9407E-320)
                int r8 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
                if (r8 <= 0) goto L56
                com.singular.sdk.internal.SingularLog r8 = com.singular.sdk.internal.SQLitePersistentQueue.access$000()     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                java.lang.String r5 = "Pruning Queue; current size = %d; max size = %d"
                r6 = 2
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r1 = 0
                r6[r1] = r0     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r0 = 10000(0x2710, float:1.4013E-41)
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r1 = 1
                r6[r1] = r0     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r8.debug(r5, r6)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
                r7.removeHead(r2)     // Catch: android.database.SQLException -> L5c java.lang.Throwable -> L6b
            L56:
                if (r2 == 0) goto L5b
                r2.close()
            L5b:
                return r3
            L5c:
                r8 = move-exception
                goto L65
            L5e:
                r0 = move-exception
                r2 = r8
                r8 = r0
                goto L6c
            L62:
                r0 = move-exception
                r2 = r8
                r8 = r0
            L65:
                java.io.IOException r0 = new java.io.IOException     // Catch: java.lang.Throwable -> L6b
                r0.<init>(r8)     // Catch: java.lang.Throwable -> L6b
                throw r0     // Catch: java.lang.Throwable -> L6b
            L6b:
                r8 = move-exception
            L6c:
                if (r2 == 0) goto L71
                r2.close()
            L71:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.singular.sdk.internal.SQLitePersistentQueue.SQLiteManager.insert(java.lang.String):long");
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0077  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        java.lang.String getHead() throws java.lang.Throwable {
            /*
                r15 = this;
                r0 = 0
                com.singular.sdk.internal.SQLitePersistentQueue$SQLiteHelper r1 = r15.helper     // Catch: java.lang.Throwable -> L60 android.database.SQLException -> L65
                android.database.sqlite.SQLiteDatabase r1 = r1.getReadableDatabase()     // Catch: java.lang.Throwable -> L60 android.database.SQLException -> L65
                long r10 = r15.getMinId(r1)     // Catch: java.lang.Throwable -> L56 android.database.SQLException -> L5b
                r12 = 1
                java.lang.String[] r4 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> L56 android.database.SQLException -> L5b
                java.lang.String r2 = "value"
                r13 = 0
                r4[r13] = r2     // Catch: java.lang.Throwable -> L56 android.database.SQLException -> L5b
                java.lang.String r5 = "_id = ?"
                java.lang.String[] r6 = new java.lang.String[r12]     // Catch: java.lang.Throwable -> L56 android.database.SQLException -> L5b
                java.lang.String r2 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L56 android.database.SQLException -> L5b
                r6[r13] = r2     // Catch: java.lang.Throwable -> L56 android.database.SQLException -> L5b
                java.lang.String r3 = "events"
                r7 = 0
                r8 = 0
                r9 = 0
                r2 = r1
                android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L56 android.database.SQLException -> L5b
                r2.moveToFirst()     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                int r3 = r2.getCount()     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                if (r3 != 0) goto L31
                goto L35
            L31:
                java.lang.String r0 = r2.getString(r13)     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
            L35:
                com.singular.sdk.internal.SingularLog r3 = com.singular.sdk.internal.SQLitePersistentQueue.access$000()     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                java.lang.String r4 = "getHead() _id = %d, value = %s"
                r5 = 2
                java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                java.lang.Long r6 = java.lang.Long.valueOf(r10)     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                r5[r13] = r6     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                r5[r12] = r0     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                r3.debug(r4, r5)     // Catch: android.database.SQLException -> L54 java.lang.Throwable -> L6f
                if (r2 == 0) goto L4e
                r2.close()
            L4e:
                if (r1 == 0) goto L53
                r1.close()
            L53:
                return r0
            L54:
                r0 = move-exception
                goto L69
            L56:
                r2 = move-exception
                r14 = r2
                r2 = r0
                r0 = r14
                goto L70
            L5b:
                r2 = move-exception
                r14 = r2
                r2 = r0
                r0 = r14
                goto L69
            L60:
                r1 = move-exception
                r2 = r0
                r0 = r1
                r1 = r2
                goto L70
            L65:
                r1 = move-exception
                r2 = r0
                r0 = r1
                r1 = r2
            L69:
                java.io.IOException r3 = new java.io.IOException     // Catch: java.lang.Throwable -> L6f
                r3.<init>(r0)     // Catch: java.lang.Throwable -> L6f
                throw r3     // Catch: java.lang.Throwable -> L6f
            L6f:
                r0 = move-exception
            L70:
                if (r2 == 0) goto L75
                r2.close()
            L75:
                if (r1 == 0) goto L7a
                r1.close()
            L7a:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.singular.sdk.internal.SQLitePersistentQueue.SQLiteManager.getHead():java.lang.String");
        }

        String removeHead() throws IOException {
            SQLiteDatabase writableDatabase = null;
            try {
                writableDatabase = this.helper.getWritableDatabase();
                return removeHead(writableDatabase);
            } finally {
                if (writableDatabase != null) {
                    writableDatabase.close();
                }
            }
        }

        private String removeHead(SQLiteDatabase sQLiteDatabase) throws Throwable {
            long minId;
            String[] strArr;
            Cursor cursorQuery;
            Cursor cursor = null;
            try {
                try {
                    minId = getMinId(sQLiteDatabase);
                    strArr = new String[]{String.valueOf(minId)};
                    cursorQuery = sQLiteDatabase.query("events", new String[]{"value"}, "_id = ?", strArr, null, null, null);
                } catch (SQLException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                cursorQuery.moveToFirst();
                if (cursorQuery.getCount() == 0) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return null;
                }
                String string = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
                sQLiteDatabase.delete("events", "_id = ?", strArr);
                SQLitePersistentQueue.logger.debug("removeHead() _id = %d", Long.valueOf(minId));
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return string;
            } catch (SQLException e2) {
                e = e2;
                cursor = cursorQuery;
                throw new IOException(e);
            } catch (Throwable th2) {
                th = th2;
                cursor = cursorQuery;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        private long getMinId(SQLiteDatabase sQLiteDatabase) throws IOException {
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = sQLiteDatabase.rawQuery("SELECT MIN(_id) FROM events", null);
                    cursorRawQuery.moveToFirst();
                    long j = cursorRawQuery.getLong(0);
                    SQLitePersistentQueue.logger.debug("getMinId() id = %d", Long.valueOf(j));
                    return j;
                } catch (SQLException e) {
                    throw new IOException(e);
                }
            } finally {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            }
        }

        private long getCount(SQLiteDatabase sQLiteDatabase) throws IOException {
            Cursor cursorRawQuery = null;
            try {
                try {
                    cursorRawQuery = sQLiteDatabase.rawQuery("SELECT COUNT(_id) FROM events", null);
                    cursorRawQuery.moveToFirst();
                    long j = cursorRawQuery.getLong(0);
                    SQLitePersistentQueue.logger.debug("getCount() = %d", Long.valueOf(j));
                    return j;
                } catch (SQLException e) {
                    throw new IOException(e);
                }
            } finally {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            }
        }
    }

    private static class SQLiteHelper extends SQLiteOpenHelper implements BaseColumns {
        private static final String COLUMN_NAME_VALUE = "value";
        private static final String COMMA_SEP = ",";
        private static final String DATABASE_NAME = "singular-1.db";
        private static final int DATABASE_VERSION = 1;
        private static final String SQL_CREATE_ENTRIES = "CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,value TEXT )";
        private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS events";
        private static final String TABLE_NAME = "events";
        private static final String TEXT_TYPE = " TEXT";

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        public SQLiteHelper(Context context) {
            super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
            sQLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        }
    }
}
