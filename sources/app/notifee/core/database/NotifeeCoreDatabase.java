package app.notifee.core.database;

import android.content.Context;
import android.database.SQLException;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import n.o.t.i.f.e.e.o;

/* loaded from: classes5.dex */
public abstract class NotifeeCoreDatabase extends RoomDatabase {

    /* renamed from: a, reason: collision with root package name */
    public static volatile NotifeeCoreDatabase f46a;
    public static final ExecutorService b = Executors.newCachedThreadPool();
    public static final Migration c = new a(1, 2);

    public class a extends Migration {
        public a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) throws SQLException {
            supportSQLiteDatabase.execSQL("ALTER TABLE work_data ADD COLUMN with_alarm_manager INTEGER NOT NULL DEFAULT 0");
        }
    }

    public abstract o a();

    public static NotifeeCoreDatabase a(Context context) {
        if (f46a == null) {
            synchronized (NotifeeCoreDatabase.class) {
                if (f46a == null) {
                    f46a = (NotifeeCoreDatabase) Room.databaseBuilder(context.getApplicationContext(), NotifeeCoreDatabase.class, "notifee_core_database").addMigrations(c).build();
                }
            }
        }
        return f46a;
    }
}
