package com.google.mlkit.vision.text.internal;

import com.google.android.gms.internal.mlkit_vision_text_common.zzjz;
import com.google.android.gms.internal.mlkit_vision_text_common.zzna;
import com.google.mlkit.vision.common.InputImage;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzn {
    public final /* synthetic */ TextRecognizerTaskWithResource zza;
    public final /* synthetic */ long zzb;
    public final /* synthetic */ zzjz zzc;
    public final /* synthetic */ InputImage zzd;

    public /* synthetic */ zzn(TextRecognizerTaskWithResource textRecognizerTaskWithResource, long j, zzjz zzjzVar, InputImage inputImage) {
        this.zza = textRecognizerTaskWithResource;
        this.zzb = j;
        this.zzc = zzjzVar;
        this.zzd = inputImage;
    }

    public final zzna zza() {
        return this.zza.zzd(this.zzb, this.zzc, this.zzd);
    }
}
