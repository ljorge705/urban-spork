package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzha implements ObjectEncoder {
    static final zzha zza = new zzha();
    private static final FieldDescriptor zzb;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
    }

    private zzha() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
