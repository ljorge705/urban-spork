package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzgn implements ObjectEncoder {
    static final zzgn zza = new zzgn();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("renderer");
        zzdf zzdfVar = new zzdf();
        zzdfVar.zza(1);
        zzb = builder.withProperty(zzdfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("vendor");
        zzdf zzdfVar2 = new zzdf();
        zzdfVar2.zza(2);
        zzc = builder2.withProperty(zzdfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("version");
        zzdf zzdfVar3 = new zzdf();
        zzdfVar3.zza(3);
        zzd = builder3.withProperty(zzdfVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("maxImages");
        zzdf zzdfVar4 = new zzdf();
        zzdfVar4.zza(4);
        zze = builder4.withProperty(zzdfVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("maxSsbo");
        zzdf zzdfVar5 = new zzdf();
        zzdfVar5.zza(5);
        zzf = builder5.withProperty(zzdfVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("workGroupSizes");
        zzdf zzdfVar6 = new zzdf();
        zzdfVar6.zza(6);
        zzg = builder6.withProperty(zzdfVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("errorCode");
        zzdf zzdfVar7 = new zzdf();
        zzdfVar7.zza(7);
        zzh = builder7.withProperty(zzdfVar7.zzb()).build();
    }

    private zzgn() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
