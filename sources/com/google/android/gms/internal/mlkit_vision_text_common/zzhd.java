package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzhd implements ObjectEncoder {
    static final zzhd zza = new zzhd();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("detectorOptions");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorCodes");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        zzc = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("totalInitializationMs");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        zzd = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("loggingInitializationMs");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        zze = builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("otherErrors");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(5);
        zzf = builder5.withProperty(zzcvVar5.zzb()).build();
    }

    private zzhd() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
