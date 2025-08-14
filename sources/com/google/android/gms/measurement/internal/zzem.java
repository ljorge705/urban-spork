package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzem extends zzf {
    private final zzel zza;
    private boolean zzb;

    zzem(zzgd zzgdVar) {
        super(zzgdVar);
        Context contextZzaw = this.zzt.zzaw();
        this.zzt.zzf();
        this.zza = new zzel(this, contextZzaw, "google_app_measurement_local.db");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0113 A[PHI: r8
      0x0113: PHI (r8v3 android.database.sqlite.SQLiteDatabase) = (r8v2 android.database.sqlite.SQLiteDatabase), (r8v4 android.database.sqlite.SQLiteDatabase) binds: [B:55:0x00e4, B:70:0x0111] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0126  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v10, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean zzq(int r18, byte[] r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzem.zzq(int, byte[]):boolean");
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    final SQLiteDatabase zzh() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:147:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0238 A[PHI: r9 r15
      0x0238: PHI (r9v3 int) = (r9v1 int), (r9v1 int), (r9v4 int) binds: [B:148:0x0226, B:163:0x0253, B:156:0x0236] A[DONT_GENERATE, DONT_INLINE]
      0x0238: PHI (r15v7 android.database.sqlite.SQLiteDatabase) = 
      (r15v5 android.database.sqlite.SQLiteDatabase)
      (r15v6 android.database.sqlite.SQLiteDatabase)
      (r15v8 android.database.sqlite.SQLiteDatabase)
     binds: [B:148:0x0226, B:163:0x0253, B:156:0x0236] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0209 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x01e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0256 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0256 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0256 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zzi(int r23) {
        /*
            Method dump skipped, instructions count: 633
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzem.zzi(int):java.util.List");
    }

    public final void zzj() {
        int iDelete;
        zzg();
        try {
            SQLiteDatabase sQLiteDatabaseZzh = zzh();
            if (sQLiteDatabaseZzh == null || (iDelete = sQLiteDatabaseZzh.delete("messages", null, null)) <= 0) {
                return;
            }
            this.zzt.zzaA().zzj().zzb("Reset local analytics data. records", Integer.valueOf(iDelete));
        } catch (SQLiteException e) {
            this.zzt.zzaA().zzd().zzb("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzk() {
        return zzq(3, new byte[0]);
    }

    final boolean zzl() {
        Context contextZzaw = this.zzt.zzaw();
        this.zzt.zzf();
        return contextZzaw.getDatabasePath("google_app_measurement_local.db").exists();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0069 A[PHI: r4
      0x0069: PHI (r4v3 int) = (r4v1 int), (r4v1 int), (r4v4 int) binds: [B:29:0x0060, B:35:0x007c, B:32:0x0067] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzm() {
        /*
            r11 = this;
            java.lang.String r0 = "Error deleting app launch break from local database"
            r11.zzg()
            boolean r1 = r11.zzb
            r2 = 0
            if (r1 == 0) goto Lb
            return r2
        Lb:
            boolean r1 = r11.zzl()
            if (r1 == 0) goto L97
            r1 = 5
            r4 = r1
            r3 = r2
        L14:
            if (r3 >= r1) goto L88
            r5 = 0
            r6 = 1
            android.database.sqlite.SQLiteDatabase r5 = r11.zzh()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            if (r5 != 0) goto L21
            r11.zzb = r6     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            return r2
        L21:
            r5.beginTransaction()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            java.lang.String r7 = "messages"
            java.lang.String r8 = "type == ?"
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            r10 = 3
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            r9[r2] = r10     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            r5.delete(r7, r8, r9)     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            r5.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            r5.endTransaction()     // Catch: java.lang.Throwable -> L3e android.database.sqlite.SQLiteException -> L40 android.database.sqlite.SQLiteFullException -> L42 android.database.sqlite.SQLiteDatabaseLockedException -> L61
            r5.close()
            return r6
        L3e:
            r0 = move-exception
            goto L82
        L40:
            r7 = move-exception
            goto L44
        L42:
            r7 = move-exception
            goto L6d
        L44:
            if (r5 == 0) goto L4f
            boolean r8 = r5.inTransaction()     // Catch: java.lang.Throwable -> L3e
            if (r8 == 0) goto L4f
            r5.endTransaction()     // Catch: java.lang.Throwable -> L3e
        L4f:
            com.google.android.gms.measurement.internal.zzgd r8 = r11.zzt     // Catch: java.lang.Throwable -> L3e
            com.google.android.gms.measurement.internal.zzet r8 = r8.zzaA()     // Catch: java.lang.Throwable -> L3e
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzd()     // Catch: java.lang.Throwable -> L3e
            r8.zzb(r0, r7)     // Catch: java.lang.Throwable -> L3e
            r11.zzb = r6     // Catch: java.lang.Throwable -> L3e
            if (r5 == 0) goto L7f
            goto L69
        L61:
            long r6 = (long) r4     // Catch: java.lang.Throwable -> L3e
            android.os.SystemClock.sleep(r6)     // Catch: java.lang.Throwable -> L3e
            int r4 = r4 + 20
            if (r5 == 0) goto L7f
        L69:
            r5.close()
            goto L7f
        L6d:
            com.google.android.gms.measurement.internal.zzgd r8 = r11.zzt     // Catch: java.lang.Throwable -> L3e
            com.google.android.gms.measurement.internal.zzet r8 = r8.zzaA()     // Catch: java.lang.Throwable -> L3e
            com.google.android.gms.measurement.internal.zzer r8 = r8.zzd()     // Catch: java.lang.Throwable -> L3e
            r8.zzb(r0, r7)     // Catch: java.lang.Throwable -> L3e
            r11.zzb = r6     // Catch: java.lang.Throwable -> L3e
            if (r5 == 0) goto L7f
            goto L69
        L7f:
            int r3 = r3 + 1
            goto L14
        L82:
            if (r5 == 0) goto L87
            r5.close()
        L87:
            throw r0
        L88:
            com.google.android.gms.measurement.internal.zzgd r0 = r11.zzt
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzaA()
            com.google.android.gms.measurement.internal.zzer r0 = r0.zzk()
            java.lang.String r1 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r1)
        L97:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzem.zzm():boolean");
    }

    public final boolean zzn(zzac zzacVar) {
        byte[] bArrZzap = this.zzt.zzv().zzap(zzacVar);
        if (bArrZzap.length <= 131072) {
            return zzq(2, bArrZzap);
        }
        this.zzt.zzaA().zzh().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzo(zzau zzauVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzav.zza(zzauVar, parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzq(0, bArrMarshall);
        }
        this.zzt.zzaA().zzh().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zzp(zzlk zzlkVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzll.zza(zzlkVar, parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length <= 131072) {
            return zzq(1, bArrMarshall);
        }
        this.zzt.zzaA().zzh().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
