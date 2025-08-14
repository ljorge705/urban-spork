package com.google.mlkit.vision.face.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_face.zzks;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes2.dex */
final class zzm implements zzb {
    private boolean zza;
    private final Context zzb;
    private final FaceDetectorOptions zzc;
    private final int zzd;
    private final zzoc zze;
    private com.google.android.gms.internal.mlkit_vision_face.zzj zzf;
    private com.google.android.gms.internal.mlkit_vision_face.zzj zzg;

    zzm(Context context, FaceDetectorOptions faceDetectorOptions, zzoc zzocVar) {
        this.zzb = context;
        this.zzc = faceDetectorOptions;
        this.zzd = GoogleApiAvailabilityLight.getInstance().getApkVersion(context);
        this.zze = zzocVar;
    }

    static int zzc(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new IllegalArgumentException("Invalid classification type: " + i);
    }

    static int zze(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new IllegalArgumentException("Invalid landmark type: " + i);
    }

    private static int zzf(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new IllegalArgumentException("Invalid mode type: " + i);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a5 A[LOOP:0: B:12:0x00a3->B:13:0x00a5, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List zzg(com.google.android.gms.internal.mlkit_vision_face.zzj r15, com.google.mlkit.vision.common.InputImage r16) throws com.google.mlkit.common.MlKitException {
        /*
            r14 = this;
            com.google.android.gms.internal.mlkit_vision_face.zzp r11 = new com.google.android.gms.internal.mlkit_vision_face.zzp     // Catch: android.os.RemoteException -> Lb9
            int r2 = r16.getWidth()     // Catch: android.os.RemoteException -> Lb9
            int r3 = r16.getHeight()     // Catch: android.os.RemoteException -> Lb9
            r4 = 0
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch: android.os.RemoteException -> Lb9
            int r0 = r16.getRotationDegrees()     // Catch: android.os.RemoteException -> Lb9
            int r7 = com.google.mlkit.vision.common.internal.CommonConvertUtils.convertToMVRotation(r0)     // Catch: android.os.RemoteException -> Lb9
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r7)     // Catch: android.os.RemoteException -> Lb9
            int r0 = r16.getFormat()     // Catch: android.os.RemoteException -> Lb9
            r1 = 35
            r12 = 0
            if (r0 != r1) goto L89
            r13 = r14
            int r0 = r13.zzd     // Catch: android.os.RemoteException -> Lb7
            r1 = 201500000(0xc02a560, float:1.0064601E-31)
            if (r0 < r1) goto L8a
            android.media.Image$Plane[] r0 = r16.getPlanes()     // Catch: android.os.RemoteException -> Lb7
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch: android.os.RemoteException -> Lb7
            android.media.Image$Plane[] r0 = (android.media.Image.Plane[]) r0     // Catch: android.os.RemoteException -> Lb7
            r1 = r0[r12]     // Catch: android.os.RemoteException -> Lb7
            java.nio.ByteBuffer r1 = r1.getBuffer()     // Catch: android.os.RemoteException -> Lb7
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r1)     // Catch: android.os.RemoteException -> Lb7
            r1 = 1
            r3 = r0[r1]     // Catch: android.os.RemoteException -> Lb7
            java.nio.ByteBuffer r3 = r3.getBuffer()     // Catch: android.os.RemoteException -> Lb7
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r3)     // Catch: android.os.RemoteException -> Lb7
            r4 = 2
            r5 = r0[r4]     // Catch: android.os.RemoteException -> Lb7
            java.nio.ByteBuffer r5 = r5.getBuffer()     // Catch: android.os.RemoteException -> Lb7
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch: android.os.RemoteException -> Lb7
            r6 = r0[r12]     // Catch: android.os.RemoteException -> Lb7
            int r6 = r6.getPixelStride()     // Catch: android.os.RemoteException -> Lb7
            r7 = r0[r1]     // Catch: android.os.RemoteException -> Lb7
            int r7 = r7.getPixelStride()     // Catch: android.os.RemoteException -> Lb7
            r8 = r0[r4]     // Catch: android.os.RemoteException -> Lb7
            int r8 = r8.getPixelStride()     // Catch: android.os.RemoteException -> Lb7
            r9 = r0[r12]     // Catch: android.os.RemoteException -> Lb7
            int r9 = r9.getRowStride()     // Catch: android.os.RemoteException -> Lb7
            r1 = r0[r1]     // Catch: android.os.RemoteException -> Lb7
            int r10 = r1.getRowStride()     // Catch: android.os.RemoteException -> Lb7
            r0 = r0[r4]     // Catch: android.os.RemoteException -> Lb7
            int r0 = r0.getRowStride()     // Catch: android.os.RemoteException -> Lb7
            r1 = r15
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r0
            com.google.android.gms.internal.mlkit_vision_face.zzf[] r0 = r1.zzf(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch: android.os.RemoteException -> Lb7
            r1 = r16
            goto L9d
        L89:
            r13 = r14
        L8a:
            com.google.mlkit.vision.common.internal.ImageConvertUtils r0 = com.google.mlkit.vision.common.internal.ImageConvertUtils.getInstance()     // Catch: android.os.RemoteException -> Lb7
            r1 = r16
            java.nio.ByteBuffer r0 = r0.convertToNv21Buffer(r1, r12)     // Catch: android.os.RemoteException -> Lb7
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r0)     // Catch: android.os.RemoteException -> Lb7
            r2 = r15
            com.google.android.gms.internal.mlkit_vision_face.zzf[] r0 = r15.zze(r0, r11)     // Catch: android.os.RemoteException -> Lb7
        L9d:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r3 = r0.length
        La3:
            if (r12 >= r3) goto Lb6
            r4 = r0[r12]
            com.google.mlkit.vision.face.Face r5 = new com.google.mlkit.vision.face.Face
            android.graphics.Matrix r6 = r16.getCoordinatesMatrix()
            r5.<init>(r4, r6)
            r2.add(r5)
            int r12 = r12 + 1
            goto La3
        Lb6:
            return r2
        Lb7:
            r0 = move-exception
            goto Lbb
        Lb9:
            r0 = move-exception
            r13 = r14
        Lbb:
            com.google.mlkit.common.MlKitException r1 = new com.google.mlkit.common.MlKitException
            java.lang.String r2 = "Failed to detect with legacy face detector"
            r3 = 13
            r1.<init>(r2, r3, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.face.internal.zzm.zzg(com.google.android.gms.internal.mlkit_vision_face.zzj, com.google.mlkit.vision.common.InputImage):java.util.List");
    }

    @Override // com.google.mlkit.vision.face.internal.zzb
    public final Pair zza(InputImage inputImage) throws MlKitException {
        List listZzg;
        if (this.zzf == null && this.zzg == null) {
            zzd();
        }
        com.google.android.gms.internal.mlkit_vision_face.zzj zzjVar = this.zzf;
        if (zzjVar == null && this.zzg == null) {
            throw new MlKitException("Waiting for the face detection module to be downloaded. Please wait.", 14);
        }
        List listZzg2 = null;
        if (zzjVar != null) {
            listZzg = zzg(zzjVar, inputImage);
            if (!this.zzc.zzg()) {
                zzh.zzf(listZzg);
            }
        } else {
            listZzg = null;
        }
        com.google.android.gms.internal.mlkit_vision_face.zzj zzjVar2 = this.zzg;
        if (zzjVar2 != null) {
            listZzg2 = zzg(zzjVar2, inputImage);
            zzh.zzf(listZzg2);
        }
        return new Pair(listZzg, listZzg2);
    }

    @Override // com.google.mlkit.vision.face.internal.zzb
    public final void zzb() {
        com.google.android.gms.internal.mlkit_vision_face.zzj zzjVar = this.zzf;
        if (zzjVar != null) {
            try {
                zzjVar.zzd();
            } catch (RemoteException e) {
                Log.e("LegacyFaceDelegate", "Failed to release legacy face detector.", e);
            }
            this.zzf = null;
        }
        com.google.android.gms.internal.mlkit_vision_face.zzj zzjVar2 = this.zzg;
        if (zzjVar2 != null) {
            try {
                zzjVar2.zzd();
            } catch (RemoteException e2) {
                Log.e("LegacyFaceDelegate", "Failed to release legacy face detector.", e2);
            }
            this.zzg = null;
        }
    }

    @Override // com.google.mlkit.vision.face.internal.zzb
    public final boolean zzd() throws MlKitException {
        if (this.zzf != null || this.zzg != null) {
            return false;
        }
        try {
            com.google.android.gms.internal.mlkit_vision_face.zzm zzmVarZza = com.google.android.gms.internal.mlkit_vision_face.zzl.zza(DynamiteModule.load(this.zzb, DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID).instantiate("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
            IObjectWrapper iObjectWrapperWrap = ObjectWrapper.wrap(this.zzb);
            if (this.zzc.zzc() == 2) {
                if (this.zzg == null) {
                    this.zzg = zzmVarZza.zzd(iObjectWrapperWrap, new com.google.android.gms.internal.mlkit_vision_face.zzh(2, 2, 0, true, false, this.zzc.zza()));
                }
                if ((this.zzc.zzd() == 2 || this.zzc.zzb() == 2 || this.zzc.zze() == 2) && this.zzf == null) {
                    this.zzf = zzmVarZza.zzd(iObjectWrapperWrap, new com.google.android.gms.internal.mlkit_vision_face.zzh(zzf(this.zzc.zze()), zze(this.zzc.zzd()), zzc(this.zzc.zzb()), false, this.zzc.zzg(), this.zzc.zza()));
                }
            } else if (this.zzf == null) {
                this.zzf = zzmVarZza.zzd(iObjectWrapperWrap, new com.google.android.gms.internal.mlkit_vision_face.zzh(zzf(this.zzc.zze()), zze(this.zzc.zzd()), zzc(this.zzc.zzb()), false, this.zzc.zzg(), this.zzc.zza()));
            }
            if (this.zzf == null && this.zzg == null && !this.zza) {
                Log.d("LegacyFaceDelegate", "Request face optional module download.");
                OptionalModuleUtils.requestDownload(this.zzb, OptionalModuleUtils.BARCODE);
                this.zza = true;
            }
            zzj.zzc(this.zze, false, zzks.NO_ERROR);
            return false;
        } catch (RemoteException e) {
            throw new MlKitException("Failed to create legacy face detector.", 13, e);
        } catch (DynamiteModule.LoadingException e2) {
            throw new MlKitException("Failed to load deprecated vision dynamite module.", 13, e2);
        }
    }
}
