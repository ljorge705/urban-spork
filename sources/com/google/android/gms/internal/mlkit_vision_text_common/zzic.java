package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzic implements ObjectEncoder {
    static final zzic zza = new zzic();
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
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_ID);
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        zzc = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        zzd = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        zze = builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(5);
        zzf = builder5.withProperty(zzcvVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzcv zzcvVar6 = new zzcv();
        zzcvVar6.zza(6);
        zzg = builder6.withProperty(zzcvVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(DynamicLink.Builder.KEY_API_KEY);
        zzcv zzcvVar7 = new zzcv();
        zzcvVar7.zza(7);
        zzh = builder7.withProperty(zzcvVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzcv zzcvVar8 = new zzcv();
        zzcvVar8.zza(8);
        zzi = builder8.withProperty(zzcvVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzcv zzcvVar9 = new zzcv();
        zzcvVar9.zza(9);
        zzj = builder9.withProperty(zzcvVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzcv zzcvVar10 = new zzcv();
        zzcvVar10.zza(10);
        zzk = builder10.withProperty(zzcvVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzcv zzcvVar11 = new zzcv();
        zzcvVar11.zza(11);
        zzl = builder11.withProperty(zzcvVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzcv zzcvVar12 = new zzcv();
        zzcvVar12.zza(12);
        zzm = builder12.withProperty(zzcvVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzcv zzcvVar13 = new zzcv();
        zzcvVar13.zza(13);
        zzn = builder13.withProperty(zzcvVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzcv zzcvVar14 = new zzcv();
        zzcvVar14.zza(14);
        zzo = builder14.withProperty(zzcvVar14.zzb()).build();
    }

    private zzic() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzlt zzltVar = (zzlt) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzltVar.zzg());
        objectEncoderContext2.add(zzc, zzltVar.zzh());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, zzltVar.zzj());
        objectEncoderContext2.add(zzf, zzltVar.zzk());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, zzltVar.zza());
        objectEncoderContext2.add(zzj, zzltVar.zzi());
        objectEncoderContext2.add(zzk, zzltVar.zzb());
        objectEncoderContext2.add(zzl, zzltVar.zzd());
        objectEncoderContext2.add(zzm, zzltVar.zzc());
        objectEncoderContext2.add(zzn, zzltVar.zze());
        objectEncoderContext2.add(zzo, zzltVar.zzf());
    }
}
