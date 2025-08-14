package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public abstract class zzz extends zzb implements zzaa {
    public static zzaa zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMapCapabilitiesDelegate");
        return iInterfaceQueryLocalInterface instanceof zzaa ? (zzaa) iInterfaceQueryLocalInterface : new zzy(iBinder);
    }
}
