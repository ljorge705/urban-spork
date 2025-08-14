package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.onfido.android.sdk.capture.core.OnfidoLauncher;
import java.io.IOException;

/* compiled from: com.google.mlkit:vision-common@@17.2.0 */
/* loaded from: classes3.dex */
final class zzgf implements ObjectEncoder {
    static final zzgf zza = new zzgf();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder(OnfidoLauncher.KEY_RESULT);
        zzae zzaeVar = new zzae();
        zzaeVar.zza(1);
        zzb = builder.withProperty(zzaeVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("ok");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.zza(2);
        zzc = builder2.withProperty(zzaeVar2.zzb()).build();
    }

    private zzgf() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}
