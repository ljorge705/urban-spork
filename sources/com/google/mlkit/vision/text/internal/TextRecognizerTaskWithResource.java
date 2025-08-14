package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.mlkit_vision_text_common.zzdx;
import com.google.android.gms.internal.mlkit_vision_text_common.zzdy;
import com.google.android.gms.internal.mlkit_vision_text_common.zzea;
import com.google.android.gms.internal.mlkit_vision_text_common.zzji;
import com.google.android.gms.internal.mlkit_vision_text_common.zzjl;
import com.google.android.gms.internal.mlkit_vision_text_common.zzjm;
import com.google.android.gms.internal.mlkit_vision_text_common.zzjq;
import com.google.android.gms.internal.mlkit_vision_text_common.zzjy;
import com.google.android.gms.internal.mlkit_vision_text_common.zzjz;
import com.google.android.gms.internal.mlkit_vision_text_common.zzka;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkb;
import com.google.android.gms.internal.mlkit_vision_text_common.zzlh;
import com.google.android.gms.internal.mlkit_vision_text_common.zzlj;
import com.google.android.gms.internal.mlkit_vision_text_common.zzlk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmx;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmz;
import com.google.android.gms.internal.mlkit_vision_text_common.zzna;
import com.google.android.gms.internal.mlkit_vision_text_common.zzni;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public class TextRecognizerTaskWithResource extends MLTask<Text, InputImage> {
    static boolean zza = true;
    private final zzj zzc;
    private final zzmx zzd;
    private final zzmz zze;
    private final TextRecognizerOptionsInterface zzf;
    private static final ImageUtils zzb = ImageUtils.getInstance();
    private static final TaskQueue taskQueue = new TaskQueue();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextRecognizerTaskWithResource(MlKitContext mlKitContext, TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super(taskQueue);
        zzmx zzmxVarZzb = zzni.zzb(textRecognizerOptionsInterface.getLoggingLibraryName());
        Context applicationContext = mlKitContext.getApplicationContext();
        zzj zzbVar = (GoogleApiAvailabilityLight.getInstance().getApkVersion(applicationContext) >= 204700000 || textRecognizerOptionsInterface.getIsThickClient()) ? new zzb(applicationContext, textRecognizerOptionsInterface) : new zzc(applicationContext);
        this.zzd = zzmxVarZzb;
        this.zzc = zzbVar;
        this.zze = zzmz.zza(MlKitContext.getInstance().getApplicationContext());
        this.zzf = textRecognizerOptionsInterface;
    }

    private final void zzf(zzjz zzjzVar, long j, InputImage inputImage) {
        final long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.zzd.zzf(new zzn(this, jElapsedRealtime, zzjzVar, inputImage), zzka.ON_DEVICE_TEXT_DETECT);
        zzdy zzdyVar = new zzdy();
        zzdyVar.zza(zzjzVar);
        zzdyVar.zzb(Boolean.valueOf(zza));
        zzlk zzlkVar = new zzlk();
        zzlkVar.zza(LoggingUtils.zza(this.zzf.getLoggingLanguageOption()));
        zzdyVar.zzc(zzlkVar.zzc());
        final zzea zzeaVarZzd = zzdyVar.zzd();
        final zzm zzmVar = new zzm(this);
        final zzmx zzmxVar = this.zzd;
        final zzka zzkaVar = zzka.AGGREGATED_ON_DEVICE_TEXT_DETECTION;
        final byte[] bArr = null;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzkaVar, zzeaVarZzd, jElapsedRealtime, zzmVar, bArr) { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzms
            public final /* synthetic */ zzka zzb;
            public final /* synthetic */ Object zzc;
            public final /* synthetic */ long zzd;
            public final /* synthetic */ com.google.mlkit.vision.text.internal.zzm zze;

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzc(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.zze.zzc(this.zzf.getLoggingEventId(), zzjzVar.zza(), jCurrentTimeMillis - jElapsedRealtime, jCurrentTimeMillis);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void load() throws MlKitException {
        this.zzc.zzb();
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void release() {
        zza = true;
        this.zzc.zzc();
    }

    @Override // com.google.mlkit.common.sdkinternal.MLTask
    /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final synchronized Text run(InputImage inputImage) throws MlKitException {
        Text textZza;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            textZza = this.zzc.zza(inputImage);
            zzf(zzjz.NO_ERROR, jElapsedRealtime, inputImage);
            zza = false;
        } catch (MlKitException e) {
            zzf(e.getErrorCode() == 14 ? zzjz.MODEL_NOT_DOWNLOADED : zzjz.UNKNOWN_ERROR, jElapsedRealtime, inputImage);
            throw e;
        }
        return textZza;
    }

    final /* synthetic */ zzna zzd(long j, zzjz zzjzVar, InputImage inputImage) {
        zzlh zzlhVar = new zzlh();
        zzjq zzjqVar = new zzjq();
        zzjqVar.zzc(Long.valueOf(j));
        zzjqVar.zzd(zzjzVar);
        zzjqVar.zze(Boolean.valueOf(zza));
        zzjqVar.zza(true);
        zzjqVar.zzb(true);
        zzlhVar.zzd(zzjqVar.zzf());
        ImageUtils imageUtils = zzb;
        int mobileVisionImageFormat = imageUtils.getMobileVisionImageFormat(inputImage);
        int mobileVisionImageSize = imageUtils.getMobileVisionImageSize(inputImage);
        zzjl zzjlVar = new zzjl();
        zzjlVar.zza(mobileVisionImageFormat != -1 ? mobileVisionImageFormat != 35 ? mobileVisionImageFormat != 842094169 ? mobileVisionImageFormat != 16 ? mobileVisionImageFormat != 17 ? zzjm.UNKNOWN_FORMAT : zzjm.NV21 : zzjm.NV16 : zzjm.YV12 : zzjm.YUV_420_888 : zzjm.BITMAP);
        zzjlVar.zzb(Integer.valueOf(mobileVisionImageSize));
        zzlhVar.zzc(zzjlVar.zzd());
        zzlk zzlkVar = new zzlk();
        zzlkVar.zza(LoggingUtils.zza(this.zzf.getLoggingLanguageOption()));
        zzlhVar.zze(zzlkVar.zzc());
        zzlj zzljVarZzf = zzlhVar.zzf();
        zzkb zzkbVar = new zzkb();
        zzkbVar.zze(this.zzf.getIsThickClient() ? zzjy.TYPE_THICK : zzjy.TYPE_THIN);
        zzkbVar.zzg(zzljVarZzf);
        return zzna.zzd(zzkbVar);
    }

    final /* synthetic */ zzna zze(zzea zzeaVar, int i, zzji zzjiVar) {
        zzkb zzkbVar = new zzkb();
        zzkbVar.zze(this.zzf.getIsThickClient() ? zzjy.TYPE_THICK : zzjy.TYPE_THIN);
        zzdx zzdxVar = new zzdx();
        zzdxVar.zza(Integer.valueOf(i));
        zzdxVar.zzc(zzeaVar);
        zzdxVar.zzb(zzjiVar);
        zzkbVar.zzd(zzdxVar.zze());
        return zzna.zzd(zzkbVar);
    }
}
