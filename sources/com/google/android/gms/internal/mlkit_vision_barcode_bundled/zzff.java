package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.mlkit:barcode-scanning@@17.0.2 */
/* loaded from: classes3.dex */
public final class zzff extends LinkedHashMap {
    private static final zzff zza;
    private boolean zzb;

    static {
        zzff zzffVar = new zzff();
        zza = zzffVar;
        zzffVar.zzb = false;
    }

    private zzff() {
        this.zzb = true;
    }

    public static zzff zza() {
        return zza;
    }

    private static int zzf(Object obj) {
        if (obj instanceof byte[]) {
            return zzel.zzb((byte[]) obj);
        }
        if (obj instanceof zzee) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    private final void zzg() {
        if (!this.zzb) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzg();
        super.clear();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(((value instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) obj2) : value.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Iterator it = entrySet().iterator();
        int iZzf = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            iZzf += zzf(entry.getValue()) ^ zzf(entry.getKey());
        }
        return iZzf;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        zzg();
        zzel.zze(obj);
        zzel.zze(obj2);
        return super.put(obj, obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        zzg();
        for (Object obj : map.keySet()) {
            zzel.zze(obj);
            zzel.zze(map.get(obj));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzg();
        return super.remove(obj);
    }

    public final zzff zzb() {
        return isEmpty() ? new zzff() : new zzff(this);
    }

    public final void zzc() {
        this.zzb = false;
    }

    public final void zzd(zzff zzffVar) {
        zzg();
        if (zzffVar.isEmpty()) {
            return;
        }
        putAll(zzffVar);
    }

    public final boolean zze() {
        return this.zzb;
    }

    private zzff(Map map) {
        super(map);
        this.zzb = true;
    }
}
