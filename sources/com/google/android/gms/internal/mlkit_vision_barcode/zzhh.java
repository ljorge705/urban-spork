package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import io.sentry.rrweb.RRWebOptionsEvent;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzhh implements ObjectEncoder {
    static final zzhh zza = new zzhh();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzdf zzdfVar = new zzdf();
        zzdfVar.zza(1);
        zzb = builder.withProperty(zzdfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RRWebOptionsEvent.EVENT_TAG);
        zzdf zzdfVar2 = new zzdf();
        zzdfVar2.zza(2);
        zzc = builder2.withProperty(zzdfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("detectedBarcodeFormats");
        zzdf zzdfVar3 = new zzdf();
        zzdfVar3.zza(3);
        zzd = builder3.withProperty(zzdfVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("detectedBarcodeValueTypes");
        zzdf zzdfVar4 = new zzdf();
        zzdfVar4.zza(4);
        zze = builder4.withProperty(zzdfVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("imageInfo");
        zzdf zzdfVar5 = new zzdf();
        zzdfVar5.zza(5);
        zzf = builder5.withProperty(zzdfVar5.zzb()).build();
    }

    private zzhh() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzkz zzkzVar = (zzkz) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzkzVar.zzd());
        objectEncoderContext2.add(zzc, zzkzVar.zze());
        objectEncoderContext2.add(zzd, zzkzVar.zza());
        objectEncoderContext2.add(zze, zzkzVar.zzb());
        objectEncoderContext2.add(zzf, zzkzVar.zzc());
    }
}
