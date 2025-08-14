package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzge implements ObjectEncoder {
    static final zzge zza = new zzge();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("imageFormat");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("originalImageSize");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        zzc = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("compressedImageSize");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        zzd = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("isOdmlImage");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        zze = builder4.withProperty(zzcvVar4.zzb()).build();
    }

    private zzge() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzjo zzjoVar = (zzjo) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzjoVar.zza());
        objectEncoderContext2.add(zzc, zzjoVar.zzb());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, (Object) null);
    }
}
