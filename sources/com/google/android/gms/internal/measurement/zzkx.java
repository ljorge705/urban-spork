package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzkx;
import com.google.android.gms.internal.measurement.zzlb;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* loaded from: classes3.dex */
public class zzkx<MessageType extends zzlb<MessageType, BuilderType>, BuilderType extends zzkx<MessageType, BuilderType>> extends zzjj<MessageType, BuilderType> {
    protected zzlb zza;
    private final zzlb zzb;

    protected zzkx(MessageType messagetype) {
        this.zzb = messagetype;
        if (messagetype.zzbR()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = messagetype.zzbD();
    }

    private static void zza(Object obj, Object obj2) {
        zzmq.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    /* renamed from: zzaA, reason: merged with bridge method [inline-methods] */
    public final zzkx clone() {
        zzkx zzkxVar = (zzkx) this.zzb.zzl(5, null, null);
        zzkxVar.zza = zzaF();
        return zzkxVar;
    }

    public final zzkx zzaB(zzlb zzlbVar) {
        if (!this.zzb.equals(zzlbVar)) {
            if (!this.zza.zzbR()) {
                zzaI();
            }
            zza(this.zza, zzlbVar);
        }
        return this;
    }

    public final zzkx zzaC(byte[] bArr, int i, int i2, zzkn zzknVar) throws zzll {
        if (!this.zza.zzbR()) {
            zzaI();
        }
        try {
            zzmq.zza().zzb(this.zza.getClass()).zzh(this.zza, bArr, 0, i2, new zzjn(zzknVar));
            return this;
        } catch (zzll e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzll.zzf();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r4 != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final MessageType zzaD() {
        /*
            r6 = this;
            com.google.android.gms.internal.measurement.zzlb r0 = r6.zzaF()
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            boolean r1 = r1.booleanValue()
            r2 = 1
            r3 = 0
            java.lang.Object r4 = r0.zzl(r2, r3, r3)
            java.lang.Byte r4 = (java.lang.Byte) r4
            byte r4 = r4.byteValue()
            if (r4 != r2) goto L19
            goto L38
        L19:
            if (r4 == 0) goto L39
            com.google.android.gms.internal.measurement.zzmq r4 = com.google.android.gms.internal.measurement.zzmq.zza()
            java.lang.Class r5 = r0.getClass()
            com.google.android.gms.internal.measurement.zzmt r4 = r4.zzb(r5)
            boolean r4 = r4.zzk(r0)
            if (r1 == 0) goto L36
            if (r2 == r4) goto L31
            r1 = r3
            goto L32
        L31:
            r1 = r0
        L32:
            r2 = 2
            r0.zzl(r2, r1, r3)
        L36:
            if (r4 == 0) goto L39
        L38:
            return r0
        L39:
            com.google.android.gms.internal.measurement.zznj r1 = new com.google.android.gms.internal.measurement.zznj
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkx.zzaD():com.google.android.gms.internal.measurement.zzlb");
    }

    @Override // com.google.android.gms.internal.measurement.zzmh
    /* renamed from: zzaE, reason: merged with bridge method [inline-methods] */
    public MessageType zzaF() {
        if (!this.zza.zzbR()) {
            return (MessageType) this.zza;
        }
        this.zza.zzbM();
        return (MessageType) this.zza;
    }

    protected final void zzaH() {
        if (this.zza.zzbR()) {
            return;
        }
        zzaI();
    }

    protected void zzaI() {
        zzlb zzlbVarZzbD = this.zzb.zzbD();
        zza(zzlbVarZzbD, this.zza);
        this.zza = zzlbVarZzbD;
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final /* bridge */ /* synthetic */ zzjj zzaw(byte[] bArr, int i, int i2) throws zzll {
        zzaC(bArr, 0, i2, zzkn.zza);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzjj
    public final /* bridge */ /* synthetic */ zzjj zzax(byte[] bArr, int i, int i2, zzkn zzknVar) throws zzll {
        zzaC(bArr, 0, i2, zzknVar);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final /* bridge */ /* synthetic */ zzmi zzbV() {
        throw null;
    }
}
