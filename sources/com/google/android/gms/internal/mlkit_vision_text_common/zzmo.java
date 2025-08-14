package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzmo extends zzmq {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    /* synthetic */ zzmo(String str, boolean z, int i, zzmn zzmnVar) {
        this.zza = str;
        this.zzb = z;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzmq) {
            zzmq zzmqVar = (zzmq) obj;
            if (this.zza.equals(zzmqVar.zzb()) && this.zzb == zzmqVar.zzc() && this.zzc == zzmqVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        String str = this.zza;
        boolean z = this.zzb;
        int i = this.zzc;
        StringBuilder sb = new StringBuilder(str.length() + 84);
        sb.append("MLKitLoggingOptions{libraryName=");
        sb.append(str);
        sb.append(", enableFirelog=");
        sb.append(z);
        sb.append(", firelogEventType=");
        sb.append(i);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmq
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmq
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmq
    public final boolean zzc() {
        return this.zzb;
    }
}
