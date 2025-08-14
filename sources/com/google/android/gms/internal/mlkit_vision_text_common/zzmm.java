package com.google.android.gms.internal.mlkit_vision_text_common;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzmm extends zzmp {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    zzmm() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmp
    public final zzmp zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmp
    public final zzmp zzb(int i) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzmp zzc(String str) {
        if (str == null) {
            throw new NullPointerException("Null libraryName");
        }
        this.zza = str;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzmp
    public final zzmq zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zzmo(str, this.zzb, this.zzc, null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" libraryName");
        }
        if ((this.zzd & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
