package com.google.android.gms.internal.mlkit_vision_face;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes3.dex */
final class zzda implements ValueEncoderContext {
    private boolean zza = false;
    private boolean zzb = false;
    private FieldDescriptor zzc;
    private final zzcw zzd;

    zzda(zzcw zzcwVar) {
        this.zzd = zzcwVar;
    }

    private final void zzb() {
        if (this.zza) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.zza = true;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(double d) throws IOException {
        zzb();
        this.zzd.zza(this.zzc, d, this.zzb);
        return this;
    }

    final void zza(FieldDescriptor fieldDescriptor, boolean z) {
        this.zza = false;
        this.zzc = fieldDescriptor;
        this.zzb = z;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(float f) throws IOException {
        zzb();
        this.zzd.zzb(this.zzc, f, this.zzb);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(int i) throws IOException {
        zzb();
        this.zzd.zzd(this.zzc, i, this.zzb);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(long j) throws IOException {
        zzb();
        this.zzd.zze(this.zzc, j, this.zzb);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(String str) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        zzb();
        this.zzd.zzc(this.zzc, str, this.zzb);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(boolean z) throws IOException {
        zzb();
        this.zzd.zzd(this.zzc, z ? 1 : 0, this.zzb);
        return this;
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final ValueEncoderContext add(byte[] bArr) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        zzb();
        this.zzd.zzc(this.zzc, bArr, this.zzb);
        return this;
    }
}
