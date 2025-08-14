package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.Preconditions;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.PermissionsTracker;

/* compiled from: com.google.android.gms:play-services-maps@@18.2.0 */
/* loaded from: classes3.dex */
public final class zzcb {
    private zzcb() {
    }

    public static Parcelable zza(Bundle bundle, String str) {
        ClassLoader classLoaderZzd = zzd();
        bundle.setClassLoader(classLoaderZzd);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(classLoaderZzd);
        return bundle2.getParcelable(str);
    }

    public static void zzb(Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        Parcelable parcelableZza = zza(bundle, "MapOptions");
        if (parcelableZza != null) {
            zzc(bundle2, "MapOptions", parcelableZza);
        }
        Parcelable parcelableZza2 = zza(bundle, "StreetViewPanoramaOptions");
        if (parcelableZza2 != null) {
            zzc(bundle2, "StreetViewPanoramaOptions", parcelableZza2);
        }
        Parcelable parcelableZza3 = zza(bundle, PermissionsTracker.CAMERA);
        if (parcelableZza3 != null) {
            zzc(bundle2, PermissionsTracker.CAMERA, parcelableZza3);
        }
        if (bundle.containsKey(ViewProps.POSITION)) {
            bundle2.putString(ViewProps.POSITION, bundle.getString(ViewProps.POSITION));
        }
        if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
            bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
        }
    }

    public static void zzc(Bundle bundle, String str, Parcelable parcelable) {
        ClassLoader classLoaderZzd = zzd();
        bundle.setClassLoader(classLoaderZzd);
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(classLoaderZzd);
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }

    private static ClassLoader zzd() {
        return (ClassLoader) Preconditions.checkNotNull(zzcb.class.getClassLoader());
    }
}
