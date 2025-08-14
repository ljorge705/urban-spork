package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;

/* compiled from: com.google.android.gms:play-services-basement@@18.2.0 */
@CheckReturnValue
@RestrictedInheritance(allowedOnPath = ".*javatests.*/com/google/android/gms/common/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
/* loaded from: classes3.dex */
public class PackageSignatureVerifier {
    static volatile zzac zza;
    private static zzad zzb;

    private static zzad zza() {
        zzad zzadVar;
        synchronized (zzad.class) {
            if (zzb == null) {
                zzb = new zzad();
            }
            zzadVar = zzb;
        }
        return zzadVar;
    }

    public PackageVerificationResult queryPackageSignatureVerified(Context context, String str) {
        boolean zHonorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(context);
        zza();
        if (!zzn.zzf()) {
            throw new zzae();
        }
        String strConcat = String.valueOf(str).concat(true != zHonorsDebugCertificates ? "-0" : "-1");
        if (zza != null && zza.zza.equals(strConcat)) {
            return zza.zzb;
        }
        zza();
        zzx zzxVarZzc = zzn.zzc(str, zHonorsDebugCertificates, false, false);
        if (zzxVarZzc.zza) {
            zza = new zzac(strConcat, PackageVerificationResult.zzd(str, zzxVarZzc.zzd));
            return zza.zzb;
        }
        Preconditions.checkNotNull(zzxVarZzc.zzb);
        return PackageVerificationResult.zza(str, zzxVarZzc.zzb, zzxVarZzc.zzc);
    }

    public PackageVerificationResult queryPackageSignatureVerifiedWithRetry(Context context, String str) {
        try {
            PackageVerificationResult packageVerificationResultQueryPackageSignatureVerified = queryPackageSignatureVerified(context, str);
            packageVerificationResultQueryPackageSignatureVerified.zzb();
            return packageVerificationResultQueryPackageSignatureVerified;
        } catch (SecurityException e) {
            PackageVerificationResult packageVerificationResultQueryPackageSignatureVerified2 = queryPackageSignatureVerified(context, str);
            if (!packageVerificationResultQueryPackageSignatureVerified2.zzc()) {
                return packageVerificationResultQueryPackageSignatureVerified2;
            }
            Log.e("PkgSignatureVerifier", "Got flaky result during package signature verification", e);
            return packageVerificationResultQueryPackageSignatureVerified2;
        }
    }
}
