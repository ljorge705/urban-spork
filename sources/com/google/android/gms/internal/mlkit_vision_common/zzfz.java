package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
final class zzfz implements ObjectEncoder {
    static final zzfz zza = new zzfz();
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
        zzae zzaeVar = new zzae();
        zzaeVar.zza(1);
        zzb = builder.withProperty(zzaeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzae zzaeVar2 = new zzae();
        zzaeVar2.zza(2);
        zzc = builder2.withProperty(zzaeVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.zza(3);
        zzd = builder3.withProperty(zzaeVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.zza(4);
        zze = builder4.withProperty(zzaeVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.zza(5);
        zzf = builder5.withProperty(zzaeVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.zza(6);
        zzg = builder6.withProperty(zzaeVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(DynamicLink.Builder.KEY_API_KEY);
        zzae zzaeVar7 = new zzae();
        zzaeVar7.zza(7);
        zzh = builder7.withProperty(zzaeVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzae zzaeVar8 = new zzae();
        zzaeVar8.zza(8);
        zzi = builder8.withProperty(zzaeVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzae zzaeVar9 = new zzae();
        zzaeVar9.zza(9);
        zzj = builder9.withProperty(zzaeVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzae zzaeVar10 = new zzae();
        zzaeVar10.zza(10);
        zzk = builder10.withProperty(zzaeVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzae zzaeVar11 = new zzae();
        zzaeVar11.zza(11);
        zzl = builder11.withProperty(zzaeVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzae zzaeVar12 = new zzae();
        zzaeVar12.zza(12);
        zzm = builder12.withProperty(zzaeVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzae zzaeVar13 = new zzae();
        zzaeVar13.zza(13);
        zzn = builder13.withProperty(zzaeVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzae zzaeVar14 = new zzae();
        zzaeVar14.zza(14);
        zzo = builder14.withProperty(zzaeVar14.zzb()).build();
    }

    private zzfz() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzjq zzjqVar = (zzjq) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzjqVar.zzg());
        objectEncoderContext2.add(zzc, zzjqVar.zzh());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, zzjqVar.zzj());
        objectEncoderContext2.add(zzf, zzjqVar.zzk());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, zzjqVar.zza());
        objectEncoderContext2.add(zzj, zzjqVar.zzi());
        objectEncoderContext2.add(zzk, zzjqVar.zzb());
        objectEncoderContext2.add(zzl, zzjqVar.zzd());
        objectEncoderContext2.add(zzm, zzjqVar.zzc());
        objectEncoderContext2.add(zzn, zzjqVar.zze());
        objectEncoderContext2.add(zzo, zzjqVar.zzf());
    }
}
