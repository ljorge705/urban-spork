package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzat implements Iterable, zzap {
    private final String zza;

    public zzat(String str) {
        if (str == null) {
            throw new IllegalArgumentException("StringValue cannot be null.");
        }
        this.zza = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzat) {
            return this.zza.equals(((zzat) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzas(this);
    }

    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x05ad  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x05f7  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0632  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0167  */
    @Override // com.google.android.gms.internal.measurement.zzap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.google.android.gms.internal.measurement.zzap zzbU(java.lang.String r21, com.google.android.gms.internal.measurement.zzg r22, java.util.List r23) {
        /*
            Method dump skipped, instructions count: 1768
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzat.zzbU(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzat(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return new zzar(this);
    }
}
