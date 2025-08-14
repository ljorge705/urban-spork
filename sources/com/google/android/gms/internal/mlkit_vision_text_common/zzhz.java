package com.google.android.gms.internal.mlkit_vision_text_common;

import com.clevertap.android.sdk.Constants;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzhz implements ObjectEncoder {
    static final zzhz zza = new zzhz();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("deviceInfo");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("nnapiInfo");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        zzc = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("gpuInfo");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        zzd = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("pipelineIdentifier");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        zze = builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("acceptedConfigurations");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(5);
        zzf = builder5.withProperty(zzcvVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder(Constants.KEY_ACTION);
        zzcv zzcvVar6 = new zzcv();
        zzcvVar6.zza(6);
        zzg = builder6.withProperty(zzcvVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("status");
        zzcv zzcvVar7 = new zzcv();
        zzcvVar7.zza(7);
        zzh = builder7.withProperty(zzcvVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customErrors");
        zzcv zzcvVar8 = new zzcv();
        zzcvVar8.zza(8);
        zzi = builder8.withProperty(zzcvVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("benchmarkStatus");
        zzcv zzcvVar9 = new zzcv();
        zzcvVar9.zza(9);
        zzj = builder9.withProperty(zzcvVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("validationTestResult");
        zzcv zzcvVar10 = new zzcv();
        zzcvVar10.zza(10);
        zzk = builder10.withProperty(zzcvVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("timestampUs");
        zzcv zzcvVar11 = new zzcv();
        zzcvVar11.zza(11);
        zzl = builder11.withProperty(zzcvVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("elapsedUs");
        zzcv zzcvVar12 = new zzcv();
        zzcvVar12.zza(12);
        zzm = builder12.withProperty(zzcvVar12.zzb()).build();
    }

    private zzhz() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
