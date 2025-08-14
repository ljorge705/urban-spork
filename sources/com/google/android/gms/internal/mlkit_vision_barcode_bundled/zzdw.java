package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public class zzdw<MessageType extends zzec<MessageType, BuilderType>, BuilderType extends zzdw<MessageType, BuilderType>> extends zzcj<MessageType, BuilderType> {
    protected zzec zza;
    protected boolean zzb = false;
    private final zzec zzc;

    protected zzdw(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzec) messagetype.zzg(4, null, null);
    }

    private static final void zza(zzec zzecVar, zzec zzecVar2) {
        zzfu.zza().zzb(zzecVar.getClass()).zzg(zzecVar, zzecVar2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final /* synthetic */ zzfl zzX() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final boolean zzY() {
        return zzec.zzT(this.zza, false);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj
    protected final /* synthetic */ zzcj zzf(zzck zzckVar) {
        zzi((zzec) zzckVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj
    /* renamed from: zzh, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final zzdw zze() {
        zzdw zzdwVar = (zzdw) this.zzc.zzg(5, null, null);
        zzdwVar.zzi(zzm());
        return zzdwVar;
    }

    public final zzdw zzi(zzec zzecVar) {
        if (this.zzb) {
            zzo();
            this.zzb = false;
        }
        zza(this.zza, zzecVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfk
    /* renamed from: zzj, reason: merged with bridge method [inline-methods] */
    public final MessageType zzl() {
        MessageType messagetype = (MessageType) zzm();
        if (messagetype.zzY()) {
            return messagetype;
        }
        throw new zzgo(messagetype);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfk
    /* renamed from: zzk, reason: merged with bridge method [inline-methods] */
    public MessageType zzm() {
        if (this.zzb) {
            return (MessageType) this.zza;
        }
        zzec zzecVar = this.zza;
        zzfu.zza().zzb(zzecVar.getClass()).zzf(zzecVar);
        this.zzb = true;
        return (MessageType) this.zza;
    }

    protected void zzo() {
        zzec zzecVar = (zzec) this.zza.zzg(4, null, null);
        zza(zzecVar, this.zza);
        this.zza = zzecVar;
    }
}
