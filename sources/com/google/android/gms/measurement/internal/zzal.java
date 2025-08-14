package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzal {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007a A[Catch: SQLiteException -> 0x00d6, TryCatch #3 {SQLiteException -> 0x00d6, blocks: (B:21:0x004a, B:23:0x006b, B:25:0x007a, B:27:0x0082, B:28:0x0085, B:29:0x00a3, B:31:0x00a6, B:33:0x00a9, B:35:0x00b1, B:36:0x00b8, B:37:0x00bb, B:39:0x00c1, B:42:0x00d2, B:43:0x00d5, B:22:0x0064), top: B:58:0x004a, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a6 A[Catch: SQLiteException -> 0x00d6, LOOP:1: B:31:0x00a6->B:36:0x00b8, LOOP_START, PHI: r13
      0x00a6: PHI (r13v1 int) = (r13v0 int), (r13v2 int) binds: [B:30:0x00a4, B:36:0x00b8] A[DONT_GENERATE, DONT_INLINE], TryCatch #3 {SQLiteException -> 0x00d6, blocks: (B:21:0x004a, B:23:0x006b, B:25:0x007a, B:27:0x0082, B:28:0x0085, B:29:0x00a3, B:31:0x00a6, B:33:0x00a9, B:35:0x00b1, B:36:0x00b8, B:37:0x00bb, B:39:0x00c1, B:42:0x00d2, B:43:0x00d5, B:22:0x0064), top: B:58:0x004a, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c1 A[Catch: SQLiteException -> 0x00d6, TryCatch #3 {SQLiteException -> 0x00d6, blocks: (B:21:0x004a, B:23:0x006b, B:25:0x007a, B:27:0x0082, B:28:0x0085, B:29:0x00a3, B:31:0x00a6, B:33:0x00a9, B:35:0x00b1, B:36:0x00b8, B:37:0x00bb, B:39:0x00c1, B:42:0x00d2, B:43:0x00d5, B:22:0x0064), top: B:58:0x004a, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r14v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void zza(com.google.android.gms.measurement.internal.zzet r15, android.database.sqlite.SQLiteDatabase r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String[] r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(com.google.android.gms.measurement.internal.zzet, android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    static void zzb(zzet zzetVar, SQLiteDatabase sQLiteDatabase) {
        if (zzetVar == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file = new File(sQLiteDatabase.getPath());
        if (!file.setReadable(false, false)) {
            zzetVar.zzk().zza("Failed to turn off database read permission");
        }
        if (!file.setWritable(false, false)) {
            zzetVar.zzk().zza("Failed to turn off database write permission");
        }
        if (!file.setReadable(true, true)) {
            zzetVar.zzk().zza("Failed to turn on database read permission for owner");
        }
        if (file.setWritable(true, true)) {
            return;
        }
        zzetVar.zzk().zza("Failed to turn on database write permission for owner");
    }
}
