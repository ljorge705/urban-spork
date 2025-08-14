package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
public abstract class zzce implements Map, Serializable {

    @CheckForNull
    private transient zzcf zza;

    @CheckForNull
    private transient zzcf zzb;

    @CheckForNull
    private transient zzbx zzc;

    zzce() {
    }

    public static zzce zzc(Object obj, Object obj2) {
        zzbj.zzb("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return zzcw.zzg(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, null);
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @Override // java.util.Map
    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzcy.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzcf zzcfVar = this.zzb;
        if (zzcfVar != null) {
            return zzcfVar;
        }
        zzcf zzcfVarZze = zze();
        this.zzb = zzcfVarZze;
        return zzcfVarZze;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        zzbj.zza(size, RRWebVideoEvent.JsonKeys.SIZE);
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
        sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb.toString();
    }

    abstract zzbx zza();

    @Override // java.util.Map
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzbx values() {
        zzbx zzbxVar = this.zzc;
        if (zzbxVar != null) {
            return zzbxVar;
        }
        zzbx zzbxVarZza = zza();
        this.zzc = zzbxVarZza;
        return zzbxVarZza;
    }

    abstract zzcf zzd();

    abstract zzcf zze();

    @Override // java.util.Map
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final zzcf entrySet() {
        zzcf zzcfVar = this.zza;
        if (zzcfVar != null) {
            return zzcfVar;
        }
        zzcf zzcfVarZzd = zzd();
        this.zza = zzcfVarZzd;
        return zzcfVarZzd;
    }
}
