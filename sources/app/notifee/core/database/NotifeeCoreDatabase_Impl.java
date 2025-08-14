package app.notifee.core.database;

import android.database.SQLException;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import n.o.t.i.f.e.e.o;
import n.o.t.i.f.e.e.p;

/* loaded from: classes5.dex */
public final class NotifeeCoreDatabase_Impl extends NotifeeCoreDatabase {
    public volatile o d;

    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) throws SQLException {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `work_data` (`id` TEXT NOT NULL, `notification` BLOB, `trigger` BLOB, `with_alarm_manager` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '24b2477514809255df232947ce7928c4')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) throws SQLException {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `work_data`");
            if (((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            ((RoomDatabase) NotifeeCoreDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
            NotifeeCoreDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) NotifeeCoreDatabase_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) throws SQLException {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap map = new HashMap(4);
            map.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
            map.put("notification", new TableInfo.Column("notification", "BLOB", false, 0, null, 1));
            map.put("trigger", new TableInfo.Column("trigger", "BLOB", false, 0, null, 1));
            map.put("with_alarm_manager", new TableInfo.Column("with_alarm_manager", "INTEGER", true, 0, "0", 1));
            TableInfo tableInfo = new TableInfo("work_data", map, new HashSet(0), new HashSet(0));
            TableInfo tableInfo2 = TableInfo.read(supportSQLiteDatabase, "work_data");
            return !tableInfo.equals(tableInfo2) ? new RoomOpenHelper.ValidationResult(false, "work_data(app.notifee.core.database.WorkDataEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2) : new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() throws SQLException {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        try {
            beginTransaction();
            writableDatabase.execSQL("DELETE FROM `work_data`");
            setTransactionSuccessful();
        } finally {
            endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "work_data");
    }

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(2), "24b2477514809255df232947ce7928c4", "1ddaa4b892e61b0f7010597ddc582ed3")).build());
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(o.class, Collections.emptyList());
        return map;
    }

    @Override // app.notifee.core.database.NotifeeCoreDatabase
    public o a() {
        o oVar;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new p(this);
            }
            oVar = this.d;
        }
        return oVar;
    }
}
