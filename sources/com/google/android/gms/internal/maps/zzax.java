package com.google.android.gms.internal.maps;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
final class zzax extends zzau {
    private final zzaz zza;

    zzax(zzaz zzazVar, int i) {
        super(zzazVar.size(), i);
        this.zza = zzazVar;
    }

    @Override // com.google.android.gms.internal.maps.zzau
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
