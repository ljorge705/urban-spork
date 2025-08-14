package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzgj implements ObjectEncoder {
    static final zzgj zza = new zzgj();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("maxMs");
        zzdf zzdfVar = new zzdf();
        zzdfVar.zza(1);
        zzb = builder.withProperty(zzdfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("minMs");
        zzdf zzdfVar2 = new zzdf();
        zzdfVar2.zza(2);
        zzc = builder2.withProperty(zzdfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("avgMs");
        zzdf zzdfVar3 = new zzdf();
        zzdfVar3.zza(3);
        zzd = builder3.withProperty(zzdfVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("firstQuartileMs");
        zzdf zzdfVar4 = new zzdf();
        zzdfVar4.zza(4);
        zze = builder4.withProperty(zzdfVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("medianMs");
        zzdf zzdfVar5 = new zzdf();
        zzdfVar5.zza(5);
        zzf = builder5.withProperty(zzdfVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("thirdQuartileMs");
        zzdf zzdfVar6 = new zzdf();
        zzdfVar6.zza(6);
        zzg = builder6.withProperty(zzdfVar6.zzb()).build();
    }

    private zzgj() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzjs zzjsVar = (zzjs) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzjsVar.zzc());
        objectEncoderContext2.add(zzc, zzjsVar.zze());
        objectEncoderContext2.add(zzd, zzjsVar.zza());
        objectEncoderContext2.add(zze, zzjsVar.zzb());
        objectEncoderContext2.add(zzf, zzjsVar.zzd());
        objectEncoderContext2.add(zzg, zzjsVar.zzf());
    }
}
