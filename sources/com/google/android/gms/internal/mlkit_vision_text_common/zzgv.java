package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzgv implements ObjectEncoder {
    static final zzgv zza = new zzgv();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfos");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorInfo");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        zzc = builder2.withProperty(zzcvVar2.zzb()).build();
    }

    private zzgv() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
