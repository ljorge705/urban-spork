package n.o.t.i.f.e.e;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class p implements o {

    /* renamed from: a, reason: collision with root package name */
    public final RoomDatabase f312a;
    public final EntityInsertionAdapter<q> b;
    public final EntityDeletionOrUpdateAdapter<q> c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;

    public class a extends EntityInsertionAdapter<q> {
        public a(p pVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, q qVar) {
            q qVar2 = qVar;
            String str = qVar2.f313a;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            byte[] bArr = qVar2.b;
            if (bArr == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindBlob(2, bArr);
            }
            byte[] bArr2 = qVar2.c;
            if (bArr2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindBlob(3, bArr2);
            }
            Boolean bool = qVar2.d;
            if ((bool == null ? null : Integer.valueOf(bool.booleanValue() ? 1 : 0)) == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindLong(4, r5.intValue());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `work_data` (`id`,`notification`,`trigger`,`with_alarm_manager`) VALUES (?,?,?,?)";
        }
    }

    public class b extends EntityDeletionOrUpdateAdapter<q> {
        public b(p pVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, q qVar) {
            q qVar2 = qVar;
            String str = qVar2.f313a;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            byte[] bArr = qVar2.b;
            if (bArr == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindBlob(2, bArr);
            }
            byte[] bArr2 = qVar2.c;
            if (bArr2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindBlob(3, bArr2);
            }
            Boolean bool = qVar2.d;
            if ((bool == null ? null : Integer.valueOf(bool.booleanValue() ? 1 : 0)) == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindLong(4, r0.intValue());
            }
            String str2 = qVar2.f313a;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, str2);
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE OR REPLACE `work_data` SET `id` = ?,`notification` = ?,`trigger` = ?,`with_alarm_manager` = ? WHERE `id` = ?";
        }
    }

    public class c extends SharedSQLiteStatement {
        public c(p pVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM work_data";
        }
    }

    public class d extends SharedSQLiteStatement {
        public d(p pVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM work_data WHERE id = ?";
        }
    }

    public p(RoomDatabase roomDatabase) {
        this.f312a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
        this.e = new d(this, roomDatabase);
    }

    @Override // n.o.t.i.f.e.e.o
    public void a(q qVar) {
        this.f312a.assertNotSuspendingTransaction();
        this.f312a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<q>) qVar);
            this.f312a.setTransactionSuccessful();
        } finally {
            this.f312a.endTransaction();
        }
    }

    @Override // n.o.t.i.f.e.e.o
    public void b(q qVar) {
        this.f312a.assertNotSuspendingTransaction();
        this.f312a.beginTransaction();
        try {
            this.c.handle(qVar);
            this.f312a.setTransactionSuccessful();
        } finally {
            this.f312a.endTransaction();
        }
    }

    @Override // n.o.t.i.f.e.e.o
    public void a(String str) {
        this.f312a.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.e.acquire();
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, str);
        }
        this.f312a.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.f312a.setTransactionSuccessful();
        } finally {
            this.f312a.endTransaction();
            this.e.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // n.o.t.i.f.e.e.o
    public void b() {
        this.f312a.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.d.acquire();
        this.f312a.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.f312a.setTransactionSuccessful();
        } finally {
            this.f312a.endTransaction();
            this.d.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // n.o.t.i.f.e.e.o
    public q b(String str) {
        boolean z = true;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from work_data WHERE id = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.f312a.assertNotSuspendingTransaction();
        q qVar = null;
        Boolean boolValueOf = null;
        Cursor cursorQuery = DBUtil.query(this.f312a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "notification");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "with_alarm_manager");
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                byte[] blob = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getBlob(columnIndexOrThrow2);
                byte[] blob2 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getBlob(columnIndexOrThrow3);
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow4));
                if (numValueOf != null) {
                    if (numValueOf.intValue() == 0) {
                        z = false;
                    }
                    boolValueOf = Boolean.valueOf(z);
                }
                qVar = new q(string, blob, blob2, boolValueOf);
            }
            return qVar;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // n.o.t.i.f.e.e.o
    public List<q> a(Boolean bool) {
        Boolean boolValueOf;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM work_data WHERE with_alarm_manager = ?", 1);
        if ((bool == null ? null : Integer.valueOf(bool.booleanValue() ? 1 : 0)) == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindLong(1, r15.intValue());
        }
        this.f312a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.f312a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "notification");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "with_alarm_manager");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                byte[] blob = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getBlob(columnIndexOrThrow2);
                byte[] blob2 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getBlob(columnIndexOrThrow3);
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow4));
                if (numValueOf == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                }
                arrayList.add(new q(string, blob, blob2, boolValueOf));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // n.o.t.i.f.e.e.o
    public List<q> a() {
        Boolean boolValueOf;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM work_data", 0);
        this.f312a.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.f312a, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "notification");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "with_alarm_manager");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                byte[] blob = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getBlob(columnIndexOrThrow2);
                byte[] blob2 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getBlob(columnIndexOrThrow3);
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow4));
                if (numValueOf == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                }
                arrayList.add(new q(string, blob, blob2, boolValueOf));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // n.o.t.i.f.e.e.o
    public void a(List<String> list) {
        this.f312a.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("DELETE FROM work_data WHERE id in (");
        StringUtil.appendPlaceholders(sbNewStringBuilder, list.size());
        sbNewStringBuilder.append(")");
        SupportSQLiteStatement supportSQLiteStatementCompileStatement = this.f312a.compileStatement(sbNewStringBuilder.toString());
        int i = 1;
        for (String str : list) {
            if (str == null) {
                supportSQLiteStatementCompileStatement.bindNull(i);
            } else {
                supportSQLiteStatementCompileStatement.bindString(i, str);
            }
            i++;
        }
        this.f312a.beginTransaction();
        try {
            supportSQLiteStatementCompileStatement.executeUpdateDelete();
            this.f312a.setTransactionSuccessful();
        } finally {
            this.f312a.endTransaction();
        }
    }
}
