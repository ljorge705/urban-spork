package com.google.android.odml.image;

import android.media.Image;

/* compiled from: com.google.android.odml:image@@1.0.0-beta1 */
/* loaded from: classes3.dex */
public class MediaImageExtractor {
    private MediaImageExtractor() {
    }

    public static Image extract(MlImage mlImage) {
        zzg zzgVarZza = mlImage.zza();
        if (zzgVarZza.zzb().getStorageType() == 3) {
            return ((zzi) zzgVarZza).zza();
        }
        throw new IllegalArgumentException("Extract Media Image from an MlImage created by objects other than Media Image is not supported");
    }
}
