package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.protocol.Device;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
final class zzfy implements ObjectEncoder {
    static final zzfy zza = new zzfy();
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

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        zzb = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("osBuild");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        zzc = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(Device.JsonKeys.BRAND);
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(3);
        zzd = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(Device.TYPE);
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(4);
        zze = builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hardware");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(5);
        zzf = builder5.withProperty(zzcvVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder(Device.JsonKeys.MANUFACTURER);
        zzcv zzcvVar6 = new zzcv();
        zzcvVar6.zza(6);
        zzg = builder6.withProperty(zzcvVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(Device.JsonKeys.MODEL);
        zzcv zzcvVar7 = new zzcv();
        zzcvVar7.zza(7);
        zzh = builder7.withProperty(zzcvVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("product");
        zzcv zzcvVar8 = new zzcv();
        zzcvVar8.zza(8);
        zzi = builder8.withProperty(zzcvVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("soc");
        zzcv zzcvVar9 = new zzcv();
        zzcvVar9.zza(9);
        zzj = builder9.withProperty(zzcvVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("socMetaBuildId");
        zzcv zzcvVar10 = new zzcv();
        zzcvVar10.zza(10);
        zzk = builder10.withProperty(zzcvVar10.zzb()).build();
    }

    private zzfy() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
