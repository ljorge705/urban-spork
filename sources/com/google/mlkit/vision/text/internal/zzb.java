package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_text_common.zznl;
import com.google.android.gms.internal.mlkit_vision_text_common.zznn;
import com.google.android.gms.internal.mlkit_vision_text_common.zznp;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes2.dex */
final class zzb implements zzj {
    private final Context zza;
    private final TextRecognizerOptionsInterface zzb;
    private boolean zzc;
    private boolean zzd;
    private zznn zze;

    zzb(Context context, TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        this.zza = context;
        this.zzb = textRecognizerOptionsInterface;
    }

    @Override // com.google.mlkit.vision.text.internal.zzj
    public final Text zza(InputImage inputImage) throws MlKitException {
        if (this.zze == null) {
            zzb();
        }
        zznn zznnVar = (zznn) Preconditions.checkNotNull(this.zze);
        if (!this.zzc) {
            try {
                zznnVar.zze();
                this.zzc = true;
            } catch (RemoteException e) {
                String strValueOf = String.valueOf(this.zzb.getLoggingLibraryName());
                throw new MlKitException(strValueOf.length() != 0 ? "Failed to init text recognizer ".concat(strValueOf) : new String("Failed to init text recognizer "), 13, e);
            }
        }
        try {
            return new Text(zznnVar.zzd(ImageUtils.getInstance().getImageDataWrapper(inputImage), new zznl(inputImage.getFormat(), inputImage.getWidth(), inputImage.getHeight(), CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()), SystemClock.elapsedRealtime())), inputImage.getCoordinatesMatrix());
        } catch (RemoteException e2) {
            String strValueOf2 = String.valueOf(this.zzb.getLoggingLibraryName());
            throw new MlKitException(strValueOf2.length() != 0 ? "Failed to run text recognizer ".concat(strValueOf2) : new String("Failed to run text recognizer "), 13, e2);
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzj
    public final void zzb() throws MlKitException {
        if (this.zze == null) {
            try {
                this.zze = zznp.zza(DynamiteModule.load(this.zza, this.zzb.getIsThickClient() ? DynamiteModule.PREFER_LOCAL : DynamiteModule.PREFER_REMOTE, this.zzb.getModuleId()).instantiate(this.zzb.getCreatorClass())).zzd(ObjectWrapper.wrap(this.zza));
            } catch (RemoteException e) {
                String strValueOf = String.valueOf(this.zzb.getLoggingLibraryName());
                throw new MlKitException(strValueOf.length() != 0 ? "Failed to create text recognizer ".concat(strValueOf) : new String("Failed to create text recognizer "), 13, e);
            } catch (DynamiteModule.LoadingException e2) {
                if (this.zzb.getIsThickClient()) {
                    throw new MlKitException(String.format("Failed to load text module %s. %s", this.zzb.getLoggingLibraryName(), e2.getMessage()), 13, e2);
                }
                if (!this.zzd) {
                    OptionalModuleUtils.requestDownload(this.zza, OptionalModuleUtils.OCR);
                    this.zzd = true;
                }
                throw new MlKitException("Waiting for the text optional module to be downloaded. Please wait.", 14);
            }
        }
    }

    @Override // com.google.mlkit.vision.text.internal.zzj
    public final void zzc() {
        zznn zznnVar = this.zze;
        if (zznnVar != null) {
            try {
                zznnVar.zzf();
            } catch (RemoteException e) {
                String strValueOf = String.valueOf(this.zzb.getLoggingLibraryName());
                Log.e("DecoupledTextDelegate", strValueOf.length() != 0 ? "Failed to release text recognizer ".concat(strValueOf) : new String("Failed to release text recognizer "), e);
            }
            this.zze = null;
        }
        this.zzc = false;
    }
}
