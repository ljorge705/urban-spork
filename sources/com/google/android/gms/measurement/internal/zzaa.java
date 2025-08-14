package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
final class zzaa extends zzku {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzaa(zzlh zzlhVar) {
        super(zzlhVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        return zzuVar.zze.get(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:400:0x09fb, code lost:
    
        r7 = r63.zzt.zzaA().zzk();
        r9 = com.google.android.gms.measurement.internal.zzet.zzn(r63.zza);
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x0a0f, code lost:
    
        if (r8.zzj() == false) goto L403;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x0a11, code lost:
    
        r8 = java.lang.Integer.valueOf(r8.zza());
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x0a1a, code lost:
    
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x0a1b, code lost:
    
        r7.zzc("Invalid property filter ID. appId, id", r9, java.lang.String.valueOf(r8));
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02bd A[PHI: r0 r5
      0x02bd: PHI (r0v61 java.util.Map) = (r0v46 java.util.Map), (r0v63 java.util.Map), (r0v40 java.util.Map) binds: [B:121:0x02ea, B:110:0x02c5, B:107:0x02bb] A[DONT_GENERATE, DONT_INLINE]
      0x02bd: PHI (r5v17 android.database.Cursor) = (r5v10 android.database.Cursor), (r5v18 android.database.Cursor), (r5v18 android.database.Cursor) binds: [B:121:0x02ea, B:110:0x02c5, B:107:0x02bb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x076b  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0775  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x078f  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0822  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x08df A[PHI: r0 r9
      0x08df: PHI (r0v139 java.util.Map) = (r0v141 java.util.Map), (r0v148 java.util.Map) binds: [B:366:0x0906, B:352:0x08dd] A[DONT_GENERATE, DONT_INLINE]
      0x08df: PHI (r9v29 android.database.Cursor) = (r9v30 android.database.Cursor), (r9v34 android.database.Cursor) binds: [B:366:0x0906, B:352:0x08dd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0924  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0a4d  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0ae5  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0156 A[PHI: r0 r5
      0x0156: PHI (r0v177 java.util.Map) = (r0v176 java.util.Map), (r0v181 java.util.Map) binds: [B:53:0x0178, B:42:0x0154] A[DONT_GENERATE, DONT_INLINE]
      0x0156: PHI (r5v61 android.database.Cursor) = (r5v60 android.database.Cursor), (r5v62 android.database.Cursor) binds: [B:53:0x0178, B:42:0x0154] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b9 A[Catch: SQLiteException -> 0x0224, all -> 0x0ae1, TRY_LEAVE, TryCatch #0 {all -> 0x0ae1, blocks: (B:61:0x01b3, B:63:0x01b9, B:67:0x01c7, B:68:0x01cc, B:69:0x01d6, B:70:0x01e6, B:75:0x020e, B:72:0x01f3, B:74:0x0207, B:89:0x0230), top: B:430:0x0197 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01c7 A[Catch: SQLiteException -> 0x0224, all -> 0x0ae1, TRY_ENTER, TryCatch #0 {all -> 0x0ae1, blocks: (B:61:0x01b3, B:63:0x01b9, B:67:0x01c7, B:68:0x01cc, B:69:0x01d6, B:70:0x01e6, B:75:0x020e, B:72:0x01f3, B:74:0x0207, B:89:0x0230), top: B:430:0x0197 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0259  */
    /* JADX WARN: Type inference failed for: r0v190, types: [android.content.ContentValues] */
    /* JADX WARN: Type inference failed for: r4v32, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v6, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v63, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v64 */
    /* JADX WARN: Type inference failed for: r5v65, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v9, types: [android.database.Cursor] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.List zza(java.lang.String r64, java.util.List r65, java.util.List r66, java.lang.Long r67, java.lang.Long r68) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 2793
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaa.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    @Override // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }
}
