package com.google.mlkit.vision.face.internal;

import com.google.android.gms.internal.mlkit_vision_face.zzjx;
import com.google.android.gms.internal.mlkit_vision_face.zzjy;
import com.google.android.gms.internal.mlkit_vision_face.zzjz;
import com.google.android.gms.internal.mlkit_vision_face.zzka;
import com.google.android.gms.internal.mlkit_vision_face.zzkb;
import com.google.android.gms.internal.mlkit_vision_face.zzkd;
import com.google.android.gms.internal.mlkit_vision_face.zzkr;
import com.google.android.gms.internal.mlkit_vision_face.zzks;
import com.google.android.gms.internal.mlkit_vision_face.zzkt;
import com.google.android.gms.internal.mlkit_vision_face.zzku;
import com.google.android.gms.internal.mlkit_vision_face.zzlm;
import com.google.android.gms.internal.mlkit_vision_face.zznr;
import com.google.android.gms.internal.mlkit_vision_face.zzoa;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.android.gms.internal.mlkit_vision_face.zzof;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes2.dex */
public final class zzj {
    static final AtomicReference zza = new AtomicReference();

    public static zzkd zza(FaceDetectorOptions faceDetectorOptions) {
        zzjx zzjxVar = new zzjx();
        int iZzd = faceDetectorOptions.zzd();
        zzjxVar.zzd(iZzd != 1 ? iZzd != 2 ? zzka.UNKNOWN_LANDMARKS : zzka.ALL_LANDMARKS : zzka.NO_LANDMARKS);
        int iZzb = faceDetectorOptions.zzb();
        zzjxVar.zza(iZzb != 1 ? iZzb != 2 ? zzjy.UNKNOWN_CLASSIFICATIONS : zzjy.ALL_CLASSIFICATIONS : zzjy.NO_CLASSIFICATIONS);
        int iZze = faceDetectorOptions.zze();
        zzjxVar.zzf(iZze != 1 ? iZze != 2 ? zzkb.UNKNOWN_PERFORMANCE : zzkb.ACCURATE : zzkb.FAST);
        int iZzc = faceDetectorOptions.zzc();
        zzjxVar.zzb(iZzc != 1 ? iZzc != 2 ? zzjz.UNKNOWN_CONTOURS : zzjz.ALL_CONTOURS : zzjz.NO_CONTOURS);
        zzjxVar.zzc(Boolean.valueOf(faceDetectorOptions.zzg()));
        zzjxVar.zze(Float.valueOf(faceDetectorOptions.zza()));
        return zzjxVar.zzk();
    }

    public static String zzb() {
        return true != zzd() ? "play-services-mlkit-face-detection" : "face-detection";
    }

    public static void zzc(zzoc zzocVar, final boolean z, final zzks zzksVar) {
        zzocVar.zzf(new zzoa() { // from class: com.google.mlkit.vision.face.internal.zzi
            @Override // com.google.android.gms.internal.mlkit_vision_face.zzoa
            public final zznr zza() {
                boolean z2 = z;
                zzks zzksVar2 = zzksVar;
                zzku zzkuVar = new zzku();
                zzkuVar.zze(z2 ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
                zzlm zzlmVar = new zzlm();
                zzlmVar.zzb(zzksVar2);
                zzkuVar.zzh(zzlmVar.zzc());
                return zzof.zzf(zzkuVar);
            }
        }, zzkt.ON_DEVICE_FACE_LOAD);
    }

    static boolean zzd() {
        AtomicReference atomicReference = zza;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean zZzc = zza.zzc(MlKitContext.getInstance().getApplicationContext());
        atomicReference.set(Boolean.valueOf(zZzc));
        return zZzc;
    }
}
