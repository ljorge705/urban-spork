package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;

/* compiled from: com.google.android.gms:play-services-measurement@@21.3.0 */
/* loaded from: classes3.dex */
public final class zzfl {
    final zzgd zza;

    zzfl(zzlh zzlhVar) {
        this.zza = zzlhVar.zzp();
    }

    final boolean zza() {
        try {
            PackageManagerWrapper packageManagerWrapperPackageManager = Wrappers.packageManager(this.zza.zzaw());
            if (packageManagerWrapperPackageManager != null) {
                return packageManagerWrapperPackageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.zza.zzaA().zzj().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
            return false;
        } catch (Exception e) {
            this.zza.zzaA().zzj().zzb("Failed to retrieve Play Store version for Install Referrer", e);
            return false;
        }
    }
}
