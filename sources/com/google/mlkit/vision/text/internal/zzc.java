package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import com.google.mlkit.vision.text.Text;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
final class zzc implements zzj {
    private final Context zza;
    private final com.google.android.gms.internal.mlkit_vision_text_common.zzp zzb = new com.google.android.gms.internal.mlkit_vision_text_common.zzp(null);
    private boolean zzc;
    private com.google.android.gms.internal.mlkit_vision_text_common.zzh zzd;

    zzc(Context context) {
        this.zza = context;
    }

    @Override // com.google.mlkit.vision.text.internal.zzj
    public final Text zza(InputImage inputImage) throws MlKitException {
        Bitmap bitmapConvertToUpRightBitmap;
        int iConvertToMVRotation;
        if (this.zzd == null) {
            zzb();
        }
        if (this.zzd == null) {
            throw new MlKitException("Waiting for the text recognition module to be downloaded. Please wait.", 14);
        }
        if (inputImage.getFormat() == -1) {
            bitmapConvertToUpRightBitmap = inputImage.getBitmapInternal();
            iConvertToMVRotation = CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees());
        } else {
            bitmapConvertToUpRightBitmap = ImageConvertUtils.getInstance().convertToUpRightBitmap(inputImage);
            iConvertToMVRotation = 0;
        }
        try {
            return zzi.zza(((com.google.android.gms.internal.mlkit_vision_text_common.zzh) Preconditions.checkNotNull(this.zzd)).zze(ObjectWrapper.wrap(bitmapConvertToUpRightBitmap), new com.google.android.gms.internal.mlkit_vision_text_common.zzd(inputImage.getWidth(), inputImage.getHeight(), 0, 0L, iConvertToMVRotation)), inputImage.getCoordinatesMatrix());
        } catch (RemoteException e) {
            throw new MlKitException("Failed to run legacy text recognizer.", 13, e);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzj
    public final void zzb() throws MlKitException {
        if (this.zzd == null) {
            try {
                com.google.android.gms.internal.mlkit_vision_text_common.zzh zzhVarZzd = com.google.android.gms.internal.mlkit_vision_text_common.zzj.zza(DynamiteModule.load(this.zza, DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID).instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator")).zzd(ObjectWrapper.wrap(this.zza), this.zzb);
                this.zzd = zzhVarZzd;
                if (zzhVarZzd != null || this.zzc) {
                    return;
                }
                Log.d("LegacyTextDelegate", "Request OCR optional module download.");
                OptionalModuleUtils.requestDownload(this.zza, OptionalModuleUtils.OCR);
                this.zzc = true;
            } catch (RemoteException e) {
                throw new MlKitException("Failed to create legacy text recognizer.", 13, e);
            } catch (DynamiteModule.LoadingException e2) {
                throw new MlKitException("Failed to load deprecated vision dynamite module.", 13, e2);
            }
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzj
    public final void zzc() {
        com.google.android.gms.internal.mlkit_vision_text_common.zzh zzhVar = this.zzd;
        if (zzhVar != null) {
            try {
                zzhVar.zzd();
            } catch (RemoteException e) {
                Log.e("LegacyTextDelegate", "Failed to release legacy text recognizer.", e);
            }
            this.zzd = null;
        }
    }
}
