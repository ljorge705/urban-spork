package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzim implements ObjectEncoder {
    static final zzim zza = new zzim();
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
        zzdf zzdfVar = new zzdf();
        zzdfVar.zza(1);
        zzb = builder.withProperty(zzdfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.APP_VERSION);
        zzdf zzdfVar2 = new zzdf();
        zzdfVar2.zza(2);
        zzc = builder2.withProperty(zzdfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzdf zzdfVar3 = new zzdf();
        zzdfVar3.zza(3);
        zzd = builder3.withProperty(zzdfVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzdf zzdfVar4 = new zzdf();
        zzdfVar4.zza(4);
        zze = builder4.withProperty(zzdfVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzdf zzdfVar5 = new zzdf();
        zzdfVar5.zza(5);
        zzf = builder5.withProperty(zzdfVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzdf zzdfVar6 = new zzdf();
        zzdfVar6.zza(6);
        zzg = builder6.withProperty(zzdfVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(DynamicLink.Builder.KEY_API_KEY);
        zzdf zzdfVar7 = new zzdf();
        zzdfVar7.zza(7);
        zzh = builder7.withProperty(zzdfVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzdf zzdfVar8 = new zzdf();
        zzdfVar8.zza(8);
        zzi = builder8.withProperty(zzdfVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzdf zzdfVar9 = new zzdf();
        zzdfVar9.zza(9);
        zzj = builder9.withProperty(zzdfVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzdf zzdfVar10 = new zzdf();
        zzdfVar10.zza(10);
        zzk = builder10.withProperty(zzdfVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzdf zzdfVar11 = new zzdf();
        zzdfVar11.zza(11);
        zzl = builder11.withProperty(zzdfVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzdf zzdfVar12 = new zzdf();
        zzdfVar12.zza(12);
        zzm = builder12.withProperty(zzdfVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzdf zzdfVar13 = new zzdf();
        zzdfVar13.zza(13);
        zzn = builder13.withProperty(zzdfVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzdf zzdfVar14 = new zzdf();
        zzdfVar14.zza(14);
        zzo = builder14.withProperty(zzdfVar14.zzb()).build();
    }

    private zzim() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzme zzmeVar = (zzme) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzmeVar.zzg());
        objectEncoderContext2.add(zzc, zzmeVar.zzh());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, zzmeVar.zzj());
        objectEncoderContext2.add(zzf, zzmeVar.zzk());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, zzmeVar.zza());
        objectEncoderContext2.add(zzj, zzmeVar.zzi());
        objectEncoderContext2.add(zzk, zzmeVar.zzb());
        objectEncoderContext2.add(zzl, zzmeVar.zzd());
        objectEncoderContext2.add(zzm, zzmeVar.zzc());
        objectEncoderContext2.add(zzn, zzmeVar.zze());
        objectEncoderContext2.add(zzo, zzmeVar.zzf());
    }
}
