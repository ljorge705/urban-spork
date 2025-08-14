package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzhu implements ObjectEncoder {
    static final zzhu zza = new zzhu();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(3);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(4);
        zzc = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(5);
        zzd = builder3.withProperty(zzcvVar3.zzb()).build();
    }

    private zzhu() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, ((zzln) obj).zza());
        objectEncoderContext2.add(zzc, (Object) null);
        objectEncoderContext2.add(zzd, (Object) null);
    }
}
