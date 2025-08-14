package com.google.mlkit.vision.face.internal;

import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_face.zzdk;
import com.google.android.gms.internal.mlkit_vision_face.zzdl;
import com.google.android.gms.internal.mlkit_vision_face.zzdn;
import com.google.android.gms.internal.mlkit_vision_face.zzjv;
import com.google.android.gms.internal.mlkit_vision_face.zzke;
import com.google.android.gms.internal.mlkit_vision_face.zzkf;
import com.google.android.gms.internal.mlkit_vision_face.zzkj;
import com.google.android.gms.internal.mlkit_vision_face.zzkr;
import com.google.android.gms.internal.mlkit_vision_face.zzks;
import com.google.android.gms.internal.mlkit_vision_face.zzkt;
import com.google.android.gms.internal.mlkit_vision_face.zzku;
import com.google.android.gms.internal.mlkit_vision_face.zzlj;
import com.google.android.gms.internal.mlkit_vision_face.zzll;
import com.google.android.gms.internal.mlkit_vision_face.zznr;
import com.google.android.gms.internal.mlkit_vision_face.zzoa;
import com.google.android.gms.internal.mlkit_vision_face.zzoc;
import com.google.android.gms.internal.mlkit_vision_face.zzoe;
import com.google.android.gms.internal.mlkit_vision_face.zzof;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.BitmapInStreamingChecker;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-mlkit-face-detection@@17.1.0 */
/* loaded from: classes2.dex */
public final class zzh extends MLTask {
    static final AtomicBoolean zza = new AtomicBoolean(true);
    private static final ImageUtils zzb = ImageUtils.getInstance();
    private final FaceDetectorOptions zzc;
    private final zzoc zzd;
    private final zzoe zze;
    private final zzb zzf;
    private boolean zzg;
    private final BitmapInStreamingChecker zzh = new BitmapInStreamingChecker();

    public zzh(zzoc zzocVar, FaceDetectorOptions faceDetectorOptions, zzb zzbVar) {
        Preconditions.checkNotNull(faceDetectorOptions, "FaceDetectorOptions can not be null");
        this.zzc = faceDetectorOptions;
        this.zzd = zzocVar;
        this.zzf = zzbVar;
        this.zze = zzoe.zza(MlKitContext.getInstance().getApplicationContext());
    }

