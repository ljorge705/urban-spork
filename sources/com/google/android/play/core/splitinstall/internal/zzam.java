package com.google.android.play.core.splitinstall.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: com.google.android.play:feature-delivery@@2.1.0 */
/* loaded from: classes3.dex */
public final class zzam {
    private final com.google.android.play.core.splitcompat.zze zza;
    private final zzah zzb;
    private final Context zzc;
    private final zzal zzd;
    private PackageInfo zze;

    public zzam(Context context, com.google.android.play.core.splitcompat.zze zzeVar, zzah zzahVar) {
        zzal zzalVar = new zzal(new com.google.android.play.core.splitcompat.zza(zzeVar));
        this.zza = zzeVar;
        this.zzb = zzahVar;
        this.zzc = context;
        this.zzd = zzalVar;
    }

    private final PackageInfo zzd() {
        if (this.zze == null) {
            try {
                this.zze = this.zzc.getPackageManager().getPackageInfo(this.zzc.getPackageName(), 64);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        return this.zze;
    }

    private static X509Certificate zze(Signature signature) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        } catch (CertificateException e) {
            Log.e("SplitCompat", "Cannot decode certificate.", e);
            return null;
        }
    }

    public final boolean zza(File[] fileArr) throws XmlPullParserException, IOException {
        long longVersionCode = Build.VERSION.SDK_INT >= 28 ? zzd().getLongVersionCode() : r0.versionCode;
        AssetManager assetManager = (AssetManager) zzbk.zzc(AssetManager.class);
        int length = fileArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
            this.zzd.zzb(assetManager, fileArr[length]);
        } while (longVersionCode == this.zzd.zza());
        return false;
    }

    public final boolean zzb(List list) throws IOException {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!this.zza.zzg(((Intent) it.next()).getStringExtra("split_id")).exists()) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0083, code lost:
    
        android.util.Log.e("SplitCompat", "Downloaded split " + r6 + " is not signed.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzc(java.io.File[] r13) {
        /*
            r12 = this;
            java.lang.String r0 = " is not signed."
            java.lang.String r1 = "Downloaded split "
            android.content.pm.PackageInfo r2 = r12.zzd()
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L2a
            android.content.pm.Signature[] r5 = r2.signatures
            if (r5 != 0) goto L11
            goto L2a
        L11:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            android.content.pm.Signature[] r2 = r2.signatures
            int r5 = r2.length
            r6 = r4
        L1a:
            if (r6 >= r5) goto L2a
            r7 = r2[r6]
            java.security.cert.X509Certificate r7 = zze(r7)
            if (r7 == 0) goto L27
            r3.add(r7)
        L27:
            int r6 = r6 + 1
            goto L1a
        L2a:
            java.lang.String r2 = "SplitCompat"
            if (r3 == 0) goto Lbe
            boolean r5 = r3.isEmpty()
            if (r5 == 0) goto L36
            goto Lbe
        L36:
            int r5 = r13.length
        L37:
            int r5 = r5 + (-1)
            if (r5 < 0) goto Lbc
            r6 = r13[r5]
            java.lang.String r6 = r6.getAbsolutePath()     // Catch: java.lang.Exception -> Lb5
            java.security.cert.X509Certificate[][] r7 = com.google.android.play.core.splitinstall.internal.zzi.zza(r6)     // Catch: java.lang.Exception -> L99
            if (r7 == 0) goto L83
            int r8 = r7.length     // Catch: java.lang.Exception -> Lb5
            if (r8 == 0) goto L83
            r8 = r7[r4]     // Catch: java.lang.Exception -> Lb5
            int r8 = r8.length     // Catch: java.lang.Exception -> Lb5
            if (r8 != 0) goto L50
            goto L83
        L50:
            boolean r6 = r3.isEmpty()     // Catch: java.lang.Exception -> Lb5
            if (r6 == 0) goto L5c
            java.lang.String r13 = "No certificates found for app."
            android.util.Log.e(r2, r13)     // Catch: java.lang.Exception -> Lb5
            goto Laf
        L5c:
            java.util.Iterator r6 = r3.iterator()     // Catch: java.lang.Exception -> Lb5
        L60:
            boolean r8 = r6.hasNext()     // Catch: java.lang.Exception -> Lb5
            if (r8 == 0) goto L37
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Exception -> Lb5
            java.security.cert.X509Certificate r8 = (java.security.cert.X509Certificate) r8     // Catch: java.lang.Exception -> Lb5
            int r9 = r7.length     // Catch: java.lang.Exception -> Lb5
            r10 = r4
        L6e:
            if (r10 >= r9) goto L7d
            r11 = r7[r10]     // Catch: java.lang.Exception -> Lb5
            r11 = r11[r4]     // Catch: java.lang.Exception -> Lb5
            boolean r11 = r11.equals(r8)     // Catch: java.lang.Exception -> Lb5
            if (r11 != 0) goto L60
            int r10 = r10 + 1
            goto L6e
        L7d:
            java.lang.String r13 = "There's an app certificate that doesn't sign the split."
            android.util.Log.i(r2, r13)     // Catch: java.lang.Exception -> Lb5
            goto Laf
        L83:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb5
            r13.<init>()     // Catch: java.lang.Exception -> Lb5
            r13.append(r1)     // Catch: java.lang.Exception -> Lb5
            r13.append(r6)     // Catch: java.lang.Exception -> Lb5
            r13.append(r0)     // Catch: java.lang.Exception -> Lb5
            java.lang.String r13 = r13.toString()     // Catch: java.lang.Exception -> Lb5
            android.util.Log.e(r2, r13)     // Catch: java.lang.Exception -> Lb5
            goto Laf
        L99:
            r13 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb5
            r3.<init>()     // Catch: java.lang.Exception -> Lb5
            r3.append(r1)     // Catch: java.lang.Exception -> Lb5
            r3.append(r6)     // Catch: java.lang.Exception -> Lb5
            r3.append(r0)     // Catch: java.lang.Exception -> Lb5
            java.lang.String r0 = r3.toString()     // Catch: java.lang.Exception -> Lb5
            android.util.Log.e(r2, r0, r13)     // Catch: java.lang.Exception -> Lb5
        Laf:
            java.lang.String r13 = "Split verification failure."
            android.util.Log.e(r2, r13)     // Catch: java.lang.Exception -> Lb5
            return r4
        Lb5:
            r13 = move-exception
            java.lang.String r0 = "Split verification error."
            android.util.Log.e(r2, r0, r13)
            return r4
        Lbc:
            r13 = 1
            return r13
        Lbe:
            java.lang.String r13 = "No app certificates found."
            android.util.Log.e(r2, r13)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.internal.zzam.zzc(java.io.File[]):boolean");
    }
}
