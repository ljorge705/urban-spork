package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public abstract class zzec<MessageType extends zzec<MessageType, BuilderType>, BuilderType extends zzdw<MessageType, BuilderType>> extends zzck<MessageType, BuilderType> {
    private static final Map zza = new ConcurrentHashMap();
    protected zzgq zzc = zzgq.zzc();
    protected int zzd = -1;

    public static zzea zzH(zzfl zzflVar, Object obj, zzfl zzflVar2, zzef zzefVar, int i, zzhf zzhfVar, Class cls) {
        return new zzea(zzflVar, obj, zzflVar2, new zzdz(null, i, zzhfVar, false, false), cls);
    }

    static zzec zzI(Class cls) throws ClassNotFoundException {
        Map map = zza;
        zzec zzecVar = (zzec) map.get(cls);
        if (zzecVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzecVar = (zzec) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzecVar == null) {
            zzecVar = (zzec) ((zzec) zzgz.zze(cls)).zzg(6, null, null);
            if (zzecVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzecVar);
        }
        return zzecVar;
    }

    protected static zzec zzJ(zzec zzecVar, byte[] bArr, zzdn zzdnVar) throws zzen {
        zzec zzecVarZzK = zzK(zzecVar, bArr, 0, bArr.length, zzdnVar);
        if (zzecVarZzK == null || zzecVarZzK.zzY()) {
            return zzecVarZzK;
        }
        zzen zzenVar = new zzen(new zzgo(zzecVarZzK).getMessage());
        zzenVar.zzf(zzecVarZzK);
        throw zzenVar;
    }

    static zzec zzK(zzec zzecVar, byte[] bArr, int i, int i2, zzdn zzdnVar) throws zzen {
        zzec zzecVar2 = (zzec) zzecVar.zzg(4, null, null);
        try {
            zzgb zzgbVarZzb = zzfu.zza().zzb(zzecVar2.getClass());
            zzgbVarZzb.zzh(zzecVar2, bArr, 0, i2, new zzco(zzdnVar));
            zzgbVarZzb.zzf(zzecVar2);
            if (zzecVar2.zzb == 0) {
                return zzecVar2;
            }
            throw new RuntimeException();
        } catch (zzen e) {
            e.zzf(zzecVar2);
            throw e;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzen) {
                throw ((zzen) e2.getCause());
            }
            zzen zzenVar = new zzen(e2);
            zzenVar.zzf(zzecVar2);
            throw zzenVar;
        } catch (IndexOutOfBoundsException unused) {
            zzen zzenVarZzg = zzen.zzg();
            zzenVarZzg.zzf(zzecVar2);
            throw zzenVarZzg;
        }
    }

    protected static zzeh zzL() {
        return zzdu.zze();
    }

    protected static zzeh zzM(zzeh zzehVar) {
        int size = zzehVar.size();
        return zzehVar.zzd(size == 0 ? 10 : size + size);
    }

    protected static zzei zzN() {
        return zzed.zzf();
    }

    protected static zzek zzO() {
        return zzfv.zze();
    }

    protected static zzek zzP(zzek zzekVar) {
        int size = zzekVar.size();
        return zzekVar.zzd(size == 0 ? 10 : size + size);
    }

    static Object zzQ(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object zzR(zzfl zzflVar, String str, Object[] objArr) {
        return new zzfw(zzflVar, str, objArr);
    }

    protected static void zzS(Class cls, zzec zzecVar) {
        zza.put(cls, zzecVar);
    }

    protected static final boolean zzT(zzec zzecVar, boolean z) {
        byte bByteValue = ((Byte) zzecVar.zzg(1, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzj = zzfu.zza().zzb(zzecVar.getClass()).zzj(zzecVar);
        if (z) {
            zzecVar.zzg(2, true != zZzj ? null : zzecVar, null);
        }
        return zZzj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzfu.zza().zzb(getClass()).zzi(this, (zzec) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int iZzb = zzfu.zza().zzb(getClass()).zzb(this);
        this.zzb = iZzb;
        return iZzb;
    }

    public final String toString() {
        return zzfn.zza(this, super.toString());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck
    final int zzB() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck
    final void zzD(int i) {
        this.zzd = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final int zzE() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int iZza = zzfu.zza().zzb(getClass()).zza(this);
        this.zzd = iZza;
        return iZza;
    }

    protected final zzdw zzF() {
        return (zzdw) zzg(5, null, null);
    }

    public final zzdw zzG() {
        zzdw zzdwVar = (zzdw) zzg(5, null, null);
        zzdwVar.zzi(this);
        return zzdwVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final /* synthetic */ zzfk zzU() {
        return (zzdw) zzg(5, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final /* synthetic */ zzfk zzV() {
        zzdw zzdwVar = (zzdw) zzg(5, null, null);
        zzdwVar.zzi(this);
        return zzdwVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl
    public final void zzW(zzdi zzdiVar) throws IOException {
        zzfu.zza().zzb(getClass()).zzm(this, zzdj.zza(zzdiVar));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final /* synthetic */ zzfl zzX() {
        return (zzec) zzg(6, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final boolean zzY() {
        return zzT(this, Boolean.TRUE.booleanValue());
    }

    protected abstract Object zzg(int i, Object obj, Object obj2);
}