    static void zzf(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Face) it.next()).zzc(-1);
        }
    }

    private final synchronized void zzg(final zzks zzksVar, long j, final InputImage inputImage, final int i, final int i2) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.zzd.zzf(new zzoa() { // from class: com.google.mlkit.vision.face.internal.zzf
            @Override // com.google.android.gms.internal.mlkit_vision_face.zzoa
            public final zznr zza() {
                return this.zza.zzc(jElapsedRealtime, zzksVar, i, i2, inputImage);
            }
        }, zzkt.ON_DEVICE_FACE_DETECT);
        zzdl zzdlVar = new zzdl();
        zzdlVar.zzc(zzksVar);
        zzdlVar.zzd(Boolean.valueOf(zza.get()));
        zzdlVar.zza(Integer.valueOf(i));
        zzdlVar.zze(Integer.valueOf(i2));
        zzdlVar.zzb(zzj.zza(this.zzc));
        final zzdn zzdnVarZzf = zzdlVar.zzf();
        final zzg zzgVar = new zzg(this);
        final zzoc zzocVar = this.zzd;
        final zzkt zzktVar = zzkt.AGGREGATED_ON_DEVICE_FACE_DETECTION;
        final byte[] bArr = null;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzktVar, zzdnVarZzf, jElapsedRealtime, zzgVar, bArr) { // from class: com.google.android.gms.internal.mlkit_vision_face.zzny
            public final /* synthetic */ zzkt zzb;
            public final /* synthetic */ Object zzc;
            public final /* synthetic */ long zzd;
            public final /* synthetic */ com.google.mlkit.vision.face.internal.zzg zze;

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzh(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.zze.zzc(true != this.zzg ? 24303 : 24304, zzksVar.zza(), jCurrentTimeMillis - jElapsedRealtime, jCurrentTimeMillis);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void load() throws MlKitException {
        this.zzg = this.zzf.zzd();
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void release() {
        this.zzf.zzb();
        zza.set(true);
        zzoc zzocVar = this.zzd;
        zzku zzkuVar = new zzku();
        zzkuVar.zze(this.zzg ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzocVar.zzd(zzof.zzf(zzkuVar), zzkt.ON_DEVICE_FACE_CLOSE);
    }

    final /* synthetic */ zznr zzc(long j, zzks zzksVar, int i, int i2, InputImage inputImage) {
        zzlj zzljVar = new zzlj();
        zzkj zzkjVar = new zzkj();
        zzkjVar.zzc(Long.valueOf(j));
        zzkjVar.zzd(zzksVar);
        zzkjVar.zze(Boolean.valueOf(zza.get()));
        zzkjVar.zza(true);
        zzkjVar.zzb(true);
        zzljVar.zzg(zzkjVar.zzf());
        zzljVar.zze(zzj.zza(this.zzc));
        zzljVar.zzd(Integer.valueOf(i));
        zzljVar.zzh(Integer.valueOf(i2));
        ImageUtils imageUtils = zzb;
        int mobileVisionImageFormat = imageUtils.getMobileVisionImageFormat(inputImage);
        int mobileVisionImageSize = imageUtils.getMobileVisionImageSize(inputImage);
        zzke zzkeVar = new zzke();
        zzkeVar.zza(mobileVisionImageFormat != -1 ? mobileVisionImageFormat != 35 ? mobileVisionImageFormat != 842094169 ? mobileVisionImageFormat != 16 ? mobileVisionImageFormat != 17 ? zzkf.UNKNOWN_FORMAT : zzkf.NV21 : zzkf.NV16 : zzkf.YV12 : zzkf.YUV_420_888 : zzkf.BITMAP);
        zzkeVar.zzb(Integer.valueOf(mobileVisionImageSize));
        zzljVar.zzf(zzkeVar.zzd());
        zzll zzllVarZzi = zzljVar.zzi();
        zzku zzkuVar = new zzku();
        zzkuVar.zze(this.zzg ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzkuVar.zzg(zzllVarZzi);
        return zzof.zzf(zzkuVar);
    }

    final /* synthetic */ zznr zzd(zzdn zzdnVar, int i, zzjv zzjvVar) {
        zzku zzkuVar = new zzku();
        zzkuVar.zze(this.zzg ? zzkr.TYPE_THICK : zzkr.TYPE_THIN);
        zzdk zzdkVar = new zzdk();
        zzdkVar.zza(Integer.valueOf(i));
        zzdkVar.zzc(zzdnVar);
        zzdkVar.zzb(zzjvVar);
        zzkuVar.zzd(zzdkVar.zze());
        return zzof.zzf(zzkuVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010a A[Catch: MlKitException -> 0x0129, all -> 0x0148, TryCatch #1 {, blocks: (B:4:0x0005, B:5:0x000e, B:9:0x0021, B:10:0x002a, B:12:0x002d, B:40:0x0104, B:47:0x0118, B:46:0x0113, B:43:0x010a, B:15:0x003a, B:16:0x0041, B:17:0x004a, B:19:0x0050, B:20:0x005b, B:22:0x0061, B:24:0x006d, B:26:0x0073, B:28:0x0081, B:30:0x00ab, B:32:0x00cf, B:34:0x00de, B:54:0x012e, B:56:0x0136, B:58:0x013b, B:59:0x0147, B:57:0x0139, B:37:0x00f1, B:39:0x00fc), top: B:64:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0113 A[Catch: MlKitException -> 0x0129, all -> 0x0148, TryCatch #1 {, blocks: (B:4:0x0005, B:5:0x000e, B:9:0x0021, B:10:0x002a, B:12:0x002d, B:40:0x0104, B:47:0x0118, B:46:0x0113, B:43:0x010a, B:15:0x003a, B:16:0x0041, B:17:0x004a, B:19:0x0050, B:20:0x005b, B:22:0x0061, B:24:0x006d, B:26:0x0073, B:28:0x0081, B:30:0x00ab, B:32:0x00cf, B:34:0x00de, B:54:0x012e, B:56:0x0136, B:58:0x013b, B:59:0x0147, B:57:0x0139, B:37:0x00f1, B:39:0x00fc), top: B:64:0x0005 }] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.util.List] */
    @Override // com.google.mlkit.common.sdkinternal.MLTask
    /* renamed from: zze, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.util.List run(com.google.mlkit.vision.common.InputImage r21) throws com.google.mlkit.common.MlKitException {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.face.internal.zzh.run(com.google.mlkit.vision.common.InputImage):java.util.List");
    }
}
