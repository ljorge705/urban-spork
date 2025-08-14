package com.google.android.gms.internal.mlkit_vision_barcode;

import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.microsoft.codepush.react.CodePushConstants;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.0.0 */
/* loaded from: classes3.dex */
final class zzgz implements ObjectEncoder {
    static final zzgz zza = new zzgz();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("name");
        zzdf zzdfVar = new zzdf();
        zzdfVar.zza(1);
        zzb = builder.withProperty(zzdfVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("version");
        zzdf zzdfVar2 = new zzdf();
        zzdfVar2.zza(2);
        zzc = builder2.withProperty(zzdfVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("source");
        zzdf zzdfVar3 = new zzdf();
        zzdfVar3.zza(3);
        zzd = builder3.withProperty(zzdfVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(ReactNativeBlobUtilConst.DATA_ENCODE_URI);
        zzdf zzdfVar4 = new zzdf();
        zzdfVar4.zza(4);
        zze = builder4.withProperty(zzdfVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder(CodePushConstants.PENDING_UPDATE_HASH_KEY);
        zzdf zzdfVar5 = new zzdf();
        zzdfVar5.zza(5);
        zzf = builder5.withProperty(zzdfVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("modelType");
        zzdf zzdfVar6 = new zzdf();
        zzdfVar6.zza(6);
        zzg = builder6.withProperty(zzdfVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(RRWebVideoEvent.JsonKeys.SIZE);
        zzdf zzdfVar7 = new zzdf();
        zzdfVar7.zza(7);
        zzh = builder7.withProperty(zzdfVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("hasLabelMap");
        zzdf zzdfVar8 = new zzdf();
        zzdfVar8.zza(8);
        zzi = builder8.withProperty(zzdfVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("isManifestModel");
        zzdf zzdfVar9 = new zzdf();
        zzdfVar9.zza(9);
        zzj = builder9.withProperty(zzdfVar9.zzb()).build();
    }

    private zzgz() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
