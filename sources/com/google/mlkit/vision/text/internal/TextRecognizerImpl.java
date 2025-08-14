package com.google.mlkit.vision.text.internal;

import com.google.android.gms.internal.mlkit_vision_text_common.zzjy;
import com.google.android.gms.internal.mlkit_vision_text_common.zzka;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkb;
import com.google.android.gms.internal.mlkit_vision_text_common.zzlh;
import com.google.android.gms.internal.mlkit_vision_text_common.zzlk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmx;
import com.google.android.gms.internal.mlkit_vision_text_common.zzna;
import com.google.android.gms.tasks.Task;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
public class TextRecognizerImpl extends MobileVisionBase<Text> implements TextRecognizer {
    TextRecognizerImpl(TextRecognizerTaskWithResource textRecognizerTaskWithResource, Executor executor, zzmx zzmxVar, TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super(textRecognizerTaskWithResource, executor);
        zzkb zzkbVar = new zzkb();
        zzkbVar.zze(textRecognizerOptionsInterface.getIsThickClient() ? zzjy.TYPE_THICK : zzjy.TYPE_THIN);
        zzlh zzlhVar = new zzlh();
        zzlk zzlkVar = new zzlk();
        zzlkVar.zza(LoggingUtils.zza(textRecognizerOptionsInterface.getLoggingLanguageOption()));
        zzlhVar.zze(zzlkVar.zzc());
        zzkbVar.zzg(zzlhVar.zzf());
        zzmxVar.zzd(zzna.zze(zzkbVar, 1), zzka.ON_DEVICE_TEXT_CREATE);
    }

    @Override // com.google.mlkit.vision.interfaces.Detector
    public final int getDetectorType() {
        return 4;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizer
    public final Task<Text> process(MlImage mlImage) {
        return super.processBase(mlImage);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizer
    public final Task<Text> process(InputImage inputImage) {
        return super.processBase(inputImage);
    }
}
