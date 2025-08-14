package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-mlkit-text-recognition-common@@17.0.0 */
/* loaded from: classes3.dex */
public abstract class zznp extends zzb implements zznq {
    public static zznq zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizerCreator");
        return iInterfaceQueryLocalInterface instanceof zznq ? (zznq) iInterfaceQueryLocalInterface : new zzno(iBinder);
    }
}
