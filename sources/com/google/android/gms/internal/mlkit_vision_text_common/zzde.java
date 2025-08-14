package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public final class zzde {
    private final Map zza;
    private final Map zzb;
    private final ObjectEncoder zzc;

    zzde(Map map, Map map2, ObjectEncoder objectEncoder) {
        this.zza = map;
        this.zzb = map2;
        this.zzc = objectEncoder;
    }

    public final byte[] zza(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new zzdb(byteArrayOutputStream, this.zza, this.zzb, this.zzc).zzf(obj);
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }
}
