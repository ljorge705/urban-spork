package com.google.android.play.core.splitcompat;

import java.io.File;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
final class zzb extends zzt {
    private final File zza;
    private final String zzb;

    zzb(File file, String str) {
        if (file == null) {
            throw new NullPointerException("Null splitFile");
        }
        this.zza = file;
        if (str == null) {
            throw new NullPointerException("Null splitId");
        }
        this.zzb = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzt) {
            zzt zztVar = (zzt) obj;
            if (this.zza.equals(zztVar.zza()) && this.zzb.equals(zztVar.zzb())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
    }

    public final String toString() {
        return "SplitFileInfo{splitFile=" + this.zza.toString() + ", splitId=" + this.zzb + "}";
    }

    @Override // com.google.android.play.core.splitcompat.zzt
    final File zza() {
        return this.zza;
    }

    @Override // com.google.android.play.core.splitcompat.zzt
    final String zzb() {
        return this.zzb;
    }
}
